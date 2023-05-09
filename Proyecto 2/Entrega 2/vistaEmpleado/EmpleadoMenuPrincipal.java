package vistaEmpleado;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsConfiguration;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.plaf.basic.BasicTreeUI.TreeCancelEditingAction;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import controlador.WindowManager;
import vistaAdmin.AdminHabitacionesFrame;
import vistaAdmin.AdminRestauranteFrame;
import vistaAdmin.AdminServiciosFrame;
import vistaAdmin.AdminTarifasFrame;

public class EmpleadoMenuPrincipal extends JFrame implements ActionListener {

	protected JPanel panelIzquierdo;
	private JPanel panelDerecho;
	
	private JTable tablaHoy;
    private DefaultTableModel modeloTablaHoy;
    private JTable tablaAnual;
    private DefaultTableModel modeloTablaAnual;
	
	protected JFrame tarifasFrame;
	protected JFrame serviciosFrame;
	protected JFrame habitacionesFrame;
	protected JFrame restauranteFrame;
	protected WindowManager windowManager;
	protected JButton checkIn;
	protected JButton checkOut;

	public EmpleadoMenuPrincipal(WindowManager windowManager){
        setLayout(new BorderLayout());
		setTitle("Menu Principal");
		
		this.windowManager = windowManager;
		
		panelIzquierdo = new JPanel();
		panelIzquierdo.setPreferredSize(new Dimension(300, 0));
		setBotones();
		
        // Panel Derecho
        panelDerecho = new JPanel();
        setPanelInfo();
        
        // Agregar los paneles al JFrame
        add(panelIzquierdo, BorderLayout.WEST);
        add(panelDerecho, BorderLayout.CENTER);


		// FRAMES
		

		tarifasFrame = new EmpleadoTarifasFrame(windowManager);
		serviciosFrame = new AdminServiciosFrame(windowManager);
		habitacionesFrame = new AdminHabitacionesFrame(windowManager);
		restauranteFrame = new AdminRestauranteFrame(windowManager);
	}

	private void setPanelInfo() {
		panelDerecho.setLayout(new BorderLayout());
		panelDerecho.setBackground(Color.decode("#ACCAF2"));
		JPanel check = new JPanel();
		check.setBackground(Color.decode("#7E8C69"));
		check.setPreferredSize(new Dimension(0,200));
		check.setLayout(new FlowLayout(FlowLayout.CENTER, 300, 80));
	    Font fontButton = new Font("Arial", Font.BOLD, 16);

		checkIn = new JButton("Check-In");
		checkIn.setPreferredSize(new Dimension(200,50));
		checkIn.setFont(fontButton);
		checkIn.setBackground(Color.decode("#D0ECF2"));
		checkIn.addActionListener(this);
		
		checkOut = new JButton("Check-Out");
		checkOut.setPreferredSize(new Dimension(200,50));
		checkOut.setFont(fontButton);
		checkOut.setBackground(Color.decode("#D0ECF2"));
		checkOut.addActionListener(this);

		
		check.add(checkIn);
		check.add(checkOut);
		
		panelDerecho.add(check, BorderLayout.SOUTH);
		
		JPanel PanelOcupacion = new JPanel();
		PanelOcupacion.setBorder(BorderFactory.createEmptyBorder(100, 100, 100, 100));
		PanelOcupacion.setBackground(Color.decode("#ACCAF2"));
		GridBagLayout gridbag = new GridBagLayout();
	    GridBagConstraints constraints = new GridBagConstraints();
	    PanelOcupacion.setLayout(gridbag);
	    
	    JLabel OHoy = new JLabel();
	    OHoy.setText("Ocupacion hoy: ");
	    OHoy.setFont(new Font("Times New Roman", 1, 20));
	    constraints.insets = new Insets(50, 0, 10, 0);
	    constraints.anchor = GridBagConstraints.WEST;
	    constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridheight = 1;
        constraints.gridwidth = 1;
        //constraints.weightx = 1;
	    PanelOcupacion.add(OHoy, constraints);
	    
	    //Creacion de la tabla servicios
  		String[] columnas = {""}; //Nombre de las columnas
        modeloTablaHoy = new DefaultTableModel(columnas, 0);
          
        //Filas de la tabla
  	    
  	    //Diseño de la tabla
          tablaHoy = new JTable(modeloTablaHoy);
          tablaHoy.getTableHeader().setBackground(Color.decode("#204473"));
          tablaHoy.getTableHeader().setForeground(Color.white);
          tablaHoy.getTableHeader().setFont(new Font("Times New Roman", 1, 30));
          tablaHoy.setFont(new Font("Times New Roman", 1, 20));
          tablaHoy.setRowHeight(70);
          tablaHoy.setEnabled(false);

          DefaultTableCellRenderer modelocentrar = new DefaultTableCellRenderer();
          modelocentrar.setHorizontalAlignment(SwingConstants.CENTER);

          
          //tablaHoy.getColumnModel().getColumn(0).setCellRenderer(modelocentrar);
          for (int i = 0; i< 1; i++) {
              tablaHoy.getColumnModel().getColumn(i).setCellRenderer(modelocentrar);
              tablaHoy.getColumnModel().getColumn(i).setCellEditor(null);
          }
          //Tamaño y ubicacion de la tabla en el panel
          constraints.gridx = 0;
          constraints.insets = new Insets(0, 0, 50, 0);
          constraints.gridy = 1;
          constraints.ipady = 20;
          constraints.ipadx = 400;
          constraints.gridheight = 1;
          constraints.gridwidth = 1;
          //constraints.weightx = 1;
          //constraints.weighty = 0.1;

          PanelOcupacion.add(tablaHoy, constraints);
          
        JLabel OAnual = new JLabel();
        OAnual.setText("Ocupacion anual: ");
        OAnual.setFont(new Font("Times New Roman", 1, 20));
  	    constraints.insets = new Insets(0, 0, 10, 0);
  	    constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.ipady = 0;
        constraints.ipadx = 0;
        constraints.gridheight = 1;
        constraints.gridwidth = 1;
        //constraints.weightx = 1;
  	    PanelOcupacion.add(OAnual, constraints);
  	    
  	//Creacion de la tabla servicios
  		String[] columnasAnual = {"", "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"}; //Nombre de las columnas
          modeloTablaAnual = new DefaultTableModel(columnasAnual, 0);
          
          //Filas de la tabla
          String[] fila1 = {"Lunes"};
          String[] fila2 = {"Martes"};
          String[] fila3 = {"Miercoles"};
          String[] fila4 = {"Jueves"};
          String[] fila5 = {"Viernes"};
          String[] fila6 = {"Sabado"};
          String[] fila7 = {"Domingo"};
          modeloTablaAnual.addRow(fila1);
          modeloTablaAnual.addRow(fila2);
          modeloTablaAnual.addRow(fila3);
  	      modeloTablaAnual.addRow(fila4);
  	      modeloTablaAnual.addRow(fila5);
  	  	  modeloTablaAnual.addRow(fila6);
  	  	  modeloTablaAnual.addRow(fila7);
  	    
  	    //Diseño de la tabla
          tablaAnual = new JTable(modeloTablaAnual);
          tablaAnual.getTableHeader().setBackground(Color.decode("#204473"));
          tablaAnual.getTableHeader().setForeground(Color.white);
          tablaAnual.getTableHeader().setReorderingAllowed(false);
          tablaAnual.getTableHeader().setFont(new Font("Times New Roman", 1, 15));
          tablaAnual.setFont(new Font("Times New Roman", 1, 15));
          tablaAnual.setRowHeight(28);
          tablaAnual.setEnabled(false);

          DefaultTableCellRenderer modelocentrarAnual = new DefaultTableCellRenderer();
          modelocentrarAnual.setHorizontalAlignment(SwingConstants.CENTER);


          for (int i = 0; i< 12; i++) {
              tablaAnual.getColumnModel().getColumn(i).setCellRenderer(modelocentrar);
              tablaAnual.getColumnModel().getColumn(i).setCellEditor(null);
          }

          JScrollPane scrollPanelAnual = new JScrollPane(tablaAnual);
          //scrollPanelAnual.setPreferredSize(new Dimension(400, 650));
          //Tamaño y ubicacion de la tabla en el panel
          constraints.gridx = 0;
          constraints.insets = new Insets(0, 0, 0, 0);
          constraints.gridy = 3;
          constraints.ipady = 200;
          constraints.ipadx = 1000;
          constraints.gridheight = 1;
          constraints.gridwidth = 1;
          //constraints.weightx = 1;
          //constraints.weighty = 0.1;

          PanelOcupacion.add(scrollPanelAnual, constraints);
	    
		panelDerecho.add(PanelOcupacion, BorderLayout.CENTER);
	}

	protected void setBotones() {
		// Configuracion General
		GridBagLayout gridbag = new GridBagLayout();
	    GridBagConstraints constraints = new GridBagConstraints();
	    panelIzquierdo.setLayout(gridbag);
	    panelIzquierdo.setBackground(Color.decode("#204473"));
	    
	    JButton[] opciones = new JButton[6];
	    String[] nombres = {"Tarifas", 
	    					"Servicios", 
	    					"Habitaciones", 
	    					"Restaurante", 
	    					"Reservas",
	    					"Cerrar sesion"};
	    
	    Font fontButton = new Font("Arial", Font.BOLD, 16);
	    panelIzquierdo.setBorder(BorderFactory.createEmptyBorder(100, 20, 100, 20));

	    for (int i = 0; i<6; i++) {
	    	opciones[i] = new JButton(nombres[i]);
	    	opciones[i].setBackground(Color.decode("#ACCAF2"));
	    	opciones[i].addActionListener(this);
	    	opciones[i].setPreferredSize(new Dimension(200, 50));
	    	opciones[i].setFont(fontButton);
	    	constraints.gridy = i;
	    	constraints.weighty = 1;
	    	panelIzquierdo.add(opciones[i],constraints);
	    }
		
	}
	
	public void checkIn() {
		UIManager.put("OptionPane.minimumSize",new Dimension(400,200));
		JOptionPane.showInputDialog(null, "Ingrese el número de su grupo para el check-in", "Check-in", JOptionPane.PLAIN_MESSAGE);
	}
	
	public void checkOut() {
		UIManager.put("OptionPane.minimumSize",new Dimension(400,200));
		JOptionPane.showInputDialog(null, "Ingrese el número de su grupo para el check-out", "Check-out", JOptionPane.PLAIN_MESSAGE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	
		switch (e.getActionCommand()) {
		case "Tarifas":
			windowManager.mostraVentana(tarifasFrame);

			break;
		case "Servicios":

			windowManager.mostraVentana(serviciosFrame);
			break;
		case "Habitaciones":

			windowManager.mostraVentana(habitacionesFrame);
			break;
		case "Restaurante":

			windowManager.mostraVentana(restauranteFrame);
			break;
		case "Reservas":
			
			break;
		case "Cerrar sesion":
			windowManager.cerrarSesion();;

			break;
			
		case "Check-In":
			checkIn();
			break;
			
		case "Check-Out":
			checkOut();
			break;

		default:
			break;
		}

	}


}
