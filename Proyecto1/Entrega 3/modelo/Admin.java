package modelo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;

public class Admin extends Empleado {
	
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
	
// FIN TARIFAS ----------------------------------------------


	public void crearServicioHotel(String nombre, double precio) {
		hotel.crearServicioHotel(nombre, precio);
	}
	
//FIN SERVICIOS HOTEL -----------------------------------------------------------

//INICIO HABITACIONES -----------------------------------------------------------

	public void crearCama(int id, int capacidadCama, boolean aptoParaNiño) {
		hotel.crearCama(id, capacidadCama, aptoParaNiño);
	}
	
	public void crearHabitacion(TipoHabitacion tipo,int id) {
		hotel.crearHabitacion(tipo, id);
	}
	
	public void setCaracteristicasHabitacion(String habitacion, int id) {
		hotel.setCaracteristicas(habitacion, id);
	}
	
	public void añadirServicioHabitacion(int id, String nombre, double precio) {
		hotel.añadirServicioHabitacion(id, nombre, precio);
	}
	
//FIN HABITACIONES -----------------------------------------------------------

//INICIO PRODUCTO MENU -----------------------------------------------------------

	public void crearProductoMenu(Date horaI, Date horaF, boolean llevable,String nombre, double precio) {
		hotel.crearProductoMenu(horaI, horaF, llevable, nombre, precio);
	}
//FIN PRODUCTO MENU -----------------------------------------------------------
}
