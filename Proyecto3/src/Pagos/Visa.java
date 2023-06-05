package Pagos;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class Visa extends FormasDePago{

	private int numeroTrasanccion = 0;

	public Visa() {
		path = "data/VisaDatos.txt";
		tarjetas = new HashMap<>();
		cargarDatosPagos();
	}

	@Override
	public void registrarPago(int montoPagado) {
		String contenido = "Código de transacción: VIS-" + numeroTrasanccion  + ", Monto pagado: " + montoPagado + ",\n";
		numeroTrasanccion ++;

        try {
            FileWriter escritor = new FileWriter("data/RegistrosVisa.txt", true); // La ruta del archivo y el segundo parámetro "true" indica que se debe agregar al final del archivo

            escritor.write(contenido);
            escritor.close();

            System.out.println("Contenido agregado al archivo correctamente.");
        } catch (IOException e) {
            System.out.println("Ocurrió un error al agregar contenido al archivo: " + e.getMessage());
        }
		
	}
	
}
