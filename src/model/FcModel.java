package model;
import java.util.ArrayList;

import controler.ICoordenada;
import controler.IPeao;


public class FcModel   { // Facade

	private Dado dado;
//	private players; ( colocar um vetor de players);
	private Round round;
	private Object[] vPlayers = new Object[4];
	private Object[] vPeoes = new Object[16];
	private Tabuleiro mTb;
	private Regra regras;
	

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
	
	public void MovePeao(Object  p, double x, double y , int dado, int postabuleiro) { // move peao IMPLEMENTAR
		
		Peao np = (Peao) p;
		Coordenada c = new Coordenada(x,y);
		//np.setPosicao(np.getPosicao()+dado);
		np.MovePeao(dado, c);
		np.setPosicao(postabuleiro);
		
		
	}	
	public boolean VerificaRegrasI1(Peao p, int dado) {	
		if (regras.RegraI1(p, dado) == 1){
			return true;
		}		
		return false;
	}
	
	
	public boolean VerificaRegrasB1() {
			
			boolean c = true;		
			
			return false;
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
	
	public void ExibeVpeoes() {
		
		for(Object p: vPeoes) {			
			Peao pe = (Peao) p;
			pe.Exibe();
		}
	}
	public Object[] getVcasas() {
		return mTb.getVcasas();
	}
	
}
