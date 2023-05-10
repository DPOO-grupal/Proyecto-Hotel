package vistaAdmin;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Collection;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import controlador.WindowManager;
import modelo.Habitacion;
import modelo.Servicio;
import modelo.TipoHabitacion;
import vistaEmpleado.EmpleadoHabitacionesFrame;

public class AdminHabitacionesFrame extends EmpleadoHabitacionesFrame{
	
	private DefaultTableModel modeloTabla;
	private JTable tablaHabitaciones;
	private JButton crearHabitacion;
	private JCheckBox cajaApto;
	private JTextField cajaPrecio;
	private JTextField cajaServicioHabitacion;
	private JTextField cajaCapacidad;
	private JComboBox<String> cajaTipoHabitacion;
	private JSpinner cajaPiso;
	
	
	public AdminHabitacionesFrame(WindowManager windowManager) {
		super(windowManager);
		cargarDatos();
		
		
	}
	
	@Override
	protected void setPanelCrear() {
		//Edita el aspecto del panel	
		panelCrear.setLayout(new GridLayout(7, 1, 10, 10));
		panelCrear.setBackground(Color.decode("#204473"));
		panelCrear.setBorder(BorderFactory.createEmptyBorder(20, 100, 20, 100));
				
		//Panel ID
		JPanel panelPiso = new JPanel();
		panelPiso.setBackground(Color.decode("#204473"));	
		panelPiso.setLayout(new GridLayout(2, 1));
		
		//Id y su caja de texto
		JLabel piso = new JLabel("Piso");
		piso.setForeground(Color.white);
		piso.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		
        SpinnerModel model = new SpinnerNumberModel(1, 1, 20, 1);

		cajaPiso = new JSpinner(model);
  		cajaPiso.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		
		panelPiso.add(piso);
		panelPiso.add(cajaPiso);
		
		//Panel tipo habitacion
		JPanel panelTipoHabitacion = new JPanel();
		panelTipoHabitacion.setBackground(Color.decode("#204473"));	
		panelTipoHabitacion.setLayout(new GridLayout(2, 1));

		//Tipo de habitacion y su caja de texto
		JLabel tipoHabitacion = new JLabel("Tipo habitación");
		tipoHabitacion.setForeground(Color.white);
		tipoHabitacion.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		
		String[] tipos = {"Estandar", "Suite", "Double Suite"};
		
		cajaTipoHabitacion =  new JComboBox<>(tipos);
		cajaTipoHabitacion.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		
		panelTipoHabitacion.add(tipoHabitacion);
		panelTipoHabitacion.add(cajaTipoHabitacion);
		
		//Panel tipo habitacion
		JPanel panelCapacidad = new JPanel();
		panelCapacidad.setBackground(Color.decode("#204473"));	
		panelCapacidad.setLayout(new GridLayout(2, 1));

		//Tipo de habitacion y su caja de texto
		JLabel capacidad = new JLabel("Capacidad");
		capacidad.setForeground(Color.white);
		capacidad.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		
		cajaCapacidad = new JTextField();
		cajaCapacidad.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		
		panelCapacidad.add(capacidad);
		panelCapacidad.add(cajaCapacidad);
		
		//Panel tipo habitacion
		JPanel panelApto = new JPanel();
		panelApto.setBackground(Color.decode("#204473"));	
		panelApto.setLayout(new GridLayout(2, 1));

		//Tipo de habitacion y su caja de texto
		JLabel apto = new JLabel("Apto para niños");
		apto.setForeground(Color.white);
		apto.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		
		cajaApto = new JCheckBox();
		cajaApto.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		
		panelApto.add(apto);
		panelApto.add(cajaApto);

		//Panel tipo habitacion
		JPanel panelServicioHabitacion = new JPanel();
		panelServicioHabitacion.setBackground(Color.decode("#204473"));	
		panelServicioHabitacion.setLayout(new GridLayout(2, 1));

		//Tipo de habitacion y su caja de texto
		JLabel servicioHabitacion = new JLabel("Servicio");
		servicioHabitacion.setForeground(Color.white);
		servicioHabitacion.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		
		cajaServicioHabitacion = new JTextField();
		cajaServicioHabitacion.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		
		panelServicioHabitacion.add(servicioHabitacion);
		panelServicioHabitacion.add(cajaServicioHabitacion);
		
		//Panel tipo habitacion
		JPanel panelPrecio = new JPanel();
		panelPrecio.setBackground(Color.decode("#204473"));	
		panelPrecio.setLayout(new GridLayout(2, 1));

		//Tipo de habitacion y su caja de texto
		JLabel precio = new JLabel("Precio servicio");
		precio.setForeground(Color.white);
		precio.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		
		cajaPrecio = new JTextField();
		cajaPrecio.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		
		panelPrecio.add(precio);
		panelPrecio.add(cajaPrecio);
		
		//Panel agregar
		JPanel panelAgregar = new JPanel();
		panelAgregar.setBackground(Color.decode("#204473"));
		
		//Boton crear habitacion
		crearHabitacion = new JButton("Crear habitación");
		crearHabitacion.setBackground(Color.decode("#ACCAF2"));
		crearHabitacion.setPreferredSize(new Dimension( 200, 60));
		crearHabitacion.setBackground(Color.CYAN);
		crearHabitacion.setFont(new Font("arial", 1, 20));
		crearHabitacion.addActionListener(this);
		
		panelAgregar.add(crearHabitacion);
		
		//Agregacion de componentes
		panelCrear.add(panelPiso);
		panelCrear.add(panelTipoHabitacion);
		panelCrear.add(panelCapacidad);
		panelCrear.add(panelApto);
		panelCrear.add(panelServicioHabitacion);
		panelCrear.add(panelPrecio);
		panelCrear.add(panelAgregar);
		
	}
	
	@Override
	protected void setPanelInfo() {
		GridBagLayout gridbag = new GridBagLayout();
        GridBagConstraints constraints = new GridBagConstraints();
        panelDerecho.setLayout(gridbag);
		panelDerecho.setBackground(Color.decode("#b2bba4"));
		panelDerecho.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		
		//Creacion de la tabla servicios
		String[] columnas = {"ID", "Tipo", "Capacidad", "Apto", "Servicios", "Características"}; //Nombre de las columnas
        modeloTabla = new DefaultTableModel(columnas, 0);
        
	    
	    //Diseño de la tabla
        tablaHabitaciones = new JTable(modeloTabla);
        tablaHabitaciones.addMouseListener(this);
        tablaHabitaciones.getTableHeader().setBackground(Color.decode("#204473"));
        tablaHabitaciones.getTableHeader().setForeground(Color.white);
        tablaHabitaciones.getTableHeader().setFont(new Font("Times New Roman", 1, 30));
        tablaHabitaciones.setFont(new Font("Times New Roman", 1, 20));
        tablaHabitaciones.setRowHeight(40);
        tablaHabitaciones.setEnabled(false);

        DefaultTableCellRenderer modelocentrar = new DefaultTableCellRenderer();
        modelocentrar.setHorizontalAlignment(SwingConstants.CENTER);

        for (int i = 0; i < columnas.length; i++) {
        	tablaHabitaciones.getColumnModel().getColumn(i).setCellRenderer(modelocentrar);	
		}

        JScrollPane scrollPanel = new JScrollPane(tablaHabitaciones);

        //Tamaño y ubicacion de la tabla en el panel
        constraints.gridx = 0;
        constraints.ipady = 0;
        constraints.ipadx = 400;

        panelDerecho.add(scrollPanel, constraints);

	}
	
	
	
	private boolean getApto(String opcion) {
		boolean apto = false;
		if (opcion.equals("si")) {
			apto = true;
		}
		return apto;
	}
	
	protected void cargarDatos() {
		modeloTabla.getDataVector().removeAllElements();
		modeloTabla.fireTableDataChanged(); 
		Collection<Habitacion> listaHabitaciones = windowManager.darHabitaciones().values();
		for (Habitacion habitacion : listaHabitaciones) {
	        String id = String.valueOf(habitacion.getId());
	        String tipo = habitacion.getTipoHabitacion().toString();
	        String capacidad = String.valueOf(habitacion.getCapacidad());
	        String apto = "No";
	        if (habitacion.getApto()) {
				apto="Si";
	        }
	        ArrayList<Servicio> servicios = habitacion.getServicios();
	        String caracteristicas = habitacion.getCaracteristicas();
	        modeloTabla.addRow(new Object[]{id, tipo, capacidad, apto, servicios, caracteristicas, "ICON", "ICON"});
	    }
	}
	
	
	private void crearHabitacion() {
		
		
	    int tipo = cajaTipoHabitacion.getSelectedIndex();
	    TipoHabitacion tipoHabitacion = TipoHabitacion.ESTANDAR;
	    
	    switch (tipo) {
		case 1:
			tipoHabitacion  = TipoHabitacion.ESTANDAR;
			break;
		case 2:
			tipoHabitacion = TipoHabitacion.SUITE;

			break;
		case 3:
			tipoHabitacion = TipoHabitacion.SUITE;

			break;

		default:
			break;
		}
		
		int piso = (int) cajaPiso.getValue();
		int id = Habitacion.getMaxHabitacion(piso);
		int capacidad = Integer.parseInt(cajaCapacidad.getText());
		boolean apto = cajaApto.isSelected();
		windowManager.crearHabitacion(tipoHabitacion, id, capacidad, apto);
		String nombre = cajaServicioHabitacion.getText();
		Double precio = Double.parseDouble(cajaPrecio.getText());
		windowManager.añadirServicioHabitacion(id, nombre, precio);
		String option = JOptionPane.showInputDialog("Caracteriticas");
		cajaTipoHabitacion.setSelectedIndex(0);;
		cajaPiso.setValue(1);
		cajaCapacidad.setText("");
		cajaApto.setText("");
		cajaServicioHabitacion.setText("");
		cajaPrecio.setText("");
		windowManager.setCaracteriticas(option, id);
		cargarDatos();
		
	}

	@Override
	public void actionPerformedFrame(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "Crear habitación":
			crearHabitacion();
			break;
		default:
			break;
		}
	}

}
