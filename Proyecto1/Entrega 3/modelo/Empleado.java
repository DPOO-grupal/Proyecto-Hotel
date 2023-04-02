package modelo;

import java.util.ArrayList;
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
	
	public void crearReserva(Date fechaI, Date fechaF, int tamanioGrupo, String[] nombres, String[] documentos, String[] emails, String[] telefonos, int[] edades) {
		hotel.crearReserva(fechaI, fechaF, tamanioGrupo, nombres, documentos, emails, telefonos, edades);
	}
	

	public boolean completarReserva(int idHabitacion) {
		return hotel.completarReserva(idHabitacion);
	}
	
	public ArrayList<Habitacion> DiponiblesParaGrupoEnCurso(TipoHabitacion tipo) {
		return hotel.DiponiblesParaGrupoEnCurso(tipo);
		
	}
	
	public double getPrecioHabitacionReserva(Habitacion habitacion) {
		return hotel.getPrecioHabitacionReserva(habitacion);
	}
	
// FIN RESERVAS ---------------------------------------------

}
