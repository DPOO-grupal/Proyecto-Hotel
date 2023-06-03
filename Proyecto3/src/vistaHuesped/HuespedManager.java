package vistaHuesped;

import javax.swing.UIManager;

import com.formdev.flatlaf.FlatLightLaf;

import controlador.WindowManager;
import modelo.Usuario;
import vistaAdmin.AutenticacionFrame;

public class HuespedManager extends WindowManager{
	
	private HuespedAutenticacion huespedAutenticacion;
	private HuespedaCrearReservasFrame huespedaCrearReservas;
	
	public HuespedManager() {
		super();

	}
	
	
	public void iniciarAutenticacion() {
		huespedAutenticacion = new HuespedAutenticacion(this);
		mostraVentana(huespedAutenticacion);
		
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
