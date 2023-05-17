import org.junit.*;
import static org.junit.Assert.*;

public class Playertest {
	
	@Test
	public void test_players() {
		Player player1 = new Player("vermelho");
		Player player2 = new Player("verde");
		Player player3 = new Player("amarelo");
		Player player4 = new Player("azul");
		
		assertEquals("vermelho", player1.get_cor());
		assertEquals("verde", player2.get_cor());
		assertEquals("amarelo", player3.get_cor());
		assertEquals("azul", player4.get_cor());
		
		//Player 1
		player1.ativar_peao(3);
		assertEquals(1, player1.get_qtd_ativo());
		
		player1.ativar_peao(2);
		assertEquals(2, player1.get_qtd_ativo());
		
		player1.desativar_peao(3);
		assertEquals(1, player1.get_qtd_ativo());
		
		//Player 2
		player2.ativar_peao(4);
		assertEquals(1, player2.get_qtd_ativo());
		
		player2.ativar_peao(3);
		assertEquals(2, player2.get_qtd_ativo());
		
		player2.desativar_peao(3);
		assertEquals(1, player2.get_qtd_ativo());
		
		//Player 3
		player3.ativar_peao(1);
		assertEquals(1, player3.get_qtd_ativo());
		
		player3.ativar_peao(2);
		assertEquals(2, player3.get_qtd_ativo());
		
		player3.desativar_peao(2);
		assertEquals(1, player3.get_qtd_ativo());
		
		//Player4
		player4.ativar_peao(2);
		assertEquals(1, player4.get_qtd_ativo());
		
		player4.ativar_peao(2);
		assertEquals(2, player4.get_qtd_ativo());
		
		player4.desativar_peao(2);
		assertEquals(1, player4.get_qtd_ativo());
	}
}