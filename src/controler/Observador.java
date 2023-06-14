package controler;

public interface Observador {
	public void update(); // atualiza algo com base na mudança do observavel
}


// o objeto observavel chama atraves do notify() este metodo update de todos que o observam ( observadores)