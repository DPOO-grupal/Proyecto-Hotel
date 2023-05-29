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

	protected DefaultTableModel modeloTabla;
	protected JTable tablaMenu;
	protected JTextField cajaNumeroHabitacion;
	protected JTextField cajaCantidadPersonas;
	protected JTextField cajaNombre;
	protected JTextField cajaPrecio;
	protected JCheckBox cajaLlevable;
	protected JTable tablaOrden;
	protected JButton añadirAHabitacion;
	protected TimePicker horaF;
	protected TimePicker horaI;

	public EmpleadoRestauranteFrame(WindowManager windowManager) {
		super(windowManager);
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
        modeloTabla = new DefaultTableModel(columnasMenu, 0);
        
        //Filas de la tabla
        modeloTabla.addTableModelListener(tablaMenu);
  	    
  	    //Diseño de la tabla
        tablaMenu = new JTable(modeloTabla);
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


        tablaMenu.getColumnModel().getColumn(0).setCellRenderer(modelocentrarServicios);

        JScrollPane scrollPanelServicios = new JScrollPane(tablaMenu);

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
		cajaLlevable.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		
		panelLlevable.add(llevable);
		panelLlevable.add(cajaLlevable);
		
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
		panelCrear.add(panelAgregarOrden);
		panelCrear.add(panelEliminarOrden);
						
	}
	
	protected void cargarDatos() {
		modeloTabla.getDataVector().removeAllElements();
		modeloTabla.fireTableDataChanged(); 
		Collection<ProductoMenu> listaProductosMenu = windowManager.getMenu().values();
		for (ProductoMenu productoMenu : listaProductosMenu) {
			String nombre = productoMenu.getNombre();
	        modeloTabla.addRow(new Object[]{nombre, "ICON", "ICON"});
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
	
	protected Date getHoraI(String nombre) {
		Collection<ProductoMenu> menu = windowManager.getMenu().values();
		Date precioServicio = null;
		for(ProductoMenu servicio : menu)
			if (servicio.getNombre().equals(nombre)) {
				precioServicio = servicio.getHorarioI();
			}
		return precioServicio;
		}
	
	protected Date getHoraF(String nombre) {
		Collection<ProductoMenu> menu = windowManager.getMenu().values();
		Date precioServicio = null;
		for(ProductoMenu servicio : menu)
			if (servicio.getNombre().equals(nombre)) {
				precioServicio = servicio.getHorarioF();
			}
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
	
	protected void añadirServicioHotelHabitacion() {
		}
	
	protected String formatoHora(Date hora) {
		SimpleDateFormat sdf = new SimpleDateFormat("h:mm a");
	    String fechaString = sdf.format(hora);
		return fechaString;
	}

	@Override
	public void actionPerformedFrame(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "Cargar a la habitación":
			añadirServicioHotelHabitacion();
			break;

		default:
			break;
		}
	}
	
	public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 1) {
			int row = tablaMenu.getSelectedRow();
			int column = tablaMenu.getSelectedColumn();
			String nombre = tablaMenu.getValueAt(row, column).toString();
			String precio = getPrecio(nombre);
			Date horaI = getHoraI(nombre);
			Date horaF = getHoraF(nombre);
			String llevable = "No";
			if (getLlevable(nombre)) {
				llevable="Si";
			}
			cajaNombre.setText(nombre);
			cajaPrecio.setText(precio);
			horaI.setTime(column);
			horaF.setTime(column);
			cajaLlevable.setText(llevable);
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

