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
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
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
import javax.swing.text.NumberFormatter;

import controlador.WindowManager;
import modelo.Cama;
import modelo.Habitacion;
import modelo.Servicio;
import modelo.TipoHabitacion;
import vistaEmpleado.EmpleadoHabitacionesFrame;

public class AdminHabitacionesFrame extends EmpleadoHabitacionesFrame implements KeyListener{
	
	private JFrame CrearHabitacionFrame;
	private JButton crearHabitacion;
	private JButton borrarHabitacion;
	private JButton editarHabitacion;
	private DefaultTableModel modeloTablaInfo;
	private JPanel panelEditar;
	
	private JComboBox<TipoHabitacion> cajaETipo;
	private DefaultTableModel modeloETablaCamas;
	private JTable tablaECamas;
	private JButton botonAgregarCamas;
	private JButton botonEliminarCamas;
	private DefaultTableModel modeloETablaServicios;
	private JTable tablaEServicios;
	private JButton botonAgregarServicio;
	private JButton botonEliminarServicios;
	private JButton botonEditarCarac;
	private JFrame panelCaracteristicas;
	
	private JCheckBox cajaBalcon;
	private JCheckBox cajaVista;
	private JCheckBox cajaCocina;
	private JCheckBox cajaAire;
	private JCheckBox cajaCalefaccion;
	private JCheckBox cajaTV;
	private JCheckBox cajaCafetera;
	private JCheckBox cajaRopa;
	private JCheckBox cajaPlancha;
	private JCheckBox cajaSecador;
	private JCheckBox cajaVoltajeAC;
	private JCheckBox cajaUSBA;
	private JCheckBox cajaUSBC;
	private JCheckBox cajaDesayuno;
	private JButton OKCaracteristicas;
	private JButton deselectAll;
	private JButton selectAll;
	private JButton botonEditarHecho;
	private JButton botonEditarDescartar;
	private JFrame frameEditar;
	private Integer ID;
	
	
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
		check.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 80));
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
		
		editarHabitacion = new JButton("Editar habitación");
		editarHabitacion.setPreferredSize(new Dimension(200,50));
		editarHabitacion.setFont(fontButton);
		editarHabitacion.setBackground(Color.decode("#D0ECF2"));
		editarHabitacion.addActionListener(this);

		
		check.add(crearHabitacion);
		check.add(editarHabitacion);
		check.add(borrarHabitacion);
		
		constraints.gridx = 0;
		constraints.gridy = 1;
        constraints.insets = new Insets(0, 0, 0, 0);

        constraints.fill = GridBagConstraints.HORIZONTAL;		
        constraints.anchor = GridBagConstraints.SOUTH;		
		panelDerecho.add(check, constraints);
		
//=================================================================================//
		
		cajaETipo = new JComboBox<TipoHabitacion>(TipoHabitacion.values());
		botonAgregarCamas = new JButton();
		botonEliminarCamas = new JButton();
		botonAgregarServicio = new JButton();
		botonEliminarServicios = new JButton();
		botonEditarCarac = new JButton();
		cajaBalcon = new JCheckBox();
		cajaVista = new JCheckBox();
		cajaCocina = new JCheckBox();
		cajaAire = new JCheckBox();
		cajaCalefaccion = new JCheckBox();
		cajaTV = new JCheckBox();
		cajaCafetera = new JCheckBox();
		cajaRopa = new JCheckBox();
		cajaPlancha = new JCheckBox();
		cajaSecador = new JCheckBox();
		cajaVoltajeAC = new JCheckBox();
		cajaUSBA = new JCheckBox();
		cajaUSBC = new JCheckBox();
		cajaDesayuno = new JCheckBox();
		OKCaracteristicas = new JButton("Ok");
		OKCaracteristicas.addActionListener(this);
		deselectAll = new JButton("Deseleccionar todas");
		deselectAll.addActionListener(this);
		selectAll = new JButton("Seleccionar todas");
		selectAll.addActionListener(this);
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
	        String carac = windowManager.getCaracteristicas(Integer.parseInt(id));
	        int cantidadCaracteristicas;
	        cantidadCaracteristicas = carac.split(",").length; 
	        if (carac.equals("Caracteristicas adicionales:\nNinguna.")) {
	        	cantidadCaracteristicas = 0;
	        }
	        cantidadCaracteristicas += 7;
	        String caracteristicas = cantidadCaracteristicas + ", Click para ver";
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
	
	private void editarHabitacion() {
		int row = tablaHabitaciones.getSelectedRow();
		//JOptionPane.showMessageDialog(null, ID);
		if (row == -1) {
			JOptionPane.showMessageDialog(null, "Seleccione una habitación primero");
		}
		else {
			ID = Integer.parseInt(tablaHabitaciones.getValueAt(row, 0).toString());
			
			int opcion = JOptionPane.YES_OPTION;
			opcion = JOptionPane.showConfirmDialog(null, "¿Desea editar la habitación: " + ID + "?", "Panel de Opciones", JOptionPane.YES_NO_OPTION);
			
	        if (opcion == JOptionPane.YES_OPTION) {
	        	setEditarHabitacion(ID);
	    		resetDatos();
	    		cargarDatos();
	    		//JOptionPane.showMessageDialog(null, "La habitación " + ID + " se editó correctamente.");
	        }
	    	else {
	    		JOptionPane.showMessageDialog(null, "Acción cancelada.");
	    	}
			}
		}
	
	public void setEditarHabitacion(Integer ID) {
		GridBagLayout gridbag = new GridBagLayout();
		GridBagConstraints constraints = new GridBagConstraints();
		
		frameEditar = new JFrame("Editar habitacion");
		frameEditar.setLocationRelativeTo(null);
		frameEditar.setSize(700, 650);
		panelEditar = new JPanel();
		panelEditar.setLayout(gridbag);
		panelEditar.setBackground(Color.decode("#ccd2c2"));
		
		
		cajaETipo.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		JLabel Etipo = new JLabel("Tipo de habitacion");
		Etipo.setForeground(Color.black);
		Etipo.setFont(new Font("Times New Roman", Font.BOLD, 25));
		
		constraints.weightx = 1;
		constraints.weighty = 1;
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.anchor = GridBagConstraints.NORTHWEST;
		constraints.ipady = 10;
		constraints.ipadx = 60;
		constraints.insets = new Insets(20, 20, 20, 20);
		panelEditar.add(cajaETipo, constraints);
		constraints.gridx = 1;
		constraints.gridy = 0;
		//constraints.insets = new Insets(0, 40, -5, 0);
		panelEditar.add(Etipo, constraints);
		
		////////////////////////////////////////////////////////////////////
		
		JLabel ECamas = new JLabel("Editar camas");
		ECamas.setForeground(Color.black);
		ECamas.setFont(new Font("Times New Roman", Font.BOLD, 25));
		
		constraints.gridx = 1;
		constraints.gridy = 1;
		constraints.anchor = GridBagConstraints.NORTHWEST;
		constraints.ipady = 5;
		//constraints.insets = new Insets(5, 10, 5, 10);
		//constraints.insets = new Insets(0, 40, -5, 0);
		panelEditar.add(ECamas, constraints);
		
		String[] columnasCamas = {"Capacidad", "Exclusiva para niños"}; //Nombre de las columnas
        modeloETablaCamas = new DefaultTableModel(columnasCamas, 0);
	    
	    //Diseño de la tabla
        tablaECamas = new JTable(modeloETablaCamas);
        tablaECamas.getTableHeader().setReorderingAllowed(false);
        tablaECamas.setDefaultEditor(Object.class, null);
        tablaECamas.getTableHeader().setBackground(Color.decode("#204473"));
        tablaECamas.getTableHeader().setForeground(Color.white);
        tablaECamas.getTableHeader().setReorderingAllowed(false);
        tablaECamas.getTableHeader().setFont(new Font("Times New Roman", 1, 25));
        tablaECamas.setFont(new Font("Times New Roman", 1, 20));
        tablaECamas.setRowHeight(40);
        tablaECamas.setEnabled(true);

        DefaultTableCellRenderer modeloCentrarCamas = new DefaultTableCellRenderer();
        modeloCentrarCamas.setHorizontalAlignment(SwingConstants.CENTER);
        modeloCentrarCamas.setForeground(Color.BLACK);
        for (int i = 0; i < columnasCamas.length; i++) {
        	tablaECamas.getColumnModel().getColumn(i).setCellRenderer(modeloCentrarCamas);	
		}
        

        JScrollPane ETablaCamas = new JScrollPane(tablaECamas);

        //Tamaño y ubicacion de la tabla en el panel
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.ipadx = 1;
        constraints.ipady = 1;
        constraints.gridwidth = 1;
        constraints.gridheight = 3;
        
		//constraints.anchor = GridBagConstraints.CENTER;
		constraints.ipady = 5;
		//constraints.insets = new Insets(20, 20, 20, 20);
        
        //constraints.insets = new Insets(-100, 20, -100, 20);

        constraints.fill = GridBagConstraints.BOTH;
        //constraints.anchor = GridBagConstraints.CENTER;

		panelEditar.add(ETablaCamas, constraints);
        
        botonAgregarCamas = new JButton("Agregar cama");
        botonAgregarCamas.addActionListener(this);
		botonAgregarCamas.setForeground(Color.BLACK);
		botonAgregarCamas.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
		constraints.gridx = 1;
		constraints.gridy = 2;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.ipadx = 25;
		constraints.ipady = 20;
        constraints.fill = GridBagConstraints.NONE;
        //constraints.insets = new Insets(10, 0, 20, 0);

        //constraints.fill = GridBagConstraints.NONE;
        //constraints.anchor = GridBagConstraints.SOUTH;

        panelEditar.add(botonAgregarCamas, constraints);
        
        botonEliminarCamas = new JButton("Eliminar cama");
        botonEliminarCamas.addActionListener(this);
		botonEliminarCamas.setForeground(Color.BLACK);
		botonEliminarCamas.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
		constraints.gridx = 1;
		constraints.gridy = 3;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.fill = GridBagConstraints.NONE;
        //constraints.insets = new Insets(10, 0, 20, 0);

        //constraints.fill = GridBagConstraints.NONE;
        //constraints.anchor = GridBagConstraints.SOUTH;

        panelEditar.add(botonEliminarCamas, constraints);
        
		////////////////////////////////////////////////////////////////////
				
		JLabel EServicios = new JLabel("Editar servicios");
		EServicios.setForeground(Color.black);
		EServicios.setFont(new Font("Times New Roman", Font.BOLD, 25));
		
		constraints.gridx = 1;
		constraints.gridy = 4;
		constraints.anchor = GridBagConstraints.NORTHWEST;
		constraints.ipady = 5;
		//constraints.insets = new Insets(5, 10, 5, 10);
		//constraints.insets = new Insets(0, 40, -5, 0);
		panelEditar.add(EServicios, constraints);
		
		String[] columnasServicios = {"Nombre", "Precio"}; //Nombre de las columnas
		modeloETablaServicios = new DefaultTableModel(columnasServicios, 0);
		
		//Diseño de la tabla
		tablaEServicios = new JTable(modeloETablaServicios);
		tablaEServicios.getTableHeader().setReorderingAllowed(false);
		tablaEServicios.setDefaultEditor(Object.class, null);
		tablaEServicios.getTableHeader().setBackground(Color.decode("#204473"));
		tablaEServicios.getTableHeader().setForeground(Color.white);
		tablaEServicios.getTableHeader().setReorderingAllowed(false);
		tablaEServicios.getTableHeader().setFont(new Font("Times New Roman", 1, 25));
		tablaEServicios.setFont(new Font("Times New Roman", 1, 20));
		tablaEServicios.setRowHeight(40);
		tablaEServicios.setEnabled(true);
		
		DefaultTableCellRenderer modeloCentrarServicios = new DefaultTableCellRenderer();
		modeloCentrarServicios.setHorizontalAlignment(SwingConstants.CENTER);
		modeloCentrarServicios.setForeground(Color.BLACK);
		for (int i = 0; i < columnasServicios.length; i++) {
		tablaEServicios.getColumnModel().getColumn(i).setCellRenderer(modeloCentrarServicios);	
		}
		
		
		JScrollPane ETablaServicios = new JScrollPane(tablaEServicios);
		
		//Tamaño y ubicacion de la tabla en el panel
		constraints.gridx = 0;
		constraints.gridy = 4;
		constraints.ipadx = 1;
		constraints.ipady = 1;
		constraints.gridwidth = 1;
		constraints.gridheight = 3;
		
		//constraints.anchor = GridBagConstraints.CENTER;
		constraints.ipady = 5;
		//constraints.insets = new Insets(0, 0, 0, 0);
		
		//constraints.insets = new Insets(-100, 20, -100, 20);
		
		constraints.fill = GridBagConstraints.BOTH;
		//constraints.anchor = GridBagConstraints.CENTER;
		
		panelEditar.add(ETablaServicios, constraints);
		
		botonAgregarServicio = new JButton("Agregar servicio");
		botonAgregarServicio.addActionListener(this);
		botonAgregarServicio.setForeground(Color.BLACK);
		botonAgregarServicio.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
		constraints.gridx = 1;
		constraints.gridy = 5;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.ipadx = 25;
		constraints.ipady = 20;
		constraints.fill = GridBagConstraints.NONE;
		//constraints.insets = new Insets(10, 0, 20, 0);
		
		//constraints.fill = GridBagConstraints.NONE;
		//constraints.anchor = GridBagConstraints.SOUTH;
		
		panelEditar.add(botonAgregarServicio, constraints);
		
		botonEliminarServicios = new JButton("Eliminar servicio");
		botonEliminarServicios.addActionListener(this);
		botonEliminarServicios.setForeground(Color.BLACK);
		botonEliminarServicios.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
		constraints.gridx = 1;
		constraints.gridy = 6;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.fill = GridBagConstraints.NONE;
		//constraints.insets = new Insets(10, 0, 20, 0);
		
		//constraints.fill = GridBagConstraints.NONE;
		//constraints.anchor = GridBagConstraints.SOUTH;

		panelEditar.add(botonEliminarServicios, constraints);
		
		JPanel panelInferior = new JPanel();
		panelInferior.setLayout(gridbag);
		panelInferior.setBackground(Color.decode("#ccd2c2"));
		
		botonEditarCarac = new JButton("Editar caracteristicas");
		botonEditarCarac.addActionListener(this);
		botonEditarCarac.setForeground(Color.BLACK);
		botonEditarCarac.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		//constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.ipadx = 25;
		constraints.ipady = 20;
		constraints.anchor = GridBagConstraints.CENTER;
		constraints.weightx = 1;
		constraints.weighty = 1;
		constraints.insets = new Insets(20, 20, 20, 100);
		
		//constraints.fill = GridBagConstraints.NONE;
		constraints.anchor = GridBagConstraints.WEST;
		panelInferior.add(botonEditarCarac, constraints);
		
/////////////////////////////////////////////////////////
		
		botonEditarHecho = new JButton("Hecho");
		botonEditarHecho.setBackground(Color.decode("#D0ECF2"));
		botonEditarHecho.addActionListener(this);
		botonEditarHecho.setForeground(Color.BLACK);
		botonEditarHecho.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
		constraints.gridx = 2;
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		//constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.ipadx = 8;
		constraints.ipady = 3;
		constraints.anchor = GridBagConstraints.EAST;
		constraints.weightx = 1;
		constraints.weighty = 1;
		constraints.insets = new Insets(20, 20, 20, 20);
		
		//constraints.fill = GridBagConstraints.NONE;
		//constraints.anchor = GridBagConstraints.SOUTH;
		panelInferior.add(botonEditarHecho, constraints);
		
		botonEditarDescartar = new JButton("Descartar");
		botonEditarDescartar.setBackground(Color.decode("#D0ECF2"));
		botonEditarDescartar.addActionListener(this);
		botonEditarDescartar.setForeground(Color.BLACK);
		botonEditarDescartar.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
		constraints.gridx = 1;
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		//constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.ipadx = 8;
		constraints.ipady = 3;
		constraints.anchor = GridBagConstraints.EAST;
		constraints.weightx = 1;
		constraints.weighty = 1;
		constraints.insets = new Insets(20, 20, 20, 20);
		
		//constraints.fill = GridBagConstraints.NONE;
		//constraints.anchor = GridBagConstraints.SOUTH;
		panelInferior.add(botonEditarDescartar, constraints);
		
		constraints.gridx = 0;
		constraints.gridy = 7;
		constraints.gridwidth = 2;
		constraints.gridheight = 1;
		//constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.ipadx = 20;
		constraints.ipady = 15;
		constraints.anchor = GridBagConstraints.CENTER;
		constraints.weightx = 2;
		//constraints.insets = new Insets(10, 0, 20, 0);


		panelEditar.add(panelInferior, constraints);
		
		Habitacion habitacion = windowManager.getHabitacion(ID);
		String caracteristics = habitacion.getCaracteristicas().substring(29, habitacion.getCaracteristicas().length()-1);
		System.out.println(caracteristics);
		String[] caracteristicas = caracteristics.split(", ");
		TipoHabitacion tipo = habitacion.getTipoHabitacion();
		cargarTablaECamas(ID);
		cargarTablaEServicios(ID);
		cajaETipo.setSelectedItem(tipo);
		setEcaracteristics(caracteristicas);
		
		frameEditar.add(panelEditar);
		frameEditar.setVisible(true);
	}
	
	private void setEcaracteristics(String[] caracteristicas) {
		ArrayList<String> array = new ArrayList<>();
		for (String carac : caracteristicas) {
			array.add(carac);
		}
		if (array.contains("Balcón"))
			cajaBalcon.setSelected(true);
		if (array.contains("Vista"))
			cajaVista.setSelected(true);
		if (array.contains("Cocina"))
			cajaCocina.setSelected(true);
		if (array.contains("Aire"))
			cajaAire.setSelected(true);
		if (array.contains("Calefaccion"))
			cajaCalefaccion.setSelected(true);
		if (array.contains("TV"))
			cajaTV.setSelected(true);
		if (array.contains("Cafetera"))
			cajaCafetera.setSelected(true);
		if (array.contains("Ropa"))
			cajaRopa.setSelected(true);
		if (array.contains("Plancha"))
			cajaPlancha.setSelected(true);
		if (array.contains("Secador"))
			cajaSecador.setSelected(true);
		if (array.contains("Voltaje AC"))
			cajaVoltajeAC.setSelected(true);
		if (array.contains("USB-A"))
			cajaUSBA.setSelected(true);
		if (array.contains("USB-C"))
			cajaUSBC.setSelected(true);
		if (array.contains("Incluye desayuno"))
			cajaDesayuno.setSelected(true);
	}
	
	public void cargarTablaECamas(Integer ID) {
		ArrayList<String[]> camas = windowManager.getCamas(ID+"");
		for (String[] cama : camas) {
			modeloETablaCamas.addRow(cama);
		}
	}
	
	public void cargarTablaEServicios(Integer ID) {
		ArrayList<String[]> servicios = windowManager.getServicios(ID+"");
		for (String[] servicio : servicios) {
			double precio = Double.parseDouble(servicio[1]);
			NumberFormat nf= NumberFormat.getInstance();
			nf.setMaximumFractionDigits(0);
		    String precioS = nf.format(precio);
		    servicio[1] = precioS;
		    //precioS = precioS.substring(0, precioS.length()-3);
			modeloETablaServicios.addRow(servicio);
		}
	}
	
	private boolean verificarReservada(Integer ID) {
		return windowManager.reservada(ID);
	}
	
	public void editarCaracteristicas() {
		GridBagLayout gridbag = new GridBagLayout();
		GridBagConstraints constraints = new GridBagConstraints();
		
		panelCaracteristicas = new JFrame("Caracteristicas");
		panelCaracteristicas.setSize(480, 400);
		panelCaracteristicas.setLayout(gridbag);
		panelCaracteristicas.setLocationRelativeTo(null);
		panelCaracteristicas.setBackground(Color.decode("#ccd2c2"));
		
		
		cajaBalcon.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		JLabel balcon = new JLabel("Balcon");
		balcon.setForeground(Color.black);
		balcon.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets = new Insets(5, 10, 5, 10);
		panelCaracteristicas.add(cajaBalcon, constraints);
		constraints.gridx = 1;
		constraints.gridy = 0;
		//constraints.insets = new Insets(0, 40, -5, 0);
		panelCaracteristicas.add(balcon, constraints);
		
		
		cajaVista.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		JLabel vista = new JLabel("Vista");
		vista.setForeground(Color.black);
		vista.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
		constraints.gridx = 0;
		constraints.gridy = 1;
		//constraints.insets = new Insets(-5, 40, 0, 0);
		panelCaracteristicas.add(cajaVista, constraints);
		constraints.gridx = 1;
		constraints.gridy = 1;
		//constraints.insets = new Insets(0, 40, -5, 0);
		panelCaracteristicas.add(vista, constraints);
		
		
		cajaCocina.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		JLabel cocina = new JLabel("Cocina integrada");
		cocina.setForeground(Color.black);
		cocina.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
		constraints.gridx = 0;
		constraints.gridy = 2;
		//constraints.insets = new Insets(-5, 40, 0, 0);
		panelCaracteristicas.add(cajaCocina, constraints);
		constraints.gridx = 1;
		constraints.gridy = 2;
		//constraints.insets = new Insets(0, 40, -5, 0);
		panelCaracteristicas.add(cocina, constraints);
		
		cajaAire.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		JLabel Aire = new JLabel("Aire");
		Aire.setForeground(Color.black);
		Aire.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
		constraints.gridx = 0;
		constraints.gridy = 3;
		//constraints.insets = new Insets(-5, 40, 0, 0);
		panelCaracteristicas.add(cajaAire, constraints);
		constraints.gridx = 1;
		constraints.gridy = 3;
		//constraints.insets = new Insets(0, 40, -5, 0);
		panelCaracteristicas.add(Aire, constraints);
		
		cajaCalefaccion.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		JLabel Calefaccion = new JLabel("Calefaccion");
		Calefaccion.setForeground(Color.black);
		Calefaccion.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
		constraints.gridx = 0;
		constraints.gridy = 4;
		//constraints.insets = new Insets(-5, 40, 0, 0);
		panelCaracteristicas.add(cajaCalefaccion, constraints);
		constraints.gridx = 1;
		constraints.gridy = 4;
		//constraints.insets = new Insets(0, 40, -5, 0);
		panelCaracteristicas.add(Calefaccion, constraints);
		
		cajaTV.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		JLabel TV = new JLabel("TV");
		TV.setForeground(Color.black);
		TV.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
		constraints.gridx = 0;
		constraints.gridy = 5;
		//constraints.insets = new Insets(-5, 40, 0, 0);
		panelCaracteristicas.add(cajaTV, constraints);
		constraints.gridx = 1;
		constraints.gridy = 5;
		//constraints.insets = new Insets(0, 40, -5, 0);
		panelCaracteristicas.add(TV, constraints);
		
		cajaCafetera.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		JLabel Cafetera = new JLabel("Cafetera");
		Cafetera.setForeground(Color.black);
		Cafetera.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
		constraints.gridx = 0;
		constraints.gridy = 6;
		//constraints.insets = new Insets(-5, 40, 0, 0);
		panelCaracteristicas.add(cajaCafetera, constraints);
		constraints.gridx = 1;
		constraints.gridy = 6;
		//constraints.insets = new Insets(0, 40, -5, 0);
		panelCaracteristicas.add(Cafetera, constraints);
		
		cajaRopa.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		JLabel Ropa = new JLabel("Ropa");
		Ropa.setForeground(Color.black);
		Ropa.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
		constraints.gridx = 4;
		constraints.gridy = 0;
		//constraints.insets = new Insets(-5, 40, 0, 0);
		panelCaracteristicas.add(cajaRopa, constraints);
		constraints.gridx = 5;
		constraints.gridy = 0;
		//constraints.insets = new Insets(0, 40, -5, 0);
		panelCaracteristicas.add(Ropa, constraints);
		
		cajaPlancha.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		JLabel Plancha = new JLabel("Plancha");
		Plancha.setForeground(Color.black);
		Plancha.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
		constraints.gridx = 4;
		constraints.gridy = 1;
		//constraints.insets = new Insets(-5, 40, 0, 0);
		panelCaracteristicas.add(cajaPlancha, constraints);
		constraints.gridx = 5;
		constraints.gridy = 1;
		//constraints.insets = new Insets(0, 40, -5, 0);
		panelCaracteristicas.add(Plancha, constraints);
		
		cajaSecador.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		JLabel Secador = new JLabel("Secador");
		Secador.setForeground(Color.black);
		Secador.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
		constraints.gridx = 4;
		constraints.gridy = 2;
		//constraints.insets = new Insets(-5, 40, 0, 0);
		panelCaracteristicas.add(cajaSecador, constraints);
		constraints.gridx = 5;
		constraints.gridy = 2;
		//constraints.insets = new Insets(0, 40, -5, 0);
		panelCaracteristicas.add(Secador, constraints);
		
		cajaVoltajeAC.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		JLabel VoltajeAC = new JLabel("Voltaje AC");
		VoltajeAC.setForeground(Color.black);
		VoltajeAC.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
		constraints.gridx = 4;
		constraints.gridy = 3;
		//constraints.insets = new Insets(-5, 40, 0, 0);
		panelCaracteristicas.add(cajaVoltajeAC, constraints);
		constraints.gridx = 5;
		constraints.gridy = 3;
		//constraints.insets = new Insets(0, 40, -5, 0);
		panelCaracteristicas.add(VoltajeAC, constraints);
		
		cajaUSBA.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		JLabel USBA = new JLabel("USB-A");
		USBA.setForeground(Color.black);
		USBA.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
		constraints.gridx = 4;
		constraints.gridy = 4;
		//constraints.insets = new Insets(-5, 40, 0, 0);
		panelCaracteristicas.add(cajaUSBA, constraints);
		constraints.gridx = 5;
		constraints.gridy = 4;
		//constraints.insets = new Insets(0, 40, -5, 0);
		panelCaracteristicas.add(USBA, constraints);
		
		cajaUSBC.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		JLabel USBC = new JLabel("USB-C");
		USBC.setForeground(Color.black);
		USBC.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
		constraints.gridx = 4;
		constraints.gridy = 5;
		//constraints.insets = new Insets(-5, 40, 0, 0);
		panelCaracteristicas.add(cajaUSBC, constraints);
		constraints.gridx = 5;
		constraints.gridy = 5;
		//constraints.insets = new Insets(0, 40, -5, 0);
		panelCaracteristicas.add(USBC, constraints);
		
		cajaDesayuno.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		JLabel Desayuno = new JLabel("Desayuno");
		Desayuno.setForeground(Color.black);
		Desayuno.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
		constraints.gridx = 4;
		constraints.gridy = 6;
		//constraints.insets = new Insets(-5, 40, 0, 0);
		panelCaracteristicas.add(cajaDesayuno, constraints);
		constraints.gridx = 5;
		constraints.gridy = 6;
		//constraints.insets = new Insets(0, 40, -5, 0);
		panelCaracteristicas.add(Desayuno, constraints);
		
		OKCaracteristicas.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		OKCaracteristicas.setForeground(Color.black);
		
		deselectAll.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		deselectAll.setForeground(Color.black);
		
		constraints.gridx = 5;
		constraints.gridy = 10;
		//constraints.insets = new Insets(-5, 40, 0, 0);
		panelCaracteristicas.add(OKCaracteristicas, constraints);
		constraints.gridx = 1;
		constraints.gridy = 10;
		//constraints.insets = new Insets(0, 40, -5, 0);
		panelCaracteristicas.add(deselectAll, constraints);
		
		selectAll.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		selectAll.setForeground(Color.black);
		constraints.gridx = 1;
		constraints.gridy = 9;
		//constraints.insets = new Insets(0, 40, -5, 0);
		panelCaracteristicas.add(selectAll, constraints);
		
		JLabel vacio = new JLabel("");
		constraints.gridx = 2;
		constraints.gridy = 8;
		//constraints.insets = new Insets(0, 40, -5, 0);
		panelCaracteristicas.add(vacio, constraints);
		
		JLabel vacio2 = new JLabel("");
		constraints.gridx = 3;
		constraints.gridy = 7;
		//constraints.insets = new Insets(0, 40, -5, 0);
		panelCaracteristicas.add(vacio2, constraints);
		
		panelCaracteristicas.setVisible(true);
	}
	
	private void nuevaCama() {
		JFrame crearCama = new JFrame();

	    // Crear un JXDatePicker con la fecha actual
	    
	    String[] capacidades = {"1", "2", "3", "4"};
	    JComboBox<String> capacidad = new JComboBox<>(capacidades);
	    JLabel mensaje = new JLabel("Capacidad"); 
	    JPanel panel = new JPanel();
	    panel.setPreferredSize(new Dimension (250, 75));
	    GridBagLayout gridbag = new GridBagLayout();
		GridBagConstraints constraints = new GridBagConstraints();
		panel.setLayout(gridbag);
		
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.ipadx = 0;
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets = new Insets(0, 0, 0, 30);
		panel.add(mensaje, constraints);
		constraints.gridx = 1;
		constraints.gridy = 0;
		constraints.ipadx = -30;
		constraints.insets = new Insets(0, 0, 0, 0);
		panel.add(capacidad, constraints);
		
		JCheckBox cajaNinios = new JCheckBox();
		cajaNinios.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		JLabel mensaje2 = new JLabel("Exlusiva para niños");
		mensaje2.setPreferredSize(new Dimension(100, 50));
		
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.ipadx = 5;
		constraints.insets = new Insets(0, 0, 0, 30);
		panel.add(mensaje2, constraints);
		constraints.gridx = 1;
		constraints.gridy = 1;
		constraints.ipadx = 0;
		constraints.insets = new Insets(0, 0, 0, 0);
		panel.add(cajaNinios, constraints);

	    int opcion = JOptionPane.showOptionDialog(crearCama, panel, "Crear una nueva cama", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);
	    
	    if (opcion == 2 || opcion == -1) {
	    	JOptionPane.showMessageDialog(null, "Cama descartada");
	    }
	    else {
	    	String capa = capacidad.getSelectedItem().toString();
	    	boolean ninios = cajaNinios.isSelected();
	    	String apta = ninios ? "Sí" : "No";
	    	String[] fila = {capa, apta};
	    	modeloETablaCamas.addRow(fila);
	    }
	}
	
	private void eliminarCama() {
		int row = tablaECamas.getSelectedRow();
		//JOptionPane.showMessageDialog(null, ID);
		if (row == -1) {
			JOptionPane.showMessageDialog(null, "Seleccione una cama primero");
		}
		else {
			int opcion = JOptionPane.YES_OPTION;
			opcion = JOptionPane.showConfirmDialog(null, "¿Desea eliminar esta cama?", "Panel de Opciones", JOptionPane.YES_NO_OPTION);
			
	        if (opcion == JOptionPane.YES_OPTION) {
	    		modeloETablaCamas.removeRow(row);
	    		//resetDatos();
	    		//cargarDatos();
	        }
	    	else {
	    		JOptionPane.showMessageDialog(null, "Acción cancelada.");
	    	}
		}
	}
	
	private void eliminarServicio() {
		int row = tablaEServicios.getSelectedRow();
		//JOptionPane.showMessageDialog(null, ID);
		if (row == -1) {
			JOptionPane.showMessageDialog(null, "Seleccione un servicio primero");
		}
		else {
			int opcion = JOptionPane.YES_OPTION;
			opcion = JOptionPane.showConfirmDialog(null, "¿Desea eliminar este servicio?", "Panel de Opciones", JOptionPane.YES_NO_OPTION);
			
	        if (opcion == JOptionPane.YES_OPTION) {
	    		modeloETablaServicios.removeRow(row);
	    		//resetDatos();
	    		//cargarDatos();
	        }
	    	else {
	    		JOptionPane.showMessageDialog(null, "Acción cancelada.");
	    	}
		}
	}
	
	private void nuevoServicio() {
		JFrame crearServicio = new JFrame();

	    // Crear un JXDatePicker con la fecha actual
	    
	    JTextField nombre = new JTextField();
	    JLabel mensaje = new JLabel("Nombre"); 
	    JPanel panel = new JPanel();
	    panel.setPreferredSize(new Dimension (250, 75));
	    GridBagLayout gridbag = new GridBagLayout();
		GridBagConstraints constraints = new GridBagConstraints();
		panel.setLayout(gridbag);
		
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.ipadx = 0;
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets = new Insets(0, 0, 10, 30);
		panel.add(mensaje, constraints);
		constraints.gridx = 1;
		constraints.gridy = 0;
		constraints.ipadx = 80;
		constraints.insets = new Insets(0, 0, 10, 0);
		panel.add(nombre, constraints);
		
		JTextField precio = new JTextField();
		precio.addKeyListener(this);
		JLabel mensaje2 = new JLabel("Precio");
		mensaje2.setPreferredSize(new Dimension(100, 50));
		
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.ipadx = 5;
		constraints.ipady = 1;
		constraints.insets = new Insets(10, 0, 0, 30);
		panel.add(mensaje2, constraints);
		constraints.gridx = 1;
		constraints.gridy = 1;
		constraints.ipadx = 80;
		constraints.insets = new Insets(10, 0, 0, 0);
		panel.add(precio, constraints);

	    int opcion = JOptionPane.showOptionDialog(crearServicio, panel, "Crear un nuevo servicio", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);
	    
	    if (opcion == 2 || opcion == -1) {
	    	JOptionPane.showMessageDialog(null, "Servicio descartado");
	    }
	    else {
	    	if (!(nombre.getText().strip().equals("")) && !(precio.getText().equals(""))) {
		    	String nom = nombre.getText();
		    	String prec = precio.getText();
		    	String[] fila = {nom, prec};
		    	modeloETablaServicios.addRow(fila);
	    	}
	    	else {
	    		JOptionPane.showMessageDialog(null, "Verifique que ni el nombre ni el precio estén vacíos", "Error de nombre o precio", JOptionPane.ERROR_MESSAGE);;
	    	}
	    }
	}
	
	private ArrayList<Cama> getECamas() {
		ArrayList<Cama> array = new ArrayList<>();
		int cantidadFilas = modeloETablaCamas.getRowCount();
		for (int i = 0 ; i < cantidadFilas ; i++) {
			String capacidad = modeloETablaCamas.getValueAt(i, 0).toString();
			String apto = modeloETablaCamas.getValueAt(i, 1).toString();
			boolean exlusiva = (apto == "No") ? false : true;
			Cama cama = windowManager.crearCama(Integer.parseInt(capacidad), exlusiva);
			array.add(cama);
		}
		return array;
	}
	
	private ArrayList<Servicio> getEServicios() {
		ArrayList<Servicio> array = new ArrayList<>();
		int cantidadFilas = modeloETablaServicios.getRowCount();
		for (int i = 0 ; i < cantidadFilas ; i++) {
			String nombre = modeloETablaServicios.getValueAt(i, 0).toString();
			String precioS = modeloETablaServicios.getValueAt(i, 1).toString().replace(",", "");
			String precio = precioS.replace(".", "");
			Servicio servicio = windowManager.crearServicio(nombre, Integer.parseInt(precio));
			array.add(servicio);
		}
		return array;
	}
	
	private String getCaracteristicas() {
		String caracteristicas = "Caracteristicas adicionales:\n";
//		boolean balcon = cajaBalcon.isSelected();
//		boolean vista = cajaVista.isSelected();
//		boolean cocina = cajaCocina.isSelected();
		if (cajaBalcon.isSelected())
			caracteristicas+= "Balcón, ";
		if (cajaVista.isSelected())
			caracteristicas+= "Vista, ";
		if (cajaCocina.isSelected())
			caracteristicas+= "Cocina, ";
		if (cajaAire.isSelected())
			caracteristicas+= "Aire, ";
		if (cajaCalefaccion.isSelected())
			caracteristicas+= "Calefaccion, ";
		if (cajaTV.isSelected())
			caracteristicas+= "TV, ";
		if (cajaCafetera.isSelected())
			caracteristicas+= "Cafetera, ";
		if (cajaRopa.isSelected())
			caracteristicas+= "Ropa, ";
		if (cajaPlancha.isSelected())
			caracteristicas+= "Plancha, ";
		if (cajaSecador.isSelected())
			caracteristicas+= "Secador, ";
		if (cajaVoltajeAC.isSelected())
			caracteristicas+= "Voltaje AC, ";
		if (cajaUSBA.isSelected())
			caracteristicas+= "USB-A, ";
		if (cajaUSBC.isSelected())
			caracteristicas+= "USB-C, ";
		if (cajaDesayuno.isSelected())
			caracteristicas+= "Incluye desayuno, ";
				
		int fin = (caracteristicas.length())-2;
		if (caracteristicas.length() > 30) {
			caracteristicas = caracteristicas.substring(0, fin);
		}
		else {
			caracteristicas += "Ninguna";
		}
		caracteristicas += ".";
		return caracteristicas;
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
			
		case "Editar habitación":
			resetCaracteristicas();
			editarHabitacion();
			break;
			
		case "Agregar cama":
			nuevaCama();
			break;
		
		case "Eliminar cama":
			eliminarCama();
			break;
		
		case "Agregar servicio":
			nuevoServicio();
			break;
		
		case "Eliminar servicio":
			eliminarServicio();
			break;
			
		case "Editar caracteristicas":
			editarCaracteristicas();
			break;
			
		case "Hecho":
			TipoHabitacion tipo = (TipoHabitacion) cajaETipo.getSelectedItem();
			ArrayList<Cama> listaCamas = getECamas();
			String caracteristicas = getCaracteristicas();
			ArrayList<Servicio> listaServicios = getEServicios();
			if (listaCamas.size() == 0) {
				JOptionPane.showMessageDialog(null, "Asegurese de colocar al menos una cama e intentelo de nuevo", "Error de camas", JOptionPane.ERROR_MESSAGE);
			}
			else {
				Habitacion habitacion = windowManager.getHabitacion(ID);
				habitacion.setTipoHabitacion(tipo);
				habitacion.setListaCamas(listaCamas);
				habitacion.setListaServicios(listaServicios);
				habitacion.setCaracteristicas(caracteristicas);
				JOptionPane.showMessageDialog(null, "Habitacion ediatada correctamente");
			}
			frameEditar.dispose();
			resetDatos();
			break;
			
		case "Descartar":
			frameEditar.dispose();;
			JOptionPane.showMessageDialog(null, "Cambios descartados");
			break;
			
		case "Ok":
			panelCaracteristicas.setVisible(false);
			break;
		case "Deseleccionar todas":
			resetCaracteristicas();
			break;
		case "Seleccionar todas":
			selectAllCaracteristicas();
			break;
			
		default:
		}
	}
	
	public void resetCaracteristicas() {
		cajaBalcon.setSelected(false);
		cajaVista.setSelected(false);
		cajaCocina.setSelected(false);
		cajaAire.setSelected(false);
		cajaCalefaccion.setSelected(false);
		cajaTV.setSelected(false);
		cajaCafetera.setSelected(false);
		cajaRopa.setSelected(false);
		cajaPlancha.setSelected(false);
		cajaSecador.setSelected(false);
		cajaVoltajeAC.setSelected(false);
		cajaUSBA.setSelected(false);
		cajaUSBC.setSelected(false);
		cajaDesayuno.setSelected(false);
	}
	
	public void selectAllCaracteristicas() {
		cajaBalcon.setSelected(true);
		cajaVista.setSelected(true);
		cajaCocina.setSelected(true);
		cajaAire.setSelected(true);
		cajaCalefaccion.setSelected(true);
		cajaTV.setSelected(true);
		cajaCafetera.setSelected(true);
		cajaRopa.setSelected(true);
		cajaPlancha.setSelected(true);
		cajaSecador.setSelected(true);
		cajaVoltajeAC.setSelected(true);
		cajaUSBA.setSelected(true);
		cajaUSBC.setSelected(true);
		cajaDesayuno.setSelected(true);
	}


	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
        JTextField caja = (JTextField) e.getSource();
        int numeros;
        try {
            String cadena = caja.getText().replace(",", "");
            cadena = cadena.replace(".", "");
            numeros = Integer.parseInt(cadena);
            NumberFormatter numberFormatter = new NumberFormatter();
            numberFormatter.setValueClass(Integer.class);
            numberFormatter.setMinimum(1);
            numberFormatter.setMaximum(Integer.MAX_VALUE);
            numberFormatter.setAllowsInvalid(false);
            JFormattedTextField input = new JFormattedTextField(numberFormatter);
            input.setText(numeros + "");
            String texto = input.getText();
            if (texto.length() == 0) {
                texto = caja.getText();
                texto = texto.substring(0, texto.length() - 1);
                caja.setText(texto);
            }
            else {
                caja.setText(input.getText());
            }
        } catch (NumberFormatException nfe) {
            String texto = caja.getText();
            if (texto.length() > 0) {
                texto = texto.substring(0, texto.length() - 1);
            }
            else {
            }
            caja.setText(texto);
        }
    }

}
