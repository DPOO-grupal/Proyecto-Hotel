package Pagos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FormasDePago {
	protected String nombreDueño;
	protected String numeroCelular;
	protected String documento;
	protected int numeroTarjeta;
	protected String fechaVencimiento;
	protected int numeroDeSeguridad;
	protected int monto;
	protected static int numeroTransaccion;
	protected String pasarela;
	protected static ArrayList<FormasDePago> listaTarjetas;
	
	public FormasDePago(String nombreDueño, String numeroCelular, String documento, int numeroTarjeta, String fechaVencimiento, int numeroDeSeguridad, int monto, String pasarela) {
		this.nombreDueño = nombreDueño;
		this.numeroCelular = numeroCelular;
		this.documento = documento;
		this.numeroTarjeta = numeroTarjeta;
		this.fechaVencimiento = fechaVencimiento;
		this.numeroDeSeguridad = numeroDeSeguridad;
		this.monto = monto;
		this.pasarela = pasarela;
		numeroTransaccion += 1;
		}
	
	public boolean autenticar(String nombre, String documento, int numeroTarjeta, String fechaDeVencimiento, int numeroDeSeguridad) {
		if (!(this.nombreDueño==nombre))
			return false;
		else if (!(this.documento==documento))
			return false;
		else if (!(this.numeroTarjeta==numeroTarjeta))
			return false;
		else if (!(this.fechaVencimiento==fechaDeVencimiento))
			return false;
		else if (!(this.numeroDeSeguridad==numeroDeSeguridad))
			return false;
		else 
			return true;
	}
	
	protected boolean verificarMonto(int saldoAPagar) {
		if (saldoAPagar<=this.monto) 
			return true;
		else 
			return false;		
	}
	
	public void registrarPago() {
		
	}
	
	public static void cargarDatosPagos() {
        try {
            FileReader lector = new FileReader(new File("./data/DatosDePagos.txt"));
            BufferedReader buffer = new BufferedReader(lector);

            String linea;
            while ((linea = buffer.readLine()) != null) {
                System.out.println(linea);
                String[] formaDePago = linea.split(",");
                String nombre = formaDePago[0];
                String numeroCelular = formaDePago[1];
                String documento = formaDePago[2];
                int numeroTarjeta = Integer.parseInt(formaDePago[3]);
                String fechaVencimiento = formaDePago[4];
                int numeroSeguridad = Integer.parseInt(formaDePago[5]);
                int monto = Integer.parseInt(formaDePago[6]);
                String pasarela = formaDePago[7];
                
                FormasDePago tarjeta = new FormasDePago(nombre, numeroCelular, documento, numeroTarjeta, fechaVencimiento, numeroSeguridad, monto, pasarela);
                listaTarjetas.add(tarjeta);
            
            }

            buffer.close();
            lector.close();
        } catch (IOException e) {
            System.out.println("Ocurrió un error al leer el archivo: " + e.getMessage());
        }
    }
		
}
