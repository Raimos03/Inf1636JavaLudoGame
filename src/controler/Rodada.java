package controler;

import view.*;
import java.util.Scanner;
import model.FcModel;
import java.awt.Color;
import java.awt.Graphics2D;

import javax.swing.JLayeredPane;
import java.util.ArrayList;


public class Rodada implements Observador { 
	//Controler
	
	
	public static int nRodada =0;
	public int dadoRodada; // ver se realmente preciso
	public FcModel facade;
	public FrameView fv;
	//public JLayeredPane layer; // ver
	public VTabuleiro vTb; // view tabuleiro
	public Object[] vPlayers;
	public Object[] vPeao;

	
	public Rodada( FrameView fv,FcModel fc) { // controle o andamento do jogo
			
		// acesso tudo de model pelo facade (FcModel)
		
		this.facade=fc;
		facade.IniciaJogo();
		
		
		vPlayers= fc.getVplayers();
		
		this.fv=fv;
		vPeao =  fc.getVpeoes();
		vTb = fv.getVTabuleiro();
		vTb.setVpeoes(vPeao);
		vTb.addObserver(this);
		fv.addObserver(this);	
		facade.CriaDado();
		
//		fv.InicializaVBotoes();
		
		facade.setCasaInicial(VTabuleiro.XPosicaoInicialVermelho,VTabuleiro.YPosicaoInicialVermelho,VTabuleiro.XPosicaoInicialVerde,VTabuleiro.YPosicaoInicialVerde,VTabuleiro.XPosicaoInicialAmarelo,VTabuleiro.YPosicaoInicialAmarelo,VTabuleiro.XPosicaoInicialAzul,VTabuleiro.YPosicaoInicialAzul);
		
		
		//MovePeao(1,dadoRodada);
		
		vTb.ExibeVpeao();

		
		
		
		
		
		// Enquando nao ha um vencedor
		
		

	
	}


	@Override
	public void update() {
		// TODO Auto-generated method stub
		System.out.println("Cliquei para sortear o dado");
		this.dadoRodada=facade.JogaDado();
		System.out.println(dadoRodada);
		fv.setNumeroDado(dadoRodada); // atualizar imagem dado
		MovePeao(8,dadoRodada);
	}
	
	
	public void MovePeao(int i,int dado){ // numero do peao no vetor de peoes e numero do dado
		
		IPeao p =(IPeao) vPeao[i];
		
		
		int posTabuleiro=p.getPosicao();
		if(posTabuleiro==-1) {
			posTabuleiro=0;
		}
		
		double x;
		double y;
			
		ICoordenada[] vcoord =vTb.getvCasaComum();
		
		if(posTabuleiro+dado>=52) {
			posTabuleiro=(posTabuleiro+dado)-52;		
		}
		
		else {
			posTabuleiro+=dado;
		}
		
		ICoordenada nc= vcoord[posTabuleiro];
		x= nc.getX1()+5;
		y= nc.getY1()+5;
		
		facade.MovePeao(vPeao[i],x,y,dado);
		
		vTb.repaint();
	}

	
}

// codigo Rodada antiga

//Basic objects
//Testes iniciais

//Round round = new Round();
//Dado dice = new Dado();
//Regra regra = new Regra();
//
////Players
//Player player = new Player("vermelho");
//
//
//  
//Player 1 turn p = round.player_turn; dice.joga_dado();
//System.out.println("Player " + "\n" + "Die Throw: " + dice.face +
//"Regra de casa inicial" + regra.regraB1(player.getPeao1(),dice.face));
