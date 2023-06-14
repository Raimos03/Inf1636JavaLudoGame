package controler;



public interface ICoordenada {
	
	public void setXY(double x1, double x2);
	public double getX1();
	public void setX1(double x1);
	public double getY1();
	public void setY1(double y1);
	public boolean eIgualCoordenada( ICoordenada n);
	public String ExibeCoordenadas();

}
