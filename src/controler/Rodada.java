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
	public int teste=1;
	
	
	private ArrayList<Observador> lobs = new ArrayList<>(); 


	
	public Rodada( FrameView fv,FcModel fc) { // controle o andamento do jogo

		
		// acesso tudo de model pelo facade
		
		CarregaInicio(fv, fc);
			
		//vTb.ExibeVpeao();
		//facade.ExibeVpeoes();
		
		
		// --- Event Listener
	
	jbSalvar.addActionListener(new ActionListener() {
	      	public void actionPerformed(ActionEvent e) {
            	IPeao[] p = (IPeao[])fc.getVpeoes();
            	String conteudo = "";
            	
            	for(int i=0; i<16; i++){
            		if((i)%4==0) conteudo += auxcor(facade.getCorPlayerPai(p[i]))+"\n";//cor ppai
            		conteudo += p[i].getX1()+"\n";										//coord x
            		conteudo += p[i].getY1()+"\n";										//coord y
            		conteudo += p[i].getPosicao()+"\n";									//posicao
            		conteudo += booleanint(p[i].isCasaSaida())+"\n";					//casasaida
            		conteudo += booleanint(p[i].isCasaInicial())+"\n";					//casainicial
            		conteudo += booleanint(p[i].isBarreira())+"\n";						//barreiro
            		conteudo += booleanint(p[i].isAbrigo())+"\n";						//abrigo
            		conteudo += booleanint(p[i].isCasaFinal())+"\n";					//casafinal
            		conteudo += auxcor(p[i].getCor())+"\n";								//cor
            	}
            	conteudo += facade.getRound()+"\n";
            	
	            JFileChooser fileChooser = new JFileChooser();
	            int resultado = fileChooser.showSaveDialog(jbSalvar);
	            if (resultado == JFileChooser.APPROVE_OPTION) {
	                String caminhoArquivo = fileChooser.getSelectedFile().getPath();
	                JBotaoFill.salvarArquivo(caminhoArquivo, conteudo );
	            }
	        	
	        }
	    });
		
		jbLoad.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	IPeao[] p = (IPeao[])fc.getVpeoes();	//mudat nome caminhoarquivo 
	            	int i, j;
	                JFileChooser fileChooser = new JFileChooser();
	                int resultado = fileChooser.showOpenDialog(null);
	                if (resultado == JFileChooser.APPROVE_OPTION) {
	                    String laurel = fileChooser.getSelectedFile().getPath();
	                    // Chamar o método carregarConteudoArquivo através da instância do JBotao
	                    String conteudo = JBotaoFill.carregaArquivo(laurel);
	                    String vec[] = conteudo.split("\n");
	                    //cores
	                    int cppai[] = new int[4];
		                for(i = 0; i<148; i++){
		                	if(i%37==0) cppai[i/37]=Integer.valueOf(vec[i]);
		                }
		                for(i = 1; i<37; i++){//vermelho
		                	j = 0;
		                	
		                	if(i%9==1) {
		                		p[j].setX1(Double.valueOf(vec[i]));
		                	}
		                	if(i%9==2) {
		                		p[j].setY1(Double.valueOf(vec[i]));
		                	}
		                	if(i%9==3) {
		                		p[j].setPosicao(Integer.valueOf(vec[i]));
		                	}
		                	if(i%9==4) {
		                		p[j].setCasaSaida(intboolean(Integer.valueOf(vec[i])));
		                	}
		                	if(i%9==5) {
		                		p[j].setCasaInicial(intboolean(Integer.valueOf(vec[i])));
		                	}
		                	if(i%9==6) {
		                		p[j].setBarreira(intboolean(Integer.valueOf(vec[i])));
		                	}
		                	if(i%9==7) {
		                		p[j].setAbrigo(intboolean(Integer.valueOf(vec[i])));
		                	}
		                	if(i%9==8) {
		                		p[j].setCasaFinal(intboolean(Integer.valueOf(vec[i])));
		                		j++;
		                	}
	                    }
		                for(i = 38; i<74; i++){//verde
		                	j = 4;
		                	if(i%9==2) {
		                		p[j].setX1(Double.valueOf(vec[i]));
		                	}
		                	if(i%9==3) {
		                		p[j].setY1(Double.valueOf(vec[i]));
		                	}
		                	if(i%9==4) {
		                		p[j].setPosicao(Integer.valueOf(vec[i]));
		                	}
		                	if(i%9==5) {
		                		p[j].setCasaSaida(intboolean(Integer.valueOf(vec[i])));
		                	}
		                	if(i%9==6) {
		                		p[j].setCasaInicial(intboolean(Integer.valueOf(vec[i])));
		                	}
		                	if(i%9==7) {
		                		p[j].setBarreira(intboolean(Integer.valueOf(vec[i])));
		                	}
		                	if(i%9==8) {
		                		p[j].setAbrigo(intboolean(Integer.valueOf(vec[i])));
		                	}
		                	if(i%9==0) {
		                		p[j].setCasaFinal(intboolean(Integer.valueOf(vec[i])));
		                		j++;
		                	}
	                    }
		                for(i = 75; i<111; i++){//amarelo
		                	j = 8;
		                	if(i%9==3) {
		                		p[j].setX1(Double.valueOf(vec[i]));
		                	}
		                	if(i%9==4) {
		                		p[j].setY1(Double.valueOf(vec[i]));
		                	}
		                	if(i%9==5) {
		                		p[j].setPosicao(Integer.valueOf(vec[i]));
		                	}
		                	if(i%9==6) {
		                		p[j].setCasaSaida(intboolean(Integer.valueOf(vec[i])));
		                	}
		                	if(i%9==7) {
		                		p[j].setCasaInicial(intboolean(Integer.valueOf(vec[i])));
		                	}
		                	if(i%9==8) {
		                		p[j].setBarreira(intboolean(Integer.valueOf(vec[i])));
		                	}
		                	if(i%9==0) {
		                		p[j].setAbrigo(intboolean(Integer.valueOf(vec[i])));
		                	}
		                	if(i%9==1) {
		                		p[j].setCasaFinal(intboolean(Integer.valueOf(vec[i])));
		                		j++;
		                	}
	                    }
		                for(i = 112; i<148; i++){//azul
		                	j = 12;
		                	if(i%9==4) {
		                		p[j].setX1(Double.valueOf(vec[i]));
		                	}
		                	if(i%9==5) {
		                		p[j].setY1(Double.valueOf(vec[i]));
		                	}
		                	if(i%9==6) {
		                		p[j].setPosicao(Integer.valueOf(vec[i]));
		                	}
		                	if(i%9==7) {
		                		p[j].setCasaSaida(intboolean(Integer.valueOf(vec[i])));
		                	}
		                	if(i%9==8) {
		                		p[j].setCasaInicial(intboolean(Integer.valueOf(vec[i])));
		                	}
		                	if(i%9==0) {
		                		p[j].setBarreira(intboolean(Integer.valueOf(vec[i])));
		                	}
		                	if(i%9==1) {
		                		p[j].setAbrigo(intboolean(Integer.valueOf(vec[i])));
		                	}
		                	if(i%9==2) {
		                		p[j].setCasaFinal(intboolean(Integer.valueOf(vec[i])));
		                		j++;
		                	}
	                    }
		                facade.setIntPlayerVez(Integer.valueOf(vec[148]));
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
						System.out.println("sem Peao na casa de saida"); // ok
											
							
						MovePeaoCasaSaida(indicepeao);
	
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
				
				
				
				// Regra Captura
				
	
				// Fim de tudo, move /
				
			
				
				if (ci!=1){
	
					if (p.isCasaComum()|| p.isCasaSaida() && ci==2 ) {
						
						MovePeao(indicepeao,dadoRodada);
					}
	
				}
				
				
				
				////// regra ca
							
							
				rRegraCA= facade.VerificaRegraCA(p, dadoRodada, vCasas); // 1  ou 0
				System.out.println(">>RegraCA: "+rRegraCA);
				
//				
//				if(rRegraCA > 0) { // encontrou um peao com a mesma casa que ele vai
//					
//					System.out.println(">>RegraCA indice: "+rRegraCA);
//					MovePeaoCasaInicial(indicepeao,rRegraCA);
//				}
				
//				else  {
//					MovePeao(indicepeao,rRegraCA);
//					
//				}
				

						
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
	public void update() { // -----------------------------------turno old ----------------
		
		//fv.desabilitaBJogaDado();

		if(fv.getIndiceBotaoPeao()==-1) {
			JOptionPane.showMessageDialog(fv,"Selecione um peao "+facade.getCorPlayerVez() + " para mexer");
			
			//fv.setIndiceBotaoPeao(0);	
		}
		
		System.out.println("Cliquei para sortear o dado");
		
		if (this.dadoRodada==0) {
			this.dadoRodada=facade.JogaDado(); // joga o dado	
		}
		 
		System.out.println("--> dado rodada"+dadoRodada);
		fv.setNumeroDado(dadoRodada); // atualizar imagem dado

		
		//JOptionPane.showMessageDialog(fv,"Proximo Player dado");
		//fv.habilitaBJogaDado();
		
	
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
			
			System.out.println(" --- Casa Antiga ---");
			antcasa.ExibeStatus();
			System.out.println(" --- Casa Antiga ---");
		}
		
		else { // casa inicial 
			
			System.out.println(" --- Casa Antiga e inicial ---");
			
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
		
		//int n=ncasa.getQtdPeao();
		
		System.out.println(" --- Casa Atual ---");
		ncasa.ExibeStatus();
		System.out.println(" --- Casa Atual fim ---");
		
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
		p.Exibe();// 
		
		facade.MovePeao(vPeao[i],x,y,posTabuleiro);
		AtualizaStatusPeao(p,dado);
		
		System.out.println("\t>>> Status Peao depois do movimento <<<<");
		p.Exibe();//debug
						
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
		p.Exibe();
	
		fv.setPosicaoBotoesPeoes(i,(int)nx,(int) ny);
		//Notify();
	}
	
	public void MovePeaoCasaInicial(int ind, int regra) {
		
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
		
		
		IPeao pvolta = (IPeao) vPeao[regra];
		int idcor = pvolta.getCorId();
		
		IPeao[] np = (IPeao[]) vPeao;
        int n = 0;

        for(int i = 0; i < 16; i++) {
            if(np[i].getPosicao() == regra) {
                n = i;
                break;
            }
        }
        
        //n  <<  tem  e a posicao do peao no vetor de peoes que volta par casa inicial
		
        if (idcor==0) { // vermelho
        	indicePeaoplayer=ind;
        	cx=cxvermelho[indicePeaoplayer];
        	cy=cyvermelho[indicePeaoplayer];       	
        }
        if (idcor==1) { // verde
        	indicePeaoplayer=ind - 4;
        	
        	cx=cxverde[indicePeaoplayer];
    	    cy=cyverde[indicePeaoplayer];
      	
        }if (idcor==2) { // amarelo
        	indicePeaoplayer=ind - 8;
        	cx=cxamarelo[indicePeaoplayer];
        	cy=cyamarelo[indicePeaoplayer];
        }
        else { // azul
        	indicePeaoplayer=ind - 12;
        	cx=cxazul[indicePeaoplayer];
        	cy=cyazul[indicePeaoplayer];
        }
		
		

		//int pos =facade.getPosicaoPeao(p); // pega a posicao atual do peao
			
		
		np[n].setCasaInicial(true); //seta para -1 nao muda a posicao
		// ICoordenada[] vcoord =vTb.getvCasaComum();
	
	
		
//		x= nc.getX1()+5;
//		y= nc.getY1()+5;
		
		System.out.println("\t>>> Status Peao antes de mover <<<<");
		pvolta.Exibe();// 
		
		facade.MovePeao(pvolta,cx+5,cy+5,-1);
		

		//Atualizando Status Peao
		
		pvolta.setCasaInicial(true);
		pvolta.setCasaSaida(false);
		pvolta.setAbrigo(false);
		pvolta.setCasaComum(false);

		//fim
		
		System.out.println("\t>>> Status Peao depois do movimento <<<<");
		pvolta.Exibe();//debug
						
		// atualiza casa
		

		
		//AtualizaCasa(posAntiga,posTabuleiro,p); // setar na mao 
	
		
		facade.setPeaoDesativadoNoPlayer(pvolta); 
					
		fv.setPosicaoBotoesPeoes(regra,(int)cx,(int) cy);	// move botoes dos peoes		
	}
		
	
	public void setNumeroDado(int n ) {
		this.dadoRodada=n;
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
	
	public void setDadoRodada(int i) {
		
		this.dadoRodada=i;
	}

	
	//////// FUNCOES DE TES
	
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




	@Override
	public void update2(int i) {
		// TODO Auto-generated method stub
		
		System.out.println("Teste Rodada comecou pelo botao JBOTAO");
		
	}


	// Metodos save e load
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
