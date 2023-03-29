package modelo;

import java.sql.Date;

public class ProductoMenu {
	
//Atributos
	private Date horario;
	private boolean llevable;
	
//Constructor
	public ProductoMenu(Date horario, boolean llevable) {
		this.horario = horario;
		this.llevable = llevable;
	}
	
}
