package model;

class Peao {
	
	
	static int idPeao=0;
	
	static String[] cores ={"vermelho","verde","amarelo","azul"}; //talvez tenha que excluir
	
	static double[] XPosicaoInicialVermelho= {1.0,2.0,5.0,6.0}; //como se fosse matriz
	static double[] YPosicaoInicialVermelho= {1.0,2.0,5.0,6.0}; // posicoes ficticias para iniciarmos
	
	static double[] XPosicaoInicialVerde= {3.0,4.0,7.0,8.0};
	static double[] YPosicaoInicialVerde= {3.0,4.0,7.0,8.0};
	
	static double[] XPosicaoInicialAmarelo= {9.0,10.0,13.0,14.0};
	static double[] YPosicaoInicialAmarelo= {9.0,10.0,13.0,14.0};
	
	static double[] XPosicaoInicialAzul= {11.0,12.0,15.0,16.0};
	static double[] YPosicaoInicialAzul= {11.0,12.0,15.0,16.0};
	
	// casa de saida
	
	static Coordenada PosicaoCasaSaidaVermelho = new Coordenada(5,6); 
	static Coordenada PosicaoCasaSaidaVerde = new Coordenada(7,8);
	static Coordenada PosicaoCasaSaidaAmarelo = new Coordenada(13,14);
	static Coordenada PosicaoCasaSaidaAzul = new Coordenada(15,16);

	//--------------------
	
	
	private Player PlayerPai;
	
	
	private int Id;
	private int IntCor;
	
	private Coordenada xy= new Coordenada();
	
	private int Posicao=-1;         // posicao no tabuleiro, inicia em 0 (que tbem e a casa inicial)
	
	private boolean NoTabuleiro=false; // false indica que esta na parte inicial dos peoes
	private boolean CasaSaida=false;
	private boolean CasaInicial=true;  // assim que nasce o peao
	private boolean Barreira=false;
	private boolean Abrigo=false;
	private boolean CasaFinal=false;
	
	public Peao (String scor, Player pai) {
		
		this.PlayerPai=pai;
		this.Id = idPeao;	
		this.IntCor=getIndiceCor(scor);
				
		this.NoTabuleiro=false;
		
		this.xy = getCoordenadaInicialSCor(Peao.getCorS(IntCor));
		
		this.CasaSaida=false;
		this.CasaInicial=false;
		this.Barreira=false;
		this.Abrigo=false;
		Peao.ContabilizaPeao();	
		
	}
	
	
	
	public int getId() {
		return Id;
	}

//	public void setId(int id) {
//		Id = id;
//	}
	
	public int getIntCor() {
		return this.IntCor;
	}

//	public void setIntCor(int indice) {
//		this.IntCor=indice;
//	}

	public int getCorId() {
		return IntCor;
	}

//	public void setCorId(int cor) {
//		IntCor = cor;
//	}

	public boolean isNoTabuleiro() {
		return NoTabuleiro;
	}

	public void setNoTabuleiro(boolean noTabuleiro) {
		NoTabuleiro = noTabuleiro;
	}

	public Coordenada getXY() {
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

	public boolean isCasaInicial() {
		return CasaInicial;
	}

	public void setCasaInicial(boolean casaInicial) {
		CasaInicial = casaInicial;
	}

	public boolean isBarreira() {
		return Barreira;
	}

	public void setBarreira(boolean barreira) {
		Barreira = barreira;
	}

	public boolean isAbrigo() {
		return Abrigo;
	}

	public void setAbrigo(boolean abrigo) {
		Abrigo = abrigo;
	}

	public static void ContabilizaPeao() {
		
		idPeao++;
	}
	
	public boolean isCasaFinal() {
		return CasaFinal;
	}


	public void setCasaFinal(boolean casaFinal) {
		CasaFinal = casaFinal;
	}
	

	public Player getPlayerPai() {
		
		return this.PlayerPai;
	}
	
	
	public static Coordenada getPosicaoCasaSaidaVermelho() {
		return PosicaoCasaSaidaVermelho;
	}


	public static void setPosicaoCasaSaidaVermelho(Coordenada posicaoCasaSaidaVermelho) {
		PosicaoCasaSaidaVermelho = posicaoCasaSaidaVermelho;
	}


	public static Coordenada getPosicaoCasaSaidaVerde() {
		return PosicaoCasaSaidaVerde;
	}


	public static void setPosicaoCasaSaidaVerde(Coordenada posicaoCasaSaidaVerde) {
		PosicaoCasaSaidaVerde = posicaoCasaSaidaVerde;
	}


	public static Coordenada getPosicaoCasaSaidaAmarelo() {
		return PosicaoCasaSaidaAmarelo;
	}


	public static void setPosicaoCasaSaidaAmarelo(Coordenada posicaoCasaSaidaAmarelo) {
		PosicaoCasaSaidaAmarelo = posicaoCasaSaidaAmarelo;
	}


	public static Coordenada getPosicaoCasaSaidaAzul() {
		return PosicaoCasaSaidaAzul;
	}


	public static void setPosicaoCasaSaidaAzul(Coordenada posicaoCasaSaidaAzul) {
		PosicaoCasaSaidaAzul = posicaoCasaSaidaAzul;
	}
	
	

	
	
	////// ------ Metodos especificos --------
	
	


		// metodo estatico que retorna a cor
	public static String getCorS(int id) {	
		return cores[id];
	}
	
		// metodo de comparacao de peao por coordenada
	public boolean eCoordenadaIgual(Peao p) {
		
		if (this.xy.eIgualCoordenada(p.xy)==true) {
			return true;
		}
		
		return false;
	}
	
	
	public int getIndiceCor(String s) { // retorna o indice da cor
			
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
	
	
		// metodo que recebe a posicao iniciar pela cor  e pelo idgeral
	public Coordenada getCoordenadaInicialSCor(String cor) {
		
			Coordenada nova= new Coordenada();
			int i = this.getIntCor(); //cor do peao
			
						
			if (i==0)  {			
				nova.setX1(Peao.XPosicaoInicialVermelho[i]);
				nova.setY1(Peao.YPosicaoInicialVermelho[i]);
				
			}		
			else if (i==1) {			
				nova.setX1(Peao.XPosicaoInicialVerde[i]);
				nova.setY1(Peao.YPosicaoInicialVerde[i]);
				
			}	
			else if(i==2) {					
				nova.setX1(Peao.XPosicaoInicialAmarelo[i]);
				nova.setY1(Peao.YPosicaoInicialAmarelo[i]);
			}					
			else if (i==3) {			
				nova.setX1(Peao.XPosicaoInicialAzul[i]);
				nova.setY1(Peao.YPosicaoInicialAzul[i]);
			}		
			
			return nova;
	}
	
	
	public int MovePeao(int dado) {
		
		int posicaoAtual=this.getPosicao();
		this.setPosicao(posicaoAtual+dado);	
		
			
		return 1;
	}
	
}
