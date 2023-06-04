package model;

 class Casa {
	 
	 // Classe criada para gerenciar ocorrencias de cassas especiais como abrigo e barreira
	 
	 private boolean barreira=false;
	 private boolean abrigo=false;
	 private boolean temPeao=false;
	 // ver se guardo peoes aqui dentro ou nao
	 
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
		
		return;
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
	
}
 
 
 