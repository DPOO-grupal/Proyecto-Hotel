package vistaAdmin;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.lang.StackWalker.Option;
import java.util.Collection;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalField;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
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
import javax.swing.text.NumberFormatter;

import com.github.lgooddatepicker.components.TimePicker;
import com.github.lgooddatepicker.components.TimePickerSettings;
import com.github.lgooddatepicker.components.TimePickerSettings.TimeIncrement;

import controlador.WindowManager;
import modelo.ProductoMenu;
import modelo.Servicio;
import vistaEmpleado.EmpleadoRestauranteFrame;

public class AdminRestauranteFrame extends EmpleadoRestauranteFrame implements MouseListener,ActionListener{

	private JButton agregarAlMenu;
	private JButton eliminarDelMenu;
	private JButton agregar;
	private JTextField cajaNombreAgregar;
	private JTextField cajaPrecioAgregar;
	private JFrame frameAgregar;
	private TimePicker horaIAgregar;
	private TimePicker horaFAgregar;
	private JCheckBox cajaLlevableAgregar;

	public AdminRestauranteFrame(WindowManager windowManager) {
		super(windowManager);
	}

	@Override
	protected void setPanelInfo() {
		super.setPanelInfo();
        GridBagConstraints constraints = new GridBagConstraints();
		//Creación boton agregar servicio
		agregarAlMenu = new JButton("Agregar al Menú");
		agregarAlMenu.addActionListener(this);
		agregarAlMenu.setBackground(Color.decode("#accaf2"));
		agregarAlMenu.setFont(new Font("arial", 1, 25));
		agregarAlMenu.setPreferredSize(new Dimension(200,40));
		
		//Tamaño y ubicacion en el panel
		constraints.gridy = 4;
		constraints.gridx = 0;
        constraints.gridheight = 1;
        constraints.gridwidth = 1;
        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.ipady = 20 ;
        constraints.insets = new Insets(10, 0, 10, 0);
		
		panelDerecho.add(agregarAlMenu, constraints);
		
		//Boton para añadir un servicio
		eliminarDelMenu = new JButton("Quitar del Menú");
		eliminarDelMenu.addActionListener(this);
		eliminarDelMenu.setBackground(Color.decode("#accaf2"));
		eliminarDelMenu.setFont(new Font("arial", 1, 25));
		eliminarDelMenu.setPreferredSize(new Dimension(200,40));
		
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
		frameAgregar = new JFrame();
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
		cajaPrecioAgregar.addKeyListener(this);
		cajaPrecioAgregar.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		
		panelPrecio.add(precio);
		panelPrecio.add(cajaPrecioAgregar);
		
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
		horaIAgregar = new TimePicker(timeSettings);
		timeSettings.initialTime = LocalTime.of(23, 0);
		horaFAgregar = new TimePicker(timeSettings);
		
		panelHorario.add(horario);
		panelHorario.add(new JLabel());
		panelHorario.add(horaIAgregar);
		panelHorario.add(horaFAgregar);
		
		//Panel precio
		JPanel panelLlevable = new JPanel();
		panelLlevable.setBackground(Color.decode("#ccd2c2"));	
		panelLlevable.setLayout(new GridLayout(2, 1));
		panelLlevable.setBorder(BorderFactory.createEmptyBorder(20, 40, 0, 40));

		
		//Precio y su caja de texto
		JLabel llevable = new JLabel("Llevable");
		
		cajaLlevableAgregar = new JCheckBox();
		cajaLlevableAgregar.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		
		panelLlevable.add(llevable);
		panelLlevable.add(cajaLlevableAgregar);
		
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
	
	
	private void añadirProductoMenu() {
		try {
			String nombre = cajaNombreAgregar.getText();
			String preci = cajaPrecioAgregar.getText().replace(",", "");
			Double precio = Double.parseDouble(preci.replace(".", ""));
			LocalTime horaInicial = horaIAgregar.getTime();
			LocalTime horaFinal = horaFAgregar.getTime();
			boolean llevable = cajaLlevableAgregar.isSelected();
			if (!(verificarExistencia(nombre))) {
				windowManager.crearProductoMenu(horaInicial, horaFinal, llevable, nombre, precio);
				cargarDatos();
				cajaNombreAgregar.setText("");
				cajaPrecioAgregar.setText("");
				TimePickerSettings timeSettings = new TimePickerSettings();
				timeSettings.initialTime = LocalTime.of(5, 0);
				timeSettings.generatePotentialMenuTimes(TimeIncrement.FifteenMinutes, null, null);
				horaIAgregar = new TimePicker(timeSettings);
				timeSettings.initialTime = LocalTime.of(23, 0);
				horaFAgregar = new TimePicker(timeSettings);
				cajaLlevableAgregar.setSelected(false);	
				frameAgregar.setVisible(false);		
			} else {
				int option = JOptionPane.showConfirmDialog(null, "Ya existe el producto menu.\n Desea cambiar sus valores?", null, JOptionPane.YES_NO_OPTION);
				if (option == 0) {
					editarProductoMenu(nombre);
					windowManager.crearProductoMenu(horaInicial, horaFinal, llevable, nombre, precio);
					cargarDatos();
					cajaNombreAgregar.setText("");
					cajaPrecioAgregar.setText("");
					TimePickerSettings timeSettings = new TimePickerSettings();
					timeSettings.initialTime = LocalTime.of(5, 0);
					timeSettings.generatePotentialMenuTimes(TimeIncrement.FifteenMinutes, null, null);
					horaIAgregar = new TimePicker(timeSettings);
					timeSettings.initialTime = LocalTime.of(23, 0);
					horaFAgregar = new TimePicker(timeSettings);
					cajaLlevableAgregar.setSelected(false);	
					frameAgregar.setVisible(false);
					}
			}
		} catch (Exception e) {
			System.err.println(e); 
			JOptionPane.showMessageDialog(null, "Debes llenar todos los espacios");
		}
		
	}
	
	private void editarProductoMenu(String nombre) {
		Collection<ProductoMenu> listaProductoMenu = windowManager.getMenu().values();
		for(ProductoMenu productoMenu : listaProductoMenu)
			if (productoMenu.getNombre().toLowerCase().equals(nombre.toLowerCase())) {
				windowManager.eliminarProductoMenu(productoMenu);
			}
	}
	
	private void eliminarProductoMenu() {
		String nombre = cajaNombre.getText();
		ProductoMenu productoMenu = getProductoMenu(nombre);
		windowManager.eliminarProductoMenu(productoMenu);
		eliminarDeOrden();
		cargarDatos();
		cajaNombre.setText("");
		cajaPrecio.setText("");
		TimePickerSettings timeSettings = new TimePickerSettings();
		timeSettings.initialTime = LocalTime.of(5, 0);
		timeSettings.generatePotentialMenuTimes(TimeIncrement.FifteenMinutes, null, null);
		horaI = new TimePicker(timeSettings);
		timeSettings.initialTime = LocalTime.of(23, 0);
		horaF = new TimePicker(timeSettings);
		cajaLlevable.setSelected(false);
		resetDatos();
	}
	
	
	
	private ProductoMenu getProductoMenu(String nombre) {
		Collection<ProductoMenu> listaProductoMenu= windowManager.getMenu().values();
		ProductoMenu productoMenu = null;
		for(ProductoMenu servicio : listaProductoMenu) {
			if (servicio.getNombre().equals(nombre)) {
				productoMenu = servicio;
			}
		}
		return productoMenu;
	}
	
	private boolean verificarExistencia(String nombre) {
		Collection<ProductoMenu> listaServicios = windowManager.getMenu().values();
		boolean existe = false;
		for(Servicio servicio : listaServicios)
			if (servicio.getNombre().toLowerCase().equals(nombre.toLowerCase())) {
				existe = true;
			}
		return existe;
		
	}
	
	public String formatoHora(Date hora) {
		SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
	    String fechaString = sdf.format(hora);
		return fechaString;
	}
	
	public void actionPerformedFrame(ActionEvent e) {
		super.actionPerformedFrame(e);
		switch (e.getActionCommand()){
		case "Agregar al Menú":
			agregarAlMenu();
			break;
			
		case "Agregar":
			añadirProductoMenu();
			break;
			
		case "Quitar del Menú":
			eliminarProductoMenu();
			break;

		default:
			break;
		}
	}
	
	public void mouseClicked(MouseEvent e) {
		super.mouseClicked(e);
	}
	
}
