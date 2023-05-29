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
import java.util.Collection;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.time.LocalTime;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
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

import com.github.lgooddatepicker.components.TimePicker;
import com.github.lgooddatepicker.components.TimePickerSettings;
import com.github.lgooddatepicker.components.TimePickerSettings.TimeIncrement;

import controlador.WindowManager;
import modelo.ProductoMenu;
import modelo.Servicio;
import vistaEmpleado.EmpleadoRestauranteFrame;

public class AdminRestauranteFrame extends EmpleadoRestauranteFrame implements MouseListener,ActionListener{

	private JButton agregarAlMenu;
	private AbstractButton eliminarDelMenu;
	private JButton agregar;

	public AdminRestauranteFrame(WindowManager windowManager) {
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
	
	
	    tablaOrden.getColumnModel().getColumn(0).setCellRenderer(modelocentrarOrden);
	
	    JScrollPane scrollPanelOrden = new JScrollPane(tablaOrden);
	
	    //Tamaño y ubicacion de la tabla en el panel
	    constraints.gridy = 3;
	    constraints.ipady = 150;
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
	
		//Creación boton agregar servicio
		agregarAlMenu = new JButton("Agregar al Menú");
		agregarAlMenu.addActionListener(this);
		agregarAlMenu.setBackground(Color.decode("#accaf2"));
		agregarAlMenu.setFont(new Font("arial", 1, 25));
		
		//Tamaño y ubicacion en el panel
		constraints.gridy = 4;
		constraints.gridx = 0;
        constraints.gridheight = 1;
        constraints.gridwidth = 1;
        constraints.weightx = 1;
        constraints.weighty = 1;
		
		panelDerecho.add(agregarAlMenu, constraints);
		
		//Boton para añadir un servicio
		eliminarDelMenu = new JButton("Quitar del Menú");
		eliminarDelMenu.addActionListener(this);
		eliminarDelMenu.setBackground(Color.decode("#accaf2"));
		eliminarDelMenu.setFont(new Font("arial", 1, 25));
		
		//Tamaño y ubicacion en el panel
		constraints.gridy = 4;
		constraints.gridx = 1;
        constraints.gridheight = 1;
        constraints.gridwidth = 1;
        constraints.weightx = 1;
        constraints.weighty = 1;
		
		panelDerecho.add(eliminarDelMenu, constraints);
	
	}
	
	private void agregarAlMenu() {
		JFrame frameAgregar = new JFrame();
		frameAgregar.setSize(300, 500);
		frameAgregar.setLocationRelativeTo(null);
		
		frameAgregar.setLayout(new GridLayout(5, 1));
		frameAgregar.setBackground(Color.decode("#ccd2c2"));
		
		//Crea el panel para agregar un servicio
		JPanel panelNombre = new JPanel();
		panelNombre.setBackground(Color.decode("#ccd2c2"));	
		panelNombre.setLayout(new GridLayout(2, 1));
		panelNombre.setBorder(BorderFactory.createEmptyBorder(20, 40, 0, 40));

		
		//Nombre y su caja de texto
		
		JLabel nombre = new JLabel("Nombre");
		nombre.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		
		cajaNombre = new JTextField();
		cajaNombre.setFont(new Font("Times New Roman", Font.PLAIN, 30));

		
		panelNombre.add(nombre);
		panelNombre.add(cajaNombre);
		
		//Panel precio
		JPanel panelPrecio = new JPanel();
		panelPrecio.setBackground(Color.decode("#ccd2c2"));	
		panelPrecio.setLayout(new GridLayout(2, 1));
		panelPrecio.setBorder(BorderFactory.createEmptyBorder(20, 40, 0, 40));

		
		//Precio y su caja de texto
		JLabel precio = new JLabel("Precio");
		precio.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		
		cajaPrecio = new JTextField();
		cajaPrecio.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		
		panelPrecio.add(precio);
		panelPrecio.add(cajaPrecio);
		
		//Panel precio
		JPanel panelHorario = new JPanel();
		panelHorario.setBackground(Color.decode("#ccd2c2"));	
		panelHorario.setLayout(new GridLayout(2, 2,15,0));
		panelHorario.setBorder(BorderFactory.createEmptyBorder(20, 40, 0, 40));

		
		
		//Precio y su caja de texto
		JLabel horario = new JLabel("Horario");
		horario.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		
		TimePickerSettings timeSettings = new TimePickerSettings();
		timeSettings.initialTime = LocalTime.of(5, 0);
		timeSettings.generatePotentialMenuTimes(TimeIncrement.FifteenMinutes, null, null);
		horaI = new TimePicker(timeSettings);
		timeSettings.initialTime = LocalTime.of(23, 0);
		horaF = new TimePicker(timeSettings);
		
		panelHorario.add(horario);
		panelHorario.add(new JLabel());
		panelHorario.add(horaI);
		panelHorario.add(horaF);
		
		//Panel precio
		JPanel panelLlevable = new JPanel();
		panelLlevable.setBackground(Color.decode("#ccd2c2"));	
		panelLlevable.setLayout(new GridLayout(2, 1));
		panelLlevable.setBorder(BorderFactory.createEmptyBorder(20, 40, 0, 40));

		
		//Precio y su caja de texto
		JLabel llevable = new JLabel("Llevable");
		llevable.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		
		cajaLlevable = new JCheckBox();
		cajaLlevable.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		
		panelLlevable.add(llevable);
		panelLlevable.add(cajaLlevable);
		
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
		frameAgregar.add(panelHorario);
		frameAgregar.add(panelLlevable);
		frameAgregar.add(panelAgregar);
		frameAgregar.setVisible(true);
	}
	
	public void cargarDatos() {
		modeloTabla.getDataVector().removeAllElements();
		modeloTabla.fireTableDataChanged(); 
		Collection<ProductoMenu> listaProductosMenu = windowManager.getMenu().values();
		for (ProductoMenu productoMenu : listaProductosMenu) {
			String nombre = productoMenu.getNombre();
	        modeloTabla.addRow(new Object[]{nombre, "ICON", "ICON"});
		}
	}
	
	private void añadirProductoMenu() {
	}
	
	public String formatoHora(Date hora) {
		SimpleDateFormat sdf = new SimpleDateFormat("h:mm a");
	    String fechaString = sdf.format(hora);
		return fechaString;
	}

	@Override
	public void actionPerformedFrame(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "Agregar al Menú":
			agregarAlMenu();
			break;
		case "Cargar a la habitación":
			añadirProductoMenu();
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
		 }
		
	}
}
