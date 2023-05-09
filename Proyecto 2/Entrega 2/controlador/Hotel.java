package controlador;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

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
	private HashMap<Integer, Servicio> serviciosHotel;
	private Restaurante restaurante;
	private Usuario usuarioActual;
	private Date hoy;
	
	

	public Hotel() {
		// TODO inicializar todas las estructuras
		grupos = new HashMap <Integer, Grupo>();
		usuarios = new HashMap<String, Usuario>();
		tarifas = new TreeMap<Date, Tarifa>();
		habitaciones = new HashMap<Integer, Habitacion>();
		serviciosHotel = new HashMap<Integer, Servicio>();
		restaurante= new Restaurante();
		ocupados = new TreeMap<Date, HashMap<Integer, Integer>>();
	}
	
	


	public boolean usuarioExiste(String login) {
		
		boolean exist = false;
		
		if (usuarios.containsKey(login))
			exist = false;
		
		return exist;
	}
	
	public void autenticar(String login, String password) throws Exception {
		
		Usuario usuarioActual = null;
		Exception e = new Exception("Usuario o Contraseña incorrectos");

		usuarioActual = usuarios.get(login);
		
		if (usuarioActual != null) {
			if (usuarioActual.iniciarSesion(password)) {
				this.usuarioActual = usuarioActual;
			}else {
				throw e;
			}
			
		} else {
			throw e;
		}

	}
	/**
	 * Crea un Usuario dependiento su tipo y lo añade al HashMap Usuarios
	 * @param tipo 1 para Admin, 2 para Empleado
	 */
	public void añadirUsuario(String login, String password, String area, int tipo) {
		switch (tipo) {
		case 1:
			Admin admin = new Admin(login, password, area);
			usuarios.put(login, admin);
			break;
		
		case 2:
			Empleado empleado = new Empleado(login, password, area);
			usuarios.put(login, empleado);
			break;
			
		default:
			break;
		}
		
	}
	
	public void quitarUsuario(String nombre) {
		usuarios.remove(nombre);
	}
	

	
	public void inicializarTarifas(){
		// TODO promando
		Tarifa tarifa;
		Date fechaI;
		Date fechaF;
		
		// Arbol completamente vacio
		if (tarifas.size() == 0) {
			fechaI = hoy;
			fechaF = pasarAnno(hoy);
			
			for (Date fecha : getDateRange(fechaI, fechaF)) {
				tarifa = new Tarifa(fecha);
				tarifas.put(fecha, tarifa);
			}
		} else {
			
			// Llenar dias antes de los que estan en el arbol
			fechaI = hoy;
			fechaF = tarifas.firstKey();
			if(fechaI.before(fechaF)) {
				ArrayList<Date> rango = getDateRange(fechaI, fechaF);
				for (Date fecha : rango) {
					if (fecha.before(fechaF)) {
						tarifa = new Tarifa(fecha);
						tarifas.put(fecha, tarifa);
					}
				}
			}
			
			// Llenar dias despues de los que estan en el arbol
			fechaI = tarifas.lastKey();
			fechaF = pasarAnno(hoy);
			if (fechaI.before(fechaF)) {
				for (Date fecha : getDateRange(fechaI, fechaF)) {
					if (fecha.before(fechaF)) {
						tarifa = new Tarifa(fecha);
						tarifas.put(fecha, tarifa);
					}
				}
			}
		}
		
		
		
		
	}



	public Collection<Tarifa> consultarTarifas(Date fechaI, Date fechaF) {
		fechaF = pasarDia(fechaF);
		SortedMap<Date, Tarifa> rangoTarifas = tarifas.subMap(fechaI, fechaF);		
		
		return rangoTarifas.values();
	}
	
	public ArrayList<Tarifa> checkTarifas() {
		Date fechaF = pasarAnno(hoy);
		boolean completo;
		ArrayList<Tarifa> faltantes = new ArrayList<Tarifa>();
		fechaF = pasarDia(fechaF);
		SortedMap<Date, Tarifa> rangoTarifas = tarifas.subMap(hoy, fechaF);
		for (Tarifa tarifa : rangoTarifas.values()) {

			completo = tarifa.completo();
			
			if (!completo) {
				faltantes.add(tarifa); 
			}
		}
		return faltantes;
	}
	
	public boolean crearTarifa(Date fechaI, Date fechaF, TipoHabitacion tipo, double valor) {
		boolean completado = true;
		fechaF = pasarDia(fechaF);
		SortedMap<Date, Tarifa> rangoTarifas = tarifas.subMap(fechaI, fechaF);
		
		for (Tarifa tarifa : rangoTarifas.values()) {
			if(!tarifa.updatePrecio(tipo, valor)) {
				completado = false;
			}
			
		}
		return completado;
		
	}
	
	public void crearServicioHotel(String nombre, double precio) {
		Servicio servicio = new Servicio(nombre, precio);
		serviciosHotel.put(servicio.getId(), servicio);
	}
	
	public HashMap<Integer, Servicio> getServiciosHotel(){
		return serviciosHotel;
	}
	
	public void setCaracteristicas(String caracteristicas, int id) {
		Habitacion habitacion =	habitaciones.get(id);
		habitacion.setCaracteristicas(caracteristicas);;
		habitaciones.put(id, habitacion);
		
	}
	
	public void añadirServicioHabitacion(int id, String nombre, double precio) {
		Habitacion habitacion =	habitaciones.get(id);
		Servicio servicio = new Servicio(nombre, precio);
		habitacion.añadirServicioHabitacion(servicio);
		habitaciones.put(id, habitacion);
	}
	
	public Servicio añadirServicioHotelHabitacion(int idHabitacion, int idServicio, int cantidad, boolean pagarEnSitio) {
		int idGrupo = ocupados.get(hoy).get(idHabitacion);
		Servicio servicio = serviciosHotel.get(idServicio);
		Grupo grupo = grupos.get(idGrupo);
		grupo.añadirServicio(servicio, cantidad, pagarEnSitio);
		return servicio;
	}
	
	public Servicio añadirProductoMenuHabitacion(int idHabitacion, int idServicio, int cantidad, boolean pagarEnSitio) {
		int idGrupo = ocupados.get(hoy).get(idHabitacion);
		Servicio servicio = restaurante.getProducto(idServicio);
		Grupo grupo = grupos.get(idGrupo);
		grupo.añadirServicio(servicio, cantidad, pagarEnSitio);
		return servicio;
	}
	
	
	public void crearCama(int id, int capacidadCama, boolean aptoParaNiño) {
		Habitacion habitacion =	habitaciones.get(id);
		Cama cama = new Cama(capacidadCama, aptoParaNiño);
		habitacion.añadirCamas(cama);
		habitaciones.put(id, habitacion);	
	}
	
	
	public void crearHabitacion(TipoHabitacion tipo, int id) {
		Habitacion habitacion = new Habitacion(tipo, id);
		habitaciones.put(id, habitacion);
	}
	
	
	public HashMap<Integer,Grupo> mostrarReservas(Date fechaI, Date fechaF) {
		HashMap<Integer,Grupo> resultado = new HashMap<Integer,Grupo>();
		Grupo grupo;
		Reserva reserva;
		fechaF = pasarDia(fechaF);
		SortedMap<Date, HashMap<Integer, Integer>> rangoTarifas = ocupados.subMap(fechaI, fechaF);
		
		for (HashMap<Integer, Integer> mapa : rangoTarifas.values()) {
			System.out.println(mapa.values().size());
			for (int idGrupo : mapa.values()) {
				grupo = grupos.get(idGrupo);
				reserva = grupo.getReserva();
				if (reserva.getFechaI().after(fechaI) || reserva.getFechaI().equals(fechaI)) {
					if(reserva.getFechaF().before(fechaF) || reserva.getFechaF().equals(fechaF)) {
						resultado.put(idGrupo, grupo);
					}
				}

			}
		}
		return resultado;
	}

	public void crearReserva(Date fechaI, Date fechaF, int tamanioGrupo, String[] nombres, String[] documentos, String[] emails, String[] telefonos, int[] edades) {
		
		Reserva reserva = new Reserva(fechaI, fechaF);
		ArrayList<Huesped> huespedes = crearHuespedes(tamanioGrupo, nombres, documentos, emails, telefonos, edades);
		Grupo grupo = new Grupo(huespedes, reserva);
		grupos.put(grupo.getId(), grupo);
		grupoEnCurso = grupo;
				
	}
	
	public boolean cancelarReserva(int id) {
		Grupo grupo = grupos.get(id);
		Date fechaI = grupo.getReserva().getFechaI();
		boolean cancelada = false;
		
		// 48 horas, dos dias
		
		fechaI = volverDia(fechaI);
		fechaI = volverDia(fechaI);
		
		if(hoy.before(fechaI)) {
			grupos.remove(id);
			cancelada = true;
			
		}
		return cancelada;
				
	}





	private ArrayList<Huesped> crearHuespedes(int tamanioGrupo, String[] nombres, String[] documentos, String[] emails, String[] telefonos, int[] edades) {
		ArrayList<Huesped> huespedes = new ArrayList<Huesped>();
		for(int i=0; i < tamanioGrupo; i++) {
			Huesped huesped = new Huesped(documentos[i], nombres[i], emails[i], telefonos[i], edades[i]);
			huespedes.add(huesped);
		}
		return huespedes;
	}
	
	private ArrayList<Habitacion> consultarDisponibilidad (Date FechaI, Date FechaF, TipoHabitacion tipo) {
		ArrayList<Habitacion> habLista = new ArrayList<Habitacion>();
		FechaF = pasarDia(FechaF);
		SortedMap<Date, HashMap<Integer, Integer>> filtrado =  ocupados.subMap(FechaI, FechaF);
		
		for (Entry<Integer, Habitacion> kv : habitaciones.entrySet()) {
			boolean aniadir = true;
			int id = kv.getKey();
			
			for (HashMap<Integer, Integer> ocupa2 : filtrado.values()) {
				if(ocupa2.containsKey(id))
					aniadir = false;
			}
			
			if(aniadir) {
				Habitacion habi = kv.getValue();
				if(habi.getTipoHabitacion() == tipo) {
					habLista.add(habi);
				}
			}
		}
		
		return habLista;
	}
	
	public ArrayList<Habitacion> DiponiblesParaGrupoEnCurso(TipoHabitacion tipo) {
		return consultarDisponibilidad(grupoEnCurso.getReserva().getFechaI(), grupoEnCurso.getReserva().getFechaF(), tipo);
		
	}

	public boolean completarReserva(int idHabitacion) {
		boolean resultado = false;
		llenarOcupados(idHabitacion);
		Habitacion habi = habitaciones.get(idHabitacion);
		int precioHabitacion = (int)getPrecioHabitacionReserva(habi);
		grupoEnCurso.añadirHabitacion(idHabitacion, habi.getCapacidad(), precioHabitacion);
		
		resultado =(grupoEnCurso.getvRelativo() <= grupoEnCurso.getCapacidadCamas());
		
		if(resultado) {
			grupos.put(grupoEnCurso.getId(), grupoEnCurso);
			grupoEnCurso = null;
		}
		
		return !resultado;
	}
		
	
	
	public double getPrecioHabitacionReserva(Habitacion habitacion) {
		TipoHabitacion tipo = habitacion.getTipoHabitacion(); 
		double precio = habitacion.getPrecioServicios();
		Date FechaI = grupoEnCurso.getReserva().getFechaI();
		Date FechaF = grupoEnCurso.getReserva().getFechaF();
		for (Tarifa tarifas: consultarTarifas(FechaI, FechaF)) {
			precio += tarifas.getPrecio(tipo);
		}
		return precio;
		

		
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
	
	public Grupo checkOut(int idGrupo) {
		Grupo grupo = grupos.get(idGrupo);
		grupos.remove(idGrupo);
		
		ArrayList<Integer> idsHabitaciones = grupo.getListaHabitaciones();
		Reserva reserva = grupo.getReserva();
		Date fechaI = reserva.getFechaI();
		Date fechaF = reserva.getFechaF();
		for (Date date : getDateRange(fechaI, fechaF)) {
			HashMap<Integer, Integer> mapa = ocupados.get(date);
			for (Integer id : idsHabitaciones) {
				mapa.remove(id);
			}
			ocupados.put(date, mapa);
		}
		return grupo;	
	}

	
	public void crearProductoMenu(Date horaI, Date horaF, boolean llevable,String nombre, double precio) {
		ProductoMenu productoMenu = new ProductoMenu(horaI, horaF, llevable, nombre, precio);
		restaurante.añadirProducto(productoMenu);
	}
	
	public void añadirProductoRestaurante(ProductoMenu productoMenu) {
		restaurante.añadirProducto(productoMenu);
	}
	
	public HashMap<Integer, ProductoMenu> getMenu() {
		return restaurante.getMenu();
	}
	
    public ArrayList<Date> getDateRange(Date start, Date end) {
        ArrayList<Date> rango = new ArrayList<Date>();
        Date fechaI = (Date) start.clone();

        while(fechaI.before(end) || fechaI.equals(end)) {
            rango.add(fechaI); 
            fechaI = pasarDia(fechaI);
        }
        
        return rango;
    }
	
    private Date pasarDia(Date start) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(start);
        cal.add(Calendar.DAY_OF_MONTH, 1);
        return cal.getTime();
    }
    
	
	private Date volverDia(Date end) {
		Calendar cal = Calendar.getInstance();
        cal.setTime(end);
        cal.add(Calendar.DAY_OF_MONTH, -1);
        return cal.getTime();
	}
	
    public Date pasarAnno(Date start) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(start);
        cal.add(Calendar.YEAR, 1);
        return cal.getTime();
    }


	public void cargarInformacion() {
		datos = new Persistencia();
		int[] nums = datos.leerStaticData();
		
		Servicio.setNumServicios(nums[0]);
		Grupo.setNumGrupo(nums[1]);
		
		Hotel hotelDatos = null;
		try {
			datos.abrirInput();
			hotelDatos = datos.leer();
			datos.cerrarInput();
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
				
		if (hotelDatos != null) {
			data(hotelDatos);
		} else {
			inicializarTarifas();
			añadirUsuario("root", "Cookie", "Lobby", 1);
		}
				
	}
	public void data (Hotel hotelDatos) {
		
		setOcupados(hotelDatos.getOcupados());
		
		setGrupoEnCurso(hotelDatos.getGrupoEnCurso());

		setGrupos(hotelDatos.getGrupos());
		
		setTarifas(hotelDatos.getTarifas());
		
		setUsuarios(hotelDatos.getUsuarios());

		setHabitaciones(hotelDatos.getHabitaciones());
		
		setRestaurante(hotelDatos.getRestaurante());
		
		setServiciosHotel(hotelDatos.getServiciosHotel());
	}
	
	public void guardarInformacion() {
		Hotel hotel = new Hotel();
		hotel.setHoy(hoy);
		hotel.data(this);
		try {
			datos.abrirOutput();
			datos.escribir(hotel);
			datos.cerrarOutput();
			datos.guardarStaticData(Servicio.getNumServicios(), Grupo.getNumGrupo());
			
			
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
	
	public String getTipo(String login) {
		Usuario usuario = usuarios.get(login);
		String tipo = "Usuario";
		if (usuario.getClass() == Admin.class) {
			tipo = "Admin";
		}
		else if (usuario.getClass() == Empleado.class) {
			tipo = "Empleado";
		}
		return tipo;
	}
	
	public String getArea(String login) {
		Usuario usuario = usuarios.get(login);
		return usuario.getArea();
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
		this.tarifas.putAll(tarifas.subMap(hoy, true, pasarAnno(hoy), true));
		inicializarTarifas();
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


	public Date getHoy() {
		return hoy;
	}
	
	public void setHoy(Date hoy) {
		this.hoy = hoy;
	}

	public void setServiciosHotel(HashMap<Integer, Servicio> serviciosHotel) {
		this.serviciosHotel = serviciosHotel;
	}

	public String[] darUsuarios() {
		HashMap<String, Usuario> mapa = getUsuarios();
		Set<String> set = mapa.keySet();
		String[] lista = set.toArray(String[]::new);
		return lista;
	}
	
	public boolean checkUsuario(String nombre) {
		Usuario usuario = usuarios.get(nombre);
		return usuarioActual.equals(usuario);
	}
}
