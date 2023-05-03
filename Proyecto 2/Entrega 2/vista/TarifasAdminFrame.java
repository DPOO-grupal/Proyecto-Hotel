package vista;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Date;

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




public class TarifasAdminFrame extends FrameBaseInfo {

	private JTable tablaTarifas;
    private DefaultTableModel modeloTabla;
    
	
	public TarifasAdminFrame() {

		super();
		
	}

	protected void setPanelInfo() {
		// TODO Auto-generated method stub
		panelDerecho.setBackground(Color.decode("#B2BBA4"));
		GridBagLayout gridbag = new GridBagLayout();
	    GridBagConstraints constraints = new GridBagConstraints();
	    panelDerecho.setLayout(gridbag);
	    
	    JPanel panelBuscar = new JPanel();	    
	    // añadir al panel
	    GridBagLayout gba = new GridBagLayout();
	    GridBagConstraints c = new GridBagConstraints();
	    
	    Font fontLabel = new Font("Arial", Font.BOLD, 16);
	    
	    panelBuscar.setLayout(gba);
	    
	    
	    JButton buscarButton = new JButton("Buscar Tarifa");
	    
//	    JXDatePicker fechaI = new JXDatePicker(new Date());
//	    JXDatePicker fechaF = new JXDatePicker(new Date());
//	    
//	    c.gridx = 0;
//	    c.gridy = 0;
//	    
//	    c.ipady = 20;
//	    c.ipadx = 100;
//	    
//	    c.weighty = 0.5;
//	    c.weightx = 1;
//	    
//	    
//	    JLabel temLabel = new JLabel("Fecha Incial");
//		temLabel.setFont(fontLabel );
//	    panelBuscar.add(temLabel,c);
//	    
//	    c.gridy = 1;
//	    c.weighty = 0;
//
//	    fechaI.setFont(fontLabel);
//	    panelBuscar.add(fechaI, c);
//	    
//	    c.gridy = 3;
//	    c.weighty = 0.5;
//	    temLabel = new JLabel("Fecha Final");
//		temLabel.setFont(fontLabel );
//	    panelBuscar.add(temLabel,c);
//	    
//	    c.gridy = 4;
//	    c.weighty = 0;
//	    fechaF.setFont(fontLabel);
//	    panelBuscar.add(fechaF, c);
//	    	    
//	    c.gridx = 2;
//	    c.gridy = 1;
//	    
//	    c.gridheight = 3;
//	    c.gridwidth = 1;
//	    
//	    c.ipadx = 100;
//	    c.ipady = 40;
//	    
//
//	    panelBuscar.add(buscarButton, c);
//	    
//	    
//	    constraints.weighty=10;
//	    constraints.ipady = 100;
//	    constraints.ipadx = 900;
//	    panelDerecho.add(panelBuscar, constraints);
	    
	    
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

	protected void setPanelCrear() {
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
		addDatos =  new BotonRedondeado("Crear Tarifa", 200, 75, 30, Color.decode("#ACCAF2"));
		addDatos.setFont(fontBoton);
		
		constraints.gridy = 9 ;
		constraints.gridy = GridBagConstraints.PAGE_END;
		panelCrear.add(addDatos,constraints);
		
	    
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
