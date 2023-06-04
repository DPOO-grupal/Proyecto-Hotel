package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Objects;

public class Huesped extends Usuario implements Serializable{


	private String documento;
	private String nombre;
	private String email;
	private String telefono;
	private int edad;
	
	public Huesped(String documento, String nombre, String email, String telefono, int edad) {
		super(null, null, "Huesped");
		this.documento = documento;
		this.nombre = nombre;
		this.email = email;
		this.telefono = telefono;
		this.edad = edad;
	}
	
	public Huesped(String login, String password, String area) {
		super(login, password, area);

	}
	
	public Huesped(String login, String password, String documento, String nombre, String email, String telefono, int edad) {
		super(login, password, "Huesped");
		this.documento = documento;
		this.nombre = nombre;
		this.email = email;
		this.telefono = telefono;
		this.edad = edad;
	}

	public String getDocumento() {
		return documento;
	}

	public String getNombre() {
		return nombre;
	}

	public String getEmail() {
		return email;
	}

	public String getTelefono() {
		return telefono;
	}


	public int getEdad() {
		return edad;
	}
	
	// INICIO RESERVAS ------------------------------------------
	public ArrayList<Integer> idHuespedReservas() {
		return hotel.idHuespedReservas();
	}
	
	public void añadirReservaAHuesped(int idGrupo) {
		hotel.añadirReservaAHuesped(idGrupo);
	}
	

	public HashMap<Integer,Grupo> mostrarReservas(Date fechaI, Date fechaF) throws Exception {
		return hotel.mostrarReservas(fechaI, fechaF);
	}
	
	public Grupo getGrupo(int id) throws Exception {
		return hotel.getGrupo(id);
	}
	
	public ArrayList<Integer> getArrayHabitaciones() {
		return hotel.getArrayHabitaciones();
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
	
	public double getPrecioHabitacionReserva(Habitacion habitacion) throws Exception {
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
	
	
	public String getCaracteristicasHabitacion(Integer ID) {
		return hotel.getCaracteristicasHabitacion(ID);
	}
	
	// FIN RESERVAS ---------------------------------------------

	
	@Override
	public int hashCode() {
		return Objects.hash(documento, nombre);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Huesped other = (Huesped) obj;
		return Objects.equals(documento, other.documento) && Objects.equals(nombre, other.nombre);
	}
}
