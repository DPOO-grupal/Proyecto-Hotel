package Pagos;

public class Mastercard extends FormasDePago{

	public Mastercard(String nombreDueño, String numeroCelular, String documento, int numeroTarjeta, String fechaVencimiento, int numeroDeSeguridad, int monto, int numeroTransaccion) {
		super(nombreDueño, numeroCelular, documento, numeroTarjeta, fechaVencimiento, numeroDeSeguridad, monto, numeroTransaccion);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void registrarPago() {
		// TODO Auto-generated method stub
		
	}

}
