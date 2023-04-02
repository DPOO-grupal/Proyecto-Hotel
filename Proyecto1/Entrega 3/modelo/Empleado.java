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
