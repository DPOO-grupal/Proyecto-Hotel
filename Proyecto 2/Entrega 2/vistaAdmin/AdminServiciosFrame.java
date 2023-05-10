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

	public AdminServiciosFrame(WindowManager windowManager) {
		super(windowManager);
		cargarDatos();
		//setTitle("Servicios");
		
	}
	
	@Override
	protected void setPanelCrear() {
		//Edita el aspecto del panel	
		panelCrear.setLayout(new GridLayout(5, 1, 10, 10));
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

		//Panel agregar
		JPanel panelAgregar = new JPanel();
		panelAgregar.setBackground(Color.decode("#204473"));
		
		//Boton para agregar un servicio
		JButton agregarServicio = new JButton("Agregar servicio");
		agregarServicio.addActionListener(this);
		agregarServicio.setBackground(Color.decode("#ACCAF2"));
		agregarServicio.setBackground(Color.CYAN);
		agregarServicio.setFont(new Font("arial", 1, 20));
		
		panelAgregar.add(agregarServicio);
		
		//Se añaden los componentes al panel
		panelCrear.add(new JLabel());
		panelCrear.add(panelNombre);
		panelCrear.add(panelPrecio);		
		panelCrear.add(panelAgregar);
		
	}

	@Override
	protected void setPanelInfo() {
		//Edita el aspecto del panel	
		GridBagLayout gridbag = new GridBagLayout();
        GridBagConstraints constraints = new GridBagConstraints();
        panelDerecho.setLayout(gridbag);
		panelDerecho.setBackground(Color.decode("#b2bba4"));
		panelDerecho.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		
		//Creacion de la tabla servicios
		String[] columnas = {"Servicios"}; //Nombre de las columnas
        modeloTabla = new DefaultTableModel(columnas, 0);
        
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

        DefaultTableCellRenderer modelocentrar = new DefaultTableCellRenderer();
        modelocentrar.setHorizontalAlignment(SwingConstants.CENTER);


        tablaServicios.getColumnModel().getColumn(0).setCellRenderer(modelocentrar);

        JScrollPane scrollPanel = new JScrollPane(tablaServicios);

        //Tamaño y ubicacion de la tabla en el panel
        constraints.gridx = 0;
        constraints.ipady = 650;
        constraints.ipadx = 400;
        constraints.gridheight = 2;
        constraints.gridwidth = 2;
        constraints.weightx = 1;

        panelDerecho.add(scrollPanel, constraints);
		
        //Creacion del recuadro para buscar un servicio
		JLabel nombre = new JLabel("Nombre");
		nombre.setForeground(Color.BLACK);
		nombre.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		
		cajaNombreInfo = new JTextField();
		
		JLabel precio = new JLabel("Precio");
		precio.setForeground(Color.BLACK);
		precio.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		
		cajaPrecioInfo = new JTextField();
		
		//Diseño 
		JPanel buscar = new JPanel(new GridLayout(4,1));
		buscar.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		buscar.setBackground(Color.decode("#accaf2"));
		
		buscar.add(nombre);
		buscar.add(cajaNombreInfo);
		buscar.add(precio);
		buscar.add(cajaPrecioInfo);
		
		//Tamaño y ubicacion en el panel
		constraints.gridx = 3;
		constraints.gridy = 0;
		constraints.ipady = 120;
        constraints.ipadx = 250;
        constraints.gridheight = 1;
        constraints.gridwidth = 1;
		
		panelDerecho.add(buscar, constraints);
		
		//Creacion del recuadro para añadir servicio a la habitacion
		JPanel habitacion = new JPanel(new GridLayout(5,1, 0, 5));
		habitacion.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		habitacion.setBackground(Color.decode("#accaf2"));
		
		//Numero de habitacion y su caja de texto
		JLabel numHabitacion = new JLabel("Número de habitacion");
		numHabitacion.setForeground(Color.BLACK);
		numHabitacion.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		
		cajaNumeroHabitacion = new JTextField();
		
		//Numero de habitacion y su caja de texto
		JLabel cantidadPersonas = new JLabel("#personas");
		cantidadPersonas.setForeground(Color.BLACK);
		cantidadPersonas.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		
		cajaCantidadPersonas = new JTextField();
		
		//Boton para añadir un servicio
		añadirAHabitacion = new JButton("Añadir a la habitación");
		añadirAHabitacion.addActionListener(this);
		añadirAHabitacion.setBackground(Color.decode("#204473"));
		añadirAHabitacion.setForeground(Color.white);
		añadirAHabitacion.setFont(new Font("arial", 1, 20));
		
		habitacion.add(numHabitacion);
		habitacion.add(cajaNumeroHabitacion);
		habitacion.add(cantidadPersonas);
		habitacion.add(cajaCantidadPersonas);
		habitacion.add(añadirAHabitacion);
		
		//Tamaño y ubicacion en el panel
		constraints.gridx = 3;
		constraints.gridy = 1;
		constraints.ipady = 50;
        constraints.ipadx = 100;
        constraints.gridheight = 1;
        constraints.gridwidth = 1;
		
		
		panelDerecho.add(habitacion, constraints);
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
		case "Agregar servicio":
			agregarServicio();
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

