package model;

class AreaQuadrado {
	private Coordenada p1;
	private Coordenada p2;
	
	private double x1;
	private double x2;
	
	private double y1;
	private double y2;
	
	public AreaQuadrado(Coordenada p1, Coordenada p2){
		
		// os pontos sao na diagonal 
		
		//  x1y1
		//
		//
		//            x2y2
		
			this.p1=p1;
			this.p2=p2;
			
			this.x1=p1.getX1();
			this.x2=p2.getX1();
			this.y1=p1.getY1();
			this.y2=p2.getY1();
	}
	
	public boolean EstaDentro(Coordenada p) {
		
		double px=p.getX1();
		double py=p.getY1();
		
		if (px>=x1 && px <=x2) {
			
			if(py>=y1 && py<=y2) {
				return true;
			}			
		}	
		return false;
	}
	
	
	//public esta na area dos pontos
	
	
	// funcao que retorna os pontos em float ou double
	
	// funcao que retorna os pontos em point Awt
}
