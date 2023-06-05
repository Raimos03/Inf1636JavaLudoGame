package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.geom.Point2D;

import javax.swing.JPanel;

public class JTriangulo extends JPanel  {

	private Point2D.Float p1;
	private Point2D.Float p2;
	private Point2D.Float p3;
	private Color cor;
	
	JTriangulo(Point2D.Float p, Point2D.Float q, Point2D.Float r, Color c){
		
		this.p1=p;
		this.p2=q;
		this.p3=r;
		this.cor=c;
		
	}


	@Override
	public void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		
		super.paintComponent(g);	
		Graphics2D g2d=(Graphics2D) g;
		
		Polygon t1 = new Polygon();
		
		t1.addPoint((int)p1.x,(int)p1.y);
		t1.addPoint((int)p2.x,(int)p2.y);
		t1.addPoint((int)p3.x,(int)p3.y);
		
		g2d.setPaint(cor);
		g2d.fill(t1);
		g2d.setPaint(Color.black);
		g2d.draw(t1);
		
	}

}
