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
<<<<<<< HEAD
			
=======
		
		
		
		//GerenciaRodada();
		
		
>>>>>>> master
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
		
		IPeao p =(IPeao) this.vPeao[i];
			
		int posAntiga =facade.getPosicaoPeao(p);
		int posTabuleiro= posAntiga;
		//posTabuleiro=posAntiga;
		
		
//		if(posTabuleiro==-1) {
//			posTabuleiro=0;
//		}
		
		double x;
		double y;
			
		ICoordenada[] vcoord =vTb.getvCasaComum();
		
		posTabuleiro = encontraNovaCasaTabuleiro(posTabuleiro, dado);
		
		ICoordenada nc= vcoord[posTabuleiro];
		x= nc.getX1()+5;
		y= nc.getY1()+5;
		
		facade.MovePeao(vPeao[i],x,y,posTabuleiro);
		
		p.Exibe();//debug
		
		
		
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
		
		//ncasa.ExibeStatus();
		
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

	public int GerenciaRodada() {
		
		fv.desabilitaBJogaDado();	
		
		int indicepeao = fv.getIndiceBotaoPeao();
		if (indicepeao ==-1) {
			indicepeao=0;
		}
		
		
		Object op =  vPeao[indicepeao];	
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
		
		System.out.println("Selecione o peao que deseja mover");
		
		System.out.println("Indice Botao Peao antes de validar--> "+indicepeao);
		
		indicepeao=ValidaSelecaoPeaoCor(indicepeao, facade.getCorPlayerVez());
		
		//indicepeao=-1; teste jogada nao valida
		if (indicepeao==-1){ // se a selecao nao condiz com a cor do player
		
			System.out.println("indice peao nao condiz");
			return 0;
		}
		
		else {
						
			rRegraI1=facade.VerificaRegrasI1(op, this.dadoRodada);
			
			if(rRegraI1==1){ // ok				
				System.out.println("Passou I1");				
				MovePeaoCasaInicial(indicepeao);
			}
			else {
				System.out.println("Regra I1 nao passou");
				
				if (rRegraI1==2) { // peao casa inicial			
					System.out.println("Peao na casa de saida");
					int indicePeaoCasaInicial= encontraPeaoCasaInicial(op); // peao de mesma cor
					MovePeao(indicePeaoCasaInicial,dadoRodada);				
					
				}
				else { // todos os 	outros caso 3					
					int indanterior = indicepeao;				
					System.out.println("RI1 Selecione um novo peao para mover com o valor do dado");					
					MovePeao(indicepeao,dadoRodada);
				}				
			}
			
			//rRegraB1=facade.VerificaRegrasB1();
			
			
			
			
			
			
			
		
			//---- por ultimo vencedor
			
			if(pVencedor!=-1) {// houve um vencedor	ultimo
				
				System.out.println("Houve um vencedor. Digite 1 para continuar ou 0 para terminar");
				Scanner sc = new Scanner(System.in);
				respContinueUsr = sc.nextInt();
				
				if(respContinueUsr==0) { // nao quer continuar
					
					// 1 - Exibe painel de vitoria
					
					System.out.println("Fechando o jogo");
					
				}
				else { // cotinuo e salvo o vencedor
					
					Vencedor[qtdVencedor]=facade.GetPlayerVez();
					pVencedor=-1;					
				}
				
				qtdVencedor++;
			}
		
		}
		
		//atualizo para o proximo jogador
		
		
		//fv.habilitaBotoesPeao(facade.getCorPlayerVez());
	
		//Notify();
		return 1;	
	}
	


	// ---------- Observadores 
	
	@Override 
	public void update() { // -----------------------------------turno ----------------
	
		// 1 - sorteio o dado
		// 2 - seleciono um peao valido
		// 3 - movo
		
		
		// botao de jogar dado foi clicado
		this.dadoRodada=5; //teste
		MovePeaoCasaInicial(0);	// teste casa saida peao na casa inicial
		MovePeaoCasaInicial(4);	// teste casa saida peao na casa inicial
		MovePeaoCasaInicial(8);	// teste casa saida peao na casa inicial
		MovePeaoCasaInicial(12);// teste casa saida peao na casa inicial
	
			
		System.out.println("Cliquei para sortear o dado");
		//this.dadoRodada=facade.JogaDado(); // joga o dado
		
		System.out.println(dadoRodada);
		//fv.setNumeroDado(dadoRodada); // atualizar imagem dado
		
		
		System.out.println("BPeao clicado:"+fv.getIndiceBotaoPeao());

		if(GerenciaRodada()==1) { // jogada valida, proximo player
		
			facade.ProximoJogador();				
			facade.NovoRound();
		
			
			fv.resetaIndiceBotaoClicado();	
			
			fv.AtualizaPainelPlayer();
			
		}
		
		else {
			System.out.println("Rodada nao valida, sorteie o dado novamente");	
			
		}
		//identifico o peao a ser movido
		
<<<<<<< HEAD
		
//		MovePeao(3,dadoRodada);	
//		MovePeao(fv.getIndiceBotaoPeao(),dadoRodada);
=======
		MovePeao(fv.getIndiceBotaoPeao(),dadoRodada);
>>>>>>> master
//		MovePeao(9,dadoRodada+2);
//		MovePeao(2,dadoRodada+2);
//		MovePeao(5,dadoRodada+4);
//		MovePeao(3,5);
//		MovePeao(0,5);
		
	
		fv.habilitaBJogaDado();
		//System.out.println("BPeao clicado:"+fv.getIndiceBotaoPeao());		
		
	}
	
	public int encontraPeaoCasaInicial(Object op) {
		
		IPeao p = (IPeao) op;
		int i;
		for(i=0;i<16;i++) {
			
			if (vPeao[i].equals(p)){
				return i;
			}
		}
		return 0;
	}
	
	public int ValidaSelecaoPeaoCor(int i,String cor){
		int inf;
		int sup;
		
		if(cor.equals("vermelho")) {
			inf =0;
			sup = 4;
		}
		else if (cor.equals("verde")) {
			inf =4;
			sup = 8;
		}
		else if (cor.equals("amarelo")) {
			inf =8;
			sup = 12;
		}
		else {// azul
			inf =12;
			sup = 16;
		}
		
		if (i>=inf &&i < sup) {
			
			return i;
		}
		
		return -1;
	}
	
	public void MovePeaoCasaInicial(int i) {
		
		
		int posTabuleiro=-1;
		ICoordenada select =null ;		
		
		IPeao p =(IPeao) this.vPeao[i];
			
		ICoordenada saidaVermelho = VTabuleiro.getCasaSaidaVermelho();
		ICoordenada saidaVerde = VTabuleiro.getCasaSaidaVerde();
		ICoordenada saidaAmarelo = VTabuleiro.getCasaSaidaAmarelo();
		ICoordenada saidaAzul =VTabuleiro.getCasaSaidaAzul();
		
		System.out.println("Cor peao:"+p.getCorId());
		
		if (p.getCorId()==0){
			
			posTabuleiro = 42; // 42 indice pos de vcasas correspondente
			select = saidaVermelho;
		}
		else if (p.getCorId()==1){
			select = saidaVerde;
			posTabuleiro = 3;
		}
		else if (p.getCorId()==2){
			select = saidaAmarelo;
			posTabuleiro = 16;
		}
		else {
			select = saidaAzul;
			posTabuleiro = 29;
		}
	
		double nx =select.getX1()+5;
		double ny =select.getY1()+5;	
		
		facade.MovePeao(p,nx,ny, posTabuleiro);
		
		//System.out.println( posTabuleiro);
		System.out.println("\tposicao setada");
		System.out.println("\t x:" + nx+ "n\t y:"+ny);
		

		facade.setCasaInicialPeao(p, false);
		facade.setCasaSaidaPeao(p, true);
		
		
		//posTabuleiro=-1;
		
		fv.setPosicaoBotoesPeoes(i,(int)nx,(int) ny);
		Notify();
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
