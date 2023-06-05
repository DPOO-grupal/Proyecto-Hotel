package Pagos;

import javax.swing.*;

import controlador.WindowManager;
import vistaEmpleado.EmpleadoMenuPrincipal;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.AreaAveragingScaleFilter;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;

import Pagos.*;

public class PagosFrame extends JFrame implements ActionListener{

    private JButton cancelar;
	private JButton continuar;
	private ArrayList<String> listaDePagos;
	private JRadioButton formaDePago;
	private double precioTotalFactura;
	private JFrame datosPagoFrame;
	private JTextField cajaNombre;
	private JTextField cajaDocumento;
	private JTextField cajaCelular;
	private JTextField cajaNumeroTarjeta;
	private JTextField cajaFechaVencimiento;
	private JTextField cajaNumeroSeguridad;
	private JButton botonEfectuarPago;
	private JTextField cajaApellido;
	private WindowManager windowManager;
	private ArrayList<JRadioButton> listaBotones; 
	private String pasarelaString;
	private FormasDePago pasarela;
	private Tarjeta tarjeta;
	private int idGrupo;
	private boolean debeHacerCheckOut = false;
	
	public PagosFrame(WindowManager windowManager, double precioTotalFactura, String idGrupo) {
        this.precioTotalFactura = precioTotalFactura;
        this.idGrupo=Integer.parseInt(idGrupo);
        this.windowManager=windowManager;
        listaBotones = new ArrayList<>();
        pasarelaString = "Default";
    	setTitle("Formas de pago");
        setSize(600, 500);
        setLocation(560, 70);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setLayout(new BorderLayout());
        listaDePagos = new ArrayList<>();
        
        try {
            FileReader lector = new FileReader(new File("datosPagos/Pasarelas.txt"));
            BufferedReader buffer = new BufferedReader(lector);

            String linea;
            while ((linea = buffer.readLine()) != null) {
                listaDePagos.add(linea);
            }

            buffer.close();
            lector.close();
        } catch (IOException e) {
            System.out.println("Ocurrió un error al leer el archivo: " + e.getMessage());
        }
        
        panelOpcionesPagos();
        panelValorFactura();
        panelBotones();
    }
    
    public void panelOpcionesPagos() {
    	JPanel panelPagos = new JPanel();
    	panelPagos.setLayout(new GridLayout(1+listaDePagos.size(),1));
    	panelPagos.setBackground(Color.decode("#b2bba4"));
    	panelPagos.setBorder(BorderFactory.createEmptyBorder(20, 10, 130, 20));


        JLabel titlePago = new JLabel("SELECCIONE UNA FORMA DE PAGO");
        titlePago.setFont(new Font("arial", 1, 18));
        panelPagos.add(titlePago);
        
        ButtonGroup grupoBotonesPago = new ButtonGroup();
        for (String nombrePago : listaDePagos) {
        	formaDePago = new JRadioButton(nombrePago);
        	grupoBotonesPago.add(formaDePago);
        	formaDePago.setFont(new Font("arial", Font.PLAIN, 20));
        	formaDePago.addActionListener(this);
        	listaBotones.add(formaDePago);
            panelPagos.add(formaDePago);
		}
        JScrollPane scroll = new JScrollPane(panelPagos);
        add(scroll, BorderLayout.CENTER);
	}
    
    public String formatoComas(double valorInicial) {
		NumberFormat nf= NumberFormat.getInstance();
		nf.setMaximumFractionDigits(0);
	    return nf.format(valorInicial);
	}
    
    public void panelValorFactura () {
    	JPanel panelValor = new JPanel();
    	panelValor.setLayout(new GridLayout(2,1));
    	panelValor.setBackground(Color.decode("#ccd2c2"));
    	panelValor.setBorder(BorderFactory.createEmptyBorder(100, 10, 200, 10));
    	
    	
    	
    	JLabel valorFactura = new JLabel("VALOR A PAGAR");
    	valorFactura.setFont(new Font("Arial", 1, 25));
    	
    	JLabel valor = new JLabel(formatoComas(precioTotalFactura));
    	valor.setFont(new Font("Arial", Font.PLAIN, 20));
    	
    	panelValor.add(valorFactura);
    	panelValor.add(valor);
    	

    	add(panelValor, BorderLayout.EAST);
    	
    }
    
    public void panelBotones() {
		JPanel panelBotones = new JPanel();
		panelBotones.setLayout(new GridLayout(1,2,70,0));
		panelBotones.setBackground(Color.decode("#7E8C69"));
		panelBotones.setBorder(BorderFactory.createEmptyBorder(25, 100, 25, 100));
		
		cancelar = new JButton("Cancelar");
		cancelar.setFont(new Font("Arial", Font.PLAIN, 20));
		cancelar.setBackground(Color.decode("#204473"));
		cancelar.setForeground(Color.WHITE);
		cancelar.addActionListener(this);
		panelBotones.add(cancelar);
		
		continuar = new JButton("Continuar");
		continuar.setFont(new Font("Arial", Font.PLAIN, 20));
		continuar.setBackground(Color.decode("#204473"));
		continuar.setForeground(Color.WHITE);
		continuar.addActionListener(this);
		panelBotones.add(continuar);
		
		add(panelBotones, BorderLayout.SOUTH);
		
	}
    
    public void datosPago() {
		datosPagoFrame = new JFrame();
		datosPagoFrame.setSize(600, 500);
		datosPagoFrame.setLocation(560, 70);
		
		JPanel panelCentral = new JPanel();
		GridBagConstraints constraints = new GridBagConstraints();
		panelCentral.setLayout(new GridBagLayout());
		panelCentral.setBackground(Color.decode("#b2bba4"));
		
		Font fontGeneralTitulos = new Font("Arial", 1, 20);
		Font fontGeneral = new Font("Arial", Font.PLAIN, 20);
		
		JPanel nombrePanel = new JPanel();
		nombrePanel.setBackground(Color.decode("#b2bba4"));
		nombrePanel.setLayout(new GridLayout(2,1));
		
		JLabel nombre = new JLabel("Nombre                ");
		nombre.setFont(fontGeneralTitulos);
		
		cajaNombre = new JTextField();
		cajaNombre.setFont(fontGeneral);
		
		nombrePanel.add(nombre);
		nombrePanel.add(cajaNombre);
		
		constraints.ipadx = 90;
		constraints.ipady = 40;
		constraints.insets = new Insets(0, 10, 0, 10);
		
		panelCentral.add(nombrePanel, constraints);
		
		JPanel apellidoPanel = new JPanel();
		apellidoPanel.setBackground(Color.decode("#b2bba4"));
		apellidoPanel.setLayout(new GridLayout(2,1));
		
		JLabel apellido = new JLabel("Apellido                ");
		apellido.setFont(fontGeneralTitulos);
		
		cajaApellido = new JTextField();
		cajaApellido.setFont(fontGeneral);
		
		apellidoPanel.add(apellido);
		apellidoPanel.add(cajaApellido);
		
		constraints.ipadx = 90;
		constraints.ipady = 40;
		constraints.gridy = 1;
		
		panelCentral.add(apellidoPanel, constraints);
		
		JPanel documentoPanel = new JPanel();
		documentoPanel.setBackground(Color.decode("#b2bba4"));
		documentoPanel.setLayout(new GridLayout(2,1));
		
		JLabel documento = new JLabel("Documento          ");
		documento.setFont(fontGeneralTitulos);
		
		cajaDocumento = new JTextField();
		cajaDocumento.setFont(fontGeneral);
		
		documentoPanel.add(documento);
		documentoPanel.add(cajaDocumento);
		
		constraints.ipadx = 90;
		constraints.ipady = 40;
		constraints.gridx = 1;
		constraints.gridy = 0;
		
		
		panelCentral.add(documentoPanel, constraints);
		
		JPanel celularPanel = new JPanel();
		celularPanel.setBackground(Color.decode("#b2bba4"));
		celularPanel.setLayout(new GridLayout(2,1));
		
		JLabel celular = new JLabel("Celular                 ");
		celular.setFont(fontGeneralTitulos);
		
		cajaCelular = new JTextField();
		cajaCelular.setFont(fontGeneral);
		
		celularPanel.add(celular);
		celularPanel.add(cajaCelular);
		
		constraints.ipadx = 90;
		constraints.ipady = 40;
		constraints.gridx = 1;
		constraints.gridy = 1;
		
		panelCentral.add(celularPanel, constraints);
		
		JPanel numeroTarjetaPanel = new JPanel();
		numeroTarjetaPanel.setBackground(Color.decode("#b2bba4"));
		numeroTarjetaPanel.setLayout(new GridLayout(2,1));
		
		JLabel numeroTarjeta = new JLabel("Numero tarjeta                                                     ");
		numeroTarjeta.setFont(fontGeneralTitulos);
		
		cajaNumeroTarjeta = new JTextField();
		cajaNumeroTarjeta.setFont(fontGeneral);
		
		numeroTarjetaPanel.add(numeroTarjeta);
		numeroTarjetaPanel.add(cajaNumeroTarjeta);
		
		constraints.ipadx = 90;
		constraints.ipady = 40;
		constraints.gridwidth = 2;
		constraints.gridx = 0;
		constraints.gridy = 2;
		
		panelCentral.add(numeroTarjetaPanel, constraints);
		
		JPanel fechaVencimientoPanel = new JPanel();
		fechaVencimientoPanel.setBackground(Color.decode("#b2bba4"));
		fechaVencimientoPanel.setLayout(new GridLayout(2,1));
		
		JLabel fechaVencimiento = new JLabel("Fecha vencimiento");
		fechaVencimiento.setFont(fontGeneralTitulos);
		
		cajaFechaVencimiento = new JTextField();
		cajaFechaVencimiento.setFont(fontGeneral);
		
		fechaVencimientoPanel.add(fechaVencimiento);
		fechaVencimientoPanel.add(cajaFechaVencimiento);
		
		constraints.ipadx = 90;
		constraints.ipady = 40;
		constraints.gridwidth = 1;
		constraints.gridx = 0;
		constraints.gridy = 3;
		
		panelCentral.add(fechaVencimientoPanel, constraints);
		
		JPanel numeroSeguridadPanel = new JPanel();
		numeroSeguridadPanel.setBackground(Color.decode("#b2bba4"));
		numeroSeguridadPanel.setLayout(new GridLayout(2,1));
		
		JLabel numeroSeguridad = new JLabel("Numero seguridad");
		numeroSeguridad.setFont(fontGeneralTitulos);
		
		cajaNumeroSeguridad = new JTextField();
		cajaNumeroSeguridad.setFont(fontGeneral);
		
		numeroSeguridadPanel.add(numeroSeguridad);
		numeroSeguridadPanel.add(cajaNumeroSeguridad);
		
		constraints.ipadx = 90;
		constraints.ipady = 40;
		constraints.gridx = 1;
		constraints.gridy = 3;
		
		panelCentral.add(numeroSeguridadPanel, constraints);
		
		JPanel botonEfectuarPagoPanel = new JPanel();
		botonEfectuarPagoPanel.setLayout(new GridLayout());
		botonEfectuarPagoPanel.setBackground(Color.decode("#b2bba4"));
		botonEfectuarPagoPanel.setBorder(BorderFactory.createEmptyBorder(10, 100, 10, 100));
		
		
		botonEfectuarPago = new JButton("Efectuar pago");
		botonEfectuarPago.setFont(new Font("Arial", 1, 20));
		botonEfectuarPago.setBackground(Color.decode("#204473"));
		botonEfectuarPago.setForeground(Color.WHITE);
		botonEfectuarPago.addActionListener(this);
		
		botonEfectuarPagoPanel.add(botonEfectuarPago);
		
		constraints.gridx = 0;
		constraints.gridy = 5;
		constraints.gridwidth = 2;
		constraints.weighty = 50;
		
		panelCentral.add(botonEfectuarPagoPanel, constraints);
		
		datosPagoFrame.add(panelCentral);
		
		datosPagoFrame.setVisible(true);
		
		
	}
    
    public void autenticar() {
    	String documento = cajaDocumento.getText();
    	int numeroTarjeta = Integer.parseInt(cajaNumeroTarjeta.getText());
    	String fechaVencimiento = cajaFechaVencimiento.getText();
    	int numeroSeguridad = Integer.parseInt(cajaNumeroSeguridad.getText());
    	pasarela = null;
    	for (int i = 0; i < listaBotones.size(); i++) {
    		if (pasarelaString.equals(listaBotones.get(i).getText())) {
    			//pasarela.auntenticar();
				try {
					String nombreDeClase = "Pagos."+pasarelaString;
					Class<?> clase = Class.forName(nombreDeClase);
					pasarela = (FormasDePago) clase.newInstance();
					//tarjetaAutenticada.autenticar(documento, numeroTarjeta, fechaVencimiento, numeroSeguridad);
					//clase.getMethod("autenticar").invoke(instancia);
					
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
    		}
		}
    	tarjeta = pasarela.autenticar(documento, numeroTarjeta, fechaVencimiento, numeroSeguridad);
	}
    
    private boolean verificarMonto(int precioTotalFactura) {
    	return pasarela.verificarMonto(precioTotalFactura, tarjeta.getNumeroTarjeta());
    }
      
    public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {		
		case "Cancelar":
			setVisible(false);
			JOptionPane.showMessageDialog(null, "Pago cancelado");
			break;
			
		case "Continuar":
			boolean funciona = false;
			for (JRadioButton jRadioButton : listaBotones) {
				if (jRadioButton.isSelected()) {
					funciona = true;
					pasarelaString = jRadioButton.getText();
					datosPago();
					setVisible(false);
				}
			}
			if (!funciona)
				JOptionPane.showMessageDialog(null, "Seleccione un metodo de pago");
			break;
			
		case "Efectuar pago":
			autenticar();
			if (tarjeta!=null) {
				boolean montoVerificado = verificarMonto((int) precioTotalFactura);
				if (montoVerificado) {
					windowManager.añadirLogFacturas(precioTotalFactura);
					pasarela.registrarPago((int) precioTotalFactura);
					JOptionPane.showMessageDialog(null, "Pago exitoso");
					datosPagoFrame.dispose();
					setEnabled(true);
					dispose();
					if (debeHacerCheckOut) {
						windowManager.checkOut(idGrupo);
					}
					else {
						JOptionPane.showMessageDialog(null, "Su reserva no se cobrará al hacer CheckOut. Tan solo deberá pagar por lo que consuma durante su estadía en el hotel. Nos vemos pronto ;D");
					}
					windowManager.cargarMenuPrincipal();
					
				}else {
					JOptionPane.showMessageDialog(null, "Monto insuficiente. Escoja otro método de pago o inténtelo de nuevo cuando tenga monto suficiente");
					datosPagoFrame.dispose();
					setEnabled(true);
					dispose();
				}
			} else {
				JOptionPane.showMessageDialog(null, "Datos incorrectos", "Error de autenticación", JOptionPane.ERROR_MESSAGE);
				resetDatos();
			}
			break;

		default:
			break;
		}
	}

	private void resetDatos() {
		cajaNombre.setText("");
		cajaApellido.setText("");
		cajaDocumento.setText("");
		cajaCelular.setText("");
		cajaNumeroTarjeta.setText("");
		cajaFechaVencimiento.setText("");
		cajaNumeroSeguridad.setText("");
		
		
	}

	public void setDebeHacerCheckOut(boolean debeHacerCheckOut) {
		this.debeHacerCheckOut = debeHacerCheckOut;
	}

    
}

