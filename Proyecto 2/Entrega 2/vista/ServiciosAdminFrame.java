package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.PageAttributes;
import java.awt.Panel;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicSplitPaneUI.BasicHorizontalLayoutManager;

public class ServiciosAdminFrame extends JFrame{
	private JPanel panelVolver;
	private JPanel panelAgregarServicio;
	private JPanel panelServicios;
	
	public ServiciosAdminFrame() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(getMaximumSize());
		setLayout(new BorderLayout());
		setTitle("Servicios");
		
		panelAgregarServicio = new JPanel();
		ventanaAgregarServicio(panelAgregarServicio);
		add(panelAgregarServicio,BorderLayout.WEST);
		
		panelServicios = new JPanel();
		ventanaServicios(panelServicios);
		add(panelServicios, BorderLayout.CENTER);
		
	}
	
	private void ventanaAgregarServicio(JPanel panelAgregarServicio) {
		panelAgregarServicio.setLayout(new GridLayout(5, 1));
		panelAgregarServicio.setBackground(Color.decode("#204473"));
		
		JPanel panelInfo = new JPanel();
		panelInfo.setBackground(Color.decode("#204473"));	
		panelInfo.setLayout(new GridLayout(4, 1, 10, 10));
		panelInfo.setBounds(100, 100, 100, 100);
		
		JLabel nombre = new JLabel("Nombre");
		nombre.setForeground(Color.white);
		nombre.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		nombre.setBounds(30, 40, 300, 50);
		
		JTextField cajaNombre = new JTextField();
		cajaNombre.setCaretColor(Color.black);
		
		JLabel precio = new JLabel("Precio");
		precio.setForeground(Color.white);
		precio.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
		JTextField cajaPrecio = new JTextField();
		
		JButton agregarServicio = new JButton("Agregar servicio");
		agregarServicio.setBackground(Color.CYAN);
		agregarServicio.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
		
		panelInfo.add(nombre);
		panelInfo.add(cajaNombre);
		panelInfo.add(precio);
		panelInfo.add(cajaPrecio);
		panelAgregarServicio.add(new JLabel( ));
		panelAgregarServicio.add(panelInfo);
		
		JPanel panelAgregar = new JPanel();
		panelAgregar.add(agregarServicio);
		panelAgregar.setBackground(Color.decode("#204473"));
		panelAgregarServicio.add(panelAgregar);
		panelAgregarServicio.add(new JLabel( ));
		
		panelVolver = new JPanel();
		ventanaVolver(panelVolver);
		panelAgregarServicio.add(panelVolver);

	}
	
	private void ventanaServicios(JPanel panelServicios) {
		panelServicios.setLayout(new FlowLayout());
		panelServicios.setBackground(Color.decode("#b2bba4"));
		
		JLabel tituloTablaServicios = new JLabel("          Servicios            ");
		tituloTablaServicios.setOpaque(true);
		tituloTablaServicios.setBackground(Color.decode("#204473"));
		tituloTablaServicios.setForeground(Color.BLACK);
		tituloTablaServicios.setFont(new Font("Times New Roman", Font.PLAIN, 50));
		
		JPanel listaServicios = new JPanel(new GridLayout(6,1));
		listaServicios.add(tituloTablaServicios);
		
		panelServicios.add(listaServicios);
		JLabel nombre = new JLabel("Nombre");
		nombre.setForeground(Color.BLACK);
		nombre.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
		JTextField cajaNombre = new JTextField();
		
		JLabel precio = new JLabel("Precio");
		precio.setForeground(Color.BLACK);
		precio.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
		JTextField cajaPrecio = new JTextField();
		
		JPanel buscar = new JPanel(new GridLayout(4,1));
		buscar.setBackground(Color.decode("#accaf2"));
		buscar.add(nombre);
		buscar.add(cajaNombre);
		buscar.add(precio);
		buscar.add(cajaPrecio);
		
		panelServicios.add(buscar);
		
		JLabel numHabitacion = new JLabel("Número de habitacion");
		numHabitacion.setForeground(Color.BLACK);
		numHabitacion.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
		JTextField cajaNumeroHabitacion = new JTextField();
		
		JButton añadirAHabitacion = new JButton("Añadir habitación");
		añadirAHabitacion.setBackground(Color.decode("#204473"));
		añadirAHabitacion.setForeground(Color.BLACK);
		añadirAHabitacion.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
		JPanel habitacion = new JPanel(new GridLayout(3,1, 0, 5));
		habitacion.setBackground(Color.decode("#accaf2"));
		habitacion.add(numHabitacion);
		habitacion.add(cajaNumeroHabitacion);
		habitacion.add(añadirAHabitacion);
		
		panelServicios.add(habitacion);
		
		

	}
	
	private void ventanaVolver(JPanel panelVolver) {
		panelVolver.setBackground(Color.decode("#7e8c69"));
		
		JButton botonVolver = new JButton("Volver");
		botonVolver.setBackground(Color.decode("#d0ecf2"));
		botonVolver.setLocation(10, 12);
		
		panelVolver.add(botonVolver);
	}
}

