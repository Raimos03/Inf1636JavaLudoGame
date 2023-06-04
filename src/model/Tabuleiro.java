package model;

class Tabuleiro {

		private double[] Area = new double[2];
		private Coordenada[] vCasaComum = new Coordenada[52];
		private Coordenada[] vCasaVitoriaVermelha = new Coordenada[6];
		private Coordenada[] vCasaVitoriaVerde = new Coordenada[6];
		private Coordenada[] vCasaVitoriaAmarela = new Coordenada[6];
		private Coordenada[] vCasaVitoriaAzul = new Coordenada[6];
		
		// Pensar neste soluçao
		
		public int getDistanciaFinal( int numero , int tipo) {
			
			if (tipo ==1) { // casa comum
				
				return this.vCasaComum.length;			
			}
						
			if (tipo==2) {			
				return this.vCasaVitoriaVermelha.length;
			}		
			else if (tipo==3) {
				
				return this.vCasaVitoriaVerde.length;
			}
			else if (tipo==4) {
				
				return this.vCasaVitoriaAmarela.length;
			}		
			else {
				
				return this.vCasaVitoriaAzul.length;  // victory Road
			}		
		}


		public double[] getArea() {
			return Area;
		}


		public void setArea(double[] area) {
			Area = area;
		}


		public Coordenada[] getvCasaComum() {
			return vCasaComum;
		}

		public void setvCasaComum(Coordenada[] vCasaComum) {
			this.vCasaComum = vCasaComum;
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
			this.vCasaVitoriaAmarela = vCasaVitoria;
		}
		
		public void setvCasaVitoriaVermelha(Coordenada[] vCasaVitoria) {
			this.vCasaVitoriaVermelha = vCasaVitoria;
		}
		public void setvCasaVitoriaAzul(Coordenada[] vCasaVitoria) {
			this.vCasaVitoriaAzul = vCasaVitoria;
		}
		public void setvCasaVitoriaVerde(Coordenada[] vCasaVitoria) {
			this.vCasaVitoriaVerde = vCasaVitoria;
		}


}
	