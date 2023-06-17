package model;
import java.util.ArrayList;
import java.util.List;
import java.util.*;  

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
	public int getDadoFace () {
		return this.dado.get_face();
	}
	public int JogaDado() {		
		dado.joga_dado();	
		return dado.get_face();
	}
	public void IniciaJogo() { // Cria os objetos e inicializa tudo
		
		this.mTb=new Tabuleiro();	
		CriaPlayers();	
		this.round = new Round(); // crio o round

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
	
	public boolean VerificaRegrasI1(Object p) {
		Peao pe = (Peao) p;
        if (regras.RegraI1(pe, dado.get_face()) == 1){
            return true;
        }
        return false;
    }
	
	public int VerificaRegrasB1(Object p) {
		
		Peao pe = (Peao) p;
		
        if (regras.RegraB1(pe) == 1) {
           Player P = pe.getPlayerPai();
           P.update_qtd_vitoria();
        }
        
        return 0;
    }
	
	public boolean VerificaRegrasB2(Object p) {
		
		Peao P = (Peao) p;
		Casa[] c = (Casa[]) mTb.getVcasas();
		Casa C = c[P.getPosicao()];
        if (regras.RegraB2(P, C) == 1){
            return true;
        }
        
        int x = P.getPosicao();
        P.setPosicao(x - 1);
        return false;
    }
	
	public boolean VerificaRegrasB3(Object p) {
		
		Peao P = (Peao) p;
		Casa[] c = (Casa[]) mTb.getVcasas();
		Casa C = c[P.getPosicao() + 1];
		int x = P.getPosicao();
		
        if (regras.RegraB3(P) == 1){
        	x = P.getPosicao();
            P.setPosicao(x - 1);
            return true;
        }
        
        return false;
    }
	
	public boolean VerificaRegras6(Object p) {
		Peao P = (Peao) p;
		if (regras.Regra6(P, dado) == 1) {
            return true;
        }
        
        return false;
	}
	
	public boolean VerificaRegrasBR(Object p) {
		Peao P = (Peao) p;
		Peao[] pv = (Peao[]) vPeoes;
		List<Peao> pl = Arrays.asList(pv);
		if (regras.RegraBR(P, dado, pl) == 1) {
            return true;
        }
        
        return false;
	}
	
	public boolean VerificaRegrasCA(Object p) {
		Peao P = (Peao) p;
		Peao[] pv = (Peao[]) vPeoes;
		List<Peao> pl = Arrays.asList(pv);
		if (regras.RegraCA(P, dado, pl) == 1) {
            return true;
        }
        
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
