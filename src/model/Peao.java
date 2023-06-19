package model;
import controler.IPeao;
import controler.ICoordenada;

class Peao implements IPeao, ICoordenada {
	
	
	static int idTotPeao=0;
	
	static String[] cores ={"vermelho","verde","amarelo","azul"}; //talvez tenha que excluir
	
	
	// casa para saida de cada cor
	
	static Coordenada PosicaoCasaSaidaVermelho = new Coordenada(43.99999,262.7999); // OK
	static Coordenada PosicaoCasaSaidaVerde = new Coordenada(350.6,43.8); // OK
	static Coordenada PosicaoCasaSaidaAmarelo = new Coordenada(569.6,350.40); //OK
	static Coordenada PosicaoCasaSaidaAzul = new Coordenada(263,569.4); //OK

	//--------------------
		
	private Player PlayerPai;
	private int Id;
	private int IntCor;
	
	private ICoordenada xy;	//coordenada onde esta no tabuleiro
	private int Posicao=-1;         // -1 (casa inicial) 0.. - primeirca casa tabuleiro  posicao no vetor do tabuleiro, inicia no indice da casa de saida 
	

	
	private boolean CasaSaida=false;  // casa de saida de cada cor
	private boolean CasaInicial=true;  // assim que nasce o peao e começa o jogo
	
	private boolean Barreira=false; // esta na barreira
	private boolean Abrigo=false;  //esta no abrigo
	private boolean CasaFinal=false; // victory road
	private boolean CasaComum=false;
	
	
	public Peao (String scor, Player pai) {
		
		this.PlayerPai=pai;
		this.Id = idTotPeao;	
		this.IntCor=getIndiceCor(scor);
	
		this.xy =new Coordenada() ; //getCoordenadaInicialSCor(Peao.getCorS(IntCor));
		Peao.ContabilizaPeao();	
		
	}
	
	public int getId() {
		return Id;
	}

	
	public int getIntCor() {
		return this.IntCor;
	}

	public String getCor() {
		return cores[this.IntCor];
	}
	public int getCorId() {
		return this.IntCor ;
	}

	public boolean isNoTabuleiro() {
		if (this.CasaInicial==true) {
			return true;
		}
		return false;
	}

	public ICoordenada getXY() {
		return xy;
	}
	public void setXY(Coordenada n) {
		this.xy = n;
	}

	public int getPosicao() {
		return Posicao;
	}

	public void setPosicao(int posicao) {
		Posicao = posicao;
	}

	public boolean isCasaSaida() {
		return CasaSaida;
	}

	public void setCasaSaida(boolean casaSaida) {
		CasaSaida = casaSaida;
	}

	public void setCasaInicial(boolean casaInicial) {
		CasaInicial = casaInicial;
	}
	
	
	public boolean isCasaInicial() {
		return this.CasaInicial;
	}

	public boolean isBarreira() {
		
		return this.Barreira;
	}

	public void setBarreira(boolean barreira) {
		Barreira = barreira;
	}

	public boolean isAbrigo() {
		return this.Abrigo;
	}

	public void setAbrigo(boolean abrigo) {
		Abrigo = abrigo;
	}

	public static void ContabilizaPeao() {	
		idTotPeao++;
	}
	
	public boolean isCasaFinal() {
		
		return this.CasaFinal;
	}


	public void setCasaFinal(boolean casaFinal) {
		CasaFinal = casaFinal;
	}
	

	public Player getPlayerPai() {
		
		return this.PlayerPai;
	}
	
	

	public static void setPosicaoCasaSaidaVermelho(Coordenada posicaoCasaSaidaVermelho) {
		PosicaoCasaSaidaVermelho = posicaoCasaSaidaVermelho;
	}




	public static void setPosicaoCasaSaidaVerde(Coordenada posicaoCasaSaidaVerde) {
		PosicaoCasaSaidaVerde = posicaoCasaSaidaVerde;
	}



	public static void setPosicaoCasaSaidaAmarelo(Coordenada posicaoCasaSaidaAmarelo) {
		PosicaoCasaSaidaAmarelo = posicaoCasaSaidaAmarelo;
	}



	public static void setPosicaoCasaSaidaAzul(Coordenada posicaoCasaSaidaAzul) {
		PosicaoCasaSaidaAzul = posicaoCasaSaidaAzul;
	}
	

	
	////// ------ Metodos especificos --------
	
	


	// metodo estatico que retorna a cor
	public  String getCorIntS(int id) {	
		return cores[id];
	}
	
	
	// metodo de comparacao de peao por coordenada
	public boolean eCoordenadaIgual(Peao p) { // testar
		
		if (this.xy.eIgualCoordenada(p.xy)==true) {
			return true;
		}
		
		return false;
	}
	
	
	public int getIndiceCor(String s) { // retorna o indice da cor - Int
			
		if(s.equals("vermelho")) {
			return 0;
		}
		else if (s.equals("verde")){
			return  1;
		}
		else if (s.equals("amarelo")){
			return  2;
		}
		else {
			return  3;
		}
		
	}
	
	


	
	public int MovePeao(Coordenada nc) { // Jogada normal
					
		this.setXY(nc);
		return 1;
	}
	
	public void Exibe() {
		
		System.out.println("\t Cor:" + this.getCor());
		//System.out.println("\t\tIDPeao:"+ Peao.idTotPeao);
		System.out.println("\t\tId:"+this.getId()+"\n\t\t"+"Posicao tb:"+this.getPosicao());
		System.out.println("\t\t X:"+ this.getXY().getX1() + " --  Casa Inicial:"+ this.CasaInicial + " -- Casa Final:"+ this.CasaFinal);
		System.out.println("\t\t Y:"+ this.getXY().getY1()+ " -- Casa Saida:"+ this.CasaSaida + " -- Casa Barreira:"+ this.Barreira);		
		//System.out.println("\t\t--  Casa Inicial:"+ this.CasaInicial);
		//System.out.println("\t\t-- Casa Saida:"+ this.CasaSaida );
		//System.out.println("\t\t-- Casa Final:"+ this.CasaFinal);
		//System.out.println("\t\t-- Casa Barreira:"+ this.Barreira);
		System.out.println("\t\t\t -- Casa Abrigo:"+ this.Abrigo);
		System.out.println("\t\t\t -- Casa Comum:"+ this.CasaComum);
		//System.out.println("------");
	}
//	
//	@Override
//	public int getPosicaox() {
//		// TODO Auto-generated method stub
//		int x = (int) this.getXY().getX1();
//		
//		return x;
//	}
//
//
//	@Override
//	public int getPosicaoy() {
//		// TODO Auto-generated method stub
//		int y =(int) this.getXY().getY1();
//		return y;
//	}


	public void setCasaComum(boolean b) {
		this.CasaComum=b;
		
	}


	@Override
	public void setXY(ICoordenada n) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public String getCorS(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean eCoordenadaIgual(IPeao p) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ICoordenada getCoordenadaInicialSCor(String cor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean MoveCasaSaida() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int MovePeao(int idpeao, int dado) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setXY(double x1, double x2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double getX1() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setX1(double x1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double getY1() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setY1(double y1) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public boolean eIgualCoordenada(ICoordenada n) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public   ICoordenada getPosicaoCasaSaidaVermelho() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public  ICoordenada getPosicaoCasaSaidaVerde() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public   ICoordenada getPosicaoCasaSaidaAmarelo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public  ICoordenada getPosicaoCasaSaidaAzul() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

	// ----- ICOORDENADA
	
	@Override
	public void ExibeCoordenadas() {
		// TODO Auto-generated method stub
		return ;
	}

	@Override
	public boolean isCasaComum() {
		
		return this.CasaComum;
	}

	
}
