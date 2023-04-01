package modelo;

import java.util.ArrayList;

public class Admin extends Usuario {
	
	public Admin(String login, String password) {
		super(login, password);
	}
	
	public void añadirUsuario(String login, String password, int tipo) {
		hotel.añadirUsuario(login, password, tipo);
	}
	
	public void crearServicio(String nombre, double precio) {
		hotel.crearServicio(nombre, precio);
	}
	
	public void añadirServicioHabitacion(int id, Servicio servicio) {
		hotel.añadirServicioHabitacion(id, servicio);
	}
	
	public Cama crearCama(String tipo, int capacidadCama, boolean aptoParaNiño) {
		return hotel.crearCama(tipo, capacidadCama, aptoParaNiño);
	}
	
	public void crearHabitacion(TipoHabitacion tipo, int id,ArrayList<Cama> listaCamas, ArrayList<Servicio> listaServicios, String caracteristicas) {
		hotel.crearHabitacion(tipo, id, listaCamas, listaServicios, caracteristicas);
	}
}
