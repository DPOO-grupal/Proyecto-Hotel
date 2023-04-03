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
	

	public boolean completarReserva(int idHabitacion, double precioHabitacion) {
		return hotel.completarReserva(idHabitacion, precioHabitacion);
	}
	
	public ArrayList<Habitacion> DiponiblesParaGrupoEnCurso(TipoHabitacion tipo) {
		return hotel.DiponiblesParaGrupoEnCurso(tipo);
		
	}
	
	public double getPrecioHabitacionReserva(Habitacion habitacion) {
		return hotel.getPrecioHabitacionReserva(habitacion);
	}
	
// FIN RESERVAS ---------------------------------------------

//INICIO SERVICIOS HOTEL -----------------------------------------------------
	
	public HashMap<Integer, Servicio> getServiciosHotel(){
		return hotel.getServiciosHotel();
	}

	public void añadirServicioHotelHabitacion(int idHabitacion, int idServicio) {
		hotel.añadirServicioHotelHabitacion(idHabitacion, idServicio);
	}
	
//FIN SERVICIOS HOTEL -----------------------------------------------------------

//INICIO HABITACIONES -----------------------------------------------------------
	
	public HashMap<Integer, Habitacion> getHabitaciones() {
		return hotel.getHabitaciones();
	}
	
//FIN HABITACIONES -----------------------------------------------------------
	
//INICIO PRODUCTO MENU -----------------------------------------------------------

	public ArrayList<ProductoMenu> getMenu() {
		return hotel.getMenu();
	}
	
//FIN PRODUCTO MENU -----------------------------------------------------------
}
