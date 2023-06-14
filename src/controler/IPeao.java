package controler;

public interface IPeao {

	public int getId();

	public int getIntCor();
	
	public String getCorIntS(int id);

	public String getCor();
	
	public int getCorId(); // duplicada

	public boolean isNoTabuleiro();
	
	public void setNoTabuleiro(boolean noTabuleiro);

	public ICoordenada getXY();

	public void setXY(ICoordenada n);

	public int getPosicao();
	
//	public int getPosicaox();
//	
//	public int getPosicaoy();

	public void setPosicao(int posicao);

	public boolean isCasaSaida();

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

	//public IPlayer getPlayerPai();
	
	public ICoordenada getPosicaoCasaSaidaVermelho();

	public  ICoordenada getPosicaoCasaSaidaVerde();

	public  ICoordenada getPosicaoCasaSaidaAmarelo();

	public ICoordenada getPosicaoCasaSaidaAzul();

	
	
	public  String getCorS(int id);
	
	public boolean eCoordenadaIgual(IPeao p); 
	
	public int getIndiceCor(String s); 
		
	public ICoordenada getCoordenadaInicialSCor(String cor); 
	
	public boolean MoveCasaSaida();
	
	
	public int MovePeao(int idpeao, int dado); 
	
	public void Exibe();


}
	

