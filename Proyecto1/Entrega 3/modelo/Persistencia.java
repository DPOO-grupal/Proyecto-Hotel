package modelo;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import controlador.Hotel;

public class Persistencia implements Serializable{
	private FileOutputStream fileOut;
	private FileInputStream fileInput;
	private ObjectOutputStream ouput;
	private ObjectInputStream input;
	private File directorio=new File("./Entrega 3/data/");
	private File dataFile = new File("./Entrega 3/data/hotel.ser");
	
	// constructor
	
	public Persistencia () {;
		
		if(!directorio.exists()) {
			directorio.mkdir();
		}
		
		try {
			if(!dataFile.exists()) {	
	
				dataFile.createNewFile();
				abrirOutput();
				escribir(null);
				cerrarOutput();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	// abrir archivo guardar
	public void abrirOutput () throws IOException {
		dataFile.delete();
		dataFile.createNewFile();
		fileOut = new FileOutputStream("./Entrega 3/data/hotel.ser");
		
		ouput = new ObjectOutputStream(fileOut);

	}
	
	// abrir archivo leectura
	public void abrirInput () throws IOException {
		
		fileInput = new FileInputStream("./Entrega 3/data/hotel.ser");
		input = new ObjectInputStream(fileInput);

	}
	
	// cerrar el fichero guardar
	
	public void cerrarOutput() throws IOException{
		if (ouput != null)
			ouput.close();
	}
	
	// cerrar el fichero leectura
	
	public void cerrarInput() throws IOException{
		if (input != null)
			input.close();
	}
	
	// escribir fichero 
	
	public void escribir(Hotel hotel) throws IOException{
		if (ouput != null)
			ouput.reset();
			ouput.writeObject(hotel);
	}
	
	// Leer fichero 
	
	public Hotel leer() throws IOException, ClassNotFoundException{
		
		Hotel hotel = null;
		
		if (input != null) {
			try {
				hotel = (Hotel) input.readObject();
			} catch (EOFException eof) {
				// fin del archivo
			}
		}
		return hotel;
			
	}
	
}
