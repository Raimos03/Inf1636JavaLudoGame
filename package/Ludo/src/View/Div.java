package View;

import javax.swing.*;
import java.awt.*;
import java.awt.geom. *;
import java.awt.Color;
import java.awt.Graphics2D;

public class Div extends JPanel { // Quadrado pequeno Tabuleiro
		
	public float largura;
	public float altura;
	
	private float pX ;
	private float pY ;
	private Color BCor = Color.orange;
	
	
	Div(float x, float y , float largura , float altura ){	
		this.pX= (float) x;
		this.pY=(float) y;
		
		this.largura=(float)largura;
		this.altura=(float) altura;
		this.setBackground(BCor);
	}
	
	Div(float x, float y ,float largura , float altura, Color cor){	
		this.pX=(float) x;
		this.pY=(float) y;	
		this.BCor=cor;
		
		this.largura=(float) largura;
		this.altura=(float) altura;
		this.setBackground(BCor);

	}
		
	public void paintComponent(Graphics g) {	
		
		super.paintComponent(g);	
		Graphics2D g2d=(Graphics2D) g;
		
		//Retangulo	
//		Rectangle2D rt = new Rectangle2D.Float(100,100,150,150);	
//		g2d.setPaint(Color.orange);
//		g2d.fill(rt);
				
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
	public float getLargura() {
		return largura;
	}
	public float getAltura() {
		return altura;
	}
	public void setpY(float pY) {
		this.pY = pY;
	}
	public void setColor(Color c) {
		this.BCor = c;
	}
	public Color getColor() {
		return BCor ;
	}
	public void setBColorFromColor() {
		this.setBackground(this.getColor());
	}
	
}
