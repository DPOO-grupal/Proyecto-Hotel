package modelo;

import java.io.Serializable;
import java.util.Objects;

public class Servicio implements Serializable{
	
@Override
	public int hashCode() {
		return Objects.hash(id, nombre, precio);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Servicio other = (Servicio) obj;
		return id == other.id && Objects.equals(nombre, other.nombre)
				&& Double.doubleToLongBits(precio) == Double.doubleToLongBits(other.precio);
	}

	//Atributos
	private String nombre;
	@Override
	public String toString() {
		return nombre;
	}

	private double precio;
	private int id;
	private static int numServicios;
	
//Constructor
	public Servicio(String nombre, double precio) {
		this.nombre = nombre;
		this.precio = precio;
		this.id = numServicios;
		numServicios ++;
	}

//Metodos
	public static void setNumServicios(int numServicios) {
		Servicio.numServicios = numServicios;
	}
	
	public static int getNumServicios() {
		return numServicios;
	}

	public String getNombre() {
		return this.nombre;
	}
	
	public double getPrecio() {
		return this.precio;
	}
	
	public int getId() {
		return this.id;
	}
}
