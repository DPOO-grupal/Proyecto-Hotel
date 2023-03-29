package modelo;

import java.util.Date;

public class Reserva {
	
	private Date fechaI;
	private Date fechaF;
	private double precioReserva;
	
	public Reserva(Date fechaI, Date fechaF) {
		/*FALTA ESTABLECER EL PRECIO USANDO LAS TARIFAS*/
		this.fechaI = fechaI;
		this.fechaF = fechaF;
	}

}
