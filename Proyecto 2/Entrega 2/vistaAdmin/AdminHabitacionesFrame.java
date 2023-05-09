package vistaAdmin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
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

public class AdminHabitacionesFrame extends FrameBaseInfo{
	
	private DefaultTableModel modeloTabla;
	private JTable tablaHabitaciones;
	
	public AdminHabitacionesFrame(WindowManager windowManager) {
		super(windowManager);
		//setTitle("Habitaciones");
		
		
	}
	@Override
	protected void setPanelCrear() {
		//Edita el aspecto del panel	
		panelCrear.setLayout(new GridLayout(7, 1, 10, 10));
		panelCrear.setBackground(Color.decode("#204473"));
		panelCrear.setBorder(BorderFactory.createEmptyBorder(20, 100, 20, 100));
		
		//Panel ID
		JPanel panelId = new JPanel();
		panelId.setBackground(Color.decode("#204473"));	
		panelId.setLayout(new GridLayout(2, 1));
		
		//Id y su caja de texto
		JLabel id = new JLabel("Nombre");
		id.setForeground(Color.white);
		id.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		
		JTextField cajaId = new JTextField();
		cajaId.setFont(new Font("Times New Roman", Font.PLAIN, 30));

		
		panelId.add(id);
		panelId.add(cajaId);
		
		//Panel tipo habitacion
		JPanel panelTipoHabitacion = new JPanel();
		panelTipoHabitacion.setBackground(Color.decode("#204473"));	
		panelTipoHabitacion.setLayout(new GridLayout(2, 1));

		//Tipo de habitacion y su caja de texto
		JLabel tipoHabitacion = new JLabel("Tipo habitación");
		tipoHabitacion.setForeground(Color.white);
		tipoHabitacion.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		
		JTextField cajaTipoHabitacion = new JTextField();
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
		
		JTextField cajaCapacidad = new JTextField();
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
		
		JTextField cajaApto = new JTextField();
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
		
		JTextField cajaServicioHabitacion = new JTextField();
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
		
		JTextField cajaPrecio = new JTextField();
		cajaPrecio.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		
		panelPrecio.add(precio);
		panelPrecio.add(cajaPrecio);
		
		//Panel agregar
		JPanel panelAgregar = new JPanel();
		panelAgregar.setBackground(Color.decode("#204473"));
		
		//Boton crear habitacion
		JButton crearHabitacion = new JButton("Crear habitación");
		crearHabitacion.setBackground(Color.decode("#ACCAF2"));
		crearHabitacion.setPreferredSize(new Dimension( 200, 60));
		crearHabitacion.setBackground(Color.CYAN);
		crearHabitacion.setFont(new Font("arial", 1, 20));
		crearHabitacion.addActionListener(this);
		
		panelAgregar.add(crearHabitacion);
		
		//Agregacion de componentes
		panelCrear.add(panelId);
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
        
        //Filas de la tabla
        String[] fila1 = {"503", "Suite", "4", "Si", ""};
        String[] fila2 = {"205", "Double Suite", "6", "No", ""};
        modeloTabla.addRow(fila1);
	    modeloTabla.addRow(fila2);
	    
	    //Diseño de la tabla
        tablaHabitaciones = new JTable(modeloTabla);
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
	
	protected void crearCama() {
		//Edita el aspecto del panel	
		panelCrear.setLayout(new GridLayout(5, 1, 10, 10));
		panelCrear.setBackground(Color.decode("#204473"));
		panelCrear.setBorder(BorderFactory.createEmptyBorder(20, 100, 20, 100));
		
		//Panel ID
		JPanel panelCapacidad = new JPanel();
		panelCapacidad.setBackground(Color.decode("#204473"));	
		panelCapacidad.setLayout(new GridLayout(2, 1));
		
		//Id y su caja de texto
		JLabel capacidadCama = new JLabel("Nombre");
		capacidadCama.setForeground(Color.white);
		capacidadCama.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		
		JTextField cajaCapacidadCama = new JTextField();
		cajaCapacidadCama.setFont(new Font("Times New Roman", Font.PLAIN, 30));

		
		panelCapacidad.add(capacidadCama);
		panelCapacidad.add(cajaCapacidadCama);
		
		//Panel tipo habitacion
		JPanel panelApto = new JPanel();
		panelApto.setBackground(Color.decode("#204473"));	
		panelApto.setLayout(new GridLayout(2, 1));

		//Tipo de habitacion y su caja de texto
		JLabel apto = new JLabel("Tipo habitación");
		apto.setForeground(Color.white);
		apto.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		
		JTextField cajaApto = new JTextField();
		cajaApto.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		
		panelApto.add(apto);
		panelApto.add(cajaApto);

		//Panel agregar
		JPanel panelCrear = new JPanel();
		panelCrear.setBackground(Color.decode("#204473"));
		
		//Boton crear habitacion
		JButton crearHabitacion = new JButton("Crear habitación");
		crearHabitacion.setBackground(Color.decode("#ACCAF2"));
		crearHabitacion.setPreferredSize(new Dimension( 200, 60));
		crearHabitacion.setBackground(Color.CYAN);
		crearHabitacion.setFont(new Font("arial", 1, 20));
		crearHabitacion.addActionListener(this);
		
		panelCrear.add(crearHabitacion);
		
		//Agregacion de componentes
		panelCrear.add(new JLabel());
		panelCrear.add(panelCapacidad);
		panelCrear.add(panelApto);
		panelCrear.add(panelCrear);
		
	}

	@Override
	public void actionPerformedFrame(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "Crear habitación":
			break;
		
		case "Añadir cama":
			break;
			
		case "Añadir servicio":
			break;

		default:
			break;
		}
	}

}
