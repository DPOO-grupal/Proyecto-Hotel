package controlador;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.TreeMap;

import modelo.Grupo;
import modelo.Habitacion;
import modelo.Huesped;
import modelo.Persistencia;
import modelo.Reserva;
import modelo.Restaurante;
import modelo.Tarifa;
import modelo.Usuario;

public class Hotel {
	
	private TreeMap<Date, HashMap<Integer, Integer>> ocupados;
	private Reserva reserva;
	private HashMap<Integer, Grupo> grupos;
	private TreeMap<Date, Tarifa> tarifas;
	private Persistencia datos;
	private HashMap<String, Usuario> usuarios;
	private HashMap<Integer, Habitacion> habitaciones;
	private Restaurante restaurante;
	
	public ArrayList<Habitacion> crearReserva(Date fechaI, Date fechaF, int tamanioGrupo, String[] nombres, String[] documentos, String[] emails, String[] telefonos, Integer[] ids, Integer[] edades) {
		/* por ahora solo crea la reserva, huespedes y grupo 
		 * FALTA HACER LA PARTE DE RETORNAR LA LISTA DE HABITACIONES LLENA *
		 */
		ArrayList<Habitacion> habitaciones = new ArrayList<Habitacion>();
		Reserva reserva = new Reserva(fechaI, fechaF);
		ArrayList<Huesped> huespedes = crearHuespedes(tamanioGrupo, nombres, documentos, emails, telefonos, ids, edades);
		Grupo grupo = new Grupo(huespedes, reserva);
		grupos.put(grupo.getId(), grupo);
		
		return habitaciones;
	}
	private ArrayList<Huesped> crearHuespedes(int tamanioGrupo, String[] nombres, String[] documentos, String[] emails, String[] telefonos, Integer[] ids, Integer[] edades) {
		ArrayList<Huesped> huespedes = new ArrayList<Huesped>();
		for(int i=0; i < tamanioGrupo; i++) {
			Huesped huesped = new Huesped(documentos[i], nombres[i], emails[i], telefonos[i], ids[i], edades[i]);
			huespedes.add(huesped);
		}
		return huespedes;
	}

}
