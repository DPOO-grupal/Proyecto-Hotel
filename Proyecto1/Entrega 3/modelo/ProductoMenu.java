package modelo;

import java.sql.Date;

public class ProductoMenu {
	
	private Date horario;
	private boolean llevable;
	
	public ProductoMenu(Date horario, boolean llevable) {
		this.horario = horario;
		this.llevable = llevable;
	}
	
}
