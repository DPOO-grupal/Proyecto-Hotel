package modelo;

import java.util.ArrayList;
import java.util.Date;

public class Admin extends Usuario {
	
	public Admin(String login, String password) {
		super(login, password);
	}
	
	public void añadirUsuario(String login, String password, int tipo) {
		hotel.añadirUsuario(login, password, tipo);
	}
	
	public ArrayList<Tarifa> crearTarifa(Date fechaI, Date fechaF, TipoHabitacion tipo, double valor) {
		return hotel.crearTarifa(fechaI, fechaF, tipo, valor);
	}

}
