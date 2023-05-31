package vistaAdmin;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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


public abstract class FrameBaseInfo extends JFrame implements ActionListener{
	protected JPanel panelVolver;
	protected JPanel panelCrear;
	protected JPanel panelDerecho;
	protected JPanel panelIzquierdo;
	protected JButton volverButton;
	protected JTextField[] datos;
	protected JButton addDatos;
	protected WindowManager windowManager;
    
	
	public FrameBaseInfo(WindowManager windowManager) {
		this.windowManager = windowManager;
		
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
		//setResizable(false);
		setExtendedState(MAXIMIZED_BOTH);
		setResizable(false);
		
		
	}
	

	protected abstract void setPanelInfo();
	protected abstract void setPanelCrear();


	private void setPanelVolver() {
		// Configuracion General
	    panelVolver.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 65));
	    panelVolver.setBackground(Color.decode("#7E8C69"));

	    // Crear Boton redondeado
	    Font font = new Font("Arial", Font.BOLD, 20);
	    
	    volverButton = new JButton("Volver");
	    volverButton.setPreferredSize(new Dimension(200, 60));
	    volverButton.setBackground(Color.decode("#D0ECF2"));
	    volverButton.setFont(font);
	    volverButton.addActionListener(this);
	    // AÃ±adirlo al Panel
	    panelVolver.add(volverButton);
	}
	
	public void volverMenu() {
		windowManager.volverMenu();
	}
	public abstract void resetDatos();
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Volver")) {
			setVisible(false);
			resetDatos();
			volverMenu();
		
		} else {
			actionPerformedFrame(e);
		}
	}
	
	protected abstract void actionPerformedFrame(ActionEvent e);


}