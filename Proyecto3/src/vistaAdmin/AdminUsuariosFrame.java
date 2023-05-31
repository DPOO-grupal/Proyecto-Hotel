package vistaAdmin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Date;
import java.util.Vector;

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

import controlador.WindowManager;

public class AdminUsuariosFrame extends FrameBaseInfo implements ActionListener{
	
	private JTable tablaUsuarios;
    private DefaultTableModel modeloTabla;
    private JTextField cajaNombre;
    private JTextField cajaArea;
    private JTextField cajaTipo;
    private JComboBox<String> comboTipo;
	
	public AdminUsuariosFrame(WindowManager windowManager) {
		super(windowManager);
		cargarDatos();
	}
	
	public void cargarDatos() {
		modeloTabla.getDataVector().removeAllElements();
		modeloTabla.fireTableDataChanged(); 
		String[] listaServicios = windowManager.darUsuarios();		
		for (int i = 0; i < listaServicios.length; i++) {
	        String nombre = listaServicios[i];
	        modeloTabla.addRow(new Object[]{nombre, "ICON", "ICON"});
	    }
	}

	@Override
	protected void setPanelInfo() {
		panelDerecho.setBackground(Color.decode("#B2BBA4"));
		panelDerecho.setBorder(BorderFactory.createEmptyBorder(30, 50, 50, 50));
		GridBagLayout gridbag = new GridBagLayout();
	    GridBagConstraints constraints = new GridBagConstraints();
	    panelDerecho.setLayout(gridbag);
	    
	    JPanel panelBuscar = new JPanel();	    
	    // añadir al panel
	    GridBagLayout gba = new GridBagLayout();
	    GridBagConstraints c = new GridBagConstraints();
	    
	    Font fontLabel = new Font("Arial", Font.BOLD, 16);
	    
	    panelBuscar.setLayout(gba);
	    
	  //Creacion de la tabla servicios
	  		String[] columnas = {"Usuarios"}; //Nombre de las columnas
	          modeloTabla = new DefaultTableModel(columnas, 0);
	          modeloTabla.addTableModelListener(tablaUsuarios);
	  	    
	  	    //Diseño de la tabla
	          tablaUsuarios = new JTable(modeloTabla);
	          tablaUsuarios.addMouseListener(new MouseAdapter() {
	        	  public void mouseClicked(MouseEvent e) {
	        		  if (e.getClickCount() == 1) {
	        		   JTable target = (JTable)e.getSource();
	        		   int row = target.getSelectedRow();
	        		   int column = target.getSelectedColumn();
	        		   String nombre = tablaUsuarios.getValueAt(row, column).toString();
	        		   String area = windowManager.getArea(nombre);
	        		   String tipo = windowManager.getTipo(nombre);
	        		   cajaNombre.setText(nombre);
	        		   cajaArea.setText(area);
	        		   cajaTipo.setText(tipo);
	        		   //JOptionPane.showMessageDialog(null, "Seleccionó a: "+tablaUsuarios.getValueAt(row, column));
	        		  }
	        		 }
	        		});
	          tablaUsuarios.setDefaultEditor(Object.class, null);
	          tablaUsuarios.getTableHeader().setBackground(Color.decode("#204473"));
	          tablaUsuarios.getTableHeader().setForeground(Color.white);
	          tablaUsuarios.getTableHeader().setReorderingAllowed(false);
	          tablaUsuarios.getTableHeader().setFont(new Font("Times New Roman", 1, 30));
	          tablaUsuarios.setFont(new Font("Times New Roman", 1, 20));
	          tablaUsuarios.setRowHeight(70);
	          //tablaUsuarios.setRowSelectionAllowed(true);
	          //tablaUsuarios.setCellSelectionEnabled(false);
	          tablaUsuarios.setEnabled(true);

	          DefaultTableCellRenderer modelocentrar = new DefaultTableCellRenderer();
	          modelocentrar.setHorizontalAlignment(SwingConstants.CENTER);


	          tablaUsuarios.getColumnModel().getColumn(0).setCellRenderer(modelocentrar);

	          JScrollPane scrollPanel = new JScrollPane(tablaUsuarios);
	          scrollPanel.setPreferredSize(new Dimension(400, 650));
	          //Tamaño y ubicacion de la tabla en el panel
	          constraints.gridx = 0;
	          constraints.gridy = 0;
	          //constraints.ipady = 650;
	          //constraints.ipadx = 400;
	          constraints.gridheight = 10;
	          constraints.gridwidth = 2;
	          constraints.weightx = 1;
	          //constraints.weighty = 0.1;

	          panelDerecho.add(scrollPanel, constraints);
	          
	        //Creacion del recuadro para consultar la información de un usuario
	  		JPanel Pusuario = new JPanel(new GridLayout(6,1, 0, 5));
	  		Pusuario.setBorder(BorderFactory.createEmptyBorder(20, 50, 50, 50));
	  		Pusuario.setBackground(Color.decode("#accaf2"));
	  		
	  		//Nombre de usuario y su caja de texto
	  		JLabel labelNombre = new JLabel("Nombre");
	  		labelNombre.setForeground(Color.BLACK);
	  		labelNombre.setFont(new Font("Times New Roman", Font.PLAIN, 30));
	  		
	  		cajaNombre = new JTextField();
	  		cajaNombre.setForeground(Color.BLACK);
	  		cajaNombre.setEditable(false);
	  		
	  		//Area de usuario y su caja de texto
	  		JLabel labelArea = new JLabel("Area");
	  		labelArea.setForeground(Color.BLACK);
	  		labelArea.setFont(new Font("Times New Roman", Font.PLAIN, 30));
	  		
	  		cajaArea = new JTextField();
	  		cajaArea.setEditable(false);
	  		cajaArea.setForeground(Color.BLACK);
	  		
	  		//Tipo de usuario y su caja de texto
	  		JLabel labelTipo = new JLabel("Tipo");
	  		labelTipo.setForeground(Color.BLACK);
	  		labelTipo.setFont(new Font("Times New Roman", Font.PLAIN, 30));
	  		
	  		cajaTipo = new JTextField();
	  		cajaTipo.setEditable(false);
	  		cajaTipo.setForeground(Color.BLACK);
	  		
	  		//Boton para añadir un servicio
	  		JButton quitarUsuario = new JButton("Quitar usuario");
	  		quitarUsuario.addActionListener(this);
	  		quitarUsuario.setBackground(Color.decode("#204473"));
	  		quitarUsuario.setForeground(Color.white);
	  		quitarUsuario.setFont(new Font("arial", 1, 20));
	  		
	  		Pusuario.add(labelNombre);
	  		Pusuario.add(cajaNombre);
	  		Pusuario.add(labelArea);
	  		Pusuario.add(cajaArea);
	  		Pusuario.add(labelTipo);
	  		Pusuario.add(cajaTipo);
	  		
	  		Pusuario.setPreferredSize(new Dimension(250, 300));
	  		
	  		//Tamaño y ubicacion en el panel
	  		constraints.gridx = 2;
	  		constraints.insets = new Insets(100, 0, 1, 0);
	  		constraints.gridy = 2;
	  		//constraints.ipady = 1;
	        //constraints.ipadx = 100;
	        constraints.gridheight = 5;
	        constraints.gridwidth = 1;
	        //constraints.weighty = 0.1;
	        
	        quitarUsuario.setPreferredSize(new Dimension(175, 50));
	        
	  		panelDerecho.add(Pusuario, constraints);
	  		constraints.insets = new Insets(50, 0, 1, 0);
	  		constraints.gridx = 2;
	  		constraints.gridy = 7;
	  		//constraints.ipady = 50;
	        //constraints.ipadx = 100;
	        constraints.gridheight = 2;
	        constraints.gridwidth = 1;
	        //constraints.weighty = 0.1;
	  		panelDerecho.add(quitarUsuario, constraints);
	}

	@Override
	protected void setPanelCrear() {
		GridBagLayout gridbag = new GridBagLayout();
	    GridBagConstraints constraints = new GridBagConstraints();
	    
	    panelCrear.setLayout(gridbag);

		panelCrear.setBorder(BorderFactory.createEmptyBorder(20, 100, 20, 100));
		panelCrear.setBackground(Color.decode("#204473"));
		
		datos = new JTextField[4];
		String[] titulos = {"Nombre", "Contraseña", "Área", "Tipo"};
		
		Font fontLabel = new Font("Arial", Font.BOLD, 20);

		
		for( int i = 0; i < 3; i++) {
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
		JLabel tipo = new JLabel();
		tipo.setText("Tipo");
		tipo.setFont(fontLabel);
		tipo.setForeground(Color.WHITE);
		constraints.gridx = 0;
		constraints.gridy = (8);
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.weighty=10;
	    panelCrear.add(tipo, constraints);
		
		comboTipo = new JComboBox<>();
		String[] opciones = {"", "Admin", "Empleado"};
		comboTipo.setPreferredSize(new Dimension(200, 30));
		comboTipo = new JComboBox<>(opciones);
		constraints.gridx = 0;
		constraints.gridy = (9);
		constraints.ipady = (7);
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.weighty=10;
		panelCrear.add(comboTipo, constraints);
		
		Font fontBoton = new Font("Arial", Font.BOLD, 20);
		addDatos =  new JButton("Agregar usuario");
		addDatos.setPreferredSize(new Dimension(200, 50));
		addDatos.setBackground(Color.decode("#ACCAF2"));
		addDatos.setFont(fontBoton);
		addDatos.addActionListener(this);
		
		constraints.gridy = 9 ;
		constraints.ipady = (1);
		constraints.gridy = GridBagConstraints.PAGE_END;
		panelCrear.add(addDatos,constraints);
		
	}
	
	public void agregarUsuario() {
		String login = datos[0].getText();
		String password = datos[1].getText();
		String area = datos[2].getText();
		int tipo = comboTipo.getSelectedIndex();
		windowManager.agregarUsuario(login, password, area, tipo);
		cargarDatos();
		datos[0].setText("");
		datos[1].setText("");
		datos[2].setText("");
		comboTipo.setSelectedIndex(0);
	}
	
	public void quitarUsuario() {
		
		String nombre = cajaNombre.getText();
		int opcion = JOptionPane.YES_OPTION;
		if (!nombre.equals("") && nombre != null) {
			opcion = JOptionPane.showConfirmDialog(null, "¿Desea eliminar el usuario: " + nombre + "?", "Panel de Opciones", JOptionPane.YES_NO_OPTION);
		}
		
        if (opcion == JOptionPane.YES_OPTION && !nombre.equals("") && nombre != null) {
        	boolean self = windowManager.checkUsuario(nombre);
        	if (!self) {
        		windowManager.quitarUsuario(nombre);
        		JOptionPane.showMessageDialog(null, "El usuario '" + nombre + "' se eliminó correctamente.");
        	}
        	else {
        		JOptionPane.showMessageDialog(null, "No puede eliminarse a usted mismo.");
        	}
        } 
        
        else if (opcion == JOptionPane.YES_OPTION && nombre.equals("") || nombre == null) {
        	JOptionPane.showMessageDialog(null, "Seleccione un usuario primero.");
        }
        else {
        	JOptionPane.showMessageDialog(null, "Acción cancelada.");
        }
        resetDatos();
	}
	
	@Override
	protected void actionPerformedFrame(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "Agregar usuario":
			agregarUsuario();
			break;
		
		case "Quitar usuario":
			quitarUsuario();
			break;

		default:
			break;
		}
	}

	@Override
	public void resetDatos() {
		cajaArea.setText("");
		cajaNombre.setText("");
		cajaTipo.setText("");
		modeloTabla.getDataVector().removeAllElements();
		modeloTabla.fireTableDataChanged(); 
		cargarDatos();
	}

}
