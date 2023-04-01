package modelo;

import java.sql.Date;

public class ProductoMenu extends Servicio{
	
//Atributos
	private Date horario;
	private boolean llevable;
	
//Constructor
	public ProductoMenu(Date horario, boolean llevable,String nombre, double precio) {
		super(nombre, precio);
		this.horario = horario;
		this.llevable = llevable;
	}
//Metodos
	public Date getHorario() {
		return this.horario;
	}
	
	public boolean getLlevable() {
		return this.llevable;
	}
}
