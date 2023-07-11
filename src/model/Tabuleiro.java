package model;


class Tabuleiro  {

		
	//Object[] vPeao = new Object[16];
	
	private Object[] vcasas = new Object[52]; // vetor com info de cada casa
	//private double[] Area = new double[2];

	
	
	public Tabuleiro () { // inicializo as coordenadas
		
		//InicializaVcasas();
		InicializaCasasInfo();
	}
	public Object[] getVCasas() {
		return vcasas;
	}
	public void InicializaCasasInfo() {
		
		int i;
		
		for(i=0;i<52;i++) {
			
			Casa c= new Casa(i);
			
			if (i==3){
				c.setCasaSaida(true);
				c.setCorCasa("verde");
			}
			else if (i==16) {
				c.setCasaSaida(true);
				c.setCorCasa("amarelo");
			}
			else if (i==29) {
				c.setCasaSaida(true);
				c.setCorCasa("azul");
			}
			else if (i==42) { // 42 vermelho
				c.setCasaSaida(true);
				c.setCorCasa("vermelho");
			}
			
			
			if (i==12 || i==25 ||  i== 38 || i == 51) {
				
				c.setCasaAbrigo(true);
			}
			
			this.vcasas[i]=c;
			
		}		
		return;
	}
	
	public void ResetaCasaInfo() {
		
		//InicializaCasasInfo();
		for(int i =0;i<16;i++) {
			
			Casa  c= (Casa) vcasas[i]; 
			c.ReiniciaCasa();
		}
		
	}
	
}
	