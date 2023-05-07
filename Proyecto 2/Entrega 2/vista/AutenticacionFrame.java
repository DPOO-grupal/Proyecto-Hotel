package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Paint;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.ImageProducer;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicBorders;
import javax.swing.plaf.basic.BasicTreeUI.TreeCancelEditingAction;

import org.jdesktop.swingx.JXImagePanel;

import controlador.WindowManager;

public class AutenticacionFrame extends JFrame implements ActionListener{
	
	private WindowManager windowManager;
	private JTextField usuJTextField;
	private JTextField contraJTextField;
	private int intentos;
	
	public AutenticacionFrame(WindowManager windowManager) {
		
		this.windowManager = windowManager;
		intentos = 0;
		GridBagLayout gridBagLayout = new GridBagLayout();
		
		
		getContentPane().setBackground(Color.decode("#88b3ec"));
		GridBagConstraints gBC = new GridBagConstraints();
		setLayout(gridBagLayout);
		JPanel panel = new JPanel();
		GridBagConstraints consPanel = new GridBagConstraints();
		
		panel.setLayout(gridBagLayout);
		panel.setBackground(Color.decode("#607ea6"));
		panel.setPreferredSize(new Dimension(1, 1));
		
		ImageIcon imageIcon = new ImageIcon("img/iconoUsuario.png");
		Image imagen = imageIcon.getImage();
        Image scaledImage = imagen.getScaledInstance(200, 200, Image.SCALE_AREA_AVERAGING);
        ImageIcon scaledImageIcon = new ImageIcon(scaledImage);
       
        
        JLabel imageLabel = new JLabel(scaledImageIcon);
       
        imageLabel.setPreferredSize(new Dimension(100,100));
        consPanel.gridy = 0;
        consPanel.gridy = 0;
        consPanel.ipadx = 100;
        consPanel.ipady = 100;
        consPanel.insets = new Insets(10, 10, 10, 10);
        panel.add(imageLabel,consPanel);
        
        Font font = new Font("Arial", Font.BOLD, 20);
        JLabel usuJLabel = new JLabel("Usuario");
        usuJLabel.setFont(font);
        consPanel.insets = new Insets(10, 10, 5, 10);
        consPanel.ipadx = 0;
        consPanel.ipady = 0;
        consPanel.gridy = 1;
        consPanel.anchor = GridBagConstraints.WEST;
        panel.add(usuJLabel,consPanel);
        
        usuJTextField = new JTextField();
        consPanel.insets = new Insets(10, 10, 20, 10);
        consPanel.fill = GridBagConstraints.HORIZONTAL;
        consPanel.ipady = 20;
        consPanel.gridy = 2;
        panel.add(usuJTextField,consPanel);
        
        JLabel contraJLabel = new JLabel("Contraseña");
        contraJLabel.setFont(font);
        consPanel.insets = new Insets(10, 10, 5, 10);
        
        consPanel.ipadx = 0;
        consPanel.ipady = 0;
        consPanel.gridy = 3;
        panel.add(contraJLabel,consPanel);
        
        contraJTextField = new JTextField();
        consPanel.insets = new Insets(10, 10, 20, 10);
        consPanel.fill = GridBagConstraints.HORIZONTAL;
        consPanel.ipady = 20;

        consPanel.gridy = 4;
        panel.add(contraJTextField,consPanel);
        
        BotonRedondeado Ingresar = new BotonRedondeado("Iniciar Sesion");
        Ingresar.addActionListener(this);
        Ingresar.setFont(font);
        Ingresar.setBackground(Color.decode("#183356"));
        Ingresar.setForeground(Color.white);
        consPanel.anchor = GridBagConstraints.CENTER;
        consPanel.ipady = 10;
        consPanel.gridy = 5;
        panel.add(Ingresar,consPanel);
        
        gBC.anchor = GridBagConstraints.WEST;
        gBC.ipadx = 450;
        gBC.ipady = 600;
        add(panel,gBC);
	}
	
	public void inciarSecion() {
		String login = usuJTextField.getText();
		String passWord = contraJTextField.getText();
		
		try {
			windowManager.autenticar(login, passWord);
			intentos ++;
		} catch (Exception e) {
			if (intentos < 3) {
				JOptionPane.showMessageDialog(null, "Opss, " + e.getMessage());
			}else {
				JOptionPane.showMessageDialog(null, "Demaciados intentos, intente mas tarde");

			}
			intentos ++;
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Iniciar Sesion")) {
			inciarSecion();
		}
		
	}

}
