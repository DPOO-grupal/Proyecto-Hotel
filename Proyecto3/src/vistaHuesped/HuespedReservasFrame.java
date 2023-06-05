package vistaHuesped;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import controlador.WindowManager;
import vistaEmpleado.EmpleadoCrearReservasFrame;
import vistaEmpleado.EmpleadoReservasFrame;

public class HuespedReservasFrame extends EmpleadoReservasFrame {
	
	private HuespedManager huespedManager;
	
	public HuespedReservasFrame(HuespedManager huespedManager) {
		super(huespedManager);
		this.huespedManager = huespedManager;
		crearReservasFrame = new HuespedaCrearReservasFrame(huespedManager);
		idHuespedReservas();
		
	}
	
	private void idHuespedReservas() {
		panelBuscar.remove(idGrupo);
		ArrayList<Integer> idsGrupos = huespedManager.idHuespedReservas();
		idGrupo = new JComboBox<Integer>();
		
		for (Integer integer : idsGrupos) {
			((JComboBox<Integer>)idGrupo).addItem(integer);
		}
		
		idGrupo.setPreferredSize(new Dimension(10, 40));
		idGrupo.addKeyListener(this);
		
		GridBagConstraints constraints = new GridBagConstraints();

		constraints.weighty = 1;
		constraints.weightx = 1;
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.insets = new Insets(1, 0, 1, 0);

		panelBuscar.add(idGrupo,constraints);
		
		
	}
	
	@Override
	public void volverMenu() {
		huespedManager.cerrarSesion();
	}
	
	@Override
	protected void buscarReserva() {
		try {
			int grupo = Integer.parseInt(((JComboBox<Integer>)idGrupo).getSelectedItem()+"");
			numeroReserva = grupo;
			llenarDatosReserva(grupo);
		}catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Ingrese un numero valido", "Error datos", JOptionPane.ERROR_MESSAGE);;
		}
		
	}
	
	@Override
	public void resetDatos() {
		super.resetDatos();
		idHuespedReservas();
	}
	


}
