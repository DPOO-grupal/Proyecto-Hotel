package modelo;

import java.io.Serializable;
import java.util.*;

public class Habitacion implements Serializable{

//Atributos 
	private int id;
	private int capacidad;
	private ArrayList<Servicio> listaServicios;
	private ArrayList<Cama> listaCamas;
	private String caracteristicas;
	private TipoHabitacion tipo;
	
//Constructor
	public Habitacion(TipoHabitacion tipo, ArrayList<Cama> listaCamas, int id, String caracteristicas) {
		this.tipo=tipo;
		this.listaCamas=listaCamas;
		this.capacidad=getCapacidad(listaCamas);
		this.id=id;
		this.caracteristicas=caracteristicas;
	}

//Metodos
	public TipoHabitacion getTipoHabitacion(TipoHabitacion tipo) {
		return this.tipo=tipo;
	}
	
	public int getId () {
		return this.id;
	}
	
	public int getCapacidad (ArrayList<Cama> listaCamas) {
		for (int i = 0; i < listaCamas.size(); i++) 
		{
			Cama cama= listaCamas.get(i);
			capacidad += cama.getCapacidadCama();
		}
		return capacidad;
	}	
	
	public void añadirServicioHabitacion (Servicio servicio) {
		listaServicios.add(servicio);
	}
	
	public void añadirCamas (Cama cama) {
		listaCamas.add(cama);
	}
	
	public TipoHabitacion getTipo() {
		return TipoHabitacion.ESTANDAR;
	}
	
	public int getApto(ArrayList<Cama> listaCamas) {
		int apto = 0;
		for (int i = 0; i < listaCamas.size(); i++) 
		{
			boolean cama = listaCamas.get(i).getAptoParaNiño();
			if (cama==true) 
			{
				apto++;
			}
			
		}
		return apto;
	}
	
	public String getCaracteristicas() {
		return this.caracteristicas;
	}
}
