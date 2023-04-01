package modelo;

public class Cama {
	
//Atributos	
	private String tipo;
	private int capacidadCama;
	private boolean aptoParaNiño;
	
	
//Constructor
	public Cama(String tipo, int capacidadCama, boolean aptoParaNiño) {
		this.tipo=tipo;
		this.capacidadCama=capacidadCama;
		this.aptoParaNiño=aptoParaNiño;
	}

//Metodos
	public String getTipo() {
		return this.tipo;
	}
	
	public int getCapacidadCama() {
		return this.capacidadCama;
	}
	
	public boolean getAptoParaNiño() {
		return this.aptoParaNiño;
	}
}
