package controlador;

import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

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
import modelo.Huesped;
import modelo.TipoHabitacion;
import modelo.Usuario;
import vistaAdmin.AutenticacionFrame;
import vistaAdmin.AdminMenuPrincipal;
import vistaAdmin.AdminUsuariosFrame;
import vistaEmpleado.EmpleadoCrearReservasFrame;
import vistaEmpleado.EmpleadoMenuPrincipal;
import vistaEmpleado.EmpleadoReservasFrame;
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
	

	public void volverReserva() {
		((EmpleadoMenuPrincipal) this.menu).volverReserva();;
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
	
	// Reservas
		
	
	public ArrayList<Habitacion> DiponiblesParaGrupoEnCurso(TipoHabitacion tipo) throws Exception {
		Empleado empleado = (Empleado) usuarioActual;
		return empleado.DiponiblesParaGrupoEnCurso(tipo);
	}
	
	public void llenarHabitaciones(int idHabitacion) {
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
	
	
	public ArrayList<Integer> getListaHabitacionesGrupo() {
		Empleado empleado = (Empleado) usuarioActual;

		return empleado.getListaHabitacionesGrupo();
	}	
	public ArrayList<Huesped> getHuespedesGrupoEnCurso() {
		Empleado empleado = (Empleado) usuarioActual;

		return empleado.getHuespedesGrupoEnCurso();
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
    		} catch (Exception e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
        	// JFrame para probar
    		JFrame pruebas = new EmpleadoCrearReservasFrame(windowManager);
    		// Menú de ese Frame
    		JFrame menu = new EmpleadoMenuPrincipal(windowManager);
    		
    		windowManager.setPruebas(pruebas, menu);
    		
        } else {
        	windowManager.iniciarAutenticacion();
        }
		

}








}
