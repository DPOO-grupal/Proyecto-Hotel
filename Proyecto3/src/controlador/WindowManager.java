package controlador;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Set;
import java.util.ArrayList;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import modelo.Servicio;
import modelo.Tarifa;
import modelo.Huesped;
import modelo.ProductoMenu;
import modelo.Reserva;
import modelo.TipoHabitacion;
import modelo.Usuario;
import vistaAdmin.AutenticacionFrame;
import vistaAdmin.CrearHabitacionFrame;
import vistaAdmin.AdminHabitacionesFrame;
import vistaAdmin.AdminMenuPrincipal;
import vistaAdmin.AdminRestauranteFrame;
import vistaAdmin.AdminHabitacionesFrame;
import vistaAdmin.AdminMenuPrincipal;
import vistaAdmin.AdminRestauranteFrame;
import vistaAdmin.AdminServiciosFrame;
import vistaEmpleado.EmpleadoHabitacionesFrame;
import vistaAdmin.AdminTarifasFrame;
import vistaEmpleado.EmpleadoMenuPrincipal;
import vistaEmpleado.EmpleadoRestauranteFrame;
import vistaEmpleado.EmpleadoServiciosFrame;
import vistaEmpleado.EmpleadoTarifasFrame;

public class WindowManager {
	private JFrame ventandaActual;
	private JFrame menu;
	private JFrame autenticacionFrame;
	private Usuario usuarioActual;
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
		Empleado empleado = (Empleado) usuarioActual;
		return empleado.getHoy();
	}

	public void mostraVentana(JFrame ventana) {
		if(ventandaActual != null) {
			ventandaActual.dispose();
		}
		
		ventandaActual = ventana;
		
		// configuraciones generales
		ventandaActual.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventandaActual.setExtendedState(JFrame.MAXIMIZED_BOTH);
		ventandaActual.setResizable(false);
		ventandaActual.setVisible(true);
		
		ventandaActual.addWindowListener(new WindowAdapter() {
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
	
	public void inciarSecion() {
		if (usuarioActual.getClass() == Empleado.class) {
			Empleado empleado = (Empleado) usuarioActual;
			menu = new EmpleadoMenuPrincipal(this);
			mostraVentana(menu);

		} else if (usuarioActual.getClass() == Admin.class) {
			Admin admin = (Admin) usuarioActual;
			menu = new AdminMenuPrincipal(this);
			mostraVentana(menu);
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
	
	public void agregarUsuario(String login, String password, String area, int tipo) {
		if (usuarioActual.getClass() == Admin.class) {
			Admin admin = (Admin) usuarioActual;
			admin.añadirUsuario(login, password, area, tipo); 
		}
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
	
	public Integer[] getHabitacionesContenedor() {
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
	
	public Habitacion getHabitacion(int id) {
		Admin admin = (Admin) usuarioActual;
		return admin.getHabitacion(id);
	}
	
	public Date getDia() {
		Empleado empleado = (Empleado) usuarioActual;
		return empleado.getDia();
				
	}
	
	public int contarOcupadasDia(Date dia) {
		Empleado empleado = (Empleado) usuarioActual;
		return empleado.contarOcupadasDia(dia);
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
	
	public void agregarServicioHotel(String nombre, double precio) {
		Admin admin = (Admin) usuarioActual;
		admin.crearServicioHotel(nombre, precio);
	}
	
	public void eliminarServicioHotel(int id) {
		Admin admin = (Admin) usuarioActual;
		admin.eliminarServicioHotel(id);
	}
	
	public void añadirServicioHabitacion(int id, String nombre, double precio) {
		Admin admin = (Admin) usuarioActual;
		admin.añadirServicioHabitacion(id, nombre, precio);
	}
	
	public void setCaracteriticas(String caracteristicas, int id) {
		Admin admin = (Admin) usuarioActual;
		admin.setCaracteristicasHabitacion(caracteristicas, id);
	}
	
	public Servicio crearServicio(String nombre, double precio) {
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
	
	// Reservas
		
	public HashMap<Integer, ProductoMenu> getMenu() {
		Empleado empleado = (Empleado)usuarioActual;
		return empleado.getMenu();
	}
	
	public void crearProductoMenu(Date horaI, Date horaF, boolean llevable,String nombre, double precio){
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
	
	public Date getHora(String horaString) {
		boolean right;
		Date hora = null;
		do{
			DateFormat DFormat = new SimpleDateFormat("hh:mm a");

			try {
				hora = DFormat.parse(horaString);
				right = false;
			} catch (ParseException e) {
				right = true;
			}
			
		} while (right);
		
		return hora;
	}
	
	public ArrayList<Habitacion> DiponiblesParaGrupoEnCurso(TipoHabitacion tipo) throws Exception {
		Empleado empleado = (Empleado) usuarioActual;
		return empleado.DiponiblesParaGrupoEnCurso(tipo);
	}
	
	public void llenarHabitaciones(int idHabitacion) throws Exception {
		Empleado empleado = (Empleado) usuarioActual;
		empleado.añadirHabitacion(idHabitacion);
	}

	public void llenarHuespeds(String documento, String nombre, String email, String telefono, int edad) throws Exception {
		Empleado empleado = (Empleado) usuarioActual;
		empleado.añadirHuesped(documento, nombre, email, telefono, edad);;
	}
	
	public int getPrecioHabitacionReserva(Habitacion habitacion) {
		Empleado empleado = (Empleado) usuarioActual;
		return (int)empleado.getPrecioHabitacionReserva(habitacion);
	}
	
	public void crearReserva(Date fechaI, Date fechaF) throws Exception {
		Empleado empleado = (Empleado) usuarioActual;
		empleado.crearReserva(fechaI, fechaF);
	}
	
	public void cambiarFechaReserva(Date fechaI, Date fechaF) throws Exception {
		Empleado empleado = (Empleado) usuarioActual;
		empleado.cambiarFechaReserva(fechaI, fechaF);
	}
	public void completarReserva() throws Exception {
		Empleado empleado = (Empleado) usuarioActual;
		empleado.completarReserva();
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
	
	// FinTarifas
	
	
	public ArrayList<Integer> getListaHabitacionesGrupo() {
		Empleado empleado = (Empleado) usuarioActual;

		return empleado.getListaHabitacionesGrupo();
	}	
	public ArrayList<Huesped> getHuespedesGrupoEnCurso() {
		Empleado empleado = (Empleado) usuarioActual;

		return empleado.getHuespedesGrupoEnCurso();
	}
	
	public boolean hayReserva() {
		Empleado empleado = (Empleado) usuarioActual;

		return empleado.hayReserva();
	}
	public void forzarCancelarReserva() {
		Empleado empleado = (Empleado) usuarioActual;
		empleado.forzarCancelarReserva();
		
	}
	
	public void cancelarReserva(int id) throws Exception {
		Empleado empleado = (Empleado) usuarioActual;
		empleado.cancelarReserva(id);
		
	}	
	
	public HashMap<Integer,Grupo> mostrarReservas(Date fechaI, Date fechaF) throws Exception {
		Empleado empleado = (Empleado) usuarioActual;

		return empleado.mostrarReservas(fechaI, fechaF);
	}
	
	
	public Grupo getGrupo(int id) throws Exception {
		Empleado empleado = (Empleado) usuarioActual;
		return empleado.getGrupo(id);
	}
	
	public String formatoFecha(Date date) {
		Empleado empleado = (Empleado) usuarioActual;
		return empleado.formatoFecha(date);
	}
	
	public Grupo getGrupoEnCurso() {
		Empleado empleado = (Empleado) usuarioActual;
		return empleado.getGrupoEnCurso();
	}
	// fin reservas
	
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
    			
    			//Date pruebaI = new Date();
    			//Date pruebaF = new Date();
    			
    			//Calendar cal = Calendar.getInstance();
    	        //cal.setTime(pruebaI);
    	        //cal.add(Calendar.DAY_OF_MONTH, 1);
    	        //pruebaF = cal.getTime();
    	        
    			//String[] nombres = {"Juanes"};
    			//String[] documentos = {"1000252720"};
    			//String[] emails = {"Juanes@gmail.com"};
    			//String[] telefonos = {"3005628702"};
    			//int[] edades = {19};
    			//windowManager.cargarReserva(pruebaI, pruebaF, 1, nombres, documentos, emails, telefonos, edades);
    		
        	} catch (Exception e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
        	// JFrame para probar

    		JFrame pruebas = new AdminTarifasFrame(windowManager);
    		// Menú de ese Frame
    		JFrame menu = new AdminMenuPrincipal(windowManager);
    		
    		windowManager.setPruebas(pruebas, menu);
    		
        } else {
        	windowManager.iniciarAutenticacion();
        }
		

}


	public void borrarDatos() {
		// TODO Auto-generated method stub
		
	}










}
