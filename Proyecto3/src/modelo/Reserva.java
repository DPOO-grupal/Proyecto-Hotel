package modelo;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

public class Reserva implements Serializable{
	
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
	
	public int getPrecioReservaDia() {
		System.out.println("Reserva.setPrecioReservaDia()");;
		Calendar calendarI = Calendar.getInstance();
		calendarI.setTime(fechaI);
		Calendar calendarF = Calendar.getInstance();
		calendarF.setTime(fechaF);
		
		int dias = calendarI.compareTo(calendarF) + 1;
		System.out.println(dias);
		return (int) (precioReserva / dias);
	}

	public Date getFechaI() {
		return fechaI;
	}

	public Date getFechaF() {
		return fechaF;
	}

	public double getPrecioReserva() {
		return precioReserva;
	}

	public void setFechaI(Date fechaI) {
		this.fechaI = fechaI;
	}

	public void setFechaF(Date fechaF) {
		this.fechaF = fechaF;
	}
	
	

}
