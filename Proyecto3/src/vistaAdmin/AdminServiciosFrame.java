package vistaAdmin;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Collection;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
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
import javax.swing.text.NumberFormatter;

import controlador.WindowManager;
import modelo.ProductoMenu;
import modelo.Servicio;
import vistaEmpleado.EmpleadoServiciosFrame;

public class AdminServiciosFrame extends EmpleadoServiciosFrame implements ActionListener, MouseListener, KeyListener{
	
	private JButton agregarServicio;
	private JButton eliminarServicio;
	private JButton agregar;
	private JTextField cajaNombreAgregar;
	private JTextField cajaPrecioAgregar;
	private JFrame frameAgregar;

	public AdminServiciosFrame(WindowManager windowManager) {
		super(windowManager);
		
	}
	
	@Override
	protected void setPanelInfo() {
		super.setPanelInfo();
        GridBagConstraints constraints = new GridBagConstraints();
		//Creación boton agregar servicio
		agregarServicio = new JButton("Agregar servicio");
		agregarServicio.addActionListener(this);
		agregarServicio.setBackground(Color.decode("#accaf2"));
		agregarServicio.setFont(new Font("arial", 1, 25));
		agregarServicio.setPreferredSize(new Dimension(200,40));
		
		//Tamaño y ubicacion en el panel
		constraints.gridy = 4;
		constraints.gridx = 0;
        constraints.gridheight = 1;
        constraints.gridwidth = 1;
        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.ipady = 20 ;
        constraints.insets = new Insets(10, 0, 10, 0);
		
		panelDerecho.add(agregarServicio, constraints);
		
		//Boton para añadir un servicio
		eliminarServicio = new JButton("Eliminar servicio");
		eliminarServicio.addActionListener(this);
		eliminarServicio.setBackground(Color.decode("#accaf2"));
		eliminarServicio.setFont(new Font("arial", 1, 25));
		eliminarServicio.setPreferredSize(new Dimension(200,40));

		//Tamaño y ubicacion en el panel
		constraints.gridy = 4;
		constraints.gridx = 1;
        constraints.gridheight = 1;
        constraints.gridwidth = 1;
        constraints.weightx = 1;
        constraints.weighty = 1;
		
		panelDerecho.add(eliminarServicio, constraints);
	}
	
	private void agregarServicio() {
		frameAgregar = new JFrame();
		frameAgregar.setSize(300, 300);
		frameAgregar.setLocationRelativeTo(null);
		
		frameAgregar.setLayout(new GridLayout(3, 1));
		frameAgregar.setBackground(Color.decode("#ccd2c2"));
		
		//Crea el panel para agregar un servicio
		JPanel panelNombre = new JPanel();
		panelNombre.setBackground(Color.decode("#ccd2c2"));	
		panelNombre.setLayout(new GridLayout(2, 1));
		panelNombre.setBorder(BorderFactory.createEmptyBorder(20, 40, 0, 40));
		
		//Nombre y su caja de texto
		
		JLabel nombre = new JLabel("Nombre");
		nombre.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		
		cajaNombreAgregar = new JTextField();
		cajaNombreAgregar.setFont(new Font("Times New Roman", Font.PLAIN, 30));

		
		panelNombre.add(nombre);
		panelNombre.add(cajaNombreAgregar);
		
		//Panel precio
		JPanel panelPrecio = new JPanel();
		panelPrecio.setBackground(Color.decode("#ccd2c2"));	
		panelPrecio.setLayout(new GridLayout(2, 1));
		panelPrecio.setBorder(BorderFactory.createEmptyBorder(20, 40, 0, 40));
		
		//Precio y su caja de texto
		JLabel precio = new JLabel("Precio");
		precio.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		
		cajaPrecioAgregar = new JTextField();
		cajaPrecioAgregar.addKeyListener(this);
		cajaPrecioAgregar.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		
		panelPrecio.add(precio);
		panelPrecio.add(cajaPrecioAgregar);
		
		//Panel boton agregar
		JPanel panelAgregar = new JPanel();
		panelAgregar.setBackground(Color.decode("#ccd2c2"));
		panelAgregar.setBorder(BorderFactory.createEmptyBorder(20, 40, 0, 40));
		
		//Creación boton agregar servicio
		agregar = new JButton("Agregar");
		agregar.addActionListener(this);
		agregar.setBackground(Color.decode("#204473"));
		agregar.setForeground(Color.WHITE);
		agregar.setFont(new Font("arial", 1, 25));
		
		panelAgregar.add(agregar);
		
		frameAgregar.add(panelNombre);
		frameAgregar.add(panelPrecio);
		frameAgregar.add(panelAgregar);
		
		frameAgregar.setVisible(true);
	}

	private void agregar() {
		try {
			String nombre = cajaNombreAgregar.getText();
			Double precio = Double.parseDouble(cajaPrecioAgregar.getText());
			if (!(verificarExistencia(nombre))) {
				windowManager.agregarServicioHotel(nombre, precio);
				cargarDatos();
				cajaNombreAgregar.setText("");
				cajaPrecioAgregar.setText("");
				frameAgregar.setVisible(false);
			}
			else {
				int option = JOptionPane.showConfirmDialog(null, "Ya existe el servicio.\n Desea cambiar sus valores?", null, JOptionPane.YES_NO_OPTION);
				if (option == 0) {
					editarServicio(nombre);
					windowManager.agregarServicioHotel(nombre, precio);
					cargarDatos();
					cajaNombreAgregar.setText("");
					cajaPrecioAgregar.setText("");
					frameAgregar.setVisible(false);
				}
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Debes llenar todos los espacios");
		}
	}
	
	private void editarServicio(String nombre) {
		Collection<Servicio> listaProductoMenu = windowManager.darServicio().values();
		for(Servicio productoMenu : listaProductoMenu)
			if (productoMenu.getNombre().toLowerCase().equals(nombre.toLowerCase())) {
				windowManager.eliminarServicioHotel(getId(nombre));
			}
	}
	
	private void eliminarServicio() {
		String nombre = cajaNombre.getText();
		int id = getId(nombre);
		windowManager.eliminarServicioHotel(id);
		cargarDatos();
		cajaNombre.setText("");
		cajaPrecio.setText("");
	}
	
	private boolean verificarExistencia(String nombre) {
		Collection<Servicio> listaServicios = windowManager.darServicio().values();
		boolean existe = false;
		for(Servicio servicio : listaServicios)
			if (servicio.getNombre().toLowerCase().equals(nombre.toLowerCase())) {
				existe = true;
			}
		return existe;
		
	}
	
	public void actionPerformedFrame(ActionEvent e) {
		super.actionPerformedFrame(e);
		switch (e.getActionCommand()) {				
		case "Agregar servicio":
			agregarServicio();
			break;
			
		case "Agregar":
			agregar();
			break;
			
		case "Eliminar servicio":
			eliminarServicio();
			break;

		default:
			break;
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
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

