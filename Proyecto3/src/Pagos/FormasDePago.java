package Pagos;

public abstract class FormasDePago {
	protected String nombreDueño;
	protected String numeroCelular;
	protected String documento;
	protected int numeroTarjeta;
	protected String fechaVencimiento;
	protected int numeroDeSeguridad;
	protected int monto;
	protected static int numeroTransaccion;
	
	public FormasDePago(String nombreDueño, String numeroCelular, String documento, int numeroTarjeta, String fechaVencimiento, int numeroDeSeguridad, int monto, int numeroDeTransaccion) {
		this.nombreDueño = nombreDueño;
		this.numeroCelular = numeroCelular;
		this.documento = documento;
		this.numeroTarjeta = numeroTarjeta;
		this.fechaVencimiento = fechaVencimiento;
		this.numeroDeSeguridad = numeroDeSeguridad;
		this.monto = monto;
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
	
	public boolean verificarMonto(int saldoAPagar) {
		if (saldoAPagar<=this.monto) 
			return true;
		else 
			return false;		
	}
	
	public abstract void registrarPago();
}
