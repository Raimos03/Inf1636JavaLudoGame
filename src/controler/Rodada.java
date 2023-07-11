package controler;

import view.*;
import java.util.Scanner;
import model.FcModel;
import java.awt.Color;
import java.awt.Graphics2D;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import java.util.ArrayList;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class Rodada implements Observador, Observado, Observador2 { 
	//Controler
	
	
	public int control_cor=0; //**
	
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

	
	//public Object dado;
	public int teste=3;
	
	
	private ArrayList<Observador> lobs = new ArrayList<>(); 


	
	public Rodada( FrameView fv,FcModel fc) { // controle o andamento do jogo

		
		// acesso tudo de model pelo facade
		
		CarregaInicio(fv, fc);
			
		//vTb.ExibeVpeao();
		//facade.ExibeVpeoes();
		

		
		// --- Event Listener
	
	jbSalvar.addActionListener(new ActionListener() {
      	public void actionPerformed(ActionEvent e) {
      		String conteudo = "";
      		for(int i = 0; i<16; i++){
      			IPeao p =(IPeao) vPeao[i];
      			conteudo += p.getPosicao() + "\n";
      		}
      		conteudo += facade.getRound()+"\n";
      		
            JFileChooser arq = new JFileChooser();
            int resultado = arq.showSaveDialog(jbSalvar);
            if (resultado == JFileChooser.APPROVE_OPTION) {
                String caminhoArquivo = arq.getSelectedFile().getPath();
                JBotaoFill.salvarArquivo(caminhoArquivo, conteudo );
            }
            JOptionPane.showMessageDialog(fv,"Save concluido com sucesso");
        }
	    });
		
	jbLoad.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
        	
			fc.resetaCasaInfo();
			
        	JFileChooser arq = new JFileChooser();
            int resultado = arq.showOpenDialog(null);
            if (resultado == JFileChooser.APPROVE_OPTION) {
            	String caminho = arq.getSelectedFile().getPath();
            	String conteudo = JBotaoFill.carregaArquivo(caminho);
            	String vec[] = conteudo.split("\n");
            	
            	
            	for(int i= 0; i<16; i++) {
            		IPeao p =(IPeao) vPeao[i];
            		if(Integer.valueOf(vec[i])==-1) {
            			MovePeaoCasaInicialIND(i);
            			
            		}
            		else {
            			int aux = Integer.valueOf(vec[i]);
            			while(p.getPosicao()!=aux) {
            				
            				int posAntiga=p.getPosicao();
            				MovePeao(i,1);
            				
            				
            			}
            		}
            	}
            	
            	
            	facade.setIntPlayerVez(Integer.valueOf(vec[16]));        	
            	//fv.setCorPainelPlayer(Integer.valueOf(vec[16]));
            	//fv.ExibePainelPlayer();
            	
            	JOptionPane.showMessageDialog(fv,"Load Concluido com sucesso");
            	
            	VerificaStatusCasas();
            	
            	String scor = fc.getCorPlayerVez();
            	
            	System.out.println( scor);	
            	System.out.println(fv.getCorPainelPlayer());	
           
            	
            	while(scor.equals(fv.getCorPainelPlayer())==false) {
            		fv.AtualizaPainelPlayer();
            		System.out.println(fv.getCorPainelPlayer());
            	}
            	fv.resetaIndiceBotaoClicado();
            	
            }

	      }
	 });
		 
	jbNovoJogo.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
            System.out.println("Reiniciando Jogo...");


            fv.setCorPainelPlayer(0);
            ReiniciaJogada();
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
		//facade.setRodada(this);
		
				
		this.fv=fv;
		vCasas=facade.getVCasas();
		
		vPeao =  fc.getVpeoes();
		vTb = fv.getVTabuleiro();
		vTb.setVpeoes(vPeao);
		
		
		vTb.setVcasas(vCasas);
		
		
		//vCasas=vTb.getVcasas();
		
		
		vPlayers= fc.getVplayers();
		
		//this.dado=fc.getDadoObject();
		
		
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
		
		
		
		fv.setRodada(this);
		//fv.setObserverRdJBotao(this);
		
		
	}

	public int GerenciaRodada() {
		
		//fv.desabilitaBJogaDado();			
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
		
		int ci=0;
		
		System.out.println("Selecione o peao que deseja mover");	
		System.out.println("Indice Botao Peao antes de validar--> "+indicepeao);
		
		indicepeao=ValidaSelecaoPeaoCor(indicepeao, facade.getCorPlayerVez());
		
		System.out.println("Indice Botao Peao depois  de validar--> "+indicepeao);
		
		//indicepeao=-1; teste jogada nao valida
		if (indicepeao==-1){ // se a selecao nao condiz com a cor do player	
			System.out.println("indice peao nao condiz");
			return 0;
		}
		
		else { // indice peao valido
				
				IPeao p = (IPeao) vPeao[indicepeao];
				rRegraI1=facade.VerificaRegrasI1(op, this.dadoRodada);		
				
				if(rRegraI1==1){ // ok		// se  tem peao na casa de saida e tirou  5
					ci=4;
					System.out.println("Passou I1");	
					System.out.println("Peao na casa de saida");
					
					ci=1;
					String scorplayer = facade.getCorPlayerVez();
					int pinicialTab;					
					if (scorplayer.equals("vermelho")) {
						pinicialTab=42;
					}
					else if (scorplayer.equals("verde")) {
						pinicialTab=3;
					}
					else if (scorplayer.equals("amarelo")) {
						pinicialTab=16;
					}
					else { // azul
						pinicialTab=29;
					}					
					//encontro indice do peao com a coordenada
					
					int indicePeaoCasaSaida= encontraIPeaoCasaSaida(pinicialTab); // peao de mesma cor							
					
					if (indicePeaoCasaSaida!=-1){
						MovePeao(indicePeaoCasaSaida,dadoRodada);	// tiro peao casa saida
					}
				
				}
				else { // n tirou 5 ou casa de saida livre
					
					System.out.println("Regra I1 nao passou>> return "+rRegraI1);
				
					
					if (rRegraI1==2) { // sem peao na casa de saida			
						ci=3;
						
						System.out.println("sem Peao na casa de saida"); // ok
						System.out.println("\t >>> Dado e info :" + dadoRodada);
						
						
						//encontra novo peao casa inicial e sai
						
						System.out.println("RI1 r2 Cor player vez:"+ facade.getCorPlayerVez());
						
						indicepeao =  ValidaSelecaoPeaoCor(indicepeao,facade.getCorPlayerVez());
						IPeao p1 = (IPeao) vPeao[indicepeao];
						
						int nind;
						int corvez=p.getIntCor();
						int indrelativo=indicepeao%4;
						int min,max;						
						
						if (indicepeao!=-1) {
							
							if (corvez==0) { //0 a3
								min=0;
								max=3;
								nind=indrelativo;							
							}
							else if (corvez==1) { //4 a7
								nind=4+indrelativo;
								min=4;
								max=7;
								
							}
							else if (corvez==2) {//8 a11
								nind=8+indrelativo;
								min=8;
								max=11;
							}
							else {//12 a 15
								nind=12+indrelativo;
								min=12;
								max=15;
							}
							
							IPeao p2 = (IPeao) vPeao[nind];	
							if(p1.getId()==p2.getId()) {
							
								for(int i=0;i<4;i++) {
									
									p2= (IPeao) vPeao[i+min];
									if(p2.isCasaInicial()) {
										if(p1.getId()!=p2.getId()) {
											nind=i+min;
											break;
										}
									}
								}
								
							}
														
							System.out.println("RI1 r2 Novo indice a mover:"+ nind);
							MovePeaoCasaSaida(nind);
						}
						
						else {
							System.out.println("RI1 r2 Selecione outro peao para mover:");
							return 0;
						}

					}
					else if (rRegraI1==3) { // todos os os peoes fora						
						
						System.out.println("RI1 Selecione um novo peao para mover com o valor do dado");
						JOptionPane.showMessageDialog(fv,"Selecione um peao "+facade.getCorPlayerVez() + " para mexer");
						MovePeao(indicepeao,dadoRodada);
						}
					
					else { // dado nao e 5 rReraI1 =0; 
						System.out.println("RI1 O dado tirado nao foi 5");
						
						ci=2;
						
						}
				}
				//if // Regra B1
				
				
				
				//if // Regra 6
				
				
				
				//if // Regra Barreira
				
		
	
				// Fim de tudo, move /
				
			
				
				if (ci!=1&& ci!=3 && ci!=4){ // move normalmente
	
					if (p.isAbrigo()||p.isCasaComum()|| p.isCasaSaida() && ci==2 ) {
						
						MovePeao(indicepeao,dadoRodada);
					}
	
				}
				
				
				
				//////// Regra Captura
							
							
				rRegraCA= facade.VerificaRegraCA(p, dadoRodada, vCasas); // 1  ou 0
				System.out.println(">>>>RegraCA: "+rRegraCA);
				
				
				if(rRegraCA != -1) { // encontrou um peao com a mesma casa que ele vai e cor diferente					
					System.out.println(">>RegraCA indice: "+rRegraCA);
					ICasa c = (ICasa) vCasas[rRegraCA];
					
					if (!c.eCasaSaida() || !c.eAbrigo()) {
						//MovePeaoCasaInicial(indicepeao,rRegraCA); //ERRO DO INDICE CLICADO > 16
						//MovePeaoCasaInicial(indicepeao,rRegraCA);
					}
					
					
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
		
		
		
		//atualizo para o proximo jogador
		

			
	
		Notify();
		return 1;	
	}
	


	// ---------- Observadores 
	
	@Override 
	public void update() { // -----------------------------------turno  ----------------
		
		fv.desabilitaBJogaDado();
		System.out.println("\t\t-- Nova Rodada --");
		System.out.println("\t\t----->>>>>>>> Player da Vez-->"+ facade.getCorPlayerVez() + "<<<<<" );
		// 1 - sorteio o dado
		// 2 - seleciono um peao valido
		// 3 - movo
		
		
		if(fv.getIndiceBotaoPeao()==-1) {
			JOptionPane.showMessageDialog(fv,"Selecione um peao "+facade.getCorPlayerVez() + " para mexer");
			
			//fv.setIndiceBotaoPeao(0);	
		}
		
		// botao de jogar dado foi clicado
	
		
//		if (teste==1)
//			TesteI1();
//			
//		if (teste==2)
//			TesteI1();
//
//		if(teste==3)
//			TesteI3();
//			teste=0;

		
	
			
		System.out.println("Cliquei para sortear o dado");
		
		if (this.dadoRodada==0) {
			this.dadoRodada=facade.JogaDado(); // joga o dado	
		}
		 
		System.out.println("--> dado rodada"+dadoRodada);
		fv.setNumeroDado(dadoRodada); // atualizar imagem dado
		
		
		
		
		System.out.println("BPeao clicado:"+fv.getIndiceBotaoPeao());
		
		
		//int controle = 1;
		
		//GerenciaRodada();
		//System.out.println("controle gerencia ->"+controle);
		//if(controle==1)
		
		if(GerenciaRodada()==1) { // jogada valida, proximo player		
					
			JOptionPane.showMessageDialog(fv,"Fim do turno "+facade.getCorPlayerVez());
			facade.ProximoJogador();				
			facade.NovoRound();
		

			fv.resetaIndiceBotaoClicado();	
			fv.AtualizaPainelPlayer(); // atualizo o painel cor
			
//			if (control_cor==-1) {
//				
//				int v=facade.getIntPlayerDaVez();
//				fv.setCorPainelPlayer(v-1);
//				control_cor=0;
//			}
//			
		
			
			
//		
//			if(control_cor == 1) {
//                fv.setCorPainelPlayer(Integer.valueOf(1));
//                fv.ExibePainelPlayer();
//                control_cor += 1;
//            }
//
//            else if(control_cor==2) {
//                
//                fv.setCorPainelPlayer(2);
//                fv.ExibePainelPlayer();
//                control_cor = 0;
//            }

			
		}
		
		else {
			System.out.println("------Rodada nao valida, sorteie o dado novamente");	
			JOptionPane.showMessageDialog(fv,"Rodada inválida.O Peao selecionado nao pertence ao player. Selecione outro e jogue o dado novamente");
		}
		
		
		//VERIFICA E ATUALIZA CASAS
		
		VerificaStatusCasas();
		
		//ExibeStatusCasas();
//		System.out.println(">>> ------Todas os peoes ----->>>\n");	
//		for(int i=0;i<16;i++){
//			
//			IPeao p= (IPeao) vPeao[i];
//			p.Exibe();
//		}
		
			
		JOptionPane.showMessageDialog(fv,"Proximo Player dado");
		fv.habilitaBJogaDado();
		Notify();

	}
	

	public void AtualizaStatusPeao(IPeao p, int dado) {
		
		//SETAR C SAIDA
		ICasa catual = (ICasa) vCasas[p.getPosicao()]; 
		System.out.println(">>pos peao:"+p.getPosicao());
			
		// seto casa atual
		
		if(p.getPosicao()!=-1) {
			
			p.setCasaInicial(false);
			
		}
		
		
		if (catual.eCasaSaida()){
			p.setCasaSaida(true);
		}else {
			p.setCasaSaida(false);
		}
		
				
		// SETAR ABRIGO		
		if(catual.eAbrigo()) {
			p.setAbrigo(true);
		}
		else {
			p.setAbrigo(false);
		}
		
		
		if (catual.eAbrigo()==false && catual.eCasaSaida()==false) {
			
			p.setCasaComum(true);
		}
		else {
			p.setCasaComum(false);
		}
		

		
		int posantiga = p.getPosicao()-dado;
		System.out.println(">>pos peao antiga:"+ posantiga );
		
		
		if (posantiga<0) {	// posicao circular	
			posantiga=  posantiga%52;
		
		}		
			
		return;
		
	}
	public void VerificaStatusCasas() {
		int i=0;
		for(i=0;i<52;i++) {
			
			ICasa c = (ICasa) vCasas[i];
			
			if (c.getQtdPeao()==0) {
				c.ReiniciaCasa();
			}
		}
	}
	
	
	
	public void AtualizaCasa( int posAntiga, int posTabuleiro, IPeao p) {
		
		ICasa antcasa;
			
		if(posAntiga!=-1) {	// pos antiga valida no tabuleiro
			antcasa = (ICasa) vCasas[posAntiga];
			antcasa.DecrementaPeaoCasa();
			
			if (antcasa.getQtdPeao()==0) {
				antcasa.ReiniciaCasa();
			}
			
			antcasa.setCasaBarreira(false);
			antcasa.setTemPeao(true);
			
			
			System.out.println(" --- Casa Antiga ---");
			//antcasa.ExibeStatus();
			System.out.println(" --- Casa Antiga ---");
		}
		
		else { // casa inicial 
			
			System.out.println(" --- Casa Antiga e inicial ---");
			
		}
		
		if(posTabuleiro!=-1) { // CASA ONDE O PEAO VAI
		
			ICasa ncasa = (ICasa) vCasas[posTabuleiro];
			
			if (ncasa.getTemPeao()) { // tem peao na nova casa
				ncasa.setCasaBarreira(true);
				ncasa.setCorPeao(1, p.getCor());
				
			}
			else { // nao tem peao na nova casa
				ncasa.setTemPeao(true);
				ncasa.setCorPeao(0, p.getCor());
			}
			
			
			ncasa.IncrementaPeaoCasa();
			
			
			System.out.println(" --- Casa Atual ---");
			//ncasa.ExibeStatus();
			System.out.println(" --- Casa Atual fim ---");
		}
		
		
		else { //nova posicao é a posicao inicial no tabuleiro ( -1)
				
			System.out.println(" --- Indo para a posicao INICAL NO TABULEIRO ---");
			
			
		}
		
		
	}
	
	public int encontraIPeaoCasaSaida(int pospeao) {
		
		IPeao p;
		
		
		int i;
		for(i=0;i<16;i++) {
			
			p = (IPeao) vPeao[i];
			if (p.getPosicao()==pospeao){
				return i;
			}
		}
		return -1;
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
		else if (cor.equals("azul")) {
			inf =12;
			sup = 16;
		}
		else {
			return -1;
		}
		
		if (i>=inf &&i < sup) {
			
			return i;
		}
		
		return -1;
	}
	
	public void MovePeao(int i,int dado){ // numero do peao no vetor de peoes e numero do dado
		
		// i indice do peao
		
		
		IPeao p =(IPeao) this.vPeao[i];
			
		int posAntiga =facade.getPosicaoPeao(p); // pega a posicao atual do peao
		int posTabuleiro=posAntiga;	
//		if(posTabuleiro==-1) {
//			posTabuleiro=0;
//			
//		}			
		double x;
		double y;
			
		ICoordenada[] vcoord =vTb.getvCasaComum();
		
		posTabuleiro = encontraNovaCasaTabuleiro(posTabuleiro, dado);
		
		ICoordenada nc= vcoord[posTabuleiro];
		x= nc.getX1()+5;
		y= nc.getY1()+5;
		
		System.out.println("\t>>> Status Peao antes de mover <<<<");
		//p.Exibe();// 
		
		facade.MovePeao(vPeao[i],x,y,posTabuleiro);
		AtualizaStatusPeao(p,dado);
		
		System.out.println("\t>>> Status Peao depois do movimento <<<<");
		//p.Exibe();//debug
						
		// atualiza casa
		
		
		AtualizaCasa(posAntiga,posTabuleiro,p);
		facade.setPeaoAtivoNoPlayer((Object )p);
					
		fv.setPosicaoBotoesPeoes(i,(int)x,(int) y);	// move botoes dos peoes	
		//Notify();
		
	}

	
	public void MovePeaoCasaSaida(int i) {
		
		
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
		AtualizaStatusPeao(p,0);
		
		//System.out.println( posTabuleiro);
		System.out.println("\tposicao setada");
		System.out.println("\t x:" + nx+ "n\t y:"+ny);
		

		
		facade.setPeaoAtivoNoPlayer((Object )p);
		
		AtualizaCasa(-1,posTabuleiro,p);
		//p.Exibe();
	
		fv.setPosicaoBotoesPeoes(i,(int)nx,(int) ny);
		//Notify();
	}
	
	public void MovePeaoCasaInicial(int indclicado, int indpeaoregra) {
		
		//ind -> indice do peao selecionado
		// regra -> ind do novo peao. pos da casa
		
		int indicePeaoplayer;

		double cx;
		double cy;
		
		double[] cxvermelho = VTabuleiro.getCoordenadasXvermelho();
		double[] cyvermelho = VTabuleiro.getCoordenadasYvermelho();
	 	
		double[] cxverde =VTabuleiro.getCoordenadasXverde();
		double[] cyverde =VTabuleiro.getCoordenadasYverde();
	 	
		double[] cxamarelo =VTabuleiro.getCoordenadasXamarelo();
		double[] cyamarelo =VTabuleiro.getCoordenadasYamarelo();
	 	
		double[] cxazul = VTabuleiro.getCoordenadasXazul();
		double[] cyazul = VTabuleiro.getCoordenadasYazul();
		
		
		//IPeao pvolta = (IPeao) vPeao[regra];
		
		System.out.println(">>>> Exibindo peao pvolta >>>>");
		//pvolta.Exibe();
		
	
		
		int n = 0;
		IPeao np;
		
        for(int i = 0; i < 16; i++) {
        	np = (IPeao) vPeao[i];
            if(np.getPosicao() == indpeaoregra) {
                n = i;
                break;
            }
        }
		
        np = (IPeao) vPeao[n];   
		int idcor = np.getCorId();
		
        //n  <<  tem  e a posicao do peao no vetor de peoes que volta par casa inicial
		
        if (idcor==0) { // vermelho
        	indicePeaoplayer=n;
        	cx=cxvermelho[indicePeaoplayer];
        	cy=cyvermelho[indicePeaoplayer];       	
        }
        if (idcor==1) { // verde
        	indicePeaoplayer=n% 4;   	
        	cx=cxverde[indicePeaoplayer];
    	    cy=cyverde[indicePeaoplayer];
      	
        }if (idcor==2) { // amarelo
        	indicePeaoplayer=n % 4;
        	cx=cxamarelo[indicePeaoplayer];
        	cy=cyamarelo[indicePeaoplayer];
        }
        else { // azul
        	indicePeaoplayer=n% 4;
        	cx=cxazul[indicePeaoplayer];
        	cy=cyazul[indicePeaoplayer];
        }
		
		//int pos =facade.getPosicaoPeao(p); // pega a posicao atual do peao

		np.setCasaInicial(true); //seta para -1 nao muda a posicao

		
//		x= nc.getX1()+5;
//		y= nc.getY1()+5;
		
		System.out.println("\t>>> Status Peao antes de mover <<<<");
		//np.Exibe();// 
		
		facade.MovePeao(np,cx+5,cy+5,-1);
		

		//Atualizando Status Peao
		
		np.setCasaInicial(true);
		np.setCasaSaida(false);
		np.setAbrigo(false);
		np.setCasaComum(false);

		//fim
		
		System.out.println("\t>>> Status Peao depois do movimento <<<<");
		np.Exibe();//debug
						
		// atualiza casa
		

		
		//AtualizaCasa(posAntiga,posTabuleiro,p); // setar na mao 

		facade.setPeaoDesativadoNoPlayer(np); 
					
		fv.setPosicaoBotoesPeoes(indpeaoregra,(int)cx,(int) cy);	// move botoes dos peoes		
	}
		
	
	public void MovePeaoCasaInicialIND(int indclicado) {
		
		int indicePeaoplayer;

		double cx;
		double cy;
		
		double[] cxvermelho = VTabuleiro.getCoordenadasXvermelho();
		double[] cyvermelho = VTabuleiro.getCoordenadasYvermelho();
	 	
		double[] cxverde =VTabuleiro.getCoordenadasXverde();
		double[] cyverde =VTabuleiro.getCoordenadasYverde();
	 	
		double[] cxamarelo =VTabuleiro.getCoordenadasXamarelo();
		double[] cyamarelo =VTabuleiro.getCoordenadasYamarelo();
	 	
		double[] cxazul = VTabuleiro.getCoordenadasXazul();
		double[] cyazul = VTabuleiro.getCoordenadasYazul();
		
		
		System.out.println(">>>> Movendo peao CASA INCIAL >>>>");
		//pvolta.Exibe();
		
		
        IPeao p = (IPeao) vPeao[indclicado];  //pego o peao		
        int posAntiga =facade.getPosicaoPeao(p);
		int idcor = p.getCorId();  // pego a cor
		
        //n  <<  tem  e a posicao do peao no vetor de peoes que volta par casa inicial
		
        if (idcor==0) { // vermelho
        	indicePeaoplayer=indclicado;
        	cx=cxvermelho[indicePeaoplayer];
        	cy=cyvermelho[indicePeaoplayer];       	
        }
        else if (idcor==1) { // verde
        	indicePeaoplayer=indclicado% 4;   	
        	cx=cxverde[indicePeaoplayer];
    	    cy=cyverde[indicePeaoplayer];
      	
        }
        else if (idcor==2) { // amarelo
        	indicePeaoplayer=indclicado % 4;
        	cx=cxamarelo[indicePeaoplayer];
        	cy=cyamarelo[indicePeaoplayer];
        }
        else { // azul
        	indicePeaoplayer=indclicado% 4;
        	cx=cxazul[indicePeaoplayer];
        	cy=cyazul[indicePeaoplayer];
        }
		
		facade.MovePeao(p,cx,cy,-1);
		

		//Atualizando Status Peao
		
		p.setCasaInicial(true); //seta para -1 nao muda a posicao
		p.setPosicao(-1);
		p.setCasaSaida(false);
		p.setAbrigo(false);
		p.setCasaComum(false);

		//fim
		
		System.out.println("\t>>> Status Peao depois do movimento <<<<");
		//p.Exibe();//debug
						

		
		//AtualizaCasa(posAntiga,posTabuleiro,p); // setar na mao 

		facade.setPeaoDesativadoNoPlayer(p); 
					
		
		
		AtualizaCasa(posAntiga,-1,p);
		VerificaStatusCasas();
		fv.setPosicaoBotoesPeoes(indclicado,(int)cx,(int) cy);	// move botoes dos peoes

	}
	
	public void setNumeroDado(int n ) {
		this.dadoRodada=n;
	}
	
	public void ReiniciaJogada() {
        for(int i = 0; i < 16; i++) {
            this.MovePeaoCasaInicialIND(i);
        }
        
        facade.setIntPlayerVez(0);      
        
//        fv.setCorPainelPlayer(0);
        
        String scor= facade.getCorPlayerVez();
        
        while(scor.equals(fv.getCorPainelPlayer())==false) {
    		fv.AtualizaPainelPlayer();
    		System.out.println(fv.getCorPainelPlayer());
    	}
        
        //fv.ExibePainelPlayer();

//        control_cor += 1;
        
        fv.resetaIndiceBotaoClicado();
    }
	
	public void ExibeStatusCasas() {
		System.out.println(">>> ------Todas as casas\n");
		for(int i=0;i<52;i++){	
			ICasa c= (ICasa) vCasas[i];
			c.ExibeStatus();
		}
	}

	@Override
	public Object getDados() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void setDadoRodada(int i) {
		
		this.dadoRodada=i;
	}

	public Object[] getVCasas() {
		
		return vCasas;
	}
	
	//////// FUNCOES DE TESTES
	
	public void TesteI1(){
//		this.dadoRodada=5; //teste teste casa saida peao na casa inicial
		MovePeaoCasaSaida(2);	// teste casa saida peao na casa inicial
		MovePeaoCasaSaida(7);	// teste casa saida peao na casa inicial
		MovePeaoCasaSaida(14);
		MovePeaoCasaSaida(11);
		
		
		MovePeao(11,2);
		MovePeao(7,9);
		MovePeao(2,3);
		
			
//		fv.AtualizaPainelPlayer();
		
//		MovePeaoCasaSaida(8);	// teste casa saida peao na casa inicial
//		MovePeaoCasaSaida(12);// teste casa saida peao na casa inicial
		teste=0;
	}

	public void TesteI3(){
//		this.dadoRodada=5; //teste teste casa saida peao na casa inicial
		MovePeaoCasaSaida(2);	// teste casa saida peao na casa inicial
		MovePeaoCasaSaida(7);	// teste casa saida peao na casa inicial
		MovePeaoCasaSaida(14);
		MovePeaoCasaSaida(11);
		MovePeaoCasaSaida(15);
		MovePeaoCasaSaida(6);
		
		
		MovePeao(11,2);
		MovePeao(7,9);
		MovePeao(2,3);
		
		MovePeao(6,2);
		MovePeao(15,4);
		MovePeao(2,3);
		
//		System.out.println(">>> TESTE PEAO CASA INICIAL <<<");
		JOptionPane.showMessageDialog(fv,"TESTE PEAO CASA INICIAL");
//		for(int i=0;i<16;i++) {	
//			MovePeaoCasaInicial(i);
//		}
		
		
		
		teste=0;
	}
	
	
	@Override
	public void update2(int i) {
		// TODO Auto-generated method stub
		
		System.out.println("Teste Rodada comecou pelo botao JBOTAO");
		
	}


	// ----- Metodos save e load
	
	public int auxcor(String cor) {
	    if(cor=="vermelho") return 0;
	    else if(cor=="verde") return 1;
	    else if(cor=="amarelo") return 2;
	    else if(cor=="azul") return 3;
	    return -1;
	}	
	public int booleanint(Boolean b) {
	    if(b) return 1;
	    return 0;
	}
	public boolean intboolean(int b) {
	    if(b==1) return true;
	    return false;
	}

	
	//----- OBSERVER
	

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
}

