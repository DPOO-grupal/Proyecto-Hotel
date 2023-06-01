package vistaEmpleado;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.ColorUIResource;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import controlador.WindowManager;
import vistaAdmin.AdminHabitacionesFrame;

public class EmpleadoMenuPrincipal extends JFrame implements ActionListener {

	protected JPanel panelIzquierdo;
	private JPanel panelDerecho;
	
	private JTable tablaHoy;
    private DefaultTableModel modeloTablaHoy;
    private JTable tablaAnual;
    private DefaultTableModel modeloTablaAnual;
	
	protected JFrame tarifasFrame;
	protected JFrame serviciosFrame;
	protected JFrame habitacionesFrame;
	protected JFrame restauranteFrame;
	protected WindowManager windowManager;
	protected JButton checkIn;
	protected JButton checkOut;
	protected JButton setFecha;
	protected JFrame reservasFrame;
	protected JPanel panelHoy;

	public EmpleadoMenuPrincipal(WindowManager windowManager){
        setLayout(new BorderLayout());
		setTitle("Menu Principal");
		
		this.windowManager = windowManager;
		
		panelIzquierdo = new JPanel();
		panelIzquierdo.setPreferredSize(new Dimension(300, 0));
		setBotones();
		
        // Panel Derecho
        panelDerecho = new JPanel();
        setPanelInfo();
        
        // Agregar los paneles al JFrame
        add(panelIzquierdo, BorderLayout.WEST);
        add(panelDerecho, BorderLayout.CENTER);


		// FRAMES
		

		tarifasFrame = new EmpleadoTarifasFrame(windowManager);
		serviciosFrame = new EmpleadoServiciosFrame(windowManager);
		reservasFrame = new EmpleadoReservasFrame(windowManager);
		serviciosFrame = new EmpleadoServiciosFrame(windowManager);
		habitacionesFrame = new EmpleadoHabitacionesFrame(windowManager);
		restauranteFrame = new EmpleadoRestauranteFrame(windowManager);
		
		ocupacionHoy();
		ocupacionAnual();

	}

	private void setPanelInfo() {
		panelDerecho.setLayout(new BorderLayout());
		panelDerecho.setBackground(Color.decode("#ACCAF2"));
		JPanel check = new JPanel();
		check.setBackground(Color.decode("#7E8C69"));
		check.setPreferredSize(new Dimension(0,200));
		check.setLayout(new FlowLayout(FlowLayout.CENTER, 300, 80));
	    Font fontButton = new Font("Arial", Font.BOLD, 16);

		checkIn = new JButton("Check-In");
		checkIn.setPreferredSize(new Dimension(200,50));
		checkIn.setFont(fontButton);
		checkIn.setBackground(Color.decode("#D0ECF2"));
		checkIn.addActionListener(this);
		
		checkOut = new JButton("Check-Out");
		checkOut.setPreferredSize(new Dimension(200,50));
		checkOut.setFont(fontButton);
		checkOut.setBackground(Color.decode("#D0ECF2"));
		checkOut.addActionListener(this);

		
		check.add(checkIn);
		check.add(checkOut);
		
		panelDerecho.add(check, BorderLayout.SOUTH);
		
		JPanel PanelOcupacion = new JPanel();
		PanelOcupacion.setBorder(BorderFactory.createEmptyBorder(20, 100, 20, 100));
		PanelOcupacion.setBackground(Color.decode("#ACCAF2"));
		GridBagLayout gridbag = new GridBagLayout();
	    GridBagConstraints constraints = new GridBagConstraints();
	    PanelOcupacion.setLayout(gridbag);
	    
	    JLabel OHoy = new JLabel();
	    OHoy.setText("Ocupacion hoy: ");
	    OHoy.setFont(new Font("Times New Roman", 1, 20));
	    constraints.insets = new Insets(10, 0, 10, 0);
	    constraints.anchor = GridBagConstraints.SOUTHWEST;
	    constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridheight = 1;
        constraints.gridwidth = 1;
        constraints.weightx = 1;
        constraints.weighty = 1;
  	    constraints.fill = GridBagConstraints.NONE;

	    PanelOcupacion.add(OHoy, constraints);
	    
	    //Creacion de la tabla servicios
  		String[] columnas = {""}; //Nombre de las columnas
        modeloTablaHoy = new DefaultTableModel(columnas, 0);
          
        //Filas de la tabla
//        String[] filaPrueba = {"0"};
//  	    modeloTablaHoy.addRow(filaPrueba);
  	    //Diseño de la tabla
          tablaHoy = new JTable(modeloTablaHoy);
//          tablaHoy.setShowHorizontalLines(true);
//          tablaHoy.setShowVerticalLines(true);
//          tablaHoy.setGridColor(Color.BLACK);
          tablaHoy.getTableHeader().setBackground(Color.decode("#204473"));
          tablaHoy.getTableHeader().setForeground(Color.white);
          tablaHoy.getTableHeader().setFont(new Font("Times New Roman", 1, 30));
          tablaHoy.getTableHeader().setReorderingAllowed(false);
          tablaHoy.setFont(new Font("Times New Roman", 1, 20));
          tablaHoy.setRowHeight(70);
          tablaHoy.setEnabled(false);

          DefaultTableCellRenderer modelocentrar = new DefaultTableCellRenderer();
          modelocentrar.setHorizontalAlignment(SwingConstants.CENTER);

          
          //tablaHoy.getColumnModel().getColumn(0).setCellRenderer(modelocentrar);
          for (int i = 0; i< 1; i++) {
              tablaHoy.getColumnModel().getColumn(i).setCellRenderer(modelocentrar);
              tablaHoy.getColumnModel().getColumn(i).setCellEditor(null);
          }
          //Tamaño y ubicacion de la tabla en el panel
          panelHoy = new JPanel();
          panelHoy.setLayout(gridbag);
          //panelHoy.setPreferredSize(new Dimension(200, 100));
          constraints.gridx = 0;
          constraints.insets = new Insets(0, 0, 0, 0);
          constraints.gridy = 1;

          constraints.gridheight = 1;
          constraints.gridwidth = 10;
          //constraints.weightx = 1;
          //constraints.weighty = 0.1;
          
  	    constraints.anchor = GridBagConstraints.WEST;
  	    constraints.fill = GridBagConstraints.BOTH;

          PanelOcupacion.add(panelHoy, constraints);
          
        JLabel OAnual = new JLabel();
        OAnual.setText("Ocupacion anual: ");
        OAnual.setFont(new Font("Times New Roman", 1, 20));
  	    constraints.insets = new Insets(0, 0, -10, 0);
  	    constraints.gridx = 0;
        constraints.gridy = 3;

        constraints.gridheight = 1;
        constraints.gridwidth = 1;
        //constraints.weightx = 1;
	    constraints.anchor = GridBagConstraints.SOUTHWEST;
  	    constraints.fill = GridBagConstraints.NONE;

  	    PanelOcupacion.add(OAnual, constraints);
  	    
  	//Creacion de la tabla servicios
  		String[] columnasAnual = new String[12]; //Nombre de las columnas
  		String[] meses = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
  		Date dia = windowManager.getDia();
  		for (int i = 0 ; i < 12 ; i++) {
  			String mes = meses[windowManager.pasarMes(dia, i).getMonth()];
  			columnasAnual[i] = mes;
  		}
          modeloTablaAnual = new DefaultTableModel(columnasAnual, 0);
          
          //Filas de la tabla
          String[] fila1 = {""};
          modeloTablaAnual.addRow(fila1);
  	    
  	    //Diseño de la tabla
          tablaAnual = new JTable(modeloTablaAnual);
          tablaAnual.setShowHorizontalLines(true);
          tablaAnual.setShowVerticalLines(true);
          tablaAnual.setGridColor(Color.BLACK);
          tablaAnual.getTableHeader().setBackground(Color.decode("#204473"));
          tablaAnual.getTableHeader().setForeground(Color.white);
          tablaAnual.getTableHeader().setReorderingAllowed(false);
          tablaAnual.getTableHeader().setFont(new Font("Times New Roman", 1, 15));
          tablaAnual.setFont(new Font("Times New Roman", 1, 15));
          tablaAnual.setRowHeight(100);
          tablaAnual.setEnabled(false);

          DefaultTableCellRenderer modelocentrarAnual = new DefaultTableCellRenderer();
          modelocentrarAnual.setHorizontalAlignment(SwingConstants.CENTER);


          for (int i = 0; i< 12; i++) {
              tablaAnual.getColumnModel().getColumn(i).setCellRenderer(modelocentrar);
              tablaAnual.getColumnModel().getColumn(i).setCellEditor(null);
          }

          JScrollPane scrollPanelAnual = new JScrollPane(tablaAnual, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
          //scrollPanelAnual.setPreferredSize(new Dimension(400, 650));
          //Tamaño y ubicacion de la tabla en el panel
          constraints.gridx = 0;
          constraints.insets = new Insets(0, 0, 10, 0);
          constraints.gridy = 4;
          constraints.ipady = 101;
          constraints.ipadx = 1000;
          constraints.gridheight = 1;
          constraints.gridwidth = 1;
          //constraints.weightx = 1;
          //constraints.weighty = 0.1;
          
          constraints.anchor = GridBagConstraints.WEST;
    	    constraints.fill = GridBagConstraints.HORIZONTAL;

          PanelOcupacion.add(scrollPanelAnual, constraints);
	    
		panelDerecho.add(PanelOcupacion, BorderLayout.CENTER);
	}

	protected void setBotones() {
		// Configuracion General
		GridBagLayout gridbag = new GridBagLayout();
	    GridBagConstraints constraints = new GridBagConstraints();
	    panelIzquierdo.setLayout(gridbag);
	    panelIzquierdo.setBackground(Color.decode("#204473"));
	    
	    JButton[] opciones = new JButton[6];
	    String[] nombres = {"Tarifas", 
	    					"Servicios", 
	    					"Habitaciones", 
	    					"Restaurante", 
	    					"Reservas",
	    					"Cerrar sesion"};
	    
	    Font fontButton = new Font("Arial", Font.BOLD, 16);
	    panelIzquierdo.setBorder(BorderFactory.createEmptyBorder(100, 20, 100, 20));

	    for (int i = 0; i<6; i++) {
	    	opciones[i] = new JButton(nombres[i]);
	    	opciones[i].setBackground(Color.decode("#ACCAF2"));
	    	opciones[i].addActionListener(this);
	    	opciones[i].setPreferredSize(new Dimension(200, 50));
	    	opciones[i].setFont(fontButton);
	    	constraints.gridy = i;
	    	constraints.weighty = 1;
	    	panelIzquierdo.add(opciones[i],constraints);
	    }
		
	}
	
	public void checkIn() {
		UIManager.put("OptionPane.minimumSize",new Dimension(400,200));
		JOptionPane.showInputDialog(null, "Ingrese el número de su grupo para el check-in", "Check-in", JOptionPane.PLAIN_MESSAGE);
	}
	
	public void checkOut() {
		try {
			UIManager.put("OptionPane.minimumSize",new Dimension(400,200));
			String idGrupo = JOptionPane.showInputDialog(null, "Ingrese el número de su grupo para el check-out", "Check-out", JOptionPane.PLAIN_MESSAGE);
			factura(idGrupo);
			//windowManager.checkOut(Integer.parseInt(idGrupo));
		} catch (Exception e) {
			String error = e.getMessage();
			if (error.contains("null")) {
				JOptionPane.showMessageDialog(null, "No existe el grupo");
			} else {
				JOptionPane.showMessageDialog(null, "Seleccione un grupo");
			}
		}
	}
	
	public void factura (String idGrupo) {
		JFrame frameFactura = new JFrame();
		frameFactura.setSize(300, 500);
		frameFactura.setLocationRelativeTo(null);
		frameFactura.setLayout(new GridLayout(1,0));
		frameFactura.setBackground(Color.decode("#ccd2c2"));

		
		//ArrayList<String[]> listaServiciosHabitacion = windowManager.getServiciosHabitacion(idGrupo);
		ArrayList<String[]> listaServiciosHabitacion = new ArrayList<String[]>();
		String[] ejemplo1 = {"Lomo", "13000"};
		String[] ejemplo2 = {"Carne", "12000"};
		String[] ejemplo3 = {"Pulpo", "11000"};
		listaServiciosHabitacion.add(ejemplo1);
		listaServiciosHabitacion.add(ejemplo2);
		listaServiciosHabitacion.add(ejemplo3);
		
		
		
		int tamañoColumnas = 2+listaServiciosHabitacion.size();
		
		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setLayout(new GridLayout(tamañoColumnas,2,2,0));
		panelPrincipal.setBackground(Color.decode("#ccd2c2"));
		
		JLabel tituloFactura = new JLabel("Factura del grupo:");
		JLabel tituloIdGrupo = new JLabel(idGrupo);
		JLabel tituloServicio = new JLabel("Servicio");
		JLabel tituloPrecio = new JLabel("Precio");
		
		panelPrincipal.add(tituloFactura);
		panelPrincipal.add(tituloIdGrupo);
		panelPrincipal.add(tituloServicio);
		panelPrincipal.add(tituloPrecio);
		
		
		for (String[] servicioHabitacion : listaServiciosHabitacion) {
			JLabel nombreServicio = new JLabel(servicioHabitacion[0]);
			nombreServicio.setSize(30, 30);
			JLabel precioServicio = new JLabel(servicioHabitacion[1]);
			precioServicio.setSize(30, 30);
			panelPrincipal.add(nombreServicio);
			panelPrincipal.add(precioServicio);
		}		
		
		JLabel tituloHabitaciones = new JLabel("Habitación");
		JLabel espacio = new JLabel("");
		
		panelPrincipal.add(tituloHabitaciones);
		panelPrincipal.add(espacio);
		
		
		JScrollPane scroll = new JScrollPane(panelPrincipal);
		
		frameFactura.add(scroll);
		frameFactura.setVisible(true);
	}
	
	public void colorearTablaAnio(int i, Color color, String cantidad) {
		DefaultTableCellRenderer modelocentrar = new DefaultTableCellRenderer();
		modelocentrar.setHorizontalAlignment(SwingConstants.CENTER);
		modelocentrar.setBackground(color);
		tablaAnual.getColumnModel().getColumn(i).setCellRenderer(modelocentrar);
		tablaAnual.getColumnModel().getColumn(i).setCellEditor(null);
		tablaAnual.setShowHorizontalLines(true);
		tablaAnual.setShowVerticalLines(true);
		modeloTablaAnual.setValueAt((Object) cantidad, 0, i);
		//resetDatos();
		
		//JLabel label = new JLabel((String) tablaAnual.getValueAt(0, i));
		//label.setBackground(Color.GREEN);
		//tablaAnual.setValueAt(label, 0, i);
		//((DefaultTableModel) tablaAnual.getModel()).fireTableDataChanged();
	}
	public void ocupacionAnual() {
//		modeloTablaAnual.getDataVector().removeAllElements();
//		modeloTablaAnual.fireTableDataChanged();
		Date dia = windowManager.getDia();
		for (int i = 0 ; i < 12 ; i++) {
			//System.out.println("-----------------------NUEVO MES: " + i + " --- DIA DE BUSQUEDA: " + dia.toString() + "-------------------------------");
			int ocupacionesMes = 0;
			int cantidadDias = hastaFinMes(dia);
			ocupacionesMes = contarOcupacionesMes(dia, cantidadDias);
			//System.out.println("ocupacionesMes " + ocupacionesMes + ",cantidadDias " + cantidadDias);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(dia);
//			calendar.set(dia.getYear(), dia.getMonth()+1, 1);
			calendar.add(Calendar.MONTH, 1);
			calendar.set(Calendar.DAY_OF_MONTH, 1);
			dia = calendar.getTime();
			int totalHabitaciones = windowManager.getTotalHabitaciones();
			int maximoPosible =  30*totalHabitaciones;
			//System.out.println("Total: " + totalHabitaciones);
			Color color = new ColorUIResource(Color.WHITE);
			if (maximoPosible == 0) {
				color = new ColorUIResource(Color.WHITE);
			}
			else if (ocupacionesMes < maximoPosible/3 && ocupacionesMes > 0) {
				color = new Color(102, 255, 102);
			}
			else if (ocupacionesMes < (maximoPosible/3)*2 && ocupacionesMes >= maximoPosible/3) {
				color = new Color(255, 255, 153);
			}
			else if (ocupacionesMes >= (maximoPosible/3)*2) {
				color = new Color(255, 102, 102);
			}
			else {
				color = new ColorUIResource(Color.WHITE);
			}
			colorearTablaAnio(i, color, ocupacionesMes+"");
		}		
	}
	
	public int contarOcupacionesMes(Date dia, int cantidadDias) {
		int contadorOcupadas = 0;
		for (int j = 0 ; j < cantidadDias ; j++) {
			int incremento = windowManager.contarOcupadasDia(dia);
			contadorOcupadas += incremento;
			//System.out.println("Incremento: " + incremento + ", Ocupadas: " + contadorOcupadas);
			//System.out.println("====================================  NUEVO DIA: " + dia.toString());
			dia = windowManager.pasarDia(dia);
		}
		return contadorOcupadas;
	}
	
	public int hastaFinMes(Date dia) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(dia.getYear(), dia.getMonth()+1, 0);
		int ultimoDia = calendar.get(Calendar.DAY_OF_MONTH);
		return ultimoDia - (dia.getDate()) + 1;
	}
	
	public void ocupacionHoy() {
		modeloTablaHoy.getDataVector().removeAllElements();
        modeloTablaHoy.fireTableDataChanged();
		Integer[] habitaciones = getHabitaciones();
		Integer[] ocupadas = getOcupadas();
		String[][] matriz = cargarHabitaciones(habitaciones.length, habitaciones);
		if ((matriz.length) == 0) {
			modeloTablaHoy.setColumnCount(1);
		}
		else {
			modeloTablaHoy.setColumnCount(matriz[0].length);
		}
		tablaHoy.setTableHeader(null);
		tablaHoy.setShowGrid(true);
		tablaHoy.setGridColor(Color.BLACK);
		panelHoy.removeAll();
//		GridBagLayout gridbag = new GridBagLayout();
//		panelHoy.setLayout(gridbag);
		
		for (int i = 0 ; i < matriz.length ; i++) {
			for (int j = 0 ; j < matriz[0].length ; j++) {
				JLabel label = new JLabel(matriz[i][j], SwingConstants.CENTER);
				label.setAlignmentX(CENTER_ALIGNMENT);
				label.setAlignmentY(CENTER_ALIGNMENT);
				label.setOpaque(false);
				label.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
				label.setFont(new Font("Times New Roman", 1, 20));
				GridBagConstraints co = new GridBagConstraints();
				co.gridy = i;
				co.gridx = j;
				co.ipadx = 20;
				co.ipady = 20;
				co.weightx = 1;
				co.weighty = 1;
				co.insets = new Insets(1, 1, 1, 1);
				co.fill = GridBagConstraints.BOTH;
				panelHoy.add(label, co);
//				int fila = (ocupadas[i]/100)-1;
//				int col = (ocupadas[i]%100)-1;
				
			}
		}
		
		for (int i = 0 ; i < ocupadas.length ; i++) {
			int fila = (ocupadas[i]/100)-1;
			int col = (ocupadas[i]%100)-1;
			JLabel label = new JLabel(matriz[fila][col], SwingConstants.CENTER);
			label.setFont(new Font("Times New Roman", 1, 20));
			Color color = new Color(255, 102, 102);
			label.setBackground(color);
			label.setOpaque(true);
			label.setAlignmentX(CENTER_ALIGNMENT);
			label.setAlignmentY(CENTER_ALIGNMENT);
			GridBagConstraints co = new GridBagConstraints();
			co.gridy = fila;
			co.gridx = col;
			co.weightx = 1;
			co.weighty = 1;
			co.insets = new Insets(1, 1, 1, 1);
			co.fill = GridBagConstraints.BOTH;
			panelHoy.add(label, co);
		}
		//colorearTabla(ocupadas);
	}
	
	public void colorearTabla(Integer[] porColorear) {
		for (int i = 0 ; i < porColorear.length ; i++) {
			int fila = (porColorear[i]/100)-1;
			int col = (porColorear[i]%100)-1;
			JLabel label = (JLabel) tablaHoy.getValueAt(fila, col);
			label.setBackground(Color.RED);
			tablaHoy.setValueAt(label, fila, col);
			((DefaultTableModel) tablaHoy.getModel()).fireTableDataChanged();
		}
	}
	
	public String[][] cargarHabitaciones(int lenArreglo, Integer[] habitaciones) {
		String[][] matriz;
		if (lenArreglo == 0) {
			matriz = new String[0][0];
		}
		else {
			int cantidadFilas = (habitaciones[lenArreglo-1])/100;
			int[] columnasXfila = new int[cantidadFilas];
			int cantidadColumnas = 0;
			for (int i = 0 ; i < lenArreglo ; i++) {
				int col = (habitaciones[i]%100);
				if (col > cantidadColumnas) {
					cantidadColumnas = col;
				}
			}			
			matriz = new String[cantidadFilas][cantidadColumnas];
//			System.out.println("Filas: " + cantidadFilas + ", Columnas: " + cantidadColumnas + ".");
//			System.out.println("Len arreglo: " + lenArreglo);
		}		
		
		for (int i = 0 ; i < lenArreglo ; i++) {
			int fila = (habitaciones[i]/100)-1;
			int col = (habitaciones[i]%100)-1;
			matriz[fila][col] = habitaciones[i] + "";
		}
		//System.out.println("Len matriz " + matriz.length);
//		for (int i = 0; i < 3; i++) {
//		    for (int j = 0; j < 1; j++) {
//		        System.out.println(matriz[i][j] + " ");}}
		return matriz;
	}
	
	public Integer[] getHabitaciones() {
		Integer[] habitaciones = windowManager.getHabitacionesContenedor();
		return habitaciones;
	}
	
	public Integer[] getOcupadas() {
		Integer[] ocupadas = windowManager.ocupacionHoy();
		return ocupadas;
	}
	public void volverReserva() {
		windowManager.mostraVentana(reservasFrame);
	}
	public void volverHabitaciones() {
		((AdminHabitacionesFrame) habitacionesFrame).resetDatos();
		habitacionesFrame.revalidate();
		windowManager.mostraVentana(habitacionesFrame);
	}
	
	public void resetDatos() {
		revalidate();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	
		switch (e.getActionCommand()) {
		case "Tarifas":
			windowManager.mostraVentana(tarifasFrame);

			break;
		case "Servicios":

			windowManager.mostraVentana(serviciosFrame);
			break;
		case "Habitaciones":

			windowManager.mostraVentana(habitacionesFrame);
			break;
		case "Restaurante":

			windowManager.mostraVentana(restauranteFrame);
			break;
		case "Reservas":
			windowManager.mostraVentana(reservasFrame);
			break;
		case "Cerrar sesion":
			windowManager.cerrarSesion();;

			break;
			
		case "Check-In":
			checkIn();
			break;
			
		case "Check-Out":
			checkOut();
			break;
			
		case "Refrescar ocupacion diaria":
			ocupacionHoy();
			break;
			
		case "Refrescar ocupacion anual":
			ocupacionAnual();
			break;

		default:
			break;
		}
	}


}
