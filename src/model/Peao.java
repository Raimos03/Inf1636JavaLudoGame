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
	
	// casa para saida de cada cor
	
	static Coordenada PosicaoCasaSaidaVermelho = new Coordenada(43.99999,262.7999); // OK
	static Coordenada PosicaoCasaSaidaVerde = new Coordenada(350.6,43.8); // OK
	static Coordenada PosicaoCasaSaidaAmarelo = new Coordenada(569.6,350.40); //OK
	static Coordenada PosicaoCasaSaidaAzul = new Coordenada(263,569.4); //OK

	//--------------------
		
	private Player PlayerPai;
	private int Id;
	private int IntCor;
	
	private Coordenada xy= new Coordenada();	//coordenada onde esta no tabuleiro
	private int Posicao=-1;         // -1 (casa inicial) 0.. - primeirca casa tabuleiro  posicao no vetor do tabuleiro, inicia no indice da casa de saida 
	

	
	private boolean CasaSaida=false;  // casa de saida de cada cor
	private boolean CasaInicial=true;  // assim que nasce o peao e começa o jogo
	
	private boolean Barreira=false; // esta na barreira
	private boolean Abrigo=false;  //esta no abrigo
	private boolean CasaFinal=false; // victory road
	
	
	public Peao (String scor, Player pai) {
		
		this.PlayerPai=pai;
		this.Id = idPeao;	
		this.IntCor=getIndiceCor(scor);
	
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

	
	public int getIntCor() {
		return this.IntCor;
	}

	public int getCorId() {
		return IntCor;
	}

	public boolean isNoTabuleiro() {
		if (this.CasaInicial==true) {
			return true;
		}
		return false;
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
		
		idPeao++;
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
	
	public boolean MoveCasaSaida() {
		
		int idsaidaPeao= this.getCorId();
		Coordenada nova;
			
		if (this.Posicao==-1) {
			
			if (idsaidaPeao==0){
				
				nova = Peao.PosicaoCasaSaidaVermelho;			
			}
			else if (idsaidaPeao==1) {
				nova = Peao.PosicaoCasaSaidaVerde;			
			}
			else if (idsaidaPeao==2) {			
				nova = Peao.PosicaoCasaSaidaAmarelo;
			}
			else {			
				nova = Peao.PosicaoCasaSaidaAzul;
			}
			
			//seta a nova posicao para o peao
			this.setXY(nova);	
			this.CasaSaida=true;
		}
		
		else {
			System.out.println("O peao ja esta no tabuleiro. Impossivel mover para saida");
		}		
		return false;
	}

	
	public int MovePeao(int dado) { // Jogada normal
		
		//int posicaoAtual=this.getPosicao();		
		//this.setPosicao(posicaoAtual+dado);	
		
		int posicaoatual=this.getPosicao();
		
		// dependendo onde esteja (casa comun ou outra ele procura a posicao)
		
		if (this.CasaFinal==false) { // casas normais
			
			this.setPosicao(posicaoatual+dado);		
			this.setXY(Tabuleiro.getVcasasComuns()[posicaoatual]);
		}
		
		
		// Ver como integras as regras de ter barreira na frente ou nao
		
		
		
		
		return 1;
	}
	
}
