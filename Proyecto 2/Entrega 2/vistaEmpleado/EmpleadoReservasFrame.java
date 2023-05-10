package vistaEmpleado;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import controlador.WindowManager;
import vistaAdmin.FrameBaseInfo;

public class EmpleadoReservasFrame extends FrameBaseInfo {

	private DefaultTableModel modeloTabla;
	private JTable tablaTarifas;
	private JButton crearReserva;
	private JButton cancelarReserva;
	private JTextField buscarJTextField;

	public EmpleadoReservasFrame(WindowManager windowManager) {
		super(windowManager);
		panelIzquierdo.setPreferredSize(new Dimension(0, 200));
		panelVolver.setPreferredSize(new Dimension(450 , 200));
		panelIzquierdo.add(panelCrear, BorderLayout.CENTER);
        panelIzquierdo.add(panelVolver, BorderLayout.WEST);
		add(panelIzquierdo, BorderLayout.SOUTH);
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

		JPanel panelBuscar = new JPanel();
		panelBuscar.setBackground(Color.decode("#accaf2"));
		panelBuscar.setPreferredSize(new Dimension(100,100));
		
		panelBuscar.setLayout(gridbag);
		  
		JLabel titulo = new JLabel("Numero de reserva");
		titulo.setFont(fontLabels );
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.insets = new Insets(30, 0, 0, 0);
	
		
		panelBuscar.add(titulo,constraints);

		
		buscarJTextField = new JTextField();
		buscarJTextField.setPreferredSize(new Dimension(200, 40));

		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.weighty = 1;
		constraints.insets = new Insets(0, 0, 0, 0);

	
		
		panelBuscar.add(buscarJTextField,constraints);
		
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
		
		
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.ipadx = 300;
		constraints.ipady = 100;
		constraints.anchor = GridBagConstraints.WEST;
	    constraints.fill = GridBagConstraints.BOTH;

		
		panelDerecho.add(panelBuscar,constraints);
		
		
		
		// panel datos
		JPanel panelDatos = new JPanel();

		
		panelDatos.setBackground(Color.decode("#B2BBA4"));		
		panelDatos.setLayout(gridbag);
		constraints = new GridBagConstraints();
		
		
		datos = new JTextField[6];
		JLabel[] labels = new JLabel[6];
		
		for (int i = 0; i < datos.length; i++) {
			datos[i] = new JTextField();
			datos[i].setForeground(Color.black);
			datos[i].setEditable(false);
			datos[i].setPreferredSize(new Dimension(200, 30));
			
			constraints.gridx = (i/2);
			constraints.gridy = ((i%2)*2)+2;
			constraints.gridwidth = 1;
			constraints.gridheight = 1;
			constraints.weighty = 1;
			constraints.weightx = 1;
			panelDatos.add(datos[i],constraints);
		}
		
		String[] nombres = {"Nombre:​","No. de identificación:​", "Correo:​", "Teléfono:​", "Fecha inicio:​​", "Fecha de fin:​​"};
		for (int i = 0; i < labels.length; i++) {
			labels[i] = new JLabel(nombres[i]);
			labels[i].setFont(fontLabels);
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

		
		panelDatos.add(tituloDatos,constraints);

		
		JLabel tituloFecha = new JLabel("Información Reserva");
		tituloFecha.setFont(font);

		constraints.gridx = 2;
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
	
		constraints.weighty = 1;
		constraints.weightx = 1;

		
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
        System.out.println(panelDerecho.getWidth());
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

	@Override
	protected void actionPerformedFrame(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "Crear Reserva":
			windowManager.mostraVentana(new EmpleadoCrearReservasFrame(windowManager));
			break;
		case "Cancelar Reserva":
			
			break;
		default:
			break;
		}

	}

}
