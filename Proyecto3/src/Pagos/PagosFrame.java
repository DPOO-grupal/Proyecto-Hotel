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

    public PagosFrame(WindowManager windowManager, double precioTotalFactura) {
        this.precioTotalFactura = precioTotalFactura;
    	setTitle("Formas de pago");
        setSize(600, 500);
        setLocation(560, 70);
        setLayout(new BorderLayout());
        listaDePagos = new ArrayList<>();
        listaDePagos.add("Visa");
        listaDePagos.add("Mastercard");
        listaDePagos.add("American Express");
        listaDePagos.add("Diners Club");
        listaDePagos.add("Diners Club");
        listaDePagos.add("Diners Club");
        listaDePagos.add("Diners Club");
        listaDePagos.add("Diners Club");
        listaDePagos.add("Diners Club");
        listaDePagos.add("Diners Club");
        
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
		//panelCentral.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		
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
      
    public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {		
		case "Cancelar":
			setVisible(false);
			break;
			
		case "Continuar":
			datosPago();
			setVisible(false);
			break;

		default:
			break;
		}
	}
    
}

