package Pagos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class FormasDePago {
	
	protected String path;
	protected HashMap<Integer, Tarjeta> tarjetas;
	
	public Tarjeta autenticar(String documento, int numeroTarjeta, String fechaDeVencimiento, int numeroDeSeguridad) {
		Tarjeta tarjeta = new Tarjeta(null, null, documento, numeroTarjeta, fechaDeVencimiento, numeroDeSeguridad, 0);
		if (tarjetas.get(numeroTarjeta) == null) {
			return null;
		}
		else if(tarjetas.get(numeroTarjeta).equals(tarjeta)){
			return tarjetas.get(numeroTarjeta);
		} else {
			return null;
		}
	}
	
	protected boolean verificarMonto(int saldoAPagar, int numeroDeTarjeta) {
		Tarjeta tarjeta = tarjetas.get(numeroDeTarjeta);
		return saldoAPagar<=tarjeta.getMonto();
	}
	
	public abstract void registrarPago(int montoPagado);
	
	
	public void cargarDatosPagos() {
        try {
            FileReader lector = new FileReader(new File(path));
            BufferedReader buffer = new BufferedReader(lector);
            String linea;
            while ((linea = buffer.readLine()) != null) {
                String[] formaDePago = linea.split(",");
                String nombre = formaDePago[0];
                String numeroCelular = formaDePago[1];
                String documento = formaDePago[2];
                int numeroTarjeta = Integer.parseInt(formaDePago[3]);
                String fechaVencimiento = formaDePago[4];
                int numeroSeguridad = Integer.parseInt(formaDePago[5]);
                int monto = Integer.parseInt(formaDePago[6]);
                System.out.println("FormasDePago.cargarDatosPagos()");
                System.out.println(numeroTarjeta);
                Tarjeta tarjeta = new Tarjeta(nombre, numeroCelular, documento, numeroTarjeta, fechaVencimiento, numeroSeguridad, monto);
                tarjetas.put(numeroTarjeta, tarjeta);
            }
            buffer.close();
            lector.close();
        } catch (IOException e) {
            System.out.println("Ocurrió un error al leer el archivo: " + e.getMessage());
        }
    }	
}
