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
	
	
}
