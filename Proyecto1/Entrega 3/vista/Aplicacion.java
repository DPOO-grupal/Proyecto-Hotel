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
	
	
	
	public void ejecutarAplicacion() {
		hotel = new Hotel();
		Usuario usuarioActual = null;
		continuar = true;
		
		while (continuar) {
			System.out.println("1. Iniciar Sesion");
			System.out.println("2. Salir");
			
			int opcionSeleccionada;
			
			opcionSeleccionada = num("Seleccione una opción");
			
			switch (opcionSeleccionada) {
				case 1:
					while (continuar) {
						if (usuarioActual == null) {
							usuarioActual = iniciarSeccion(usuarioActual);
						} else if (usuarioActual.getClass() == Empleado.class) {
							Empleado empleado = (Empleado)usuarioActual;
							menuEmpleado(empleado);
						} else if (usuarioActual.getClass() == Admin.class) {
							Admin admin = (Admin)usuarioActual;
							menuAdmin(admin);
						}
					}
					
					continuar = true;
					break;
					
				case 2:
					hotel.guardarInformacion(hotel);
					continuar = false;
					break;
				default:
					input("Debe seleccionar una de las opciones del menú");
					break;
			}
						
		
		}
	}
	
	
	public Usuario iniciarSeccion(Usuario usuarioActual) {
		boolean autent = false;
		int intentos = 0;
		
		
		System.out.println("---------- INICIO DE SESION ----------");
		String login = input("Ingrese login");
		do {
			
			String password = input("Ingrese password");
			autent = hotel.autenticar(login, password);
			
			if (autent) {
					usuarioActual = hotel.getUsuarioActual();	
			} else {
				System.out.println("Contraseña Incorrecta");
			}
			
			intentos ++;
			
		} while (!autent && intentos < 3); 
		
		if (intentos < 3) {
			
			usuarioActual = hotel.getUsuarioActual();
		} else {
			input("Acceso denegado, ENTER para continuar");
		}
		return usuarioActual;
		 
		
	}
	
	public void menuEmpleado(Empleado empleado) {
		
		System.out.println("0. Cerrar Sesion");
		System.out.println("1. ");
		System.out.println("2. ");
		System.out.println("3. ");
		System.out.println("4. ");
		System.out.println("5. ");
		System.out.println("6. ");
		System.out.println("7. ");
		System.out.println("8. ");
		
		int opcionSeleccionada;
		
		opcionSeleccionada = num("Seleccione una opción");
		ejecutarOpcionEmpleado(empleado, opcionSeleccionada);
		input("Presione 'Enter' para continuar");

	}
	
	private void ejecutarOpcionEmpleado(Empleado empleado, int opcionSeleccionada) {
		// TODO Ejecutar las opciones del empleado
		switch (opcionSeleccionada) {
		case 0:
			empleado.cerrarSesion();
			continuar = false;
			input("Sesion cerrada.....");
			break;
		case 1:
			
			break;
		case 2:
			
			break;

		default:
			break;
		}
	}

	public void menuAdmin(Admin admin) {
		System.out.println("0. Cerrar Sesion");
		System.out.println("1. Añadir Usuario");
		System.out.println("2. ");
		System.out.println("3. ");
		System.out.println("4. ");
		System.out.println("5. ");
		System.out.println("6. ");
		System.out.println("7. ");
		System.out.println("8. ");
		
		int opcionSeleccionada;
		
		opcionSeleccionada = num("Seleccione una opción");
		ejecutarOpcionAdmin(admin, opcionSeleccionada);
		input("Presione 'Enter' para continuar");

	}
	
	
	private void ejecutarOpcionAdmin(Admin admin, int opcionSeleccionada) {
		// TODO Ejecutar las opciones del Admin
				switch (opcionSeleccionada) {
				case 0:
					admin.cerrarSesion();
					continuar = false;
					input("Sesion cerrada.....");
					break;
				case 1:
					String login = input("\nIngrese login del nuevo usuario");
						
					String password = input("Ingrese password del nuevo usuario");
					
					int tipo = num("Ingrese tipo de Usuario:\n1. Admin\n2. Empleado");
					
					admin.añadirUsuario(login, password, tipo);;
					
					break;
				case 2:
					
					break;

				default:
					break;
				}
		
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
	
	public int num(String mensaje) {
		int num = -1;
		try {
			num = Integer.parseInt(input(mensaje));
		}
		catch (NumberFormatException e)
		{
			System.out.println("Debe seleccionar uno de los números de las opciones.");
		}
		return num;
		
	}
	
	
	public static void main(String[] args) {
		Aplicacion app = new Aplicacion();
		app.ejecutarAplicacion();

	}

}
