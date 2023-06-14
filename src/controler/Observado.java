package controler;


public interface Observado {
	
	public void addObserver(Observador o); // recebe o observador 
	public void removeObserver(Observador o); // retira o observador
	public Object getDados();
	public void Notify(); // notifica todos que estao observando-o


}
