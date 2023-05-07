package vista;

import javax.swing.JFrame;

public class WindowManager {
	private JFrame ventandaActual;
	private JFrame menu;
	
	public WindowManager() {
		menu = new MenuPrincipalAdmin(this);
		mostraVentana(menu);
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

}
