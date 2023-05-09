package modelo;

import java.io.Serializable;
import java.util.Date;

import controlador.Hotel;

public class Usuario implements Serializable{
	protected String login;
	protected String password;
	protected static Hotel hotel;
	
	public Usuario(String login, String password) {
		this.login = login;
		this.password = password;
	}
	
	public boolean iniciarSesion(String password) {
		
		boolean autent = false;
		
		if (this.password.equals(password)) {
			autent = true;
		}
		
		return autent;
	}
	
	public void cerrarSesion() {
		hotel.setUsuarioActual(null);
		
	}
	
	public static void setHotel() {
		Usuario.hotel = new Hotel();
	}
	public static void cargarInformacion() {
		hotel.cargarInformacion();

	}
	public static void setHoy(Date fecha) {
		hotel.setHoy(fecha);
		
	}

	public static void guardarInformacion() {
		hotel.guardarInformacion();
		
	}

	public static void autenticar(String login, String password) throws Exception {
		hotel.autenticar(login, password);
		
	}

	public static Usuario getUsuarioActual() {
		return hotel.getUsuarioActual();
	}
	
	
	
	
	
}
