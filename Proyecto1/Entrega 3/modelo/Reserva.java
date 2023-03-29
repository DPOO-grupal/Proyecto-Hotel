package modelo;

import java.util.Date;

public class Reserva {
	
	private Date fechaI;
	private Date fechaF;
	private double precioReserva;
	
	public Reserva(Date fechaI, Date fechaF) {
		this.fechaI = fechaI;
		this.fechaF = fechaF;
		this.precioReserva = 0;
	}

	public void setPrecioReserva(double precioReserva) {
		this.precioReserva = precioReserva;
	}

}
