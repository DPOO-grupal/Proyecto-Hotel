package vista;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class TarifasAdminFrame extends JFrame {
	private JPanel panelVolver;
	private JPanel panelCrear;
	private JPanel panelDerecho;
	private JPanel panelIzquierdo;
	private JButton volverButton;
	private JTextField[] datos;
	private JButton addTarifa;
	
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
		setExtendedState(MAXIMIZED_BOTH);
		setResizable(false);
		

		
		
	}

	private void setPanelInfo() {
		// TODO Auto-generated method stub
		
	}

	private void setPanelCrear() {
		   // Configuracion General
		
		GridLayout gridLayout = new GridLayout(10, 1);
		gridLayout.setVgap(15);
		
		BoxLayout boxLayout = new BoxLayout(panelCrear, BoxLayout.Y_AXIS);
		panelCrear.setLayout(gridLayout);
		panelCrear.setBorder(BorderFactory.createEmptyBorder(20, 100, 20, 100));
		panelCrear.setBackground(Color.decode("#204473"));
		
		datos = new JTextField[4];
		String[] titulos = {"Tipo", "Precio", "Fecha Inicial", "Fecha Inicial"};
		
		Font fontLabel = new Font("Arial", Font.BOLD, 20);

		
		for( int i = 0; i < 4; i++) {
			JTextField campo = new JTextField();
			campo.setPreferredSize(new Dimension(200, 0));
			
			JLabel titulo = new JLabel(titulos[i]);
			titulo.setFont(fontLabel);
			titulo.setForeground(Color.WHITE);
			
			panelCrear.add(titulo);
			panelCrear.add(campo);
		}
	    
		panelCrear.add(new JLabel());
		
		Font fontBoton = new Font("Arial", Font.BOLD, 20);
		addTarifa =  new BotonRedondeado("Volver", 200, 75, 30);
		addTarifa.setFont(fontBoton);
		
		panelCrear.add(addTarifa);
		
	    
	}

	private void setPanelVolver() {
		// Configuracion General
	    panelVolver.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 65));
	    panelVolver.setBackground(Color.decode("#7E8C69"));

	    // Crear Boton redondeado
	    Font font = new Font("Arial", Font.BOLD, 20);
	    
	    volverButton = new BotonRedondeado("Volver", 200, 75, 30);
	    volverButton.setFont(font);
	    
	    // Añadirlo al Panel
	    panelVolver.add(volverButton);
	}


}
