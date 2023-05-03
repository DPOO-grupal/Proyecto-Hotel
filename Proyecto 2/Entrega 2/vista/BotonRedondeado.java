package vista;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicButtonUI;

public class BotonRedondeado extends JButton {
    
    private int x;
    private int y;
	private int arc;
    private Color color;
    
    public BotonRedondeado(String text, int x, int y, int arc, Color color) {
        super(text);
        this.color = color;
        this.x = x;
        this.y = y;
        this.arc = arc;
        setUI(new RoundedButtonUI());
        setPreferredSize(new Dimension(x, y));
        setOpaque(false);
        setBorderPainted(false);
    }
    
    public BotonRedondeado(String text) {
        super(text);
        this.arc = 30;
        this.color = Color.white;
        setUI(new RoundedButtonUI());
        setBorderPainted(false);
        setOpaque(true);
        
    }
    
    public void setBackground(Color color) {
    	this.color = color;
    	repaint();
    }
    
    private class RoundedButtonUI extends BasicButtonUI {
        
        @Override
        public void paint(Graphics g, JComponent c) {
            Graphics2D g2 = (Graphics2D) g;
            
            int x = 0;
            int y = 0;
            int w = c.getWidth();
            int h = c.getHeight();
            
            RoundRectangle2D rect = new RoundRectangle2D.Float(x, y, w, h, arc, arc);
//            g2.setColor(Color.black);
//            g2.fill(rect);
//            
//            RoundRectangle2D rect2 = new RoundRectangle2D.Float(x+2, y+2, w-4, h-4, arc, arc);
            g2.setColor(color);
            g2.fill(rect);
            
            
            super.paint(g, c);
        }
    }
}
