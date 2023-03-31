package controlador;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.swing.text.GapContent;

import modelo.*;

/**
 * @author tu papa
 *
 */
public class Hotel implements Serializable{
	
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
		grupos = new HashMap <Integer, Grupo>();
		usuarios = new HashMap<String, Usuario>();
		
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
	
	public void inicializarTarifas() {
		tarifas = new TreeMap<Date, Tarifa>();
		Date fechaI = new Date(2023, 3, 31);
		Date fechaF = fechaI;
		int annoI = fechaI.getYear();
		annoI++;
		fechaF.setYear(annoI);
	}
	
	public ArrayList<Tarifa> checkTarifas(Date fechaI, Date fechaF) {
		boolean completo;
		ArrayList<Tarifa> faltantes = new ArrayList<Tarifa>();
		SortedMap<Date, Tarifa> rangoTarifas = tarifas.subMap(fechaI, fechaF);
		
		for (Tarifa tarifa : rangoTarifas.values()) {
			
			completo = tarifa.completo();
			
			if (!completo) {
				faltantes.add(tarifa); 
			}
		}
		return faltantes;
	}
	
	public void crearTarifa(Date fechaI, Date fechaF, TipoHabitacion tipo, double valor) {
		SortedMap<Date, Tarifa> rangoTarifas = tarifas.subMap(fechaI, fechaF);
		
		for (Tarifa tarifa : rangoTarifas.values()) {
			tarifa.updatePrecio(tipo, valor);
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
	
	public boolean completarReserva(int idHabitacion) {
		boolean resultado = false;
		llenarOcupados(idHabitacion);
		
		return resultado;
	}
	
	public void llenarOcupados(int idHabitacion) {
		Date fechaInicial = grupoEnCurso.getReserva().getFechaI();
		Date fechaFinal = grupoEnCurso.getReserva().getFechaF();
		int idGrupo = grupoEnCurso.getId();
		ArrayList<Date> llaves = getDateRange(fechaInicial, fechaFinal);
		for(Date fecha : llaves) {
			HashMap<Integer, Integer> mapa = ocupados.get(fecha);
			if (mapa == null) {
				mapa = new HashMap<Integer, Integer>();
			}
			mapa.put(idHabitacion, idGrupo);
			ocupados.put(fecha, mapa);
		}
	}
	
	
    public ArrayList<Date> getDateRange(Date start, Date end) {
        ArrayList<Date> rango = new ArrayList<Date>();
        Date fechaI = start;
        while(fechaI.before(end) || fechaI.equals(end)) {
            rango.add(fechaI); 
            fechaI = pasarDia(fechaI);
        }
        return rango;
    }
	
	private Date pasarDia(Date start) {
		Date end = start;
		int diaI = start.getDate();
		diaI++;
		end.setDate(diaI);
		return end;
	}


	public void cargarInformacion() {
		datos = new Persistencia();
		
		Hotel hotelDatos = null;
		try {
			datos.abrirInput();
			hotelDatos = datos.leer();
			datos.cerrarInput();
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
				
		System.out.println(hotelDatos);
		if (hotelDatos != null) {
			data(hotelDatos);
		} else {
			
			añadirUsuario("root", "Cookie", 1);
		}
		
		int sizeGrupo = grupos.size();
		Grupo.setNumGrupo(sizeGrupo);
		// TODO Aumentar el contador de servicios, sumar los productos menu y servicios
		Usuario.setHotel(this);
		
	}
	public void data (Hotel hotelDatos) {
		setOcupados(hotelDatos.getOcupados());
		
		setGrupoEnCurso(hotelDatos.getGrupoEnCurso());

		setGrupos(hotelDatos.getGrupos());
		
		setTarifas(hotelDatos.getTarifas());
		
		setUsuarios(hotelDatos.getUsuarios());

		setHabitaciones(hotelDatos.getHabitaciones());
		
		setRestaurante(hotelDatos.getRestaurante());
	}
	
	public void guardarInformacion() {
		Hotel hotel = new Hotel();
		hotel.data(this);
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
