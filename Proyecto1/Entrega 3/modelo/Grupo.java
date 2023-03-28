package modelo;

import java.util.ArrayList;

public class Grupo {
	private int id;
	private static int numGrupo;
	private ArrayList<Huesped> huespedes;
	private ArrayList<Integer> listaHabitaciones;
	private double saldoPagado = 0;
	private ArrayList<Servicio> listaServicios = new ArrayList<Servicio>();
	private int cReal;
	private int cRelativa;
	
	public Grupo(ArrayList<Huesped> huespedes, ArrayList<Integer> habitaciones) {
		this.huespedes = huespedes;
		this.listaHabitaciones = habitaciones;
		this.cReal = huespedes.size();
		this.cRelativa = calcularCRelativa(huespedes);
		this.id = numGrupo;
		numGrupo ++;
	}
	
	private int calcularCRelativa(ArrayList<Huesped> huespedes) {
		int total = 0;
		/* evaluar la edad de cada huesped y sumar */
		return total;
	}
}
