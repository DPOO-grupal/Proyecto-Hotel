package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Grupo implements Serializable{
	private int id;
	private static int numGrupo;
	private ArrayList<Huesped> huespedes;
	private ArrayList<Integer> listaHabitaciones;
	private double saldoPagado = 0;
	private HashMap<Servicio, Integer> listaServicios = new HashMap<Servicio, Integer>();
	private int vReal;
	private int vRelativo;
	private int capacidadCamas;

	private Reserva reserva;
	
	public Grupo(ArrayList<Huesped> huespedes, Reserva reserva) {
		this.huespedes = huespedes;
		this.listaHabitaciones = new ArrayList<Integer>();
		this.vReal = huespedes.size();
		this.vRelativo = calcularVRelativo(huespedes);
		this.id = numGrupo++;
		numGrupo ++;
		this.reserva = reserva;
	}
	
	private int calcularVRelativo(ArrayList<Huesped> huespedes) {
		int total = 0;
		for(Huesped huesped : huespedes) {
			if(huesped.getEdad() >= 2)
				total ++;
		}
		return total;
	}

	public static int getNumGrupo() {
		return numGrupo;
	}

	public static void setNumGrupo(int numGrupo) {
		Grupo.numGrupo = numGrupo;
	}
	
	public void añadirHabitacion(int id, int capacidad, double precioHabitacion) {
		listaHabitaciones.add(id);
		precioHabitacion += reserva.getPrecioReserva();
		reserva.setPrecioReserva(precioHabitacion);
		capacidadCamas += capacidad;
	}

	public int getId() {
		return id;
	}

	public ArrayList<Huesped> getHuespedes() {
		return huespedes;
	}

	public ArrayList<Integer> getListaHabitaciones() {
		return listaHabitaciones;
	}

	public double getSaldoPagado() {
		return saldoPagado;
	}

	public HashMap<Servicio, Integer> getListaServicios() {
		return listaServicios;
	}

	public int getvReal() {
		return vReal;
	}

	public int getvRelativo() {
		return vRelativo;
	}

	public Reserva getReserva() {
		return reserva;
	}

	public void setSaldoPagado(double saldoPagado) {
		this.saldoPagado = saldoPagado;
	}

	public void setListaServicios(HashMap<Servicio, Integer> listaServicios) {
		this.listaServicios = listaServicios;
	}
	
	public int getCapacidadCamas() {
		return capacidadCamas;
	}
	
	public Huesped getLider() {
		return huespedes.get(0);
	}
	
	public void añadirServicio(Servicio servicio, int cantidad, boolean pagarEnSitio) {
		listaServicios.put(servicio, cantidad);
		if (pagarEnSitio) {
			saldoPagado+=servicio.getPrecio()*cantidad;
		}
	}
	
}
