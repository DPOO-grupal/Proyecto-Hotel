package modelo;

import java.time.LocalTime;
import java.util.Date;
import java.time.LocalTime;

public class ProductoMenu extends Servicio{
	
//Atributos
	private LocalTime horarioI;
	private LocalTime horarioF;
	private boolean llevable;
	
//Constructor
	public ProductoMenu(LocalTime horarioI, LocalTime horarioF, boolean llevable,String nombre, double precio) {
		super(nombre, precio);
		this.horarioI = horarioI;
		this.horarioF = horarioF;
		this.llevable = llevable;
	}
//Metodos
	public LocalTime getHorarioI() {
		return this.horarioI;
	}
	
	public LocalTime getHorarioF() {
		return this.horarioF;
	}
	
	public boolean getLlevable() {
		return this.llevable;
	}
}
