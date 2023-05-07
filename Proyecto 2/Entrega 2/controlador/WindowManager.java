package controlador;

import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.plaf.basic.BasicTreeUI.TreeCancelEditingAction;

import org.jdesktop.swingx.JXDatePicker;

import modelo.Admin;
import modelo.Empleado;
import modelo.Usuario;
import vista.AutenticacionFrame;
import vista.MenuPrincipalAdmin;

public class WindowManager {
	private JFrame ventandaActual;
	private JFrame menu;
	private JFrame autenticacionFrame;
	private Hotel hotel;
	private Usuario usuarioActual;
		
	
	public WindowManager() {
		hotel = new Hotel();
		setDay();
		hotel.cargarInformacion();

	}
	
	private void setDay() {
		
	    JFrame setDayFrame = new JFrame();

	    // Crear un JXDatePicker con la fecha actual
	    JXDatePicker datePicker = new JXDatePicker(new Date());
	    JLabel mensaje = new JLabel("Seleccione una fecha"); 
	    JPanel panel = new JPanel();
	    panel.add(mensaje);
	    panel.add(datePicker);

	    JOptionPane.showOptionDialog(setDayFrame, panel, "Establecer fecha del Hotel", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);

	    Date fecha = datePicker.getDate();
		hotel.setHoy(fecha);
	

	
		
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
	            hotel.guardarInformacion();
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
			menu = new MenuPrincipalAdmin(this);
			mostraVentana(menu);

		} else if (usuarioActual.getClass() == Admin.class) {
			Admin admin = (Admin) usuarioActual;
			menu = new MenuPrincipalAdmin(this);
			mostraVentana(menu);
		}
	}
	
	public void autenticar(String login, String password) throws Exception {
		hotel.autenticar(login, password);
		usuarioActual = hotel.getUsuarioActual();
		inciarSecion();

	}
	
	public static void main(String[] args) {
		
		WindowManager windowManager = new WindowManager();
		windowManager.iniciarAutenticacion();
}

}
