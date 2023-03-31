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
	public int getId() 
	{
		return this.id;
	}
	public int getCapacidad () 
	{
		return this.capacidad;
	}	
	
	public void añadirServicioHabitacion (Servicio servicio) 
	{
		listaServicios.add(servicio);
	}
	
	public TipoHabitacion getTipo() {
		return TipoHabitacion.ESTANDAR;
	}
	
	public String getCaracteristicas() 
	{
		return null;
	}
}
