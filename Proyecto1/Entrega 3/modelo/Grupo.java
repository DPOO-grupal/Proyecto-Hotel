package modelo;

import java.util.ArrayList;

public class Grupo {
	private int id;
	private static int numGrupo;
	private ArrayList<Huesped> huespedes;
	private ArrayList<Integer> listaHabitaciones;
	private double saldoPagado = 0;
	private ArrayList<Servicio> listaServicios = new ArrayList<Servicio>();
	private int vReal;
	private int vRelativo;
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

	public ArrayList<Servicio> getListaServicios() {
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

	public void setListaServicios(ArrayList<Servicio> listaServicios) {
		this.listaServicios = listaServicios;
	}
}
