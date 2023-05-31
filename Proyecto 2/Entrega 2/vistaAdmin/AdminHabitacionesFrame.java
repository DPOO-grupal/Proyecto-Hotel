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
        modeloTabla = new DefaultTableModel(columnas, 0);        
	    
	    //Diseño de la tabla
        tablaHabitaciones = new JTable(modeloTabla);
        tablaHabitaciones.setDefaultEditor(Object.class, null);
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
        constraints.ipady = 0;
        constraints.ipadx = 400;
        constraints.insets = new Insets(90, 0, 100, 0);

        panelDerecho.add(scrollPanel, constraints);
        
		JPanel check = new JPanel();
		check.setBackground(Color.decode("#7E8C69"));
		check.setPreferredSize(new Dimension(0,199));
		check.setLayout(new FlowLayout(FlowLayout.CENTER, 300, 80));
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
        constraints.ipady = 1;
        constraints.ipadx = 1084;
        constraints.insets = new Insets(10, 0, 0, 0);
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
		modeloTabla.getDataVector().removeAllElements();
		modeloTabla.fireTableDataChanged(); 
		Collection<Habitacion> listaHabitaciones = windowManager.darHabitaciones().values();
		for (Habitacion habitacion : listaHabitaciones) {
	        String id = String.valueOf(habitacion.getId());
	        String tipo = habitacion.getTipoHabitacion().toString();
	        String capacidad = String.valueOf(habitacion.getCapacidad());
	        String apto = "No";
	        if (habitacion.getApto()) {
				apto="Si";
	        }
	        ArrayList<Servicio> servicios = habitacion.getServicios();
	        String caracteristicas = habitacion.getCaracteristicas();
	        modeloTabla.addRow(new Object[]{id, tipo, capacidad, apto, servicios, caracteristicas, "ICON", "ICON"});
	    }
	}
	
	
	private void crearHabitacion() {
		//JOptionPane.showMessageDialog(null, "Crear");
		windowManager.mostraVentana(CrearHabitacionFrame);
	}
	
	private void borrarHabitacion() {
		JOptionPane.showMessageDialog(null, "Borrar");
	}

	@Override
	public void actionPerformedFrame(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "Crear habitación":
			crearHabitacion();
			break;
			
		default:
			case "Borrar habitación":
			borrarHabitacion();
			break;
		}
	}

}
