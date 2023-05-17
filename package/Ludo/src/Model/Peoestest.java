package Model;
import org.junit.*;
import static org.junit.Assert.*;

public class Peoestest {
	
	@Test
	public void test_peoes() {
		//Basic objects
		Round round = new Round();
		Regra regra = new Regra();
		
		//Players
		Player player = new Player("vermelho");
		
		//Peoes
		Peao peao1 = player.getPeao1();
		Peao peao2 = player.getPeao2();
				
		//Basic variables
		int p;
		
		//Testar regra 1 no peao 1 com alteracao
		//Player 1 turn
		p = round.player_turn;
		assertEquals(1, p);
		
		peao1.setCasaFinal(true);
		assertEquals(1, regra.regraB1(player.getPeao1(), 1));
		
		//New round begins
		round.turn += 1;
		round.New_round();
		
		//Testar regra 1 no peao 2
		//Player 2 turn 
		p = round.player_turn; 
		assertEquals(2, p);
		
		assertEquals(0, regra.regraB1(player.getPeao2(), 2));
		
		//New round begins
		round.turn += 1;
		round.New_round();
		
		//Testar regra 2 no peao 2 com alteracao
		//Player 3 turn 
		p = round.player_turn; 
		assertEquals(3, p);
		
		peao2.setCasaSaida(true);
		assertEquals(1, regra.regraB2(player.getPeao2()));
		
		//New round begins
		round.turn += 1;
		round.New_round();
			
		//Testar regra 2 no peao 1 com alteracao
		//Player 4 turn 
		p = round.player_turn; 
		assertEquals(4, p);
				
		peao1.setCasaSaida(true); 
		assertEquals(1, regra.regraB2(player.getPeao1()));
	}
}