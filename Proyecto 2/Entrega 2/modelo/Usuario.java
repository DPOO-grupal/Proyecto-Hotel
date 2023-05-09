package modelo;

import java.io.Serializable;
import java.util.Objects;

import controlador.Hotel;

public class Usuario implements Serializable{
	protected String login;
	protected String password;
	protected static Hotel hotel;
	protected String area;
	
	public Usuario(String login, String password, String area) {
		this.login = login;
		this.password = password;
		this.area = area;
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
	
	public String getArea() {
		return this.area;
	}

	@Override
	public int hashCode() {
		return Objects.hash(login, password);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(login, other.login) && Objects.equals(password, other.password);
	}
	
}
