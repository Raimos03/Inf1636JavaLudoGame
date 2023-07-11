package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import javax.swing.*;


public class PainelPlayer extends JPanel {
	// painel que mostra a cor do player
	
	
	Color vCores[] = {Color.red, Color.green, Color.yellow, Color.blue} ;
	String vStringCores[]= {"vermelho","verde","amarelo","azul"};
	int IndCor=0;
	Color cor;

	int x,y,larg,alt;

	
	PainelPlayer (){
		this.cor = vCores[IndCor];
	}
	
	PainelPlayer ( int x, int y, int larg, int alt){
		this.cor=Color.red;
		this.x=x;
		this.y=y;
		this.larg=larg;
		this.alt=alt;		
	}
	
	
	public Color getColor() {
		return this.cor;
	}
	public String getSCorPlayerVez() {
		return this.vStringCores[IndCor];
	}
	
	public void setCor(int i) {
		this.cor=vCores[i];	
		this.IndCor=i;
		//this.setBackground(this.cor);
		return;
	}
	
	public void setCor(Color c) {
		this.cor=c;
		//this.setBackground(c);
		return;
	}

	public void proximaCorPlayer() {	
		int novo= this.IndCor+1;
		
		if (novo>=4) {
			novo=0;
		}	
		
		this.IndCor=novo;	
		this.setCor(novo);
		
		return ;
	}
	public void ExibePainel() {
		this.setBackground(this.cor);
	}

	
	
	
}
