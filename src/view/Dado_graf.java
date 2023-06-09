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
	
	Image imagem0;
	Image imagem1;
	Image imagem2;
	Image imagem3;
	Image imagem4;
	Image imagem5;
	Image imagem6;
	Image imagemInicial;

	Image imagem0_scale;
	Image imagem1_scale;
	Image imagem2_scale;
	Image imagem3_scale;
	Image imagem4_scale;
	Image imagem5_scale;
	Image imagem6_scale;
	Image imagemInicial_scale;
	
	Dado_graf(){
		try {
			imagemInicial = ImageIO.read(new File("./Images/DadoInicial.png"));
			imagem0=ImageIO.read(new File("./Images/DadoFim.png"));
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
		}
		imagem0_scale = imagem0.getScaledInstance(tamanho, tamanho, Image.SCALE_DEFAULT);
		imagem1_scale = imagem1.getScaledInstance(tamanho, tamanho, Image.SCALE_DEFAULT);
		imagem2_scale = imagem2.getScaledInstance(tamanho, tamanho, Image.SCALE_DEFAULT);
		imagem3_scale = imagem3.getScaledInstance(tamanho, tamanho, Image.SCALE_DEFAULT);
		imagem4_scale = imagem4.getScaledInstance(tamanho, tamanho, Image.SCALE_DEFAULT);
		imagem5_scale = imagem5.getScaledInstance(tamanho, tamanho, Image.SCALE_DEFAULT);
		imagem6_scale = imagem6.getScaledInstance(tamanho, tamanho, Image.SCALE_DEFAULT);
		imagemInicial_scale =imagemInicial.getScaledInstance(tamanho, tamanho, Image.SCALE_DEFAULT);
		
	}
	
	public void GeraDado(int n, Graphics2D g2d) {
	
		if (Face == -1) {
			
			g2d.drawImage(imagemInicial_scale, X,Y, null);
		}
		else if (Face == 0) {
			g2d.drawImage(imagem0_scale, X,Y, null);
		}
		
		else if(Face == 1) {
			g2d.drawImage(imagem1_scale, X,Y, null);
		}
		
		else if(Face == 2) {
			g2d.drawImage(imagem2_scale, X,Y, null);
		}
		
		else if(Face == 3) {
			g2d.drawImage(imagem3_scale, X,Y, null);
		}
		
		else if(Face == 4) {
			g2d.drawImage(imagem4_scale, X,Y, null);
		}
		
		else if(Face == 5) {
			g2d.drawImage(imagem5_scale,X, Y, null);
		}
		
		else if(Face == 6) {
			g2d.drawImage(imagem6_scale, X,Y, null);
		}
		return ;
	}
	
	public void paint(Graphics graphics) {
	
		Graphics2D g2D = (Graphics2D) graphics;
		//g2D.drawImage(imagemInicial_scale, X,Y, null);	
		GeraDado(Dado_graf.Face, g2D);
		
	}
	
	public void Inicia() {
		Dado_graf.setFace(-1);
		return;
	}
	
	public void AtualizaImagem(int n) {
		Dado_graf.Face=n;
		this.GeraDado(n,(Graphics2D) this.getGraphics());
	}

	public static void setFace(int n) {	
		Dado_graf.Face=n;
	}
	public static int getFace() {	
		return Dado_graf.Face;
	}


	
}