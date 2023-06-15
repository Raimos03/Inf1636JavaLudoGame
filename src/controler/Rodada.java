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
	public Object[] vCasas;


	
	public Rodada( FrameView fv,FcModel fc) { // controle o andamento do jogo
			
		// acesso tudo de model pelo facade (FcModel)
		
		this.facade=fc;
		facade.IniciaJogo();
		vCasas=facade.getVcasas();
		
		
		vPlayers= fc.getVplayers();
		
		this.fv=fv;
		vPeao =  fc.getVpeoes();
		vTb = fv.getVTabuleiro();
		vTb.setVpeoes(vPeao);
		vTb.setVcasas(vCasas);
		
		vTb.addObserver(this);
		fv.addObserver(this);	
		facade.CriaDado();
		
//		fv.InicializaVBotoes();
		
		facade.setCasaInicial(VTabuleiro.XPosicaoInicialVermelho,VTabuleiro.YPosicaoInicialVermelho,VTabuleiro.XPosicaoInicialVerde,VTabuleiro.YPosicaoInicialVerde,VTabuleiro.XPosicaoInicialAmarelo,VTabuleiro.YPosicaoInicialAmarelo,VTabuleiro.XPosicaoInicialAzul,VTabuleiro.YPosicaoInicialAzul);
		
		
		//MovePeao(1,dadoRodada);
		
		//vTb.ExibeVpeao();
		facade.ExibeVpeoes();

		
		
		
		
		
		// Enquando nao ha um vencedor
		
		

	
	}


	@Override
	public void update() {
		// TODO Auto-generated method stub
		System.out.println("Cliquei para sortear o dado");
		this.dadoRodada=facade.JogaDado();
		System.out.println(dadoRodada);
		fv.setNumeroDado(dadoRodada); // atualizar imagem dado
		
		//identifico o peao a ser movido
		
		MovePeao(8,dadoRodada);
		MovePeao(9,dadoRodada+2);
		MovePeao(2,dadoRodada+2);
		MovePeao(5,dadoRodada+4);
		MovePeao(3,5);
		MovePeao(0,5);
		MovePeao(3,dadoRodada);
		MovePeao(13,dadoRodada+2);
		MovePeao(11,dadoRodada+5);
		MovePeao(10,dadoRodada+3);
		MovePeao(7,dadoRodada+12);
		MovePeao(1,dadoRodada+6);
		MovePeao(15,dadoRodada+12);
		MovePeao(12,dadoRodada+7);
	}
	
	public int encontraNovaCasaTabuleiro(int posicaoantiga, int dado) {		
		if(posicaoantiga+dado>51) {
			posicaoantiga=(posicaoantiga+dado)-51-1;		
		}		
		else {
			posicaoantiga+=dado;
		}		
		return posicaoantiga;
	}
	
	public void MovePeao(int i,int dado){ // numero do peao no vetor de peoes e numero do dado
		
		IPeao p =(IPeao) vPeao[i];
	
		
		int posAntiga =facade.getPosicaoPeao(p);
		int posTabuleiro= posAntiga;
		//posTabuleiro=posAntiga;
		
		
		if(posTabuleiro==-1) {
			posTabuleiro=0;
		}
		
		double x;
		double y;
			
		ICoordenada[] vcoord =vTb.getvCasaComum();
		
		posTabuleiro = encontraNovaCasaTabuleiro(posTabuleiro, dado);
		
		ICoordenada nc= vcoord[posTabuleiro];
		x= nc.getX1()+5;
		y= nc.getY1()+5;
		
		facade.MovePeao(vPeao[i],x,y,dado,posTabuleiro);
		p.Exibe();
		
		//vTb.dBarreiraMesmaCor((Graphics2D) vTb.getGraphics(), (int) x,(int) y, p.getCor());
		
		// atualiza casa
		
		
		ICasa antcasa;
		
		if(posAntiga!=-1) {	
			antcasa = (ICasa) vCasas[posAntiga];
			antcasa.DecrementaPeaoCasa();
			
			if (antcasa.getQtdPeao()==0) {
				antcasa.eCasaZerada();
			}
			
			antcasa.ExibeStatus();
		}
		
		
		
		
		ICasa ncasa = (ICasa) vCasas[posTabuleiro];
		
		if (ncasa.getTemPeao()) {
			ncasa.setCasaBarreira(true);
			ncasa.setCorPeao(1, p.getCor());
			
		}
		else {
			ncasa.setTemPeao(true);
			ncasa.setCorPeao(0, p.getCor());
		}
		
		ncasa.IncrementaPeaoCasa();
		int n=ncasa.getQtdPeao();
		
		ncasa.ExibeStatus();
		fv.setPosicaoPeoesBotoes(i,(int)x,(int) y);
		
		vTb.repaint();
		fv.repaint();
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
