package controler;

public interface ICasa {
	
 public boolean eBarreira();
	public boolean eAbrigo();
	public void setCorPeao(int i,String s);
	public void setCasaAbrigo(boolean t);
	public void setCasaBarreira(boolean t);
	public void ReiniciaCasa();
	public int getQtdPeao();
	public void setTemPeao(boolean t);
	public boolean getTemPeao();
	public int IncrementaPeaoCasa();
	public int DecrementaPeaoCasa();
	public int QualTipoBarreira();
	public String getCor1();
	public String getCor2();
	public boolean eCasaZerada();	
	public int getPosicaoCasa();
	public void ExibeStatus();
	public void setCorCasa(String s);
	public String getCorCasa();
	public boolean eCasaSaida(); 
	public void setCasaSaida(boolean t);

}
