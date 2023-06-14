package controler;

import view.*;
import model.FcModel;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FrameView frame = new FrameView();	
		frame.setTitle("Ludo Game");
		frame.setLayout(null);
		frame.setVisible(true);
		
		FcModel fachada= new FcModel();
		
		
		Rodada rd = new Rodada(frame, fachada);


		
	}

}
