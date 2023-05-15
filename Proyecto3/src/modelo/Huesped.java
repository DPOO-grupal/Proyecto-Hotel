package modelo;

import java.io.Serializable;
import java.util.Objects;

public class Huesped implements Serializable{
	@Override
	public int hashCode() {
		return Objects.hash(documento, nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Huesped other = (Huesped) obj;
		return Objects.equals(documento, other.documento) && Objects.equals(nombre, other.nombre);
	}


	private String documento;
	private String nombre;
	private String email;
	private String telefono;
	private int edad;
	
	public Huesped(String documento, String nombre, String email, String telefono, int edad) {
		this.documento = documento;
		this.nombre = nombre;
		this.email = email;
		this.telefono = telefono;
		this.edad = edad;
	}

	public String getDocumento() {
		return documento;
	}

	public String getNombre() {
		return nombre;
	}

	public String getEmail() {
		return email;
	}

	public String getTelefono() {
		return telefono;
	}


	public int getEdad() {
		return edad;
	}

}
