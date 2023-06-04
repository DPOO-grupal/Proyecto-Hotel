package Pagos;

import java.io.FileWriter;
import java.io.IOException;

public class Visa extends FormasDePago{
	public Visa(String nombreDueño, String numeroCelular, String documento, int numeroTarjeta, String fechaVencimiento, int numeroDeSeguridad, int monto, int numeroTransaccion, String pasarela) {
		super(nombreDueño, numeroCelular, documento, numeroTarjeta, fechaVencimiento, numeroDeSeguridad, monto, pasarela);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void registrarPago() {
        String contenido = "Pago realizado con Visa, numero de transaccion: " + numeroTransaccion;

        try {
            FileWriter escritor = new FileWriter("ruta_del_archivo.txt", true); // La ruta del archivo y el segundo parámetro "true" indica que se debe agregar al final del archivo

            escritor.write(contenido);
            escritor.close();

            System.out.println("Contenido agregado al archivo correctamente.");
        } catch (IOException e) {
            System.out.println("Ocurrió un error al agregar contenido al archivo: " + e.getMessage());
        }
    }

	
	protected boolean verificarMonto(int saldoAPagar) {
		return verificarMonto(saldoAPagar);
	}
}
