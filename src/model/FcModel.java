package model;

public class FcModel { // Facade

	private Dado dado;
//	private players; ( colocar um vetor de players);
	private Round round;
	
	
	
	public Dado  CriaDado() {
		
		Dado dado = new Dado();
		return dado;
	
	}
	
	public int JogaDado() {		
		dado.joga_dado();	
		return dado.get_face();
	}
	
	public void  CriaPlayers() {
		
		Player p1 = new Player("Vermelho");
		Player p2 = new Player("Verde");
		Player p3 = new Player("Amarelo");
		Player p4 = new Player("Azul");
		
//		player1.add
	}
	
	public int PlayerDaVez() {
			
		return Round.getRound();
	}
	

	
	
}
