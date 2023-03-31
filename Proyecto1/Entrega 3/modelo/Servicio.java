package modelo;

public class Servicio {
	
	private String nombre;
	private double precio;
	//protected int id;
	//private static int numServicios;
	
	public Servicio(String nombre, double precio) {
		this.nombre = nombre;
		this.precio = precio;
		//this.id = numServicios;
		//numServicios ++;
	}

	public static void setNumServicios(int numServicios) {
		//Servicio.numServicios = numServicios;
	}

}
