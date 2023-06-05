package controler;

import view.*;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FrameView Frame = new FrameView();	
		Frame.setTitle("Ludo Game");
		Frame.setLayout(null);
		Frame.setVisible(true);
		
		Rodada rd = new Rodada();
		rd.setFrameView(Frame);
		
		
	}

}
