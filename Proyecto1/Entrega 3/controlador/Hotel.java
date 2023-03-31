package controlador;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import modelo.*;

/**
 * @author tu papa
 *
 */
public class Hotel{
	
	/**
	 * 
	 */
	private TreeMap<Date, HashMap<Integer, Integer>> ocupados;
	private Grupo grupoEnCurso;
	private HashMap<Integer, Grupo> grupos;
	private TreeMap<Date, Tarifa> tarifas;
	private Persistencia datos;
	private HashMap<String, Usuario> usuarios;
	private HashMap<Integer, Habitacion> habitaciones;
	private Restaurante restaurante;
	private Usuario usuarioActual;
	
	

	public Hotel() {
		// TODO inicializar todas las estructuras
		datos = new Persistencia();
		grupos = new HashMap <Integer, Grupo>();
		usuarios = new HashMap<String, Usuario>();
		
		Hotel hotelDatos = cargarInformacion();
		
		if (hotelDatos != null) {
			
			setOcupados(hotelDatos.getOcupados());
			
			setGrupoEnCurso(hotelDatos.getGrupoEnCurso());
			setGrupos(hotelDatos.getGrupos());			
			setTarifas(hotelDatos.getTarifas());			
			setUsuarios(hotelDatos.getUsuarios());
			setHabitaciones(hotelDatos.getHabitaciones());			
			setRestaurante(hotelDatos.getRestaurante());		} else {
			
			añadirUsuario("root", "Cookie", 1);
		}
		
		int sizeGrupo = grupos.size();
		Grupo.setNumGrupo(sizeGrupo);
		// TODO Aumentar el contador de servicios, sumar los productos menu y servicios
		Usuario.setHotel(this);
	}
	
	
	public boolean usuarioExiste(String login) {
		
		boolean exist = false;
		
		if (usuarios.containsKey(login))
			exist = false;
		
		return exist;
	}
	
	public boolean autenticar(String login, String password) {
		
		boolean autent = false;
		Usuario usuarioActual = null;
		
		usuarioActual = usuarios.get(login);
		
		if (usuarioActual != null) {
	
			if (usuarioActual.iniciarSesion(password)) {
				autent = true;
				this.usuarioActual = usuarioActual;
			}
		} 
		
		return autent;
	}
	/**
	 * Crea un Usuario dependiento su tipo y lo añade al HashMap Usuarios
	 * @param tipo 1 para Admin, 2 para Empleado
	 */
	public void añadirUsuario(String login, String password, int tipo) {
		switch (tipo) {
		case 1:
			Admin admin = new Admin(login, password);
			usuarios.put(login, admin);
			break;
		
		case 2:
			Empleado empleado = new Empleado(login, password);
			usuarios.put(login, empleado);
			break;
			
		default:
			break;
		}
		
	}
	
	public ArrayList<Habitacion> crearReserva(Date fechaI, Date fechaF, int tamanioGrupo, String[] nombres, String[] documentos, String[] emails, String[] telefonos, Integer[] ids, Integer[] edades, TipoHabitacion tipo) {
		
		Reserva reserva = new Reserva(fechaI, fechaF);
		ArrayList<Huesped> huespedes = crearHuespedes(tamanioGrupo, nombres, documentos, emails, telefonos, ids, edades);
		Grupo grupo = new Grupo(huespedes, reserva);
		grupos.put(grupo.getId(), grupo);
		grupoEnCurso = grupo;
		
		ArrayList<Habitacion> habitaciones = consultarDisponibilidad(fechaI, fechaF, tipo);
		
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
	
	private ArrayList<Habitacion> consultarDisponibilidad (Date FechaI, Date FechaF, TipoHabitacion tipo) {
		ArrayList<Habitacion> habLista = new ArrayList<Habitacion>();
		
		TreeMap<Date, HashMap<Integer, Integer>> filtrado = (TreeMap<Date, HashMap<Integer, Integer>>) ocupados.subMap(FechaI, FechaF);
		
		for (Map.Entry<Integer, Habitacion> kv : habitaciones.entrySet()) {
			boolean aniadir = false;
			int id = kv.getKey();
			
			for (HashMap<Integer, Integer> ocupa2 : filtrado.values()) {
				if(ocupa2.containsKey(id))
					aniadir = false;
			}
			
			if(aniadir) {
				Habitacion habi = kv.getValue();
				if(habi.getTipo() == tipo) {
					habLista.add(habi);
				}
			}
		}
		
		return habLista;
	}
	
	public Hotel cargarInformacion() {
		Hotel hotelDatos = null;
		try {
			datos.abrirInput();
			hotelDatos = datos.leer();
			datos.cerrarInput();
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		
		return hotelDatos;
	}
	
	public void guardarInformacion(Hotel hotel) {
		try {
			datos.abrirOutput();
			datos.escribir(hotel);
			datos.cerrarOutput();
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public TreeMap<Date, HashMap<Integer, Integer>> getOcupados() {
		return ocupados;
	}

	public Grupo getGrupoEnCurso() {
		return grupoEnCurso;
	}

	public HashMap<Integer, Grupo> getGrupos() {
		return grupos;
	}

	public TreeMap<Date, Tarifa> getTarifas() {
		return tarifas;
	}

	public HashMap<String, Usuario> getUsuarios() {
		return usuarios;
	}

	public HashMap<Integer, Habitacion> getHabitaciones() {
		return habitaciones;
	}

	public Restaurante getRestaurante() {
		return restaurante;
	}

	public void setOcupados(TreeMap<Date, HashMap<Integer, Integer>> ocupados) {
		this.ocupados = ocupados;
	}

	public void setGrupoEnCurso(Grupo grupoEnCurso) {
		this.grupoEnCurso = grupoEnCurso;
	}

	public void setGrupos(HashMap<Integer, Grupo> grupos) {
		this.grupos = grupos;
	}

	public void setTarifas(TreeMap<Date, Tarifa> tarifas) {
		this.tarifas = tarifas;
	}

	public void setUsuarios(HashMap<String, Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public void setHabitaciones(HashMap<Integer, Habitacion> habitaciones) {
		this.habitaciones = habitaciones;
	}

	public void setRestaurante(Restaurante restaurante) {
		this.restaurante = restaurante;
	}
	public Usuario getUsuarioActual() {
		return usuarioActual;
	}

	public void setUsuarioActual(Usuario usuarioActual) {
		this.usuarioActual = usuarioActual;
	}




}
