package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsConfiguration;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.plaf.basic.BasicTreeUI.TreeCancelEditingAction;

public class MenuPrincipalAdmin extends JFrame implements ActionListener {

	private JPanel panelIzquierdo;
	private JPanel panelDerecho;
	private JButton[] Opciones;
	
	private TarifasAdminFrame tarifasAdminFrame;
	private ServiciosAdminFrame serviciosAdminFrame;
	private HabitacionesAdminFrame habitacionesAdminFrame;
	private RestauranteAdminFrame restauranteAdminFrame;
	private UsuariosAdminFrame usuariosAdminFrame;

	public MenuPrincipalAdmin(){
        setLayout(new BorderLayout());
		setTitle("Menu Principal");
		
		panelIzquierdo = new JPanel();
		panelIzquierdo.setPreferredSize(new Dimension(300, 0));
		setBotones();
		
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
		
		// FRAMES
		
		tarifasAdminFrame = new TarifasAdminFrame(this);
		serviciosAdminFrame = new ServiciosAdminFrame(this);
		habitacionesAdminFrame = new HabitacionesAdminFrame(this);
		restauranteAdminFrame = new RestauranteAdminFrame(this);
		usuariosAdminFrame = new UsuariosAdminFrame(this);
	}

	private void setPanelInfo() {
		panelDerecho.setLayout(new BorderLayout());
		panelDerecho.setBackground(Color.decode("#ACCAF2"));
		JPanel check = new JPanel();
		check.setBackground(Color.decode("#7E8C69"));
		check.setPreferredSize(new Dimension(0,200));
		check.setLayout(new FlowLayout(FlowLayout.CENTER, 300, 80));
	    Font fontButton = new Font("Arial", Font.BOLD, 16);

		JButton checkIn = new BotonRedondeado("Check-In");
		checkIn.setPreferredSize(new Dimension(200,50));
		checkIn.setFont(fontButton);
		checkIn.setBackground(Color.decode("#D0ECF2"));
		
		JButton checkOut = new BotonRedondeado("Check-Out");
		checkOut.setPreferredSize(new Dimension(200,50));
		checkOut.setFont(fontButton);
		checkOut.setBackground(Color.decode("#D0ECF2"));

		
		check.add(checkIn);
		check.add(checkOut);
		
		panelDerecho.add(check, BorderLayout.SOUTH);
	}

	private void setBotones() {
		// Configuracion General
		GridBagLayout gridbag = new GridBagLayout();
	    GridBagConstraints constraints = new GridBagConstraints();
	    panelIzquierdo.setLayout(gridbag);
	    panelIzquierdo.setBackground(Color.decode("#204473"));
	    
	    JButton[] opciones = new JButton[6];
	    String[] nombres = {"Administrar usuarios" , 
	    					"Tarifas", 
	    					"Servicios", 
	    					"Habitaciones", 
	    					"Restaurante", 
	    					"Reservas"};
	    
	    Font fontButton = new Font("Arial", Font.BOLD, 16);
	    panelIzquierdo.setBorder(BorderFactory.createEmptyBorder(100, 20, 100, 20));

	    for (int i = 0; i<6; i++) {
	    	opciones[i] = new BotonRedondeado(nombres[i]);
	    	opciones[i].setBackground(Color.decode("#ACCAF2"));
	    	opciones[i].addActionListener(this);
	    	opciones[i].setPreferredSize(new Dimension(200, 50));
	    	opciones[i].setFont(fontButton);
	    	constraints.gridy = i;
	    	constraints.weighty = 1;
	    	panelIzquierdo.add(opciones[i],constraints);
	    }
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	
		switch (e.getActionCommand()) {
		case "Administrar usuarios":
			setVisible(false);
			usuariosAdminFrame.setVisible(true);
			break;
		case "Tarifas":
			setContentPane(tarifasAdminFrame.getContentPane());
			setVisible(true);

			break;
		case "Servicios":
			setVisible(false);
			serviciosAdminFrame.setVisible(true);
			break;
		case "Habitaciones":
			setVisible(false);
			habitacionesAdminFrame.setVisible(true);
			break;
		case "Restaurante":
			setVisible(false);
			restauranteAdminFrame.setVisible(true);			
			break;
		case "Reservas":
			
			break;

		default:
			break;
		}

	}


}
