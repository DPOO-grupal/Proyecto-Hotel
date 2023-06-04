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
	public void inciarSecion() {
		menu = new HuespedReservasFrame(this);
		mostraVentana(menu);

 
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
}
