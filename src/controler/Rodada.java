package controler;

import view.*;
import java.util.Scanner;
import model.FcModel;
import java.awt.Color;
import java.awt.Graphics2D;

import javax.swing.JFileChooser;
import java.util.ArrayList;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class Rodada implements Observador, Observado { 
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
	
	public JBotaoFill jbSalvar;
	public JBotaoFill jbLoad;
	public JBotaoFill jbNovoJogo;
	public PainelPlayer pplayer;
	
	
	private ArrayList<Observador> lobs = new ArrayList<>(); 


	
	public Rodada( FrameView fv,FcModel fc) { // controle o andamento do jogo

		
		// acesso tudo de model pelo facade
		
		CarregaInicio(fv, fc);
		
		
		
		//GerenciaRodada();
		
		
		//vTb.ExibeVpeao();
		//facade.ExibeVpeoes();
		
		
		// --- Event Listener
		
		jbSalvar.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            JFileChooser fileChooser = new JFileChooser();
	            int resultado = fileChooser.showSaveDialog(jbSalvar);
	            if (resultado == JFileChooser.APPROVE_OPTION) {
	                String caminhoArquivo = fileChooser.getSelectedFile().getPath();
	                String conteudo = "teste";
	                
	                
	                JBotaoFill.salvarArquivo(caminhoArquivo, conteudo );
	            }
	        }
	    });
		
		 jbLoad.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                JFileChooser fileChooser = new JFileChooser();
	                int resultado = fileChooser.showOpenDialog(null);
	                if (resultado == JFileChooser.APPROVE_OPTION) {
	                    String caminhoArquivo = fileChooser.getSelectedFile().getPath();
	                    // Chamar o método carregarConteudoArquivo através da instância do JBotao
	                    String conteudo = JBotaoFill.carregaArquivo(caminhoArquivo);

	                    System.out.println(conteudo);
	                }
	            }
	        });
		 
		 jbNovoJogo.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	              
	            	System.out.println(" --- Iniciar Novo Jogo ---");
	            }
		 		
	        });
		
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
		p.Exibe();//debug
		
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
		fv.setPosicaoBotoesPeoes(i,(int)x,(int) y);
		
		Notify();
		
	}
	
	public void incializaBotoesPeoes() {
		
		int i;
		double x;
		double y;
		for(i=0;i<16;i++) {
			
			IPeao p =(IPeao) this.vPeao[i];
			x=p.getXY().getX1();
			y=p.getXY().getY1();
			fv.setPosicaoBotoesPeoes(i,(int)x,(int) y);
			
		}	
	}
	
	public void CarregaInicio(FrameView fv,FcModel fc) {
		
		this.facade=fc;
		facade.IniciaJogo();
		vCasas=facade.getVcasas();
			
		vPlayers= fc.getVplayers();
		
		this.fv=fv;
		vPeao =  fc.getVpeoes();
		vTb = fv.getVTabuleiro();
		vTb.setVpeoes(vPeao);
		vTb.setVcasas(vCasas);
		
		//vTb.addObserver(this);
		this.addObserver(vTb);
		fv.addObserver(this);	
		facade.CriaDado();	
		
		facade.setCasaInicial(VTabuleiro.XPosicaoInicialVermelho,VTabuleiro.YPosicaoInicialVermelho,VTabuleiro.XPosicaoInicialVerde,VTabuleiro.YPosicaoInicialVerde,VTabuleiro.XPosicaoInicialAmarelo,VTabuleiro.YPosicaoInicialAmarelo,VTabuleiro.XPosicaoInicialAzul,VTabuleiro.YPosicaoInicialAzul);
		incializaBotoesPeoes();	
		
		Object[] vBotoesMenu = fv.getvBotoesMenu();
		jbSalvar = (JBotaoFill) vBotoesMenu[0];
		jbLoad = (JBotaoFill) vBotoesMenu[1];
		jbNovoJogo = (JBotaoFill) vBotoesMenu[2];
		pplayer = (PainelPlayer) fv.getPainelPlayer();
	}

	public void GerenciaRodada() {
		
		fv.desabilitaBJogaDado();
		
		
		Object[] Vencedor = new Object[4];
		int pVencedor = -1; //player dif de -1 , -1 nao tem vencedor
		int respContinueUsr=1;  // 1 continuar o jogo , 0 sair
		int qtdVencedor=0;
		
		int rRegraI1=0;
		int rRegraB1=0;
		int rRegraB2=0;
		int rRegraB3=0;
		int rRegra6=0;
		int rRegraBR=0;
		int rRegraCA=0;
		
		
					
					
		// while(respContinueUsr!=0){			
					//turno
					
		
		
		
		if(pVencedor!=-1) {// houve um vencedor	ultimo
			
			System.out.println("Houve um vencedor. Digite 1 para continuar ou 0 para terminar");
			Scanner sc = new Scanner(System.in);
			respContinueUsr = sc.nextInt();
			
			if(respContinueUsr==0) {
				
				// 1 - Exibe painel de vitoria
				
				System.out.println("Fechando o jogo");
				
			}
			else { // cotinuo e salvo o vencedor
				
				Vencedor[qtdVencedor]=facade.GetPlayerVez();
				pVencedor=-1;					
			}
			
			qtdVencedor++;
		}
		
		
		
		//atualizo para o proximo jogador
		
		facade.ProximoJogador();				
		facade.NovoRound();
		fv.habilitaBJogaDado();
		
	
	 //} // fim while
		
			
	}
	


	// ---------- Observadores 
	
	@Override
	public void update() {
	
		// botao de jogar dado foi clicado
			
		System.out.println("Cliquei para sortear o dado");
		this.dadoRodada=facade.JogaDado();
		System.out.println(dadoRodada);
		fv.setNumeroDado(dadoRodada); // atualizar imagem dado
		
		
		System.out.println("BPeao clicado:"+fv.getIndiceBotaoPeao());
		
		
		GerenciaRodada();
		
		
		//identifico o peao a ser movido
		
//		MovePeao(fv.getIndiceBotaoPeao(),dadoRodada);
//		MovePeao(9,dadoRodada+2);
//		MovePeao(2,dadoRodada+2);
//		MovePeao(5,dadoRodada+4);
//		MovePeao(3,5);
//		MovePeao(0,5);
		
		
		
		
		fv.resetaIndiceBotaoClicado();
		
		//System.out.println("BPeao clicado:"+fv.getIndiceBotaoPeao());
		
		fv.AtualizaPainelPlayer();
		
	}

	@Override
	public void addObserver(Observador o) {
		// TODO Auto-generated method stub
		lobs.add(o);
	}


	@Override
	public void removeObserver(Observador o) {
		// TODO Auto-generated method stub
		lobs.remove(o);
	}

	@Override
	public void Notify() {
		// TODO Auto-generated method stub
		for( Observador obj : this.lobs) {
			obj.update();
		}
	}


	@Override
	public Object getDados() {
		// TODO Auto-generated method stub
		return null;
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

//Player 1 turn p = round.player_turn; dice.joga_dado();
//System.out.println("Player " + "\n" + "Die Throw: " + dice.face +
//"Regra de casa inicial" + regra.regraB1(player.getPeao1(),dice.face));
