package modelo;

import java.io.Serializable;
import java.util.Date;
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
	
	public void iniciarSesion(String password) throws Exception {
		
		
		if (!this.password.equals(password)) {
			throw new Exception("Contraseña Incorrecta");
		}
			
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
	
	public void borrarDatos() {
		hotel.borrarDatos();
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
	
	public String formatoFecha(Date date) {
		return hotel.formatoFecha(date);
		 
	}
	
}
