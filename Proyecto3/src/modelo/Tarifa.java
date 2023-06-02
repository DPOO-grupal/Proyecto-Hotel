package modelo;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public class Tarifa implements Serializable{
	private double estandar;
	private double suite;
	private double suiteDouble;
	private Date fecha;
	
	public Tarifa(Date fecha) {
		this.fecha = fecha;
		estandar = -1;
		suite = -1;
		suiteDouble = -1;
	}
	
	public double getPrecio(TipoHabitacion tipo) throws Exception {
		double precio = -1;
		
		switch (tipo) {
		case ESTANDAR:
			precio = estandar;
			break;
		case SUITE:
			precio = suite;
			break;
		case SUITEDOUBLE:
			precio = suiteDouble;
			break;
		default:
			break;
		}
		
		if (precio < 0) {
			throw new Exception("No hay tarifa");
		}
		
		return precio;
		
	}
	
	private void setPrecio(TipoHabitacion tipo, double valor) {
		
		switch (tipo) {
		case ESTANDAR: 
			estandar = valor;
			break;
		case SUITE:
			suite = valor;
			break;
		case SUITEDOUBLE:
			suiteDouble = valor;
			break;
		default:
			break;
		}		
	}
	
	
	public void updatePrecio(TipoHabitacion tipo, double valorNuevo, boolean sobreEscribir) throws Exception {
		System.out.println("Tarifa.updatePrecio()");
		System.out.println("tipo: " + tipo + "valorNuevo: " + valorNuevo + "sobreEscribir: " +sobreEscribir);

		if (sobreEscribir) {
			setPrecio(tipo, valorNuevo);
			System.out.println("if 0");
			return;
		}
		
		double valorAnterior = -1;
		try {
			valorAnterior = getPrecio(tipo);
			System.out.println("if 1");

		} catch (Exception e) {
			setPrecio(tipo, valorNuevo);
			System.out.println("if 2");

			return;

		}
		
		if(valorNuevo <= valorAnterior) {
			System.out.println("if 3");
			setPrecio(tipo, valorNuevo);
		} else {
			System.out.println("if 4");

			throw new Exception("No editable");
		}
		
		 
	
	}
	
	
	public void updatePrecio(TipoHabitacion tipo, double valorNuevo) throws Exception {
		updatePrecio(tipo, valorNuevo, false);
	
	}
	
	public boolean completo() {
		boolean right = true;
		
		if (right && estandar < 0) {
			right = false;
		} else if (right && suite < 0){
			right = false;
		} else if (right && suiteDouble < 0){
			right = false;
		}
		
		return right;
		
	}
	
	public ArrayList<TipoHabitacion> getFaltantes() {
		ArrayList<TipoHabitacion> faltantes = new ArrayList<TipoHabitacion>();
		
		if (estandar < 0) {
			faltantes.add(TipoHabitacion.ESTANDAR);
		} 
		if (suite < 0){
			faltantes.add(TipoHabitacion.SUITE);
		} 
		if (suiteDouble < 0){
			faltantes.add(TipoHabitacion.SUITEDOUBLE);
		}
		
		return faltantes;
		
	}

	@Override
	public int hashCode() {
		return Objects.hash(fecha);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tarifa other = (Tarifa) obj;
		return Objects.equals(fecha, other.fecha);
	}

	public Date getFechaDate() {
		return this.fecha;
	}
	public String getFechaString() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	    String fechaString = sdf.format(fecha);
	
		return fechaString;
	}
	
	
	
	
	
}
