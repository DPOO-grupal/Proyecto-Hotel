package test;



import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.Date;

import javax.swing.plaf.basic.BasicTreeUI.TreeCancelEditingAction;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controlador.Hotel;
import modelo.Cama;
import modelo.Grupo;
import modelo.Habitacion;
import modelo.Huesped;
import modelo.TipoHabitacion;
public class ReservaTest {
	private Hotel hotel;
	private Grupo grupo;
	
	@BeforeEach
	public void estableserHotel(){
		hotel = new Hotel();
		hotel.setHoy(new Date());
		hotel.cargarInformacion();
		hotel.borrarDatos();
		
		Date fechaI = new Date();
		Date fechaF = new Date();
		
		try {
			hotel.crearReserva(fechaI, fechaF);

			grupo = hotel.getGrupoEnCurso();
			
			assertNotEquals(null, grupo, "Grupo no nulo");
			
		} catch (Exception e) {
			assertNotEquals("Debe ingresar las fechas", e.getMessage(), "Fechas Invalidad");
			
		}
	}
	
	@Test 
	public void IniciarReservaInvalida() {
		Date fechaI = null;
		Date fechaF = null;
		
		try {
			hotel.crearReserva(fechaI, fechaF);

			grupo = hotel.getGrupoEnCurso();
			
			assertEquals(null, grupo, "Grupo no nulo");
			
		} catch (Exception e) {
			assertEquals("Debe ingresar las fechas", e.getMessage(), "Fechas Invalidad");
			
		}
	}
	
	@Test
	public void añadirHuespedValido() {
		
		try {
			hotel.añadirHuesped("1025524919", "Juan", "ejemplo@ejemplo.com", "314298017", 18);
		} catch (Exception e) {
			assertNotEquals("El huesped ya existe", e.getMessage(), "Huespes repetido");
			assertNotEquals("Debe establecer una fecha", e.getMessage(), "No grupo");
		}
		
		ArrayList<Huesped> huespedes = hotel.getHuespedesGrupoEnCurso();
		assertEquals(huespedes.size(),1, "Numero de huespeds");
		
	}
	@Test
	public void añadirHuespedInvalido() {
		
		try {
			hotel.añadirHuesped("1025524919", "Juan", "ejemplo@ejemplo.com", "314298017", 18);
		} catch (Exception e) {
			fail("Huesped repetido", e);
		}
		
		try {
			hotel.añadirHuesped("1025524919", "Juan", "ejemplo@ejemplo.com", "314298017", 18);
		} catch (Exception e) {
			assertEquals("El huesped ya existe", e.getMessage(), "Huespes repetido");
		}
		
	}
	@Test
	public void añadirHuespedInvalidoGrupoNulo() {
		hotel.setGrupoEnCurso(null);
		try {
			hotel.añadirHuesped("1025524919", "Juan", "ejemplo@ejemplo.com", "314298017", 18);
		} catch (Exception e) {
			assertEquals("Debe establecer una fecha", e.getMessage(), "Grupo no creado");
		}
		
	}
	
	@Test
	public void añadirHabitacionInvalida() {
		
		try {
			hotel.añadirHabitacionReserva(1);
		} catch (Exception e) {
			assertEquals("Cannot invoke \"modelo.Habitacion.getTipoHabitacion()\" because \"habitacion\" is null", e.getMessage(), "Habitacion Invalia");

		}
		
		
	}
	@Test
	public void añadirHabitacionValidaConHuesped() {
		
		Date fechaI = new Date();
		Date fechaF = new Date();
		
		boolean[] dias = new  boolean[7];
		
		for (int i = 0; i < dias.length; i++) {
			dias[i] = true;
			
		}
		
		hotel.crearTarifasRango(fechaI, fechaF, TipoHabitacion.ESTANDAR, 0, dias);
		
		
		hotel.crearHabitacion(TipoHabitacion.ESTANDAR, 1);
		Habitacion habitacion = hotel.getHabitacion(1);
		ArrayList<Cama> camas = new ArrayList<Cama>();
		
		Cama cama = hotel.crearCama(2, false);

		camas.add(cama);
		
		habitacion.setListaCamas(camas);
		try {
			hotel.añadirHuesped("1025524919", "Juan", "ejemplo@ejemplo.com", "314298017", 18);
		} catch (Exception e1) {
			fail("No añadio huesped", e1);
		}
		
		try {
			hotel.añadirHabitacionReserva(1);
			int tamaño = grupo.getListaHabitaciones().size();
			assertEquals(tamaño, 1);
		} catch (Exception e) {
			fail(e.getMessage(), e);	
		}
		
	}
	
	@Test
	public void añadirHabitacionValidaSinHuesped() {
		hotel.crearHabitacion(TipoHabitacion.ESTANDAR, 1);
		Habitacion habitacion = hotel.getHabitacion(1);
		ArrayList<Cama> camas = new ArrayList<Cama>();
		Cama cama = hotel.crearCama(2, false);
		camas.add(cama);
		
		habitacion.setListaCamas(camas);
		
		
		try {
			hotel.añadirHabitacionReserva(1);
		} catch (Exception e) {
			assertNotEquals("NullPointerException", e.getMessage(), "Habitacion Invalia");
			
		}
		
	}
	@Test
	public void añadirHabitacionInvalidaGrupoNulo() {
		hotel.setGrupoEnCurso(null);
		hotel.crearHabitacion(TipoHabitacion.ESTANDAR, 1);

		
		try {
			hotel.añadirHabitacionReserva(1);
		} catch (Exception e) {
			assertEquals("Cannot invoke \"modelo.Grupo.getReserva()\" because \"this.grupoEnCurso\" is null", e.getMessage(), "Habitacion Invalia");
			
		}
		
	}
	
	@Test
	public void completarReservaInvalidoGrupoNulo() {

		hotel.setGrupoEnCurso(null);
		
		try {
			hotel.completarReserva();
		} catch (Exception e) {
			assertEquals("Debe establecer una fecha", e.getMessage(), "Grupo no creado");
			
		}
		
	}
	
	
	@Test
	public void completarReservaInvalidoNoLider() {
		
		try {
			hotel.completarReserva();
		} catch (Exception e) {
			assertEquals("Debe añadir almenos un huesped", e.getMessage(), "No completada");
			
		}
		
	}
	
	@Test
	public void completarReservaInvalidoHabitacionesInsificientes() {
		try {
			hotel.añadirHuesped("1025524919", "Juan", "ejemplo@ejemplo.com", "314298017", 18);
		} catch (Exception e1) {
			fail("No añadio huesped", e1);
		}
		
		grupo = hotel.getGrupoEnCurso();

		boolean aceptable = (grupo.getvRelativo() <= grupo.getCapacidadCamas());
		
		try {
			hotel.completarReserva();
		} catch (Exception e) {
			if (!aceptable) {
				assertEquals("Aun No hay suficientes habitaciones", e.getMessage(), "No completado");
				
			}else {
				fail("Capacidad Suficioente", e);
			}
			
		}
		
	}
	
	@Test
	public void completarReservaValido() {
		Date fechaI = new Date();
		Date fechaF = new Date();
		
		boolean[] dias = new  boolean[7];
		
		for (int i = 0; i < dias.length; i++) {
			dias[i] = true;
			
		}
		
		hotel.crearTarifasRango(fechaI, fechaF, TipoHabitacion.ESTANDAR, 0, dias);
		
		hotel.crearHabitacion(TipoHabitacion.ESTANDAR, 1);
		Habitacion habitacion = hotel.getHabitacion(1);
		ArrayList<Cama> camas = new ArrayList<Cama>();
		Cama cama = hotel.crearCama(2, false);
		camas.add(cama);
		
		habitacion.setListaCamas(camas);
		
		
		try {
			hotel.añadirHuesped("1025524919", "Juan", "ejemplo@ejemplo.com", "314298017", 18);
		} catch (Exception e1) {
			fail("No añadio huesped", e1);
		}
		
		try {
			hotel.añadirHabitacionReserva(1);
		} catch (Exception e1) {
			fail("Habitacion Invalia", e1);

		}

		
		grupo = hotel.getGrupoEnCurso();

		boolean aceptable = (grupo.getvRelativo() <= grupo.getCapacidadCamas());
		assertTrue("Capacidad Correcta", aceptable);

		try {
			hotel.completarReserva();
		} catch (Exception e) {
			fail(e.getMessage(), e);
			
		}
		
	}
	@Test
	public void cancelarReservaInvalidoGrupo() {
		int idGrupo = 0;
				
		try {
			hotel.cancelarReserva(idGrupo);
		} catch (Exception e) {
			assertEquals("Numero de reserva invalido", e.getMessage());
		}
		
		
		
	}
	
	@Test 
	public void cancelarReservaInvalidoFecha() {
		int idGrupo = 0;
		
		try {
		
		Date fechaI = new Date();
		Date fechaF = new Date();
		
		boolean[] dias = new  boolean[7];
		
		for (int i = 0; i < dias.length; i++) {
			dias[i] = true;
			
		}
		
		hotel.crearTarifasRango(fechaI, fechaF, TipoHabitacion.ESTANDAR, 0, dias);
		
		hotel.crearHabitacion(TipoHabitacion.ESTANDAR, 1);
		Habitacion habitacion = hotel.getHabitacion(1);
		ArrayList<Cama> camas = new ArrayList<Cama>();
		Cama cama = hotel.crearCama(2, false);
		camas.add(cama);
		
		habitacion.setListaCamas(camas);
		
		hotel.añadirHuesped("1025524919", "Juan", "ejemplo@ejemplo.com", "314298017", 18);
	

		hotel.añadirHabitacionReserva(1);
	

		grupo = hotel.getGrupoEnCurso();
		idGrupo = grupo.getId();
		boolean aceptable = (grupo.getvRelativo() <= grupo.getCapacidadCamas());
		assertTrue("Capacidad Correcta", aceptable);
		

		hotel.completarReserva();

	
		
		
		} catch (Exception e) {
			fail("Crear Reserva", e);
			
		}
				
		try {
			hotel.cancelarReserva(idGrupo);
		} catch (Exception e) {
			assertEquals("No es posible cancelar la reserva, pues no está entre los tiempos de calcelación", e.getMessage());
		}
		
		
		
	}
	
	
	@Test 
	public void cancelarReservaValido() {
		int idGrupo = 0;
		
		try {
		
		Date fechaI = hotel.pasarMes(new Date(), 1);
		Date fechaF = hotel.pasarMes(new Date(), 1);
		
		hotel.crearReserva(fechaI, fechaF);
		
		boolean[] dias = new  boolean[7];
		
		for (int i = 0; i < dias.length; i++) {
			dias[i] = true;
			
		}
		
		hotel.crearTarifasRango(fechaI, fechaF, TipoHabitacion.ESTANDAR, 1, dias);
		
		hotel.crearHabitacion(TipoHabitacion.ESTANDAR, 1);
		Habitacion habitacion = hotel.getHabitacion(1);
		ArrayList<Cama> camas = new ArrayList<Cama>();
		Cama cama = hotel.crearCama(2, false);
		camas.add(cama);
		
		habitacion.setListaCamas(camas);
		
		hotel.añadirHuesped("1025524919", "Juan", "ejemplo@ejemplo.com", "314298017", 18);
	

		hotel.añadirHabitacionReserva(1);
	

		grupo = hotel.getGrupoEnCurso();
		idGrupo = grupo.getId();
		boolean aceptable = (grupo.getvRelativo() <= grupo.getCapacidadCamas());
		

		hotel.completarReserva();
		
		if(!aceptable) {
			fail("Capacidad incorrecta");
		}
		
		
		} catch (Exception e) {
			fail(e.getMessage(), e);
			
		}
				
		try {
			hotel.cancelarReserva(idGrupo);
		} catch (Exception e) {
			fail("Falla cancelación");
		}
		
		try {
			hotel.getGrupo(idGrupo);
		} catch (Exception e) {
			assertEquals("El Numero ingresado no esta registrado", e.getMessage());
		}

		
		
		
	}
	
	
}
