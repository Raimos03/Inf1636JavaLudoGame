package view;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;

public class Poligono  extends JPanel  {
	
	// Divisoria
	private int largura;
	private int altura;
	private int posX;
	private int posY;
	private Color Cor = Color.green;
	
	
	Poligono(int posX, int posY ,int larg, int alt, Color Cor){		
		this.largura=larg;
		this.altura=alt;	
		this.posY=posY;
		this.posX=posX;
		this.Cor=Cor;
	}
	
	Poligono(int posX, int posY ,int larg, int alt){		
		this.largura=larg;
		this.altura=alt;	
		this.posY=posY;
		this.posX=posX;
	}
	
	
	public void paintComponent(Graphics g) {
			
			super.paintComponent(g);
			Graphics2D g2d=(Graphics2D) g;
			
			//Retangulo
			
			Rectangle2D rt = new Rectangle2D.Float(posX,posY,this.largura,this.altura);
			g2d.setPaint(this.Cor);
			g2d.fill(rt);
			
	}

	public int getLargura() {
		return largura;
	}

	public void setLargura(int largura) {
		this.largura = largura;
	}

	public int getAltura() {
		return altura;
	}

	public void setAltura(int altura) {
		this.altura = altura;
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	
}
