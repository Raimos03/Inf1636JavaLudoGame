package controler;

import view.*;
import java.util.Scanner;
import model.FcModel;
import java.awt.Color;
import javax.swing.JLayeredPane;


public class Rodada { 
	//Controler
	
	
	public static int nRodada =0;
	public int dadoRodada; // ver se realmente preciso
	public FcModel facade;
	public FrameView Fv;
	public JLayeredPane layer; // ver
	public VTabuleiro vTb;
	
	{
		facade=new FcModel();
		Fv = new FrameView();
	}
	
	
	public Rodada() { // controle o andamento do jogo
			
		// acesso tudo de model pelo facade (FcModel)
		
		facade.IniciaJogo();
		
		
		// Enquando nao ha um vencedor
		
		
//		System.out.println("opcao>");
//		Scanner sc = new Scanner(System.in);
//		int opcao = sc.nextInt();
//		
//				
//		if (opcao ==4) {		
////			Div nq = Fv.getDivs(1);
////			nq.setBackground(Color.orange);
////			nq.repaint();
//			
//		
//						
//		}
		
	
//		sc.close();
	}
	
	public void setFrameView(FrameView nfv) {
		this.Fv=nfv;
	}
	
	
}

// codigo Rodada antiga

//Basic objects
//Testes iniciais

//Round round = new Round();
//Dado dice = new Dado();
//Regra regra = new Regra();
//
////Players
//Player player = new Player("vermelho");
//
//
//  
//Player 1 turn p = round.player_turn; dice.joga_dado();
//System.out.println("Player " + "\n" + "Die Throw: " + dice.face +
//"Regra de casa inicial" + regra.regraB1(player.getPeao1(),dice.face));
