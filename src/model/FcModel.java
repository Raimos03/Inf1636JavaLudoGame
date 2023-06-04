package model;
import java.util.ArrayList;

public class FcModel { // Facade

	private Dado dado;
//	private players; ( colocar um vetor de players);
	private Round round;
	ArrayList <Player> vPlayers = new ArrayList <Player>();
	private Tabuleiro mTb;
	
	
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
		
		//CriaPlayers();
		// Crio model tabuleiro
	}
	
	public void CriaPlayers() { // crio e seto vetor de players			
		Player p1 = new Player("Vermelho");
		Player p2 = new Player("Verde");
		Player p3 = new Player("Amarelo");
		Player p4 = new Player("Azul");	
	
		vPlayers.add(p1);
		vPlayers.add(p2);
		vPlayers.add(p3);
		vPlayers.add(p4);
	}
	
	public int IntPlayerDaVez() {		
		return round.getIntPlayerVez();
	}
	
	public Player GetPlayerVez() {		
		return vPlayers.get(round.getIntPlayerVez());
	}
	
	public void ProximoJogador() { // passa para o proximo jogador no ROund	
		round.nextRound();		
		return ;
	}
	
	public void MovePeao() { // move peao
		
		
		
	}
	public boolean VerificaRegras() {
		
		boolean c = true;
		
		// condicional de todas as regras
		
		return false;
	}

}
