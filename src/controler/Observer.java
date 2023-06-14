package controler;

public class Observer {

    private static int x;
    private static int y;

	public Observer(int c1, int c2) {
	    x = c1;
	    y = c2;
	}
	
	public int getx() {
	    return x;
	}
	
	public int gety() {
	    return y;
	}
	
	public void changex(int c1) {
	    Observer.x = c1;
	}
	
	public void changey(int c2) {
	    Observer.y = c2;
	}
	
	public void move(int c1, int c2) {
	    this.changex(c1);
	    this.changey(c2);
	}
}