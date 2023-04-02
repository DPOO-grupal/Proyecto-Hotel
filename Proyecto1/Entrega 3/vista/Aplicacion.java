package vista;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.SortedMap;
import java.util.HashMap;

import controlador.Hotel;
import modelo.Admin;
import modelo.Empleado;
import modelo.ProductoMenu;
import modelo.Servicio;
import modelo.Habitacion;
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
			input("Debe seleccionar una de las opciones del menú");
			break;
		}
	}
	

	public void menuAdmin(Admin admin) {
		
		System.out.println(" ---------- Menú ----------");

		
		if (!admin.tarifasCompletas());{
			System.out.println("LLENAR TARIFAS, OPCION 2");
		}	
		
		System.out.println("0. Cerrar Sesion");
		System.out.println("1. Añadir Usuario");
		System.out.println("2. Tarifas");
		System.out.println("3. Crear servicio hotel");
		System.out.println("4. Crear habitacion");
		System.out.println("5. Crear producto menu");
		System.out.println("6. Reservas");
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
			menuTarifasAdmin(admin);
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
		case 6:
			menuReservasAdmin(admin);
			break;
		default:
			input("Debe seleccionar una de las opciones del menú");
			break;
		}

	}

// INICIO TARIFAS ---------------------------------------------
	public void menuTarifasAdmin(Admin admin) {
		boolean continuarTarifa = true;
		do {
			
			System.out.println("\n-------- TARIFAS --------");
			System.out.println("1. Ver Tarifas faltantes");
			System.out.println("2. Consular Tarifa por rango de fechas");
			System.out.println("3. Establecer tarifa en un rango de fechas");
			System.out.println("4. Salir");


		
			int opcionSeleccionada;
		
			opcionSeleccionada= num("Seleccione una Opcion");
			
			switch (opcionSeleccionada) {
			case 1:
				mostrarTarifasFaltantes(admin); 
				input("Presione 'Enter' para continuar");

				break;
			case 2:
				consultarTarifas(admin);
				input("Presione 'Enter' para continuar");

				break;
			case 3:
				CrearTarifa(admin);
				input("Presione 'Enter' para continuar");

				break;
			case 4:
				continuarTarifa = false;
				break;

			default:
				input("Debe seleccionar una de las opciones del menú");
				break;
			}
		} while(continuarTarifa);
	}
	
	public void mostrarTarifasFaltantes(Admin admin) {
		ArrayList<Tarifa> faltantes = admin.checkTarifas();
		System.out.println(faltantes.size());
		if (faltantes.size() > 0) {
			System.out.println("Faltan la siguientes Tarifas para los tipos de Habitación");
			for (Tarifa tarifa : faltantes) {
				System.out.println(tarifa.getFaltantes().toString() + " " + formatoFecha(tarifa.getFecha()));
			}		
		} else {
			System.out.println("No faltan llenar tarifas");
		}
	}
	
	public void consultarTarifas(Admin admin) {
		Date fechaI = getDate("ingrese fecha inicial de la tarifa");
		Date fechaF = getDate("ingrese fecha final de la tarifa");
		
		mostarTarifasRango(admin, fechaI, fechaF);		
		
	}
	
	public void mostarTarifasRango(Admin admin, Date fechaI, Date fechaF) {
		ArrayList<Tarifa> rangoTarifas = admin.consultarTarifas(fechaI, fechaF);

		for (Tarifa tarifa : rangoTarifas) {
			System.out.println(tarifa.getFaltantes().toString() + " " + formatoFecha(tarifa.getFecha()));
		}	
	}
	
	public void CrearTarifa(Admin admin) {
		
		boolean añadir;
		boolean completado;
		do {
			
			Date fechaI = getDate("ingrese fecha inicial de la tarifa");
			Date fechaF = getDate("ingrese fecha final de la tarifa");
			TipoHabitacion tipo = getTipoHabitacion("Ingrse el tipo de habitahocion");
			double precio = getDouble("Ingrese el precio de la habitacion");
			completado = admin.crearTarifa(fechaI, fechaF, tipo, precio);
			
			if (!completado) {
				System.out.println("Alguna de las Tarifas es menor que la ingresada");
				if (getboolean("¿Desea sobre escribirlas?")) {
					admin.crearTarifa(fechaI, fechaF, tipo, -precio);
				}
			}
			
			System.out.println("\n Tarifas Actualizadas...");
			mostarTarifasRango(admin, fechaI, fechaF);;

			
			añadir = getboolean("¿Desea añadir otra tarifa?");
			
		}while(añadir);
		
		
	}
	
	public boolean tarifasCompletas(Admin admin) {
		ArrayList<Tarifa> faltantes = admin.checkTarifas();
		boolean completo = true;
		if (faltantes.size()>0) {
			completo = false;
		}
		return completo;
	}
// FIN TARIFAS ----------------------------------------------
	
// INICIO RESERVAS ------------------------------------------
	
public void menuReservasAdmin(Admin admin) {
	boolean continuarTarifa = true;
	System.out.println("\n-------- RESERVAS --------");
	System.out.println("1. Consular Reserva por rango de fechas ");
	System.out.println("2. Crear Reserva");
	System.out.println("3. Cancelar Reserva");
	System.out.println("4. Salir");


	int opcionSeleccionada;
	
	do {
		opcionSeleccionada = num("Seleccione una Opcion");
		switch (opcionSeleccionada) {
		case 1:
			mostrarReservas(admin); 
			break;
		case 2:
			crearReservas(admin);
			llenarReserva(admin);
			break;
		case 3:
			cancelarReserva(admin);
			break;
		case 4:
			continuarTarifa = false;
			break;

		default:
			break;
		}
	} while(continuarTarifa);
}	

	private void cancelarReserva(Admin admin) {
		
	
}
	private void llenarReserva(Admin admin) {
		boolean repetir = false;
		Habitacion habitacion;
		TipoHabitacion tipo;
		ArrayList<Habitacion> habitacionesConsulta;

		do {
			System.out.println("\nSeleccionar habitaciones Dispobibles");
			tipo = getTipoHabitacion("Ingrse el tipo de habitación que desea consultar");
			habitacionesConsulta = admin.DiponiblesParaGrupoEnCurso(tipo);
			
			if (habitacionesConsulta.size()> 0) {
				
				for (int i = 0; i < habitacionesConsulta.size(); i++) {
					habitacion = habitacionesConsulta.get(i);
					System.out.println("Numero " + habitacion.getId() + " precio " + admin.getPrecioHabitacionReserva(habitacion));
					int pos = num("Seleccione una Habitación");
					repetir = admin.completarReserva(pos);	
				}
			} else {
				System.out.println("No hay habitaciones Disponibles para este dia");
				repetir = false;
			}
			
			
					

		} while (repetir);

	
}
	private void crearReservas(Admin admin) {
		Date fechaI = getDate("ingrese fecha inicial de la tarifa");
		Date fechaF = getDate("ingrese fecha final de la tarifa");
		int tamañoGrupo= num("Ingrse el el numero de personas");
		
		String[] nombres = new String[tamañoGrupo];
		String[] documentos = new String[tamañoGrupo];
		String[] emails = new String[tamañoGrupo];
		String[] telefonos = new String[tamañoGrupo];
		int[] edades =new int[tamañoGrupo];
		
		for (int i = 0; i <tamañoGrupo; i++) {
			
			if(i == 0) {
				nombres[i] = input("Ingrese el nombre del lider del grupo");
				documentos[i] = input("Ingrese el numero de documento del lider del grupo");
				emails[i] = input("Ingrese el correo del lider del grupo");
				telefonos[i] = input("Ingrese el numero de telefono del lider del grupo");
				edades[i] = num("Ingrese la edad del lider del grupo");
			} else {
				nombres[i] = input("Ingrese el nombre de la " + (i+1) +" persona del grupo");
				documentos[i] = input("Ingrese el numero de documento de la " + (i+1) +" persona del grupo");
				emails[i] = input("Ingrese el correo de la " + (i+1) +" persona del grupo");
				telefonos[i] = input("Ingrese el numero de telefono de la " + (i+1) +" persona del grupo");
				edades[i] = num("Ingrese la edad de la " + (i+1) +" persona del grupo");
			}
			
		}
				
		admin.crearReserva(fechaI, fechaF, tamañoGrupo, nombres, documentos, emails, telefonos, edades);
		
	}
	private void mostrarReservas(Admin admin) {
		Date fechaI = getDate("ingrese fecha inicial de la tarifa");
		Date fechaF = getDate("ingrese fecha final de la tarifa");
		admin.mostrarReservas(fechaI, fechaF);
	}
	
	
// FIN RESERVAS ---------------------------------------------
	
	public String formatoFecha(Date fecha) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	    String fechaString = sdf.format(fecha);
		return fechaString;
	}
	
	public void crearServicioHotel(Admin admin) {
		HashMap<Integer,Servicio> listaServicios = admin.getServiciosHotel();
		if (!(listaServicios.isEmpty())) {
			System.out.println("\nServicios");
			for (int i = 0; i < listaServicios.size(); i++) {
				Servicio servicio = listaServicios.get(i);
				String nombre = servicio.getNombre();
				System.out.println(nombre + " (" + i + ")");
			}
		}
		System.out.println("\nCreando servicio hotel...");
		boolean centinela = true;
		while (centinela) {
			String nombre = input("Nombre servicio");
			double precio = getDouble("Precio");
			admin.crearServicioHotel(nombre, precio);
			centinela = getboolean("\n¿Desea añadir otro servicio?");
		}
	}
	
	public void añadirServicioHabitacion(Admin admin, int id) {
		System.out.println("\nCreando servicio habitacion...");
		boolean centinela = true;
		while (centinela) {
			String nombre = input("Nombre servicio habitacion");
			double precio = getDouble("Precio");
			admin.añadirServicioHabitacion(id, nombre, precio);
			centinela = getboolean("\n¿Desea añadir otro servicio?");
		}
	}
	
	
	private void crearHabitacion(Admin admin) {
		System.out.println("\nCreando habitacion...");
		boolean centinela1=true;
		while (centinela1) {
			TipoHabitacion tipoHabitacion = getTipoHabitacion("Tipo habitacion");
			int id = num("Numero habitacion");
			admin.crearHabitacion(tipoHabitacion, id);
			
			System.out.println("\nAñadiendo camas...");
			boolean centinela2=true;
			while (centinela2) {
				int capacidadCama = num("Capacidad cama");
				boolean apto = getboolean("\n¿Apto para niños?");
				admin.crearCama(id, capacidadCama, apto);
				centinela2 = getboolean("\n¿Desea añadir otra cama?");
			}
			
			System.out.println("\nAñadiendo servicios...");
			boolean centinela3=true;
			while (centinela3) {
				String nombre = input("Nombre servicio habitacion");
				double precio = getDouble("Precio");
				admin.añadirServicioHabitacion(id, nombre, precio);
				centinela3 = getboolean("\n¿Desea añadir otra servicio?");
			
			String caracteristicas = input("Caracteristicas");
			admin.setCaracteristicasHabitacion(caracteristicas, id);
			centinela1 = getboolean("\n¿Desea añadir otra habitacion?");

		}
		}
	}

	private void crearProductoMenu(Admin admin) {
		ArrayList<ProductoMenu> listaProductosMenu = admin.getMenu();
		if (!(listaProductosMenu.isEmpty())) {
			System.out.println("\nMenu");
			for (int i = 0; i < listaProductosMenu.size(); i++) {
				String nombre = listaProductosMenu.get(i).getNombre();
				int precio = (int)listaProductosMenu.get(i).getPrecio();
				boolean llevable = listaProductosMenu.get(i).getLlevable();
				String esLlevable;
				if (llevable) {
					esLlevable = "(Llevable)";
				} else {
					esLlevable = "(No llevable)";
				}
				System.out.println(nombre + ": " + precio +" "+ esLlevable);
			}
		}
		
		System.out.println("\nCreando producto menu...");
		boolean centinela = true;
		while (centinela) {
			//Date date = getDate("Hora admitida");
			boolean llevable = getboolean("\n¿Es llevable?");
			String nombre = input("Nombre producto menu");
			double precio = getDouble("Precio");
			admin.crearProductoMenu(null, llevable, nombre, precio);
			centinela=getboolean("\n¿Desea añadir otro producto?");
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
