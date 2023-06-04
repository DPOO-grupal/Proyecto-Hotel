package vistaEmpleado;
import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.ActionEvent;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.TimeZone;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
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
import modelo.Tarifa;
import modelo.TipoHabitacion;
import vistaAdmin.FrameBaseInfo;




public class EmpleadoTarifasFrame extends FrameBaseInfo implements MouseListener {

	private JTable tablaTarifas;
    private DefaultTableModel modeloTabla;
    private JXDatePicker[] fechaBusqueda;
    protected JXDatePicker[] fechaMostrar;
    private JCheckBox[] dias;
    
	
	public EmpleadoTarifasFrame(WindowManager windowManager) {

		super(windowManager);
		
	}

	protected void setPanelInfo() {
		// TODO Auto-generated method stub
		panelDerecho.setBackground(Color.decode("#B2BBA4"));
		GridBagLayout gridbag = new GridBagLayout();
	    GridBagConstraints constraints = new GridBagConstraints();
	    panelDerecho.setLayout(gridbag);
	    
	    // Configuracion General
 		GridBagLayout gba = new GridBagLayout();
 	    GridBagConstraints c = new GridBagConstraints();
 	    
 	    Font fontLabel = new Font("Arial", Font.BOLD, 16);
 	    
 	    JPanel panelBuscar = new JPanel();
 		panelBuscar.setLayout(gba);
 	    
 	    
 		JButton buscarButton = new JButton("Buscar Tarifa");
	    buscarButton.setBackground(Color.decode("#204473"));
	    buscarButton.setFont(fontLabel);
	    buscarButton.setForeground(Color.white);
	    buscarButton.addActionListener(this);
 	    

 	    fechaBusqueda = new JXDatePicker[2];
 	    fechaBusqueda[0] = new JXDatePicker(windowManager.getHoy());
 	    fechaBusqueda[1]  = new JXDatePicker(windowManager.getHoy());
 	    
 	    c.gridx = 0;
 	    c.gridy = 0;
 	    
 	    c.ipady = 20;
 	    c.ipadx = 100;
 	    
 	    c.insets = new Insets(0, 0, 0, 0);
 	    
 	    JLabel temLabel = new JLabel("Fecha Incial");
 		temLabel.setFont(fontLabel);
 		temLabel.setForeground(Color.black);
 		panelBuscar.add(temLabel,c);
 	    
 	    c.gridy = 1;
 	    c.gridx = 0;
 	    c.insets = new Insets(0, 0, 20, 0);

 	    fechaBusqueda[0].setFont(fontLabel);
 	    panelBuscar.add(fechaBusqueda[0], c);
 	    
 	    c.gridy = 3;
 	    c.gridx = 0;
 	    
 	    c.insets = new Insets(0, 0, 0, 0);
 	    
 	    temLabel = new JLabel("Fecha Final");
 		temLabel.setFont(fontLabel );
 		temLabel.setForeground(Color.black );
 		panelBuscar.add(temLabel,c);
 	    
 	    c.gridy = 4;
 	    c.gridx = 0;
 	    
 	    c.insets = new Insets(0, 0, 0, 0);
 	    

 	    fechaBusqueda[1].setFont(fontLabel);
 	    panelBuscar.add(fechaBusqueda[1], c);
 	    	    
 	    c.gridx = 2;
	    c.gridy = 1;
	    
	    c.gridheight = 3;
	    c.gridwidth = 1;
 	    
 	    c.ipadx = 100;
 	    c.ipady = 40;
 	    

 	    c.insets = new Insets(0, 50, 0, 0);

 	    panelBuscar.add(buscarButton, c);
 	    
 	    panelBuscar.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
 	    panelBuscar.setBackground(Color.decode("#accaf2"));
 	    constraints.weightx = 1;
 	    constraints.weighty = 1;
 	    constraints.gridx = 0;
 	    constraints.gridy = 0;
 	    constraints.gridwidth =3;
 	    constraints.anchor = GridBagConstraints.NORTH;
 	    constraints.fill = GridBagConstraints.BOTH;
 	    constraints.insets = new Insets(0, 0, 0, 0);
 	    
 	    
 	    panelDerecho.add(panelBuscar, constraints); 
    
    
	    // tabla
	    String[] columnas = {"Fecha", "Precio", "Tipo"};
        modeloTabla = new DefaultTableModel(columnas, 0);
//	        String[] fila1 = {"01/01/2022", "31/01/2022", "$100"};
//	        String[] fila2 = {"01/02/2022", "28/02/2022", "$150"};
//	        modeloTabla.addRow(fila1);
//	        modeloTabla.addRow(fila2);        
        Font fontTabla= new Font("Arial", Font.BOLD, 20);
        tablaTarifas = new JTable(modeloTabla);
        tablaTarifas.setDefaultEditor(Object.class, null);
        tablaTarifas.getTableHeader().setReorderingAllowed(false);
        tablaTarifas.getTableHeader().setBackground(Color.decode("#204473"));
        tablaTarifas.getTableHeader().setForeground(Color.white);
        tablaTarifas.getTableHeader().setFont(fontTabla);
        tablaTarifas.setFont(fontTabla);
        tablaTarifas.setBackground(Color.decode("#FFFFFF"));
        tablaTarifas.setRowHeight(50);
        tablaTarifas.setName("TablaTarifas");
        tablaTarifas.addMouseListener(this);
        
        DefaultTableCellRenderer modelocentrar = new DefaultTableCellRenderer();
        modelocentrar.setHorizontalAlignment(SwingConstants.CENTER);
        modelocentrar.setFont(fontTabla);
        
        
        for (int i = 0; i< modeloTabla.getColumnCount(); i++) {
            tablaTarifas.getColumnModel().getColumn(i).setCellRenderer(modelocentrar);
            tablaTarifas.getColumnModel().getColumn(i).setCellEditor(null);
        }
  
       
        JScrollPane scrollPanel = new JScrollPane(tablaTarifas);
        scrollPanel.setBackground(Color.decode("#B2BBA4"));
        constraints.gridx = 0;
	    constraints.gridy = 1;
	    constraints.gridwidth =3;
	    constraints.anchor = GridBagConstraints.NORTH;
	    constraints.fill = GridBagConstraints.BOTH;
	    constraints.insets = new Insets(0, 0, 0, 0);

	    panelDerecho.add(scrollPanel, constraints);
		
	}
	

	
	@Override
	protected void setPanelCrear() {
		   // Configuracion General
		
		GridBagLayout gridbag = new GridBagLayout();
	    GridBagConstraints constraints = new GridBagConstraints();
	    
	    panelCrear.setLayout(gridbag);

		panelCrear.setBorder(BorderFactory.createEmptyBorder(20, 100, 20, 100));
		panelCrear.setBackground(Color.decode("#204473"));
		
		datos = new JTextField[2];
		String[] titulos = {"Tipo", "Precio"};
		
		Font fontLabel = new Font("Arial", Font.BOLD, 20);

		constraints.weightx=10;
		constraints.weighty=10;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.gridheight = 1;
		constraints.gridwidth = 3;
		constraints.gridx = 0;

		
		for( int i = 0; i < datos.length; i++) {
			JTextField campo = new JTextField();
			campo.setPreferredSize(new Dimension(200, 30));
			campo.setEditable(false);
			JLabel titulo = new JLabel(titulos[i]);
			titulo.setFont(fontLabel);
			titulo.setForeground(Color.WHITE);
			
			datos[i] = campo;
			
			constraints.gridy = (i*2);
			panelCrear.add(titulo, constraints);
			
			constraints.gridy = (i*2)+1;
			panelCrear.add(campo, constraints);
		}
		constraints.gridy +=1;
		constraints.gridwidth = 3;
		JLabel titulo = new JLabel("Dias De la Semana");
		titulo.setFont(fontLabel);
		titulo.setForeground(Color.WHITE);
		panelCrear.add(titulo, constraints);
		
		constraints.gridheight = 1;
		constraints.gridwidth = 1;
		
		int iSum = constraints.gridy + 1;
		
		String[] diasSemana = {"Mon", "Tue", "Wed", "Thu", "Fry", "Sat", "Sun"};
	    dias = new JCheckBox[7];
		for( int i = 0; i < diasSemana.length; i++) {
			JCheckBox campo = new JCheckBox(diasSemana[i]);
			campo.setEnabled(false);
			campo.setForeground(Color.white);
			dias[i] = campo;
			
			constraints.gridy= (i/3)+iSum ;
			constraints.gridx = i%3;
		
			panelCrear.add(campo, constraints);
		}
		
	
		
		fechaMostrar = new JXDatePicker[1];
		titulos[0] = "Fecha";
		iSum = constraints.gridy + 1;
		constraints.gridwidth = 3;

		
		for( int i = 0; i < fechaMostrar.length; i++) {
			JXDatePicker campo = new JXDatePicker(new Date());
			campo.setPreferredSize(new Dimension(200, 40));
			campo.setEditable(false);

			titulo = new JLabel(titulos[i]);
			titulo.setFont(fontLabel);
			titulo.setForeground(Color.WHITE);
			
			fechaMostrar[i] = campo;
			fechaMostrar[i].setFont(fontLabel);
			fechaMostrar[i].setEditable(false);

			
			constraints.gridy = iSum + i*2;

			panelCrear.add(titulo, constraints);
			
			constraints.gridy = iSum + i*2 + 1;

			panelCrear.add(campo, constraints);
		}
		
//		panelCrear.add(new JLabel());
//		
//		Font fontBoton = new Font("Arial", Font.BOLD, 20);
//		addDatos =  new JButton("Crear Tarifa");
//		addDatos.setBackground( Color.decode("#ACCAF2"));
//		addDatos.setFont(fontBoton);
//		
//		constraints.gridy = 9 ;
//		constraints.gridy = GridBagConstraints.PAGE_END;
//		panelCrear.add(addDatos,constraints);
		
	    
	}
	

	protected void buscarTarifa() {
		Date dateI = fechaBusqueda[0].getDate();
		Date dateF = fechaBusqueda[1].getDate();

		try {
			fechasValidas(dateI, dateF);
			Collection<Tarifa> tarifasColl = windowManager.consultarTarifas(dateI, dateF);
			llenarTabla(tarifasColl);
		} catch (Exception e) {
			fechaBusqueda[0].setDate(windowManager.getHoy());
			fechaBusqueda[1].setDate(windowManager.getHoy());
			JOptionPane.showMessageDialog(null, e.getMessage());

		}
		
	}

	private void llenarTabla(Collection<Tarifa> tarifasColl) {
		resetDatos();
		modeloTabla.getDataVector().removeAllElements();
		modeloTabla.fireTableDataChanged();
		for (Tarifa tarifa : tarifasColl) {
			String [] datos = new String[3];
			
			datos[0] = tarifa.getFechaString();
			for (TipoHabitacion tipo: TipoHabitacion.values()) {
				int precio;
				try {
					precio = (int)tarifa.getPrecio(tipo);
					NumberFormatter numberFormatter = new NumberFormatter();
		            numberFormatter.setValueClass(Integer.class);
		            numberFormatter.setMinimum(1);
		            numberFormatter.setMaximum(Integer.MAX_VALUE);
		            numberFormatter.setAllowsInvalid(false);
		            JFormattedTextField input = new JFormattedTextField(numberFormatter);
		            input.setText(precio + "");
					datos[1] = input.getText();
					datos[2] = tipo.toString();
					modeloTabla.addRow(datos);
				} catch (Exception e) {
				}
				
	            
				
					
				
			}
			
		}
		
	}

	@Override
	public void actionPerformedFrame(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "Buscar Tarifa":
			buscarTarifa();
			break;

		default:
			break;
		
		
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
			resetDatos();
			JTable tabla = (JTable) e.getSource();
			if (tabla.getName().equals("TablaTarifas")) {
				int row = tablaTarifas.getSelectedRow();
				String fechaString = ((String) tablaTarifas.getValueAt(row, 0));
				String tipo = (String) tablaTarifas.getValueAt(row, 1);
				String precio = (String) tablaTarifas.getValueAt(row, 2);
				Calendar calendar = Calendar.getInstance();
				DateFormat DFormat = new SimpleDateFormat("dd/MM/yyyy");
				Date fecha = null;
				try {
					fecha = DFormat.parse(fechaString);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				calendar.setTime(fecha);

				datos[0].setText(tipo);
				datos[1].setText(precio);
				fechaMostrar[0].setDate(fecha);
				dias[((calendar.get(Calendar.DAY_OF_WEEK))+5)%7].setSelected(true);
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

		for (JCheckBox dia:dias) {
			dia.setSelected(false);
		}
		
		for (JTextField datos:datos) {
			datos.setText("");
		}
				
	}



}
