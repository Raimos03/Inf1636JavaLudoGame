package model;


import controler.*;
import view.vCasa;

import java.awt.Color;


 class Casa implements ICasa {
	 
	// Classe criada para gerenciar ocorrencias de cassas especiais como abrigo e barreira
	 
		
	 public  int vQtdPeoes = 0;
	 private int posicaoCasa;
	 private boolean barreira=false;
	 private boolean abrigo=false;
	 private boolean temPeao=false;
	 private boolean casaSaida=false;
	 private String corCasadeSaida=null;
	 private String[] vcorPeoes = new String[2];
	 
	 // ver se guardo peoes aqui dentro ou nao
	 

	// private Object[] vPeoesCasa=new Object[2]; // max tamanho 2
	 
	 // vetor com numero maximo de poes que pode ficar em uma casa
	 {
		 
		 int i=0;
		 for(i=0;i<52;i++) {
			 vQtdPeoes=0;
		 }
	 }
	  
	 
	 Casa(int i){
		 posicaoCasa=i;
		 vcorPeoes[0]=null;
		 vcorPeoes[1]=null;
		 
		 
	 }
	 

	 public boolean eBarreira() {
		 
		 if (this.barreira==true) {
		 	return true;
		 }
		 	return false;
	 	}
	 public boolean eCasaSaida() {
		 
		if( this.casaSaida==true) {
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
 
	public void setCasaAbrigo(boolean t) {
		 
		this.abrigo=t;
		return;
	}
	public void SetCasaBarreira(boolean t) {
		 
		this.barreira=t;
		return;
	}
	public void setCasaSaida(boolean t) {
		this.casaSaida=t;
		return;
	}
	public void setCorPeao(int i, String s) {
		this.vcorPeoes[i]=s;
	}
	public void setTemPeao(boolean t) {
		this.temPeao=t;
	}
	public boolean getTemPeao() {
		return this.temPeao;
	}
	public void setCorCasa(String s) {
		this.corCasadeSaida= s;
	}
	public String getCorCasa() {
		return this.corCasadeSaida;
	}
	
	public void ReiniciaCasa() {
	
		this.barreira=false;
		this.temPeao=false;
		
		
		vcorPeoes[0]=null;
		vcorPeoes[1]=null;
		return;
		
	}
	public int getQtdPeao() {
		int posicao = this.getPosicaoCasa();
		int qtd =  vQtdPeoes;
		return qtd;
	}
	public int IncrementaPeaoCasa() {
		int posicao = this.getPosicaoCasa();
		int qtd= vQtdPeoes;
		qtd++;
		vQtdPeoes=qtd;
		
		if(qtd==1) {
			this.temPeao=true;
		}
		return qtd;
	}
	public int DecrementaPeaoCasa() {
		int posicao = this.getPosicaoCasa();
		int qtd= vQtdPeoes;
		qtd--;
		vQtdPeoes=qtd;
		if(qtd==0) {
			this.temPeao=false;
		}
		
		return qtd;
	}
	public int QualTipoBarreira() {			
			String c1= vcorPeoes[0];			
			if(c1.equals(vcorPeoes[1])) { // mesma cor
				return 1;
			}	
			return 2; // diferentes
		}
	public String getCor1() {
			
			return this.vcorPeoes[0];
		}
	
	public String getCor2() {
			return this.vcorPeoes[1];
	}
	
	public boolean eCasaZerada() {
		
		if (this.abrigo==false && this.barreira==false && this.temPeao==false) {
			return true;
		}
		return false;
	}
	
	public int getPosicaoCasa() {
		
		return this.posicaoCasa;
	}
	
	
	public void ExibeStatus() {
		
		String b="",a="",p="";
		System.out.println("-----c ");
		System.out.println("\t QTD VPEOES CASA: "+ this.vQtdPeoes);
		System.out.println("\t PosicaoCasa: "+this.getPosicaoCasa());
		
		
		if (this.barreira==true) {
			b= " E Barreira";
		}		
		else if (this.abrigo==true) {		
			a =" E Abrigo";
		}	
		else if (this.casaSaida==true) {
			p= " Casa saida";
		}
		else if (this.temPeao==true) {
			p= " Tem peao";
		}
		
	
		System.out.println("\t\t"+ b + a + p);
		
		if(this.vcorPeoes[0]!=null) {
			System.out.println("\t\t Cor0: "+this.getCor1());
		}
		if(this.vcorPeoes[1]!=null) {
			System.out.println("\t\t Cor1: "+this.getCor2());
		}
		
		//System.out.println(" ----c ");
		return ;	
	}




	@Override
	public void setCasaBarreira(boolean t) {
		// TODO Auto-generated method stub
		this.barreira=t;
	}
	
		 

}

 
	
	
 
 
 