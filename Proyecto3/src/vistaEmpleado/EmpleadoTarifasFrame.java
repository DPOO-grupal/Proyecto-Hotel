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
import java.awt.event.ActionEvent;
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

import org.jdesktop.swingx.JXDatePicker;

import controlador.WindowManager;
import vistaAdmin.FrameBaseInfo;




public class EmpleadoTarifasFrame extends FrameBaseInfo {

	private JTable tablaTarifas;
    private DefaultTableModel modeloTabla;
    
	
	public EmpleadoTarifasFrame(WindowManager windowManager) {

		super(windowManager);
		
	}

	protected void setPanelInfo() {
		// TODO Auto-generated method stub
		panelDerecho.setBackground(Color.decode("#B2BBA4"));
		GridBagLayout gridbag = new GridBagLayout();
	    GridBagConstraints constraints = new GridBagConstraints();
	    panelDerecho.setLayout(gridbag);
	    
	    
	    // tabla
	    String[] columnas = {"Fecha Inicial", "Fecha Final", "Precio", "Tipo"};
        modeloTabla = new DefaultTableModel(columnas, 0);
//        String[] fila1 = {"01/01/2022", "31/01/2022", "$100", "Tipo 1"};
//        String[] fila2 = {"01/02/2022", "28/02/2022", "$150", "Tipo 2"};
//        modeloTabla.addRow(fila1);
//        modeloTabla.addRow(fila2);        
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
        
        for (int i = 0; i< 4; i++) {
            tablaTarifas.getColumnModel().getColumn(i).setCellRenderer(modelocentrar);
            tablaTarifas.getColumnModel().getColumn(i).setCellEditor(null);
        }
  
       
        JScrollPane scrollPanel = new JScrollPane(tablaTarifas);
        scrollPanel.setBackground(Color.decode("#B2BBA4"));
        constraints.gridx = 0;
	    constraints.gridy = 1;
	    constraints.ipadx = 1000;
	    constraints.ipady = 800;
	    
	    panelDerecho.add(scrollPanel, constraints);
	}
	
	public void addTarifaTabla(String fechaI, String fechaF, String precio, String Tipo) {
      String[] fila = {fechaI, fechaF, precio, Tipo};
      modeloTabla.addRow(fila);

	}

	protected void setPanelCrear() {
		   // Configuracion General
		GridBagLayout gba = new GridBagLayout();
	    GridBagConstraints c = new GridBagConstraints();
	    
	    Font fontLabel = new Font("Arial", Font.BOLD, 16);
	    
	    panelCrear.setLayout(gba);
	    
	    
	    JButton buscarButton = new JButton("Buscar Tarifa");
	    buscarButton.setBackground( Color.decode("#ACCAF2"));
	    buscarButton.setFont(fontLabel);
	    buscarButton.setForeground(Color.black);
	    
	    JXDatePicker fechaI = new JXDatePicker(new Date());
	    JXDatePicker fechaF = new JXDatePicker(new Date());
	    
	    c.gridx = 0;
	    c.gridy = 0;
	    
	    c.ipady = 20;
	    c.ipadx = 100;
	    
	    c.insets = new Insets(0, 0, 0, 0);
	    
	    JLabel temLabel = new JLabel("Fecha Incial");
		temLabel.setFont(fontLabel);
		temLabel.setForeground(Color.white);
		panelCrear.add(temLabel,c);
	    
	    c.gridy = 1;
	    c.gridx = 0;
	    c.insets = new Insets(0, 0, 25, 0);
	    
	    fechaI.setFont(fontLabel);
	    panelCrear.add(fechaI, c);
	    
	    c.gridy = 3;
	    c.gridx = 0;
	    
	    c.insets = new Insets(0, 0, 0, 0);
	    
	    temLabel = new JLabel("Fecha Final");
		temLabel.setFont(fontLabel );
		temLabel.setForeground(Color.white);
		panelCrear.add(temLabel,c);
	    
	    c.gridy = 4;
	    c.gridx = 0;
	    
	    c.insets = new Insets(0, 0, 0, 0);
	    
	    fechaF.setFont(fontLabel);
	    panelCrear.add(fechaF, c);
	    	    
	    c.gridx = 0;
	    c.gridy = 5;
	    
	    c.gridheight = 3;
	    c.gridwidth = 1;
	    
	    c.ipadx = 100;
	    c.ipady = 40;
	    
	    c.insets = new Insets(50, 0, 0, 0);

	    

	    panelCrear.add(buscarButton, c);
	    panelCrear.setBackground(Color.decode("#204473"));
	}

	@Override
	public void actionPerformedFrame(ActionEvent e) {

		
	}


}
