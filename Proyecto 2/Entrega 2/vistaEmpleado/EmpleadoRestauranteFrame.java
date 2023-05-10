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

import controlador.WindowManager;
import modelo.Habitacion;
import modelo.ProductoMenu;
import modelo.Servicio;
import vistaAdmin.FrameBaseInfo;

public class EmpleadoRestauranteFrame extends FrameBaseInfo implements MouseListener{

	private DefaultTableModel modeloTabla;
	private JTable tablaMenu;
	private JTextField cajaNombre;
	private JTextField cajaPrecio;
	private JTextField cajaLlevable;
	private JButton agregarOrden;
	private JButton eliminarOrden;
	private JTextField cajaNumeroHabitacion;
	private JButton cargarAHabitacion;
	private JTextField cajaHorarioI;
	private JTextField cajaHorarioF;

	public EmpleadoRestauranteFrame(WindowManager windowManager) {
		super(windowManager);
		//cargarDatos();
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
	    
	    //Diseño de la tabla
        tablaMenu = new JTable(modeloTabla);
        tablaMenu.addMouseListener(this);
        tablaMenu.setDefaultEditor(Object.class, null);
        tablaMenu.getTableHeader().setBackground(Color.decode("#204473"));
        tablaMenu.getTableHeader().setForeground(Color.white);
        tablaMenu.getTableHeader().setFont(new Font("Times New Roman", 1, 30));
        tablaMenu.setFont(new Font("Times New Roman", 1, 20));
        tablaMenu.setRowHeight(60);
        tablaMenu.setEnabled(true);

        DefaultTableCellRenderer modelocentrarMenu = new DefaultTableCellRenderer();
        modelocentrarMenu.setHorizontalAlignment(SwingConstants.CENTER);


        tablaMenu.getColumnModel().getColumn(0).setCellRenderer(modelocentrarMenu);

        JScrollPane scrollPanelMenu = new JScrollPane(tablaMenu);

        //Tamaño y ubicacion de la tabla en el panel
        constraints.gridx = 0;
        constraints.ipady = 650;
        constraints.ipadx = 400;
        constraints.gridheight = 4;
        constraints.gridwidth = 1;
        constraints.weighty = 1;
        constraints.weightx = 1;

        panelDerecho.add(scrollPanelMenu, constraints);
		
      //Creacion del recuadro para añadir producto menu a la habitacion
  		JPanel panelAgregarOrden = new JPanel(new GridLayout(6,0, 0, 10));
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
		
		cajaNombre = new JTextField();
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
		
		cajaPrecio = new JTextField();
		cajaPrecio.setFont(new Font("Times New Roman", Font.PLAIN, 30));

		panelPrecio.add(precio);
		panelPrecio.add(cajaPrecio);
		
		//Crea el panel horario
		JPanel panelHorarioI = new JPanel();
		panelHorarioI.setBackground(Color.decode("#204473"));	
		panelHorarioI.setLayout(new GridLayout(2, 1));
		
		//Precio y su caja de texto
		JLabel horarioI = new JLabel("Horario");
		horarioI.setForeground(Color.white);
		horarioI.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		
		cajaHorarioI = new JTextField();
		cajaHorarioI.setFont(new Font("Times New Roman", Font.PLAIN, 30));

		panelHorarioI.add(horarioI);
		panelHorarioI.add(cajaHorarioI);
		
		//Crea el panel horario
		JPanel panelHorarioF = new JPanel();
		panelHorarioF.setBackground(Color.decode("#204473"));	
		panelHorarioF.setLayout(new GridLayout(2, 1));
		
		//Precio y su caja de texto
		JLabel horarioF = new JLabel("Horario");
		horarioF.setForeground(Color.white);
		horarioF.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		
		cajaHorarioF = new JTextField();
		cajaHorarioF.setFont(new Font("Times New Roman", Font.PLAIN, 30));

		panelHorarioF.add(horarioF);
		panelHorarioF.add(cajaHorarioF);
		
		//Crea el panel llevable
		JPanel panelLlevable = new JPanel();
		panelLlevable.setBackground(Color.decode("#204473"));	
		panelLlevable.setLayout(new GridLayout(2, 1));
		
		//Precio y su caja de texto
		JLabel llevable = new JLabel("Llevable");
		llevable.setForeground(Color.white);
		llevable.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		
		cajaLlevable = new JTextField();
		cajaLlevable.setFont(new Font("Times New Roman", Font.PLAIN, 30));

		panelLlevable.add(llevable);
		panelLlevable.add(cajaLlevable);
		
		//Crea el panel agregar orden
		JPanel panelAgregarEliminarBotones = new JPanel(new GridLayout(2,0, 0 ,5));
		panelAgregarEliminarBotones.setBackground(Color.decode("#204473"));
		
		//Boton par agregar un servicio
		agregarOrden = new JButton("Agregar a la orden");
		agregarOrden.setBackground(Color.decode("#accaf2"));
		agregarOrden.setFont(new Font("arial", 1, 20));
		agregarOrden.addActionListener(this);

		//Boton par agregar un servicio
		eliminarOrden = new JButton("Eliminar a la orden");
		eliminarOrden.setBackground(Color.decode("#accaf2"));
		eliminarOrden.setFont(new Font("arial", 1, 20));
		eliminarOrden.addActionListener(this);
		
		panelAgregarEliminarBotones.add(agregarOrden);		
		panelAgregarEliminarBotones.add(eliminarOrden);
		
		panelAgregarOrden.add(panelNombre);
		panelAgregarOrden.add(panelPrecio);
		panelAgregarOrden.add(panelHorarioI);
		panelAgregarOrden.add(panelHorarioF);
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
  		
  		cajaNumeroHabitacion = new JTextField();
  		cajaNumeroHabitacion.setFont(new Font("Times New Roman", Font.PLAIN, 30));
  		
  		panelNumeroHabitacion.add(numHabitacion);
  		panelNumeroHabitacion.add(cajaNumeroHabitacion);
  		
  		JPanel botonesHabitacion = new JPanel(new GridLayout(2,0, 0 ,5));
  		botonesHabitacion.setBackground(Color.decode("#accaf2"));
  		
  		cargarAHabitacion = new JButton("Cargar a la habitación​");
  		cargarAHabitacion.setBackground(Color.decode("#204473"));
  		cargarAHabitacion.setForeground(Color.white);
  		cargarAHabitacion.setFont(new Font("arial", 1, 20));
  		cargarAHabitacion.addActionListener(this);
  		
  		
  		botonesHabitacion.add(cargarAHabitacion);
  		
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
	
	@Override
	public void actionPerformedFrame(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "Agregar orden":
			break;
		case "Eliminar orden":
			break;
		case "Cargar a habitacion":
			break;

		default:
			break;
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
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

