package view;

import controler.ICoordenada;

class VCoordenada implements ICoordenada {
	
	private double x1;
	private double y1;

	VCoordenada (){		
		this.x1=0;
		this.y1=0;
	}

	VCoordenada (double x, double y ){
		
		x1=x;
		y1=y;		
	}
	public double getX1() {
		return x1;
	}
	public void setX1(double x1) {
		this.x1 = x1;
	}


	public double getY1() {
		return y1;
	}

	public void setY1(double y1) {
		this.y1 = y1;
	}
		
	public void setP1(double x, double y) {
		this.x1=x;
		this.y1=y;
	}

	
	// comparaçao de coordenadas recebe dois objetos
	public boolean eIgualCoordenada( VCoordenada n) {
		
		if (this.x1== n.x1 && this.y1 == n.y1){
			return true;
		}
		
		return false;
	}
	
	public String ExibeCoordenadas() {
		return "\tX:"+this.getX1()+ "\n\tY:"+this.getY1(); 
	}

	@Override
	public boolean eIgualCoordenada(ICoordenada n) {
		if (this.x1== n.getX1() && this.y1 == n.getY1()){
			return true;
		}
		
		return false;
	}
	
	// funcao que converte coordenada em point awt
	
	//public Point2D 
}
