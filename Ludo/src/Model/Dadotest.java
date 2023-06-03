package Model;
import org.junit.*;
import static org.junit.Assert.*;

public class Dadotest {
	
	@Test
	public void test_dado() {
		Dado dice = new Dado();
		
		int x1 = 0;
		int x2 = 0;
		int x3 = 0;
		
		//Jogada 1
		dice.joga_dado();
		x1 = dice.get_face();
		System.out.println("Die Throw: "  + x1);
		System.out.println("R1: "  + dice.get_R1());
		System.out.println("R2: "  + dice.get_R2());
		System.out.println("R3: "  + dice.get_R3());
		assertEquals(x1, dice.get_R1());
		
		//Jogada 2
		dice.joga_dado();
		x2 = dice.get_face();
		System.out.println("Die Throw: "  + x2);
		System.out.println("R1: "  + dice.get_R1());
		System.out.println("R2: "  + dice.get_R2());
		System.out.println("R3: "  + dice.get_R3());
		assertEquals(x2, dice.get_R1());
		assertEquals(x1, dice.get_R2());
		
		//Jogada 3
		dice.joga_dado();
		x3 = dice.get_face();
		System.out.println("Die Throw: "  + x3);
		System.out.println("R1: "  + dice.get_R1());
		System.out.println("R2: "  + dice.get_R2());
		System.out.println("R3: "  + dice.get_R3());
		assertEquals(x3, dice.get_R1());
		assertEquals(x2, dice.get_R2());
		assertEquals(x1, dice.get_R3());
		
		//Jogada 4
		dice.joga_dado();
		x1 = dice.get_face();
		System.out.println("Die Throw: "  + x1);
		System.out.println("R1: "  + dice.get_R1());
		System.out.println("R2: "  + dice.get_R2());
		System.out.println("R3: "  + dice.get_R3());
		assertEquals(x1, dice.get_R1());
		assertEquals(x3, dice.get_R2());
		assertEquals(x2, dice.get_R3());
		
	}
}