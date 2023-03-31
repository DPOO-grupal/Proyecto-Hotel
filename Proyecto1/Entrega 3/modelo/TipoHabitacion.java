package modelo;

import java.io.Serializable;

public enum TipoHabitacion implements Serializable {
	ESTANDAR, SUITE, SUITEDOUBLE;
	
	public String darTipoHab() {
		return toString().toLowerCase(); 
	}
}
