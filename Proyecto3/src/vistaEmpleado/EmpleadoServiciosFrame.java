package vistaEmpleado;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

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
import vistaAdmin.FrameBaseInfo;

public class EmpleadoServiciosFrame extends FrameBaseInfo implements MouseListener{
	
	protected DefaultTableModel modeloTablaServicios;
	protected DefaultTableModel modeloTablaOrden;
	protected JTable tablaServicios;
	protected JTextField cajaNumeroHabitacion;
	protected JButton añadirAHabitacion;
	protected JTextField cajaNombre;
	protected JTextField cajaPrecio;
	protected JTextField cajaCantidad;
	protected JTable tablaOrden;
	protected HashMap<String, Integer> listaOrden;
	
	public EmpleadoServiciosFrame(WindowManager windowManager) {
		super(windowManager);
		listaOrden = new HashMap<>();
		cargarDatos();
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
		cajaNombre.setEditable(false);
		
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
		cajaPrecio.setEditable(false);
		
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
        constraints.ipady = 300;
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
	    constraints.ipady = 250;
	    constraints.ipadx = 400;
	    constraints.gridheight = 1;
	    constraints.gridwidth = 1;
	    constraints.weightx = 1;
	    constraints.weighty = 1;
	
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
		habitacion.add(añadirAHabitacion);
		
		//Tamaño y ubicacion en el panel
		constraints.gridx = 1;
		constraints.gridy = 3;
		constraints.ipady = 50;
        constraints.ipadx = 100;
        constraints.gridheight = 1;
        constraints.gridwidth = 1;
		
		panelDerecho.add(habitacion, constraints);
	}
	
	protected void cargarDatos() {
		modeloTablaServicios.getDataVector().removeAllElements();
		modeloTablaServicios.fireTableDataChanged(); 
		Collection<Servicio> listaServicios = windowManager.darServicio().values();
		for (Servicio servicio : listaServicios) {
	        String nombre = servicio.getNombre();
	        Double precio = servicio.getPrecio();
	        modeloTablaServicios.addRow(new Object[]{nombre, precio, "ICON"});
	    }
		
		modeloTablaOrden.getDataVector().removeAllElements();
		modeloTablaOrden.fireTableDataChanged();
		for (String nombreServicio : listaOrden.keySet()) {
			modeloTablaOrden.addRow(new Object[]{nombreServicio, "ICON", "ICON"});
			}
	}
	
	protected String getPrecio(String nombre) {
		Collection<Servicio> listaServicios = windowManager.darServicio().values();
		String nombreServicio = null;
		for(Servicio servicio : listaServicios)
			if (servicio.getNombre().equals(nombre)) {
				nombreServicio = String.valueOf(servicio.getPrecio());
			}
		return nombreServicio;
		}

	protected int getId(String nombre) {
		Collection<Servicio> listaServicios = windowManager.darServicio().values();
		int precioServicio = 0;
		for(Servicio servicio : listaServicios)
			if (servicio.getNombre().equals(nombre)) {
				precioServicio = servicio.getId();
			}
		return precioServicio;
		}
	
	protected void añadirServicioHotelHabitacion() {
		try {
			boolean pagarEnSitio = false;
			int option = JOptionPane.showConfirmDialog(null, "¿Desea pagar ahora?", "Pagar", JOptionPane.YES_NO_OPTION);
			if (option==JOptionPane.YES_OPTION) {
				pagarEnSitio=true;
			}
			
			int idHabitacion = Integer.parseInt(cajaNumeroHabitacion.getText());
			Set<String> nombres = listaOrden.keySet();
			for (String nombre : nombres) {
				int idServicio = getId(nombre);
				int cantidad = listaOrden.get(nombre);
				windowManager.añadirServicioHotelHabitacion(idHabitacion, idServicio, cantidad, pagarEnSitio);	
				listaOrden.remove(nombre);
			}	
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "No existe la habitación");
		}
		cajaNombre.setText("");
		cajaPrecio.setText("");
		cajaCantidad.setText("");
		cajaNumeroHabitacion.setText("");
	}
	
	protected void agregarALaOrden() {
		try {
			String nombre = cajaNombre.getText();
			int cantidad = Integer.parseInt(cajaCantidad.getText());
			listaOrden.put(nombre, cantidad);
			cargarDatos();
			cajaNombre.setText("");
			cajaPrecio.setText("");
			cajaCantidad.setText("");	
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Dedes selecionar un servicio y decir la cantidad");
		}
	}
	
	protected void eliminarDeOrden() {
		String nombre = cajaNombre.getText();
		listaOrden.remove(nombre);
		cargarDatos();
		cajaNombre.setText("");
		cajaPrecio.setText("");
		cajaCantidad.setText("");
	}
	
	protected void actionPerformedFrame(ActionEvent e) {
		switch (e.getActionCommand()) {		
		case "Agregar a la orden":
			agregarALaOrden();
			break;
			
		case "Eliminar de orden":
			eliminarDeOrden();
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
			if (e.getSource()==tablaServicios) {
				int rowSer = tablaServicios.getSelectedRow();
				int columnSer = tablaServicios.getSelectedColumn();
				String nombre = tablaServicios.getValueAt(rowSer, columnSer).toString();
				String precio = getPrecio(nombre);
				cajaNombre.setText(nombre);
				cajaPrecio.setText(precio);
			}
			
			if (e.getSource()==tablaOrden) {
				int rowOrd = tablaOrden.getSelectedRow();
				int columnOrd = tablaOrden.getSelectedColumn();
				String nombreOrden = tablaOrden.getValueAt(rowOrd, columnOrd).toString();
				String precioOrden = getPrecio(nombreOrden);
				String cantidad = listaOrden.get(nombreOrden).toString();
				cajaNombre.setText(nombreOrden);
				cajaPrecio.setText(precioOrden);	
				cajaCantidad.setText(cantidad);
			}
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

	@Override
	public void resetDatos() {
		// TODO Auto-generated method stub
		
	}
}

