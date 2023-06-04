package view;

import javax.swing.*;
import java.awt.*;
import java.awt.geom. *;

public class QuadradoCasa extends JPanel { // Quadrado pequeno Tabuleiro
		
	public static float largura= (float) 44.0; //44.0
	public static float altura= (float) 44.0;
	

	private float pX;
	private float pY;
	private Color Cor = Color.black;
	
	
	QuadradoCasa(float x, float y ){	
		this.pX=x;
		this.pY=y;	
	}
	
	QuadradoCasa(float x, float y , Color cor){	
		this.pX=x;
		this.pY=y;	
		this.Cor=cor;
	}
	
//	public void pintaQuadrado(Graphics g) {
//		
//		super.paintComponent(g);
//		Graphics2D g2d=(Graphics2D) g;
//			
//		//Quadrado
//		Rectangle2D rt = new Rectangle2D.Float(pX,pY,QuadradoCasa.largura,QuadradoCasa.altura);
//		g2d.setPaint(this.Cor);
//		g2d.fill(rt);
//				
//		Ellipse2D e1 = new Ellipse2D.Float(500, 500, 100, 100);
//		e1.setFrame(rt2);
//		g2d.setPaint(Color.orange);
//		g2d.fill(e1);
//		
//		
//	}
	
	@Override	
	public void paintComponent(Graphics g)	{ // toda a pintura do tabuleiro é feita aqui
			
		super.paintComponent(g);
		Graphics2D g2d=(Graphics2D) g;
				
		//Quadrado
		Rectangle2D rt = new Rectangle2D.Float(this.getpX(),this.getpY(),QuadradoCasa.largura,QuadradoCasa.altura);
		g2d.setPaint(this.Cor);
		g2d.fill(rt);
				
	}


	public void setLarguraEAltura(float larg , float alt) {	
		this.largura=larg;
		this.altura=alt;
		
		return;
	}
	
	public float getpX() {
		return pX;
	}
	public void setpX(float pX) {
		this.pX = pX;
	}
	public float getpY() {
		return pY;
	}
	public void setpY(float pY) {
		this.pY = pY;
	}
	public void setColor(Color c) {
		this.Cor = c;
	}

	public Color GetColor() {
		return this.Cor ;
	}
	
}
