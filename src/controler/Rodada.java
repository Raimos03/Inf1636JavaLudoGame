package controler;

import view.*;
import model.FcModel;

public class Rodada { 
	//Controler
	
	
	public static int nRodada =0;
	public int dadoRodada; // ver se realmente preciso
	public FcModel facade;
	
	{
		facade=new FcModel();
	}
	
	
	public Rodada() { // controle o andamento do jogo
			
		// acesso tudo de model pelo facade (FcModel)
		
		facade.IniciaJogo();
		
		// Enquando nao ha um vencedor
		
		
		
				
	}
	
	
}
