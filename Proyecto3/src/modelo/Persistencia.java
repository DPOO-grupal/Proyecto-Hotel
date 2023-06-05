package modelo;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

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
	private String[] arhivosLog = {"productos.log", "facturas.log", "restaurante.log"};
	
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
		
		carpetaLog();
		// ArchivosLog
		
		for(String fileString : arhivosLog) {
			File file = new File("log/" + fileString);
			if(!file.exists()) {	
				crearLog(file);
			}
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
		if (input != null) {
			input.close();
		}
	}
	
	// escribir fichero 
	
	public void escribir(Hotel hotel) throws IOException{
		if (ouput != null) {
			ouput.reset();
			ouput.writeObject(hotel);
		}
			
	}
	
	// Leer fichero 
	
	public Hotel leer() throws IOException, ClassNotFoundException{
		
		Hotel hotel = null;
		
		if (input != null) {
			try {
				 hotel = (Hotel) input.readObject();
			} catch (EOFException eof) {
				// fin del archivo
			} catch (Exception e) {
				borrarDatos();
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
		java.io.PrintWriter output = null;
		 try {
			output = new java.io.PrintWriter(staticDataFile);
			output.print(texto);
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}finally {
			if(output!=null) {
				output.close();
			}
		}
			
		
	}
	public void borrarDatos() {
		try {
			dataFile.delete();
			dataFile.createNewFile();
			abrirOutput();
			escribir(null);
			cerrarOutput();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		guardarStaticData(0, 0, new ArrayList<Integer>());
		
	}
	
	// Archivos log
	
	
	public void añadirLogProductos(ProductoMenu producto, int cantidad) {
		File file = new File("log/" + arhivosLog[0]);
		String linea = producto.getNombre() + "," + producto.getPrecio() + "," + cantidad;
		añadirLineaLog(file, linea);
	}
	
	public HashMap<String, int[]> obtenerLogProductos() {
		HashMap<String, int[]> resultado = new HashMap<String, int[]>();
		int[] valores = new int[2];
		String[] datos = new String[3];
		BufferedReader br = null;
		
		 try {
			 br = new BufferedReader(new FileReader("log/productos.log"));
			 String linea = br.readLine();
			 while (linea != null) {
				 	datos = linea.split(",");
				 	valores = resultado.get(datos[0]);
				 	if (valores == null) {
				 		valores =  new int[2];
				 	}
					valores[0] += Integer.parseInt(datos[1]);
					valores[1] += Integer.parseInt(datos[2]);
				 	resultado.put(datos[0], valores);
					linea = br.readLine();
				}
			 
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if (br != null) {
	            try {
	            	br.close();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
		}
		return resultado;		
	}
	
	
	public void añadirLogRestaurante(ProductoMenu producto, int cantidad, Reserva reserva) {
		File file = new File("log/" + arhivosLog[2]);
		String linea = (producto.getPrecio()*cantidad) + "," + reserva.getPrecioReservaDia();
		añadirLineaLog(file, linea);
	}
	public ArrayList<int[]> datosReporteRestaurante() {
		ArrayList<int[]> resultado = new ArrayList<int[]>();
		int[] valores;
		String[] datos = new String[3];
		BufferedReader br = null;
		
		try {
			br = new BufferedReader(new FileReader("log/restaurante.log"));
			String linea = br.readLine();
			while (linea != null) {
				valores = new int[2];
				datos = linea.split(",");
				valores[0] = Integer.parseInt(datos[0]);
				valores[1] = Integer.parseInt(datos[1]);
				resultado.add(valores);
				linea = br.readLine();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return resultado;	
	}
	public void añadirLogFacturas(Date date, int precio) {
		File file = new File("log/" + arhivosLog[1]);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String fechaString = sdf.format(date);
		String linea = (fechaString + "," + precio);
		añadirLineaLog(file, linea);
	}
	
	public HashMap<String, Integer> datosReporteFacturas() {
		HashMap<String, Integer> resultado = new HashMap<>();
		String[] datos = new String[3];
		BufferedReader br = null;
		
		try {
			br = new BufferedReader(new FileReader("log/facturas.log"));
			String linea = br.readLine();
			while (linea != null) {
				datos = linea.split(",");
				Integer valor = resultado.get(datos[0]);
				if(valor == null) {
					valor = 0;
				}
				valor += Integer.parseInt(datos[1]);
				resultado.put(datos[0], valor);
				linea = br.readLine();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return resultado;	
	}
	
	public void carpetaLog() {
		File directorioLog = new File("log");
		if(!directorioLog .exists()) {
			directorioLog.mkdir();
		}
	}
	
	public void crearLog(File file) {
		try {
			file.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void borrarLog(File file) {
		
		file.delete();
		
		try {
			file.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void añadirLineaLog(File file, String linea) {
	    BufferedWriter writer = null;
	    try {
	        writer = new BufferedWriter(new FileWriter(file, true));
	        writer.write(linea);
	        writer.newLine();
	    } catch (IOException e) {
	        e.printStackTrace();
	    } finally {
	        if (writer != null) {
	            try {
	                writer.close();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	}

	
	

	
}
