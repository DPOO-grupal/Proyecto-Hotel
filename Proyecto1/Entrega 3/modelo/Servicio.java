package modelo;

public class Servicio {
	
	private String nombre;
	private double precio;
	private int id = 0;
	private static int numServicios = 1;
	
	public Servicio(String nombre, double precio) {
		this.nombre = nombre;
		this.precio = precio;
		this.id = numServicios;
		numServicios += 1;
	}

}
