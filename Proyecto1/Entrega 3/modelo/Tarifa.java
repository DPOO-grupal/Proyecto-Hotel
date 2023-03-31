package modelo;

import java.io.Serializable;

public class Tarifa implements Serializable{
	double estandar;
	double suite;
	double suiteDouble;
	
	public Tarifa(TipoHabitacion tipo, double valor) {
		//TODO revisar creacion
		setPrecio(tipo, valor);
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
	
	
	
	
	
}
