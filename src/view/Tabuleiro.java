package view;

import javax.swing.*;
import java.awt.*;
import java.awt.geom. *;

public class Tabuleiro extends JPanel implements IDesenha {
	
	private static float largura=660;
	private static float altura=660;
	private static float larguraCasaInicial= 263;
	private static float espacoEntreCasaInicial= 132;
	private static float deslocamentoCasaInicial = larguraCasaInicial + espacoEntreCasaInicial;

	
	private static Color[] vCores = {Color.red,Color.green,Color.yellow,Color.blue};
	//private Color vCoordenadaInicial	
	Tabuleiro(){
		
	}
	
	@Override
	public void Desenha() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void paintComponent(Graphics g) {		
		// tabuleiro
		super.paintComponent(g);	
		Graphics2D g2d=(Graphics2D) g;
		

		 		
		DCasaInicial(g2d);
		DCirculosInicio(g2d);
		DCasaVitoriaCentro(g2d);
		DCasas(g2d);
		
		// Cria triangulos
		
		
		// Cria Peao
		
		DCriaPeoes(g2d); // Falta implementar de todas as cores
		
		
		//mover peao
		
		// novas funcoes
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
		int posx = (int) Tabuleiro.larguraCasaInicial; // comeco do quadrado centro
		int posy = (int) Tabuleiro.larguraCasaInicial;
		Point2D.Float centro = new Point2D.Float(Tabuleiro.getLargura()/2,Tabuleiro.getAltura()/2);
				
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
		
		float posInicialx= Tabuleiro.larguraCasaInicial;
		float posInicialy=  Tabuleiro.larguraCasaInicial;
		float largura = (float) 43.8;
		
		int icor=1;
		
		float y=0;
		int i,j;
		i=j=0;	
		Color c = Color.white;
		Color[] v = Tabuleiro.getVCores();
		
		System.out.println("\n-----CASA VERDE ----");
		
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
			System.out.println("i:"+i+"\n"+" posInicialx:"+posInicialx+" y:"+y+" largura:"+largura); 
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
	
		float posInicialx= Tabuleiro.larguraCasaInicial+132;
		float posInicialy=  Tabuleiro.larguraCasaInicial;
		float largura = (float) 43.8;
		
		int icor=2;
		
		float y=posInicialy;
		int i,j;
		i=j=0;
		
		Color c = Color.white;
		Color[] v = Tabuleiro.getVCores();
		System.out.println("\n-----CASA AMARELA ----");
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
				System.out.println("	j:"+j+"\n"+"	posInicialx:"+posInicialx+"\n"+"	y:"+y+" largura:"+largura);
				posInicialx+=largura;					
			}			
			posInicialx=Tabuleiro.larguraCasaInicial+132;
			y+=largura;
			System.out.println("i:"+i+"\n"+" posInicialx:"+posInicialx+" y:"+y+" largura:"+largura); 
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
		float posInicialy=  Tabuleiro.larguraCasaInicial;
		float largura = (float) 43.8;
		
		int icor=0;
		
		float y=posInicialy;
		int i,j;
		i=j=0;
		
		Color c = Color.white;
		Color[] v = Tabuleiro.getVCores();
		
		System.out.println("\n-----CASA VERMELHA ----");
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
				
				
				System.out.println("	j:"+j+"\n"+"	posInicialx:"+posInicialx+"\n"+"	y:"+y+" largura:"+largura); // coordenada
				
				posInicialx+=largura;	
				
			}			
			
			posInicialx=0;
			y+=largura;
			
			System.out.println("i:"+i+"\n"+" posInicialx:"+posInicialx+" y:"+y+" largura:"+largura); 
		}
		
		int ajustaX=-73;
		int ajustaY=135;
		
		
		Point2D.Float p1= new Point2D.Float(150+ajustaX,150+ajustaY); // triangulo vermelho
		Point2D.Float p2 = new Point2D.Float(130+ajustaX,140+ajustaY);
		Point2D.Float p3 = new Point2D.Float(130+ajustaX,160+ajustaY);
		DTriangulo(g2d,p1,p2,p3,Color.white);
		
		
	}		
	public void DCasaAzul(Graphics2D g2d) {
		
		float posInicialx=Tabuleiro.larguraCasaInicial;
		float posInicialy=  Tabuleiro.larguraCasaInicial+132;
		float largura = (float) 43.8;
		
		int icor=3;
		
		float y=posInicialy;
		int i,j;
		i=j=0;
		
		Color c = Color.white;
		Color[] v = Tabuleiro.getVCores();
		
		System.out.println("\n-----CASA AZUL ----");
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
				
				System.out.println("	j:"+j+"\n"+"	posInicialx:"+posInicialx+"\n"+"	y:"+y+" largura:"+largura);
				y+=largura;					
			}			
			posInicialx+=largura;
			y=posInicialy;
			System.out.println("i:"+i+"\n"+" posInicialx:"+posInicialx+" y:"+y+" largura:"+largura); 
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
		Color[] vCores= Tabuleiro.getVCores();
		
		float posInicialx=Tabuleiro.larguraCasaInicial;
		float deslocamento1= (float) ((raio)*1.5) ;	
		float deslocamento = raio/2;
		float simetriay = 0;
		//float simetria 
		
		float posInicialy=Tabuleiro.larguraCasaInicial+132;	
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
	
	public void DCriaPeoes(Graphics2D g2d){
		
		//Total 16 peoes
		

		Color[] vCores= Tabuleiro.getVCores();	
		DCriaPeao(g2d, 268,400,new Color(53,84,250));
	
	}
	
	public void DCriaPeao(Graphics2D g2d,float x, float y,Color c) {
		
		// Raio maior padrao =35
		// largura padrao (quadrado) 20
		// Raio menor padrao = 10
		
		float raioCMaior=35;
		float raioCMenor=25;
		float largQuadrado=25;
		

		Ellipse2D b1 = new Ellipse2D.Float(x, y, raioCMaior,raioCMaior); // Base maior
//		e1.setFrame(rt2); colocando em outro frame	
		g2d.setStroke(new BasicStroke(2));
		g2d.setPaint(c);
		g2d.fill(b1);
		g2d.setPaint(Color.black);
		g2d.draw(b1);
		
		Rectangle2D rt1 = new Rectangle2D.Float(x+5,y-4,largQuadrado,largQuadrado);	// Base maior
		g2d.setPaint(c);
		g2d.setStroke(new BasicStroke(1));
		g2d.fill(rt1);
		g2d.setPaint(Color.black);
		g2d.draw(rt1);
		
			
		Ellipse2D b2 = new Ellipse2D.Float(x+5, y-15, raioCMenor,raioCMenor); // Base cabeça	
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
		Tabuleiro.largura = largura;
	}

	public static float getAltura() {
		return altura;
	}

	public static void setAltura(float altura) {
		Tabuleiro.altura = altura;
	}
	public static Color[] getVCores() {
		return Tabuleiro.vCores;
	}
}
