package model;

class Player {

	private String cor;
	private int[] peoes = {0, 0, 0, 0}; // quais pioes estao ativos
	private int qtdPeoesVitoria=0;
	private int qtd_ativo = 0;
	private int qtd_vitoria = 0;
	
	private Peao p1;
	private Peao p2;
	private Peao p3;
	private Peao p4;
	
	
	Player (String x) {	
		cor = x;	
		p1=new Peao(x,this);
		p2=new Peao(x,this);
		p3=new Peao(x,this);
		p4=new Peao(x,this);	
			
	}
	
	public void update_qtd_vitoria() {
		this.qtd_vitoria++;
	}
	
	public int get_qtd_vitoria() {
		return this.qtd_vitoria;
	}
	
	
	
	public void ativar_peao(int x) { // ativando peao 1 a 4
		peoes[x - 1] = 1;
		qtd_ativo += 1;
	}
	
	public void desativar_peao(int x) { // desativando 1 a 4
		peoes[x - 1] = 0;
		qtd_ativo -= 1;
	}
	
	public int get_qtd_ativo() {
		return qtd_ativo;
	}
	
	public String get_cor() {
		return cor;
	}

	public Peao getPeao1() {
		return p1;
	}

	public void setPeao1(Peao p1) {
		this.p1 = p1;
	}

	public Peao getPeao2() {
		return p2;
	}

	public void setPeao2(Peao p2) {
		this.p2 = p2;
	}

	public Peao getPeao3() {
		return p3;
	}

	public void setPeao3(Peao p3) {
		this.p3 = p3;
	}
	
	public Peao getPeao4() {
		return p4;
	}

	public void setPeao4(Peao p) {
		this.p4=p;
	}
	public void ContabilizaVictoriaPeao() {
		this.qtdPeoesVitoria++;
	}
}