package controler;

public interface Observado2 {

	public void addObserver2(Observador2 o); // recebe o observador 
	public void removeObserver2(Observador2 o); // retira o observador
	public Object getDados2();
	public void Notify2(int i); // notifica todos que estao observando-o
}
