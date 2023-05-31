package vistaEmpleado;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Collection;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import controlador.WindowManager;
import modelo.Habitacion;
import modelo.Servicio;
import vistaAdmin.FrameBaseInfo;

public class EmpleadoHabitacionesFrame extends FrameBaseInfo implements MouseListener{
	
	protected DefaultTableModel modeloTabla;
	protected DefaultTableModel modeloTablaCamas;
	protected DefaultTableModel modeloTablaServicios;
	protected JTable tablaHabitaciones;
	protected JTable tablaHabitacionesCamas;
	protected JTable tablaHabitacionesServicios;
	protected JTextField cajaPiso;
	protected JTextField cajaTipo;
	
	public EmpleadoHabitacionesFrame(WindowManager windowManager) {
		super(windowManager);
		cargarDatos();
		
	}
	
	@Override
	protected void setPanelInfo() {
		GridBagLayout gridbag = new GridBagLayout();
		GridBagConstraints constraints = new GridBagConstraints();
        panelDerecho.setLayout(gridbag);
		panelDerecho.setBackground(Color.decode("#b2bba4"));
		panelDerecho.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		
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

        panelDerecho.add(scrollPanel, constraints);

	}
	
	protected void setPanelCrear() {
		//Edita el aspecto del panel	
		panelCrear.setLayout(new GridBagLayout());
		panelCrear.setBackground(Color.decode("#204473"));
		panelCrear.setBorder(BorderFactory.createEmptyBorder(20, 100, 20, 100));
		
		GridBagConstraints constraints = new GridBagConstraints();
		
		JLabel labelPiso =  new JLabel("Piso");
		labelPiso.setForeground(Color.white);
		labelPiso.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		constraints.insets = new Insets(0, 100, 0, 0);
	    constraints.anchor = GridBagConstraints.WEST;
	    constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridheight = 1;
        constraints.gridwidth = 1;
		panelCrear.add(labelPiso, constraints);
		
		cajaPiso = new JTextField();
		cajaPiso.setEditable(false);
		cajaPiso.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		constraints.ipadx = 200;
	    constraints.anchor = GridBagConstraints.WEST;
	    constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridheight = 1;
        constraints.gridwidth = 3;
		panelCrear.add(cajaPiso, constraints);
		
		JLabel labelTipo =  new JLabel("Tipo de habitacion");
		labelTipo.setForeground(Color.white);
		labelTipo.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		constraints.insets = new Insets(10, 100, 0, 0);
	    constraints.anchor = GridBagConstraints.WEST;
	    constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridheight = 1;
        constraints.gridwidth = 1;
		panelCrear.add(labelTipo, constraints);
		
		cajaTipo = new JTextField();
		cajaTipo.setEditable(false);
		cajaTipo.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		constraints.ipadx = 200;
	    constraints.anchor = GridBagConstraints.WEST;
	    constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridheight = 1;
        constraints.gridwidth = 3;
		panelCrear.add(cajaTipo, constraints);
		
		JLabel labelCamas =  new JLabel("Camas");
		labelCamas.setForeground(Color.white);
		labelCamas.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		constraints.insets = new Insets(10, 100, 0, 0);
	    constraints.anchor = GridBagConstraints.WEST;
	    constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.gridheight = 1;
        constraints.gridwidth = 1;
		panelCrear.add(labelCamas, constraints);
		
		//Creacion de la tabla servicios
		String[] columnasCamas = {"Capacidad", "Exlusiva para niños"}; //Nombre de las columnas
		modeloTablaCamas = new DefaultTableModel(columnasCamas, 0);
		        
			    
		//Diseño de la tabla
        tablaHabitacionesCamas = new JTable(modeloTablaCamas);
        tablaHabitacionesCamas.setDefaultEditor(Object.class, null);
        tablaHabitacionesCamas.addMouseListener(this);
        tablaHabitacionesCamas.getTableHeader().setBackground(Color.decode("#204473"));
        tablaHabitacionesCamas.getTableHeader().setForeground(Color.white);
        tablaHabitacionesCamas.getTableHeader().setFont(new Font("Times New Roman", 1, 17));
        tablaHabitacionesCamas.setFont(new Font("Times New Roman", 1, 15));
        tablaHabitacionesCamas.setRowHeight(40);
        tablaHabitacionesCamas.setEnabled(false);

        DefaultTableCellRenderer modelocentrar = new DefaultTableCellRenderer();
        modelocentrar.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < columnasCamas.length; i++) {
        	tablaHabitacionesCamas.getColumnModel().getColumn(i).setCellRenderer(modelocentrar);	
		}

        JScrollPane scrollPanel = new JScrollPane(tablaHabitacionesCamas);

        //Tamaño y ubicacion de la tabla en el panel
        constraints.insets = new Insets(0, 100, 0, 0);
	    constraints.anchor = GridBagConstraints.WEST;
	    constraints.ipadx = 300;
	    constraints.ipady = 100;
	    constraints.gridx = 0;
        constraints.gridy = 5;
        constraints.gridheight = 1;
        constraints.gridwidth = 1;

        panelCrear.add(scrollPanel, constraints);
        
        JLabel labelServicios =  new JLabel("Servicios");
		labelServicios.setForeground(Color.white);
		labelServicios.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		constraints.insets = new Insets(-40, 100, -20, 0);
	    constraints.anchor = GridBagConstraints.WEST;
	    constraints.gridx = 0;
        constraints.gridy = 6;
        constraints.gridheight = 1;
        constraints.gridwidth = 1;
		panelCrear.add(labelServicios, constraints);
		
		//Creacion de la tabla servicios
		String[] columnasServicios = {"Nombre", "Precio"}; //Nombre de las columnas
		modeloTablaServicios = new DefaultTableModel(columnasServicios, 0);
		        
			    
		//Diseño de la tabla
        tablaHabitacionesServicios = new JTable(modeloTablaServicios);
        tablaHabitacionesServicios.setDefaultEditor(Object.class, null);
        tablaHabitacionesServicios.addMouseListener(this);
        tablaHabitacionesServicios.getTableHeader().setBackground(Color.decode("#204473"));
        tablaHabitacionesServicios.getTableHeader().setForeground(Color.white);
        tablaHabitacionesServicios.getTableHeader().setFont(new Font("Times New Roman", 1, 17));
        tablaHabitacionesServicios.setFont(new Font("Times New Roman", 1, 15));
        tablaHabitacionesServicios.setRowHeight(40);
        tablaHabitacionesServicios.setEnabled(false);

        DefaultTableCellRenderer modelocentrarServicios = new DefaultTableCellRenderer();
        modelocentrarServicios.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < columnasServicios.length; i++) {
        	tablaHabitacionesServicios.getColumnModel().getColumn(i).setCellRenderer(modelocentrarServicios);	
		}	

        JScrollPane scrollPanelServicios = new JScrollPane(tablaHabitacionesServicios);

        //Tamaño y ubicacion de la tabla en el panel
        constraints.insets = new Insets(-30, 100, 0, -20);
	    constraints.anchor = GridBagConstraints.WEST;
	    constraints.ipadx = 300;
	    constraints.ipady = 100;
	    constraints.gridx = 0;
        constraints.gridy = 7;
        constraints.gridheight = 1;
        constraints.gridwidth = 1;

        panelCrear.add(scrollPanelServicios, constraints);
	}
	
	protected void cargarDatos() {
		modeloTabla.getDataVector().removeAllElements();
		//String[] fila = {"301", "Suite", "5", "Balcon"};
		//modeloTabla.addRow(fila);
		modeloTabla.fireTableDataChanged(); 
		Collection<Habitacion> listaHabitaciones = windowManager.darHabitaciones().values();
		for (Habitacion habitacion : listaHabitaciones) {
	        String id = String.valueOf(habitacion.getId());
	        String tipo = habitacion.getTipoHabitacion().toString();
	        String capacidad = String.valueOf(habitacion.getCapacidad());
	        String caracteristicas = habitacion.getCaracteristicas();
	        modeloTabla.addRow(new Object[]{id, tipo, capacidad, caracteristicas, "ICON", "ICON"});
	    }
	}
	
	public void llenarTablaServicios(String ID) {
		ArrayList<String[]> servicios = windowManager.getServicios(ID);
		for (String[] servicio : servicios) {
			modeloTablaServicios.addRow(servicio);
		}
	}
	
	public void llenarTablaCamas(String ID) {
		ArrayList<String[]> camas = windowManager.getCamas(ID);
		for (String[] cama : camas) {
			modeloTablaServicios.addRow(cama);
		}
	}
	
	@Override
	protected void actionPerformedFrame(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 1) {
			JTable target = (JTable)e.getSource();
			int row = target.getSelectedRow();
 		    String ID = tablaHabitaciones.getValueAt(row, 0).toString();
 		    cajaPiso.setText(ID.substring(0, 1));
 		    cajaTipo.setText(tablaHabitaciones.getValueAt(row, 1).toString());
 		    JOptionPane.showMessageDialog(null, "Seleccionó a: "+tablaHabitaciones.getValueAt(row, 0));
 		    llenarTablaCamas(ID);
 		    llenarTablaServicios(ID);
 		  }
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
