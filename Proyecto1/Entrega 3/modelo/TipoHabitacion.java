package modelo;

public enum TipoHabitacion {
	ESTANDAR, SUITE, SUITEDOUBLE;
	
	public String darTipoHab() {
		return toString().toLowerCase(); 
	}
}
