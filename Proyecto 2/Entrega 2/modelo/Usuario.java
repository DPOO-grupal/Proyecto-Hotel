package modelo;

import java.io.Serializable;

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
	
	public static void setHotel(Hotel hotel) {
		Usuario.hotel = hotel;
	}
	
	
	
	
	
}
