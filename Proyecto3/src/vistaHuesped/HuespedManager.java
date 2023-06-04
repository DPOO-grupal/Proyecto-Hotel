package vistaHuesped;

import java.util.ArrayList;

import javax.swing.UIManager;

import com.formdev.flatlaf.FlatLightLaf;

import controlador.WindowManager;
import modelo.Admin;
import modelo.Empleado;
import modelo.Huesped;
import modelo.Usuario;
import vistaAdmin.AdminMenuPrincipal;
import vistaAdmin.AutenticacionFrame;
import vistaEmpleado.EmpleadoMenuPrincipal;

public class HuespedManager extends WindowManager{
		
	public HuespedManager() {
		super();

	}
	
	
	public void iniciarAutenticacion() {
		autenticacionFrame = new HuespedAutenticacion(this);
		mostraVentana(autenticacionFrame);

		
	}
	@Override
	public void inciarSecion() throws Exception {

		if (usuarioActual.getClass() == Huesped.class) {
			Huesped huesped = (Huesped) usuarioActual;
			if(huesped.getEmail() == null) {
				throw new Exception("Faltan datos de Huesped");
			}
			
			menu = new HuespedReservasFrame(this);
			mostraVentana(menu);

		} else {
			throw new Exception("Usuario invalido");
		}
	}
	
	public ArrayList<Integer> idHuespedReservas() {
		return ((Huesped) usuarioActual).idHuespedReservas();
	}
	
	public void añadirReservaAHuesped(int idGrupo) {
		((Huesped) usuarioActual).añadirReservaAHuesped(idGrupo);;
	}
	
	
	
	@Override
	public void volverReserva() {
		mostraVentana(menu);
	}
	
	
	public static void main(String[] args) {
		FlatLightLaf.install();
		UIManager.put( "Button.arc", 500 );
		UIManager.put( "Component.arrowType", "chevron" );
		UIManager.put( "TextComponent.arc", 5 );
		
		HuespedManager huespedManager = new HuespedManager();
		huespedManager.iniciarAutenticacion();
		
		
	}
	
	
	public void añadirUsuarioHuesped(String login, String password, String documento, String email, String telefono, int edad) throws Exception {
		System.out.println("HuespedManager.añadirUsuarioHuesped()");
		if (usuarioActual== null) {
			System.out.println("Usuario nulo");
			usuarioActual = new Admin(null, null, null);
			try {
				((Admin)usuarioActual).añadirUsuarioHuesped(login, password, documento, documento, email, telefono, edad);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				throw e;
			}
		}else {
			
			System.out.println("Completar");
			System.out.println(usuarioActual.getClass());
			System.out.println(((Huesped)usuarioActual).getArea());
			
			completarHuesped(documento, email, telefono, edad);
		}

			
	}

	public void completarHuesped(String documento, String email, String telefono, int edad) {
		((Huesped) usuarioActual).setDocumento(documento);
		((Huesped) usuarioActual).setEmail(email);
		((Huesped) usuarioActual).setTelefono(telefono);
		((Huesped) usuarioActual).setEdad(edad);
	}
	
	public void reservaSoloConLider() {
		Huesped huesped = (Huesped) usuarioActual;
		huesped.reservaSoloConLider();
	}
	

	public void añadirLider() {
		System.out.println("HuespedManager.añadirLider()");
		Huesped huesped = (Huesped) usuarioActual;
		try {
			llenarHuespeds(huesped.getDocumento(), huesped.getNombre(), huesped.getEmail(), huesped.getTelefono(), huesped.getEdad());
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		
	}
	
	
}
