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
	private boolean apto;
	private ArrayList<Servicio> listaServicios;
	private ArrayList<Cama> listaCamas;
	private String caracteristicas;
	private TipoHabitacion tipo;
	private static ArrayList<Integer> pisoIds = new ArrayList<Integer>();; 
	
//Constructor
	public Habitacion(TipoHabitacion tipo, int id, int capacidad, Boolean apto) {
		listaServicios = new ArrayList<Servicio>();
		this.capacidad = capacidad;
		this.tipo=tipo;
		this.id=id;
		this.apto=apto;
	}

//Metodos
	public TipoHabitacion getTipoHabitacion() {
		return this.tipo;
	}
	
	public int getId () {
		return this.id;
	}
	
	public int getCapacidad () {
		return this.capacidad;
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
	
	public boolean getApto() {
		return this.apto;
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
