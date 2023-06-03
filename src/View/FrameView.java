package View;


import java.util.Scanner;
import javax.swing.*;
import java.awt.*;
import javax.swing.JLayeredPane;

public class FrameView extends JFrame{ // Canvas 
	
	private final int LARG_DEFAULT = 1200; // Constantes de tamanho	- largura
	private final int ALT_DEFAULT = 700; // Constantes de tamanho - altura
	
	public final int Div1_inicio = 0 ;//menu 0 a 470
	public final int Div1_final = 470;
			
	public final int Div2_inicio =  470; // divisoria 470 , + 10 lag
	public final int Div2_final = 480;
	
	public final int Div3_inicio = 483;  // board 483 ate 1200
	public final int Div3_final = 1200;

	
	
	public Div jp1 = new Div(0,0,470,700, Color.blue);
	public Div jp2 = new Div(470,0,10,700,Color.GREEN);
	public Div jp3 = new Div(480,0,700,700,Color.RED);	// Tabuleiro
	
	
	public JLayeredPane layers = new JLayeredPane();
	
	// Menus cima e baixo com conteudo
	
	//public Div menuCimaMaster = new Div(0,0,425,150, Color.yellow);
	//public Div menuBaixoMaster = new Div(0,0,425,500, Color.white);
	public Div menuCimaMaster = new Div(Color.yellow);
	public Div menuBaixoMaster = new Div(Color.white);
	
	//divs menores Cima e Baixo ( botoes e rodadas)
	
	
	FrameView(){
			
		setSize(LARG_DEFAULT,ALT_DEFAULT);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
		
		
//		layers.setSize(new Dimension(1200,700));
		layers.setBounds(0, 0, 1200, 700);
		
		
		Tabuleiro Tb = new Tabuleiro();		
		Tb.setBounds(505,0,(int)Tabuleiro.getLargura(),(int)Tabuleiro.getAltura());
		Tb.setBackground(Color.orange);
		
		
		menuCimaMaster.setBounds(18,15,430,150);
		menuCimaMaster.setBackground(menuCimaMaster.getColor());
		
		menuBaixoMaster.setBounds(18,180,430,467);
		menuBaixoMaster.setBackground(menuBaixoMaster.getColor());
		
		
		Div PainelDado = new Div(0,0,196,164,Color.LIGHT_GRAY);
		PainelDado.setBounds(245,245,(int) PainelDado.getLargura(),(int)PainelDado.getAltura());
		PainelDado.setBackground(PainelDado.getColor());
		
		
		
		
//		jp1.setBColorFromColor();
//		jp2.setBColorFromColor();
//		jp3.setBColorFromColor();

		// criacao das Divisoes
			
		jp1.setBounds(0,0,470,700);
		jp2.setBounds(470,0,10,700);
		jp3.setBounds(480,0,700,700);
		
		
		// adicionando filhos aos paineis Pai
		
		
		jp1.add(PainelDado);
		jp3.add(Tb);	
	
		// Setagem de profundidade	
		
		layers.add(jp1, 500); // menor, mais em cima
		layers.add(jp2, 70); // de 10 em 10
		layers.add(jp3, 500);
		layers.add(Tb,0);
		
		// add menus
		
		jp1.add(menuCimaMaster);
		jp1.add(menuBaixoMaster);
		
		layers.add(menuCimaMaster,0);
		layers.add(menuBaixoMaster,0);
		layers.add(PainelDado,0);
		//teste do git
		

		// add o layer no Frame
		
		getContentPane().add(layers);

	}
	
	public Div getDivs(int i) { //retorna as divs baseado no numero	
		if (i==1){
			return this.jp1;
		}
		else if (i==2){
			return this.jp2;
		}
		return this.jp3;
	}

}
