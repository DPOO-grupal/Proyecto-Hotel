package controlador;

import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.plaf.basic.BasicTreeUI.TreeCancelEditingAction;

import org.jdesktop.swingx.JXDatePicker;

import com.formdev.flatlaf.FlatLightLaf;

import modelo.Admin;
import modelo.Empleado;
import modelo.Habitacion;
import modelo.Servicio;
import modelo.TipoHabitacion;
import modelo.Usuario;
import vistaAdmin.AutenticacionFrame;
import vistaAdmin.AdminHabitacionesFrame;
import vistaAdmin.AdminMenuPrincipal;
import vistaAdmin.AdminServiciosFrame;
import vistaAdmin.AdminUsuariosFrame;
import vistaEmpleado.EmpleadoCrearReservasFrame;
import vistaEmpleado.EmpleadoHabitacionesFrame;
import vistaEmpleado.EmpleadoMenuPrincipal;
import vistaEmpleado.EmpleadoReservasFrame;
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

	public void mostraVentana(JFrame ventana) {
		if(ventandaActual != null) {
			ventandaActual.dispose();
		}
		
		ventandaActual = ventana;
		
		// configuraciones generales
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.setExtendedState(JFrame.MAXIMIZED_BOTH);
		ventana.setResizable(false);
		ventana.setVisible(true);
		
		ventandaActual.addWindowListener(new WindowAdapter() {
	        @Override
	        public void windowClosing(WindowEvent e) {
	            Usuario.guardarInformacion();
	        }
	    });
		
	}
	
	public void volverMenu() {
		mostraVentana(menu);
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
	
	public void añadirServicioHabitacion(int id, String nombre, double precio) {
		Admin admin = (Admin) usuarioActual;
		admin.añadirServicioHabitacion(id, nombre, precio);
	}
	
	public void setCaracteriticas(String caracteristicas, int id) {
		Admin admin = (Admin) usuarioActual;
		admin.setCaracteristicasHabitacion(caracteristicas, id);
	}
	
	public void añadirServicioHotelHabitacion(int idHabitacion, int idServicio, int cantidad, boolean pagarEnSitio) {
		Empleado empleado = (Admin) usuarioActual;
		empleado.añadirServicioHotelHabitacion(idHabitacion, idServicio, cantidad, pagarEnSitio);
	}
	
	public void crearHabitacion(TipoHabitacion tipoHabitacion, int id, int capacidad, boolean apto) {
		Admin admin = (Admin) usuarioActual;
		admin.crearHabitacion(tipoHabitacion, id, capacidad, apto);
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
    		JFrame pruebas = new AdminHabitacionesFrame(windowManager);
    		// Menú de ese Frame
    		JFrame menu = new AdminMenuPrincipal(windowManager);
    		
    		windowManager.setPruebas(pruebas, menu);
    		
        } else {
        	windowManager.iniciarAutenticacion();
        }
		
		
}

	public void cargarReserva(Date fechaI, Date fechaF, int tamanioGrupo, String[] nombres, String[] documentos, String[] emails, String[] telefonos, int[] edades) {
		if (usuarioActual.getClass() == Empleado.class) {
			Empleado empleado = (Empleado) usuarioActual;
			empleado.crearReserva(fechaI, fechaF, tamanioGrupo, nombres, documentos, emails, telefonos, edades);
			empleado.llenarOcupados(104);
		}
	}

}
