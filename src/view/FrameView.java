package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import controler.*;

// apenas para sortear o dado

public class FrameView extends JFrame implements Observado{ // Canvas 
	
	
	
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
	
	public Div menuCimaMaster = new Div(new Color(106,138,222));
	public Div menuBaixoMaster = new Div(Color.white);
	
	private ArrayList<JBotao> vbotoes = new ArrayList<>(); //tam 52 , 0 a 51
	public JLayeredPane layers = new JLayeredPane();
	public VTabuleiro Tb;
	private ArrayList<Observador> lobs = new ArrayList<>(); 
	private int numerodado;
	
	

	
	
	public FrameView(){
	
		
		int ajustaXpainelInfo=3;
		int ajustaYpainelInfo=-2;
		
		int ajusataXCompDireita=-10;
		int ajusataXCompEsquerda=+5;
			
		setSize(LARG_DEFAULT,ALT_DEFAULT);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		// criacao das Divisoes
		
		jp1.setBounds(0,0,470,700);
		jp2.setBounds(470,0,10,700);
		jp3.setBounds(480,0,700,700);
	
		
//		layers.setSize(new Dimension(1200,700));
		layers.setBounds(0, 0, 1200, 700);	
		jp1.setImagemFundo("./Images/teste2.png",470,660);
//		jp2.setImagemFundo("./Images/teste2.png",470,660); // imagem fundo
//		jp3.setImagemFundo("./Images/teste2.png",470,660); // imagem fundo
		
		
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
		PainelDado.setBounds(ajusataXCompDireita+262,255,(int) PainelDado.getLargura(),(int)PainelDado.getAltura());
		PainelDado.setBackground(PainelDado.getColor());
		
			
		// add Dado e Botao de rolar o dado
		
		Dado_graf VDado = new Dado_graf(); 
		VDado.setBounds(ajusataXCompDireita+5,10,500,500);		
		
		 // Inicializa o desenho do dado
		VDado.pinta_dado(-1);
		
		
		
		JBotao bJogaDado = new JBotao("Jogue o dado"); // botao Jogar dado
		bJogaDado.setBounds(ajusataXCompDireita+262,422,167,70);
		bJogaDado.setBorder(new RoundedBorder(7));
		bJogaDado.setBackground(new Color(37,220,40));
		bJogaDado.setPressedBackgroundColor(new Color(101,254,3));
		bJogaDado.setHoverBackgroundColor(new Color(82,227,124));
		
		
		JBotao BSalvar = new JBotao("Salvar");
		BSalvar.setBounds(47+ajustaXpainelInfo,40+ajustaYpainelInfo,176,45);
		BSalvar.setBorder(new RoundedBorder(7));
		BSalvar.setBackground(new Color(255,255,255));
		BSalvar.setPressedBackgroundColor(new Color(100,200,251));
		BSalvar.setHoverBackgroundColor(new Color(248,246,246));
		
		JBotao BNovoJogo = new JBotao("Novo Jogo");
		BNovoJogo.setBounds(47+ajustaXpainelInfo,98+ajustaYpainelInfo,176,45);
		BNovoJogo.setBorder(new RoundedBorder(7));
		BNovoJogo.setBackground(new Color(255,255,255));
		BNovoJogo.setPressedBackgroundColor(new Color(100,200,251));
		BNovoJogo.setHoverBackgroundColor(new Color(248,246,246));
		
		JBotao BLoadGame = new JBotao("Carregar Jogo");
		BLoadGame.setBounds(239+ajustaXpainelInfo,40+ajustaYpainelInfo,176,45);
		BLoadGame.setBorder(new RoundedBorder(7));
		BLoadGame.setBackground(new Color(255,255,255));
		BLoadGame.setPressedBackgroundColor(new Color(100,200,251));
		BLoadGame.setHoverBackgroundColor(new Color(248,246,246));
		
		
		
		Div InfPlayer = new Div(162,560,167,120,new Color(244,239,239));//new Color(244,239,239)
		InfPlayer.setBounds(ajusataXCompDireita+262,560,167,60);
		InfPlayer.setBackground(PainelDado.getColor());
		
		
		PainelPlayer CorPlayer = new PainelPlayer(162,550,167,120);//new Color(244,239,239)
		CorPlayer.setBounds(ajusataXCompDireita+272,570,147,40);
		CorPlayer.setBackground(CorPlayer.getColor());
		
		Div InfTexto = new Div(35,300,167,400,new Color(244,239,239));//new Color(244,239,239)
		InfTexto.setBounds(ajusataXCompEsquerda+45,255,167,365);
		InfTexto.setBackground(InfTexto.getColor());
		
		
		
		
		// add textos
		
		JLabel labelCorPlayer = new JLabel("Jogador da vez");
        JLabel labelDado = new JLabel("Dado");
        JLabel labelInfo = new JLabel("Informações");
        JLabel labelMenu = new JLabel("Menu");
        labelCorPlayer.setBounds(ajusataXCompDireita+263,520,100,20);
        labelDado.setBounds(ajusataXCompDireita+263,212,100,20);
        labelInfo.setBounds(ajusataXCompEsquerda+45,200,100,20);
        labelMenu.setBounds(263,100,100,20);
		
		


		
		
		InfPlayer.add(CorPlayer);
		
		// adicionando filhos aos paineis Pai
		
//		jp1.add(menuCimaMaster);
//		jp1.add(menuBaixoMaster);	
//		jp1.add(PainelDado);
		
//		menuBaixoMaster.add(PainelDado);
//		PainelDado.add(VDado);
//		menuBaixoMaster.add(bJogaDado);
		
		

	
		// Setagem de profundidade	
		
		layers.add(jp1, 500); // menor, mais em cima		
		layers.add(jp2, 70); // de 10 em 10
		layers.add(jp3, 500);
		layers.add(Tb,0);
		
		
		
//		layers.add(jp1,Integer.valueOf(5)); // menor, mais em cima, ideal
		
		// add menus ao layer
	
		layers.add(menuCimaMaster,0);
		layers.add(menuBaixoMaster,0);
		layers.add(PainelDado,0);
		layers.add(VDado, 0); 
		layers.add(BSalvar,0);
		layers.add(BNovoJogo,0);
		layers.add(BLoadGame,0);
		layers.add(InfPlayer,0);
		layers.add(CorPlayer,0);
		layers.add(labelMenu,0);
		layers.add(labelInfo,0);
		layers.add(labelDado,0);
		layers.add(labelCorPlayer,0);
		layers.add(InfTexto,0);				
		layers.add(bJogaDado, 0); 
		
		//addBotoesPeoes(layers);
		
		// add layer no frame
		getContentPane().add(layers);
		

		// ---------------------- Eventos ------------------
		
		
		//Tb.move_peao(268,90);
		
		bJogaDado.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // codigo a ser executado quando o botao for clicado
                //System.out.println("O botão foi clicado!");
                //Random random = new Random();
                //int numeroInteiro = random.nextInt(6);//numero de 0-5
                //numeroInteiro+=1;
                //System.out.println(numeroInteiro);
     
                
                //Tb.redesenha((Graphics2D)Tb.getGraphics(),263+(44*numeroInteiro) ,alet); ***
                //repaint();
                
                
                
                Notify();
                VDado.GeraDado(numerodado,(Graphics2D)VDado.getGraphics());
                CorPlayer.proximaCorPlayer();
                
            }

        });

		
		
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
	
	public void setNumeroDado(int n) {
		this.numerodado=n;
	}
	public JLayeredPane getVLayers() {
		return layers;
	}	
	public VTabuleiro getVTabuleiro() { //retorna as divs baseado no numero	
		return this.Tb;
	}
	
	public void InicializaVBotoes() {
		int[] pospeao = new int[2];
		
		int i=0;	
		for (i=0;i<16;i++) { // procurando em vpeoes
			
			pospeao=Tb.getCoordenadaPeaoIntXY(i);
			//pospeao[0]
			//pospeao[1]
			
			JBotao bpeao = new JBotao("B PEAO"); // botao Jogar dado
			bpeao.setBounds(20,20,42,42);
			bpeao.setBackground(Color.orange);
			bpeao.setPressedBackgroundColor(new Color(30,30,30));
			bpeao.setHoverBackgroundColor(new Color(35,35,35));
			vbotoes.add(bpeao);
		}
		return ;
	}
	
	public void addBotoesPeoes(JLayeredPane l) {
		
		
		for(JButton j: this.vbotoes) {
			l.add(j,0);
		}
		
		return;
	}

	@Override
	public void addObserver(Observador o) {
		// TODO Auto-generated method stub
		lobs.add(o);
	}


	@Override
	public void removeObserver(Observador o) {
		// TODO Auto-generated method stub
		lobs.remove(o);
	}


	@Override
	public Object getDados() {
		// TODO Auto-generated method stub
		return this;
	}


	@Override
	public void Notify() {
		// TODO Auto-generated method stub
		for( Observador obj : this.lobs) {
			obj.update();
		}
	}
	

}
