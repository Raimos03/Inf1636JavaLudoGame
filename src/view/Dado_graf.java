package view;


import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;

import javax.swing.*;
import java.io.*;
import javax.imageio.*;


class Dado_graf extends JPanel {
	
	public static int Face;
	public int tamanho=125;
	// x 280, y 255
	public int X=280;
	public int Y=255;
	

//	Image imagem1 = new ImageIcon("./Images/Dado1.png").getImage();
//	Image imagem2 = new ImageIcon("./Images/Dado2.png").getImage();
//	Image imagem3 = new ImageIcon("./Images/Dado3.png").getImage();
//	Image imagem4 = new ImageIcon("./Images/Dado4.png").getImage();
//	Image imagem5 = new ImageIcon("./Images/Dado5.png").getImage();
//	Image imagem6 = new ImageIcon("./Images/Dado6.png").getImage();
	
	Image imagem1;
	Image imagem2;
	Image imagem3;
	Image imagem4;
	Image imagem5;
	Image imagem6;

	Image imagem1_scale;
	Image imagem2_scale;
	Image imagem3_scale;
	Image imagem4_scale;
	Image imagem5_scale;
	Image imagem6_scale;
	

	public void pinta_dado(int n) {
		//Adapts g for better resourses
		
		//Dice builder	
		//paint(super.getGraphics(),n);
		
		
		listaImagens();
		Dado_graf.setFace(n);
		
	}
	
	public void listaImagens() {				
		try {
			imagem1=ImageIO.read(new File("./Images/Dado1.png"));
			imagem2=ImageIO.read(new File("./Images/Dado2.png"));
			imagem3=ImageIO.read(new File("./Images/Dado3.png"));
			imagem4=ImageIO.read(new File("./Images/Dado4.png"));
			imagem5=ImageIO.read(new File("./Images/Dado5.png"));
			imagem6=ImageIO.read(new File("./Images/Dado6.png"));	
			
			
		}
		catch (IOException e) {			
			System.out.println(e.getMessage());
			System.exit(1);
		}				imagem1_scale = imagem1.getScaledInstance(tamanho, tamanho, Image.SCALE_DEFAULT);		imagem2_scale = imagem2.getScaledInstance(tamanho, tamanho, Image.SCALE_DEFAULT);		imagem3_scale = imagem3.getScaledInstance(tamanho, tamanho, Image.SCALE_DEFAULT);		imagem4_scale = imagem4.getScaledInstance(tamanho, tamanho, Image.SCALE_DEFAULT);		imagem5_scale = imagem5.getScaledInstance(tamanho, tamanho, Image.SCALE_DEFAULT);		imagem6_scale = imagem6.getScaledInstance(tamanho, tamanho, Image.SCALE_DEFAULT);
		
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