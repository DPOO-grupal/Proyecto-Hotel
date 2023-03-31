package modelo;

import java.io.Serializable;
import java.util.ArrayList;

public class Restaurante implements Serializable{
	
//Atributos
	private ArrayList<ProductoMenu> menu;
	
//Constructor
	public Restaurante() 
	{
		this.menu = new ArrayList<ProductoMenu>();
	}
	
	public void añadirProducto(ProductoMenu producto) 
	{
		menu.add(producto);
	}
	
	public void quitarProducto(ProductoMenu producto) 
	{
		for (int i = 0; i < menu.size(); i++) 
		{
			if (producto.equals(menu.get(i))) 
			{
				menu.remove(i);
			}
		}
	}
	
	public ArrayList<ProductoMenu> getMenu()
	{
		return this.menu;
	}
	
	public ProductoMenu getProducto(int id) 
	{
		return menu.get(id);	
	}
	
}
