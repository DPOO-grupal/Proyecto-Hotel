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

public class RestauranteAdminFrame extends FrameBaseInfo{

	private DefaultTableModel modeloTabla;
	private JTable tablaServicios;

	public RestauranteAdminFrame(WindowManager windowManager) {
		super(windowManager);
		//setTitle("Restaurante");
	}

	@Override
	protected void setPanelInfo() {
		//Edita el aspecto del panel	
		GridBagLayout gridbag = new GridBagLayout();
        GridBagConstraints constraints = new GridBagConstraints();
        panelDerecho.setLayout(gridbag);
		panelDerecho.setBackground(Color.decode("#b2bba4"));
		panelDerecho.setBorder(BorderFactory.createEmptyBorder(5, 31, 5, 1));
		
		//Creacion de la tabla menu
		String[] columnasMenu = {"Menú"}; //Nombre de las columnas
        modeloTabla = new DefaultTableModel(columnasMenu, 0);
        
        //Filas de la tabla
        String[] fila1Menu = {"Pollo gratinado​"};
        String[] fila2Menu = {"Costillas BBQ​"};
        String[] fila3Menu = {"Sobrebarriga en salsa​"};
        String[] fila4Menu = {"Salmón dorado​"};
        String[] fila5Menu = {"Pollo frito​"};
        String[] fila6Menu = {"Papas a la francesa​"};
        String[] fila7Menu = {"Limonada natural​"};
        modeloTabla.addRow(fila1Menu);
	    modeloTabla.addRow(fila2Menu);
	    modeloTabla.addRow(fila3Menu);
	    modeloTabla.addRow(fila4Menu);
	    modeloTabla.addRow(fila5Menu);
	    modeloTabla.addRow(fila6Menu);
	    modeloTabla.addRow(fila7Menu);
	    
	    //Diseño de la tabla
        tablaServicios = new JTable(modeloTabla);
        tablaServicios.getTableHeader().setBackground(Color.decode("#204473"));
        tablaServicios.getTableHeader().setForeground(Color.white);
        tablaServicios.getTableHeader().setFont(new Font("Times New Roman", 1, 30));
        tablaServicios.setFont(new Font("Times New Roman", 1, 20));
        tablaServicios.setRowHeight(60);
        tablaServicios.setEnabled(false);

        DefaultTableCellRenderer modelocentrarMenu = new DefaultTableCellRenderer();
        modelocentrarMenu.setHorizontalAlignment(SwingConstants.CENTER);


        tablaServicios.getColumnModel().getColumn(0).setCellRenderer(modelocentrarMenu);

        JScrollPane scrollPanelMenu = new JScrollPane(tablaServicios);

        //Tamaño y ubicacion de la tabla en el panel
        constraints.gridx = 0;
        constraints.ipady = 350;
        constraints.ipadx = 400;
        constraints.gridheight = 2;
        constraints.gridwidth = 1;
        constraints.weighty = 1;
        constraints.weightx = 1;

        panelDerecho.add(scrollPanelMenu, constraints);
        
        //Creacion de la tabla orden
  		String[] columnasOrden = {"Orden"}; //Nombre de las columnas
  		modeloTabla = new DefaultTableModel(columnasOrden, 0);
          
  		//Filas de la tabla
  		String[] fila1Orden = {"Pollo gratinado​"};
  		String[] fila2Orden = {"Costillas BBQ​"};
  		String[] fila3Orden = {"Sobrebarriga en salsa​"};
  		String[] fila4Orden = {"Salmón dorado​"};
  		String[] fila5Orden = {"Pollo frito​"};
  		String[] fila6Orden = {"Papas a la francesa​"};
  		String[] fila7Orden = {"Limonada natural​"};
  		modeloTabla.addRow(fila1Orden);
  	    modeloTabla.addRow(fila2Orden);
  	    modeloTabla.addRow(fila3Orden);
  	    modeloTabla.addRow(fila4Orden);
  	    modeloTabla.addRow(fila5Orden);
  	    modeloTabla.addRow(fila6Orden);
  	    modeloTabla.addRow(fila7Orden);
  	    
  	    //Diseño de la tabla
        tablaServicios = new JTable(modeloTabla);
        tablaServicios.getTableHeader().setBackground(Color.decode("#204473"));
        tablaServicios.getTableHeader().setForeground(Color.white);
        tablaServicios.getTableHeader().setFont(new Font("Times New Roman", 1, 30));
        tablaServicios.setFont(new Font("Times New Roman", 1, 20));
        tablaServicios.setRowHeight(60);
        tablaServicios.setEnabled(false);

        DefaultTableCellRenderer modelocentrarOrden = new DefaultTableCellRenderer();
        modelocentrarOrden.setHorizontalAlignment(SwingConstants.CENTER);


        tablaServicios.getColumnModel().getColumn(0).setCellRenderer(modelocentrarOrden);

        JScrollPane scrollPanelOrden = new JScrollPane(tablaServicios);

        //Tamaño y ubicacion de la tabla en el panel
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.ipady = 150;
        constraints.ipadx = 400;
        constraints.gridheight = 2;
        constraints.gridwidth = 1;
        constraints.weighty = 2;
        constraints.weightx = 1;

        panelDerecho.add(scrollPanelOrden, constraints);
		
      //Creacion del recuadro para añadir producto menu a la habitacion
  		JPanel panelAgregarOrden = new JPanel(new GridLayout(5,0, 0, 10));
  		panelAgregarOrden.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
  		panelAgregarOrden.setBackground(Color.decode("#204473"));
  		
  		//Crea el panel nombre
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
		
		//Crea el panel precio
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
		
		//Crea el panel horario
		JPanel panelHorario = new JPanel();
		panelHorario.setBackground(Color.decode("#204473"));	
		panelHorario.setLayout(new GridLayout(2, 1));
		
		//Precio y su caja de texto
		JLabel horario = new JLabel("Horario");
		horario.setForeground(Color.white);
		horario.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		
		JTextField cajaHorario = new JTextField();
		cajaHorario.setFont(new Font("Times New Roman", Font.PLAIN, 30));

		panelHorario.add(horario);
		panelHorario.add(cajaHorario);
		
		//Crea el panel llevable
		JPanel panelLlevable = new JPanel();
		panelLlevable.setBackground(Color.decode("#204473"));	
		panelLlevable.setLayout(new GridLayout(2, 1));
		
		//Precio y su caja de texto
		JLabel llevable = new JLabel("Llevable");
		llevable.setForeground(Color.white);
		llevable.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		
		JTextField cajaLlevable = new JTextField();
		cajaLlevable.setFont(new Font("Times New Roman", Font.PLAIN, 30));

		panelLlevable.add(llevable);
		panelLlevable.add(cajaLlevable);
		
		//Crea el panel agregar orden
		JPanel panelAgregarEliminarBotones = new JPanel(new GridLayout(2,0, 0 ,5));
		panelAgregarEliminarBotones.setBackground(Color.decode("#204473"));
		
		//Boton par agregar un servicio
		JButton agregarOrden = new BotonRedondeado("Agregar a la orden", 200, 60, 30, Color.decode("#ACCAF2"));
		agregarOrden.setBackground(Color.decode("#accaf2"));
		agregarOrden.setFont(new Font("arial", 1, 20));
		agregarOrden.addActionListener(this);

		//Boton par agregar un servicio
		JButton eliminarOrden = new BotonRedondeado("Eliminar a la orden", 200, 60, 30, Color.decode("#ACCAF2"));
		eliminarOrden.setBackground(Color.decode("#accaf2"));
		eliminarOrden.setFont(new Font("arial", 1, 20));
		eliminarOrden.addActionListener(this);
		
		panelAgregarEliminarBotones.add(agregarOrden);		
		panelAgregarEliminarBotones.add(eliminarOrden);
		
		panelAgregarOrden.add(panelNombre);
		panelAgregarOrden.add(panelPrecio);
		panelAgregarOrden.add(panelHorario);
		panelAgregarOrden.add(panelLlevable);
		panelAgregarOrden.add(panelAgregarEliminarBotones);
  		
  		//Tamaño y ubicacion en el panel
  		constraints.gridx = 1;
		constraints.gridy = 0;
		constraints.ipady = 50;
        constraints.ipadx = 131;
        constraints.gridheight = 2;
        constraints.gridwidth = 2;
        constraints.weighty = 2;
        constraints.weightx = 2;
  		
  		
  		panelDerecho.add(panelAgregarOrden, constraints);
        
        //Creacion del recuadro para añadir producto menu a la habitacion
  		JPanel panelHabitacion = new JPanel(new GridLayout(2,0, 0, 10));
  		panelHabitacion.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
  		panelHabitacion.setBackground(Color.decode("#accaf2"));
  		
  		JPanel panelNumeroHabitacion = new JPanel(new GridLayout(2,0));
  		panelNumeroHabitacion.setBackground(Color.decode("#accaf2"));
  		
  		JLabel numHabitacion = new JLabel("#Habitación");
  		numHabitacion.setFont(new Font("Times New Roman", Font.PLAIN, 30));
  		
  		JTextField cajaNumeroHabitacion = new JTextField();
  		cajaNumeroHabitacion.setFont(new Font("Times New Roman", Font.PLAIN, 30));
  		
  		panelNumeroHabitacion.add(numHabitacion);
  		panelNumeroHabitacion.add(cajaNumeroHabitacion);
  		
  		JPanel botonesHabitacion = new JPanel(new GridLayout(2,0, 0 ,5));
  		botonesHabitacion.setBackground(Color.decode("#accaf2"));
  		
  		JButton cargarAHabitacion = new BotonRedondeado("Cargar a la habitación​", 200, 60, 30, Color.white);
  		cargarAHabitacion.setBackground(Color.decode("#204473"));
  		cargarAHabitacion.setForeground(Color.white);
  		cargarAHabitacion.setFont(new Font("arial", 1, 20));
  		cargarAHabitacion.addActionListener(this);
  		
  		
  		JButton pagarAhora = new BotonRedondeado("Pagar ahora​", 200, 60, 30, Color.white);
  		pagarAhora.setBackground(Color.decode("#204473"));
  		pagarAhora.setForeground(Color.white);
  		pagarAhora.setFont(new Font("arial", 1, 20));
  		pagarAhora.addActionListener(this);
  		
  		botonesHabitacion.add(cargarAHabitacion);
  		botonesHabitacion.add(pagarAhora);
  		
  		panelHabitacion.add(panelNumeroHabitacion);
  		panelHabitacion.add(botonesHabitacion);
  		
  		//Tamaño y ubicacion en el panel
  		constraints.gridx = 1;
		constraints.gridy = 2;
		constraints.ipadx = 100;
  		
  		panelDerecho.add(panelHabitacion, constraints);

	}

	@Override
	protected void setPanelCrear() {
		//Edita el aspecto del panel	
		panelCrear.setLayout(new GridLayout(5, 1, 10, 10));
		panelCrear.setBackground(Color.decode("#204473"));
		panelCrear.setBorder(BorderFactory.createEmptyBorder(20, 100, 20, 100));
		
		//Crea el panel nombre
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
		
		//Crea el panel precio
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
		
		//Crea el panel horario
		JPanel panelHorario = new JPanel();
		panelHorario.setBackground(Color.decode("#204473"));	
		panelHorario.setLayout(new GridLayout(2, 1));
		
		//Precio y su caja de texto
		JLabel horario = new JLabel("Horario");
		horario.setForeground(Color.white);
		horario.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		
		JTextField cajaHorario = new JTextField();
		cajaHorario.setFont(new Font("Times New Roman", Font.PLAIN, 30));

		panelHorario.add(horario);
		panelHorario.add(cajaHorario);
		
		//Crea el panel llevable
		JPanel panelLlevable = new JPanel();
		panelLlevable.setBackground(Color.decode("#204473"));	
		panelLlevable.setLayout(new GridLayout(2, 1));
		
		//Precio y su caja de texto
		JLabel llevable = new JLabel("Llevable");
		llevable.setForeground(Color.white);
		llevable.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		
		JTextField cajaLlevable = new JTextField();
		cajaLlevable.setFont(new Font("Times New Roman", Font.PLAIN, 30));

		panelLlevable.add(llevable);
		panelLlevable.add(cajaLlevable);
		
		//Crea el panel agregar servicio
		JPanel panelAgregarServicio = new JPanel();
		panelAgregarServicio.setBackground(Color.decode("#204473"));
		
		//Boton par agregar un servicio
		JButton agregarServicio = new BotonRedondeado("Agregar al menú", 200, 60, 30, Color.decode("#ACCAF2"));
		agregarServicio.setBackground(Color.decode("#accaf2"));
		agregarServicio.setFont(new Font("arial", 1, 20));
		agregarServicio.addActionListener(this);
		
		panelAgregarServicio.add(agregarServicio);
		
		//Se añaden los componentes al panel
		panelCrear.add(panelNombre);
		panelCrear.add(panelPrecio);
		panelCrear.add(panelHorario);
		panelCrear.add(panelLlevable);
		panelCrear.add(panelAgregarServicio);
				
		
	}

	@Override
	public void actionPerformedFrame(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "Agregar orden":
			break;
		case "Eliminar orden":
			break;
		case "Cargar a habitacion":
			break;
		case "Pagar ahora":
			break;
		case "Agregar al menú":		
			break;

		default:
			break;
		}
	}
	
}
