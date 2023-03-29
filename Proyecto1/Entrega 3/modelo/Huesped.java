package modelo;

public class Huesped {
	private String documento;
	private String nombre;
	private String email;
	private String telefono;
	private int id;
	private int edad;
	
	public Huesped(String documento, String nombre, String email, String telefono, int id, int edad) {
		this.documento = documento;
		this.nombre = nombre;
		this.email = email;
		this.telefono = telefono;
		this.id = id;
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

	public int getId() {
		return id;
	}

	public int getEdad() {
		return edad;
	}

}
