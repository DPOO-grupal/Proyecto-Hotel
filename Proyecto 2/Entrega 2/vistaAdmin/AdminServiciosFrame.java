package vistaAdmin;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Collection;
import java.util.Iterator;

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
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import controlador.WindowManager;
import modelo.Servicio;
import vistaEmpleado.EmpleadoServiciosFrame;

public class AdminServiciosFrame extends EmpleadoServiciosFrame implements ActionListener, MouseListener{
	
	private DefaultTableModel modeloTabla;
	private JTable tablaServicios;
	private JTextField cajaNombre;
	private JTextField cajaPrecio;
	private JTextField cajaNombreInfo;
	private JTextField cajaPrecioInfo;
	private JTextField cajaNumeroHabitacion;
	private JTextField cajaCantidadPersonas;
	private JButton añadirAHabitacion;
	private JTextField cajaCantidad;
	private JTable tablaOrden;
	private JButton agregarServicio;
	private JButton eliminarServicio;
	private JPanel panelEmergente;

	public AdminServiciosFrame(WindowManager windowManager) {
		super(windowManager);
		cargarDatos();
		//setTitle("Servicios");
		
	}
	
	@Override
	protected void setPanelCrear() {
		//Edita el aspecto del panel	
		panelCrear.setLayout(new GridLayout(7, 1, 10, 10));
		panelCrear.setBackground(Color.decode("#204473"));
		panelCrear.setBorder(BorderFactory.createEmptyBorder(20, 100, 20, 100));
		
		//Crea el panel para agregar un servicio
		JPanel panelNombre = new JPanel();
		panelNombre.setBackground(Color.decode("#204473"));	
		panelNombre.setLayout(new GridLayout(2, 1));
		
		//Nombre y su caja de texto
		
		JLabel nombre = new JLabel("Nombre");
		nombre.setForeground(Color.white);
		nombre.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		
		cajaNombre = new JTextField();
		cajaNombre.setFont(new Font("Times New Roman", Font.PLAIN, 30));

		
		panelNombre.add(nombre);
		panelNombre.add(cajaNombre);
		
		//Panel precio
		JPanel panelPrecio = new JPanel();
		panelPrecio.setBackground(Color.decode("#204473"));	
		panelPrecio.setLayout(new GridLayout(2, 1));
		
		//Precio y su caja de texto
		JLabel precio = new JLabel("Precio");
		precio.setForeground(Color.white);
		precio.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		
		cajaPrecio = new JTextField();
		cajaPrecio.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		
		panelPrecio.add(precio);
		panelPrecio.add(cajaPrecio);
		
		//Panel precio
		JPanel panelCantidad = new JPanel();
		panelCantidad.setBackground(Color.decode("#204473"));	
		panelCantidad.setLayout(new GridLayout(2, 1));
		
		//Precio y su caja de texto
		JLabel cantidad = new JLabel("Cantidad");
		cantidad.setForeground(Color.white);
		cantidad.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		
		cajaCantidad = new JTextField();
		cajaCantidad.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		
		panelCantidad.add(cantidad);
		panelCantidad.add(cajaCantidad);

		//Panel agregar
		JPanel panelAgregarOrden = new JPanel();
		panelAgregarOrden.setBackground(Color.decode("#204473"));
		
		//Boton para agregar un servicio
		JButton agregarALaOrden = new JButton("Agregar a la orden");
		agregarALaOrden.addActionListener(this);
		agregarALaOrden.setBackground(Color.decode("#accaf2"));
		agregarALaOrden.setFont(new Font("arial", 1, 24));
		
		panelAgregarOrden.add(agregarALaOrden);
		
		//Panel agregar
		JPanel panelEliminarOrden = new JPanel();
		panelEliminarOrden.setBackground(Color.decode("#204473"));
		
		//Boton para agregar un servicio
		JButton eliminarOrden = new JButton("Eliminar de orden");
		eliminarOrden.addActionListener(this);
		eliminarOrden.setBackground(Color.decode("#accaf2"));
		eliminarOrden.setFont(new Font("arial", 1, 24));
		
		panelEliminarOrden.add(eliminarOrden);
		
		//Se añaden los componentes al panel
		panelCrear.add(new JLabel());
		panelCrear.add(panelNombre);
		panelCrear.add(panelPrecio);
		panelCrear.add(panelCantidad);
		panelCrear.add(new JLabel());
		panelCrear.add(panelAgregarOrden);
		panelCrear.add(panelEliminarOrden);
				
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
        modeloTabla = new DefaultTableModel(columnasServicio, 0);
        
        //Filas de la tabla
        modeloTabla.addTableModelListener(tablaServicios);
  	    
  	    //Diseño de la tabla
        tablaServicios = new JTable(modeloTabla);
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


        tablaServicios.getColumnModel().getColumn(0).setCellRenderer(modelocentrarServicios);

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
	    modeloTabla = new DefaultTableModel(columnasOrden, 0);
	      
	    //Filas de la tabla
	    modeloTabla.addTableModelListener(tablaOrden);
		
		//Diseño de la tabla
	    tablaOrden = new JTable(modeloTabla);
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
	
	
	    tablaServicios.getColumnModel().getColumn(0).setCellRenderer(modelocentrarOrden);
	
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
	
	public void cargarDatos() {
		modeloTabla.getDataVector().removeAllElements();
		modeloTabla.fireTableDataChanged(); 
		Collection<Servicio> listaUsuarios = windowManager.darServicio().values();
		for (Servicio servicio : listaUsuarios) {
	        String nombre = servicio.getNombre();
	        modeloTabla.addRow(new Object[]{nombre, "ICON", "ICON"});
	    }
	}
	
	private void agregarServicio() {
		String nombre = cajaNombre.getText();
		Double precio = Double.parseDouble(cajaPrecio.getText());
		windowManager.agregarServicioHotel(nombre, precio);
		cargarDatos();
		cajaNombre.setText("");
		cajaPrecio.setText("");
	}
	
	private void añadirServicioHotelHabitacion() {
		int idServicio = getId(cajaNombreInfo.getText());
		int idHabitacion = Integer.parseInt(cajaNumeroHabitacion.getText());
		int cantidad = Integer.parseInt(cajaCantidadPersonas.getText());
		boolean pagarEnSitio = false;
		int option = JOptionPane.showConfirmDialog(null, "¿Desea pagar ahora?", "Pagar", JOptionPane.YES_NO_OPTION);
		cajaNombreInfo.setText("");
		cajaPrecioInfo.setText("");
		cajaNumeroHabitacion.setText("");
		cajaCantidadPersonas.setText("");
		if (option==JOptionPane.YES_OPTION) {
			pagarEnSitio=true;
		}
		windowManager.añadirServicioHotelHabitacion(idHabitacion, idServicio, cantidad, pagarEnSitio);
	}
	
	
	private String getPrecio(String nombre) {
		Collection<Servicio> listaServicios = windowManager.darServicio().values();
		String nombreServicio = null;
		for(Servicio servicio : listaServicios)
			if (servicio.getNombre().equals(nombre)) {
				nombreServicio = String.valueOf(servicio.getPrecio());
			}
		return nombreServicio;
		}
	
	private int getId(String nombre) {
		Collection<Servicio> listaServicios = windowManager.darServicio().values();
		int precioServicio = 0;
		for(Servicio servicio : listaServicios)
			if (servicio.getNombre().equals(nombre)) {
				precioServicio = servicio.getId();
			}
		return precioServicio;
		}

	public void actionPerformedFrame(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "Agregar a la orden":
			break;
			
		case "Eliminar de orden":
			break;
			
		case "Agregar servicio":
			agregarServicio();
			break;
			
		case "Eliminar servicio":
			break;
		
		case "Añadir a la habitación":
			añadirServicioHotelHabitacion();
			break;

		default:
			break;
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 1) {
			int row = tablaServicios.getSelectedRow();
			int column = tablaServicios.getSelectedColumn();
			String nombre = tablaServicios.getValueAt(row, column).toString();
			String precio = getPrecio(nombre);
			cajaNombreInfo.setText(nombre);
			cajaPrecioInfo.setText(precio);
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

