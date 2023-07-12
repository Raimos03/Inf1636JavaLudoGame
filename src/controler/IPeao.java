package controler;

public interface IPeao {

	public int getId();

	public int getIntCor();
	
	public String getCorIntS(int id);

	public String getCor();
	
	public int getCorId(); // duplicada

	public boolean isNoTabuleiro();
	
//	public void setNoTabuleiro(boolean noTabuleiro);

	public ICoordenada getXY();

	public void setXY(ICoordenada n);

	public int getPosicao();

	public void setPosicao(int posicao);

	public boolean isCasaSaida();
	
	public boolean isCasaComum();

	public void setCasaSaida(boolean casaSaida);

	public boolean isCasaInicial();

	public void setCasaInicial(boolean casaInicial);

	public boolean isBarreira();

	public void setBarreira(boolean barreira);

	public boolean isAbrigo();

	public void setAbrigo(boolean abrigo);
	
	public static void ContabilizaPeao() {
	}
	public boolean isCasaFinal();

	public void setCasaFinal(boolean casaFinal);

	//public Object getPlayerPai(); //**
	
	public ICoordenada getPosicaoCasaSaidaVermelho();

	public  ICoordenada getPosicaoCasaSaidaVerde();

	public  ICoordenada getPosicaoCasaSaidaAmarelo();

	public ICoordenada getPosicaoCasaSaidaAzul();	
	
	public  String getCorS(int id);
	
	public boolean eCoordenadaIgual(IPeao p); 
	
	public int getIndiceCor(String s); 
		
	public ICoordenada getCoordenadaInicialSCor(String cor); 
	
	public boolean MoveCasaSaida(); //** ver
	
	public int MovePeao(int idpeao, int dado); 
	
	public void Exibe();
	
	public void setCasaComum(boolean b);

	public double getX1();
	public double getY1();

	public void setX1(double x);
	public void setY1(double y);}	

