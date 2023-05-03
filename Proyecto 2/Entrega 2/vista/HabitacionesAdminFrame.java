package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class HabitacionesAdminFrame extends FrameBaseInfo{
	
	public HabitacionesAdminFrame(JFrame anterior) {
		super(anterior);
		setTitle("Habitaciones");
		
		
	}
	@Override
	protected void setPanelCrear() {
	
		panelCrear.setLayout(new BorderLayout());
		panelCrear.setPreferredSize(new Dimension(450, 0));
		panelCrear.setBackground(Color.decode("#204473"));
		
		JPanel panelInfo = new JPanel();
		panelInfo.setBackground(Color.decode("#204473"));	
		panelInfo.setLayout(new GridLayout(9, 1));
		
		
		JTextField cajaId = new JTextField("ID");
		JTextField cajaTipoHabitacion = new JTextField("Tipo Habitación");
		JTextField cajaCapacidadCama = new JTextField("Capacidad Cama");
		JTextField cajaApto = new JTextField("Apto Para Niños");
		JTextField cajaServicio = new JTextField("Servicio");
		JTextField cajaPrecio = new JTextField("Precio");
		
		JButton crearHabitacion = new JButton("Agregar servicio");
		crearHabitacion.setBackground(Color.CYAN);
		crearHabitacion.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
		
		panelInfo.add(cajaId);
		panelInfo.add(cajaTipoHabitacion);
		panelInfo.add(cajaCapacidadCama);
		panelInfo.add(cajaApto);
		panelInfo.add(cajaServicio);
		panelInfo.add(cajaPrecio);
		panelCrear.add(new JLabel( ));
		panelCrear.add(panelInfo);
		
		JPanel panelAgregar = new JPanel();
		panelAgregar.add(crearHabitacion);
		panelAgregar.setBackground(Color.decode("#204473"));
		panelCrear.add(panelAgregar);
		panelCrear.add(new JLabel( ));
		

	}
	
	@Override
	protected void setPanelInfo() {
		panelDerecho.setLayout(new FlowLayout());
		panelDerecho.setBackground(Color.decode("#b2bba4"));
		
		JLabel tituloTablaServicios = new JLabel("          Servicios            ");
		tituloTablaServicios.setOpaque(true);
		tituloTablaServicios.setBackground(Color.decode("#204473"));
		tituloTablaServicios.setForeground(Color.BLACK);
		tituloTablaServicios.setFont(new Font("Times New Roman", Font.PLAIN, 50));
		
		JPanel listaServicios = new JPanel(new GridLayout(6,1));
		listaServicios.add(tituloTablaServicios);
		
		panelDerecho.add(listaServicios);
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
		
		panelDerecho.add(buscar);
		
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
		
		panelDerecho.add(habitacion);
		
		

	}


}
