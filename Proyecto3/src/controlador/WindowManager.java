package controlador;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;

import org.jdesktop.swingx.JXDatePicker;

import com.formdev.flatlaf.FlatLightLaf;

import modelo.Admin;
import modelo.Cama;
import modelo.Empleado;
import modelo.Grupo;
import modelo.Habitacion;
import modelo.Huesped;
import modelo.ProductoMenu;
import modelo.Servicio;
import modelo.Tarifa;
import modelo.TipoHabitacion;
import modelo.Usuario;
import vistaAdmin.AdminMenuPrincipal;
import vistaAdmin.AdminReportesFrame;
import vistaAdmin.AutenticacionFrame;
import vistaAdmin.FrameBaseInfo;
import vistaEmpleado.EmpleadoMenuPrincipal;

public class WindowManager {
	protected JFrame ventanaActual;
	protected JFrame menu;
	protected JFrame autenticacionFrame;
	protected Usuario usuarioActual;
	private JFrame pruebas;
		
	
	public WindowManager() {
		Usuario.setHotel();
		setDay();
		Usuario.cargarInformacion();

	}
	
	
	public void setDay() {
		
	    JFrame setDayFrame = new JFrame();

	    // Crear un JXDatePicker con la fecha actual
	    JXDatePicker datePicker = new JXDatePicker(new Date());
	    JLabel mensaje = new JLabel("Seleccione una fecha"); 
	    JPanel panel = new JPanel();
	    panel.add(mensaje);
	    panel.add(datePicker);

	    JOptionPane.showOptionDialog(setDayFrame, panel, "Establecer fecha del Hotel", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);

	    Date fecha = datePicker.getDate();
		Usuario.setHoy(fecha);
	

	
		
	}
	
	public Date getHoy() {
		Huesped huesped = (Huesped) usuarioActual;
		return huesped.getHoy();
	}

	public void mostraVentana(JFrame ventana) {
		if(ventanaActual != null) {
			ventanaActual.dispose();
		}
		
		ventanaActual = ventana;
		try {
			((FrameBaseInfo)ventanaActual).resetDatos();
		}
		catch (ClassCastException e) {
			try {
				((EmpleadoMenuPrincipal)ventanaActual).resetDatos();
			}
			catch (ClassCastException e1) {
				try {
					((AdminReportesFrame)ventanaActual).resetDatos();
				}
				catch (ClassCastException e2) {
				}
			}
		}
		
		// configuraciones generales
		ventanaActual.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventanaActual.setExtendedState(JFrame.MAXIMIZED_BOTH);
		ventanaActual.setResizable(false);
		ventanaActual.setVisible(true);
		
		ventanaActual.addWindowListener(new WindowAdapter() {
	        @Override
	        public void windowClosing(WindowEvent e) {
	            Usuario.guardarInformacion();
	        }
	    });
		
	}
	
	public void mostraVentanaPagos(JFrame ventana) {		
		ventanaActual = ventana;
		try {
			((FrameBaseInfo)ventanaActual).resetDatos();
		}
		catch (ClassCastException e) {
			try {
				((EmpleadoMenuPrincipal)ventanaActual).resetDatos();
			}
			catch (ClassCastException e1) {
			}
		}
		
		// configuraciones generales
//		ventanaActual.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		ventanaActual.setResizable(false);
		ventanaActual.setVisible(true);
		
		ventanaActual.addWindowListener(new WindowAdapter() {
	        @Override
	        public void windowClosing(WindowEvent e) {
	            Usuario.guardarInformacion();
	        }
	    });
		
	}
	
	public void volverMenu() {
		((EmpleadoMenuPrincipal) this.menu).resetDatos();
		((EmpleadoMenuPrincipal) this.menu).ocupacionHoy();
		((EmpleadoMenuPrincipal) this.menu).ocupacionAnual();
		mostraVentana(menu);
	}
	
	public void volverHabitaciones() {
		((EmpleadoMenuPrincipal) this.menu).volverHabitaciones();
	}
	
	public void volverReserva() {
		((EmpleadoMenuPrincipal) this.menu).volverReserva();
	}
	
	

	public void iniciarAutenticacion() {
		autenticacionFrame = new AutenticacionFrame(this);
		mostraVentana(autenticacionFrame);
		
	}
	
	public void inciarSecion() throws Exception {
		if (usuarioActual.getClass() == Empleado.class) {
			Empleado empleado = (Empleado) usuarioActual;
			menu = new EmpleadoMenuPrincipal(this);
			mostraVentana(menu);

		} else if (usuarioActual.getClass() == Admin.class) {
			Admin admin = (Admin) usuarioActual;
			menu = new AdminMenuPrincipal(this);
			mostraVentana(menu);
		}else {
			throw new Exception("Usuario invalido");
		}
	}
	
	public void autenticar(String login, String password) throws Exception {
		Usuario.autenticar(login, password);
		usuarioActual = Usuario.getUsuarioActual();
		inciarSecion();

	}
	
	public void cerrarSesion() {
		if (usuarioActual != null) {
			usuarioActual.cerrarSesion();
		}
		
		iniciarAutenticacion();
		
	}
	
	public void setPruebas(JFrame pruebas, JFrame menu) {
		this.pruebas = pruebas;
		this.menu = menu;
		mostraVentana(pruebas);
		
	}
	
	public String[] darUsuarios() {
		String[] lista = {"Oops"};
		if (usuarioActual.getClass() == Admin.class) {
			Admin admin = (Admin) usuarioActual;
			lista = admin.darUsuarios();
		}
		return lista;
	}
	
	public void agregarUsuario(String login, String password, String area, int tipo, boolean sobreEscibir) throws Exception {
		if (usuarioActual.getClass() == Admin.class) {
			Admin admin = (Admin) usuarioActual;
			admin.añadirUsuario(login, password, area, tipo, sobreEscibir); 
		}
	}
	

	public void agregarUsuario(String login, String password, String area, int tipo) throws Exception {
		agregarUsuario(login, password, area, tipo, false);
		
	}
	
	public void quitarUsuario(String nombre) {
		if (usuarioActual.getClass() == Admin.class) {
			Admin admin = (Admin) usuarioActual;
			admin.quitarUsuario(nombre);
		}
	}
	
	public void quitarHabitacion(Integer ID) {
		if (usuarioActual.getClass() == Admin.class) {
			Admin admin = (Admin) usuarioActual;
			admin.quitarHabitacion(ID);
		}
	}
	
	public boolean reservada(Integer ID) {
		boolean resultado = false;
		if (usuarioActual.getClass() == Admin.class) {
			Admin admin = (Admin) usuarioActual;
			resultado = admin.reservada(ID);
		}
		return resultado;
	}
	
	public String getTipo(String login) {
		String tipo = "Usuario";
		if (usuarioActual.getClass() == Admin.class) {
			Admin admin = (Admin) usuarioActual;
			tipo = admin.getTipo(login);
		}
		return tipo;
	}
	
	public String getArea(String login) {
		String area = "Default";
		if (usuarioActual.getClass() == Admin.class) {
			Admin admin = (Admin) usuarioActual;
			area = admin.getArea(login);
		}
		return area;
	}
	
	public ArrayList<String[]> getServicios(String ID) {
		ArrayList<String[]> servicios = new ArrayList<>();
		usuarioActual.getClass();
		Empleado empleado = (Empleado) usuarioActual;
		servicios = empleado.getServicios(ID);
		return servicios;
	}
	
	public ArrayList<String[]> getCamas(String ID) {
		ArrayList<String[]> camas = new ArrayList<>();
		usuarioActual.getClass();
		Empleado empleado = (Empleado) usuarioActual;
		camas = empleado.getCamas(ID);
		return camas;
	}
	
	
	public boolean checkUsuario(String nombre) {
		boolean self = false;
		Empleado empleado = (Empleado) usuarioActual;
		self = empleado.checkUsuario(nombre);
		return self;
	}
	
	public Integer[] ocupacionHoy() {
		return ((Empleado) usuarioActual).ocupacionHoy();
	}
	
	public Integer[] getHabitacionesIds() {
		HashMap<Integer,Habitacion> mapa = ((Empleado) usuarioActual).getHabitaciones();
		if (mapa == null) {
			return new Integer[0];	
		}
		else {
			Set<Integer> y = mapa.keySet();
			Object[] x = y.toArray();
			int[] r = Arrays.stream(x).mapToInt(o -> (int)o).toArray();
			Integer[] resultados = Arrays.stream(r).boxed().toArray( Integer[]::new );
			Arrays.sort(resultados);
			return resultados;
		}
	}
	public Integer[] getGruposChecked() {
		ArrayList<Grupo> grupos = getGruposSinClasificar();
		ArrayList<Integer> arrayChecked = new ArrayList<>();
		for (Grupo grupo : grupos) {
			if (grupo.getCheck()) {
				arrayChecked.add(grupo.getId());
			}
		}
		Object[] x = arrayChecked.toArray();
		int[] r = Arrays.stream(x).mapToInt(o -> (int)o).toArray();
		Integer[] resultados = Arrays.stream(r).boxed().toArray( Integer[]::new );
		Arrays.sort(resultados);
		return resultados;
	}
	
	public Integer[] getGruposUnChecked() {
		ArrayList<Grupo> grupos = getGruposSinClasificar();
		ArrayList<Integer> arrayUnChecked = new ArrayList<>();
		for (Grupo grupo : grupos) {
			boolean noHaCheckeado = grupo.getCheck() ? false : true;
			boolean diaValido = grupo.getReserva().getFechaI().equals(getHoy());			
			if (noHaCheckeado && diaValido) {
				arrayUnChecked.add(grupo.getId());
			}
		}
		Object[] x = arrayUnChecked.toArray();
		int[] r = Arrays.stream(x).mapToInt(o -> (int)o).toArray();
		Integer[] resultados = Arrays.stream(r).boxed().toArray( Integer[]::new );
		Arrays.sort(resultados);
		return resultados;
	}
	
	
	public ArrayList<Grupo> getGruposSinClasificar() {
		ArrayList<Grupo> resultado = new ArrayList<>();
		HashMap<Integer, Grupo> mapa = ((Empleado) usuarioActual).getGrupos();
		if (mapa == null) {
			return resultado;	
		}
		else {
			for (Grupo grupo : mapa.values()) {
				resultado.add(grupo);
			}
			return resultado;
		}
	}
	
	public Integer[][] getGruposIdsClasificadoss() {
		ArrayList<Integer[]> resultado = new ArrayList<>();
		ArrayList<Integer> checked = new ArrayList<>();
		ArrayList<Integer> unchecked = new ArrayList<>();
		HashMap<Integer, Grupo> mapa = ((Empleado) usuarioActual).getGrupos();
		if (mapa == null) {
			return new Integer[2][0];	
		}
		else {
			for (Grupo grupo : mapa.values()) {
				if (grupo.getCheck()) {
					checked.add((Integer) grupo.getId());
				}
				else {
					unchecked.add((Integer) grupo.getId());
				}
			}
			resultado.add((Integer[]) checked.toArray());
			resultado.add((Integer[]) unchecked.toArray());
			return (Integer[][]) resultado.toArray();
		}
	}
	
	public Habitacion getHabitacion(int id) {
		Admin admin = (Admin) usuarioActual;
		return admin.getHabitacion(id);
	}
	
	public int contarOcupadasDia(Date dia) {
		Empleado empleado = (Empleado) usuarioActual;
		return empleado.contarOcupadasDia(dia);
	}
	
	public int getTotalHabitaciones() {
		Empleado empleado = (Empleado) usuarioActual;
		return empleado.getTotalHabitaciones();
	}
	
	public Date pasarDia(Date dia) {
		Empleado empleado = (Empleado) usuarioActual;
		return empleado.pasarDia(dia);
	}
	
	public Date pasarMes(Date dia, int i) {
		Empleado empleado = (Empleado) usuarioActual;
		return empleado.pasarMes(dia, i);
	}

	public HashMap<Integer,Servicio> darServicio() {
		HashMap<Integer, Servicio> listaServicios = null;
		if (usuarioActual.getClass() == Empleado.class) {
			Empleado empleado = (Empleado) usuarioActual;
			listaServicios = empleado.getServiciosHotel();
		}
		else if (usuarioActual.getClass() == Admin.class) {
			Empleado empleado = (Admin) usuarioActual;
			listaServicios = empleado.getServiciosHotel();
		}
		return listaServicios;
		
	}
	
	public HashMap<Integer, Habitacion> darHabitaciones() {
		Empleado empleado = (Empleado) usuarioActual;
		HashMap<Integer, Habitacion> listaHabitaciones = empleado.getHabitaciones();
		return listaHabitaciones;
	}
	
	public void agregarServicioHotel(String nombre, int precio) {
		Admin admin = (Admin) usuarioActual;
		admin.crearServicioHotel(nombre, precio);
	}
	
	public void eliminarServicioHotel(int id) {
		Admin admin = (Admin) usuarioActual;
		admin.eliminarServicioHotel(id);
	}
	
	public void añadirServicioHabitacion(int id, String nombre, int precio) {
		Admin admin = (Admin) usuarioActual;
		admin.añadirServicioHabitacion(id, nombre, precio);
	}
	
	public void setCaracteriticas(String caracteristicas, int id) {
		Admin admin = (Admin) usuarioActual;
		admin.setCaracteristicasHabitacion(caracteristicas, id);
	}
	
	public Servicio crearServicio(String nombre, int precio) {
		Admin admin = (Admin) usuarioActual;
		return admin.crearServicio(nombre, precio);
	}
	
	public Cama crearCama(int capacidad, boolean exclusiva) {
		Admin admin = (Admin) usuarioActual;
		return admin.crearCama(capacidad, exclusiva);
	}
	
	public void añadirServicioHotelHabitacion(int idHabitacion, int idServicio, int cantidad, boolean pagarEnSitio) {
		Empleado empleado = (Admin) usuarioActual;
		empleado.añadirServicioHotelHabitacion(idHabitacion, idServicio, cantidad, pagarEnSitio);
	}
	
	public void crearHabitacion(TipoHabitacion tipoHabitacion, int id) {
		Admin admin = (Admin) usuarioActual;
		admin.crearHabitacion(tipoHabitacion, id);
	}
	
	public TipoHabitacion getTipoHabitacion(String opcion) {
		boolean right;
		TipoHabitacion tipo = null;
		do {
			right = false;
			
			switch (opcion) {
			case "estandar":
				tipo = TipoHabitacion.ESTANDAR;
				break;
			case "suite":
				tipo = TipoHabitacion.SUITE;
				break;
			case "suite double":
				tipo = TipoHabitacion.SUITEDOUBLE;
				break;
	
			default:
				right = true;
				break;
			}
		} while(right);

		
		return tipo;
	}
	

		
	public HashMap<Integer, ProductoMenu> getMenu() {
		Empleado empleado = (Empleado)usuarioActual;
		return empleado.getMenu();
	}
	
	public void crearProductoMenu(LocalTime horaI, LocalTime horaF, boolean llevable,String nombre, int precio){
		Admin admin = (Admin)usuarioActual;
		admin.crearProductoMenu(horaI, horaF, llevable, nombre, precio);
	}
	
	public void eliminarProductoMenu(ProductoMenu productoMenu) {
		Admin admin = (Admin)usuarioActual;
		admin.eliminarProductoMenu(productoMenu);
	}
	
	public void añadirProductoMenuHabitacion(int idHabitacion, int idServicio, int cantidad, boolean pagarEnSitio){
		Empleado empleado = (Empleado)usuarioActual;
		empleado.añadirProductoMenuHabitacion(idHabitacion, idServicio, cantidad, pagarEnSitio);
	}
	
	public Date getHora(LocalTime localTime) {
		Date date = new Date(localTime.getNano());
		return date;
	}
	

	// Tarifas
	public Collection<Tarifa> consultarTarifas(Date fechaI, Date fechaF) {
		return ((Empleado) usuarioActual).consultarTarifas(fechaI, fechaF);
	}
	
	public boolean tarifasCompletas() {
		Admin admin = (Admin) usuarioActual;
		return admin.tarifasCompletas();
	}

	public ArrayList<Tarifa> TarifasFaltantes() {
		Admin admin = (Admin) usuarioActual;
		return admin.TarifasFaltantes();
	}
	
	public ArrayList<Tarifa> crearTarifa(Date fechaI, Date fechaF, TipoHabitacion tipo, double valor, boolean[] diasValores) {
		Admin admin = (Admin) usuarioActual;
		return admin.crearTarifa(fechaI, fechaF, tipo, valor, diasValores);
	}
	public void borrarTarifa(Date fecha, TipoHabitacion tipo) {
		Admin admin = (Admin) usuarioActual;
		admin.borrarTarifa(fecha, tipo);
		
	}
	


	public void forzarCrearTarifas(ArrayList<Tarifa> tarifas,  TipoHabitacion tipo, double valor) {
		Admin admin = (Admin) usuarioActual;
		admin.forzarCrearTarifas(tarifas, tipo, valor);
		
	}
	

	public ArrayList<Tarifa> crearTarifasSobreFechas(ArrayList<Date> fechasFaltates, TipoHabitacion selectedItem,
			double parseDouble) {
		Admin admin = (Admin) usuarioActual;
		return admin.crearTarifasSobreFechas(fechasFaltates, selectedItem, parseDouble);
		
	}


	// FinTarifas
	
	// inicio Reservas
	public HashMap<Integer,Grupo> mostrarReservas(Date fechaI, Date fechaF) throws Exception {
		Huesped huesped = (Huesped) usuarioActual;
		
		return huesped.mostrarReservas(fechaI, fechaF);
	}
	public Grupo getGrupo(int id) throws Exception {
		Huesped huesped = (Huesped) usuarioActual;
		return huesped.getGrupo(id);
	}
	public ArrayList<Integer> getArrayHabitaciones() {
		Huesped huesped = (Huesped) usuarioActual;
		return huesped.getArrayHabitaciones();
	}
	
	public void cancelarReserva(int id) throws Exception {
		Huesped huesped = (Huesped) usuarioActual;
		huesped.cancelarReserva(id);
		
	}	
	
	public void crearReserva(Date fechaI, Date fechaF) throws Exception {
		Huesped huesped = (Huesped) usuarioActual;
		huesped.crearReserva(fechaI, fechaF);
	}
	
	public void cambiarFechaReserva(Date fechaI, Date fechaF) throws Exception {
		Huesped huesped = (Huesped) usuarioActual;
		huesped.cambiarFechaReserva(fechaI, fechaF);
	}
	public void completarReserva() throws Exception {
		Huesped huesped = (Huesped) usuarioActual;
		huesped.completarReserva();
	}
	
	public boolean hayReserva() {
		Huesped huesped = (Huesped) usuarioActual;
		
		return huesped.hayReserva();
	}
	
	public ArrayList<Habitacion> DiponiblesParaGrupoEnCurso(TipoHabitacion tipo) throws Exception {
		Huesped huesped = (Huesped) usuarioActual;
		return huesped.DiponiblesParaGrupoEnCurso(tipo);
	}
	public int getPrecioHabitacionReserva(Habitacion habitacion) throws Exception {
		Huesped huesped = (Huesped) usuarioActual;
		return (int)huesped.getPrecioHabitacionReserva(habitacion);
	}
	
	public void llenarHabitaciones(int idHabitacion) throws Exception {
		Huesped huesped = (Huesped) usuarioActual;
		huesped.añadirHabitacion(idHabitacion);
	}
	public ArrayList<Integer> getListaHabitacionesGrupo() {
		Huesped huesped = (Huesped) usuarioActual;
		
		return huesped.getListaHabitacionesGrupo();
	}	

	public void llenarHuespeds(String documento, String nombre, String email, String telefono, int edad) throws Exception {
		Huesped huesped = (Huesped) usuarioActual;
		huesped.añadirHuesped(documento, nombre, email, telefono, edad);;
	}
	

	
	public ArrayList<Huesped> getHuespedesGrupoEnCurso() {
		Huesped huesped = (Huesped) usuarioActual;

		return huesped.getHuespedesGrupoEnCurso();
	}
	
	public void forzarCancelarReserva() {
		Huesped huesped = (Huesped) usuarioActual;
		huesped.forzarCancelarReserva();
		
	}
	
	public Grupo getGrupoEnCurso() {
		Huesped huesped = (Huesped) usuarioActual;
		return huesped.getGrupoEnCurso();
	}
	
	public String getCaracteristicas(Integer ID) {
		Huesped huesped = (Huesped) usuarioActual;
		return huesped.getCaracteristicasHabitacion(ID);
	}
	// fin reservas

	
	public boolean validarCheckIn(Grupo grupo) {
		Date fechaI = grupo.getReserva().getFechaI();
		Date hoy = getHoy();
		return fechaI.equals(hoy);
	}
	
	public boolean validarCheckOut(Grupo grupo) {
		Date fechaF = grupo.getReserva().getFechaF();
		Date hoy = getHoy();
		boolean resultado;
		int comparacion = hoy.compareTo(fechaF);
		if (comparacion > 0) {
			resultado = false;
		}
		else {
			resultado = true;
		}
		return resultado;
	}

	
	public String formatoFecha(Date date) {
		Empleado empleado = (Empleado) usuarioActual;
		return empleado.formatoFecha(date);
	}
	
	
	public Grupo checkOut(int idGrupo) {
		Empleado empleado = (Empleado) usuarioActual;
		return empleado.checkOut(idGrupo);
	}

	public void cargarMenuPrincipal() {
		volverMenu();
		//((EmpleadoMenuPrincipal) menu).resetDatos();
	}
	
	public static void main(String[] args) {
		
		FlatLightLaf.install();
		
		UIManager.put( "Button.arc", 500 );
		UIManager.put( "Component.arrowType", "chevron" );
		UIManager.put( "TextComponent.arc", 5 );
		
		WindowManager windowManager = new WindowManager();
		
	
		
       int opcion = JOptionPane.showConfirmDialog(null, "¿Deseas hacer pruebas?", "Panel de Opciones", JOptionPane.YES_NO_OPTION);

        if (opcion == JOptionPane.YES_OPTION) {
        	try {
    			windowManager.autenticar("root", "Cookie");
    		
        	} catch (Exception e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
        	// JFrame para probar

    		JFrame pruebas = new AdminMenuPrincipal(windowManager);
    		// Menú de ese Frame
    		JFrame menu = new AdminMenuPrincipal(windowManager);
    		
    		windowManager.setPruebas(pruebas, menu);
    		
        } else {
        	windowManager.iniciarAutenticacion();
        }
		

}

	// Reportes
	
	public HashMap<String, int[]> datosReporteProductos() {
		Admin admin = (Admin) usuarioActual;
		return admin.datosReporteProductos();
	} 
	public ArrayList<int[]> datosReporteRestaurante() {
		Admin admin = (Admin) usuarioActual;
		return admin.datosReporteRestaurante();
	} 

	
	public void borrarDatos() {
		usuarioActual.borrarDatos();
		volverMenu();
		
	}
















}
