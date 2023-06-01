package modelo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;

public class Empleado extends Usuario  {
	
	public Empleado (String login, String password, String area) {
		super(login, password, area);
	}
	
// INICIO TARIFAS ---------------------------------------------

	public Collection<Tarifa> consultarTarifas(Date fechaI, Date fechaF) {
		return hotel.consultarTarifas(fechaI, fechaF);
	}
	
// FIN TARIFAS ---------------------------------------------

// INICIO RESERVAS ------------------------------------------

	public HashMap<Integer,Grupo> mostrarReservas(Date fechaI, Date fechaF) throws Exception {
		return hotel.mostrarReservas(fechaI, fechaF);
	}
	
	public Grupo getGrupo(int id) {
		return hotel.getGrupo(id);
	}
	
	public void cancelarReserva(int id) throws Exception {
		hotel.cancelarReserva(id);
		
	}	
	
	public void crearReserva(Date fechaI, Date fechaF) throws Exception {
		hotel.crearReserva(fechaI, fechaF);
	}
	
	public void cambiarFechaReserva(Date fechaI, Date fechaF) throws Exception {
		hotel.cambiarFechaReserva(fechaI, fechaF);
				
	}
	
	
	public void añadirHuesped(String documento, String nombre, String email, String telefono, int edad) throws Exception {
		hotel.añadirHuesped(documento,nombre, email,telefono,edad);
	}
	public void añadirHabitacion(int idHabi) throws Exception {
		hotel.añadirHabitacionReserva(idHabi);
	}

	public void completarReserva() throws Exception {
		hotel.completarReserva();
	}
	
	public boolean hayReserva() {
		Grupo grupo = hotel.getGrupoEnCurso();
		if (grupo==null) {
			return false;
		} else {
			return true;
		}
	}
	
	public ArrayList<Habitacion> DiponiblesParaGrupoEnCurso(TipoHabitacion tipo) throws Exception {
		return hotel.DiponiblesParaGrupoEnCurso(tipo);
		
	}
	
	public double getPrecioHabitacionReserva(Habitacion habitacion) {
		return hotel.getPrecioHabitacionReserva(habitacion);
	}
	
	public int getIdGrupo() {
		return hotel.getGrupoEnCurso().getId();
	}
	
	public ArrayList<Integer> getListaHabitacionesGrupo() {
		return hotel.getListaHabitacionesGrupo();
	}	
	public ArrayList<Huesped> getHuespedesGrupoEnCurso() {
		return hotel.getHuespedesGrupoEnCurso();
	}
	
	public Grupo getGrupoEnCurso() {
		return hotel.getGrupoEnCurso();
	}
	public void forzarCancelarReserva() {
		hotel.setGrupoEnCurso(null);
	}
	
	public String formatoFecha(Date date) {
		return hotel.formatoFecha(date);
		 
	}
	
// FIN RESERVAS ---------------------------------------------

//INICIO SERVICIOS HOTEL -----------------------------------------------------
	
	public HashMap<Integer, Servicio> getServiciosHotel(){
		return hotel.getServiciosHotel();
	}

	public Servicio añadirServicioHotelHabitacion(int idHabitacion, int idServicio, int cantidad, boolean pagarEnSitio) {
		return hotel.añadirServicioHotelHabitacion(idHabitacion, idServicio, cantidad, pagarEnSitio);
	}
	
	public Servicio añadirProductoMenuHabitacion(int idHabitacion, int idServicio, int cantidad, boolean pagarEnSitio) {
		return hotel.añadirProductoMenuHabitacion(idHabitacion, idServicio, cantidad, pagarEnSitio);
	}
	
	public ArrayList<String[]> getServiciosHabitacion(String ID){
		return hotel.getServiciosHabitacion(ID);
	}
	
//FIN SERVICIOS HOTEL -----------------------------------------------------------

//INICIO HABITACIONES -----------------------------------------------------------
	
	public HashMap<Integer, Habitacion> getHabitaciones() {
		return hotel.getHabitaciones();
	}
	
	public ArrayList<String[]> getServicios(String ID) {
		return hotel.getServiciosHabitacion(ID);
	}
	
	public ArrayList<String[]> getCamas(String ID) {
		return hotel.getCamasHabitacion(ID);
	}
	
//FIN HABITACIONES -----------------------------------------------------------
	
//INICIO PRODUCTO MENU -----------------------------------------------------------

	public HashMap<Integer, ProductoMenu> getMenu() {
		return hotel.getMenu();
	}
	
//FIN PRODUCTO MENU -----------------------------------------------------------

	public Date getHoy() {
		return hotel.getHoy();
	}
	
	private Date pasarAnno(Date start) {
        return hotel.pasarAnno(start);
    }

	public Grupo checkOut(int idGrupo) {
		return hotel.checkOut(idGrupo);
	}
	
	public boolean checkUsuario(String nombre) {
		return hotel.checkUsuario(nombre);
	}
	public void llenarOcupados(int ID) {
		hotel.llenarOcupados(ID);
	}
	
	public Integer[] ocupacionHoy() {
		return hotel.ocupacionHoy();
	}
	
	public Date getDia() {
		return hotel.getHoy();
	}
	
	public int contarOcupadasDia(Date dia) {
		//hotel.printOcupados();
		return hotel.contarOcupadasDia(dia);
	}
	
	public int getTotalHabitaciones() {
		return hotel.getTotalHabitaciones();
	}
	
	public Date pasarDia(Date dia) {
		return hotel.pasarDia(dia);
	}
	
	public Date pasarMes(Date dia, int i) {
		return hotel.pasarMes(dia, i);
	}

}
