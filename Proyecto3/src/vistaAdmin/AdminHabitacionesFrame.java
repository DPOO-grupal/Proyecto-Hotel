package vistaAdmin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Collection;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import controlador.WindowManager;
import modelo.Habitacion;
import modelo.Servicio;
import modelo.TipoHabitacion;
import vistaEmpleado.EmpleadoHabitacionesFrame;

public class AdminHabitacionesFrame extends EmpleadoHabitacionesFrame{
	
	private JButton crearHabitacion;
	private JButton borrarHabitacion;
	private JFrame CrearHabitacionFrame;
	private DefaultTableModel modeloTablaInfo;
	
	
	public AdminHabitacionesFrame(WindowManager windowManager) {
		super(windowManager);
		cargarDatos();
		CrearHabitacionFrame = new CrearHabitacionFrame(windowManager);
	}
	
	
	@Override
	public void setPanelInfo() {
		GridBagLayout gridbag = new GridBagLayout();
		GridBagConstraints constraints = new GridBagConstraints();
        panelDerecho.setLayout(gridbag);
		panelDerecho.setBackground(Color.decode("#b2bba4"));
		panelDerecho.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		
		//Creacion de la tabla servicios
		String[] columnas = {"ID", "Tipo", "Capacidad", "Características"}; //Nombre de las columnas
        modeloTablaInfo = new DefaultTableModel(columnas, 0);        
	    
	    //Diseño de la tabla
        tablaHabitaciones = new JTable(modeloTablaInfo);
        tablaHabitaciones.setDefaultEditor(Object.class, null);
        tablaHabitaciones.getTableHeader().setReorderingAllowed(false);
        tablaHabitaciones.addMouseListener(this);
        tablaHabitaciones.getTableHeader().setBackground(Color.decode("#204473"));
        tablaHabitaciones.getTableHeader().setForeground(Color.white);
        tablaHabitaciones.getTableHeader().setFont(new Font("Times New Roman", 1, 30));
        tablaHabitaciones.setFont(new Font("Times New Roman", 1, 20));
        tablaHabitaciones.setRowHeight(40);
        tablaHabitaciones.setEnabled(true);

        DefaultTableCellRenderer modelocentrar = new DefaultTableCellRenderer();
        modelocentrar.setHorizontalAlignment(SwingConstants.CENTER);
        modelocentrar.setForeground(Color.BLACK);
        for (int i = 0; i < columnas.length; i++) {
        	tablaHabitaciones.getColumnModel().getColumn(i).setCellRenderer(modelocentrar);	
		}
        

        JScrollPane scrollPanel = new JScrollPane(tablaHabitaciones);

        //Tamaño y ubicacion de la tabla en el panel
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx =1;
        constraints.weighty =1;
        constraints.insets = new Insets(30, 30, 30, 30);
        constraints.fill = GridBagConstraints.BOTH;

        panelDerecho.add(scrollPanel, constraints);
        
		JPanel check = new JPanel();
		check.setBackground(Color.decode("#7E8C69"));
		check.setPreferredSize(new Dimension(0,199));
		check.setLayout(new FlowLayout(FlowLayout.CENTER, 200, 80));
	    Font fontButton = new Font("Arial", Font.BOLD, 16);

		crearHabitacion = new JButton("Crear habitación");
		crearHabitacion.setPreferredSize(new Dimension(200,50));
		crearHabitacion.setFont(fontButton);
		crearHabitacion.setBackground(Color.decode("#D0ECF2"));
		crearHabitacion.addActionListener(this);
		
		borrarHabitacion = new JButton("Borrar habitación");
		borrarHabitacion.setPreferredSize(new Dimension(200,50));
		borrarHabitacion.setFont(fontButton);
		borrarHabitacion.setBackground(Color.decode("#D0ECF2"));
		borrarHabitacion.addActionListener(this);

		
		check.add(crearHabitacion);
		check.add(borrarHabitacion);
		
		constraints.gridx = 0;
		constraints.gridy = 1;
        constraints.insets = new Insets(0, 0, 0, 0);

        constraints.fill = GridBagConstraints.HORIZONTAL;		
        constraints.anchor = GridBagConstraints.SOUTH;		
		panelDerecho.add(check, constraints);
	}
	
	
	
	private boolean getApto(String opcion) {
		boolean apto = false;
		if (opcion.equals("si")) {
			apto = true;
		}
		return apto;
	}
	
	protected void cargarDatos() {
		modeloTablaInfo.getDataVector().removeAllElements();
		modeloTablaInfo.fireTableDataChanged(); 
		Collection<Habitacion> listaHabitaciones = windowManager.darHabitaciones().values();
		for (Habitacion habitacion : listaHabitaciones) {
	        String id = String.valueOf(habitacion.getId());
	        String tipo = habitacion.getTipoHabitacion().toString();
	        String capacidad = String.valueOf(habitacion.getCapacidad());
	        ArrayList<Servicio> servicios = habitacion.getServicios();
	        String caracteristicas = habitacion.getCaracteristicas();
	        modeloTablaInfo.addRow(new Object[]{id, tipo, capacidad, caracteristicas, "ICON", "ICON"});
	    }
	}
	
	
	private void crearHabitacion() {
		//JOptionPane.showMessageDialog(null, "Crear");
		resetDatos();
		windowManager.mostraVentana(CrearHabitacionFrame);
	}
	
	
	private void borrarHabitacion() {
		int row = tablaHabitaciones.getSelectedRow();
		//JOptionPane.showMessageDialog(null, ID);
		if (row == -1) {
			JOptionPane.showMessageDialog(null, "Seleccione una habitación primero");
		}
		else {
			Integer ID = Integer.parseInt(tablaHabitaciones.getValueAt(row, 0).toString());
			boolean habReservada = verificarReservada(ID);
			if (habReservada) {
				JOptionPane.showMessageDialog(null, "No puedes eliminar una habitación que está reservada.");
			}
			else {
				int opcion = JOptionPane.YES_OPTION;
				opcion = JOptionPane.showConfirmDialog(null, "¿Desea eliminar la habitación: " + ID + "?", "Panel de Opciones", JOptionPane.YES_NO_OPTION);
				
		        if (opcion == JOptionPane.YES_OPTION) {
		    		windowManager.quitarHabitacion(ID);
		    		resetDatos();
		    		cargarDatos();
		    		JOptionPane.showMessageDialog(null, "La habitación " + ID + " se eliminó correctamente.");
		        }
		    	else {
		    		JOptionPane.showMessageDialog(null, "Acción cancelada.");
		    	}
			}
		}
	}
	
	private boolean verificarReservada(Integer ID) {
		return windowManager.reservada(ID);
	}

	@Override
	public void actionPerformedFrame(ActionEvent e) {
		super.actionPerformedFrame(e);
		switch (e.getActionCommand()) {
		case "Crear habitación":
			crearHabitacion();
			break;
			
		case "Borrar habitación":
			borrarHabitacion();
			break;
			
		default:
		}
	}

}
