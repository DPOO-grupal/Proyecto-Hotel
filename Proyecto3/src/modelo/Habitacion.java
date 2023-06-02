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
	private static ArrayList<Integer> pisoIds = new ArrayList<Integer>();; 
	
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
	
	public TipoHabitacion setTipoHabitacion(TipoHabitacion tipoHabitacion) {
		return this.tipo = tipoHabitacion;
	}
	
	public int getId () {
		return this.id;
	}
	
	public int getCapacidad () {
		int capa = 0;
		for (int i = 0; i < listaCamas.size(); i++) {
			Cama cama= listaCamas.get(i);
			capa += cama.getCapacidadCama();
		}
		return capa;
	}	
	
	public void añadirServicioHabitacion(Servicio servicio) {
		listaServicios.add(servicio);
	}
	
	public ArrayList<Servicio> getServicios() {
		return this.listaServicios;
	}
	
	public ArrayList<Cama> getCamas() {
		return this.listaCamas;
	}
	
	public void añadirCamas(Cama cama) {
		listaCamas.add(cama);
		this.capacidad=getCapacidad();
	}
	

	public void setListaServicios(ArrayList<Servicio> listaServicios) {
		this.listaServicios = listaServicios;
	}
	
	public void setListaCamas(ArrayList<Cama> listaCamas) {
		this.listaCamas = listaCamas;
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
	
	public static int getMaxHabitacion(int piso) {
		int idHabitacion = 0;
		int size = pisoIds.size();
		if (size<=(piso-1)) {
			for (int i = size; i < piso; i++) {
				pisoIds.add((i+1)*100);
			}
		}
		idHabitacion = pisoIds.get(piso - 1);
		idHabitacion++;
		pisoIds.set(piso - 1, idHabitacion);
		return idHabitacion;
	}
	
	public static ArrayList<Integer> getPisoIds() {
		return pisoIds;
	}

	public static void setPisoIds(ArrayList<Integer> pisoIds) {
		Habitacion.pisoIds = pisoIds;
	}

	public double getPrecioServicios() {
		double precio = 0;
		for (Servicio servicio : listaServicios) {
			precio += servicio.getPrecio();
		}
		return precio;
	}
}
