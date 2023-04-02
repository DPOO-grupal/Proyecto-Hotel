package vista;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import controlador.Hotel;
import modelo.Admin;
import modelo.Empleado;
import modelo.Tarifa;
import modelo.TipoHabitacion;
import modelo.Usuario;

public class Aplicacion {
	private Hotel hotel;
	private boolean continuar;

	public void ejecutarAplicacion() {
		System.out.println(" ---------- BIENVENIDO ----------");
		
		hotel = new Hotel();
		setDay();
		
		hotel.cargarInformacion();

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
						Empleado empleado = (Empleado) usuarioActual;
						menuEmpleado(empleado);
					} else if (usuarioActual.getClass() == Admin.class) {
						Admin admin = (Admin) usuarioActual;
						menuAdmin(admin);
					}
				}

				continuar = true;
				break;

			case 2:
				hotel.guardarInformacion();
				System.out.print("Taluego");
				continuar = false;
				break;
			default:
				input("Debe seleccionar una de las opciones del menú");
				break;
			}

		}
	}
	public void setDay() {
		
		hotel.setHoy(getDate("Intrese el dia de hoy "));
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

			intentos++;

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
			System.out.println("Sesion cerrada.....");
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
		System.out.println("2. Crear Tarifas");
		System.out.println("3. Crear servicio hotel");
		System.out.println("4. Crear habitacion");
		System.out.println("5. Crear producto menu");
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
		
		if (!tarifasCompletas());{
			System.out.println("LLENAR TARIFAS, OPCION 2");
		}	
		
		switch (opcionSeleccionada) {
		case 0:
			admin.cerrarSesion();
			continuar = false;
			System.out.println("Sesion cerrada.....");
			break;
		case 1:
			String login = input("\nIngrese login del nuevo usuario");

			String password = input("Ingrese password del nuevo usuario");

			int tipo = num("Ingrese tipo de Usuario:\n1. Admin\n2. Empleado\nSelecione una opción");

			admin.añadirUsuario(login, password, tipo);
			;

			break;
		case 2:
			CrearTarifa(admin);
			break;
		case 3:
			crearServicioHotel(admin);
			break;
		case 4:
			crearHabitacion(admin);
			break;
		case 5:
			crearProductoMenu(admin);
			break;
		default:
			break;
		}

	}
	
	public void CrearTarifa(Admin admin) {
		
		boolean verFaltantes = true;
		boolean añadir;
		
		do {
			if (verFaltantes) {
				ArrayList<Tarifa> faltantes = hotel.checkTarifas();
				if (faltantes.size() > 0) {
					System.out.println("Faltan la siguientes Tarifas para los tipos de Habitación");
					for (Tarifa tarifa : faltantes) {
						System.out.println(tarifa.getFaltantes().toString() + " " + formatoFecha(tarifa.getFecha()));
					}		
				}
			}
			
			Date fechaI = getDate("ingrese fecha inicial de la tarifa");
			Date fechaF = getDate("ingrese fecha fiinal de la tarifa");
			TipoHabitacion tipo = getTipoHabitacion("Ingrse el tipo de habitahocion");
			double precio = getDouble("Ingrese el precio de la habitacion");
			ArrayList<Tarifa> faltantesRango = admin.crearTarifa(fechaI, fechaF, tipo, precio);
			
			if (faltantesRango.size() > 0) {
				System.out.println(" En ese rango faltan la siguientes Tarifas para los tipos de Habitación");
				for (Tarifa tarifa : faltantesRango) {
					System.out.println(tarifa.getFaltantes().toString() + " " + formatoFecha(tarifa.getFecha()));
				}		
			}
		
		añadir = getboolean("¿Desea añadir otra tarifa?");
		verFaltantes = getboolean("¿Desea ver todas las tarifas Faltantes?");
		
		}while(añadir);
		
		
	}
	
	public boolean tarifasCompletas() {
		ArrayList<Tarifa> faltantes = hotel.checkTarifas();
		boolean completo = true;
		if (faltantes.size()>0) {
			completo = false;
		}
		
		return completo;
	}
	
	public String formatoFecha(Date fecha) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	    String fechaString = sdf.format(fecha);
		return fechaString;
	}
	
	public void crearServicioHotel(Admin admin) {
		System.out.println("Creando servicio hotel...");
		boolean centinela = true;
		while (centinela) {
			String nombre = input("Nombre servicio");
			double precio = getDouble("Precio");
			admin.crearServicioHotel(nombre, precio);
			centinela = getboolean("Desea añadir otra servicio?(true/false)");
		}
	}
	
	public void añadirServicioHabitacion(Admin admin, int id) {
		System.out.println("Creando servicio habitacion...");
		boolean centinela = true;
		while (centinela) {
			String nombre = input("Nombre servicio habitacion");
			double precio = getDouble("Precio");
			admin.añadirServicioHabitacion(id, nombre, precio);
			centinela = getboolean("Desea añadir otra servicio?(true/false)");
		}
	}
	
	public void listaHabitaciones(Admin admin) {
		
	}
	
	private void crearHabitacion(Admin admin) {
		System.out.println("Creando habitacion...");
		boolean centinela1=true;
		while (centinela1) {
			TipoHabitacion tipoHabitacion = getTipoHabitacion("Tipo habitacion");
			int id = num("Numero habitacion");
			admin.crearHabitacion(tipoHabitacion, id);
			
			System.out.println("Añadiendo camas...");
			boolean centinela2=true;
			while (centinela2) {
				int capacidadCama = num("Capacidad cama");
				boolean apto = getboolean("Apto para niños(true/false)");
				admin.crearCama(id, capacidadCama, apto);
				centinela2 = getboolean("Desea añadir otra cama?(true/false)");
			}
			
			System.out.println("Añadir servicios");
			boolean centinela3=true;
			while (centinela3) {
				String nombre = input("Nombre servicio habitacion");
				double precio = getDouble("Precio");
				admin.añadirServicioHabitacion(id, nombre, precio);
				centinela3 = getboolean("Desea añadir otra servicio?(true/false)");
			
			String caracteristicas = input("Caracteristicas");
			admin.setCaracteristicasHabitacion(caracteristicas, id);
			centinela1 = getboolean("Desea añadir otra habitacion?(true/false)");

		}
		}
	}

	private void crearProductoMenu(Admin admin) {
		System.out.println("Creando producto menu\n");
		boolean centinela = true;
		while (centinela) {
			//Date date = getDate("Hora admitida");
			boolean llevable = getboolean("Es llevable");
			String nombre = input("Nombre producto menu");
			double precio = getDouble("Precio");
			admin.crearProductoMenu(null, llevable, nombre, precio);
		}
	}

	public String input(String mensaje){
		try
		{

			System.out.print(mensaje + ": ");
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			return reader.readLine();
		} catch (IOException e) {
			System.out.println("Error leyendo de la consola");
			e.printStackTrace();
		}
		return null;
	}

	public int num(String mensaje) {
		int num = -1;
		boolean right;
		do {
			try {
				num = Integer.parseInt(input(mensaje));
				right = false;
			} catch (NumberFormatException e) {
				System.out.println("Debe ingresar un números.");
				right = true;
			} 
		}while (right);
			
		return num;

	}
	
	public double getDouble(String mensaje) {
		double num = -1;
		boolean right;
		do {
			try {
				num = Double.parseDouble(input(mensaje));
				right = false;
			} catch (NumberFormatException e) {
				System.out.println("Debe ingresar un números.");
				right = true;
			} 
		}while (right);
			
		return num;

	}
	
	public Date getDate(String mensaje) {
		boolean right;
		Date dia = null;
		do{
			String diaString = input(mensaje + " (dd/MM/yyyy)");
			DateFormat DFormat = new SimpleDateFormat("dd/MM/yyyy");

			try {
				dia = DFormat.parse(diaString);
				right = false;
			} catch (ParseException e) {
				System.out.println("Formato Incorrecto");
				right = true;
			}
			
		} while (right);
		
		return dia;
	}
	
	
	public TipoHabitacion getTipoHabitacion(String mensaje) {
		TipoHabitacion tipo = null;
		
		boolean right;
		do {
			right = false;
			
			System.out.println(mensaje);
			System.out.println("Ingrese Una de las opciones");
			System.out.println("1. Estandar");
			System.out.println("2. Suite");
			System.out.println("3. Suite Double");
			
			int opcion = num("Seleccione una de las opciones");
			
			switch (opcion) {
			case 1:
				tipo = TipoHabitacion.ESTANDAR;
				break;
			case 2:
				tipo = TipoHabitacion.SUITE;
				break;
			case 3:
				tipo = TipoHabitacion.SUITEDOUBLE;
				break;
	
			default:
				input("Debe selecionar una de las opciones");
				right = true;
				break;
			}
		} while(right);

		
		return tipo;
	}
	public boolean getboolean(String mensaje) {
		boolean valor = false;
		boolean right;
		do {
			System.out.println(mensaje);
			System.out.println("Ingrese Una de las opciones");
			System.out.println("1. Verdadero");
			System.out.println("2. Falso");
			
			int opcion = num("Seleccione una de las opciones");
			
			switch (opcion) {
			case 1:
				valor = true;
				right = false;
				break;
			case 2:
				valor = false;
				right = false;
				break;
			default:
				input("Debe selecionar una de las opciones");
				right = true;
				break;
			}
		} while(right);

		
		return valor;
	}
	

	public static void main(String[] args) {
		Aplicacion app = new Aplicacion();
		app.ejecutarAplicacion();

	}

}
