package vistaAdmin;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import controlador.WindowManager;
import vistaEmpleado.EmpleadoServiciosFrame;

public class AdminServiciosFrame extends EmpleadoServiciosFrame implements ActionListener, MouseListener{
	
	private JButton agregarServicio;
	private JButton eliminarServicio;
	private JButton agregar;
	private JTextField cajaNombreAgregar;
	private JTextField cajaPrecioAgregar;
	private JFrame frameAgregar;

	public AdminServiciosFrame(WindowManager windowManager) {
		super(windowManager);
		
	}
	
	@Override
	protected void setPanelInfo() {
		//Edita el aspecto del panel	
		GridBagLayout gridbag = new GridBagLayout();
        GridBagConstraints constraints = new GridBagConstraints();
        panelDerecho.setLayout(gridbag);
		panelDerecho.setBackground(Color.decode("#b2bba4"));
		panelDerecho.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		
		//Tamaño y ubicacion en el panel
		JLabel titulo = new JLabel("Servicios");
		titulo.setFont(new Font("Times New Roman", Font.PLAIN, 40));
		
		constraints.ipady = 50;
        constraints.gridheight = 1;
        constraints.gridwidth = 2;
		panelDerecho.add(titulo,constraints);
		
		//Creacion de la tabla servicios
		String[] columnasServicio = {"Nombre", "Precio"}; //Nombre de las columnas
        modeloTablaServicios = new DefaultTableModel(columnasServicio, 0);
        
        //Filas de la tabla
        modeloTablaServicios.addTableModelListener(tablaServicios);
  	    
  	    //Diseño de la tabla
        tablaServicios = new JTable(modeloTablaServicios);
        tablaServicios.addMouseListener(this);
	    
	    //Diseño de la tabla
        tablaServicios.setDefaultEditor(Object.class, null);
        tablaServicios.getTableHeader().setBackground(Color.decode("#204473"));
        tablaServicios.getTableHeader().setForeground(Color.white);
        tablaServicios.getTableHeader().setFont(new Font("Times New Roman", 1, 30));
        tablaServicios.setFont(new Font("Times New Roman", 1, 20));
        tablaServicios.setRowHeight(70);
        tablaServicios.setEnabled(true);

        DefaultTableCellRenderer modelocentrarServicios = new DefaultTableCellRenderer();
        modelocentrarServicios.setHorizontalAlignment(SwingConstants.CENTER);

        for (int i = 0; i < columnasServicio.length; i++) {
        	tablaServicios.getColumnModel().getColumn(i).setCellRenderer(modelocentrarServicios);	
		}
        
        JScrollPane scrollPanelServicios = new JScrollPane(tablaServicios);

        //Tamaño y ubicacion de la tabla en el panel
        constraints.gridy = 2;
        constraints.ipady = 200;
        constraints.ipadx = 830;
        constraints.gridheight = 1;
        constraints.gridwidth = 2;
        constraints.weightx = 1;

        panelDerecho.add(scrollPanelServicios, constraints);
        
        //Creacion de la tabla servicios
		String[] columnasOrden = {"Orden"}; //Nombre de las columnas
	    modeloTablaOrden = new DefaultTableModel(columnasOrden, 0);
	      
	    //Filas de la tabla
	    modeloTablaOrden.addTableModelListener(tablaOrden);
		
		//Diseño de la tabla
	    tablaOrden = new JTable(modeloTablaOrden);
	    tablaOrden.addMouseListener(this);
	    
	    //Diseño de la tabla
	    tablaOrden.setDefaultEditor(Object.class, null);
	    tablaOrden.getTableHeader().setBackground(Color.decode("#204473"));
	    tablaOrden.getTableHeader().setForeground(Color.white);
	    tablaOrden.getTableHeader().setFont(new Font("Times New Roman", 1, 30));
	    tablaOrden.setFont(new Font("Times New Roman", 1, 20));
	    tablaOrden.setRowHeight(70);
	    tablaOrden.setEnabled(true);
	
	    DefaultTableCellRenderer modelocentrarOrden = new DefaultTableCellRenderer();
	    modelocentrarOrden.setHorizontalAlignment(SwingConstants.CENTER);
	
	
	    tablaOrden.getColumnModel().getColumn(0).setCellRenderer(modelocentrarOrden);
	
	    JScrollPane scrollPanelOrden = new JScrollPane(tablaOrden);
	
	    //Tamaño y ubicacion de la tabla en el panel
	    constraints.gridy = 3;
	    constraints.ipady = 150;
	    constraints.ipadx = 400;
	    constraints.gridheight = 1;
	    constraints.gridwidth = 1;
	    constraints.weightx = 1;
	
	    panelDerecho.add(scrollPanelOrden, constraints);
		
		//Creacion del recuadro para añadir servicio a la habitacion
	    JPanel habitacion = new JPanel(new GridLayout(3,1, 0, 5));
		habitacion.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		habitacion.setBackground(Color.decode("#accaf2"));
		
		//Numero de habitacion y su caja de texto
		JLabel numHabitacion = new JLabel("Número de habitación");
		numHabitacion.setForeground(Color.BLACK);
		numHabitacion.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		
		cajaNumeroHabitacion = new JTextField();
		
		//Boton para añadir un servicio
		añadirAHabitacion = new JButton("Añadir a la habitación");
		añadirAHabitacion.addActionListener(this);
		añadirAHabitacion.setBackground(Color.decode("#204473"));
		añadirAHabitacion.setForeground(Color.white);
		añadirAHabitacion.setFont(new Font("arial", 1, 20));
		
		habitacion.add(numHabitacion);
		habitacion.add(cajaNumeroHabitacion);
		habitacion.add(cajaNumeroHabitacion);
		habitacion.add(añadirAHabitacion);
		
		//Tamaño y ubicacion en el panel
		constraints.gridx = 1;
		constraints.gridy = 3;
		constraints.ipady = 50;
        constraints.ipadx = 100;
        constraints.gridheight = 1;
        constraints.gridwidth = 1;
        constraints.weightx = 1;
        constraints.weighty = 1;
		
		panelDerecho.add(habitacion, constraints);
		
		//Creación boton agregar servicio
		agregarServicio = new JButton("Agregar servicio");
		agregarServicio.addActionListener(this);
		agregarServicio.setBackground(Color.decode("#accaf2"));
		agregarServicio.setFont(new Font("arial", 1, 25));
		
		//Tamaño y ubicacion en el panel
		constraints.gridy = 4;
		constraints.gridx = 0;
        constraints.gridheight = 1;
        constraints.gridwidth = 1;
        constraints.weightx = 1;
        constraints.weighty = 1;
		
		panelDerecho.add(agregarServicio, constraints);
		
		//Boton para añadir un servicio
		eliminarServicio = new JButton("Eliminar servicio");
		eliminarServicio.addActionListener(this);
		eliminarServicio.setBackground(Color.decode("#accaf2"));
		eliminarServicio.setFont(new Font("arial", 1, 25));
		
		//Tamaño y ubicacion en el panel
		constraints.gridy = 4;
		constraints.gridx = 1;
        constraints.gridheight = 1;
        constraints.gridwidth = 1;
        constraints.weightx = 1;
        constraints.weighty = 1;
		
		panelDerecho.add(eliminarServicio, constraints);
	}
	
	private void agregarServicio() {
		frameAgregar = new JFrame();
		frameAgregar.setSize(300, 300);
		frameAgregar.setLocationRelativeTo(null);
		
		frameAgregar.setLayout(new GridLayout(3, 1));
		frameAgregar.setBackground(Color.decode("#ccd2c2"));
		
		//Crea el panel para agregar un servicio
		JPanel panelNombre = new JPanel();
		panelNombre.setBackground(Color.decode("#ccd2c2"));	
		panelNombre.setLayout(new GridLayout(2, 1));
		panelNombre.setBorder(BorderFactory.createEmptyBorder(20, 40, 0, 40));
		
		//Nombre y su caja de texto
		
		JLabel nombre = new JLabel("Nombre");
		nombre.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		
		cajaNombreAgregar = new JTextField();
		cajaNombreAgregar.setFont(new Font("Times New Roman", Font.PLAIN, 30));

		
		panelNombre.add(nombre);
		panelNombre.add(cajaNombreAgregar);
		
		//Panel precio
		JPanel panelPrecio = new JPanel();
		panelPrecio.setBackground(Color.decode("#ccd2c2"));	
		panelPrecio.setLayout(new GridLayout(2, 1));
		panelPrecio.setBorder(BorderFactory.createEmptyBorder(20, 40, 0, 40));
		
		//Precio y su caja de texto
		JLabel precio = new JLabel("Precio");
		precio.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		
		cajaPrecioAgregar = new JTextField();
		cajaPrecioAgregar.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		
		panelPrecio.add(precio);
		panelPrecio.add(cajaPrecioAgregar);
		
		//Panel boton agregar
		JPanel panelAgregar = new JPanel();
		panelAgregar.setBackground(Color.decode("#ccd2c2"));
		panelAgregar.setBorder(BorderFactory.createEmptyBorder(20, 40, 0, 40));
		
		//Creación boton agregar servicio
		agregar = new JButton("Agregar");
		agregar.addActionListener(this);
		agregar.setBackground(Color.decode("#204473"));
		agregar.setForeground(Color.WHITE);
		agregar.setFont(new Font("arial", 1, 25));
		
		panelAgregar.add(agregar);
		
		frameAgregar.add(panelNombre);
		frameAgregar.add(panelPrecio);
		frameAgregar.add(panelAgregar);
		
		frameAgregar.setVisible(true);
	}

	private void agregar() {
		String nombre = cajaNombreAgregar.getText();
		Double precio = Double.parseDouble(cajaPrecioAgregar.getText());
		windowManager.agregarServicioHotel(nombre, precio);
		cargarDatos();
		cajaNombreAgregar.setText("");
		cajaPrecioAgregar.setText("");
		frameAgregar.setVisible(false);
	}
	
	private void eliminarServicio() {
		String nombre = cajaNombre.getText();
		int id = getId(nombre);
		windowManager.eliminarServicioHotel(id);
		cargarDatos();
		cajaNombre.setText("");
		cajaPrecio.setText("");
	}
	
	public void actionPerformedFrame(ActionEvent e) {
		super.actionPerformedFrame(e);
		switch (e.getActionCommand()) {				
		case "Agregar servicio":
			agregarServicio();
			break;
			
		case "Agregar":
			agregar();
			break;
			
		case "Eliminar servicio":
			eliminarServicio();
			break;

		default:
			break;
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

