package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.Scanner;

import javax.swing.*;
import java.awt.*;
import javax.swing.JLayeredPane;
import javax.swing.border.Border;
import java.util.Random;


// apenas para sortear o dado

public class FrameView extends JFrame{ // Canvas 
	
	private final int LARG_DEFAULT = 1200; // Constantes de tamanho	- largura
	private final int ALT_DEFAULT = 700; // Constantes de tamanho - altura
	
	public final int Div1_inicio = 0 ;//menu 0 a 470
	public final int Div1_final = 470;
			
	public final int Div2_inicio =  470; // divisoria 470 , + 10 lag
	public final int Div2_final = 480;
	
	public final int Div3_inicio = 483;  // board 483 ate 1200
	public final int Div3_final = 1200;

	
	
	
	public Div jp1 = new Div(0,0,470,700, Color.blue);  // Menu info
	public Div jp2 = new Div(470,0,10,700,Color.GREEN);
	public Div jp3 = new Div(480,0,700,700,Color.RED);	// Tabuleiro
	
	
	public JLayeredPane layers = new JLayeredPane();
	public VTabuleiro Tb;
	
	// Menus cima e baixo com conteudo
	
	//public Div menuCimaMaster = new Div(0,0,425,150, Color.yellow);
	//public Div menuBaixoMaster = new Div(0,0,425,500, Color.white);
	public Div menuCimaMaster = new Div(Color.yellow);
	public Div menuBaixoMaster = new Div(Color.white);
	
	//divs menores Cima e Baixo ( botoes e rodadas)
	
	
	public FrameView(){
			
		setSize(LARG_DEFAULT,ALT_DEFAULT);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	
		
//		layers.setSize(new Dimension(1200,700));
		layers.setBounds(0, 0, 1200, 700);
		
		
		Tb = new VTabuleiro();		
		Tb.setBounds(505,0,(int)VTabuleiro.getLargura(),(int)VTabuleiro.getAltura());
		Tb.setBackground(Color.orange);
		
		
		menuCimaMaster.setBounds(18,15,430,150);
		menuCimaMaster.setBackground(menuCimaMaster.getColor());
		
		menuBaixoMaster.setBounds(18,180,430,467);
		menuBaixoMaster.setBackground(menuBaixoMaster.getColor());
		
		//167 x 144 largura altura
		Div PainelDado = new Div(0,0,167,144,Color.LIGHT_GRAY);
		
		// x e y -> 262 x 255
		PainelDado.setBounds(262,255,(int) PainelDado.getLargura(),(int)PainelDado.getAltura());
		PainelDado.setBackground(PainelDado.getColor());
		
		// add Dado e Botao de rolar o dado
		
		Dado_graf VDado = new Dado_graf(); 
		VDado.setBounds(5,10,500,500);		
		
		VDado.pinta_dado(3); // desenha o dado dado uma face
		
		JBotao bJogaDado = new JBotao("Jogue o dado");
		bJogaDado.setBounds(262,422,167,70);
		bJogaDado.setBorder(new RoundedBorder(10));
		bJogaDado.setBackground(new Color(248,209,68));
		bJogaDado.setPressedBackgroundColor(new Color(253,200,12));
		bJogaDado.setHoverBackgroundColor(new Color(246,209,75));
		
				
		
//		jp1.setBColorFromColor();
//		jp2.setBColorFromColor();
//		jp3.setBColorFromColor();

		// criacao das Divisoes
			
		jp1.setBounds(0,0,470,700);
		jp2.setBounds(470,0,10,700);
		jp3.setBounds(480,0,700,700);
		
		
		
		// adicionando filhos aos paineis Pai
//		jp1.add(menuCimaMaster);
//		jp1.add(menuBaixoMaster);
		
		
//		jp1.add(PainelDado);
		
//		menuBaixoMaster.add(PainelDado);
//		PainelDado.add(VDado);
//		menuBaixoMaster.add(bJogaDado);
		
		
		
//		jp3.add(Tb);	
		
	
		// Setagem de profundidade	
		
		layers.add(jp1, 500); // menor, mais em cima
		
		layers.add(jp2, 70); // de 10 em 10
		layers.add(jp3, 500);
		layers.add(Tb,0);
		
		
		
//		layers.add(jp1,Integer.valueOf(5)); // menor, mais em cima
//		layers.add(jp2,Integer.valueOf(5)); // de 10 em 10
//		layers.add(jp3,Integer.valueOf(5));
//		
//		layers.add(Tb,Integer.valueOf(10));
		
		
		// ad dado
		
		// add menus
	
		layers.add(menuCimaMaster,0);
		layers.add(menuBaixoMaster,0);
		layers.add(PainelDado,0);
		layers.add(VDado, 0); 
		layers.add(bJogaDado, 0); 
		
		
//		layers.add(menuCimaMaster,Integer.valueOf(10));
//		layers.add(menuBaixoMaster,Integer.valueOf(10));	
//		layers.add(PainelDado,Integer.valueOf(30));
//		layers.add(bJogaDado,Integer.valueOf(30));	
//		layers.add(VDado,Integer.valueOf(50)); 
//			
		
		// adicionando botao
		
		
				
		// add o layer no Frame
		
		getContentPane().add(layers);
		
		
		
		bJogaDado.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // codigo a ser executado quando o botao for clicado
                System.out.println("O botão foi clicado!");
                Random random = new Random();
                int numeroInteiro = random.nextInt(6);//numero de 0-5
                numeroInteiro+=1;
                System.out.println(numeroInteiro);
                
                int alet = random.nextInt(400);
                //pos+=numeroInteiro;
                //System.out.println(pos);
                //double x = Tb.getEllipse1X();//pegando a posicao
                //System.out.println(x);
                //Tb.setEllipse1X(x + 44);//alterando-a

                Tb.redesenha((Graphics2D)Tb.getGraphics(),263+(44*numeroInteiro) ,alet);
                
            }

        });

		
		Tb.move_peao(268,90);
	}
	
	public Div  getDivs(int i) { //retorna as divs baseado no numero // teste	
		
		if (i==1){
			return this.jp1;
		}
		else if (i==2){
			return this.jp2;
		} else {
			return this.jp3;
		}
		
	}
	public JLayeredPane getVLayers() {
		return layers;
	}
	
	public VTabuleiro getVTabuleiro() { //retorna as divs baseado no numero	
		return this.Tb;
	}
	
	
	

}
