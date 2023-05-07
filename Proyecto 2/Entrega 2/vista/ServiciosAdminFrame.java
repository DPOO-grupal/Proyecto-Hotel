package vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class ServiciosAdminFrame extends FrameBaseInfo{
	
	private DefaultTableModel modeloTabla;
	private JTable tablaServicios;

	public ServiciosAdminFrame(WindowManager windowManager) {
		super(windowManager);
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
		
		JTextField cajaNombre = new JTextField();
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
		
		JTextField cajaPrecio = new JTextField();
		cajaPrecio.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		
		panelPrecio.add(precio);
		panelPrecio.add(cajaPrecio);

		//Panel agregar
		JPanel panelAgregar = new JPanel();
		panelAgregar.setBackground(Color.decode("#204473"));
		
		//Boton para agregar un servicio
		JButton agregarServicio = new BotonRedondeado("Agregar servicio", 200, 60, 30, Color.decode("#ACCAF2"));
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
        String[] fila1 = {"Spa"};
        String[] fila2 = {"LOL"};
        modeloTabla.addRow(fila1);
	    modeloTabla.addRow(fila2);
	    
	    //Diseño de la tabla
        tablaServicios = new JTable(modeloTabla);
        tablaServicios.getTableHeader().setBackground(Color.decode("#204473"));
        tablaServicios.getTableHeader().setForeground(Color.white);
        tablaServicios.getTableHeader().setFont(new Font("Times New Roman", 1, 30));
        tablaServicios.setFont(new Font("Times New Roman", 1, 20));
        tablaServicios.setRowHeight(70);
        tablaServicios.setEnabled(false);

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
		
		JTextField cajaNombre = new JTextField();
		
		JLabel precio = new JLabel("Precio");
		precio.setForeground(Color.BLACK);
		precio.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		
		JTextField cajaPrecio = new JTextField();
		
		//Diseño 
		JPanel buscar = new JPanel(new GridLayout(4,1));
		buscar.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		buscar.setBackground(Color.decode("#accaf2"));
		
		buscar.add(nombre);
		buscar.add(cajaNombre);
		buscar.add(precio);
		buscar.add(cajaPrecio);
		
		//Tamaño y ubicacion en el panel
		constraints.gridx = 3;
		constraints.gridy = 0;
		constraints.ipady = 120;
        constraints.ipadx = 250;
        constraints.gridheight = 1;
        constraints.gridwidth = 1;
		
		panelDerecho.add(buscar, constraints);
		
		//Creacion del recuadro para añadir servicio a la habitacion
		JPanel habitacion = new JPanel(new GridLayout(3,1, 0, 5));
		habitacion.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		habitacion.setBackground(Color.decode("#accaf2"));
		
		//Numero de habitacion y su caja de texto
		JLabel numHabitacion = new JLabel("Número de habitacion");
		numHabitacion.setForeground(Color.BLACK);
		numHabitacion.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		
		JTextField cajaNumeroHabitacion = new JTextField();
		
		//Boton para añadir un servicio
		JButton añadirAHabitacion = new BotonRedondeado("Añadir habitación", 200, 50, 30, Color.white);
		añadirAHabitacion.setBackground(Color.decode("#204473"));
		añadirAHabitacion.setForeground(Color.white);
		añadirAHabitacion.setFont(new Font("arial", 1, 20));
		
		habitacion.add(numHabitacion);
		habitacion.add(cajaNumeroHabitacion);
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

	public void actionPerformedFrame(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "Agregar servicio":
			break;
		
		case "Añadir a la habitacion":
			break;

		default:
			break;
		}
	}
}

