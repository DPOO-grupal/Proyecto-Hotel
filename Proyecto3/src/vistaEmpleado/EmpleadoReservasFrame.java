package vistaEmpleado;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
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

import org.jdesktop.swingx.JXDatePicker;

import controlador.WindowManager;
import modelo.Grupo;
import modelo.Habitacion;
import modelo.Huesped;
import modelo.Reserva;
import modelo.Servicio;
import modelo.TipoHabitacion;
import vistaAdmin.FrameBaseInfo;

public class EmpleadoReservasFrame extends FrameBaseInfo implements MouseListener, KeyListener{

	private DefaultTableModel modeloTabla;
	private JTable tablaTarifas;
	private JButton crearReserva;
	private JButton cancelarReserva;
	private JFrame verReservasFrame;
	protected JFrame crearReservasFrame;
	protected int numeroReserva;
	protected JComponent idGrupo;
	protected JPanel panelBuscar;


	public EmpleadoReservasFrame(WindowManager windowManager) {
		super(windowManager);
		panelIzquierdo.setPreferredSize(new Dimension(0, 200));
		panelVolver.setPreferredSize(new Dimension(450 , 200));
		panelIzquierdo.add(panelCrear, BorderLayout.CENTER);
        panelIzquierdo.add(panelVolver, BorderLayout.WEST);
		add(panelIzquierdo, BorderLayout.SOUTH);
		crearReservasFrame = new EmpleadoCrearReservasFrame(windowManager);
	}

	@Override
	protected void setPanelInfo() {
		// TODO Auto-generated method stub
		panelDerecho.setBackground(Color.decode("#B2BBA4"));
		GridBagLayout gridbag = new GridBagLayout();
		GridBagConstraints constraints = new GridBagConstraints();
		panelDerecho.setLayout(gridbag);
	    Font font = new Font("Arial", Font.BOLD, 20);
		Font fontLabels = new Font("Arial", Font.BOLD, 15);

		panelBuscar = new JPanel();
		panelBuscar.setBackground(Color.decode("#accaf2"));
		panelBuscar.setPreferredSize(new Dimension(150,100));
		panelBuscar.setBorder(BorderFactory.createEmptyBorder(10, 100, 10, 100));
		
		panelBuscar.setLayout(gridbag);
		  
		JLabel titulo = new JLabel("Numero de reserva");
		titulo.setFont(fontLabels );
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.weighty = 1;
		constraints.weightx = 1;
		constraints.insets = new Insets(0, 0, 0, 0);
	
		
		panelBuscar.add(titulo,constraints);
  
		
		idGrupo = new JTextField();
		idGrupo.setPreferredSize(new Dimension(10, 40));
		idGrupo.addKeyListener(this);
		constraints.gridx = 0;
		constraints.gridy = 1;
	
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.insets = new Insets(1, 0, 1, 0);

		panelBuscar.add(idGrupo,constraints);
		
		JButton buscarButton = new JButton("Buscar Reserva");
		buscarButton.setPreferredSize(new Dimension(200, 40));
		buscarButton.setBackground(Color.decode("#204473"));
		buscarButton.setFont(font);
		buscarButton.setForeground(Color.white);
		buscarButton.addActionListener(this);
		
		constraints.weighty = 1;
		constraints.gridx = 0;
		constraints.gridy = 2;
		
		panelBuscar.add(buscarButton,constraints);
		
		JButton verReservas = new JButton("Ver reservas");
		verReservas.setPreferredSize(new Dimension(200, 40));
		verReservas.setBackground(Color.decode("#204473"));
		verReservas.setFont(font);
		verReservas.setForeground(Color.white);
		verReservas.addActionListener(this);
		
		constraints.weighty = 1;
		constraints.gridx = 0;
		constraints.gridy = 3;
		
		panelBuscar.add(verReservas,constraints);
		
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.ipadx = 1;
		constraints.ipady = 1;
		constraints.anchor = GridBagConstraints.NORTHWEST;
	    constraints.fill = GridBagConstraints.BOTH;
	    constraints.insets = new Insets(0, 0, 0, 0);
		
		panelDerecho.add(panelBuscar,constraints);
		
		
		
		// panel datos
		JPanel panelDatos = new JPanel();
		panelDatos.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		panelDatos.setBackground(Color.decode("#B2BBA4"));		
		panelDatos.setLayout(gridbag);
		constraints = new GridBagConstraints();
		
		
		datos = new JTextField[6];
		JLabel[] labels = new JLabel[6];
		
		for (int i = 0; i < datos.length; i++) {
			datos[i] = new JTextField();
			datos[i].setForeground(Color.black);
			datos[i].setEditable(false);
			datos[i].setPreferredSize(new Dimension(1000, 30));
			constraints.anchor = GridBagConstraints.CENTER;
		    constraints.fill = GridBagConstraints.BOTH;
			constraints.insets = new Insets(1, 20, 1, 20);
			constraints.gridx = (i/2);
			constraints.gridy = ((i%2)*2)+2;
			constraints.gridwidth = 1;
			constraints.gridheight = 1;
			constraints.weighty = 1;
			constraints.weightx = 1;
			constraints.ipadx = 1;
			constraints.ipady = 1;
			panelDatos.add(datos[i],constraints);
		}
		
		String[] nombres = {"Nombre:​","No. de identificación:​", "Correo:​", "Teléfono:​", "Fecha inicio:​​", "Fecha de fin:​​"};
		for (int i = 0; i < labels.length; i++) {
			labels[i] = new JLabel(nombres[i]);
			labels[i].setFont(fontLabels);
		    constraints.fill = GridBagConstraints.NONE;
			constraints.insets = new Insets(0, 0, 0, 0);
			constraints.gridx = (i/2);
			constraints.gridy = ((i%2)*2)+1;
			constraints.gridwidth = 1;
			constraints.gridheight = 1;
			constraints.weighty = 1;
			constraints.weightx = 1;
			panelDatos.add(labels[i],constraints);
		}
		
		JLabel tituloDatos = new JLabel("Datos del líder del grupo​");
		tituloDatos.setFont(font);
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 2;
		constraints.gridheight = 1;  
	
		constraints.weighty = 1;
		constraints.weightx = 1;
		constraints.insets = new Insets(5, 20, 5, 20);

		
		panelDatos.add(tituloDatos,constraints);

		
		JLabel tituloFecha = new JLabel("Información Reserva");
		tituloFecha.setFont(font);

		constraints.gridx = 2;
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
	
		constraints.weighty = 1;
		constraints.weightx = 1;
		constraints.insets = new Insets(0, 0, 0, 0);
 
		
		panelDatos.add(tituloFecha,constraints);
		
		
		constraints.gridx = 1;
		constraints.gridy = 0;
		constraints.ipadx = 300;
		constraints.ipady = 100;
		constraints.gridwidth = 1;
		constraints.anchor = GridBagConstraints.CENTER;
	    constraints.fill = GridBagConstraints.BOTH;
	    
		
		panelDerecho.add(panelDatos,constraints);
		
		constraints = new GridBagConstraints();
	    
	    
	    // tabla
	    String[] columnas = {"Huespedes", "Habitaciones", "Servicios Consumindos"};
        modeloTabla = new DefaultTableModel(columnas, 0);
       
        Font fontTabla= new Font("Arial", Font.BOLD, 20);
        tablaTarifas = new JTable(modeloTabla);
        tablaTarifas.getTableHeader().setBackground(Color.decode("#204473"));
        tablaTarifas.getTableHeader().setForeground(Color.white);
        tablaTarifas.getTableHeader().setReorderingAllowed(false);
        tablaTarifas.getTableHeader().setFont(fontTabla);
        tablaTarifas.setBackground(Color.decode("#B2BBA4"));
        tablaTarifas.setRowHeight(50);
        tablaTarifas.setEnabled(false);
        
        DefaultTableCellRenderer modelocentrar = new DefaultTableCellRenderer();
        modelocentrar.setHorizontalAlignment(SwingConstants.CENTER);
        modelocentrar.setFont(fontTabla);
        
        for (int i = 0; i< 3; i++) {
            tablaTarifas.getColumnModel().getColumn(i).setCellRenderer(modelocentrar);
            tablaTarifas.getColumnModel().getColumn(i).setCellEditor(null);
        }
  
       
        JScrollPane scrollPanel = new JScrollPane(tablaTarifas);
        scrollPanel.setPreferredSize(new Dimension(500, 500));
        scrollPanel.setBackground(Color.decode("#B2BBA4"));
        constraints.gridx = 0;
	    constraints.gridy = 1;
	    constraints.gridheight = 1;
	    constraints.gridwidth = 3;
	    constraints.fill = GridBagConstraints.BOTH;
	    constraints.ipadx = 100;
	    constraints.ipady = 400 ;
	    constraints.weightx = 1;
	    
	    panelDerecho.add(scrollPanel, constraints);
	


	}

	@Override
	protected void setPanelCrear() {
		// Configuracion General
		GridBagLayout gba = new GridBagLayout();
	    GridBagConstraints c = new GridBagConstraints();
	    
	    Font font = new Font("Arial", Font.BOLD, 20);
	    
	    panelCrear.setLayout(gba);
	    panelCrear.setBackground(Color.decode("#204473"));
	    
	    c.gridx = 0;
	    c.weightx = 1;
	    c.insets = new Insets(0, 100, 0, 0);
	    crearReserva = new JButton("Crear Reserva");
	    crearReserva.setPreferredSize(new Dimension(200, 60));
	    crearReserva.setBackground(Color.decode("#D0ECF2"));
	    crearReserva.setFont(font);
	    crearReserva.addActionListener(this);
	    panelCrear.add(crearReserva,c);

	    c.gridx = 1;
	    c.insets = new Insets(0, 0, 0, 100);

	    cancelarReserva = new JButton("Cancelar Reserva");
	    cancelarReserva.setPreferredSize(new Dimension(200, 60));
	    cancelarReserva.setBackground(Color.decode("#D0ECF2"));
	    cancelarReserva.setFont(font);
	    cancelarReserva.addActionListener(this);
	    panelCrear.add(cancelarReserva,c);
	    


	}
	
	private void verReservas() {
		
		JFrame selectFechas = new JFrame();

	    // Crear un JXDatePicker con la fecha actual
	    
		JXDatePicker fechaIJX = new JXDatePicker(new Date());
		JXDatePicker fechaFJX = new JXDatePicker(new Date());

	    JLabel mensaje = new JLabel("Seleccione un rango de fechas para la consulta"); 
	    JPanel panel = new JPanel();
	    
	    panel.add(mensaje);
	    panel.add(fechaIJX);
	    panel.add(fechaFJX);

	    JOptionPane.showOptionDialog(selectFechas, panel, "Seleccionar Rango", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);

	    try {
			fechasValidas(fechaIJX.getDate(), fechaFJX.getDate());
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
			return;

		}
	    
	    HashMap<Integer, Grupo> reservas = null;
	    
	    
		try {
			
			
			reservas = windowManager.mostrarReservas(fechaIJX.getDate(), fechaFJX.getDate());
			
			
		    verReservasFrame = new JFrame();
		    verReservasFrame.setSize(new Dimension(700,500));
		    verReservasFrame.setLocale(null);
		    String[] datosReserva = {"Identificador de reserva","Nombre lider grupo", "Numero de documento lider Grupo"};
		    
		    DefaultTableModel modelodisponibles = new DefaultTableModel(datosReserva, reservas.size());
		    
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
		    tablaDisponibles.setName("TablaReservas");
		    
			DefaultTableCellRenderer modelocentrar = new DefaultTableCellRenderer();
			modelocentrar.setHorizontalAlignment(SwingConstants.CENTER);
			modelocentrar.setFont(fontTabla);
			modelocentrar.setBackground(Color.white);
			
			for (int i = 0; i < datosReserva.length; i++) {
				tablaDisponibles.getColumnModel().getColumn(i).setCellRenderer(modelocentrar);
				tablaDisponibles.getColumnModel().getColumn(i).setCellEditor(null);
			}
		    
			modelodisponibles.getDataVector().removeAllElements();
			modelodisponibles.fireTableDataChanged();
			
		    for (Grupo grupo :reservas.values()) {
		    	String[] data = {grupo.getId()+"",""+grupo.getLider().getNombre(), ""+grupo.getLider().getDocumento()};
		    	modelodisponibles.addRow(data);
		    }
		    
		    JScrollPane scrollPane = new JScrollPane(tablaDisponibles);
		    verReservasFrame.add(scrollPane);
		    verReservasFrame.setVisible(true);
	    
		} catch (Exception e) {
			if (e.getMessage().equals("No hay reservas en ese rango")) {
				JOptionPane.showMessageDialog(null, "No hay reservas en ese rango");
			}else {
				e.getStackTrace();
			}

		}
		
	}

	private void cancelarReserva() {
		try {
			windowManager.cancelarReserva(numeroReserva);
			resetDatos();
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

		}
		
	}

	
	protected void llenarDatosReserva(int parseInt) {
		modeloTabla.getDataVector().removeAllElements();
		modeloTabla.fireTableDataChanged();
		
		Grupo grupo;
		try {
			grupo = windowManager.getGrupo(parseInt);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			return;
		}		
		Huesped huesped = grupo.getLider();
		Reserva reserva = grupo.getReserva();
		datos[0].setText(huesped.getNombre());
		datos[1].setText(huesped.getDocumento());
		datos[2].setText(huesped.getEmail());
		datos[3].setText(huesped.getTelefono());
		datos[4].setText(windowManager.formatoFecha(reserva.getFechaI()));
		datos[5].setText(windowManager.formatoFecha(reserva.getFechaF()));
		
		ArrayList<Huesped> huespedes = grupo.getHuespedes();
		ArrayList<Integer> habitaciones = grupo.getListaHabitaciones();
		Object[] servicios =  grupo.getListaServicios().values().toArray();
		
		int numFilas = huespedes.size();
		
		if (numFilas <habitaciones.size()) {
			numFilas = habitaciones.size();
		} else if(numFilas <servicios.length) {
			numFilas = servicios.length;
		}
		
		modeloTabla.getDataVector().removeAllElements();
		modeloTabla.fireTableDataChanged();
		
		for (int i = 0; i <numFilas; i++) {
			String huespedString = "";
			String habitacion = "";
			String servicio = "";
			
			if (huespedes.size()>i) {
				huespedString = huespedes.get(i).getNombre();
			}
			
			if (habitaciones.size()>i) {
				habitacion = habitaciones.get(i)+"";
			}
			if (servicios.length>i) {
				servicio = servicios[i]+"";
			}
			
			String[] fila = {huespedString, habitacion, servicio};
			modeloTabla.addRow(fila);			
			
		}
		
		
	}
	@Override
	protected void actionPerformedFrame(ActionEvent e) {
		System.out.println("EmpleadoReservasFrame.actionPerformedFrame()");
		switch (e.getActionCommand()) {
		case "Crear Reserva":
			System.out.println("Crear reserva");
			((EmpleadoCrearReservasFrame) crearReservasFrame).estadoReserva();
			windowManager.mostraVentana(crearReservasFrame);
			break;
		case "Cancelar Reserva":
			cancelarReserva();
			break;
		case "Ver reservas":
			verReservas();
			break;
		case "Buscar Reserva":
			buscarReserva();
		default:
			break;
		}

	}

	protected void buscarReserva() {
		try {
			int grupo = Integer.parseInt(((JTextField) idGrupo).getText().replace(".", "").replace(",", ""));
			numeroReserva = grupo;
			llenarDatosReserva(grupo);
		}catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Ingrese un numero valido", "Error datos", JOptionPane.ERROR_MESSAGE);;
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		JTable table = (JTable) e.getSource();
		if (table.getName().equals("TablaReservas")) {
			llenarDatosReserva(Integer.parseInt((String) table.getValueAt(table.getSelectedRow(), 0)));
			verReservasFrame.dispose();
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

	@Override
	public void resetDatos() {
		modeloTabla.getDataVector().removeAllElements();
		modeloTabla.fireTableDataChanged();
		

		datos[0].setText("");
		datos[1].setText("");
		datos[2].setText("");
		datos[3].setText("");
		datos[4].setText("");
		datos[5].setText("");
		
		modeloTabla.getDataVector().removeAllElements();
		modeloTabla.fireTableDataChanged();
		
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
		
	}


}
