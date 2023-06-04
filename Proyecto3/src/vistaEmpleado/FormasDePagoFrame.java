package vistaEmpleado;

import javax.swing.*;

import controlador.WindowManager;
import vistaEmpleado.EmpleadoMenuPrincipal;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class FormasDePagoFrame extends JFrame implements ActionListener{

    private JButton cancelar;
	private JButton continuar;
	private ArrayList<String> listaDePagos;
	private JRadioButton formaDePago;
	private double precioTotalFactura;

    public FormasDePagoFrame(WindowManager windowManager) {
        this.precioTotalFactura = EmpleadoMenuPrincipal.getPrecioTotalFactura();
        System.out.println(precioTotalFactura+"");
    	setTitle("Formas de pago");
        setSize(600, 500);
        setLocation(560, 70);
        setLayout(new BorderLayout());
        listaDePagos = new ArrayList<>();
        listaDePagos.add("Visa");
        listaDePagos.add("Mastercard");
        listaDePagos.add("American Express");
        listaDePagos.add("Diners Club");
        panelOpcionesPagos();
        panelValorFactura();
        panelBotones();
    }
    
    public void panelOpcionesPagos() {
    	JPanel panelPagos = new JPanel();
    	panelPagos.setLayout(new GridLayout(5, 1));
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
        
        add(panelPagos, BorderLayout.CENTER);
	}
    
    public void panelValorFactura () {
    	JPanel panelValor = new JPanel();
    	panelValor.setLayout(new GridLayout(2,1));
    	panelValor.setBackground(Color.decode("#ccd2c2"));
    	panelValor.setBorder(BorderFactory.createEmptyBorder(100, 10, 200, 10));
    	
    	
    	
    	JLabel valorFactura = new JLabel("VALOR A PAGAR");
    	valorFactura.setFont(new Font("Arial", 1, 25));
    	
    	JLabel valor = new JLabel(precioTotalFactura+"");
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
    
      
    public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {		
		case "Cancelar":
			setVisible(false);
			break;
			
		case "Continuar":
			break;

		default:
			break;
		}
	}
    
}

