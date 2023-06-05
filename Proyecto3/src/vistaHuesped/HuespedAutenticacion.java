package vistaHuesped;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField; 
import javax.swing.JTextField;

import modelo.Usuario;
import vistaAdmin.AutenticacionFrame;


public class HuespedAutenticacion extends AutenticacionFrame{

	private HuespedManager huespedManager;
	private JTextField[] datos;
	private JFrame frame;
	
	public HuespedAutenticacion(HuespedManager huespedManager) {
		super(huespedManager);
		this.huespedManager = huespedManager;
		
	}
	
	@Override
	public void cargueComponentes() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		GridBagConstraints constraints = new GridBagConstraints();
		setLayout(gridBagLayout);
		ImageIcon imageIcon = new ImageIcon("img/HotelAutenticar.jpg");
		Image imagen = imageIcon.getImage();
		Image scaledImage = imagen.getScaledInstance(1627, 1080, Image.SCALE_AREA_AVERAGING);
        ImageIcon scaledImageIcon = new ImageIcon(scaledImage);

        
        
        JPanel infoJPanel = new JPanel(gridBagLayout); 
        infoJPanel.setBackground(Color.yellow);
        infoJPanel.setBackground(Color.decode("#183356"));
        
        JPanel panelimage = new JPanel(gridBagLayout); 
        panelimage.setBackground(Color.blue);
        
        ImageIcon imageIcon1 = new ImageIcon("img/iconoUsuario.png");
		Image imagen1 = imageIcon1.getImage();
        Image scaledImage1 = imagen1.getScaledInstance(200, 200, Image.SCALE_AREA_AVERAGING);
        ImageIcon scaledImageIcon1 = new ImageIcon(scaledImage1);
       
        
        JLabel imageLabel1 = new JLabel(scaledImageIcon1);
       
        imageLabel1.setPreferredSize(new Dimension(150,100));
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.fill = GridBagConstraints.NONE;
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.ipadx = 100;
        constraints.ipady = 100;

        constraints.ipady = 100;
        constraints.insets = new Insets(10, 10, 10, 10);
        infoJPanel.add(imageLabel1,constraints);
        
        Font font = new Font("Arial", Font.BOLD, 20);
        Font fontTextfiel = new Font("Arial",0, 15);
        JLabel usuJLabel = new JLabel("Usuario");
        usuJLabel.setFont(font);
        usuJLabel.setForeground(Color.white);
        constraints.insets = new Insets(10, 10, 5, 10);
        constraints.gridx = 0;
        constraints.gridy = 1;

        constraints.ipadx = 0;
        constraints.ipady = 0;
        constraints.gridy = 1;
        constraints.anchor = GridBagConstraints.WEST;
        infoJPanel.add(usuJLabel,constraints);
        
        usuJTextField = new JTextField();
        usuJTextField.setFont(fontTextfiel);
        constraints.insets = new Insets(10, 10, 20, 10);
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridy = 2;

        constraints.ipady = 20;
        constraints.gridy = 2;
        infoJPanel.add(usuJTextField,constraints);
        
        JLabel contraJLabel = new JLabel("Contraseña");
        contraJLabel.setForeground(Color.white);

        contraJLabel.setFont(font);
        constraints.insets = new Insets(10, 10, 5, 10);
        
        constraints.ipadx = 0;
        constraints.ipady = 0;
        constraints.gridy = 3;
        infoJPanel.add(contraJLabel,constraints);
        
        contraJPasswordField =new JPasswordField();
        contraJPasswordField.setFont(fontTextfiel);
        constraints.insets = new Insets(10, 10, 20, 10);
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.ipady = 20;
        
        constraints.gridy = 4;
        infoJPanel.add(contraJPasswordField,constraints);
        
        JButton Ingresar = new JButton("Iniciar Sesion");
//        Ingresar.putClientProperty( "JButton.buttonType", "roundRect" );
        Ingresar.addActionListener(this);
        Ingresar.setFont(font);
        Ingresar.setBackground( Color.decode("#ACCAF2"));
        Ingresar.setForeground(Color.BLACK);
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.ipady = 10;
        constraints.gridy = 5;
        infoJPanel.add(Ingresar,constraints);
        
        JButton crear = new JButton("Nuevo Huesped");
        crear.addActionListener(this);
        crear.setFont(font);
        crear.setBackground( Color.decode("#ACCAF2"));
        crear.setForeground(Color.BLACK);
		constraints.anchor = GridBagConstraints.CENTER;
		constraints.ipady = 10;
		constraints.gridy = 7;
		infoJPanel.add(crear,constraints);

        constraints.insets = new Insets(0, 0, 0, 0);

        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;

        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.anchor = GridBagConstraints.EAST;
        constraints.fill = GridBagConstraints.VERTICAL;
        constraints.ipadx = 200;
        add(infoJPanel,constraints);
        
        JLabel imageLabe = new JLabel(scaledImageIcon);
        imageLabe.setBackground(Color.red);
        constraints.anchor = GridBagConstraints.WEST;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.ipadx = -1;
        constraints.ipady = -1;

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.insets =  new Insets(-100, -20, -100, -20);
        
        panelimage.add(imageLabe,constraints);
        
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        constraints.gridheight = 1;
        constraints.ipadx = 1;
        constraints.ipady = 1;
        constraints.insets =  new Insets(0, 0, 0, 0);
        


        add(panelimage,constraints);
 

	}
	
	public void frameLlenadoDatos() {
		GridBagLayout gridbag = new GridBagLayout();
		GridBagConstraints constraints = new GridBagConstraints();

		JPanel panelCrear = new JPanel();
		panelCrear .setLayout(gridbag);

		panelCrear.setBorder(BorderFactory.createEmptyBorder(20, 100, 20, 100));
		panelCrear.setBackground(Color.decode("#204473"));
		Font fontTitulo = new Font("Arial", Font.BOLD, 30);

		JLabel tituloGeneral = new JLabel("Completar Datos");
		tituloGeneral.setFont(fontTitulo);
		tituloGeneral.setForeground(Color.white);
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.anchor = GridBagConstraints.CENTER;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.weighty = 10;
		panelCrear.add(tituloGeneral, constraints);

		String[] titulos = {"Login/Nombre", "Contraseña", "Correo", "Edad", "Telefono", "No. identificacion" };
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
		//datos[2].addKeyListener(this);

		panelCrear.add(new JLabel());

		Font fontBoton = new Font("Arial", Font.BOLD, 20);
		JButton addDatos = new JButton("Terminar");
		addDatos.setBackground(Color.decode("#ACCAF2"));
		addDatos.setFont(fontBoton);
		addDatos.addActionListener(this);
		
		constraints.gridy = 9;
		constraints.gridy = GridBagConstraints.PAGE_END;
		constraints.insets = new Insets(0, 0, 0, 0);
		panelCrear.add(addDatos, constraints);
		
		frame = new JFrame();
		frame.add(panelCrear);
		frame.setVisible(true);
		frame.setSize(new Dimension(400, 700));
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.addWindowListener(new WindowAdapter() {
	        @Override
	        public void windowClosing(WindowEvent e) {
	        	setEnabled(true);
	        }
	    });
		setEnabled(false);
	}
	
	
	@Override
	public void inciarSecion() throws Exception{
		try {
			super.inciarSecion();
		} catch (Exception e) {
			e.printStackTrace();
			frameLlenadoDatos();
			datos[0].setText(usuJTextField.getText());
			datos[0].setEditable(false);
			datos[1].setText("******");
			datos[1].setEditable(false);
		}
		
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		super.actionPerformed(e);

		if(e.getActionCommand().equals("Terminar")) {
			String login = datos[0].getText(); 
			String contraseña = datos[1].getText(); 
			String documento = datos[5].getText(); 
			String email = datos[2].getText(); 
			String telefono = datos[4].getText(); 
			int edad = Integer.parseInt(datos[3].getText());
			
			try {
				huespedManager.añadirUsuarioHuesped(login, contraseña, documento,  email,  telefono,  edad);
				frame.dispose();
				setEnabled(true);
				huespedManager.mostraVentana(this);
				
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
			}
		}else if (e.getActionCommand().equals("Nuevo Huesped")){
			frameLlenadoDatos();
		}
		
	}
	
	

}
