package modelo;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;

public class Admin extends Empleado {
	
	public Admin(String login, String password, String area) {
		super(login, password, area);
	}
	
	public void añadirUsuario(String login, String password, String area, int tipo) {
		hotel.añadirUsuario(login, password, area, tipo);
	}

// INICIO TARIFAS ---------------------------------------------
	
	public boolean tarifasCompletas() {
		return TarifasFaltantes().size() == 0;
	}

	public ArrayList<Tarifa> TarifasFaltantes() {
		return hotel.TarifasFaltantes();
	}
	
	public ArrayList<Tarifa> crearTarifa(Date fechaI, Date fechaF, TipoHabitacion tipo, double valor, boolean[] diasValores) {
		return hotel.crearTarifasRango(fechaI, fechaF, tipo, valor, diasValores);
	}

	public void borrarTarifa(Date fecha) {
		hotel.borrarTarifa(fecha);
	}
	
// FIN TARIFAS ----------------------------------------------

//INICIO SERVICIOS HOTEL -----------------------------------------------------------

	public void crearServicioHotel(String nombre, double precio) {
		hotel.crearServicioHotel(nombre, precio);
	}
	
	public void eliminarServicioHotel(int id) {
		hotel.eliminarServicioHotel(id);
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
	
	public Servicio crearServicio(String nombre, double precio) {
		return hotel.crearServicio(nombre, precio);
	}
	
	public Cama crearCama(int capacidad, boolean exclusiva) {
		return hotel.crearCama(capacidad, exclusiva);
	}
	
	public void añadirServicioHabitacion(int id, String nombre, double precio) {
		hotel.añadirServicioHabitacion(id, nombre, precio);
	}
	
//FIN HABITACIONES -----------------------------------------------------------

//INICIO PRODUCTO MENU -----------------------------------------------------------

	public void crearProductoMenu(LocalTime horaI, LocalTime horaF, boolean llevable,String nombre, double precio) {
		hotel.crearProductoMenu(horaI, horaF, llevable, nombre, precio);
	}
	
	public void eliminarProductoMenu(ProductoMenu productoMenu) {
		hotel.eliminarProductoMenu(productoMenu);
	}
	
//FIN PRODUCTO MENU -----------------------------------------------------------
	
//INICIO DAR DATOS ------------------------------------------------------------
	
	public String[] darUsuarios() {
		String[] lista = hotel.darUsuarios();
		return lista;
	}
	
	public String getTipo(String login) {
		return hotel.getTipo(login);
	}
	
	public String getArea(String login) {
		return hotel.getArea(login);
	}
	
	public Habitacion getHabitacion(int id) {
		return hotel.getHabitacion(id);
	}
	
	public void quitarUsuario(String nombre) {
		hotel.quitarUsuario(nombre);
	}
	
	public void quitarHabitacion(Integer ID) {
		hotel.quitarHabitacion(ID);
	}
	
	public boolean reservada(Integer ID) {
		return hotel.reservada(ID);
	}
//FIN DAR DATOS ---------------------------------------------------------------

	public void borrarDatos() {
		hotel.borrarDatos();
		
	}

}
