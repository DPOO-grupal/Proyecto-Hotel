package vistaAdmin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.DefaultXYDataset;
import org.jfree.data.xy.XYDataset;

import controlador.WindowManager;

public class AdminReportesFrame extends JFrame implements ActionListener {
	
	private WindowManager windowManager;
	private JPanel panelGraficos;

	public AdminReportesFrame(WindowManager windowManager) {
		this.windowManager = windowManager;
		setLayout(new BorderLayout());
		panelGraficos = new JPanel();
		panelGraficos.setBackground(Color.decode("#accaf2"));
		add(panelGraficos, BorderLayout.CENTER);
		
		addGraficos();
		
		JPanel panelVolver = new JPanel();
		panelVolver.setBackground(Color.white);
		add(panelVolver, BorderLayout.SOUTH);
		
		panelVolver.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 30));
	    panelVolver.setBackground(Color.decode ("#7E8C69"));

	    // Crear Boton redondeado
	    Font font = new Font("Arial", Font.BOLD, 20);
	    
	    JButton volverButton = new JButton("Volver");
	    volverButton.setPreferredSize(new Dimension(200, 60));
	    volverButton.setBackground(Color.decode("#D0ECF2"));
	    volverButton.setFont(font);
	    volverButton.addActionListener(this);
	    // Añadirlo al Panel
	    panelVolver.add(volverButton);
	}

	private void addGraficos() {
	    panelGraficos.setLayout(new GridBagLayout());
	    GridBagConstraints constraints = new GridBagConstraints();

	    constraints.weightx = 1;
	    constraints.weighty = 1;
	    constraints.ipadx = 1;
	    constraints.ipady = 1;
	    constraints.insets = new Insets(25, 25, 25, 25);
	    constraints.fill = GridBagConstraints.BOTH;

	    HashMap<String, int[]> productosDatos = windowManager.datosReporteProductos();
	    DefaultCategoryDataset dataset1 = new DefaultCategoryDataset();
	    DefaultCategoryDataset dataset2 = new DefaultCategoryDataset();

	    for (Entry<String, int[]> entry : productosDatos.entrySet()) {
	        int[] valores = entry.getValue();
	        String nombre = entry.getKey();
	        dataset1.setValue(valores[0], "Productos", nombre);
	        dataset2.setValue(valores[1], "Productos", nombre);
	    }

	    // Grafico 1
	    JFreeChart grafico1 = ChartFactory.createBarChart("Ganancia en ventas", "Producto", "Precio",
	            dataset1, PlotOrientation.VERTICAL, false, true, false);
	    grafico1.setBackgroundPaint(panelGraficos.getBackground());

	    CategoryPlot plot1 = grafico1.getCategoryPlot();
	    plot1.setBackgroundPaint(panelGraficos.getBackground());
	    plot1.setForegroundAlpha(1);
	    BarRenderer renderer1 = (BarRenderer) plot1.getRenderer();
	    renderer1.setSeriesPaint(0, Color.decode("#204473")); // Cambiar el color de las barras
	    
	    ChartPanel panelGrafico1 = new ChartPanel(grafico1);
	    //panelGrafico1.setPreferredSize(new Dimension(500, 500));
	    constraints.gridx = 0;
	    constraints.gridy = 0;

	    panelGraficos.add(panelGrafico1, constraints);

	    // Grafico 2
	    JFreeChart grafico2 = ChartFactory.createBarChart("Unidades vendidas", "Producto", "Cantidad",
	            dataset2, PlotOrientation.VERTICAL, false, true, false);

	    grafico2.setBackgroundPaint(panelGraficos.getBackground());
	    CategoryPlot plot2 = grafico2.getCategoryPlot();
	    plot2.setBackgroundPaint(panelGraficos.getBackground());
	    plot2.setForegroundAlpha(1);
	    BarRenderer renderer2 = (BarRenderer) plot2.getRenderer();
        renderer2.setSeriesPaint(0, Color.decode("#027373"));
        
	    ChartPanel panelGrafico2 = new ChartPanel(grafico2);
	    panelGrafico2.setPreferredSize(new Dimension(500, 500));

	    constraints.gridx = 1;  
	    constraints.gridy = 0;

	    panelGraficos.add(panelGrafico2, constraints);

	    // Grafico 3
	    XYDataset dataset3 = createDataset();
	    JFreeChart grafico3 = createLineChart(dataset3);
	    
	    grafico3.setBackgroundPaint(panelGraficos.getBackground());
	    XYPlot plot3 = grafico3.getXYPlot();
	    plot3.setBackgroundPaint(panelGraficos.getBackground());
	    plot3.setForegroundAlpha(1);
	    XYLineAndShapeRenderer renderer3 = (XYLineAndShapeRenderer) plot3.getRenderer();
	    renderer3.setSeriesPaint(0, Color.decode("#027373"));
        
	    ChartPanel panelGrafico3 = new ChartPanel(grafico3);
	    panelGrafico3.setPreferredSize(new Dimension(500, 500));

	    constraints.gridx = 1;  
	    constraints.gridy = 1;

	    panelGraficos.add(panelGrafico3, constraints);
	    
	 
	}

	private JFreeChart createLineChart(XYDataset dataset3) {
		JFreeChart chart = ChartFactory.createXYLineChart("Comparacion consumo Restaurante como valor por noche", "Restaurante", "Valor una noche", dataset3,
                PlotOrientation.VERTICAL, false, true, false);

        XYPlot plot = chart.getXYPlot();

        return chart;
	}

	private XYDataset createDataset() {
		System.out.println("AdminReportesFrame.createDataset()");
	    ArrayList<int[]> restauranteDatos = windowManager.datosReporteRestaurante();
        Collections.sort(restauranteDatos, Comparator.comparingInt(arr -> arr[0]));

	    double[][] data = new double[2][restauranteDatos.size()];

	    for (int i = 0; i < restauranteDatos.size(); i++) {
	    	int[] tem = restauranteDatos.get(i);
			System.out.println(tem[0] + ", " + tem[1]);
	    	
			data[0][i] = tem[0];
	    	data[1][i] = tem[1];
		}
	    	    
		DefaultXYDataset dataset = new DefaultXYDataset();

        dataset.addSeries("Serie 1", data);
        
        return dataset;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		windowManager.volverMenu();
		
	}

	public void resetDatos() {
		panelGraficos.removeAll();
		panelGraficos.revalidate();
		addGraficos();
		
	}

}
