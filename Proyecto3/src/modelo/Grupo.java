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
	private boolean check;
	private Reserva reserva;
	private double precioTotalFactura = 0;
	
	public Grupo(Reserva reserva) {
		this.huespedes = new ArrayList<Huesped>();
		this.listaHabitaciones = new ArrayList<Integer>();
		this.vReal = huespedes.size();
		this.vRelativo = calcularVRelativo(huespedes);
		this.id = numGrupo++;
		numGrupo ++;
		this.reserva = reserva;
	}
	
	public void añadirHuesped(Huesped huesped) throws Exception {
		if (huespedes.contains(huesped)) {
			Exception e = new Exception("El huesped ya existe");
			throw e;
		}else {
			huespedes.add(huesped);
			this.vReal = huespedes.size();
			this.vRelativo = calcularVRelativo(huespedes);

		}
		
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
	
	public void añadirHabitacion(int id, int capacidad, double precioHabitacion) throws Exception {
		if (listaHabitaciones.contains(id)) {
			Exception e = new Exception("La habitación ya está añadida");
			throw e;
		}
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
	
	public void setPrecioTotalFactura(Double precioTotalFactura) {
		this.precioTotalFactura=precioTotalFactura;
	}
	
	public double getPrecioTotalFactura() {
		return this.precioTotalFactura;
	}
	
	public int getCapacidadCamas() {
		return capacidadCamas;
	}
	
	public Huesped getLider() {
		if (huespedes.size()== 0) {
			return null;
		}else {
			return huespedes.get(0);
			
		}
	}
	
	public void añadirServicio(Servicio servicio, int cantidad, boolean pagarEnSitio) {
		listaServicios.put(servicio, cantidad);
		if (pagarEnSitio) {
			saldoPagado+=servicio.getPrecio()*cantidad;
		}
		
		
	}

	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
		
	}

	public void borrarHabitaciones() {
		this.listaHabitaciones = new ArrayList<Integer>();
		
	}

	public boolean getCheck() {
		return check;
	}

	public void setCheck(boolean check) {
		this.check = check;
	}
	
}
