package View;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.*;

class Dado_graf extends JPanel {
	
	
	
	public static int Face;
	public int tamanho=125;
	public int X=280;
	public int Y=255;
	
	Image imagem1 = new ImageIcon("./Images/Dado1.png").getImage();
	Image imagem2 = new ImageIcon("./Images/Dado2.png").getImage();
	Image imagem3 = new ImageIcon("./Images/Dado3.png").getImage();
	Image imagem4 = new ImageIcon("./Images/Dado4.png").getImage();
	Image imagem5 = new ImageIcon("./Images/Dado5.png").getImage();
	Image imagem6 = new ImageIcon("./Images/Dado6.png").getImage();
	
	Image imagem1_scale = imagem1.getScaledInstance(tamanho, tamanho, Image.SCALE_DEFAULT);
	Image imagem2_scale = imagem2.getScaledInstance(tamanho, tamanho, Image.SCALE_DEFAULT);
	Image imagem3_scale = imagem3.getScaledInstance(tamanho, tamanho, Image.SCALE_DEFAULT);
	Image imagem4_scale = imagem4.getScaledInstance(tamanho, tamanho, Image.SCALE_DEFAULT);
	Image imagem5_scale = imagem5.getScaledInstance(tamanho, tamanho, Image.SCALE_DEFAULT);
	Image imagem6_scale = imagem6.getScaledInstance(tamanho, tamanho, Image.SCALE_DEFAULT);
	

	public void pinta_dado(int n) {
		//Adapts g for better resourses
		
//		//Square builder
//		if(cor == "white") {
//			//Draw red square
//			g2D.setColor(Color.white);
//			//g2D.drawRect(1200, 500, 150, 150);
//			g2D.fillRect(980, 450 ,150, 150);
//		}
//		else if(cor == "red") {
//			//Draw red square
//			g2D.setColor(Color.red);
//			//g2D.drawRect(1200, 500, 150, 150);
//			g2D.fillRect(980, 450 ,150, 150);
//		}
//		
//		else if(cor == "yellow") {
//			//Draw red square
//			g2D.setColor(Color.yellow);
//			//g2D.drawRect(1200, 500, 150, 150);
//			g2D.fillRect(980, 450 ,150, 150);
//		}
//		
//		else if(cor == "blue") {
//			//Draw red square
//			g2D.setColor(Color.blue);
//			//g2D.drawRect(1200, 500, 150, 150);
//			g2D.fillRect(980, 450 ,150, 150);
//		}
//		
//		else if(cor == "green") {
//			//Draw red square
//			g2D.setColor(Color.green);
//			//g2D.drawRect(1200, 500, 150, 150);
//			g2D.fillRect(980, 450 ,150, 150);
//		}
//		
		//Dice builder
	
		//paint(super.getGraphics(),n);
		
		Dado_graf.setFace(n);
		
	}
	
	public void paint(Graphics graphics) {
	
		//Adapts g for better resourses
		Graphics2D g2D = (Graphics2D) graphics;
		
		//gray bar
		//g2D.setColor(Color.gray);
		//g2D.fillRect(900, 0 , 700, 800);
	
		if(Face == 1) {
			g2D.drawImage(imagem1_scale, X,Y, null);
		}
		
		else if(Face == 2) {
			g2D.drawImage(imagem2_scale, X,Y, null);
		}
		
		else if(Face == 3) {
			g2D.drawImage(imagem3_scale, X,Y, null);
		}
		
		else if(Face == 4) {
			g2D.drawImage(imagem4_scale, X,Y, null);
		}
		
		else if(Face == 5) {
			g2D.drawImage(imagem5_scale,X, Y, null);
		}
		
		else if(Face == 6) {
			g2D.drawImage(imagem6_scale, X,Y, null);
		}

	}
	
	public static void setFace(int n) {	
		Dado_graf.Face=n;
	}
	public static int getFace() {	
		return Dado_graf.Face;
	}
	
}