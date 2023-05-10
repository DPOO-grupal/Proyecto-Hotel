package modelo;

import java.io.Serializable;
import java.lang.reflect.Constructor;

public enum TipoHabitacion implements Serializable {
	ESTANDAR, SUITE, SUITEDOUBLE;
	
	TipoHabitacion () {
		
	}
	
	public String darTipoHab() {
		return toString().toLowerCase(); 
	}
}
