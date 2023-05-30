package vistaAdmin;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.text.NumberFormatter;

import com.formdev.flatlaf.json.ParseException;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JComboBox;
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

public class CrearHabitacionFrame extends FrameBaseInfo implements KeyListener{

	private JSpinner cajaPiso;
	private JComboBox cajaTipoHabitacion;
	private JTextField cajaCapacidad;
	private JCheckBox cajaApto;
	private JTextField cajaServicioHabitacion;
	private JTextField cajaPrecio;
	private JButton crearHabitacion;
	private JCheckBox cajaBalcon;
	private JCheckBox cajaVista;
	private JCheckBox cajaCocina;
	private JButton borrarHabitacion;
	private DefaultTableModel modeloTablaCamas;
	private JTable tablaCamas;
	private DefaultTableModel modeloTablaServicios;
	private JTable tablaServicios;
	private JButton botonServicios;
	private JButton botonCamas;

	public CrearHabitacionFrame(WindowManager windowManager) {
		super(windowManager);
	}

	@Override
	protected void setPanelInfo() {
		GridBagLayout gridbag = new GridBagLayout();
		GridBagConstraints constraints = new GridBagConstraints();
		panelDerecho.setLayout(gridbag);
		panelDerecho.setBackground(Color.decode("#b2bba4"));
		panelDerecho.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
//-------------------------------------------------------------------------------------------------------------------------------//		
		JLabel labelCamas = new JLabel("Camas");
		labelCamas.setForeground(Color.BLACK);
		labelCamas.setFont(new Font("Times New Roman", Font.BOLD, 30));
		
		constraints.gridx = 0;
		constraints.gridy = 0;
        constraints.ipady = 0;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        //constraints.ipadx = 150;
        constraints.insets = new Insets(34, 60, 0, 0);

        panelDerecho.add(labelCamas, constraints);
		
		//Creacion de la tabla camas
			String[] columnasCamas = {"Capacidad", "Exclusiva para niños"}; //Nombre de las columnas
	        modeloTablaCamas = new DefaultTableModel(columnasCamas, 0);        
		    
		    //Diseño de la tabla
	        tablaCamas = new JTable(modeloTablaCamas);
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
	        constraints.ipady = 0;
	        constraints.ipadx = 10;
	        constraints.gridwidth = 1;
	        constraints.gridheight = 1;
	        constraints.insets = new Insets(20, 60, 30, 0);

	        panelDerecho.add(scrollPanel, constraints);
	        
	        botonCamas = new JButton("Agregar nueva cama");
	        botonCamas.addActionListener(this);
			botonCamas.setForeground(Color.BLACK);
			botonCamas.setFont(new Font("Times New Roman", Font.PLAIN, 30));
			
			constraints.gridx = 0;
			constraints.gridy = 2;
	        constraints.ipady = 0;
	        constraints.gridwidth = 1;
	        constraints.gridheight = 1;
	        constraints.insets = new Insets(0, 60, 10, 0);
	        //constraints.ipadx = 150;
	        //constraints.insets = new Insets(0, 0, 0, 0);

	        panelDerecho.add(botonCamas, constraints);
//-------------------------------------------------------------------------------------------------------------------------------//
	        JLabel labelServicios = new JLabel("Servicios");
			labelServicios.setForeground(Color.BLACK);
			labelServicios.setFont(new Font("Times New Roman", Font.BOLD, 30));
			
			constraints.gridx = 1;
			constraints.gridy = 0;
	        constraints.ipady = 0;
	        constraints.gridwidth = 1;
	        constraints.gridheight = 1;
	        //constraints.ipadx = 150;
	        constraints.insets = new Insets(20, 20, -10, 0);

	        panelDerecho.add(labelServicios, constraints);
			
			//Creacion de la tabla camas
				String[] columnasServicios = {"Nombre", "Precio"}; //Nombre de las columnas
		        modeloTablaServicios = new DefaultTableModel(columnasServicios, 0);        
			    
			    //Diseño de la tabla
		        tablaServicios = new JTable(modeloTablaServicios);
		        tablaServicios.setDefaultEditor(Object.class, null);
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
		        constraints.ipady = 0;
		        constraints.ipadx = 10;
		        constraints.gridwidth = 1;
		        constraints.gridheight = 1;
		        constraints.insets = new Insets(-10, 0, 0, 0);

		        panelDerecho.add(scrollPanelServicios, constraints);
		        
		        botonServicios = new JButton("Agregar nuevo servicio");
		        botonServicios.addActionListener(this);
				botonServicios.setForeground(Color.BLACK);
				botonServicios.setFont(new Font("Times New Roman", Font.PLAIN, 30));
				
				constraints.gridx = 1;
				constraints.gridy = 2;
		        constraints.ipady = 0;
		        constraints.gridwidth = 1;
		        constraints.gridheight = 1;
		        //constraints.ipadx = 150;
		        constraints.insets = new Insets(0, 0, 15, 0);

		        panelDerecho.add(botonServicios, constraints);
//-------------------------------------------------------------------------------------------------------------------------------//		
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
		
		borrarHabitacion = new JButton("Descartar habitación");
		borrarHabitacion.setPreferredSize(new Dimension(200,50));
		borrarHabitacion.setFont(fontButton);
		borrarHabitacion.setBackground(Color.decode("#D0ECF2"));
		borrarHabitacion.addActionListener(this);

		
		check.add(crearHabitacion);
		check.add(borrarHabitacion);
		
		constraints.gridx = 0;
		constraints.gridy = 3;
        constraints.ipady = 1;
        constraints.ipadx = 1084;
        constraints.gridwidth = 2;
        constraints.gridheight = 1;
        constraints.insets = new Insets(30, 0, 0, 0);
        constraints.anchor = GridBagConstraints.SOUTH;		
		panelDerecho.add(check, constraints);
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
		
        SpinnerModel model = new SpinnerNumberModel(1, 1, 20, 1);

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
		
		String[] tipos = {"Estandar", "Suite", "Double Suite"};
		
		cajaTipoHabitacion =  new JComboBox<>(tipos);
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
        constraints.ipadx = 105;
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

		//Tipo de habitacion y su caja de texto
		JLabel caracteristicas = new JLabel("Caracteristicas:");
		caracteristicas.setForeground(Color.white);
		caracteristicas.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		
		cajaBalcon = new JCheckBox();
		cajaBalcon.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		JLabel balcon = new JLabel("Balcon");
		balcon.setForeground(Color.white);
		balcon.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
		cajaVista = new JCheckBox();
		cajaVista.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		JLabel vista = new JLabel("Vista");
		vista.setForeground(Color.white);
		vista.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
		cajaCocina = new JCheckBox();
		cajaCocina.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		JLabel cocina = new JLabel("Cocina integrada");
		cocina.setForeground(Color.white);
		cocina.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
		constraints.gridx = 0;
		constraints.gridy = 4;
		constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(10, 10, -5, 0);	
		panelCrear.add(caracteristicas, constraints);	
		
		constraints.gridx = 0;
		constraints.gridy = 6;
		constraints.insets = new Insets(-5, 40, 0, 0);
		panelCrear.add(cajaBalcon, constraints);
		constraints.gridx = 0;
		constraints.gridy = 5;
		constraints.insets = new Insets(0, 40, -5, 0);
		panelCrear.add(balcon, constraints);
		
		constraints.gridx = 0;
		constraints.gridy = 8;
		constraints.insets = new Insets(-5, 40, 0, 0);
		panelCrear.add(cajaVista, constraints);
		constraints.gridx = 0;
		constraints.gridy = 7;
		constraints.insets = new Insets(0, 40, -5, 0);
		panelCrear.add(vista, constraints);
		
		constraints.gridx = 0;
		constraints.gridy = 10;
		constraints.insets = new Insets(-5, 40, 0, 0);
		panelCrear.add(cajaCocina, constraints);
		constraints.gridx = 0;
		constraints.gridy = 9;
		constraints.insets = new Insets(0, 40, -5, 0);
		panelCrear.add(cocina, constraints);
		
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
		try {
		for (int i = 0; i < cantidadFilas; i++) {
			int capacidad = Integer.parseInt((String) tablaCamas.getValueAt(i, 0));
			total+=capacidad;
		}
		}
		catch (ParseException e) {
			for (int i = 0; i < cantidadFilas; i++) {
				int capacidad = Integer.parseInt((String) tablaCamas.getValueAt(i, 1));
				total+=capacidad;
			}
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
	    	String nom = nombre.getText();
	    	String prec = precio.getText();
	    	String[] fila = {nom, prec};
	    	modeloTablaServicios.addRow(fila);
	    }
	}
	public void crearHabitacion() {
		
	}
	
	@Override
	public void volverMenu() {
		windowManager.volverHabitaciones();
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
			JOptionPane.showMessageDialog(null, "Habitación descartada");
			setVisible(false);
			volverMenu();
			break;
		case "Crear habitación":
			crearHabitacion();
			break;
		case "Volver":
			setVisible(false);
			volverMenu();
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
