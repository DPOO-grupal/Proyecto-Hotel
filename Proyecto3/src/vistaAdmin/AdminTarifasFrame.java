package vistaAdmin;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;

import controlador.WindowManager;
import vistaEmpleado.EmpleadoTarifasFrame;

public class AdminTarifasFrame extends EmpleadoTarifasFrame {

	public AdminTarifasFrame(WindowManager windowManager) {
		super(windowManager);
		añadirAdminOpciones();
	}
	
	public void añadirAdminOpciones() {
	    GridBagConstraints constraints = new GridBagConstraints();
	    Font fontLabel = new Font("Arial", Font.BOLD, 16);

	    JButton crearButton = new JButton("Crear Tarifa");
	    crearButton.setBackground(Color.decode("#204473"));
	    crearButton.setFont(fontLabel);
	    crearButton.setForeground(Color.white);
	    crearButton.setPreferredSize(new Dimension(200, 50));
	    crearButton.addActionListener(this);
	    
	    constraints.insets = new Insets(20, 0, 20, 0);
	    constraints.gridx = 0;
	    constraints.gridy = 2;
	    constraints.weightx = 1;
	    constraints.weighty = 1;

	    constraints.ipadx =1;
	    constraints.ipady =1;
	    
	    panelDerecho.add(crearButton, constraints);
		
		JButton borrarButton = new JButton("Borrar Tarifa");
		borrarButton.setBackground(Color.decode("#204473"));
		borrarButton.setFont(fontLabel);
		borrarButton.setForeground(Color.white);
	    borrarButton.setPreferredSize(new Dimension(200, 50));
	    borrarButton.addActionListener(this);

	    
	    constraints.gridx = 1;
		panelDerecho.add(borrarButton, constraints);


	    
	}
	

	private void borrarTarifa() {
		// TODO Auto-generated method stub
		
	}

	private void crearTarifa() {
		JFrame selectHabitacion = new JFrame();
	    selectHabitacion.setSize(new Dimension(700,500));
	    selectHabitacion.setLocationRelativeTo(null);
	    
	    
	    selectHabitacion.setVisible(true);
	    
		
	}
	
	
	@Override
	public void actionPerformedFrame(ActionEvent e) {
		super.actionPerformedFrame(e);
		switch (e.getActionCommand()) {
		case "Crear Tarifa":
			crearTarifa();
			break;
			
		case "Borrar Tarifa":
			borrarTarifa();
			break;

		default:
			break;
		
		
		}
	}


}
