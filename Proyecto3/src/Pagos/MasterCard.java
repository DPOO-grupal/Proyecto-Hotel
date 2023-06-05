package Pagos;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class MasterCard extends FormasDePago{
	
	private int numeroTrasanccion = 0;

	public MasterCard() {
		path = "data/MasterCardDatos.txt";
		tarjetas = new HashMap<>();
		cargarDatosPagos();
	}

	@Override
	public void registrarPago(int montoPagado) {
		String contenido = "Código de transacción: MASC-" + numeroTrasanccion + ", Monto pagado: " + montoPagado + ",\n";
		numeroTrasanccion ++ ;
		
        try {
            FileWriter escritor = new FileWriter("data/RegistrosMasterCard.txt", true); // La ruta del archivo y el segundo parámetro "true" indica que se debe agregar al final del archivo

            escritor.write(contenido);
            escritor.close();

            System.out.println("Contenido agregado al archivo correctamente.");
        } catch (IOException e) {
            System.out.println("Ocurrió un error al agregar contenido al archivo: " + e.getMessage());
        }
		
	}

}