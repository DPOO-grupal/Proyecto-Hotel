package modelo;

import java.io.Serializable;

public class Servicio implements Serializable{
	
//Atributos
	private String nombre;
	private double precio;
	private int id;
	private static int numServicios;
	
//Constructor
	public Servicio(String nombre, double precio) 
	{
		this.nombre = nombre;
		this.precio = precio;
		this.id = numServicios;
		numServicios ++;
	}

//Metodos
	public static void setNumServicios(int numServicios) 
	{
		Servicio.numServicios = numServicios;
	}
	
	public String getNombre() 
	{
		return this.nombre;
	}
	
	public double getPrecio() 
	{
		return this.precio;
	}
	
	public int getId() 
	{
		return this.id;
	}
}
