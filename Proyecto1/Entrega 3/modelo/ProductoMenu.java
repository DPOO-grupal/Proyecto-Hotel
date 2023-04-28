package modelo;

import java.util.Date;


public class ProductoMenu extends Servicio{
	
//Atributos
	private Date horarioI;
	private Date horarioF;
	private boolean llevable;
	
//Constructor
	public ProductoMenu(Date horarioI, Date horarioF, boolean llevable,String nombre, double precio) {
		super(nombre, precio);
		this.horarioI = horarioI;
		this.horarioF = horarioF;
		this.llevable = llevable;
	}
//Metodos
	public Date getHorarioI() {
		return this.horarioI;
	}
	
	public Date getHorarioF() {
		return this.horarioF;
	}
	
	public boolean getLlevable() {
		return this.llevable;
	}
}
