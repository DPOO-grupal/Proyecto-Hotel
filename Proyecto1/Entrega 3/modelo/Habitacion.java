package modelo;

import java.util.*;

public class Habitacion {

//Atributos 
	private int id;
	private int capacidad;
	private ArrayList<Servicio> listaServicios;

//Constructor
	public Habitacion(String tipo, int capacidad, int id) 
	{
		this.capacidad=capacidad;
		this.id=id;
	}
	
//Metodos
	public double getPrecio(Date fechaI, Date fechaF) 
	{
		return 0;
	}
	
	public int getCapacidad () 
	{
		return 0;
	}	
	
	public void añadirServicioHabitacion (Servicio servicio) 
	{
		
	}
	
	public TipoHabitacion getTipo() {
		return TipoHabitacion.ESTANDAR;
	}
	
	public String getCaracteristicas() 
	{
		return null;
	}
}
