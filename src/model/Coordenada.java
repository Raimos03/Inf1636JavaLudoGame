package model;

import controler.ICoordenada;

class Coordenada implements ICoordenada{
	
	private double x1;
	private double y1;

	Coordenada (){		
		this.x1=0;
		this.y1=0;
	}

	Coordenada (double x, double y ){	
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
			
	// comparaçao de coordenadas recebe dois objetos
	public boolean eIgualCoordenada( ICoordenada n) {
		
		if (this.x1== n.getX1() && this.y1 == n.getY1()){
			return true;
		}
		
		return false;
	}
	
	public void ExibeCoordenadas() {
		
		System.out.println("\tX:"+this.getX1()+ "\n\tY:"+this.getY1() );
		return ;
	}

	@Override
	public void setXY(double x1, double x2) {
		// TODO Auto-generated method stub	
	}
	
}
