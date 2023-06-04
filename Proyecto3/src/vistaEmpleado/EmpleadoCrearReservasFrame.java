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
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JComponent;
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

import org.jdesktop.swingx.JXDatePicker;

import controlador.WindowManager;
import modelo.Grupo;
import modelo.Habitacion;
import modelo.Huesped;
import modelo.TipoHabitacion;
import modelo.Usuario;
import vistaAdmin.FrameBaseInfo;

public class EmpleadoCrearReservasFrame extends FrameBaseInfo implements MouseListener, KeyListener {

	private JTable tablaTarifas;
	private DefaultTableModel modeloTabla;
	private JButton crearReserva;
	private JButton cancelarReserva;
	private JTextField numHabitacion;
	private JFrame selectHabitacion;
	protected JXDatePicker fechaI;
	protected JXDatePicker fechaF;

	public EmpleadoCrearReservasFrame(WindowManager windowManager) {

		super(windowManager);
		
	}
	
	public void estadoReserva() {
		if(windowManager.hayReserva()) {
			JOptionPane.showMessageDialog(null, "Hay una reserva en curso");
		}
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
		panelHabi.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		JLabel temLabel = new JLabel("Añadir habitación");
		temLabel.setFont(fontLabel);

		c.gridx = 0;
		c.gridy = 1;

		c.weightx = 1;
		c.weighty = 1;
	    c.ipadx = 1;
	    c.ipady = 1;
	    
		c.anchor = GridBagConstraints.SOUTH;
		c.insets = new Insets(0, 0, 0, 0);

		panelHabi.add(temLabel, c);



		JButton agregarButton = new JButton("Agregar habitación");
		agregarButton.setBackground(Color.decode("#204473"));
		agregarButton.setFont(fontLabel);
		agregarButton.setForeground(Color.white);
		agregarButton.addActionListener(this);
		agregarButton.setPreferredSize(new Dimension(200, 40));

		c.gridx = 0;
		c.gridy = 2;
	    c.ipadx = 1;
	    c.ipady = 1;

		c.anchor = GridBagConstraints.CENTER;

		c.insets = new Insets(10, 0, 0, 0);

		panelHabi.add(agregarButton, c);
	    
	    
	    fechaI = new JXDatePicker(new Date());
	    fechaF = new JXDatePicker(new Date());
	    
	    c.gridx = 1;
	    c.gridy = 0;
		c.anchor = GridBagConstraints.SOUTH;

	    
	    c.insets = new Insets(0, 0, 0, 0);
	    
	    JLabel temLabel1 = new JLabel("Fecha Incial");
		temLabel1.setFont(fontLabel);
		panelHabi.add(temLabel1,c);
	    
	    c.gridy = 1;
	    c.gridx = 1;
		c.anchor = GridBagConstraints.CENTER;
	    c.insets = new Insets(0, 0, 25, 0);
	    
	    fechaI.setFont(fontLabel);
	    fechaI.setPreferredSize(new Dimension(200, 40));

	    panelHabi.add(fechaI, c);
	    
	    c.gridy = 2;
	    c.gridx = 1;
		c.anchor = GridBagConstraints.CENTER;
	    c.insets = new Insets(0, 0, 0, 0);
	    
	    temLabel1 = new JLabel("Fecha Final");
		temLabel1.setFont(fontLabel );
		panelHabi.add(temLabel1,c);
	    
	    c.gridy = 3;
	    c.gridx = 1;
	    c.ipadx = 1;
	    c.ipady = 1;
		c.anchor = GridBagConstraints.NORTH;

	    c.insets = new Insets(0, 0, 0, 0);
	    
	    fechaF.setFont(fontLabel);
	    fechaF.setPreferredSize(new Dimension(200, 40));
	    panelHabi.add(fechaF, c);
	    
	    JButton establecerFecha = new JButton("Establecer Fecha");
	    
	    establecerFecha.setBackground(Color.decode("#204473"));
	    establecerFecha.setFont(fontLabel);
	    establecerFecha.setForeground(Color.white);
	    establecerFecha.addActionListener(this);
	    establecerFecha.setPreferredSize(new Dimension(200, 40));

	    
	    c.gridy = 1;
	    c.gridx = 2;
		c.anchor = GridBagConstraints.CENTER;
	    c.insets = new Insets(0, 0, 0, 0);
	    
	    panelHabi.add(establecerFecha, c);
	    
	    JButton cambiarFecha = new JButton("Cambiar fecha");
	    
	    cambiarFecha.setBackground(Color.decode("#204473"));
	    cambiarFecha.setFont(fontLabel);
	    cambiarFecha.setForeground(Color.white);
	    cambiarFecha.addActionListener(this);
	    cambiarFecha.setPreferredSize(new Dimension(200, 40));

	    
	    
	    c.gridy = 2;
	    c.gridx = 2;
		c.anchor = GridBagConstraints.CENTER;
	    c.insets = new Insets(0, 0, 0, 0);
	    
	    panelHabi.add(cambiarFecha, c);
	    

		panelHabi.setBackground(Color.decode("#accaf2"));
		constraints.ipadx = 200;
		constraints.ipady = 200;
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.fill = GridBagConstraints.BOTH;
		constraints.anchor = GridBagConstraints.NORTHWEST;
		constraints.weighty = 0.1;
		constraints.weightx = 1;
	    
	    
	    panelDerecho.add(panelHabi, constraints);
		
		// tabla
		String[] columnas = { "Habitacion", "Huesped" };
		modeloTabla = new DefaultTableModel(columnas, 0);

		Font fontTabla = new Font("Arial", Font.BOLD, 20);

		tablaTarifas = new JTable(modeloTabla);
		tablaTarifas.setDefaultEditor(Object.class, null);
		tablaTarifas.getTableHeader().setBackground(Color.decode("#204473"));
		tablaTarifas.getTableHeader().setForeground(Color.white);
		tablaTarifas.getTableHeader().setReorderingAllowed(false);
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
		
		constraints.gridwidth = 1;
		
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
		datos[2].addKeyListener(this);

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
	    
	    JComboBox<TipoHabitacion> tiposHabi = new JComboBox<>(TipoHabitacion.values());
	    JLabel mensaje = new JLabel("Seleccione una tipo de habitacion"); 
	    JPanel panel = new JPanel();
	    panel.add(mensaje);
	    panel.add(tiposHabi);

	    JOptionPane.showOptionDialog(setTipoHabi, panel, "Seleccionar tipo Habitación", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);

	    TipoHabitacion tipoEnum = (TipoHabitacion) tiposHabi.getSelectedItem();
		
	    
	    ArrayList<Habitacion> disponibles = null;
		try {
			disponibles = windowManager.DiponiblesParaGrupoEnCurso(tipoEnum);
		} catch (Exception e) {
			if (e.getMessage().equals("No hay grupo")) {
				JOptionPane.showMessageDialog(null, "Debe establecer la Fecha");
				
			}
			return;

		}
		
		
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
	    tablaDisponibles.getTableHeader().setReorderingAllowed(false);
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
		
		boolean todas = true;
		int cantidad = 0;
	    for (Habitacion habitacion:disponibles) {
	    	int precioHabitacion;
			try {
				precioHabitacion = getPrecioHabitacionReserva(habitacion);
//				double precio = Double.parseDouble(precioHabitacion+"");
//				NumberFormat nf= NumberFormat.getInstance();
//				nf.setMaximumFractionDigits(0);
//			    String precioS = nf.format(precio);
//			    precioHabitacion = Integer.parseInt(precioS);
				String[] data = {habitacion.getId()+"",""+habitacion.getCapacidad(), ""+precioHabitacion, habitacion.getCaracteristicas()};
		    	modelodisponibles.addRow(data);
			} catch (Exception e) {
				todas = false;
				cantidad += 1;
			}
	    	
	    }
	    
	    if (!todas) {
	    	JOptionPane.showMessageDialog(null, "Algunas habitaciones no fueron mostradas por no tener tarifa", "Tarifa Faltantes", JOptionPane.ERROR_MESSAGE);;
	    } else if(disponibles.isEmpty() || disponibles.size() == cantidad) {
	    	JOptionPane.showMessageDialog(null, "No hay habitaciones disponibles para esta fecha o categoria", "No hay habitaciones", JOptionPane.ERROR_MESSAGE);;
	    	return;
		}
	    
	    JScrollPane scrollPane = new JScrollPane(tablaDisponibles);
	    selectHabitacion.add(scrollPane);
	    selectHabitacion.setVisible(true);
	    
	
	    
	}
	public int getPrecioHabitacionReserva(Habitacion habitacion) throws Exception {
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

	protected void establecerFecha() {
		try {
			fechasValidas(fechaI.getDate(),fechaF.getDate());
			fechaF.setEnabled(false);
			fechaI.setEnabled(false);
			windowManager.crearReserva(fechaI.getDate(), fechaF.getDate());
		} catch (Exception e) {
			fechaI.setDate(windowManager.getHoy());
			fechaF.setDate(windowManager.getHoy());
			JOptionPane.showMessageDialog(null, e.getMessage());
		};
	}
	

	protected void crearReserva() {
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
			establecerFecha();
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
		if(windowManager.hayReserva()) {
			Grupo grupo = windowManager.getGrupoEnCurso();
			Date dateI =  grupo.getReserva().getFechaI();
			if (dateI == null) {
				fechaI.setDate( windowManager.getHoy());
				fechaI.setEnabled(true);

			}else {
				fechaI.setDate(dateI);
				fechaI.setEnabled(false);

			}
			
			Date dateF =  grupo.getReserva().getFechaI();
			if (dateI == null) {
				fechaF.setDate( windowManager.getHoy());
				fechaF.setEnabled(true);
				
			}else {
				fechaF.setDate(dateF);
				fechaF.setEnabled(false);

			}
			
			
		}
		
		llenarTablaReserva();
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		 // TODO Auto-generated method stub
        JTextField caja = (JTextField) e.getSource();
        int numeros;
        try {
            String cadena = caja.getText().replace(",", "");
            cadena = cadena.replace(".", "");
            numeros = Integer.parseInt(cadena);
            NumberFormatter numberFormatter = new NumberFormatter();
            numberFormatter.setValueClass(Integer.class);
            numberFormatter.setMinimum(1);
            numberFormatter.setMaximum(Integer.MAX_VALUE);
            numberFormatter.setAllowsInvalid(false);
            JFormattedTextField input = new JFormattedTextField(numberFormatter);
            input.setText(numeros + "");
            String texto = input.getText();
            if (texto.length() == 0) {
                texto = caja.getText();
                texto = texto.substring(0, texto.length() - 1);
                caja.setText(texto);
            }
            else {
                caja.setText(input.getText());
            }
        } catch (NumberFormatException nfe) {
            String texto = caja.getText();
            if (texto.length() > 0) {
                texto = texto.substring(0, texto.length() - 1);
            }
            else {
            }
            caja.setText(texto);
        }
	}

}
