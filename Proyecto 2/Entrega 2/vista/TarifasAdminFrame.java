package vista;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.awt.Panel;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class TarifasAdminFrame extends JFrame {
	private JPanel panelVolver;
	private JPanel panelCrear;
	private JPanel panelDerecho;
	private JPanel panelIzquierdo;
	private JButton volverButton;
	private JTextField[] datos;
	private JButton addTarifa;
	private JTable tablaTarifas;
    private DefaultTableModel modeloTabla;
	
	public TarifasAdminFrame() {

		
		//Panel Izquierdo
		
        panelIzquierdo = new JPanel();
        panelCrear = new JPanel();
        panelVolver = new JPanel();

        panelIzquierdo.setLayout(new BorderLayout());

        panelIzquierdo.add(panelCrear, BorderLayout.CENTER);
        panelIzquierdo.add(panelVolver, BorderLayout.SOUTH);


        panelVolver.setPreferredSize(new Dimension(0, 200));
        panelIzquierdo.setPreferredSize(new Dimension(450, 0));

		setPanelVolver();
		setPanelCrear();
        
        // Panel Derecho
        panelDerecho = new JPanel();
        setPanelInfo();
        
        // Agregar los paneles al JFrame
        add(panelIzquierdo, BorderLayout.WEST);
        add(panelDerecho, BorderLayout.CENTER);

		// configuraciones generales
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		//setResizable(false);
		setExtendedState(MAXIMIZED_BOTH);
		setVisible(true);
		setResizable(false);
		

		
		
	}

	private void setPanelInfo() {
		// TODO Auto-generated method stub
		panelDerecho.setBackground(Color.decode("#B2BBA4"));
		GridBagLayout gridbag = new GridBagLayout();
	    GridBagConstraints constraints = new GridBagConstraints();
	    panelDerecho.setLayout(gridbag);
	    
	    JPanel panelBuscar = new JPanel();
	    
	    constraints.weighty=1;
	    constraints.ipady = 250;
	    constraints.ipadx = 1000;
	    panelDerecho.add(panelBuscar, constraints);
	    
	    // añadir al panel

	    panelBuscar.setLayout(gridbag);
	    
	    JTextField fechaITextField = new JTextField("Fecha Incial");
	    
	    JTextField fechaFTextField = new JTextField("Fecha Final");
	    JButton buscarButton = new JButton("Buscar Tarifa");
	    constraints.gridx = 0;
	    constraints.gridy = 0;
	    constraints.weighty=1;
	    constraints.ipady = 0;
	    constraints.ipadx = 0;
	    panelBuscar.add(fechaITextField, constraints);
	    
	    constraints.gridx = 0;
	    constraints.gridy = 1;
	    panelBuscar.add(fechaFTextField, constraints);
	    
	    
	    
	    
	    
	    
	    // tabla
	    String[] columnas = {"Fecha Inicial", "Fecha Final", "Precio", "Tipo"};
        modeloTabla = new DefaultTableModel(columnas, 0);
//        String[] fila1 = {"01/01/2022", "31/01/2022", "$100", "Tipo 1"};
//        String[] fila2 = {"01/02/2022", "28/02/2022", "$150", "Tipo 2"};
//        modeloTabla.addRow(fila1);
//        modeloTabla.addRow(fila2);        
        
        tablaTarifas = new JTable(modeloTabla);
        tablaTarifas.getTableHeader().setBackground(Color.decode("#204473"));
        tablaTarifas.getTableHeader().setForeground(Color.white);
        tablaTarifas.setBackground(Color.decode("#B2BBA4"));
        tablaTarifas.setRowHeight(50);
        tablaTarifas.setEnabled(false);
        
        DefaultTableCellRenderer modelocentrar = new DefaultTableCellRenderer();
        modelocentrar.setHorizontalAlignment(SwingConstants.CENTER);
        
        
        for (int i = 0; i< 4; i++) {
            tablaTarifas.getColumnModel().getColumn(i).setCellRenderer(modelocentrar);
        }
  
       
        JScrollPane scrollPanel = new JScrollPane(tablaTarifas);
        scrollPanel.setBackground(Color.decode("#B2BBA4"));
	    constraints.gridx = 0;
	    constraints.ipady = 450;
	    constraints.ipadx = 1000;
	    
	    panelDerecho.add(scrollPanel, constraints);
	}
	
	public void addTarifaTabla(String fechaI, String fechaF, String precio, String Tipo) {
      String[] fila = {fechaI, fechaF, precio, Tipo};
      modeloTabla.addRow(fila);

	}

	private void setPanelCrear() {
		   // Configuracion General
		
		GridBagLayout gridbag = new GridBagLayout();
	    GridBagConstraints constraints = new GridBagConstraints();
	    
	    panelCrear.setLayout(gridbag);

		panelCrear.setBorder(BorderFactory.createEmptyBorder(20, 100, 20, 100));
		panelCrear.setBackground(Color.decode("#204473"));
		
		datos = new JTextField[4];
		String[] titulos = {"Tipo", "Precio", "Fecha Inicial", "Fecha Inicial"};
		
		Font fontLabel = new Font("Arial", Font.BOLD, 20);

		
		for( int i = 0; i < 4; i++) {
			JTextField campo = new JTextField();
			campo.setPreferredSize(new Dimension(200, 30));
			
			JLabel titulo = new JLabel(titulos[i]);
			titulo.setFont(fontLabel);
			titulo.setForeground(Color.WHITE);
			
			datos[i] = campo;
			
			constraints.gridx = 0;
			constraints.gridy = (i*2);
			constraints.fill = GridBagConstraints.HORIZONTAL;
			constraints.weighty=10;
			panelCrear.add(titulo, constraints);
			
			constraints.gridy = (i*2)+1 ;
			constraints.weighty=10;
			constraints.fill = GridBagConstraints.HORIZONTAL;
			panelCrear.add(campo, constraints);
		}
	    
		panelCrear.add(new JLabel());
		
		Font fontBoton = new Font("Arial", Font.BOLD, 20);
		addTarifa =  new BotonRedondeado("Crear Tarifa", 200, 75, 30, Color.decode("#ACCAF2"));
		addTarifa.setFont(fontBoton);
		
		constraints.gridy = 9 ;
		constraints.gridy = GridBagConstraints.PAGE_END;
		panelCrear.add(addTarifa,constraints);
		
	    
	}

	private void setPanelVolver() {
		// Configuracion General
	    panelVolver.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 65));
	    panelVolver.setBackground(Color.decode("#7E8C69"));

	    // Crear Boton redondeado
	    Font font = new Font("Arial", Font.BOLD, 20);
	    
	    volverButton = new BotonRedondeado("Volver", 200, 75, 30, Color.decode("#D0ECF2"));
	    volverButton.setFont(font);
	    
	    // Añadirlo al Panel
	    panelVolver.add(volverButton);
	}


}
