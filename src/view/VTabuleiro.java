package view;

import javax.swing.*;
import java.awt.*;
import java.awt.geom. *;
import java.util.ArrayList;

import controler.*;


import java.awt.Color;
;


public class VTabuleiro extends JPanel implements IPeao,ICoordenada, Observador, ICasa {
	
	private static Color[] vCores = {new Color(218,36,28),new Color(5,191,89),new Color(255,243,0),new Color(03,148,222)};
	private Color[] vCoresPeao = {new Color(250,53,53),new Color(104,250,53),new Color(250,218,53),new Color(53,84,250)};
	
	
	private static VCoordenada[] vCasaComum = new VCoordenada[52]; //OK
	private static VCoordenada[] vCasaVitoriaVermelha = new VCoordenada[6];  //OK
	private static VCoordenada[] vCasaVitoriaVerde = new VCoordenada[6];  //OK
	private static VCoordenada[] vCasaVitoriaAmarela = new VCoordenada[6];  //OK
	private static VCoordenada[] vCasaVitoriaAzul = new VCoordenada[6];  //OK
//	private static Casa[] vCasasModificadas = new Casa[52];
	
	
	public static double[] XPosicaoInicialVermelho= {75.5,154,75.5,154}; // matriz
	public static double[] YPosicaoInicialVermelho= {75,75,153,153}; //  posicoes iniciais ok
	
	public static double[] XPosicaoInicialVerde= {471,549,471,549};
	public static double[] YPosicaoInicialVerde= {75,75,153,153};
	
	public static double[] XPosicaoInicialAmarelo= {471,549,471,549};
	public static double[] YPosicaoInicialAmarelo= {469,469,548,548};
	
	public static double[] XPosicaoInicialAzul= {75.5,154,75.5,154};
	public static double[] YPosicaoInicialAzul= {469,469,548,548};

	
	public static ICoordenada PosicaoCasaSaidaVermelho = new VCoordenada(43.99999,262.7999); // OK
	public static ICoordenada PosicaoCasaSaidaVerde = new VCoordenada(350.6,43.8); // OK
	public static ICoordenada PosicaoCasaSaidaAmarelo = new VCoordenada(569.6,350.40); //OK
	public static ICoordenada PosicaoCasaSaidaAzul = new VCoordenada(263,569.4); //OK
	
	//----------------- testes
	
	//private int xt=268;
	//private int yt=90;
		
	//-----------------
	
	private static float largura=660;
	private static float altura=660;
	private static float larguraCasaInicial= 263;
//	private static float espacoEntreCasaInicial= 132;
//	private static float deslocamentoCasaInicial = larguraCasaInicial + espacoEntreCasaInicial;

	
	private Object[] vpeoes = new Object[16];
	private Object[] vcasas = new Object[52];
	
	IPeao peao;
	ICoordenada icord;
	ICasa casa;
	
	//public Observer o = new Observer(268, 400);

	
	VTabuleiro(){
		InicializaVCoordenadas();
		
	}
	

	@Override
	public void paintComponent(Graphics g) {		
		
		super.paintComponent(g);	
		Graphics2D g2d=(Graphics2D) g;
		
		 		
		DCasaInicial(g2d);
		DCirculosInicio(g2d);
		DCasaVitoriaCentro(g2d);
		DCasas(g2d);
		
		
		
		//DCriaPeoesInicio(g2d, vpeoes);
		
		if (!(vpeoes[0]==null) && !(vcasas[0]==null)) { // Desenha peoes e casas
			
			int pospeao;
			int i=0;
			for (i=0;i<16;i++) {	// vpeoes		
				peao = (IPeao) vpeoes[i];
				icord = peao.getXY();
				pospeao=peao.getPosicao();
				
				if(pospeao!=-1) { // tabuleiro
					
					casa=(ICasa) vcasas[peao.getPosicao()];
					
					if(casa.eBarreira()){// for barreira ou outra configuracao{
					
						if(casa.QualTipoBarreira()==1) {	// mesma cor									
							dBarreiraMesmaCor(g2d, (int)icord.getX1()-2, (int)icord.getY1()-2,casa.getCor1());
						}
						else { // cor diferente				
							dBarreiraCorDiferente(g2d, (int)icord.getX1()-2, (int)icord.getY1()-2,casa.getCor1(),casa.getCor2());
						}					
					}
					else { // se nao
						DCriaPeao(g2d,(int)icord.getX1(),(int)icord.getY1(),vCoresPeao[peao.getIntCor()]);
					}
				}				
				else { // desenha peao na casa inicial
					DCriaPeao(g2d,(int)icord.getX1(),(int)icord.getY1(),vCoresPeao[peao.getIntCor()]);				
							
				}
			}	
			
			
		}
		//DCriaPeao(g2d, o.getx(), o.gety());		
		//DCriaPeao(g2d,this.xt,this.yt);
		

	}
	
	public void setVcasas(Object[] vc) {
		this.vcasas=vc;
	}
	
	public void setVpeoes(Object[] vp) {
		this.vpeoes = vp;
	}
	
	public VCoordenada[] getvCasaComum() {
		return vCasaComum;
	}

	public void setvCasaComum(VCoordenada[] vCasaComum) {
		VTabuleiro.vCasaComum = vCasaComum;
	}


	public VCoordenada[] getvCasaVitoriaVermelha() {
		return vCasaVitoriaVermelha;
	}
	public VCoordenada[] getvCasaVitoriaVerde() {
		return vCasaVitoriaVerde;
	}
	public VCoordenada[] getvCasaVitoriaAmarela() {
		return vCasaVitoriaAmarela;
	}
	public VCoordenada[] getvCasaVitoriaAzul() {
		return vCasaVitoriaAzul;
	}


	public void setvCasaVitoriaAmarela(VCoordenada[] vCasaVitoria) {
		VTabuleiro.vCasaVitoriaAmarela = vCasaVitoria;
	}
	
	public void setvCasaVitoriaVermelha(VCoordenada[] vCasaVitoria) {
		VTabuleiro.vCasaVitoriaVermelha = vCasaVitoria;
	}
	public void setvCasaVitoriaAzul(VCoordenada[] vCasaVitoria) {
		VTabuleiro.vCasaVitoriaAzul = vCasaVitoria;
	}
	public void setvCasaVitoriaVerde(VCoordenada[] vCasaVitoria) {
		VTabuleiro.vCasaVitoriaVerde = vCasaVitoria;
	}
	
	public static ICoordenada getCasaSaidaVermelho() {
		return VTabuleiro.PosicaoCasaSaidaVermelho;
	}
	
	public static ICoordenada getCasaSaidaVerde() {
		return VTabuleiro.PosicaoCasaSaidaVerde;
	}
	
	public static ICoordenada getCasaSaidaAmarelo() {
		return VTabuleiro.PosicaoCasaSaidaAmarelo;
	}
	
	public static ICoordenada getCasaSaidaAzul() {
		return VTabuleiro.PosicaoCasaSaidaAzul;
	}
	
	
	public static VCoordenada[] getVcasasComuns(){
		
		return VTabuleiro.vCasaComum;
	}
	public static VCoordenada[] getVRoadVermelha(){
				
		return VTabuleiro.vCasaVitoriaVermelha;
	}
	public static VCoordenada[] getVRoadVerde(){
		
		return VTabuleiro.vCasaVitoriaVerde;	
	}
	public static VCoordenada[] getVRoadAmarela(){
		
		return VTabuleiro.vCasaVitoriaAmarela;	
	}
	public static VCoordenada[] getVRoadAzul(){
				
		return VTabuleiro.vCasaVitoriaAzul;			
	}
	
//	public static Casa[] getVCasasModificadas(){
//		
//		return VTabuleiro.vCasasModificadas;			
//	}


	public void InicializaVCoordenadas() { // salva as posicoes x e y do tabuleiro em todos os vetores de posicao
		
		int i=0;
		double x=263;
		double y=0;
		double larg=43.8;
		
		int andax=1;
		int sinalx=1;
		int sinaly=1;
		int pulameio=1;
		
		for(i=0;i<52;i++) {
			
			if(i==1) { // preenche Victory road verde
				preencheVroad(x,y+larg,vCasaVitoriaVerde,0,1);
			}
			else if (i==2) {
				andax=0;
			}
			else if (i==7) { // casas onde viro
				andax=1;					
			}
			else if (i==13) {
				andax=0;
			}				
			else if (i==14) {
				preencheVroad(x-larg,y,vCasaVitoriaAmarela,1,-1);
			}
			else if (i==15) {
				andax=1;
				sinalx=-1;
			}
			else if (i==20) {
				andax=0;
				pulameio=-1;					
			}
			else if (i==26) {
				andax=1;
			}
			else if (i==27) {
				preencheVroad(x,y-larg,vCasaVitoriaAzul,0,-1);
			}				
			else if (i==28) {
				andax=0;
				sinaly=-1;
			}
			else if (i==33) {
				andax=1;										
			}
			else if (i==39) {
				andax=0;
				sinalx=1;
			}			
			else if (i==40) {
				preencheVroad(x+larg,y,vCasaVitoriaVermelha,1,1);
			}
			else if (i==41) {
				andax=1;
				pulameio=1;					
			}
			else if (i==46) {
				andax=0;
			}	
//			System.out.println(""+i+"\t"+x+" "+y);		// debug casa e posicao x y 		
			vCasaComum[i]= new VCoordenada(x,y);
			
			if (i==7|| i==20 || i==33 || i==46) {									
				if(andax==1) {
					y+=larg*pulameio;
					x+=larg*sinalx;	
					}
				else {
					x+=larg*pulameio;
					y+=larg*sinaly;
					}
			}			
			else {		
				if(andax==1) { // anda em x
					x+=larg*sinalx;					
					}					
				else {					
					y+=larg*sinaly;
					}	
				}
			}						
	}
	
	public void preencheVroad(Double x, Double y, VCoordenada[] vroad, int direcao, int sinal ) {			
	
		int i =0;					
		for(i=0;i<6;i++) {			
			//System.out.println("\t\t"+i+"\t"+x+" "+y);
			vroad[i]= new VCoordenada(x,y);
			
			if (direcao==1) { // anda x			
				x+=43.8*sinal;
			}				
			else { // anda y					
				y+=43.8*sinal;
			}					
		}	
		return ;
	}

		
	public void DCasaInicial(Graphics2D g2d) {		
		//largura = 263
		//altura = 263	
		
		float fgInternaRetX = 44 ;
		float fgInternaRetY = 44 ;
		float fgInternaRetTam = 175;
		
		int fgLargura = 263;
		int fgEspaco = 132;
		int fgPosInicialx =0;
		int fgPosInicialy =0;
		
		int deslocamento = fgLargura+fgEspaco;
		
		int k=0;
		int i=0;
		int j=0;

		g2d.setPaint(vCores[k++]);

		Rectangle2D rt1 = new Rectangle2D.Float(fgPosInicialx,fgPosInicialy,fgLargura,fgLargura);	
		g2d.setStroke(new BasicStroke(1));
		g2d.fill(rt1);
		g2d.setPaint(Color.black);
		g2d.draw(rt1);
		
		//retangulo dentro
		g2d.setPaint(Color.green);
		Rectangle2D rt5 = new Rectangle2D.Float(fgInternaRetX, fgInternaRetY,fgInternaRetTam,fgInternaRetTam);	
		g2d.setStroke(new BasicStroke(2));
		g2d.draw(rt5);
				
		Rectangle2D rt2 = new Rectangle2D.Float(fgPosInicialx+deslocamento,fgPosInicialy,fgLargura,fgLargura);	
		g2d.setPaint(vCores[++i]);
		g2d.setStroke(new BasicStroke(1));
		g2d.fill(rt2);
		g2d.setPaint(Color.black);
		g2d.draw(rt2);
		
		
		//retangulo dentro 2 
		g2d.setPaint(Color.yellow);
		Rectangle2D rt6 = new Rectangle2D.Float(fgInternaRetX+deslocamento, fgInternaRetY,fgInternaRetTam,fgInternaRetTam);	
		g2d.setStroke(new BasicStroke(2));
		g2d.draw(rt6);
			
	
		Rectangle2D rt3 = new Rectangle2D.Float(fgPosInicialx+deslocamento,fgPosInicialy+deslocamento,fgLargura,fgLargura);	
		g2d.setPaint(vCores[++i]);
		g2d.setStroke(new BasicStroke(1));
		g2d.fill(rt3);
		g2d.setPaint(Color.black);
		g2d.draw(rt3);
		
	
		//retangulo dentro 3
		g2d.setPaint(Color.blue);
		Rectangle2D rt7 = new Rectangle2D.Float(fgInternaRetX+deslocamento, fgInternaRetY+deslocamento,fgInternaRetTam,fgInternaRetTam);	
		g2d.setStroke(new BasicStroke(2));
		g2d.draw(rt7);
		
		Rectangle2D rt8 = new Rectangle2D.Float(fgPosInicialx,fgPosInicialy+deslocamento,fgLargura,fgLargura);	
		g2d.setPaint(vCores[++i]);
		g2d.setStroke(new BasicStroke(1));
		g2d.fill(rt8);
		g2d.setPaint(Color.black);
		g2d.draw(rt8);
		
		
		//retangulo dentro 4 
		g2d.setPaint(Color.red);
		Rectangle2D rt9 = new Rectangle2D.Float(fgInternaRetX, fgInternaRetY+deslocamento,fgInternaRetTam,fgInternaRetTam);	
		g2d.setStroke(new BasicStroke(2));
		g2d.draw(rt9);		
		//continuar	
	}
		
	public void DCasaVitoriaCentro(Graphics2D g2d) {
		
		int i=0;		
		g2d.setPaint(Color.white);
		// largura 132
		int posx = (int) VTabuleiro.larguraCasaInicial; // comeco do quadrado centro
		int posy = (int) VTabuleiro.larguraCasaInicial;
		Point2D.Float centro = new Point2D.Float(VTabuleiro.getLargura()/2,VTabuleiro.getAltura()/2);
				
		// Quadrado centro
		Rectangle2D rt1 = new Rectangle2D.Float(posx,posy,132,132);	
		g2d.setStroke(new BasicStroke(1));
		g2d.fill(rt1);
		g2d.setPaint(Color.black);
		g2d.draw(rt1);
				
//		// Quadrado centro
//		Ellipse2D.Float el = new Ellipse2D.Float(centro.x-10,centro.y-10,20,20);	
//		g2d.setStroke(new BasicStroke(1));
//		g2d.fill(el);
//		g2d.setPaint(Color.yellow);
//		g2d.draw(el);
		
		Point2D.Float p1 = centro; // vermelho
		Point2D.Float p2 = new Point2D.Float(posx,posy);
		Point2D.Float p3 = new Point2D.Float(posx,posy+132);
		DTriangulo(g2d,p1,p2,p3,vCores[i++]);
		
		Point2D.Float p4= centro; //verde
		Point2D.Float p5 = new Point2D.Float(posx,posy);
		Point2D.Float p6 = new Point2D.Float(posx+132,posy);
		DTriangulo(g2d,p4,p5,p6,vCores[i++]);
		
		Point2D.Float p7= centro; // amarelo
		Point2D.Float p8 = new Point2D.Float(posx+132,posy);
		Point2D.Float p9 = new Point2D.Float(posx+132,posy+132);
		DTriangulo(g2d,p7,p8,p9,vCores[i++]);
		
		Point2D.Float p10= centro; // azul
		Point2D.Float p11= new Point2D.Float(posx+132,posy+132);
		Point2D.Float p12= new Point2D.Float(posx,posy+132);
		DTriangulo(g2d,p10,p11,p12,vCores[i++]);
		
	}
	
	public void DCasas(Graphics2D g2d) {
		//tamanho= 44
		// 3 x 6		
		DCasaVerde(g2d);
		DCasaAmarela(g2d);
		DCasaVermelha(g2d);
		DCasaAzul(g2d);			
	}

	public void DCasaVerde(Graphics2D g2d) {
		
		float posInicialx= VTabuleiro.larguraCasaInicial;
		float posInicialy=  VTabuleiro.larguraCasaInicial;
		float largura = (float) 43.8;
		
		int icor=1;
		
		float y=0;
		int i,j;
		i=j=0;	
		Color c = Color.white;
		Color[] v = VTabuleiro.getVCores();
		
		//System.out.println("\n-----CASA VERDE ----");
		
		for(i=0;i<3;i++) {
					
			for(j=0;j<6;j++) {
				
				if (j>0) {	
					
					if (i==0 && j ==1) {
						c = Color.DARK_GRAY;
					}
					
					else if (i==2 && j ==1) {
						c= v[icor];
					}
					else if (i==1) {						
						c= v[icor] ;
					}
					
					else {
						c = Color.white;
					}
				}
				
				else {
					 c = Color.white;			
				}
				
				DQuadrado(g2d,posInicialx, y ,largura, c);
				y+=largura;					
			}
		
			posInicialx+=largura;
			y=0;
			//System.out.println("i:"+i+"\n"+" posInicialx:"+posInicialx+" y:"+y+" largura:"+largura); 
		}	
		
		// DESENHA TRIGANGULO BRANCO
				int ajustaX=172;
				int ajustaY=-104;
				
				
				Point2D.Float p1= new Point2D.Float(200+ajustaX,180+ajustaY); // triangulo vermelho
				Point2D.Float p2 = new Point2D.Float(190+ajustaX,160+ajustaY);
				Point2D.Float p3 = new Point2D.Float(210+ajustaX,160+ajustaY);
				DTriangulo(g2d,p1,p2,p3,Color.white);
	}
	
	public void DCasaAmarela(Graphics2D g2d) {
	
		float posInicialx= VTabuleiro.larguraCasaInicial+132;
		float posInicialy=  VTabuleiro.larguraCasaInicial;
		float largura = (float) 43.8;
		
		int icor=2;
		
		float y=posInicialy;
		int i,j;
		i=j=0;
		
		Color c = Color.white;
		Color[] v = VTabuleiro.getVCores();
		//System.out.println("\n-----CASA AMARELA ----");
		for(i=0;i<3;i++) {						
			for(j=0;j<6;j++) {
				
				if (i>0) {
					
					
					if (i==2 && j==4) {
						c=v[icor];
					}
					
					else if(i==1 && j<5) {
						c=v[icor];
					}
					else {
						c = Color.white;
					}		
				}
				
				else {
					
					if (j ==4 ){ // casa preta 
						c=Color.DARK_GRAY;
					}
					else {
					 c = Color.white;
					}
				}				
				DQuadrado(g2d,posInicialx, y,largura, c);
				//System.out.println("	j:"+j+"\n"+"	posInicialx:"+posInicialx+"\n"+"	y:"+y+" largura:"+largura);
				posInicialx+=largura;					
			}			
			posInicialx=VTabuleiro.larguraCasaInicial+132;
			y+=largura;
			//System.out.println("i:"+i+"\n"+" posInicialx:"+posInicialx+" y:"+y+" largura:"+largura); 
		}			
		
		// DESENHA TRIGANGULO BRANCO
		int ajustaX=381;
		int ajustaY=192;
		
		
		Point2D.Float p1= new Point2D.Float(200+ajustaX,180+ajustaY); // triangulo vermelho
		Point2D.Float p2 = new Point2D.Float(220+ajustaX,170+ajustaY);
		Point2D.Float p3 = new Point2D.Float(220+ajustaX,190+ajustaY);
		DTriangulo(g2d,p1,p2,p3,Color.white);
	}
	
	public void DCasaVermelha(Graphics2D g2d)  {
		

		float posInicialx= 0;
		float posInicialy=  VTabuleiro.larguraCasaInicial;
		float largura = (float) 43.8;
		
		int icor=0;
		
		float y=posInicialy;
		int i,j;
		i=j=0;
		
		Color c = Color.white;
		Color[] v = VTabuleiro.getVCores();
		
		//System.out.println("\n-----CASA VERMELHA ----");
		for(i=0;i<3;i++) {
						
			for(j=0;j<6;j++) {
				
				
				if (i<=1) {					
					if (j==1 && i==0  ) { // casa diferente de cor
						c=v[icor];
						
					}
					
									
					else if(i==1 && j>0) {						
						c=v[icor];
					}
					else {
						c = Color.white;						
					}				
				}						
				else {
					
					if (i==2 && j==1) { // casa preta
						c=Color.DARK_GRAY;
					}
					else {
						 c = Color.white;	
					}
				}				
				DQuadrado(g2d,posInicialx, y,largura, c);
				
				
				//System.out.println("	j:"+j+"\n"+"	posInicialx:"+posInicialx+"\n"+"	y:"+y+" largura:"+largura); // coordenada
				
				posInicialx+=largura;	
				
			}			
			
			posInicialx=0;
			y+=largura;
			
			//System.out.println("i:"+i+"\n"+" posInicialx:"+posInicialx+" y:"+y+" largura:"+largura); 
		}
		
		int ajustaX=-73;
		int ajustaY=135;
		
		
		Point2D.Float p1= new Point2D.Float(150+ajustaX,150+ajustaY); // triangulo vermelho
		Point2D.Float p2 = new Point2D.Float(130+ajustaX,140+ajustaY);
		Point2D.Float p3 = new Point2D.Float(130+ajustaX,160+ajustaY);
		DTriangulo(g2d,p1,p2,p3,Color.white);
		
		
	}		
	public void DCasaAzul(Graphics2D g2d) {
		
		float posInicialx=VTabuleiro.larguraCasaInicial;
		float posInicialy=  VTabuleiro.larguraCasaInicial+132;
		float largura = (float) 43.8;
		
		int icor=3;
		
		float y=posInicialy;
		int i,j;
		i=j=0;
		
		Color c = Color.white;
		Color[] v = VTabuleiro.getVCores();
		
		//System.out.println("\n-----CASA AZUL ----");
		for(i=0;i<3;i++) {
						
			for(j=0;j<6;j++) {
				
				if (i>=1) {
					
					if (j==4 && i==2) {
						c=Color.DARK_GRAY;
					}
									
					else if(i==1 && j<5) {
						
						c=v[icor];
					}
					else {
						c = Color.white;						
					}				
				}						
				else {
					
					if (j==4) { // casa preta
						c=v[icor];
					}
					else {

					 c = Color.white;			
					}
				}
				
				DQuadrado(g2d,posInicialx, y,largura, c);
				
				//System.out.println("	j:"+j+"\n"+"	posInicialx:"+posInicialx+"\n"+"	y:"+y+" largura:"+largura);
				y+=largura;					
			}			
			posInicialx+=largura;
			y=posInicialy;
			//System.out.println("i:"+i+"\n"+" posInicialx:"+posInicialx+" y:"+y+" largura:"+largura); 
		}	
		
		int ajustaX=156;
		int ajustaY=441;
		
		
		Point2D.Float p1= new Point2D.Float(130+ajustaX,140+ajustaY); // triangulo azul
		Point2D.Float p2 = new Point2D.Float(120+ajustaX,160+ajustaY);
		Point2D.Float p3 = new Point2D.Float(140+ajustaX,160+ajustaY);
		DTriangulo(g2d,p1,p2,p3,Color.white);
	}
	
	public void DQuadrado(Graphics2D g2d, float x,  float y, float tamanho, Color c) {
		

		Rectangle2D rt1 = new Rectangle2D.Float(x,y,tamanho,tamanho);	
		g2d.setPaint(c);
		g2d.setStroke(new BasicStroke(1));
		g2d.fill(rt1);
		g2d.setPaint(Color.black);
		g2d.draw(rt1);
		
	}
	
	public void DCirculosInicio(Graphics2D g2d) {
		
		// raio = 39
		
		float raio = (float) 39;
		Color[] vCores= VTabuleiro.getVCores();
		
		float posInicialx=VTabuleiro.larguraCasaInicial;
		float deslocamento1= (float) ((raio)*1.5) ;	
		float deslocamento = raio/2;
		float simetriay = 0;
		//float simetria 
		
		float posInicialy=VTabuleiro.larguraCasaInicial+132;	
		float centroCasaInicial =  posInicialx/2;
		
		float proximaCasaX =0;
		float proximaCasaY =0;
		
		//float proximaCasaY = 
		
		
		int i =0;
		//(esquerda-> direita)
		
		for (i=0;i<2;i++) {			
			DCirculo(g2d,proximaCasaX+centroCasaInicial-deslocamento1,proximaCasaY+centroCasaInicial-deslocamento1+simetriay,raio,Color.white);
			DCirculo(g2d,proximaCasaX+centroCasaInicial+deslocamento,proximaCasaY+centroCasaInicial-deslocamento1+simetriay,raio,Color.white);
			
		
			simetriay+=2*(raio);		
		}	
		proximaCasaX=posInicialx+132;
		simetriay=0;
		
		
		for (i=0;i<2;i++) {			
			DCirculo(g2d,proximaCasaX+centroCasaInicial-deslocamento1,proximaCasaY+centroCasaInicial-deslocamento1+simetriay,raio,Color.white);
			DCirculo(g2d,proximaCasaX+centroCasaInicial+deslocamento,proximaCasaY+centroCasaInicial-deslocamento1+simetriay,raio,Color.white);			
			simetriay+=2*(raio);	
		}
		
		///// casas abaixo (azul e amarelo)
		
		proximaCasaY=posInicialx - 25  ;
		proximaCasaX=0;
		
		

		for (i=0;i<2;i++) {			
			DCirculo(g2d,proximaCasaX+centroCasaInicial-deslocamento1,proximaCasaY+centroCasaInicial-deslocamento1+simetriay,raio,Color.white);
			DCirculo(g2d,proximaCasaX+centroCasaInicial+deslocamento,proximaCasaY+centroCasaInicial-deslocamento1+simetriay,raio,Color.white);
			
			simetriay+=2*(raio);		
		}
		
		
//		proximaCasaX=posInicialx+132; // ok
//		proximaCasaY+=132+deslocamento ; // melhorar
//		simetriay=0;
		
		
		proximaCasaX=posInicialx+132;
		proximaCasaY=posInicialy ;
		simetriay = 0 ;

		for (i=0;i<2;i++) {			
			DCirculo(g2d,proximaCasaX+centroCasaInicial-deslocamento1,proximaCasaY+centroCasaInicial-deslocamento1+simetriay,raio,Color.white);
			DCirculo(g2d,proximaCasaX+centroCasaInicial+deslocamento,proximaCasaY+centroCasaInicial-deslocamento1+simetriay,raio,Color.white);			
			simetriay+=2*(raio);	
		}
		
		
	}
	
	public void DCirculo(Graphics2D g2d,float x, float y, float raio,Color c) {
		
		Ellipse2D e1 = new Ellipse2D.Float(x, y, raio,raio);
//		e1.setFrame(rt2); colocando em outro frame
		
		g2d.setStroke(new BasicStroke(1));
		g2d.setPaint(c);
		g2d.fill(e1);
		g2d.setPaint(Color.black);
		g2d.draw(e1);
		
	}
	
	
	
//	public void DCriaPeao(Graphics2D g2d,int x, int y){
//		
//		//Total 16 peoes
//
//		
//		DCriaPeao(g2d, x,y,new Color(53,84,250));
//	
//	}
	

	
	public void DCriaPeao(Graphics2D g2d,int x, int y,Color c) {
		
		// Raio maior padrao =35
		// largura padrao (quadrado) 20
		// Raio menor padrao = 10
		
		float raioCMaior=35;
		float raioCMenor=25;
		float largQuadrado=25;
		

		Ellipse2D b1 = new Ellipse2D.Float(x, y, raioCMaior,raioCMaior); // Base maior
		//e1.setFrame(rt2); colocando em outro frame	
		g2d.setStroke(new BasicStroke(2));
		g2d.setPaint(c);
		g2d.fill(b1);
		g2d.setPaint(Color.black);
		g2d.draw(b1);
		
		Rectangle2D rt1 = new Rectangle2D.Float(x+5,y-2,largQuadrado,largQuadrado);	// Base maior
		g2d.setPaint(c);
		g2d.setStroke(new BasicStroke(1));
		g2d.fill(rt1);
		g2d.setPaint(Color.black);
		g2d.draw(rt1);
					
		Ellipse2D b2 = new Ellipse2D.Float(x+5, y-10, raioCMenor,raioCMenor); // Base cabeça	
		g2d.setStroke(new BasicStroke(1));
		g2d.setPaint(c);
		g2d.fill(b2);
		g2d.setPaint(Color.black);
		g2d.draw(b2);
		
	}
	
	public void DTriangulo(Graphics2D g2d, Point2D.Float p, Point2D.Float q, Point2D.Float r , Color c ) {
		
		Polygon t1 = new Polygon();
		
		t1.addPoint((int)p.x,(int)p.y);
		t1.addPoint((int)q.x,(int)q.y);
		t1.addPoint((int)r.x,(int)r.y);
		
		g2d.setPaint(c);
		g2d.fill(t1);
		g2d.setPaint(Color.black);
		g2d.draw(t1);
	
	}	
	
	public static float getLargura() {
		return largura;
	}

	public static void setLargura(float largura) {
		VTabuleiro.largura = largura;
	}

	public static float getAltura() {
		return altura;
	}

	public static void setAltura(float altura) {
		VTabuleiro.altura = altura;
	}
	public static Color[] getVCores() {
		return VTabuleiro.vCores;
	}
	
	public void redesenha(Graphics2D g2d, int x, int y) { // testar para sobrescrever

//        this.xt = x;
//        this.yt = y;

       
        
//		move_peao(x,y);	
        

        //e1.setFrame(0, e1.getY(), e1.getWidth(), e1.getHeight());

        //repaint();
		
    }


	
	public Color encontraCor(String s) {
		
		Color c;
		if (s.equals("vermelho")) {
			c=vCores[0];
		}
		else if (s.equals("verde")) {
			c=vCores[1];
		}
		else if (s.equals("amarelo")) {
			c=vCores[2];
		}	
		else {
			c=vCores[3];
		}	
		return c; 
	}
	
	public void dBarreiraMesmaCor(Graphics2D g2d,int x, int y,String s) {
				
			Color c= encontraCor(s);
						
			// Raio maior padrao =35
			// largura padrao (quadrado) 20
			// Raio menor padrao = 10
			
			float raioCMaior=40;
			float raioCMenor=25;			

			Ellipse2D b1 = new Ellipse2D.Float(x, y, raioCMaior,raioCMaior); // Base maior
			//e1.setFrame(rt2); colocando em outro frame	
			g2d.setStroke(new BasicStroke(2));
			//g2d.setPaint(c);
			//g2d.fill(b1);
			g2d.setPaint(c.black);
			g2d.draw(b1);
			
			
			Ellipse2D b3 = new Ellipse2D.Float(x+1, y+1, raioCMaior-2,raioCMaior-2); // linha cor meio
			//e1.setFrame(rt2); colocando em outro frame	
			g2d.setStroke(new BasicStroke(2));
			g2d.setPaint(c);
			//g2d.fill(b1);
			g2d.draw(b3);
			
				
			Ellipse2D b2 = new Ellipse2D.Float(x+7, y+7, raioCMenor,raioCMenor); // Base cabeça	
			g2d.setStroke(new BasicStroke(1));
			g2d.setPaint(c);
			g2d.fill(b2);
			//g2d.setPaint(c);
			g2d.draw(b2);						
	}
	
	public void dBarreiraCorDiferente(Graphics2D g2d,int x, int y,String s, String s2) {
			
			Color c= encontraCor(s);
			Color c2= encontraCor(s2);
						
			// Raio maior padrao =35
			// largura padrao (quadrado) 20
			// Raio menor padrao = 10
			
			float raioCMaior=40;
			float raioCMenor=25;			

			Ellipse2D b1 = new Ellipse2D.Float(x, y, raioCMaior,raioCMaior); // Base maior
			//e1.setFrame(rt2); colocando em outro frame	
			g2d.setStroke(new BasicStroke(2));
			g2d.setPaint(c);
			g2d.fill(b1);
			g2d.setPaint(c);
			g2d.draw(b1);
			
			
			Ellipse2D b3 = new Ellipse2D.Float(x, y, raioCMaior,raioCMaior); // Base maior
			//e1.setFrame(rt2); colocando em outro frame	
			g2d.setStroke(new BasicStroke(1));
			//g2d.setPaint(c);
			//g2d.fill(b1);
			g2d.setPaint(c.DARK_GRAY);
			g2d.draw(b3);
				
			Ellipse2D b2 = new Ellipse2D.Float(x+7, y+7, raioCMenor,raioCMenor); // Base cabeça	
			g2d.setStroke(new BasicStroke(1));
			g2d.setPaint(c2);
			g2d.fill(b2);
			g2d.setPaint(c2);
			g2d.draw(b2);						
	
	}
	
	public int[] getCoordenadaPeaoIntXY(int i) {
		
		int[] vXY = new int[2]; // x e y
				
		IPeao p = (IPeao) vpeoes[i];			
		return vXY;
	}
//	
//	public void ExibeVpeao() {		
//		for(Object p: this.vpeoes) {			
//			IPeao pe = (IPeao) p;
//			pe.Exibe();
//		}
//	}


	// ------------ Override
	
	@Override
	public double getX1() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public void setX1(double x1) {
		// TODO Auto-generated method stub		
	}


	@Override
	public double getY1() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setY1(double y1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean eIgualCoordenada(ICoordenada n) {
		// TODO Auto-generated method stub
		return false;
	}
	

   //------ IPeao


	@Override
	public int getId() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getIntCor() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getCorId() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isNoTabuleiro() {
		// TODO Auto-generated method stub
		return false;
	}



	@Override
	public ICoordenada getXY() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setXY(ICoordenada n) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getPosicao() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setPosicao(int posicao) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isCasaSaida() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setCasaSaida(boolean casaSaida) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isCasaInicial() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setCasaInicial(boolean casaInicial) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isBarreira() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setBarreira(boolean barreira) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isAbrigo() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setAbrigo(boolean abrigo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isCasaFinal() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setCasaFinal(boolean casaFinal) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ICoordenada getPosicaoCasaSaidaVermelho() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ICoordenada getPosicaoCasaSaidaVerde() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ICoordenada getPosicaoCasaSaidaAmarelo() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public ICoordenada getPosicaoCasaSaidaAzul() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String getCor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCorS(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean eCoordenadaIgual(IPeao p) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getIndiceCor(String s) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ICoordenada getCoordenadaInicialSCor(String cor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean MoveCasaSaida() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int MovePeao(int peao, int dado) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public void Exibe() {
//		// TODO Auto-generated method stub
//		System.out.println("\t Cor:" + peao.getCor());
//		System.out.println("\t\tID:"+peao.getId()+"\n\t\t"+"Posicao tb:"+peao.getPosicao());
////		System.out.println("\t\t X:"+peao.getPosicaox());
////		System.out.println("\t\t Y:"+peao.getPosicaoy());
//		System.out.println("------// --");
	}
	


	
	// ------------------------ Observer

//	@Override
//	public void addObserver(Observador o) {
//		// TODO Auto-generated method stub
//		lobs.add(o);
//	}
//	@Override
//	public void removeObserver(Observador o) {
//		// TODO Auto-generated method stub
//		lobs.remove(o);
//	}
//	@Override
//	public Object getDados() {
//		// TODO Auto-generated method stub
//		return this;
//	}
//	@Override
//	public void Notify(int i) {
//		// TODO Auto-generated method stub
//		for( Observador obj : this.lobs) {
//			obj.update(i);
//		}
//	}
	
	//------------------
	
	@Override
	public String getCorIntS(int id) {
		// TODO Auto-generated method stub
		return null;
	}


	//----- Coordenadas
	
	@Override
	public void setXY(double x1, double x2) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void ExibeCoordenadas() {
		// TODO Auto-generated method stub
		return ;
	}

	// ------- ICasas

	@Override
	public boolean eBarreira() {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean eAbrigo() {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public void setCasaAbrigo(boolean t) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void setCasaBarreira(boolean t) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void ReiniciaCasa() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public int getQtdPeao() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public void setTemPeao(boolean t) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public boolean getTemPeao() {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public int IncrementaPeaoCasa() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public int DecrementaPeaoCasa() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public int QualTipoBarreira() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public String getCor1() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public String getCor2() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public boolean eCasaZerada() {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public int getPosicaoCasa() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public void ExibeStatus() {
		// TODO Auto-generated method stub
		return;
	}


	@Override
	public void setCorPeao(int i, String s) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void update() {
		// TODO Auto-generated method stub
		//System.out.println("O model mudou");
		repaint();
		
	}


	@Override
	public void setCorCasa(String s) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public String getCorCasa() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public boolean eCasaSaida() {
		// TODO Auto-generated method stub
		return false;
	}




	

	




}
