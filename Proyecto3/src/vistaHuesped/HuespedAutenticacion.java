package vistaHuesped;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField; 
import javax.swing.JTextField;

import vistaAdmin.AutenticacionFrame;


public class HuespedAutenticacion extends AutenticacionFrame{

	private HuespedManager huespedManager;
	
	public HuespedAutenticacion(HuespedManager huespedManager) {
		super(huespedManager);
		
	}
	
	@Override
	public void cargueComponentes() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		GridBagConstraints constraints = new GridBagConstraints();
		setLayout(gridBagLayout);
		ImageIcon imageIcon = new ImageIcon("img/Tipos-de-hoteles.jpg");
		Image imagen = imageIcon.getImage();
		Image scaledImage = imagen.getScaledInstance(1920, 1080, Image.SCALE_AREA_AVERAGING);
        ImageIcon scaledImageIcon = new ImageIcon(scaledImage);

        
        
        JPanel infoJPanel = new JPanel(gridBagLayout); 
        infoJPanel.setBackground(Color.yellow);

        
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
        constraints.insets = new Insets(10, 10, 10, 10);
        infoJPanel.add(imageLabel1,constraints);
        
        Font font = new Font("Arial", Font.BOLD, 20);
        Font fontTextfiel = new Font("Arial",0, 15);
        JLabel usuJLabel = new JLabel("Usuario");
        usuJLabel.setFont(font);
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
        Ingresar.setBackground(Color.decode("#183356"));
        Ingresar.setForeground(Color.white);
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.ipady = 10;
        constraints.gridy = 5;
        infoJPanel.add(Ingresar,constraints);
        
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;

        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.anchor = GridBagConstraints.EAST;
        constraints.fill = GridBagConstraints.VERTICAL;
        constraints.ipadx = 300;
        add(infoJPanel,constraints);
        
        JLabel imageLabel = new JLabel(scaledImageIcon);
        imageLabel.setBackground(Color.red);
        constraints.anchor = GridBagConstraints.WEST;
        constraints.ipadx = 1;
        constraints.fill = GridBagConstraints.NONE;

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.weightx = 1;
        constraints.weighty = 1;
        

        
        
        panelimage.add(imageLabel,constraints);
        
        constraints.anchor = GridBagConstraints.WEST;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        constraints.gridheight = 1;


        add(panelimage,constraints);
 

	}

}
