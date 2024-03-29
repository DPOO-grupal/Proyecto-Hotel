package controlador;

import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
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
		ocupados = new TreeMap<Date, HashMap<Integer, Integer>>(); // <Date, <ID habitacion, ID grupo>
	}
	
	


	public boolean usuarioExiste(String login) {
		
		boolean exist = false;
		
		if (usuarios.containsKey(login))
			exist = false;
		
		return exist;
	}
	
	public void autenticar(String login, String password) throws Exception {
		
		Usuario usuarioActual = null;
		Exception e = new Exception("el usuario no existe");

		usuarioActual = usuarios.get(login);
		
		if (usuarioActual != null) {
			usuarioActual.iniciarSesion(password);
			this.usuarioActual = usuarioActual;
		
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
	
	public Date pasarMes(Date start, int i) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(start);
        cal.add(Calendar.MONTH, i);
        return cal.getTime();
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
	
	public ArrayList<String[]> getServiciosHabitacion(String ID) {
		ArrayList<String[]> array = new ArrayList<>();
		Habitacion habitacion = habitaciones.get(Integer.parseInt(ID));
		ArrayList<Servicio> listaServicios = habitacion.getServicios();
		for (Servicio servicio : listaServicios) {
			String[] contenedora = {servicio.getNombre(), servicio.getPrecio()+""};
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
			String[] contenedora = {cama.getCapacidadCama()+"", apto};
			array.add(contenedora);
		}
		return array;
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
	
	
	public void crearHabitacion(TipoHabitacion tipo, int id, int capacidad, boolean apto) {
		Habitacion habitacion = new Habitacion(tipo, id, capacidad, apto);
		habitaciones.put(id, habitacion);
	}
	
	
	public HashMap<Integer,Grupo> mostrarReservas(Date fechaI, Date fechaF) throws Exception {
		HashMap<Integer,Grupo> resultado = new HashMap<Integer,Grupo>();
		Grupo grupo;
		Reserva reserva;
		fechaF = pasarDia(fechaF);
		SortedMap<Date, HashMap<Integer, Integer>> rangoTarifas = ocupados.subMap(fechaI, fechaF);
		
		for (HashMap<Integer, Integer> mapa : rangoTarifas.values()) {
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
	
	public Grupo getGrupo(int id) {
		Grupo grupo = grupos.get(id);
		return grupo;
	}
	
	public void cancelarReserva(int id) throws Exception {
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
		
		if (!cancelada) {
			Exception e = new Exception("No es posible cancelar la reserva");
			throw e;
		}
				
	}


	public void añadirHuesped(String documento, String nombre, String email, String telefono, int edad) throws Exception {
		if (grupoEnCurso == null) {
			Exception e = new Exception("Debe establecer una fecha");
			throw e;
		} 
		Huesped huesped = new Huesped(documento, nombre, email, telefono, edad);
		grupoEnCurso.añadirHuesped(huesped);
	}
	
	public void añadirHabitacionReserva(int idHabitacion) throws Exception {
		Habitacion habi = habitaciones.get(idHabitacion);
		int precioHabitacion = (int)getPrecioHabitacionReserva(habi);
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
		System.out.println(grupoEnCurso.getvRelativo());
		System.out.print(grupoEnCurso.getCapacidadCamas());
		
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
	
	public ArrayList<Habitacion> DiponiblesParaGrupoEnCurso(TipoHabitacion tipo) throws Exception {
		if (grupoEnCurso == null) {
			Exception e = new Exception("No hay grupo");
			throw e;
		}
		return consultarDisponibilidad(grupoEnCurso.getReserva().getFechaI(), grupoEnCurso.getReserva().getFechaF(), tipo);
		
	}
		
	public String formatoFecha(Date fecha) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	    String fechaString = sdf.format(fecha);
		return fechaString;
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
			añadirUsuario("root", "Cookie", "Lobby", 1);
			añadirUsuario("E1", "Empleado", "Lobby", 1);
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
	
	public Integer[] ocupacionHoy() {
		HashMap<Integer, Integer> mapa = ocupados.get(hoy);
		if (mapa == null) {
			return new Integer[0];	
		}
		else {
			Set<Integer> setInt = mapa.keySet();
			Object[] resultado = (Object[]) setInt.toArray();
			Arrays.sort(resultado);
			Integer[] integerArray = new Integer[resultado.length];
	        for (int i = 0; i < resultado.length; i++) {
	            integerArray[i] = (Integer)resultado[i];
	        }
			return integerArray;
		}
		//Integer[] r = {104, 105, 202, 203, 206, 308};
		//return r;
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
}
