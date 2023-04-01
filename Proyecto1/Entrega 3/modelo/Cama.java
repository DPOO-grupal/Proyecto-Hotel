package modelo;

import java.io.Serializable;

public class Cama implements Serializable{
	
//Atributos	
	private int capacidadCama;
	private boolean aptoParaNiño;
	
	
//Constructor
	public Cama(int capacidadCama, boolean aptoParaNiño) {
		this.capacidadCama=capacidadCama;
		this.aptoParaNiño=aptoParaNiño;
	}

//Metodos
	public int getCapacidadCama() {
		return this.capacidadCama;
	}
	
	public boolean getAptoParaNiño() {
		return this.aptoParaNiño;
	}
}
