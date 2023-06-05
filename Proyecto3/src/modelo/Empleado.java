package modelo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Set;

public class Empleado extends Huesped  {
	
	public Empleado (String login, String password, String area) {
		super(login, password, area);
	}
	
// INICIO TARIFAS ---------------------------------------------

	public Collection<Tarifa> consultarTarifas(Date fechaI, Date fechaF) {
		return hotel.consultarTarifas(fechaI, fechaF);
	}
	
// FIN TARIFAS ---------------------------------------------

//INICIO SERVICIOS HOTEL -----------------------------------------------------
	
	public HashMap<Integer, Servicio> getServiciosHotel(){
		return hotel.getServiciosHotel();
	}

	public Servicio añadirServicioHotelHabitacion(int idHabitacion, int idServicio, int cantidad, boolean pagarEnSitio) {
		return hotel.añadirServicioHotelHabitacion(idHabitacion, idServicio, cantidad, pagarEnSitio);
	}
	
	public Servicio añadirProductoMenuHabitacion(int idHabitacion, int idServicio, int cantidad, boolean pagarEnSitio) {
		return hotel.añadirProductoMenuHabitacion(idHabitacion, idServicio, cantidad, pagarEnSitio);
	}
	
	public ArrayList<String[]> getServiciosHabitacion(String ID){
		return hotel.getServiciosHabitacion(ID);
	}
	
//FIN SERVICIOS HOTEL -----------------------------------------------------------

//INICIO HABITACIONES -----------------------------------------------------------
	
	public HashMap<Integer, Habitacion> getHabitaciones() {
		return hotel.getHabitaciones();
	}
	
	public HashMap<Integer, Grupo> getGrupos() {
		return hotel.getGrupos();
	}
	
	public ArrayList<String[]> getServicios(String ID) {
		return hotel.getServiciosHabitacion(ID);
	}
	
	public ArrayList<String[]> getCamas(String ID) {
		return hotel.getCamasHabitacion(ID);
	}
	
//FIN HABITACIONES -----------------------------------------------------------
	
//INICIO PRODUCTO MENU -----------------------------------------------------------

	public HashMap<Integer, ProductoMenu> getMenu() {
		return hotel.getMenu();
	}
	
//FIN PRODUCTO MENU -----------------------------------------------------------

	


	public Grupo checkOut(int idGrupo) {
		return hotel.checkOut(idGrupo);
	}
	
	public boolean checkUsuario(String nombre) {
		return hotel.checkUsuario(nombre);
	}
	public void llenarOcupados(int ID) {
		hotel.llenarOcupados(ID);
	}
	
	public Integer[] ocupacionHoy() {
		return hotel.ocupacionHoy();
	}
	
	
	public int contarOcupadasDia(Date dia) {
		//hotel.printOcupados();
		return hotel.contarOcupadasDia(dia);
	}
	
	public int getTotalHabitaciones() {
		return hotel.getTotalHabitaciones();
	}
	




}
