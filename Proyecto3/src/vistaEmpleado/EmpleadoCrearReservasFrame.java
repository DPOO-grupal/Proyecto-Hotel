package vistaEmpleado;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
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

import org.jdesktop.swingx.JXDatePicker;

import controlador.WindowManager;
import modelo.Habitacion;
import modelo.Huesped;
import modelo.TipoHabitacion;
import modelo.Usuario;
import vistaAdmin.FrameBaseInfo;

public class EmpleadoCrearReservasFrame extends FrameBaseInfo implements MouseListener {

	private JTable tablaTarifas;
	private DefaultTableModel modeloTabla;
	private JButton crearReserva;
	private JButton cancelarReserva;
	private JTextField numHabitacion;
	private JFrame selectHabitacion;
	private JXDatePicker fechaI;
	private JXDatePicker fechaF;

	public EmpleadoCrearReservasFrame(WindowManager windowManager) {

		super(windowManager);
		if(windowManager.hayReserva()) {
			JOptionPane.showMessageDialog(null, "Hay una reserva en curso");
			fechaF.setEnabled(false);
			fechaI.setEnabled(false);
		};
	}

	protected void setPanelInfo() {
		// TODO Auto-generated method stub
		panelDerecho.setBackground(Color.decode("#B2BBA4"));
		GridBagLayout gridbag = new GridBagLayout();
		GridBagConstraints constraints = new GridBagConstraints();
		panelDerecho.setLayout(gridbag);

		JPanel panelHabi = new JPanel();
		// añadir al panel
		GridBagLayout gba = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();

		Font fontLabel = new Font("Arial", Font.BOLD, 16);

		panelHabi.setLayout(gba);

		JLabel temLabel = new JLabel("Añadir habitación");
		temLabel.setFont(fontLabel);

		c.gridx = 0;
		c.gridy = 0;

		c.ipady = 20;
		c.ipadx = 100;

		c.weightx = 1;

		c.insets = new Insets(0, 0, 0, 0);

		panelHabi.add(temLabel, c);



		JButton agregarButton = new JButton("Agregar habitación");
		agregarButton.setBackground(Color.decode("#204473"));
		agregarButton.setFont(fontLabel);
		agregarButton.setForeground(Color.white);
		agregarButton.addActionListener(this);

		c.gridx = 0;
		c.gridy = 3;

		c.ipadx = 100;
		c.ipady = 10;

		c.insets = new Insets(10, 0, 0, 0);

		panelHabi.add(agregarButton, c);

		panelHabi.setBackground(Color.decode("#accaf2"));
		constraints.ipadx = 200;
		constraints.ipady = 200;
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.fill = GridBagConstraints.BOTH;
		constraints.anchor = GridBagConstraints.NORTHWEST;
		constraints.weighty = 0.1;

		panelDerecho.add(panelHabi, constraints);
		
		JPanel panelBuscar = new JPanel();	    
	    // añadir al panel
	    	    
	    panelBuscar.setLayout(gba);
	    panelBuscar.setBackground(Color.decode("#accaf2"));
	    
	    
	    fechaI = new JXDatePicker(new Date());
	    fechaF = new JXDatePicker(new Date());
	    
	    c.gridx = 0;
	    c.gridy = 0;
	    
	    c.ipady = 20;
	    c.ipadx = 100;
	    
	    c.insets = new Insets(0, 0, 0, 0);
	    
	    JLabel temLabel1 = new JLabel("Fecha Incial");
		temLabel1.setFont(fontLabel);
	    panelBuscar.add(temLabel1,c);
	    
	    c.gridy = 1;
	    c.gridx = 0;
	    c.insets = new Insets(0, 0, 25, 0);
	    
	    fechaI.setFont(fontLabel);
	    panelBuscar.add(fechaI, c);
	    
	    c.gridy = 3;
	    c.gridx = 0;
	    
	    c.insets = new Insets(0, 0, 0, 0);
	    
	    temLabel1 = new JLabel("Fecha Final");
		temLabel1.setFont(fontLabel );
	    panelBuscar.add(temLabel1,c);
	    
	    c.gridy = 4;
	    c.gridx = 0;
	    
	    c.insets = new Insets(0, 0, 0, 0);
	    
	    fechaF.setFont(fontLabel);
	    panelBuscar.add(fechaF, c);
	    
	    JButton establecerFecha = new JButton("Establecer Fecha");
	    
	    establecerFecha.setBackground(Color.decode("#204473"));
	    establecerFecha.setFont(fontLabel);
	    establecerFecha.setForeground(Color.white);
	    establecerFecha.addActionListener(this);
	    
	    
	    c.gridy = 1;
	    c.gridx = 2;
	    c.ipadx = 10;
	    c.insets = new Insets(0, 0, 0, 0);
	    
	    panelBuscar.add(establecerFecha, c);
	    
	    JButton cambiarFecha = new JButton("Cambiar fecha");
	    
	    cambiarFecha.setBackground(Color.decode("#204473"));
	    cambiarFecha.setFont(fontLabel);
	    cambiarFecha.setForeground(Color.white);
	    cambiarFecha.addActionListener(this);
	    
	    
	    c.gridy = 3;
	    c.gridx = 2;
	    c.ipadx = 10;
	    c.insets = new Insets(0, 0, 0, 0);
	    
	    panelBuscar.add(cambiarFecha, c);
	    
	    constraints.gridx = 1;
	    constraints.gridy = 0;
	    
	    constraints.ipadx = 100;
	    constraints.ipady = 40;
	    
	    constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.anchor = GridBagConstraints.NORTHEAST;
		
	    constraints.insets = new Insets(0, 0  , 0, 0);

	    panelDerecho.add(panelBuscar, constraints);
		
		// tabla
		String[] columnas = { "Habitacion", "Huesped" };
		modeloTabla = new DefaultTableModel(columnas, 0);

		Font fontTabla = new Font("Arial", Font.BOLD, 20);

		tablaTarifas = new JTable(modeloTabla);
		tablaTarifas.setDefaultEditor(Object.class, null);
		tablaTarifas.getTableHeader().setBackground(Color.decode("#204473"));
		tablaTarifas.getTableHeader().setForeground(Color.white);
		tablaTarifas.getTableHeader().setFont(fontTabla);
		tablaTarifas.setBackground(Color.decode("#B2BBA4"));
		tablaTarifas.setRowHeight(50);
		tablaTarifas.addMouseListener(this);
		tablaTarifas.setName("Datos Reserva");
		
		DefaultTableCellRenderer modelocentrar = new DefaultTableCellRenderer();
		modelocentrar.setHorizontalAlignment(SwingConstants.CENTER);
		modelocentrar.setFont(fontTabla);
		modelocentrar.setBackground(Color.white);
		
		for (int i = 0; i < 2; i++) {
			tablaTarifas.getColumnModel().getColumn(i).setCellRenderer(modelocentrar);
			tablaTarifas.getColumnModel().getColumn(i).setCellEditor(null);
		}
		
		llenarTablaReserva();
		
		JScrollPane scrollPanel = new JScrollPane(tablaTarifas);

		scrollPanel.setBackground(Color.decode("#B2BBA4"));
		constraints.gridx = 0;
		constraints.gridy = 1;
		
		constraints.gridwidth = 2;
		
		constraints.fill = GridBagConstraints.BOTH;
		constraints.anchor = GridBagConstraints.CENTER;

		constraints.ipadx = 0;
		constraints.ipady = 500;

		panelDerecho.add(scrollPanel, constraints);

		JPanel panelInferior = new JPanel();
		Font font = new Font("Arial", Font.BOLD, 20);

		panelInferior.setLayout(gba);
		panelInferior.setPreferredSize(new Dimension(800, 30));
		panelInferior.setBackground(Color.decode("#204473"));

		c.gridx = 0;
		c.weightx = 1;
		c.weighty = 1;
	    c.ipadx = 0;


		c.insets = new Insets(0, 0, 0, 0);

		crearReserva = new JButton("Crear Reserva");
		crearReserva.setPreferredSize(new Dimension(200, 30));
		crearReserva.setBackground(Color.decode("#D0ECF2"));
		crearReserva.setFont(font);
		crearReserva.addActionListener(this);
		panelInferior.add(crearReserva, c);

		c.gridx = 1;
		c.insets = new Insets(0, 0, 0, 0);

		cancelarReserva = new JButton("Cancelar Reserva");
		cancelarReserva.setPreferredSize(new Dimension(200, 30));
		cancelarReserva.setBackground(Color.decode("#D0ECF2"));
		cancelarReserva.setFont(font);
		cancelarReserva.addActionListener(this);
		panelInferior.add(cancelarReserva, c);

		constraints.gridx = 0;
		constraints.gridy = 2;
		constraints.anchor = GridBagConstraints.SOUTH;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.ipadx = 550;
		constraints.ipady = 200;
		panelDerecho.add(panelInferior, constraints);
	}

	private void llenarTablaReserva() {
		modeloTabla.getDataVector().removeAllElements();
        modeloTabla.fireTableDataChanged();
		ArrayList<Integer> habitaciones = windowManager.getListaHabitacionesGrupo();

		ArrayList<Huesped> huespedes = windowManager.getHuespedesGrupoEnCurso();
		int numFilas = huespedes.size();
		if (numFilas<habitaciones.size()) {
			numFilas = habitaciones.size();
		}
		
		for (int i = 0; i <numFilas; i++) {
			String nombre = "";
			String habitacion = "";
			
			if (huespedes.size()>i) {
				nombre = huespedes.get(i).getNombre();
			}
			
			if (habitaciones.size()>i) {
				habitacion = habitaciones.get(i)+"";
			}
			
			String[] fila = {habitacion, nombre};
			modeloTabla.addRow(fila);			
			
		}
		
	}


	@Override
	protected void setPanelCrear() {
		// Configuracion General

		GridBagLayout gridbag = new GridBagLayout();
		GridBagConstraints constraints = new GridBagConstraints();

		panelCrear.setLayout(gridbag);

		panelCrear.setBorder(BorderFactory.createEmptyBorder(20, 100, 20, 100));
		panelCrear.setBackground(Color.decode("#204473"));
		Font fontTitulo = new Font("Arial", Font.BOLD, 30);

		JLabel tituloGeneral = new JLabel("Añadir huesped");
		tituloGeneral.setFont(fontTitulo);
		tituloGeneral.setForeground(Color.white);
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.anchor = GridBagConstraints.CENTER;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.weighty = 10;
		panelCrear.add(tituloGeneral, constraints);

		String[] titulos = { "Nombre", "Correo", "Edad", "Telefono", "No. identificacion" };
		datos = new JTextField[titulos.length];

		Font fontLabel = new Font("Arial", Font.BOLD, 20);

		for (int i = 0; i < titulos.length; i++) {
			JTextField campo = new JTextField();
			campo.setPreferredSize(new Dimension(200, 30));

			JLabel titulo = new JLabel(titulos[i]);
			titulo.setFont(fontLabel);
			titulo.setForeground(Color.WHITE);

			datos[i] = campo;

			constraints.gridx = 0;
			constraints.gridy = (i * 2) + 1;
			constraints.fill = GridBagConstraints.HORIZONTAL;
			constraints.weighty = 10;
			constraints.insets = new Insets(0, 0, -20, 0);
			panelCrear.add(titulo, constraints);

			constraints.gridy = (i * 2) + 2;
			constraints.weighty = 10;
			constraints.fill = GridBagConstraints.HORIZONTAL;
			constraints.insets = new Insets(0, 0, 0, 0);

			panelCrear.add(campo, constraints);
		}

		panelCrear.add(new JLabel());

		Font fontBoton = new Font("Arial", Font.BOLD, 20);
		addDatos = new JButton("Añadir Huesped");
		addDatos.setBackground(Color.decode("#ACCAF2"));
		addDatos.setFont(fontBoton);
		addDatos.addActionListener(this);
		
		constraints.gridy = 9;
		constraints.gridy = GridBagConstraints.PAGE_END;
		constraints.insets = new Insets(0, 0, 0, 0);
		panelCrear.add(addDatos, constraints);

	}

	public void mostrarHabitaciones() {
	    JFrame setTipoHabi = new JFrame();

	    // Crear un JXDatePicker con la fecha actual
	    
	    String[] tipos = {"estandar", "Suite", "Double Suite"};
	    JComboBox<String> tiposHabi = new JComboBox<>(tipos);
	    JLabel mensaje = new JLabel("Seleccione una tipo de habitacion"); 
	    JPanel panel = new JPanel();
	    panel.add(mensaje);
	    panel.add(tiposHabi);

	    JOptionPane.showOptionDialog(setTipoHabi, panel, "Seleccionar tipo Habitación", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);

	    int tipo = tiposHabi.getSelectedIndex();
	    TipoHabitacion tipoEnum = TipoHabitacion.ESTANDAR;
	    
	    switch (tipo) {
		case 1:
			tipoEnum = TipoHabitacion.ESTANDAR;
			break;
		case 2:
			tipoEnum = TipoHabitacion.SUITE;

			break;
		case 3:
			tipoEnum = TipoHabitacion.SUITE;

			break;

		default:
			break;
		}
	    
	    ArrayList<Habitacion> disponibles = null;
		try {
			disponibles = windowManager.DiponiblesParaGrupoEnCurso(tipoEnum);
	
		    selectHabitacion = new JFrame();
		    selectHabitacion.setSize(new Dimension(700,500));
		    selectHabitacion.setLocationRelativeTo(null);
		    String[] datosHabitaciones = {"Número","Capacidad", "Precio", "Caracteristicas"};
		    
		    DefaultTableModel modelodisponibles = new DefaultTableModel(datosHabitaciones, disponibles.size());
		    
			Font fontTabla = new Font("Arial", Font.BOLD, 20);
			
		    JTable tablaDisponibles = new JTable(modelodisponibles);
		    tablaDisponibles.setDefaultEditor(Object.class, null);
		    tablaDisponibles.getTableHeader().setBackground(Color.decode("#204473"));
		    tablaDisponibles.getTableHeader().setForeground(Color.white);
		    tablaDisponibles.getTableHeader().setFont(fontTabla);
		    tablaDisponibles.setBackground(Color.decode("#B2BBA4"));
		    tablaDisponibles.setRowHeight(50);
		    tablaDisponibles.addMouseListener(this);
		    tablaDisponibles.setName("TablaDisponibles");
		    
			DefaultTableCellRenderer modelocentrar = new DefaultTableCellRenderer();
			modelocentrar.setHorizontalAlignment(SwingConstants.CENTER);
			modelocentrar.setFont(fontTabla);
			modelocentrar.setBackground(Color.white);
			
			for (int i = 0; i < datosHabitaciones.length; i++) {
				tablaDisponibles.getColumnModel().getColumn(i).setCellRenderer(modelocentrar);
				tablaDisponibles.getColumnModel().getColumn(i).setCellEditor(null);
			}
		    
			modelodisponibles.getDataVector().removeAllElements();
			modelodisponibles.fireTableDataChanged();
			
		    for (Habitacion habitacion:disponibles) {
		    	int precioHabitacion = getPrecioHabitacionReserva(habitacion);
		    	String[] data = {habitacion.getId()+"",""+habitacion.getCapacidad(), ""+precioHabitacion, habitacion.getCaracteristicas()};
		    	modelodisponibles.addRow(data);
		    }
		    
		    JScrollPane scrollPane = new JScrollPane(tablaDisponibles);
		    selectHabitacion.add(scrollPane);
		    selectHabitacion.setVisible(true);
	    
		} catch (Exception e) {
			if (e.getMessage().equals("No hay grupo")) {
				JOptionPane.showMessageDialog(null, "Debe establecer la Fecha");
			}

		}
	    
	}
	public int getPrecioHabitacionReserva(Habitacion habitacion) {
		return windowManager.getPrecioHabitacionReserva(habitacion);
	}

	public void volverMenu() {
		windowManager.volverReserva();
	}

	public void llenarHabitaciones(int i) {
		try {
			windowManager.llenarHabitaciones(i);
			llenarTablaReserva();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "La habitación ya fue añadida");
			
		}
	}
	
	public void checkGrupoEnCurso() {
		//windowManager.checkGrupoEnCurso();
	}
	
	public void llenarHuespeds() {
		String nombre ;
		String email ;
		int edad ;
		String telefono;
		String documento;
		try {
			 nombre = datos[0].getText();
			 email = datos[1].getText();
			 edad = Integer.parseInt(datos[2].getText());
			 telefono = datos[3].getText();
			 documento = datos[4].getText();
			 
			try {
				windowManager.llenarHuespeds(documento, nombre, email, telefono, edad);
				
				for (int i = 0; i<datos.length;i++) {
					datos[i].setText("");
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
				e.getStackTrace();
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Revisar los datos");
			e.getStackTrace();
		}
		
	}

	
	private void cambiarFecha() {
		try {
			windowManager.cambiarFechaReserva(fechaI.getDate(), fechaF.getDate());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());

		}
		llenarTablaReserva();
		
	}

	private void EstablecerFecha() {
		try {
			windowManager.crearReserva(fechaI.getDate(), fechaF.getDate());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		};
	}


	private void crearReserva() {
		try {
			int id = windowManager.getGrupoEnCurso().getId();
			windowManager.completarReserva();
			JOptionPane.showMessageDialog(null, "Su numero de Reserva es " + id);
			volverMenu();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());

		}
		
	}
	
	private void cancelarReserva() {
		windowManager.forzarCancelarReserva();
		
	}
	
	@Override
	public void actionPerformedFrame(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "Agregar habitación":
			mostrarHabitaciones();
			llenarTablaReserva();
			break;
		case "Añadir Huesped":
			llenarHuespeds();
			llenarTablaReserva();
			break;
		case "Cambiar fecha":
			fechaF.setEnabled(true);
			fechaI.setEnabled(true);
			cambiarFecha();
			break;
		case "Establecer Fecha":
			fechaF.setEnabled(false);
			fechaI.setEnabled(false);
			EstablecerFecha();
			break;
		case "Crear Reserva":
			crearReserva();
			break;
		case "Cancelar Reserva":
			cancelarReserva();
			volverMenu();
			break;
		default:
			break;
		}
	}







	@Override
	public void mouseClicked(MouseEvent e) {
		JTable table = (JTable) e.getSource();
		if (table.getName().equals("TablaDisponibles")) {
			llenarHabitaciones(Integer.parseInt((String) table.getValueAt(table.getSelectedRow(), 0)));
			selectHabitacion.dispose();
		}
		
		

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// JOptionPane.showMessageDialog(null, tablaTarifas.getSelectedColumn());

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
