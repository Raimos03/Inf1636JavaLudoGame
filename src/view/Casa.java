package view;

import java.util.ArrayList;
import controler.*;



 class Casa implements IPeao, ICoordenada {
	 
	 // Classe criada para gerenciar ocorrencias de cassas especiais como abrigo e barreira
	 
	 private boolean barreira=false;
	 private boolean abrigo=false;
	 private boolean temPeao=false;
	 
	 // ver se guardo peoes aqui dentro ou nao
	 
//	 ArrayList <Peao> vPeoesCasa = new ArrayList <Peao>(); 
	 private Object[] vPeoesCasa=new Object[2]; // max tamanho 2
	 
	 // vetor com numero maximo de poes que pode ficar em uma casa
	 

	 public boolean eBarreira() {
		 
		 if (this.barreira==true) {
		 	return true;
		 }
		 	return false;
	 	}

	 public boolean eAbrigo() {
		 
		 if (this.abrigo==true) {
		 	return true;
		 }
		 	return false;
	 	}
 
	public void SetCasaAbrigo() {
		 
		this.abrigo=true;
		return;
	}
	public void SetCasaBarreira() {
		 
		this.barreira=true;
		return;
	}
	public void ReiniciaCasa() {
		
		this.abrigo=false;
		this.barreira=false;
		this.temPeao=false;
		
		vPeoesCasa[0]=null;
		vPeoesCasa[1]=null;
		
		return;
	}
	
	public boolean CasaZerada() {
		
		if (this.abrigo==false && this.barreira==false && this.temPeao==false) {
			return true;
		}
		return false;
	}
	
	public Object[] getVpeoesCasa() {
		return this.vPeoesCasa;
	}
	
	
	
	public String ExibeStatusCasa() {
		
		if (this.barreira==true) {
			return "\t"+""+" E Barreira";
		}		
		else if (this.abrigo==true) {		
			return "\t"+""+" E Abrigo";
		}	
		else if (this.temPeao==true) {
			return "\t"+""+" Tem peao";
		}	
		return "\t Casa Livre";	
	}
	
	// ----- IPEAO ----OVERRIDE

	@Override
	public int getId() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getIntCor() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getCorId() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isNoTabuleiro() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setNoTabuleiro(boolean noTabuleiro) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ICoordenada getXY() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setXY(ICoordenada n) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getPosicao() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setPosicao(int posicao) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isCasaSaida() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setCasaSaida(boolean casaSaida) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isCasaInicial() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setCasaInicial(boolean casaInicial) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isBarreira() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setBarreira(boolean barreira) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isAbrigo() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setAbrigo(boolean abrigo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isCasaFinal() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setCasaFinal(boolean casaFinal) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ICoordenada getPosicaoCasaSaidaVermelho() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setPosicaoCasaSaidaVermelho(ICoordenada posicaoCasaSaidaVermelho) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ICoordenada getPosicaoCasaSaidaVerde() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setPosicaoCasaSaidaVerde(ICoordenada posicaoCasaSaidaVerde) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ICoordenada getPosicaoCasaSaidaAmarelo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setPosicaoCasaSaidaAmarelo(ICoordenada posicaoCasaSaidaAmarelo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ICoordenada getPosicaoCasaSaidaAzul() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setPosicaoCasaSaidaAzul(ICoordenada posicaoCasaSaidaAzul) {
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
	public int getIndiceCor(String s) {
		// TODO Auto-generated method stub
		return 0;
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
	public int MovePeao(int dado) {
		// TODO Auto-generated method stub
		return 0;
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
	public void setP1(double x, double y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean eIgualCoordenada(ICoordenada n) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
 
 
 