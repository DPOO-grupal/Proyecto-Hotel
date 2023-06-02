package vistaAdmin;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.text.NumberFormatter;

import com.formdev.flatlaf.json.ParseException;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.JFormattedTextField;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JCheckBox;
import javax.swing.BorderFactory;

import controlador.WindowManager;
import modelo.Cama;
import modelo.Habitacion;
import modelo.Servicio;
import modelo.TipoHabitacion;

public class CrearHabitacionFrame extends FrameBaseInfo implements KeyListener{

	private JSpinner cajaPiso;
	private JComboBox cajaTipoHabitacion;
	private JTextField cajaCapacidad;
	private JTextField cajaServicioHabitacion;
	private JTextField cajaPrecio;
	private JCheckBox cajaApto;
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
	private DefaultTableModel modeloTablaCamas;
	private DefaultTableModel modeloTablaServicios;
	private JTable tablaCamas;
	private JTable tablaServicios;
	private JButton crearHabitacion;
	private JButton botonCaracteristicas;
	private JButton borrarHabitacion;
	private JButton botonServicios;
	private JButton botonCamas;
	private JButton OKCaracteristicas;
	private JButton deselectAll;
	private JButton selectAll;
	private JFrame panelCaracteristicas;

	public CrearHabitacionFrame(WindowManager windowManager) {
		super(windowManager);
	}

	@Override
	protected void setPanelInfo() {
		GridBagLayout gridbag = new GridBagLayout();
		GridBagConstraints constraints = new GridBagConstraints();
		panelDerecho.setLayout(gridbag);
		panelDerecho.setBackground(Color.decode("#b2bba4"));
//-------------------------------------------------------------------------------------------------------------------------------//		
		JLabel labelCamas = new JLabel("Camas");
		labelCamas.setForeground(Color.BLACK);
		labelCamas.setFont(new Font("Times New Roman", Font.BOLD, 30));
		
		constraints.weightx = 1;
		constraints.weighty = 1;
		
		constraints.gridx = 0;
		constraints.gridy = 0;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.insets = new Insets(20, 0, 0, 0);

        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.NORTH;

        panelDerecho.add(labelCamas, constraints);
		
		//Creacion de la tabla camas
			String[] columnasCamas = {"Capacidad", "Exclusiva para niños"}; //Nombre de las columnas
	        modeloTablaCamas = new DefaultTableModel(columnasCamas, 0);
		    
		    //Diseño de la tabla
	        tablaCamas = new JTable(modeloTablaCamas);
	        tablaCamas.getTableHeader().setReorderingAllowed(false);
	        tablaCamas.setDefaultEditor(Object.class, null);
	        tablaCamas.getTableHeader().setBackground(Color.decode("#204473"));
	        tablaCamas.getTableHeader().setForeground(Color.white);
	        tablaCamas.getTableHeader().setFont(new Font("Times New Roman", 1, 25));
	        tablaCamas.setFont(new Font("Times New Roman", 1, 20));
	        tablaCamas.setRowHeight(40);
	        tablaCamas.setEnabled(true);

	        DefaultTableCellRenderer modelocentrar = new DefaultTableCellRenderer();
	        modelocentrar.setHorizontalAlignment(SwingConstants.CENTER);
	        modelocentrar.setForeground(Color.BLACK);
	        for (int i = 0; i < columnasCamas.length; i++) {
	        	tablaCamas.getColumnModel().getColumn(i).setCellRenderer(modelocentrar);	
			}
	        

	        JScrollPane scrollPanel = new JScrollPane(tablaCamas);

	        //Tamaño y ubicacion de la tabla en el panel
	        constraints.gridx = 0;
	        constraints.gridy = 1;
	        
	        constraints.gridwidth = 1;
	        constraints.gridheight = 3;
	        
	        constraints.insets = new Insets(-100, 20, -100, 20);

	        constraints.fill = GridBagConstraints.BOTH;
	        constraints.anchor = GridBagConstraints.CENTER;

	        panelDerecho.add(scrollPanel, constraints);
	        
	        botonCamas = new JButton("Agregar nueva cama");
	        botonCamas.addActionListener(this);
			botonCamas.setForeground(Color.BLACK);
			botonCamas.setFont(new Font("Times New Roman", Font.PLAIN, 30));
			
			constraints.gridx = 0;
			constraints.gridy = 4;
	        constraints.gridwidth = 1;
	        constraints.gridheight = 1;
	        constraints.insets = new Insets(10, 0, 20, 0);

	        constraints.fill = GridBagConstraints.NONE;
	        constraints.anchor = GridBagConstraints.SOUTH;

	        panelDerecho.add(botonCamas, constraints);
//-------------------------------------------------------------------------------------------------------------------------------//
	        JLabel labelServicios = new JLabel("Servicios");
			labelServicios.setForeground(Color.BLACK);
			labelServicios.setFont(new Font("Times New Roman", Font.BOLD, 30));
			
			constraints.gridx = 1;
			constraints.gridy = 0;
	        constraints.gridwidth = 1;
	        constraints.gridheight = 1;
	        constraints.insets = new Insets(20, 0, 0, 0);

	        constraints.fill = GridBagConstraints.NONE;
	        constraints.anchor = GridBagConstraints.NORTH;

	        panelDerecho.add(labelServicios, constraints);
			
			//Creacion de la tabla camas
				String[] columnasServicios = {"Nombre", "Precio"}; //Nombre de las columnas
		        modeloTablaServicios = new DefaultTableModel(columnasServicios, 0);        
			    
			    //Diseño de la tabla
		        tablaServicios = new JTable(modeloTablaServicios);
		        tablaServicios.setDefaultEditor(Object.class, null);
		        tablaServicios.getTableHeader().setReorderingAllowed(false);
		        tablaServicios.getTableHeader().setBackground(Color.decode("#204473"));
		        tablaServicios.getTableHeader().setForeground(Color.white);
		        tablaServicios.getTableHeader().setFont(new Font("Times New Roman", 1, 25));
		        tablaServicios.setFont(new Font("Times New Roman", 1, 20));
		        tablaServicios.setRowHeight(40);
		        tablaServicios.setEnabled(true);

		        DefaultTableCellRenderer modelocentrarServicios = new DefaultTableCellRenderer();
		        modelocentrarServicios.setHorizontalAlignment(SwingConstants.CENTER);
		        modelocentrarServicios.setForeground(Color.BLACK);
		        for (int i = 0; i < columnasServicios.length; i++) {
		        	tablaServicios.getColumnModel().getColumn(i).setCellRenderer(modelocentrarServicios);	
				}
		        

		        JScrollPane scrollPanelServicios = new JScrollPane(tablaServicios);

		        //Tamaño y ubicacion de la tabla en el panel
		        constraints.gridx = 1;
		        constraints.gridy = 1;

		        constraints.gridwidth = 1;
		        constraints.gridheight = 3;
		        constraints.insets = new Insets(-100, 20, -100, 20);

		        constraints.fill = GridBagConstraints.BOTH;
		        constraints.anchor = GridBagConstraints.CENTER;

		        panelDerecho.add(scrollPanelServicios, constraints);
		        
		        botonServicios = new JButton("Agregar nuevo servicio");
		        botonServicios.addActionListener(this);
				botonServicios.setForeground(Color.BLACK);
				botonServicios.setFont(new Font("Times New Roman", Font.PLAIN, 30));
				
				constraints.gridx = 1;
				constraints.gridy = 4;
				
		        constraints.gridwidth = 1;
		        constraints.gridheight = 1;
		        
		        constraints.insets = new Insets(10, 0, 20, 0);

		        constraints.fill = GridBagConstraints.NONE; 
		        constraints.anchor = GridBagConstraints.SOUTH;

		        panelDerecho.add(botonServicios, constraints);
//-------------------------------------------------------------------------------------------------------------------------------//		
		JPanel check = new JPanel();
		check.setBackground(Color.decode("#7E8C69"));
		check.setPreferredSize(new Dimension(0,199));
		check.setLayout(new FlowLayout(FlowLayout.CENTER, 200, 60));
	    Font fontButton = new Font("Arial", Font.BOLD, 16);

		crearHabitacion = new JButton("Crear habitación");
		crearHabitacion.setPreferredSize(new Dimension(200,50));
		crearHabitacion.setFont(fontButton);
		crearHabitacion.setBackground(Color.decode("#D0ECF2"));
		crearHabitacion.addActionListener(this);
		
		borrarHabitacion = new JButton("Descartar habitación");
		borrarHabitacion.setPreferredSize(new Dimension(200,50));
		borrarHabitacion.setFont(fontButton);
		borrarHabitacion.setBackground(Color.decode("#D0ECF2"));
		borrarHabitacion.addActionListener(this);

		
		check.add(crearHabitacion);
		check.add(borrarHabitacion);
		
		constraints.gridx = 0;
		constraints.gridy = 5;
		constraints.gridwidth = 2;
        constraints.gridheight = 1;
        
        constraints.ipady = -90 ;
        
        constraints.insets = new Insets(0, 0, 0, 0);

        constraints.fill = GridBagConstraints.BOTH;		
        constraints.anchor = GridBagConstraints.SOUTH;		
		panelDerecho.add(check, constraints);
		OKCaracteristicas = new JButton("Ok");
		OKCaracteristicas.addActionListener(this);
		deselectAll = new JButton("Deseleccionar todas");
		deselectAll.addActionListener(this);
		selectAll = new JButton("Seleccionar todas");
		selectAll.addActionListener(this);
	}

	@Override
	protected void setPanelCrear() {
		//Edita el aspecto del panel
		GridBagLayout gridbag = new GridBagLayout();
		GridBagConstraints constraints = new GridBagConstraints();
		panelCrear.setLayout(gridbag);
		panelCrear.setBackground(Color.decode("#204473"));
		panelCrear.setBorder(BorderFactory.createEmptyBorder(50, 100, 50, 100));
		
		//Id y su caja de texto
		JLabel piso = new JLabel("Piso");
		piso.setForeground(Color.white);
		piso.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		
        SpinnerModel model = new SpinnerNumberModel(1, 1, 9, 1);

		cajaPiso = new JSpinner(model);
  		cajaPiso.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
  		
  		constraints.gridx = 0;
		constraints.gridy = 0;
        //constraints.ipady = 0;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.anchor = GridBagConstraints.WEST;	
        //constraints.ipadx = 150;
        constraints.insets = new Insets(10, 10, 1, 10);
        panelCrear.add(piso, constraints);
        
        constraints.gridx = 0;
		constraints.gridy = 1;
        constraints.ipadx = 180;
        constraints.ipady = 10;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        //constraints.ipadx = 150;
        constraints.insets = new Insets(1, 10, 10, 10);
        panelCrear.add(cajaPiso, constraints);

		//Tipo de habitacion y su caja de texto
		JLabel tipoHabitacion = new JLabel("Tipo habitación");
		tipoHabitacion.setForeground(Color.white);
		tipoHabitacion.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		
		TipoHabitacion[] tipos = TipoHabitacion.values();
		
		cajaTipoHabitacion =  new JComboBox<>(tipos);
		cajaTipoHabitacion.setPreferredSize(new Dimension(10,30));
		cajaTipoHabitacion.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
		constraints.gridx = 0;
		constraints.gridy = 2;
        //constraints.ipady = 0;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.anchor = GridBagConstraints.WEST;	
        //constraints.ipadx = 150;
        constraints.insets = new Insets(10, 10, 1, 10);
        panelCrear.add(tipoHabitacion, constraints);
        
        constraints.gridx = 0;
		constraints.gridy = 3;
        constraints.ipadx = 70;
        constraints.ipady = 10;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        //constraints.ipadx = 150;
        constraints.insets = new Insets(1, 10, 10, 10);
        panelCrear.add(cajaTipoHabitacion, constraints);

		//Tipo de habitacion y su caja de texto
		JLabel capacidad = new JLabel("Capacidad");
		capacidad.setForeground(Color.white);
		capacidad.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		
		cajaCapacidad = new JTextField();
		cajaCapacidad.setEditable(false);
		cajaCapacidad.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		//cajaCapacidad.setPreferredSize(new Dimension(100, 5));
		
		constraints.gridx = 0;
		constraints.gridy = 11;
        //constraints.ipady = 0;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.anchor = GridBagConstraints.WEST;	
        //constraints.ipadx = 150;
        constraints.insets = new Insets(10, 10, 1, 10);
		panelCrear.add(capacidad, constraints);
		constraints.gridx = 0;
		constraints.gridy = 12;
        constraints.ipadx = 180;
        constraints.ipady = 10;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        //constraints.ipadx = 150;
        constraints.insets = new Insets(1, 10, 40, 10);
		panelCrear.add(cajaCapacidad, constraints);
		
		cajaVista = new JCheckBox();
		cajaCocina = new JCheckBox();
		cajaBalcon = new JCheckBox();
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
		borrarHabitacion = new JButton();
//----------------------------------------------------------------------------------------------------------//
		//Tipo de habitacion y su caja de texto
		JLabel caracteristicas = new JLabel("Caracteristicas:");
		caracteristicas.setForeground(Color.white);
		caracteristicas.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		
		constraints.gridx = 0;
		constraints.gridy = 4;
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets = new Insets(10, 10, -5, 0);	
		panelCrear.add(caracteristicas, constraints);	
		
		botonCaracteristicas = new JButton("Editar caracteristicas");
		botonCaracteristicas.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		botonCaracteristicas.setPreferredSize(new Dimension(10,30));
		botonCaracteristicas.addActionListener(this);
		
		constraints.gridx = 0;
		constraints.gridy = 5;
		constraints.ipadx = 1;
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets = new Insets(5, 10, 20, 0);	
		panelCrear.add(botonCaracteristicas, constraints);	
		
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
	    	modeloTablaCamas.addRow(fila);
	    	String nuevaCapacidad = calcularNuevaCapacidad()+"";
	    	cajaCapacidad.setText(nuevaCapacidad);
	    }
	}
	private int calcularNuevaCapacidad() {
		int total = 0;
		int cantidadFilas = tablaCamas.getRowCount();
		for (int i = 0; i < cantidadFilas; i++) {
			int capacidad = Integer.parseInt((String) tablaCamas.getValueAt(i, 0));
			total+=capacidad;
		}
		return total;
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
		    	modeloTablaServicios.addRow(fila);
	    	}
	    	else {
	    		JOptionPane.showMessageDialog(null, "Verifique que ni el nombre ni el precio estén vacíos", "Error de nombre o precio", JOptionPane.ERROR_MESSAGE);;
	    	}
	    }
	}
	public void crearHabitacion() {
		int piso = (int) cajaPiso.getValue();
		int idHab = Habitacion.getMaxHabitacion(piso);
		TipoHabitacion tipo = (TipoHabitacion) cajaTipoHabitacion.getSelectedItem();
		windowManager.crearHabitacion(tipo, idHab);
		Habitacion habitacion = windowManager.getHabitacion(idHab);
		
		ArrayList<Servicio> servicios = crearListaServicios();
		habitacion.setListaServicios(servicios);
		
		String caracteristicas = getCaracteristicas();
		habitacion.setCaracteristicas(caracteristicas);
		
		ArrayList<Cama> camas = crearListaCamas();
		habitacion.setListaCamas(camas);
		
		JOptionPane.showMessageDialog(null, "Habitación creada correctamente");
		setVisible(false);
		volverMenu();
		
		
		//windowManager.setServiciosHabitacion(idHab, servicios);
	}
	
	private ArrayList<Cama> crearListaCamas() {
		ArrayList<Cama> arrayCamas = new ArrayList<>();
		int cantidadCamas = tablaCamas.getRowCount();
		for (int i = 0; i < cantidadCamas; i++) {
			int capacidad = Integer.parseInt(tablaCamas.getValueAt(i, 0).toString());
			String apto = tablaCamas.getValueAt(i, 1).toString();
			boolean exclusiva = (apto == "No") ? false : true;
			Cama cama = windowManager.crearCama(capacidad, exclusiva);
			arrayCamas.add(cama);
		}
		return arrayCamas;
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
	
	public ArrayList<Servicio> crearListaServicios() {
		ArrayList<Servicio> arrayServicios = new ArrayList<>();
		int cantidadServicios = tablaServicios.getRowCount();
		for (int i = 0; i < cantidadServicios; i++) {
			String nombreServicio = tablaServicios.getValueAt(i, 0).toString();
			double precioServicio = Double.parseDouble(tablaServicios.getValueAt(i, 1).toString().replace(",", ""));
			Servicio servicio = windowManager.crearServicio(nombreServicio, precioServicio);
			arrayServicios.add(servicio);
		}
		return arrayServicios;
	}
	@Override
	public void volverMenu() {
		modeloTablaCamas.getDataVector().removeAllElements();
		modeloTablaCamas.fireTableDataChanged();
		modeloTablaServicios.getDataVector().removeAllElements();
		modeloTablaServicios.fireTableDataChanged();
		windowManager.volverHabitaciones();
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
	public void resetDatos() {
		cajaPiso.setValue(new Integer(1));
		cajaTipoHabitacion.setSelectedIndex(0);
		resetCaracteristicas();
		cajaCapacidad.setText("");
		modeloTablaCamas.getDataVector().removeAllElements();
		modeloTablaCamas.fireTableDataChanged();
		modeloTablaServicios.getDataVector().removeAllElements();
		modeloTablaServicios.fireTableDataChanged();
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
	
	@Override
	protected void actionPerformedFrame(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "Agregar nueva cama":
			nuevaCama();
			break;
		case "Agregar nuevo servicio":
			nuevoServicio();
			break;
		case "Descartar habitación":
			resetDatos();
			JOptionPane.showMessageDialog(null, "Habitación descartada");
			setVisible(false);
			volverMenu();
			break;
		case "Editar caracteristicas":
			editarCaracteristicas();
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
		case "Crear habitación":
			if (tablaCamas.getRowCount() == 0) {
				JOptionPane.showMessageDialog(null, "Añade camas antes de crear la habitación.");
			}
			else {
				crearHabitacion();
				resetDatos();
			}
			break;
		default:
			break;
	}
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
