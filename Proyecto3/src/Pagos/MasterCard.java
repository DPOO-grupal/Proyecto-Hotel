package Pagos;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Random;

public class MasterCard extends FormasDePago{
	
	private int numeroTrasanccion = 0;

	public MasterCard() {
		path = "datosPagos/MasterCardDatos.txt";
		tarjetas = new HashMap<>();
		cargarDatosPagos();
	}

	@Override
	public void registrarPago(int montoPagado) {
		Random random = new Random();
		String contenido = "Código de transacción: MASC-" + random.nextInt(9999) + ", Monto pagado: " + montoPagado + ",\n";
		numeroTrasanccion ++ ;
		
        try {
            FileWriter escritor = new FileWriter("datosPagos/RegistrosMasterCard.txt", true); // La ruta del archivo y el segundo parámetro "true" indica que se debe agregar al final del archivo

            escritor.write(contenido);
            escritor.close();

        } catch (IOException e) {
            System.out.println("Ocurrió un error al agregar contenido al archivo: " + e.getMessage());
        }
		
	}

}
