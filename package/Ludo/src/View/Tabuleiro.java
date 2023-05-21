package View;

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

	public void paintComponent(Graphics g) {
		
		 // tabuleiro
		super.paintComponent(g);	
		Graphics2D g2d=(Graphics2D) g;
		
//		QuadradoCasa c1 = new QuadradoCasa(200,200);
//		c1.setBounds(500,50,50,50);		
		
//		Rectangle2D rt = new Rectangle2D.Float(0,100,300,300);		//teste
//		g2d.setPaint(Color.GREEN);
//		g2d.fill(rt);
		 
		
		DCasaInicial(g2d);
		DCasaVitoriaCentro(g2d);
		DCasas(g2d);	
		
		//DPeao()
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
		
		
		// Quadrado centro
		Ellipse2D.Float el = new Ellipse2D.Float(centro.x-10,centro.y-10,20,20);	
		g2d.setStroke(new BasicStroke(1));
		g2d.fill(el);
		g2d.setPaint(Color.yellow);
		g2d.draw(el);
		
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
		
		for(i=0;i<3;i++) {
			
			
			for(j=0;j<6;j++) {
				
				if (j>0) {
					
					if (i==2 && j ==1) {
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
		}
		
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
					 c = Color.white;			
				}
				
				DQuadrado(g2d,posInicialx, y,largura, c);
				posInicialx+=largura;	
				
			}
		
			
			posInicialx=Tabuleiro.larguraCasaInicial+132;
			y+=largura;
		}
		
	
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
		
		for(i=0;i<3;i++) {
						
			for(j=0;j<6;j++) {
				
				if (i<=1) {
					
					if (j==1) {
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
					 c = Color.white;			
				}
				
				DQuadrado(g2d,posInicialx, y,largura, c);
				posInicialx+=largura;					
			}
			
			posInicialx=0;
			y+=largura;
		}
		
	
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
		
		for(i=0;i<3;i++) {
						
			for(j=0;j<6;j++) {
				
				if (i>=0) {
					
					if (j==4 && i ==0) {
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
					 c = Color.white;			
				}
				
				DQuadrado(g2d,posInicialx, y,largura, c);
				y+=largura;					
			}
			
			posInicialx+=largura;
			y=posInicialy;
		}
		
	}
	
	
	public void DQuadrado(Graphics2D g2d, float x,  float y, float tamanho, Color c) {
		
		
		
		Rectangle2D rt1 = new Rectangle2D.Float(x,y,tamanho,tamanho);	
		g2d.setPaint(c);
		g2d.setStroke(new BasicStroke(1));
		g2d.fill(rt1);
		g2d.setPaint(Color.black);
		g2d.draw(rt1);
		
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
