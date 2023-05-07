package modelo;

import java.io.Serializable;
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
	
	public double getPrecio(TipoHabitacion tipo) {
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
	
	
	public boolean updatePrecio(TipoHabitacion tipo, double valor) {
		boolean right = false;
		if (valor < 0) {
			setPrecio(tipo, -valor);
			right =  true;
		} else if(getPrecio(tipo)<0) {
			setPrecio(tipo, valor);
			right =  true;
			
		} else if(getPrecio(tipo) > valor) {
			setPrecio(tipo, valor);
			return right;
		}
		return right;
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

	public Date getFecha() {
		return this.fecha;
	}
	
	
	
	
	
}
