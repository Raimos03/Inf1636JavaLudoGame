package model;


class Tabuleiro {

		//private double[] Area = new double[2];
		
		private static Coordenada[] vCasaComum = new Coordenada[52]; // fica na view
		private static Coordenada[] vCasaVitoriaVermelha = new Coordenada[6];  // fica na view
		private static Coordenada[] vCasaVitoriaVerde = new Coordenada[6]; // fica na view
		private static Coordenada[] vCasaVitoriaAmarela = new Coordenada[6]; // fica na view
		private static Coordenada[] vCasaVitoriaAzul = new Coordenada[6]; // fica na view
		
		Object[] vPeao = new Object[16];
	
		
		public Tabuleiro () { // inicializo as coordenadas
			
			InicializaVcasas();
		
		}
		
		// Pensar nesta soluçao
		
		public int getDistanciaFinal( int numero , int tipo) {
			
			if (tipo ==1) { // casa comum			
				return Tabuleiro.vCasaComum.length;			
			}						
			if (tipo==2) {			
				return Tabuleiro.vCasaVitoriaVermelha.length;
			}		
			else if (tipo==3) {				
				return Tabuleiro.vCasaVitoriaVerde.length;
			}
			else if (tipo==4) {				
				return Tabuleiro.vCasaVitoriaAmarela.length;
			}		
			else {				
				return Tabuleiro.vCasaVitoriaAzul.length;  // victory Road
			}		
		}
		

//		public double[] getArea() {
//			return Area;
//		}
//
//		public void setArea(double[] area) {
//			Area = area;
//		}

		public Coordenada[] getvCasaComum() {
			return vCasaComum;
		}

		public void setvCasaComum(Coordenada[] vCasaComum) {
			Tabuleiro.vCasaComum = vCasaComum;
		}


		public Coordenada[] getvCasaVitoriaVermelha() {
			return vCasaVitoriaVermelha;
		}
		public Coordenada[] getvCasaVitoriaVerde() {
			return vCasaVitoriaVerde;
		}
		public Coordenada[] getvCasaVitoriaAmarela() {
			return vCasaVitoriaAmarela;
		}
		public Coordenada[] getvCasaVitoriaAzul() {
			return vCasaVitoriaAzul;
		}


		public void setvCasaVitoriaAmarela(Coordenada[] vCasaVitoria) {
			Tabuleiro.vCasaVitoriaAmarela = vCasaVitoria;
		}
		
		public void setvCasaVitoriaVermelha(Coordenada[] vCasaVitoria) {
			Tabuleiro.vCasaVitoriaVermelha = vCasaVitoria;
		}
		public void setvCasaVitoriaAzul(Coordenada[] vCasaVitoria) {
			Tabuleiro.vCasaVitoriaAzul = vCasaVitoria;
		}
		public void setvCasaVitoriaVerde(Coordenada[] vCasaVitoria) {
			Tabuleiro.vCasaVitoriaVerde = vCasaVitoria;
		}
		
		
		
		public static Coordenada[] getVcasasComuns(){
			
			return Tabuleiro.vCasaComum;
		}
		public static Coordenada[] getVRoadVermelha(){
					
			return Tabuleiro.vCasaVitoriaVermelha;
		}
		public static Coordenada[] getVRoadVerde(){
			
			return Tabuleiro.vCasaVitoriaVerde;	
		}
		public static Coordenada[] getVRoadAmarela(){
			
			return Tabuleiro.vCasaVitoriaAmarela;	
		}
		public static Coordenada[] getVRoadAzul(){
					
			return Tabuleiro.vCasaVitoriaAzul;			
		}
		
		public void setVpeoes(Object[] vp){
			
			this.vPeao=vp;
			return;
		}


		public void InicializaVcasas() { // salva as posicoes x e y do tabuleiro em todos os vetores de posicao
			
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
				//System.out.println(""+i+"\t"+x+" "+y);		// debug casa e posicao x y 		
				vCasaComum[i]= new Coordenada(x,y);
				
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
		
		public void preencheVroad(Double x, Double y, Coordenada[] vroad, int direcao, int sinal ) {			
		
			int i =0;					
			for(i=0;i<6;i++) {			
				//System.out.println("\t\t"+i+"\t"+x+" "+y);
				vroad[i]= new Coordenada(x,y);
				
				if (direcao==1) { // anda x			
					x+=43.8*sinal;
				}				
				else { // anda y					
					y+=43.8*sinal;
				}					
			}	
			return ;
		}

}
	