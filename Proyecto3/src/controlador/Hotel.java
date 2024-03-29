package controlador;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import modelo.*;

/**
 * @author tu papa
 *
 */
public class Hotel implements Serializable {

	/**
	 * 
	 */
	private TreeMap<Date, HashMap<Integer, Integer>> ocupados;
	private Grupo grupoEnCurso;
	private HashMap<Integer, Grupo> grupos;
	private HashMap<Usuario, ArrayList<Integer>> huespedReservas;
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
		grupos = new HashMap<Integer, Grupo>();
		usuarios = new HashMap<String, Usuario>();
		tarifas = new TreeMap<Date, Tarifa>();
		habitaciones = new HashMap<Integer, Habitacion>();
		serviciosHotel = new HashMap<Integer, Servicio>();
		restaurante = new Restaurante();
		ocupados = new TreeMap<Date, HashMap<Integer, Integer>>(); // <Date, <ID habitacion, ID grupo>
		huespedReservas = new HashMap<Usuario, ArrayList<Integer>>();
	}

	public boolean usuarioExiste(String login) {

		boolean exist = false;

		if (usuarios.containsKey(login))
			exist = false;

		return exist;
	}

	public void autenticar(String login, String password) throws Exception {

		Usuario usuarioActual = null;

		usuarioActual = usuarios.get(login);

		if (usuarioActual != null) {
			usuarioActual.iniciarSesion(password);
			this.usuarioActual = usuarioActual;

		} else {
			throw new Exception("el usuario no existe");
		}

	}

	/**
	 * Crea un Usuario dependiento su tipo y lo añade al HashMap Usuarios
	 * 
	 * @param tipo 1 para Admin, 2 para Empleado, 3 para huesped
	 * @throws Exception 
	 */
	public void añadirUsuario(String login, String password, String area, int tipo, boolean sobreEscribir ) throws Exception {
		if (usuarios.get(login)!= null && !sobreEscribir) {
			throw new Exception("El usuario ya existe");
		}
		switch (tipo) {
		case 1:
			Admin admin = new Admin(login, password, area);
			usuarios.put(login, admin);
			break;

		case 2:
			Empleado empleado = new Empleado(login, password, area);
			usuarios.put(login, empleado);
			break;
		case 3:
			Huesped huesped = new Huesped(login, password, area);
			usuarios.put(login, huesped);
			break;

		default:
			break;
		}

	}
	
	public void añadirUsuario(String login, String password, String area, int tipo) throws Exception {
		añadirUsuario(login, password, area, tipo, false);
	}

	public void añadirUsuarioHuesped(String login, String password, String documento, String nombre, String email,
			String telefono, int edad) throws Exception {
		Huesped huesped = new Huesped(login, password, documento, nombre, email, telefono, edad);
		añadirUsuario(login, password, "Huesped", 3, false);
		usuarios.put(login, huesped);
	}

	public void quitarUsuario(String nombre) {
		usuarios.remove(nombre);
	}

	public void quitarHabitacion(Integer ID) {
		habitaciones.remove(ID);
	}

	public boolean reservada(Integer ID) {
		try {
			Date first = ocupados.ceilingKey(this.hoy);
			Date last = ocupados.lastKey();
			if (first == null) {
				first = ocupados.floorKey(this.hoy);
			}
			SortedMap<Date, HashMap<Integer, Integer>> submapa = ocupados.subMap(first, last);
			for (HashMap<Integer, Integer> mapa : submapa.values()) {
				if (mapa.get(ID) != null) {
					return true;
				}
			}
			return false;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	public void inicializarTarifas() {
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
			if (fechaI.before(fechaF)) {
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

	public Date pasarMes(Date start, int i) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(start);
		cal.add(Calendar.MONTH, i);
		return cal.getTime();
	}

	public ArrayList<Tarifa> TarifasFaltantes() {
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

	public ArrayList<Tarifa> crearTarifasRango(Date fechaI, Date fechaF, TipoHabitacion tipo, double valor,
			boolean[] diasValores) {
		ArrayList<Tarifa> tarifasFaltantes = new ArrayList<>();

		fechaF = pasarDia(fechaF);
		SortedMap<Date, Tarifa> rangoTarifas = tarifas.subMap(fechaI, fechaF);

		for (Tarifa tarifa : rangoTarifas.values()) {

			Calendar calendar = Calendar.getInstance();
			calendar.setTime(tarifa.getFechaDate());

			int diaSemana = (calendar.get(Calendar.DAY_OF_WEEK) + 5) % 7;
			System.out.println("dia: " + diaSemana + "Estado: " + diasValores[diaSemana]);
			if (diasValores[diaSemana]) {
				try {
					tarifa.updatePrecio(tipo, valor);
				} catch (Exception e) {

					tarifasFaltantes.add(tarifa);
				}
			}

		}
		return tarifasFaltantes;

	}

	public void ForzarTarifasSobreTarifas(ArrayList<Tarifa> tarifas, TipoHabitacion tipo, double valor) {

		for (Tarifa tarifa : tarifas) {
			try {
				tarifa.updatePrecio(tipo, valor, true);
			} catch (Exception e) {

			}
		}
	}

	public ArrayList<Tarifa> crearTarifasSobreFechas(ArrayList<Date> fechas, TipoHabitacion tipo, double valor) {
		ArrayList<Tarifa> tarifasFaltantes = new ArrayList<>();

		for (Date fecha : fechas) {
			Tarifa tarifa = tarifas.get(fecha);
			try {
				tarifa.updatePrecio(tipo, valor);
			} catch (Exception e) {
				tarifasFaltantes.add(tarifa);
			}
		}
		return tarifasFaltantes;

	}

	public void borrarTarifa(Date fecha, TipoHabitacion tipo) {
		Tarifa tarifa = tarifas.get(fecha);
		try {
			tarifa.updatePrecio(tipo, -1, true);
		} catch (Exception e) {

		}

	}

	public void crearServicioHotel(String nombre, int precio) {
		Servicio servicio = new Servicio(nombre, precio);
		serviciosHotel.put(servicio.getId(), servicio);
	}

	public void eliminarServicioHotel(int id) {
		serviciosHotel.remove(id);
	}

	public HashMap<Integer, Servicio> getServiciosHotel() {
		return serviciosHotel;
	}

	public ArrayList<String[]> getServiciosHabitacion(String ID) {
		ArrayList<String[]> array = new ArrayList<>();
		Habitacion habitacion = habitaciones.get(Integer.parseInt(ID));
		ArrayList<Servicio> listaServicios = habitacion.getServicios();
		for (Servicio servicio : listaServicios) {
			String[] contenedora = { servicio.getNombre(), servicio.getPrecio() + "" };
			array.add(contenedora);
		}
		return array;
	}

	public ArrayList<String[]> getCamasHabitacion(String ID) {
		ArrayList<String[]> array = new ArrayList<>();
		Habitacion habitacion = habitaciones.get(Integer.parseInt(ID));
		ArrayList<Cama> listaCamas = habitacion.getCamas();
		for (Cama cama : listaCamas) {
			String apto = cama.getAptoParaNiño() ? "Si" : "No";
			String[] contenedora = { cama.getCapacidadCama() + "", apto };
			array.add(contenedora);
		}
		return array;
	}

	public void setCaracteristicas(String caracteristicas, int id) {
		Habitacion habitacion = habitaciones.get(id);
		habitacion.setCaracteristicas(caracteristicas);
		;
		habitaciones.put(id, habitacion);

	}

	public void añadirServicioHabitacion(int id, String nombre, int precio) {
		Habitacion habitacion = habitaciones.get(id);
		Servicio servicio = new Servicio(nombre, precio);
		habitacion.añadirServicioHabitacion(servicio);
		habitaciones.put(id, habitacion);
	}

	public Servicio añadirServicioHotelHabitacion(int idHabitacion, int idServicio, int cantidad,
			boolean pagarEnSitio) {
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
		
		// Añadir a log
		datos.añadirLogProductos((ProductoMenu) servicio, cantidad);	
		datos.añadirLogRestaurante((ProductoMenu) servicio, cantidad, grupo.getReserva());	
		return servicio;
	}

	public void crearCama(int id, int capacidadCama, boolean aptoParaNiño) {
		Habitacion habitacion = habitaciones.get(id);
		Cama cama = new Cama(capacidadCama, aptoParaNiño);
		habitacion.añadirCamas(cama);
		habitaciones.put(id, habitacion);
	}

	public void crearHabitacion(TipoHabitacion tipo, int id) {
		Habitacion habitacion = new Habitacion(tipo, id);
		habitaciones.put(id, habitacion);
	}

	public Servicio crearServicio(String nombre, int precio) {
		Servicio servicio = new Servicio(nombre, precio);
		return servicio;
	}

	public HashMap<Integer, Grupo> mostrarReservas(Date fechaI, Date fechaF) throws Exception {
		HashMap<Integer, Grupo> resultado = new HashMap<Integer, Grupo>();
		Grupo grupo;
		Reserva reserva;
		fechaF = pasarDia(fechaF);
		SortedMap<Date, HashMap<Integer, Integer>> rangoTarifas = ocupados.subMap(fechaI, fechaF);

		for (HashMap<Integer, Integer> mapa : rangoTarifas.values()) {
			for (int idGrupo : mapa.values()) {
				grupo = grupos.get(idGrupo);
				reserva = grupo.getReserva();
				if (reserva.getFechaI().after(fechaI) || reserva.getFechaI().equals(fechaI)) {
					if (reserva.getFechaF().before(fechaF) || reserva.getFechaF().equals(fechaF)) {
						resultado.put(idGrupo, grupo);
					}
				}

			}
		}
		if (resultado.isEmpty()) {
			Exception e = new Exception("No hay reservas en ese rango");
			throw e;
		}

		return resultado;
	}

	public void crearReserva(Date fechaI, Date fechaF) throws Exception {

		if (fechaI == null || fechaF == null) {
			Exception e = new Exception("Debe ingresar las fechas");
			throw e;
		}

		Reserva reserva = new Reserva(fechaI, fechaF);
		if (grupoEnCurso == null) {
			Grupo grupo = new Grupo(reserva);
			grupoEnCurso = grupo;

		} else {
			cambiarFechaReserva(fechaI, fechaF);
		}

	}

	public void cambiarFechaReserva(Date fechaI, Date fechaF) throws Exception {

		if (fechaI == null || fechaF == null) {
			Exception e = new Exception("Debe ingresar las fechas");
			throw e;
		}

		Reserva reserva = new Reserva(fechaI, fechaF);
		grupoEnCurso.setReserva(reserva);
		grupoEnCurso.borrarHabitaciones();

	}
	
	public void reservaSoloConLider() {
		Reserva reserva = new Reserva(null, null);
		Grupo grupo = new Grupo(reserva);
		grupoEnCurso = grupo;

	}

	public Grupo getGrupo(int id) throws Exception {
		Grupo grupo = grupos.get(id);

		if (grupo == null) {
			throw new Exception("El Numero ingresado no esta registrado");
		}

		return grupo;
	}

	public void cancelarReserva(int id) throws Exception {
		Grupo grupo = grupos.get(id);

		if (grupo == null) {
			throw new Exception("Numero de reserva invalido");
		}

		Date fechaI = grupo.getReserva().getFechaI();
		Date fechaF = grupo.getReserva().getFechaF();
		boolean cancelada = false;

		// 48 horas, dos dias

		Date fecha = volverDia(fechaI);
		fecha = volverDia(fecha);

		if (hoy.before(fecha)) {

			for (int habitacion : grupo.getListaHabitaciones()) {
				for (Date fechaX : getDateRange(fechaI, fechaF)) {
					HashMap<Integer, Integer> ocupacion = ocupados.get(fechaX);
					ocupacion.remove(habitacion);
					ocupados.put(fechaX, ocupacion);
				}
			}

			grupos.remove(id);

			cancelada = true;

		}

		if (!cancelada) {
			Exception e = new Exception(
					"No es posible cancelar la reserva, pues no está entre los tiempos de calcelación");
			throw e;
		}

	}

	public void añadirHuesped(String documento, String nombre, String email, String telefono, int edad)
			throws Exception {
		if (grupoEnCurso == null) {
			Exception e = new Exception("Debe establecer una fecha");
			throw e;
		}
		Huesped huesped = new Huesped(documento, nombre, email, telefono, edad);
		grupoEnCurso.añadirHuesped(huesped);
	}

	public void añadirHabitacionReserva(int idHabitacion) throws Exception {
		Habitacion habi = habitaciones.get(idHabitacion);
		int precioHabitacion = (int) getPrecioHabitacionReserva(habi);
		grupoEnCurso.añadirHabitacion(idHabitacion, habi.getCapacidad(), precioHabitacion);
	}

	public void completarReserva() throws Exception {
		boolean resultado = false;

		if (grupoEnCurso == null) {
			Exception e = new Exception("Debe establecer una fecha");
			throw e;
		}

		if (grupoEnCurso.getLider() == null) {
			Exception e = new Exception("Debe añadir almenos un huesped");
			throw e;
		}
		;
		resultado =(grupoEnCurso.getvRelativo() <= grupoEnCurso.getCapacidadCamas());
		
		if(resultado) {
			
			for (int idHabi: grupoEnCurso.getListaHabitaciones()) {
				llenarOcupados(idHabi);
			}

			grupos.put(grupoEnCurso.getId(), grupoEnCurso);
			grupoEnCurso = null;

		} else {
			Exception e = new Exception("Aun No hay suficientes habitaciones");
			throw e;
		}

	}

	public ArrayList<Integer> getArrayHabitaciones() {
		Set<Integer> set = habitaciones.keySet();
		ArrayList<Integer> array = new ArrayList<>();
		for (Integer ID : set) {
			array.add(ID);
		}
		return array;
	}

	public ArrayList<Integer> getListaHabitacionesGrupo() {
		if (grupoEnCurso == null) {
			return new ArrayList<Integer>();
		}
		return grupoEnCurso.getListaHabitaciones();
	}

	public ArrayList<Huesped> getHuespedesGrupoEnCurso() {
		if (grupoEnCurso == null) {
			return new ArrayList<Huesped>();
		}
		return grupoEnCurso.getHuespedes();
	}

	private ArrayList<Habitacion> consultarDisponibilidad(Date FechaI, Date FechaF, TipoHabitacion tipo) {
		ArrayList<Habitacion> habLista = new ArrayList<Habitacion>();
		FechaF = pasarDia(FechaF);
		SortedMap<Date, HashMap<Integer, Integer>> filtrado = ocupados.subMap(FechaI, FechaF);

		for (Entry<Integer, Habitacion> kv : habitaciones.entrySet()) {
			boolean aniadir = true;
			int id = kv.getKey();

			for (HashMap<Integer, Integer> ocupa2 : filtrado.values()) {
				if (ocupa2.containsKey(id))
					aniadir = false;
			}

			if (aniadir) {
				Habitacion habi = kv.getValue();
				if (habi.getTipoHabitacion() == tipo) {
					habLista.add(habi);
				}
			}
		}

		return habLista;
	}

	public ArrayList<Habitacion> DiponiblesParaGrupoEnCurso(TipoHabitacion tipo) throws Exception {
		if (grupoEnCurso == null) {
			Exception e = new Exception("No hay grupo");
			throw e;
		}
		return consultarDisponibilidad(grupoEnCurso.getReserva().getFechaI(), grupoEnCurso.getReserva().getFechaF(),
				tipo);

	}

	public String formatoFecha(Date fecha) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String fechaString = sdf.format(fecha);
		return fechaString;
	}

	public String getCaracteristicasHabitacion(Integer ID) {
		Habitacion habitacion = habitaciones.get(ID);
		return habitacion.getCaracteristicas();
	}

	public double getPrecioHabitacionReserva(Habitacion habitacion) throws Exception {
		TipoHabitacion tipo = habitacion.getTipoHabitacion();
		double precio = habitacion.getPrecioServicios();
		Date FechaI = grupoEnCurso.getReserva().getFechaI();
		Date FechaF = grupoEnCurso.getReserva().getFechaF();
		for (Tarifa tarifas : consultarTarifas(FechaI, FechaF)) {
			precio += tarifas.getPrecio(tipo);
		}
		return precio;

	}

	public void llenarOcupados(int idHabitacion) {
		Date fechaInicial = grupoEnCurso.getReserva().getFechaI();
		Date fechaFinal = grupoEnCurso.getReserva().getFechaF();
		int idGrupo = grupoEnCurso.getId();
		ArrayList<Date> llaves = getDateRange(fechaInicial, fechaFinal);
		for (Date fecha : llaves) {
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

	public void crearProductoMenu(LocalTime horaI, LocalTime horaF, boolean llevable, String nombre, int precio) {
		ProductoMenu productoMenu = new ProductoMenu(horaI, horaF, llevable, nombre, precio);
		restaurante.añadirProducto(productoMenu);
	}

	public void eliminarProductoMenu(ProductoMenu productoMenu) {
		restaurante.quitarProducto(productoMenu);
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

		while (fechaI.before(end) || fechaI.equals(end)) {
			rango.add(fechaI);
			fechaI = pasarDia(fechaI);
		}

		return rango;
	}

	public Date pasarDia(Date start) {
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
		ArrayList<Integer> nums = datos.leerStaticData();
		ArrayList<Integer> pisoIds = new ArrayList<Integer>();

		Servicio.setNumServicios(nums.get(0));
		Grupo.setNumGrupo(nums.get(1));

		for (int i = 2; i < nums.size(); i++) {
			pisoIds.add(nums.get(i));

		}
		Habitacion.setPisoIds(pisoIds);

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
			try {
				añadirUsuario("root", "Cookie", "Admin", 1);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				añadirUsuario("E1", "E1", "Empleado", 2);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public void data(Hotel hotelDatos) {

		setOcupados(hotelDatos.getOcupados());

		setGrupoEnCurso(hotelDatos.getGrupoEnCurso());

		setGrupos(hotelDatos.getGrupos());

		setTarifas(hotelDatos.getTarifas());

		setUsuarios(hotelDatos.getUsuarios());

		setHabitaciones(hotelDatos.getHabitaciones());

		setRestaurante(hotelDatos.getRestaurante());

		setServiciosHotel(hotelDatos.getServiciosHotel());

		setHuespedReservas(hotelDatos.getHuespedReservas());
	}

	public void guardarInformacion() {
		Hotel hotel = new Hotel();
		hotel.setHoy(hoy);
		hotel.data(this);
		try {
			datos.abrirOutput();
			datos.escribir(hotel);
			datos.cerrarOutput();
			datos.guardarStaticData(Servicio.getNumServicios(), Grupo.getNumGrupo(), Habitacion.getPisoIds());

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
		} else if (usuario.getClass() == Empleado.class) {
			tipo = "Empleado";
		} else if (usuario.getClass() == Huesped.class) {
			tipo = "Huesped";
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

	public Habitacion getHabitacion(int id) {
		return habitaciones.get(id);
	}

	public Cama crearCama(int capacidad, boolean exclusiva) {
		return new Cama(capacidad, exclusiva);
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

	public HashMap<Usuario, ArrayList<Integer>> getHuespedReservas() {
		return huespedReservas;
	}

	public void setHuespedReservas(HashMap<Usuario, ArrayList<Integer>> huespedReservas) {
		this.huespedReservas = huespedReservas;
	}

	public Date getHoy() {
		return hoy;
	}

	public void setHoy(Date hoy) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(hoy);
		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		this.hoy = calendar.getTime();
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

	public Integer[] ocupacionHoy() {
		HashMap<Integer, Integer> mapa = ocupados.get(hoy);
		if (mapa == null) {
			return new Integer[0];
		} else {
			Set<Integer> setInt = mapa.keySet();
			Object[] resultado = (Object[]) setInt.toArray();
			Arrays.sort(resultado);
			Integer[] integerArray = new Integer[resultado.length];
			for (int i = 0; i < resultado.length; i++) {
				integerArray[i] = (Integer) resultado[i];
			}
			return integerArray;
		}
		// Integer[] r = {104, 105, 202, 203, 206, 308};
		// return r;
	}

	public void printOcupados() {
		// System.out.println("------------INICIO LLAVES------------");
		Set<Date> keyss = ocupados.keySet();
		for (Date dia : keyss) {
		}
	}

	public int contarOcupadasDia(Date diaX) {
		HashMap<Integer, Integer> mapa = ocupados.get(diaX);
		if (mapa == null) {
			return 0;	
		}
		else {
			Set<Integer> setInt = mapa.keySet();
			Object[] array = (Object[]) setInt.toArray();
			int resultado = array.length;
			return resultado;
		}
	}

	public int getTotalHabitaciones() {
		return habitaciones.keySet().size();
	}

	public void borrarDatos() {
		grupos = new HashMap<Integer, Grupo>();
		tarifas = new TreeMap<Date, Tarifa>();
		habitaciones = new HashMap<Integer, Habitacion>();
		serviciosHotel = new HashMap<Integer, Servicio>();
		restaurante = new Restaurante();
		ocupados = new TreeMap<Date, HashMap<Integer, Integer>>(); // <Date, <ID habitacion, ID grupo>
		inicializarTarifas();
		datos.borrarLog(new File("log/productos.log"));
		datos.borrarLog(new File("log/restaurante.log"));
		datos.borrarLog(new File("log/facturas.log"));
	}

	public ArrayList<Integer> idHuespedReservas() {
		System.out.println("Hotel.idHuespedReservas()");
		ArrayList<Integer> ids = huespedReservas.get(usuarioActual);
		if (ids == null) {
			ids = new ArrayList<Integer>();
		}

		return ids;
	}

	public void añadirReservaAHuesped(int idGrupo) {
		System.out.println("Hotel.añadirReservaAHuesped()");
		ArrayList<Integer> ids = huespedReservas.get(usuarioActual);
		if (ids == null) {
			ids = new ArrayList<Integer>();
			System.out.println("Ids Nulo");
		}
		System.out.println(idGrupo);
		ids.add(idGrupo);
		huespedReservas.put(usuarioActual, ids);
	}
	
	public HashMap<String, int[]> datosReporteProductos() {
		return datos.obtenerLogProductos();

		
	}

	public ArrayList<int[]> datosReporteRestaurante() {
		// TODO Auto-generated method stub
		return datos.datosReporteRestaurante();
	}

	public void añadirLogFacturas(double precioTotalFactura) {
		datos.añadirLogFacturas(hoy, getTotalHabitaciones());
		
	}
	
	public HashMap<String, Integer> datosReporteFacturas() {
		return datos.datosReporteFacturas();
	}

}
