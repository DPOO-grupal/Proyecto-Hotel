package modelo;

import java.sql.Date;

public class ProductoMenu extends Servicio{
	
//Atributos
	private Date horario;
	private boolean llevable;
	//private int id;
	private static int numProducto;
	
//Constructor
	public ProductoMenu(Date horario, boolean llevable, String nombre, double precio) 
	{
		super(nombre,precio);
		this.horario=horario;
		this.llevable=llevable;
		//this.id=numProducto;
	}
	
}
