package Model;

import java.util.Random;

class Dado {
	private int face;
	private int R1NumeroDado = 0;
	private int R2NumeroDado = 0;
	private int R3NumeroDado = 0;
	
	public void joga_dado() {
		Random rand = new Random(); 

		// Stablishing the range of the die, 0 - 5
		int D6_range = 5;
	 
		//Correcting die difference
		face = rand.nextInt(D6_range); 
		face += 1;
		
		if(R1NumeroDado == 0) {
			R1NumeroDado = face;
		}
		
		else if(R2NumeroDado == 0) {
			R2NumeroDado = face;
		}
		
		else if(R3NumeroDado == 0) {
			R3NumeroDado = face;
		}
		
		else {
			R3NumeroDado = R2NumeroDado;
			R2NumeroDado = R1NumeroDado;
			R1NumeroDado = face;
		}
	}
	
	public int get_face() {		
		return face;	
	}
}