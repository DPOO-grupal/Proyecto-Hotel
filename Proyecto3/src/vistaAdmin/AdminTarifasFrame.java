package vistaAdmin;

import java.awt.Checkbox;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.jdesktop.swingx.JXDatePicker;

import controlador.WindowManager;
import modelo.TipoHabitacion;
import javax.swing.text.NumberFormatter;
import vistaEmpleado.EmpleadoTarifasFrame;
import javax.swing.JFormattedTextField;	

public class AdminTarifasFrame extends EmpleadoTarifasFrame implements KeyListener{

	private Checkbox[] dias;
	private JXDatePicker[] fechaSeleccionada;
	private JComboBox<TipoHabitacion> tiposHabi;
	private JTextField precio;


	public AdminTarifasFrame(WindowManager windowManager) {
		super(windowManager);
		añadirAdminOpciones();
	}
	
	public void añadirAdminOpciones() {
	    GridBagConstraints constraints = new GridBagConstraints();
	    Font fontLabel = new Font("Arial", Font.BOLD, 16);

	    JButton crearButton = new JButton("Crear Tarifa");
	    crearButton.setBackground(Color.decode("#558ad0"));
	    crearButton.setFont(fontLabel);
	    crearButton.setForeground(Color.black);
	    crearButton.setPreferredSize(new Dimension(200, 50));
	    crearButton.addActionListener(this);
	    
	    constraints.insets = new Insets(20, 0, 20, 0);
	    constraints.gridx = 0;
	    constraints.gridy = 2;
	    constraints.weightx = 1;
	    constraints.weighty = 1;

	    constraints.ipadx =1;
	    constraints.ipady =1;
	    
	    panelDerecho.add(crearButton, constraints);
		
		JButton borrarButton = new JButton("Borrar Tarifa");
		borrarButton.setBackground(Color.decode("#558ad0"));
		borrarButton.setFont(fontLabel);
		borrarButton.setForeground(Color.black);
	    borrarButton.setPreferredSize(new Dimension(200, 50));
	    borrarButton.addActionListener(this);

	    
	    constraints.gridx = 1;
		panelDerecho.add(borrarButton, constraints);
		
		JButton faltantesButton = new JButton("Faltantes");
		faltantesButton.setBackground(Color.decode("#558ad0"));
		faltantesButton.setFont(fontLabel);
		faltantesButton.setForeground(Color.black);
		faltantesButton.setPreferredSize(new Dimension(200, 50));
		faltantesButton.addActionListener(this);

	    
	    constraints.gridx = 2;
		panelDerecho.add(faltantesButton, constraints);


	    
	}
	

	private void borrarTarifa() {
		// TODO Auto-generated method stub
		
	}

	private void crearTarifa() {
		JFrame selectHabitacion = new JFrame();
	    selectHabitacion.setSize(new Dimension(300,600));
	    selectHabitacion.setLocationRelativeTo(null);
	    
	    GridBagLayout gridbag = new GridBagLayout();
	    GridBagConstraints constraints = new GridBagConstraints();
	    
	    JPanel panel = new JPanel();
	    
	    panel.setLayout(gridbag);

		panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		panel.setBackground(Color.decode("#ccd2c2"));
		
		TipoHabitacion[] tipos = TipoHabitacion.values();
	    tiposHabi = new JComboBox<TipoHabitacion>(tipos);
		
		precio = new JTextField();
		precio.addKeyListener(this);
		JComponent[] components = {tiposHabi, precio}; 
		
		String[] titulos = {"Tipo", "Precio"};
		
		Font fontLabel = new Font("Arial", Font.BOLD, 20);

		constraints.weightx=10;
		constraints.weighty=10;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.gridheight = 1;
		constraints.gridwidth = 3;
		constraints.gridx = 0;

		
		for( int i = 0; i < datos.length; i++) {
			
			JLabel titulo = new JLabel(titulos[i]);
			titulo.setFont(fontLabel);
			titulo.setForeground(Color.black);
						
			constraints.gridy = (i*2);
			panel.add(titulo, constraints);
			
			JComponent campo = components[i];
			campo.setPreferredSize(new Dimension(200, 30));
			
			constraints.gridy = (i*2)+1;
			panel.add(campo, constraints);
		}
		
		constraints.gridy +=1;
		constraints.gridwidth = 3;
		JLabel titulo = new JLabel("Dias De la Semana");
		titulo.setFont(fontLabel);
		titulo.setForeground(Color.black);
		panel.add(titulo, constraints);
		
		constraints.gridheight = 1;
		constraints.gridwidth = 1;
		
		int iSum = constraints.gridy + 1;
		
		String[] diasSemana = {"Mon", "Tue", "Wed", "Thu", "Fry", "Sat", "Sun"};
	    dias = new Checkbox[7];
		for( int i = 0; i < diasSemana.length; i++) {
			Checkbox campo = new Checkbox(diasSemana[i]);

			dias[i] = campo;
			
			constraints.gridy= (i/3)+iSum ;
			constraints.gridx = i%3;
		
			panel.add(campo, constraints);
		}
		
	
		
		fechaSeleccionada = new JXDatePicker[2];
		titulos[0] = "Fecha Inicial";
		titulos[1] = "Fecha Final";
		iSum = constraints.gridy + 1;
		constraints.gridwidth = 3;

		
		for( int i = 0; i < fechaSeleccionada.length; i++) {
			JXDatePicker campo = new JXDatePicker(new Date());
			campo.setPreferredSize(new Dimension(200, 40));
			

			titulo = new JLabel(titulos[i]);
			titulo.setFont(fontLabel);
			titulo.setForeground(Color.black);
			
			fechaSeleccionada[i] = campo;
			fechaSeleccionada[i].setFont(fontLabel);

			
			constraints.gridy = iSum + i*2;

			panel.add(titulo, constraints);
			
			constraints.gridy = iSum + i*2 + 1;

			panel.add(campo, constraints);
		}
		
		Font fontBoton = new Font("Arial", Font.BOLD, 20);
		addDatos =  new JButton("Añadir Tarifa");
		addDatos.setBackground( Color.decode("#204473"));
		addDatos.setForeground(Color.white);
		addDatos.setFont(fontBoton);
		addDatos.addActionListener(this);
		
		constraints.gridy += 1;
		constraints.gridx = 0 ;
		constraints.gridheight = 1;
		constraints.gridwidth = 3;
		panel.add(addDatos,constraints);
		
	    selectHabitacion.add(panel);
	    selectHabitacion.setVisible(true);
	    
		
	}
	

	private void añadirTarifa() {
		// TODO Auto-generated method stub
		
	}
	
	
	@Override
	public void actionPerformedFrame(ActionEvent e) {
		super.actionPerformedFrame(e);
		switch (e.getActionCommand()) {
		case "Crear Tarifa":
			crearTarifa();
			break;
			
		case "Borrar Tarifa":
			borrarTarifa();
			break;
		case "Añadir Tarifa":
			añadirTarifa();
			break;
		default:
			break;
		
		
		}
	}


	@Override
	public void keyTyped(KeyEvent e) {
		keyReleased(e);
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		keyReleased(e);
		
	}

	@Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
        JTextField caja = (JTextField) e.getSource();
        int numeros;
        try {
            String cadena = caja.getText().replace(",", "").replace(".", "");

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
