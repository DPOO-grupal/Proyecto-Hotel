package Pagos;

import java.util.ArrayList;
import java.util.Objects;

public class Tarjeta {

	private String nombreDueño;
	private String numeroCelular;
	private String documento;
	private int numeroTarjeta;
	private String fechaVencimiento;
	private int numeroDeSeguridad;
	private int monto;
	private String pasarela;
	
	public Tarjeta(String nombreDueño, String numeroCelular, String documento, int numeroTarjeta, String fechaVencimiento, int numeroDeSeguridad, int monto) {
		this.nombreDueño = nombreDueño;
		this.numeroCelular = numeroCelular;
		this.documento = documento;
		this.numeroTarjeta = numeroTarjeta;
		this.fechaVencimiento = fechaVencimiento;
		this.numeroDeSeguridad = numeroDeSeguridad;
		this.monto = monto;
		//this.pasarela = pasarela;
	}
	public int getMonto() {
		return monto;
	}
	
	public void setMonto(int monto) {
		this.monto = monto;
	}
	
	public String getNombreDueño() {
		return nombreDueño;
	}
	
	public String getNumeroCelular() {
		return numeroCelular;
	}
	
	public String getDocumento() {
		return documento;
	}
	
	public int getNumeroTarjeta() {
		return numeroTarjeta;
	}
	
	public String getFechaVencimiento() {
		return fechaVencimiento;
	}
	
	public int getNumeroDeSeguridad() {
		return numeroDeSeguridad;
	}
	
	public String getPasarela() {
		return pasarela;
	}
	@Override
	public int hashCode() {
		return Objects.hash(documento, fechaVencimiento, numeroDeSeguridad, numeroTarjeta);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tarjeta other = (Tarjeta) obj;
		return Objects.equals(documento, other.documento) && Objects.equals(fechaVencimiento, other.fechaVencimiento)
				&& numeroDeSeguridad == other.numeroDeSeguridad && numeroTarjeta == other.numeroTarjeta;
	}

}
