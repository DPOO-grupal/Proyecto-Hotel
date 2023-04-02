package modelo;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;

public class Empleado extends Usuario  {
	
	public Empleado (String login, String password) {
		super(login, password);
	}
	
	
// INICIO TARIFAS ---------------------------------------------

	public Collection<Tarifa> consultarTarifas(Date fechaI, Date fechaF) {
		return hotel.consultarTarifas(fechaI, fechaF);
	}
// FIN TARIFAS ---------------------------------------------

// INICIO RESERVAS ------------------------------------------

	public HashMap<Integer,Grupo> mostrarReservas(Date fechaI, Date fechaF) {
		return hotel.mostrarReservas(fechaI, fechaF); 
		
	}
	
	public boolean cancelarReserva(int id) {
		return hotel.cancelarReserva(id);
		
	}
	
// FIN RESERVAS ---------------------------------------------

	
}
