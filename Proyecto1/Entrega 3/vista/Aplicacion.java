package vista;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.SortedMap;
import java.util.HashMap;

import controlador.Hotel;
import modelo.Admin;
import modelo.Empleado;
import modelo.Grupo;
import modelo.ProductoMenu;
import modelo.Servicio;
import modelo.Habitacion;
import modelo.Huesped;
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
			System.out.println("1. Iniciar sesion");
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
		
		hotel.setHoy(getDate("Ingrese el día de hoy "));
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

		System.out.println("0. Cerrar Sesión");
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
		System.out.println("4. Crear habitación");
		System.out.println("5. Crear producto menú");
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
			menuServiciosAdmin(admin);
			break;
		case 4:
			crearHabitacion(admin);
			break;
		case 5:
			menuProductoMenu(admin);
			break;
		case 6:
			menuReservas(admin);
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
		
			opcionSeleccionada= num("Seleccione una opción");
			
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
	
	public void menuTarifasEmpleado(Empleado empleado) {
		boolean continuarTarifa = true;
		do {
			
			System.out.println("\n-------- TARIFAS --------");
			System.out.println("1. Consular Tarifa por rango de fechas");
			System.out.println("1. Salir");


		
			int opcionSeleccionada;
		
			opcionSeleccionada= num("Seleccione una opción");
			
			switch (opcionSeleccionada) {
			case 1:
				consultarTarifas(empleado);
				input("Presione 'Enter' para continuar");

			case 2:
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
			System.out.println("Faltan crear las siguientes Tarifas para los tipos de Habitación");
			for (Tarifa tarifa : faltantes) {
				System.out.println(tarifa.getFaltantes().toString() + " " + formatoFecha(tarifa.getFecha()));
			}		
		} else {
			System.out.println("No faltan llenar tarifas");
		}
	}
	
	public void consultarTarifas(Empleado empleado) {
		Date fechaI = getDate("Ingrese fecha inicial de la tarifa");
		Date fechaF = getDate("Ingrese fecha final de la tarifa");
		
		mostarTarifasRango(empleado, fechaI, fechaF);		
		
	}
	
	public void mostarTarifasRango(Empleado empleado, Date fechaI, Date fechaF) {
		Collection<Tarifa> rangoTarifas = empleado.consultarTarifas(fechaI, fechaF);
		double precio;
		for (Tarifa tarifa : rangoTarifas) {
			System.out.println(formatoFecha(tarifa.getFecha()));
			
			precio = tarifa.getPrecio(TipoHabitacion.ESTANDAR);
			if (precio > 0) {
				System.out.println("Estandar: $" + precio);

			}
			precio = tarifa.getPrecio(TipoHabitacion.SUITE);
			if (precio > 0) {
				System.out.println("Suite: $" + precio);

			}
			precio = tarifa.getPrecio(TipoHabitacion.SUITEDOUBLE);
			if (precio > 0) {
				System.out.println("Suite Double: $" + precio);

			}
			
			System.out.println("");
			

		}	
	}
	
	public void CrearTarifa(Admin admin) {
		
		boolean añadir;
		boolean completado;
		do {
			
			Date fechaI = getDate("Ingrese fecha inicial de la tarifa");
			Date fechaF = getDate("Ingrese fecha final de la tarifa");
			TipoHabitacion tipo = getTipoHabitacion("Ingrese el tipo de habitación");
			double precio = getDouble("Ingrese el precio de la habitación");
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
	
	public void menuReservas(Empleado empleado) {
		boolean continuarTarifa = true;
	
	
		int opcionSeleccionada;
		
		do {
			System.out.println("\n-------- RESERVAS --------");
			System.out.println("1. Consular Reserva por rango de fechas ");
			System.out.println("2. Crear Reserva");
			System.out.println("3. Cancelar Reserva");
			System.out.println("4. Salir");
			opcionSeleccionada = num("Seleccione una opción");
			switch (opcionSeleccionada) {
			case 1:
				mostrarReservas(empleado); 
				break;
			case 2:
				crearReservas(empleado);
				llenarReserva(empleado);
				break;
			case 3:
				cancelarReserva(empleado);
				break;
			case 4:
				continuarTarifa = false;
				break;
	
			default:
				break;
			}
		} while(continuarTarifa);
	}
	
	

	private void cancelarReserva(Empleado empleado) {
		
		mostrarReservas(empleado);
		
		int id = num("Ingrese Identificador de la Reserva");
		
		boolean cancelada = empleado.cancelarReserva(id);
		
		if(cancelada) {
			System.out.println("La reserva fue cancelada");
		} else {
			System.out.println("La reserva ya no puede ser cancelada");
		}
	
}
	private void llenarReserva(Empleado empleado) {
		boolean repetir = false;
		Habitacion habitacion;
		TipoHabitacion tipo;
		ArrayList<Habitacion> habitacionesConsulta;

		do {
			System.out.println("\nSeleccionar habitaciones Dispobibles");
			tipo = getTipoHabitacion("Ingrese el tipo de habitación que desea consultar");
			habitacionesConsulta = empleado.DiponiblesParaGrupoEnCurso(tipo);
			
			if (habitacionesConsulta.size()> 0) {
				
				for (int i = 0; i < habitacionesConsulta.size(); i++) {
					habitacion = habitacionesConsulta.get(i);
					System.out.println("Número " + habitacion.getId() + " precio " + empleado.getPrecioHabitacionReserva(habitacion));
					int pos = num("Seleccione una Habitación");
					repetir = empleado.completarReserva(pos);	
				}
			} else {
				System.out.println("No hay habitaciones Disponibles para este día");
				repetir = false;
			}
			
			
					

		} while (repetir);

	
}
	private void crearReservas(Empleado empleado) {
		Date fechaI = getDate("Ingrese fecha inicial de la tarifa");
		Date fechaF = getDate("Ingrese fecha final de la tarifa");
		int tamañoGrupo= num("Ingrse el el número de personas");
		
		String[] nombres = new String[tamañoGrupo];
		String[] documentos = new String[tamañoGrupo];
		String[] emails = new String[tamañoGrupo];
		String[] telefonos = new String[tamañoGrupo];
		int[] edades =new int[tamañoGrupo];
		
		for (int i = 0; i <tamañoGrupo; i++) {
			
			if(i == 0) {
				nombres[i] = input("Ingrese el nombre del lider del grupo");
				documentos[i] = input("Ingrese el número de documento del lider del grupo");
				emails[i] = input("Ingrese el correo del lider del grupo");
				telefonos[i] = input("Ingrese el numero de telefono del lider del grupo");
				edades[i] = num("Ingrese la edad del lider del grupo");
			} else {
				nombres[i] = input("Ingrese el nombre de la " + (i+1) +" persona del grupo");
				documentos[i] = input("Ingrese el numero de documento de la " + (i+1) +" persona del grupo");
				emails[i] = input("Ingrese el correo de la " + (i+1) +" persona del grupo");
				telefonos[i] = input("Ingrese el número de telefono de la " + (i+1) +" persona del grupo");
				edades[i] = num("Ingrese la edad de la " + (i+1) +" persona del grupo");
			}
			
		}
				
		empleado.crearReserva(fechaI, fechaF, tamañoGrupo, nombres, documentos, emails, telefonos, edades);
		
	}
	private void mostrarReservas(Empleado empleado) {
		Date fechaI = getDate("Ingrese fecha inicial de la reserva");
		Date fechaF = getDate("Ingrese fecha final de la reserva");
		HashMap<Integer,Grupo> reservas = empleado.mostrarReservas(fechaI, fechaF);
		Huesped lider;
		for (Grupo grupo : reservas.values()) {
			lider = grupo.getLider();
			System.out.println("\nIdentificador de reserva: " + grupo.getId());
			System.out.println("Lider del grupo:");
			System.out.println("Nombre: " + lider.getNombre());
			System.out.println("Numero de documento: " + lider.getDocumento());
		}
		System.out.println("\n");
		
	}
	
	
// FIN RESERVAS ---------------------------------------------
	
	public String formatoFecha(Date fecha) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	    String fechaString = sdf.format(fecha);
		return fechaString;
	}
	
// INICIO SERVICIO HOTEL -----------------------------------------------------
	
	public void menuServiciosAdmin(Admin admin) {
		boolean continuar = true;
		int opcionSeleccionada;
		
		do {
			System.out.println("\n-------- SERVICIOS HOTEL --------");
			System.out.println("1. Consular servicios del hotel");
			System.out.println("2. Crear servicio hotel");
			System.out.println("3. Añadir servicio hotel a habitación");
			System.out.println("4. Salir");
			opcionSeleccionada = num("Seleccione una opción");
			switch (opcionSeleccionada) {
			case 1:
				mostrarServiciosHotel(admin);
				input("Presione 'Enter' para continuar");
				break;
			case 2:
				crearServicioHotel(admin);
				input("Presione 'Enter' para continuar");
				break;
			case 3:
				añadirServicioHotelHabitacion(admin);
				input("Presione 'Enter' para continuar");
				break;
			case 4:
				continuar = false;
				break;

			default:
				input("Presione 'Enter' para continuar");
				break;
			}
		} while(continuar);
	}
	
	public void mostrarServiciosHotel(Admin admin) {
		HashMap<Integer,Servicio> Servicios = admin.getServiciosHotel();
		if (!(Servicios.isEmpty())) {
			System.out.println("\nServicios");
			Collection<Servicio> listaServicios = Servicios.values();
			for (Servicio servicio : listaServicios) {
				String nombre = servicio.getNombre();
				int precio = (int) servicio.getPrecio();
				int id = servicio.getId();
				System.out.println(nombre + " (" + id + ")" + ": " + precio);
			}
		} else {
			System.out.println("\nNo hay servicios");
		}
	}
	
	public void crearServicioHotel(Admin admin) {
		System.out.println("\nCreando servicio hotel...");
		boolean centinela = true;
		while (centinela) {
			String nombre = input("Nombre servicio");
			double precio = getDouble("Precio");
			admin.crearServicioHotel(nombre, precio);
			centinela = getboolean("\n¿Desea crear otro servicio hotel?");
		}
	}
	
	public void añadirServicioHotelHabitacion(Admin admin) {
		System.out.println("\nAñadiendo servicio hotel...");
		boolean centinela = true;
		while (centinela) {
			mostrarServiciosHotel(admin);
			int idServicio = num("ID del servicio hotel");
			int idHabitacion = num("ID habitación");
			try {
				admin.añadirServicioHotelHabitacion(idHabitacion, idServicio);
				
			} catch (Exception e) {
				System.out.println("No existe la habitacion o el servicio");
			}
			centinela = getboolean("\n¿Desea añadir otro servicio hotel?");
		}
	}
	
	
//FIN SERVICIOS HOTEL ---------------------------------------------------------
	
//INICIO HABITACION ---------------------------------------------------------------
	
	public void menuHabitaciones(Admin admin) {
		boolean continuar = true;
		int opcionSeleccionada;
		
		do {
			System.out.println("\n-------- HABITACION --------");
			System.out.println("1. Consular habitaciones");
			System.out.println("2. Crear habitación");
			System.out.println("3. Añadir servicio a habitación");
			System.out.println("4. Salir");
			opcionSeleccionada = num("Seleccione una opción");
			switch (opcionSeleccionada) {
			case 1:
				mostrarHabitaciones(admin); 
				break;
			case 2:
				crearHabitacion(admin);
				break;
			case 3:
				añadirServicioHabitacion(admin, opcionSeleccionada);
				break;
			case 4:
				continuar = false;
				break;

			default:
				break;
			}
		} while(continuar);
	}
	
	
	private void mostrarHabitaciones(Admin admin) {
		HashMap<Integer, Habitacion> listaHabitaciones = admin.getHabitaciones();
		if (!(listaHabitaciones.isEmpty())) {
			System.out.println("\nHabitaciones");
			for (int i = 0; i < listaHabitaciones.size(); i++) {
				Habitacion habitacion = listaHabitaciones.get(i);
				int id = habitacion.getId();
				ArrayList<Servicio> servicios =habitacion.getServicios();
				String caracteristicas = habitacion.getCaracteristicas();
				System.out.println("\nid: " + id);
				System.out.println("Servicios: " + servicios.toString());
				System.out.println("Caracteristicas: " + caracteristicas);
			}
		} else {
			System.out.println("No hay habitaciones");
		}	
	}
	
	
	private void crearHabitacion(Admin admin) {
		System.out.println("\nCreando habitación...");
		boolean centinela1=true;
		while (centinela1) {
			TipoHabitacion tipoHabitacion = getTipoHabitacion("Tipo habitación");
			int id = num("Numero habitación");
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
				String nombre = input("Nombre de servicio habitación");
				double precio = getDouble("Precio");
				admin.añadirServicioHabitacion(id, nombre, precio);
				centinela3 = getboolean("\n¿Desea añadir otra servicio?");
			}
			
			String caracteristicas = input("Caracteristicas");
			admin.setCaracteristicasHabitacion(caracteristicas, id);
			centinela1 = getboolean("\n¿Desea añadir otra habitación?");

		}
	}
	

	public void añadirServicioHabitacion(Admin admin, int id) {
		System.out.println("\nCreando servicio habitación...");
		boolean centinela = true;
		while (centinela) {
			mostrarHabitaciones(admin);
			String nombre = input("Nombre servicio habitación");
			double precio = getDouble("Precio");
			try {
				admin.añadirServicioHabitacion(id, nombre, precio);
				
			} catch (Exception e) {
				System.out.println("No existe la habitación");
			}
			centinela = getboolean("\n¿Desea añadir otro servicio?");
		}
	}
	
	
//FIN HABITACION --------------------------------------------------------------
	
//INICIO PRODUCTO MENU -------------------------------------------------------	
	
	public void menuProductoMenu(Admin admin) {
		boolean continuar = true;
		int opcionSeleccionada;
		
		do {
			System.out.println("\n-------- PRODUCTO MENU --------");
			System.out.println("1. Consular menu");
			System.out.println("2. Crear producto menu");
			System.out.println("3. Añadir producto menu a habitación");
			System.out.println("4. Salir");
			opcionSeleccionada = num("Seleccione una opción");
			switch (opcionSeleccionada) {
			case 1:
				mostrarMenuProductos(admin); 
				break;
			case 2:
				crearProductoMenu(admin);
				break;
			case 3:
				añadirProductoMenuHabitacion(admin);
				break;
			case 4:
				continuar = false;
				break;

			default:
				break;
			}
		} while(continuar);
	}
	
	private void mostrarMenuProductos(Admin admin) {
		ArrayList<ProductoMenu> listaProductosMenu = admin.getMenu();
		if (!(listaProductosMenu.isEmpty())) {
			System.out.println("\nMenu");
			for (int i = 0; i < listaProductosMenu.size(); i++) {
				String nombre = listaProductosMenu.get(i).getNombre();
				int precio = (int)listaProductosMenu.get(i).getPrecio();
				boolean llevable = listaProductosMenu.get(i).getLlevable();
				int id = listaProductosMenu.get(i).getId();
				String esLlevable;
				if (llevable) {
					esLlevable = "(Llevable)";
				} else {
					esLlevable = "(No llevable)";
				}
				System.out.println(nombre + "(" + id + ")" + ": " + precio +" "+ esLlevable);
			}
		} else {
			System.out.println("\nNo hay productos en el menú");
		}
	}
	
	private void crearProductoMenu(Admin admin) {
		
		System.out.println("\nCreando producto menú...");
		boolean centinela = true;
		while (centinela) {
			//Date date = getDate("Hora admitida");
			boolean llevable = getboolean("\n¿Es llevable?");
			String nombre = input("Nombre producto menú");
			double precio = getDouble("Precio");
			admin.crearProductoMenu(null, llevable, nombre, precio);
			centinela=getboolean("\n¿Desea añadir otro producto?");
		}
	}
	
	private void añadirProductoMenuHabitacion(Admin admin) {
		System.out.println("\nAñadiendo producto menu...");
		boolean centinela = true;
		while (centinela) {
			mostrarMenuProductos(admin);
			int idProductoMenu = num("ID del producto menu");
			int idHabitacion = num("ID habitacion");
			try {
				admin.añadirServicioHotelHabitacion(idHabitacion, idProductoMenu);
				
			} catch (Exception e) {
				System.out.println("No existe la habitacion o el producto menú");
			}
			centinela = getboolean("\n¿Desea añadir otro servicio hotel?");
		}
	}
	
//FIN PRODUCTO MENU ----------------------------------------------------------------	
	

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
