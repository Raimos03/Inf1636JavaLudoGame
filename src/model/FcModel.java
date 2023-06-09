package model;
import java.util.ArrayList;
import java.util.Arrays;

import controler.*;

public class FcModel   { // Facade // Implementa um Singleton

	private Dado dado;
//	private players; ( colocar um vetor de players);
	
	private Round round;
	private Object[] vPlayers = new Object[4];
	private Object[] vPeoes = new Object[16];
	private Tabuleiro mTb;
	private Regra regras;
	private static FcModel facade=null;

	private FcModel(){
		
	}
	
	public static FcModel getInstance() {
		
		if(facade==null) {
			facade = new FcModel();
		}
		return facade;
	}
	
	public void  CriaDado() {		
		this.dado = new Dado();
	}

	public Dado getDado () {
		return this.dado;
	}
	public int JogaDado() {		
		dado.joga_dado();	
		return dado.get_face();
	}
	public void IniciaJogo() { // Cria os objetos e inicializa tudo
		
		this.mTb=new Tabuleiro();	
		CriaPlayers();	
		this.round = new Round(); // crio o round
		this.regras = new Regra();

	}
	
	public void CriaPlayers() { // crio e seto vetor de players			
		Player p1 = new Player("vermelho");
		Player p2 = new Player("verde");
		Player p3 = new Player("amarelo");
		Player p4 = new Player("azul");	
	
		vPlayers[0]=p1;
		adicionaPeoes(p1,0);
		vPlayers[1]=p2;
		adicionaPeoes(p2,4);
		vPlayers[2]=p3;
		adicionaPeoes(p3,8);
		vPlayers[3]=p4;
		adicionaPeoes(p4,12);
		
		//mTb.vPeao=vPeoes;
		//ExibeVpeoes();
	}

	public Object[] getVplayers() {	
		return vPlayers;
	}
	public Object[] getVpeoes(){
		return vPeoes;
	}
	public Object GetPlayerVez() {		
		return vPlayers[round.getIntPlayerVez()];
	}
	public void SetIntPlayerVez(int n) {
		round.setIntPlayerVez(n);
	}
	
	public String getCorPlayerVez() {
		
		
		int indp= round.getIntPlayerVez();
		if (indp ==4){
			indp=0;
		}
		Player p = (Player) vPlayers[indp];
		//System.out.println("int vez player" + (round.getIntPlayerVez()-1) );
		String icor = p.get_cor();
		//System.out.println("cor player vez ->" + icor);
		return icor;	
	}
	public Tabuleiro getTabuleiro() {	
		return mTb;
	}
	public int getIntPlayerDaVez() {		
		return round.getIntPlayerVez();
	}
	public void NovoRound() {
		round.New_round();
	}
	public void ProximoJogador() { // passa para o proximo jogador no ROund	
		round.nextRound();		
		return ;
	}
	public int getPosicaoPeao(IPeao p ) {
		Peao np = (Peao) p;
		return  np.getPosicao();
	}
	
	
	public void MovePeao(Object  p, double nx, double ny , int postabuleiro) { // move peao IMPLEMENTAR

		
		Peao np = (Peao) p;
		Coordenada c = new Coordenada(nx,ny);
		//np.setPosicao(np.getPosicao()+dado);
		np.MovePeao(c);
		np.setPosicao(postabuleiro);
		
		
	}	
	
	
	public Object[] getVCasas() {
		return mTb.getVCasas();
	}
	
	public int VerificaRegrasI1(Object p, int dado) {				
		return regras.RegraI1((Peao) p, dado);
	}
	
	
	public int VerificaRegrasB1(Object p) {

        Peao pe = (Peao) p;

        if (regras.RegraB1(pe) == 1) {
           Player P = pe.getPlayerPai();
           P.ContabilizaVictoriaPeao();
        }
        return 0;
    }
	
//	 public boolean VerificaRegrasB2(Object p) {
//
//	        Peao P = (Peao) p;
//	        //#Casa[] c = (Casa[]) mTb.getVcasas();
//	        Casa[] c = (Casa[]) mTb.getVCasas();
//	        Casa C = c[P.getPosicao()];
//	        if (regras.RegraB2(P, C) == 1){
//	            return true;
//	        }
//
//	        int x = P.getPosicao();
//	        P.setPosicao(x - 1);
//	        return false;
//	    }
	 
	 public boolean VerificaRegrasB3(Object p, Object[] vcasa) {
	        Peao P = (Peao) p;
	        Casa[] c = (Casa[]) vcasa;
	        
	        Casa C = c[P.getPosicao() + 1]; // ver o +1
	        int x = P.getPosicao();

	        if (regras.RegraB3((Casa) C ) == 1){
	            //x = P.getPosicao();
	            P.setPosicao(x - 1);
	            return true;
	        }

	        return false;
	    }
	 
	 public int VerificaRegraCA(Object p, int dado, Object[] vp) {
		 
		 Peao np = (Peao) p; 		 
		 return regras.RegraCA( np, vp, dado );
		 
	 }
	 
	 public int VerificaRegra6(int d, int i) {
         return regras.Regra6(d, i);
     }
	 
//	 public void setRodada(Rodada nrd) {
//		 this.rodada=nrd;
//	 }
	 
	 
	 public void ExibePeoesAtivosPlayer() {
		 Player player= (Player)vPlayers[round.getIntPlayerVez()];
		 System.out.println("---->PEOES ATIVOS PLAYER: " + player.get_qtd_ativo());
	 }
	 
	 public void SetQtdPeoesAtivosnoPlayer(Object p,int n) {
		 Peao np = (Peao) p;
		 Player pl = np.getPlayerPai();
		 
		 pl.setQtdPeoesAtivos(n);
	 }
	 
	 public void setPeaoDesativadoNoPlayer(Object p) {
		 
		 
			Peao np = (Peao) p;
			
			if(np.getPosicao()==-1) {
				Player pl = np.getPlayerPai();
						
				pl.decrementaPeaoAtivo();
			}
		}
	
	public void setPeaoAtivoNoPlayer(Object p) {
		Peao np = (Peao) p;
		
		if( np.getPosicao()!=-1) {
			Player pl = np.getPlayerPai();				
			pl.incrementaPeaoAtivo();
		}
		
		
	}
	
	public int getQtdPeoesAtivos(Object p ) {
		Peao np = (Peao) p;	
		return  np.getPlayerPai().get_qtd_ativo();
	}
	
	private void adicionaPeoes(Player p,int i) {
		
		Peao np = p.getPeao1();
		this.vPeoes[i]=np;
		
		np = p.getPeao2();
		this.vPeoes[i+1]=np;
		
		np=p.getPeao3();
		this.vPeoes[i+2]=np;
		
		np=p.getPeao4();
		this.vPeoes[i+3]=np;

	}
	public void setCasaComumPeao(Object p,boolean b) {
		Peao np = (Peao) p;
		np.setCasaComum(b);
		
	}
	
	public void setCasaSaidaPeao(Object p,boolean b) {
		Peao np = (Peao) p;
		np.setCasaSaida(b);
		
	}	
	
	public void setCasaInicialPeao(Object p,boolean b) {
		Peao np = (Peao) p;
		np.setCasaInicial(b);
		
	}
	public int VerificaTodosPeoesAtivos(IPeao p) {
		
		Peao np = (Peao) p;
		Player npai = (Player) np.getPlayerPai();
		if( regras.VerificaPeoesAtivos(npai)==0) {
			return 1; // caso ainda tenha peao para sair 
		}
		
		return 0;
	}
	
	public void setCasaInicial(double[] posinvermelhox,double[] posinvermelhoy,double[] posinverdex,double[] posinverdey,double[] posinamarelx,double[] posinamarely,double[] posinazulx,double[] posinazuly) {
		
//		for (Object p: this.vPeoes) {
//			
//			Peao ip = (Peao) p;
//			ip.setXY(new Coordenada(50,70));
//		}
		
		int x;
		int y;
		int i;
		int j=0;
		Peao p;

		Object[] vp=this.vPeoes;
		// fazer em funcao do peao
		for (i=0;i<16;i++) {
			
			if(i<4) {
				j=i;
				x = (int) posinvermelhox[j];
				y = (int) posinvermelhoy[j];	
				
				p=(Peao) vp[i];
				Coordenada nc = new Coordenada(x,y);
				
				nc.setX1(x);
				nc.setY1(y);	
				p.setXY(nc);

			}	
			
			else if (i<8) {
				j=i-4;
				x = (int) posinverdex[j];
				y = (int) posinverdey[j];
				p=(Peao) vp[i];
				Coordenada nc = new Coordenada(x,y);
				nc.setX1(x);
				nc.setY1(y);	
				p.setXY(nc);
			}	
			
			else if (i<12) {
				j=i-8;
				x = (int) posinamarelx[j];
				y = (int) posinamarely[j];
				p=(Peao) vp[i];
				Coordenada nc = new Coordenada(x,y);
				nc.setX1(x);
				nc.setY1(y);	
				p.setXY(nc);
				
				j++;
			}	
			
			else  {
				j=i-12;
				x = (int) posinazulx[j];
				y = (int) posinazuly[j];	
				p=(Peao) vp[i];
				Coordenada nc = new Coordenada(x,y);
				nc.setX1(x);
				nc.setY1(y);	
				p.setXY(nc);
				
				j++;
			}			
		}
		
		
	}
	
	public Object getDadoObject() {
		return this.dado;
	}
	public void ExibeVpeoes() {
		for(Object p: vPeoes) {			
			Peao pe = (Peao) p;
			pe.Exibe();
		}
	}
	
//	public void setvCasaVitoriaVermelha(Coordenada[] vVitoriaVermelha) {	
//		mTb.setvCasaVitoriaVermelha(vVitoriaVermelha);
//	}
//	
//	public void setvCasaVitoriaVerde( Coordenada[] vVitoriaVermelha) {
//		mTb.setvCasaVitoriaVerde(vVitoriaVermelha);	
//	}
//	public void setvCasaVitoriaAzul(Coordenada[] vVitoriaVermelha) {
//		
//		mTb.setvCasaVitoriaAzul(vVitoriaVermelha);	
//	}
//	public void setvCasaVitoriaAmarela(Coordenada[] vVitoriaVermelha) {
//		mTb.setvCasaVitoriaAmarela(vVitoriaVermelha);	
//		
//	}
//	
//	public Object[] getVcasas() {
//		return mTb.getVcasas();
//	}
	
	public void resetaCasaInfo() {
		mTb.ResetaCasaInfo();
	}
	public int getRound() {
		return round.getIntPlayerVez();
	}
	public void setIntPlayerVez(int n) {
		
		round.setIntPlayerVez(n);
		return;
	}
	public String getCorPlayerPai(Object p) {		
		Peao np = (Peao) p;
		return np.getPlayerPai().get_cor() ;
	}
}
