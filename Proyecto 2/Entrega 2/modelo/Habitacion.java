package modelo;

import java.io.Serializable;
import java.util.*;

public class Habitacion implements Serializable{

@Override
	public String toString() {
		return "Capacidad:" + capacidad + ", Caracteristicas:" + caracteristicas;
	}

	//Atributos 
	private int id;
	private int capacidad;
	private ArrayList<Servicio> listaServicios;
	private ArrayList<Cama> listaCamas;
	private String caracteristicas;
	private TipoHabitacion tipo;
	
//Constructor
	public Habitacion(TipoHabitacion tipo, int id) {
		listaServicios = new ArrayList<Servicio>();
		listaCamas = new ArrayList<Cama>();
		this.tipo=tipo;
		this.id=id;
	}

//Metodos
	public TipoHabitacion getTipoHabitacion() {
		return this.tipo;
	}
	
	public int getId () {
		return this.id;
	}
	
	public int getCapacidad () {
		for (int i = 0; i < listaCamas.size(); i++) {
			Cama cama= listaCamas.get(i);
			capacidad += cama.getCapacidadCama();
		}
		return capacidad;
	}	
	
	public void añadirServicioHabitacion(Servicio servicio) {
		listaServicios.add(servicio);
	}
	
	public ArrayList<Servicio> getServicios() {
		return this.listaServicios;
	}
	
	public void añadirCamas(Cama cama) {
		listaCamas.add(cama);
		this.capacidad=getCapacidad();
	}
	

	public void setListaServicios(ArrayList<Servicio> listaServicios) {
		this.listaServicios = listaServicios;
	}
	
	public int getApto(ArrayList<Cama> listaCamas) {
		int apto = 0;
		for (int i = 0; i < listaCamas.size(); i++) {
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

	public void setCaracteristicas(String caracteristicas) {
		this.caracteristicas = caracteristicas;
	}
	
	public double getPrecioServicios() {
		double precio = 0;
		for (Servicio servicio : listaServicios) {
			precio += servicio.getPrecio();
		}
		return precio;
	}
}
