package vistaAdmin;

import java.awt.Color;
import java.awt.Dimension;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import controlador.WindowManager;
import vistaEmpleado.EmpleadoMenuPrincipal;
import vistaEmpleado.EmpleadoTarifasFrame;

public class AdminMenuPrincipal extends EmpleadoMenuPrincipal implements ActionListener {
	
	private JFrame usuariosFrame;
	private JFrame reportesFrame;


	public AdminMenuPrincipal(WindowManager windowManager){
        super(windowManager);
        usuariosFrame = new AdminUsuariosFrame(windowManager);
        tarifasFrame = new AdminTarifasFrame(windowManager);
		restauranteFrame = new AdminRestauranteFrame(windowManager);
		habitacionesFrame = new AdminHabitacionesFrame(windowManager);
		serviciosFrame = new AdminServiciosFrame(windowManager);
		reportesFrame = new AdminReportesFrame(windowManager);
	}

	@Override
	protected void setBotones() {
		// Configuracion General
		GridBagLayout gridbag = new GridBagLayout();
	    GridBagConstraints constraints = new GridBagConstraints();
	    panelIzquierdo.setLayout(gridbag);
	    panelIzquierdo.setBackground(Color.decode("#204473"));
	    
	    String[] nombres = {"Administrar usuarios" , 
	    					"Tarifas", 
	    					"Servicios", 
	    					"Habitaciones", 
	    					"Restaurante", 
	    					"Reservas",
	    					"Reportes",
	    					"Cerrar sesion",
	    					"Borrar Datos"};
	    
	    JButton[] opciones = new JButton[nombres.length];
	    Font fontButton = new Font("Arial", Font.BOLD, 16);
	    panelIzquierdo.setBorder(BorderFactory.createEmptyBorder(100, 20, 100, 20));

	    for (int i = 0; i<nombres.length; i++) {
	    	opciones[i] = new JButton(nombres[i]);
	    	opciones[i].setBackground(Color.decode("#ACCAF2"));
	    	opciones[i].addActionListener(this);
	    	opciones[i].setPreferredSize(new Dimension(200, 50));
	    	opciones[i].setFont(fontButton);
	    	constraints.gridy = i;
	    	constraints.weighty = 1;
	    	panelIzquierdo.add(opciones[i],constraints);
	    }
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		super.actionPerformed(e);
		
		switch (e.getActionCommand()) {
		case "Administrar usuarios":
			windowManager.mostraVentana(usuariosFrame);
			break;
		case "Borrar Datos":
			windowManager.borrarDatos();
			break;
		case "Reportes":
			windowManager.mostraVentana(reportesFrame);
			break;
		default:
			break;
		}

	}


}
