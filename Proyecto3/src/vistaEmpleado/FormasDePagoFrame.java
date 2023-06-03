package vistaEmpleado;

import javax.swing.*;

import controlador.WindowManager;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormasDePagoFrame extends JFrame implements ActionListener{

    private JRadioButton efectivo;
    private JRadioButton tarjeta;
    private JRadioButton transferencia;
	private JButton cancelar;
	private JButton continuar;

    public FormasDePagoFrame(WindowManager windowManager) {
        setTitle("Formas de pago");
        setSize(600, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        panelOpcionesPagos();
        panelValorFactura();
        panelBotones();
    }
    
    public void panelOpcionesPagos() {
    	JPanel panelPagos = new JPanel();
    	panelPagos.setLayout(new GridLayout(4, 1));
    	panelPagos.setBackground(Color.decode("#b2bba4"));
    	panelPagos.setBorder(BorderFactory.createEmptyBorder(20, 10, 200, 20));


        JLabel titlePago = new JLabel("SELECCIONE UNA FORMA DE\n PAGO");
        titlePago.setFont(new Font("arial", 1, 18));
        panelPagos.add(titlePago);

        efectivo = new JRadioButton("Efectivo");
        efectivo.setFont(new Font("arial", 1, 20));
        panelPagos.add(efectivo);

        tarjeta = new JRadioButton("Tarjeta");
        tarjeta.setFont(new Font("arial", 1, 20));
        panelPagos.add(tarjeta);

        transferencia = new JRadioButton("Transferencia");
        transferencia.setFont(new Font("arial", 1, 20));
        panelPagos.add(transferencia);
        

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(efectivo);
        buttonGroup.add(tarjeta);
        buttonGroup.add(transferencia);


        add(panelPagos, BorderLayout.CENTER);
	}
    
    public void panelValorFactura () {
    	JPanel panelValor = new JPanel();
    	panelValor.setLayout(new GridLayout(2,1));
    	panelValor.setBackground(Color.decode("#ccd2c2"));
    	panelValor.setBorder(BorderFactory.createEmptyBorder(100, 10, 200, 10));
    	
    	JLabel valorFactura = new JLabel("VALOR A PAGAR");
    	valorFactura.setFont(new Font("Arial", 1, 25));
    	
    	JLabel valor = new JLabel("120000");
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
		panelBotones.add(cancelar);
		
		continuar = new JButton("Continuar");
		continuar.setFont(new Font("Arial", Font.PLAIN, 20));
		continuar.setBackground(Color.decode("#204473"));
		cancelar.setForeground(Color.WHITE);
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

