package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Restaurante implements Serializable{
	
//Atributos
	private HashMap<Integer, ProductoMenu> menu;
	
//Constructor
	public Restaurante() {
		this.menu = new HashMap<Integer, ProductoMenu>();
	}
	
	public void añadirProducto(ProductoMenu producto) {
		menu.put(producto.getId(), producto);
	}
	
	public void quitarProducto(ProductoMenu producto) {
		int llave = producto.getId();
		menu.remove(llave);
//		for (int i = 0; i < menu.size(); i++) {
//			if (producto.equals(menu.get(i))) {
//				menu.remove(i);
//			}
//		}
	}
	
	public HashMap<Integer, ProductoMenu> getMenu(){
		return this.menu;
	}
	
	public ProductoMenu getProducto(int id) {
		return menu.get(id);
	}
	
}
