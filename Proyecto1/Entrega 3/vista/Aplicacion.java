package vista;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import controlador.Hotel;
import modelo.Admin;
import modelo.Empleado;
import modelo.Usuario;

public class Aplicacion {
	private Hotel hotel;
	private boolean continuar;
	private Usuario usuarioActual;
	
	
	
	public void ejecutarAplicacion() {
		hotel = new Hotel();
		
		continuar = true;
		
		while (continuar) {
			
			if (usuarioActual == null) {
				iniciarSeccion();
			} else if (usuarioActual.getClass() == Empleado.class) {
				menuEmpleado();
			} else if (usuarioActual.getClass() == Admin.class) {
				menuAdmin();
			}			
		
		}
	}
	
	public void iniciarSeccion() {
		
	}
	
	public void menuEmpleado() {
		
		
		System.out.println("1. ");
		System.out.println("2. ");
		System.out.println("3. ");
		System.out.println("4. ");
		System.out.println("5. ");
		System.out.println("6. ");
		System.out.println("7. ");
		System.out.println("8. ");
		
		int opcionSeleccionada;
		
		try {
			opcionSeleccionada = Integer.parseInt(input("Seleccione una opción"));
			ejecutarOpcionEmpleado(opcionSeleccionada);
			input("Presione 'Enter' para continuar");
		}
		catch (NumberFormatException e)
		{
			System.out.println("Debe seleccionar uno de los números de las opciones.");
		}
	}
	
	private void ejecutarOpcionEmpleado(int opcionSeleccionada) {
		// TODO Ejecutar las opciones del empleado
		
	}

	public void menuAdmin() {
		// TODO Listar las opciones del Admin
	}
	
	
	public String input(String mensaje){
		try
		{
			System.out.print(mensaje + ": ");
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			return reader.readLine();
		}
		catch (IOException e)
		{
			System.out.println("Error leyendo de la consola");
			e.printStackTrace();
		}
		return null;
	}
	
	
	public static void main(String[] args) {
		Aplicacion app = new Aplicacion();
		app.ejecutarAplicacion();

	}

}
