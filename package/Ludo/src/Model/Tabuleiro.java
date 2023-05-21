package Model;

class Tabuleiro {

		private double[] Area = new double[2];
		private Coordenada[] vCasaComum = new Coordenada[52];
		private Coordenada[] vCasaVitoria = new Coordenada[6];
		
	
		public int getDistanciaFinal( int numero , int tipo) {
			
			if (tipo ==1) { // casa comum
				
				return this.vCasaComum.length;
				
			}
			
			return this.vCasaVitoria.length;  // victory Road
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



		public Coordenada[] getvCasaVitoria() {
			return vCasaVitoria;
		}



		public void setvCasaVitoria(Coordenada[] vCasaVitoria) {
			this.vCasaVitoria = vCasaVitoria;
		}



}
	