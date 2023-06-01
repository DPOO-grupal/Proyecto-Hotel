package vistaEmpleado;

import java.awt.Color;
import java.awt.Font;
import com.github.lgooddatepicker.components.*;
import com.github.lgooddatepicker.components.TimePickerSettings.TimeIncrement;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
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
import modelo.ProductoMenu;
import modelo.Servicio;
import vistaAdmin.FrameBaseInfo;

public class EmpleadoRestauranteFrame extends FrameBaseInfo implements MouseListener{

	protected JTable tablaMenu;
	protected JTextField cajaNumeroHabitacion;
	protected JTextField cajaNombre;
	protected JTextField cajaPrecio;
	protected JCheckBox cajaLlevable;
	protected JTextField cajaCantidad;
	protected JTable tablaOrden;
	protected JButton añadirAHabitacion;
	protected TimePicker horaF;
	protected TimePicker horaI;
	protected DefaultTableModel modeloTablaMenu;
	protected DefaultTableModel modeloTablaOrden;
	protected HashMap<String, Integer> listaOrden;

	public EmpleadoRestauranteFrame(WindowManager windowManager) {
		super(windowManager);
		listaOrden = new HashMap<>();
		cargarDatos();
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
		JLabel titulo = new JLabel("Menú");
		titulo.setFont(new Font("Times New Roman", Font.PLAIN, 40));
		
		constraints.ipady = 50;
        constraints.gridheight = 1;
        constraints.gridwidth = 2;
		panelDerecho.add(titulo,constraints);
		
		//Creacion de la tabla servicios
		String[] columnasMenu = {"Nombre", "Precio"}; //Nombre de las columnas
        modeloTablaMenu = new DefaultTableModel(columnasMenu, 0);
        
        //Filas de la tabla
        modeloTablaMenu.addTableModelListener(tablaMenu);
  	    
  	    //Diseño de la tabla
        tablaMenu = new JTable(modeloTablaMenu);
        tablaMenu.addMouseListener(this);
	    
	    //Diseño de la tabla
        tablaMenu.setDefaultEditor(Object.class, null);
        tablaMenu.getTableHeader().setBackground(Color.decode("#204473"));
        tablaMenu.getTableHeader().setForeground(Color.white);
        tablaMenu.getTableHeader().setFont(new Font("Times New Roman", 1, 30));
        tablaMenu.setFont(new Font("Times New Roman", 1, 20));
        tablaMenu.setRowHeight(70);
        tablaMenu.setEnabled(true);

        DefaultTableCellRenderer modelocentrarServicios = new DefaultTableCellRenderer();
        modelocentrarServicios.setHorizontalAlignment(SwingConstants.CENTER);

        for (int i = 0; i < columnasMenu.length; i++) {
        	tablaMenu.getColumnModel().getColumn(i).setCellRenderer(modelocentrarServicios);	
		}
        
        JScrollPane scrollPanelMenu = new JScrollPane(tablaMenu);

        //Tamaño y ubicacion de la tabla en el panel
        constraints.gridy = 2;
        constraints.ipady = 300;
        constraints.ipadx = 830;
        constraints.gridheight = 1;
        constraints.gridwidth = 2;
        constraints.weightx = 1;

        panelDerecho.add(scrollPanelMenu, constraints);
        
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

	@Override
	protected void setPanelCrear() {
		//Edita el aspecto del panel	
		panelCrear.setLayout(new GridLayout(8, 1, 10, 10));
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
		JPanel panelHorario = new JPanel();
		panelHorario.setBackground(Color.decode("#204473"));	
		panelHorario.setLayout(new GridLayout(2, 2,15,0));
		
		
		//Precio y su caja de texto
		JLabel horario = new JLabel("Horario");
		horario.setForeground(Color.white);
		horario.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		
		TimePickerSettings timeSettings = new TimePickerSettings();timeSettings.initialTime = LocalTime.of(15, 30);     timeSettings.generatePotentialMenuTimes(TimeIncrement.FifteenMinutes, null, null);
		horaI = new TimePicker();
		horaI.setEnabled(false);
		
		horaF = new TimePicker();
		horaF.setEnabled(false);
		
		panelHorario.add(horario);
		panelHorario.add(new JLabel());
		panelHorario.add(horaI);
		panelHorario.add(horaF);
		
		//Panel precio
		JPanel panelLlevable = new JPanel();
		panelLlevable.setBackground(Color.decode("#204473"));	
		panelLlevable.setLayout(new GridLayout(2, 1));
		
		//Precio y su caja de texto
		JLabel llevable = new JLabel("Llevable");
		llevable.setForeground(Color.white);
		llevable.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		
		cajaLlevable = new JCheckBox();
		cajaLlevable.setEnabled(false);
		
		panelLlevable.add(llevable);
		panelLlevable.add(cajaLlevable);
		
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
		panelCrear.add(panelHorario);
		panelCrear.add(panelLlevable);
		panelCrear.add(panelCantidad);
		panelCrear.add(panelAgregarOrden);
		panelCrear.add(panelEliminarOrden);
						
	}
	
	protected void cargarDatos() {
		modeloTablaMenu.getDataVector().removeAllElements();
		modeloTablaMenu.fireTableDataChanged(); 
		Collection<ProductoMenu> listaProductosMenu = windowManager.getMenu().values();
		for (ProductoMenu productoMenu : listaProductosMenu) {
			String nombre = productoMenu.getNombre();
			String precio = productoMenu.getPrecio()+"";
			modeloTablaMenu.addRow(new Object[]{nombre, precio, "ICON"});
		}
		
		modeloTablaOrden.getDataVector().removeAllElements();
		modeloTablaOrden.fireTableDataChanged();
		for (String nombreProductoMenu : listaOrden.keySet()) {
			modeloTablaOrden.addRow(new Object[]{nombreProductoMenu, "ICON", "ICON"});
			}
	}
	
	protected String getPrecio(String nombre) {
		Collection<ProductoMenu> menu = windowManager.getMenu().values();
		String nombreServicio = null;
		for(Servicio servicio : menu)
			if (servicio.getNombre().equals(nombre)) {
				nombreServicio = String.valueOf(servicio.getPrecio());
			}
		return nombreServicio;
		}
	
	protected int getId(String nombre) {
		Collection<ProductoMenu> listaServicios = windowManager.getMenu().values();
		int precioServicio = 0;
		for(Servicio servicio : listaServicios)
			if (servicio.getNombre().equals(nombre)) {
				precioServicio = servicio.getId();
			}
		return precioServicio;
		}
	
	protected LocalTime getHoraI(String nombre) {
		Collection<ProductoMenu> menu = windowManager.getMenu().values();
		LocalTime precioServicio = null;
		for(ProductoMenu servicio : menu)
			if (servicio.getNombre().equals(nombre)) {
				precioServicio = servicio.getHorarioI();
				System.out.println("EmpleadoRestauranteFrame.getHoraI()");
			}
		System.out.println(precioServicio);
		return precioServicio;
		}
	
	protected LocalTime getHoraF(String nombre) {
		Collection<ProductoMenu> menu = windowManager.getMenu().values();
		LocalTime precioServicio = null;
		for(ProductoMenu servicio : menu)
			if (servicio.getNombre().equals(nombre)) {
				precioServicio = servicio.getHorarioF();
				System.out.println("EmpleadoRestauranteFrame.getHoraF()");
			}
		System.out.println(precioServicio);
		return precioServicio;
		}
	
	protected boolean getLlevable(String nombre) {
		Collection<ProductoMenu> menu = windowManager.getMenu().values();
		boolean precioServicio = false;
		for(ProductoMenu servicio : menu)
			if (servicio.getNombre().equals(nombre)) {
				precioServicio = servicio.getLlevable();
			}
		return precioServicio;
		}
	
	protected void añadirProductoMenuHabitacion() {
		try {
			if (cajaNumeroHabitacion.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "Hace falta el número de la habitación");
			}
			else {
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
					windowManager.añadirProductoMenuHabitacion(idHabitacion, idServicio, cantidad, pagarEnSitio);	
					listaOrden.remove(nombre);
				}
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "No existe la habitación");
		}
		cajaNombre.setText("");
		cajaPrecio.setText("");
		cajaCantidad.setText("");
		cajaNumeroHabitacion.setText("");
	}

	
	protected String formatoHora(Date hora) {
		SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
	    String fechaString = sdf.format(hora);
		return fechaString;
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
			JOptionPane.showMessageDialog(null, "Dedes selecionar un producto del menu y decir la cantidad");
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

	@Override
	public void actionPerformedFrame(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "Agregar a la orden":
			agregarALaOrden();
			break;
			
		case "Eliminar de orden":
			eliminarDeOrden();
			break;
			
		case "Añadir a la habitación":
			añadirProductoMenuHabitacion();
			break;

		default:
			break;
		}
	}
	

	public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 1) {
			if (e.getSource()==tablaMenu) {
				int rowSer = tablaMenu.getSelectedRow();
				String nombre = tablaMenu.getValueAt(rowSer, 0).toString();
				String precio = getPrecio(nombre);
				LocalTime horaInicial = getHoraI(nombre);
				LocalTime horaFinal =  getHoraF(nombre);
				boolean llevable = getLlevable(nombre);
				cajaNombre.setText(nombre);
				cajaPrecio.setText(precio);
				horaI.setTime(horaInicial);
				horaF.setTime(horaFinal);
				cajaLlevable.setSelected(llevable);
				cajaCantidad.setText("");
				cajaCantidad.setEditable(true);
			}
			
			if (e.getSource()==tablaOrden) {
				int rowOrd = tablaOrden.getSelectedRow();
				int columnOrd = tablaOrden.getSelectedColumn();
				String nombreOrden = tablaOrden.getValueAt(rowOrd, columnOrd).toString();
				String precioOrden = getPrecio(nombreOrden);
				LocalTime horaInicial = getHoraI(nombreOrden);
				LocalTime horaFinal = getHoraF(nombreOrden);
				boolean llevable = getLlevable(nombreOrden);
				String cantidad = listaOrden.get(nombreOrden).toString();
				cajaNombre.setText(nombreOrden);
				cajaPrecio.setText(precioOrden);
				horaI.setTime(horaInicial);
				horaF.setTime(horaFinal);
				cajaLlevable.setSelected(llevable);
				cajaCantidad.setText(cantidad);
				cajaCantidad.setEditable(false);
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

