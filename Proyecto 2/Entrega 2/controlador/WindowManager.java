package controlador;

import javax.swing.JFrame;

import vista.AutenticacionFrame;
import vista.MenuPrincipalAdmin;

public class WindowManager {
	private JFrame ventandaActual;
	private JFrame menu;
	private JFrame autenticacionFrame;
	private Hotel hotel;

	public WindowManager() {
		menu = new MenuPrincipalAdmin(this);
		
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
		
	}
	
	public void volverMenu() {
		mostraVentana(menu);
	}
	

	public void iniciarAutenticacion() {
		autenticacionFrame = new AutenticacionFrame(this);
		mostraVentana(autenticacionFrame);
		
	}

	public static void main(String[] args) {

		WindowManager windowManager = new WindowManager();
		windowManager.iniciarAutenticacion();
}

}
