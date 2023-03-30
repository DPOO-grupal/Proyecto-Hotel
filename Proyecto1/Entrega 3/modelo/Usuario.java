package modelo;

import controlador.Hotel;

public class Usuario {
	private String login;
	private String password;
	private static Hotel hotel;
	
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
	public static void setHotel(Hotel hotel) {
		Usuario.hotel = hotel;
	}
	
	
	
}
