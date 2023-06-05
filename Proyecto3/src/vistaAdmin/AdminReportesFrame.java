package vistaAdmin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

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
		
		panelVolver.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 65));
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
	    panelGraficos.setLayout(new GridLayout());
	    GridBagConstraints constraints = new GridBagConstraints();

	    constraints.weightx = 1;
	    constraints.weighty = 1;
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

	    // Obtener la trama del gráfico
	    CategoryPlot plot1 = grafico1.getCategoryPlot();

	    // Obtener el renderizador de barras
	    BarRenderer renderer1 = (BarRenderer) plot1.getRenderer();

	    // Aplicar un estilo personalizado a las barras
	    renderer1.setSeriesPaint(0, Color.BLUE); // Cambiar el color de las barras
	    
	    ChartPanel panelGrafico1 = new ChartPanel(grafico1);
	    constraints.gridx = 0;
	    constraints.gridy = 0;

	    panelGraficos.add(panelGrafico1, constraints);

	    // Grafico 2
	    JFreeChart grafico2 = ChartFactory.createBarChart("Unidades vendidas", "Producto", "Cantidad",
	            dataset2, PlotOrientation.VERTICAL, false, true, false);

	    // Obtener la trama del gráfico
	    CategoryPlot plot2 = grafico2.getCategoryPlot();

	    // Obtener el renderizador de barras
	    BarRenderer renderer2 = (BarRenderer) plot2.getRenderer();

	    // Aplicar un estilo personalizado a las barras
	 // Aplicar un gradiente de color a las barras
        GradientPaint gradientPaint = new GradientPaint(0, 0, Color.BLUE, 0, 0, Color.blue);
        renderer2.setSeriesPaint(0, gradientPaint);

	    ChartPanel panelGrafico2 = new ChartPanel(grafico2);
	    constraints.gridx = 1;
	    constraints.gridy = 0;

	    panelGraficos.add(panelGrafico2, constraints);
	    
	    pack();
	    repaint();
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
