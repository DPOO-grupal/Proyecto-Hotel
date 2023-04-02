package modelo;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Admin extends Usuario {
	
	public Admin(String login, String password) {
		super(login, password);
	}
	
	public void añadirUsuario(String login, String password, int tipo) {
		hotel.añadirUsuario(login, password, tipo);
	}

// INICIO TARIFAS ---------------------------------------------
	
	public boolean tarifasCompletas() {
		return checkTarifas().size() == 0;
	}

	
	public ArrayList<Tarifa> checkTarifas() {
		return hotel.checkTarifas();
	}
	
	public boolean crearTarifa(Date fechaI, Date fechaF, TipoHabitacion tipo, double valor) {
		return hotel.crearTarifa(fechaI, fechaF, tipo, valor);
	}
	
	public boolean cancelarReserva() {
		return false;
		
	}
	
	public ArrayList<Tarifa> consultarTarifas(Date fechaI, Date fechaF) {
		return hotel.consultarTarifas(fechaI, fechaF);
	}
// FIN TARIFAS ----------------------------------------------

// INICIO RESERVAS ------------------------------------------

	public HashMap<Integer,Grupo> mostrarReservas(Date fechaI, Date fechaF) {
		return hotel.mostrarReservas(fechaI, fechaF); 
		
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


	public void crearServicioHotel(String nombre, double precio) {
		hotel.crearServicioHotel(nombre, precio);
	}
	
	public void añadirServicioHabitacion(int id, String nombre, double precio) {
		hotel.añadirServicioHabitacion(id, nombre, precio);
	}
	
	public void crearCama(int id, int capacidadCama, boolean aptoParaNiño) {
		hotel.crearCama(id, capacidadCama, aptoParaNiño);
	}
	
	public void crearHabitacion(TipoHabitacion tipo,int id) {
		hotel.crearHabitacion(tipo, id);
	}
	
	public void setCaracteristicasHabitacion(String habitacion, int id) {
		hotel.setCaracteristicas(habitacion, id);
	}



}
