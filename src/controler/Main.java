package controler;

import view.*;
import model.FcModel;

public class Main {

	public static void main(String[] args) {
		
		
		FrameView frame = new FrameView();	
		frame.setTitle("Ludo Game");
		frame.setLayout(null);
		frame.setVisible(true);
		
		FcModel fachada= FcModel.getInstance();
		
		
		Rodada rd = new Rodada(frame, fachada);

		
	}

}
