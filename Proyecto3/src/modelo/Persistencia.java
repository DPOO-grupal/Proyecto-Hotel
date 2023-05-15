package modelo;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import controlador.Hotel;

public class Persistencia implements Serializable{
	private FileOutputStream fileOut;
	private FileInputStream fileInput;
	private ObjectOutputStream ouput;
	private ObjectInputStream input;
	private File directorio=new File("./data/");
	private File dataFile = new File("./data/hotel.ser");
	private File staticDataFile = new File("./data/static.txt");
	
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
			
			if(!staticDataFile.exists()) {	
				
				dataFile.createNewFile();
				guardarStaticData(0, 0, new ArrayList<Integer>());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	// abrir archivo guardar
	public void abrirOutput () throws IOException {
		dataFile.delete();
		dataFile.createNewFile();
		fileOut = new FileOutputStream("./data/hotel.ser");
		
		ouput = new ObjectOutputStream(fileOut);

	}
	
	// abrir archivo leectura
	public void abrirInput () throws IOException {
		
		fileInput = new FileInputStream("./data/hotel.ser");
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
	
	public ArrayList<Integer> leerStaticData() {
		ArrayList<Integer> arrayList = new ArrayList<Integer>();
		BufferedReader br;

		 try {
			 br = new BufferedReader(new FileReader(staticDataFile));
			 String[] linea = br.readLine().split(";");
			
			 arrayList.add(Integer.parseInt(linea[0]));
			 arrayList.add(Integer.parseInt(linea[1]));
			 
			 String lista = linea[2].replace("[", "").replace("]", "").replace(" ", "");
			 for (String numero : lista.split(",")) {
				 if (!numero.equals("")) {
					 arrayList.add(Integer.parseInt(numero));
				}
			}
			 
		} catch (IOException e) {
			e.printStackTrace();
		}
		return arrayList;		
	}
	
	public void guardarStaticData(int servicio, int grupo, ArrayList<Integer> arrayList) {
		String texto = String.valueOf(servicio) + ";" + String.valueOf(grupo) + ";" + arrayList.toString();
		 try {
			java.io.PrintWriter output = new java.io.PrintWriter(staticDataFile);
			output.print(texto);
			output.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
			
		
	}
	
}
