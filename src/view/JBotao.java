package view;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;

import controler.*;




public class JBotao extends JButton implements ActionListener, Observado2 { // botao customizado 
	
	private int indice;
	private Color hoverBackgroundColor;
    private Color pressedBackgroundColor;
    private static ArrayList<Observador2> lobs = new ArrayList<>();

    public JBotao() {
        this(null);
    }

    public JBotao(String text) {
        super(text);
        super.setContentAreaFilled(false);
    }
	
    public JBotao(int i) {
    	
      
        super.setContentAreaFilled(false);
        this.indice=i;
    }
    
    public JBotao(int i,String text) {
    	
        super(text);
        super.setContentAreaFilled(false);
        this.indice=i;
    }
    
    
	 
//	JBotao(String s){
//		this.setText(s);
//	}
//
//	}
    
    @Override
    protected void paintComponent(Graphics g) {
    	
        if (getModel().isPressed()) {
            g.setColor(pressedBackgroundColor);
        } else if (getModel().isRollover()) {
            g.setColor(hoverBackgroundColor);
        } else {
            g.setColor(getBackground());
        }
        //g.fillRect(0, 0, getWidth(), getHeight()); //**
        
        //g.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
        
        super.paintComponent(g);
    }
    @Override
    public void setContentAreaFilled(boolean b) {
    }

    public Color getHoverBackgroundColor() {
        return hoverBackgroundColor;
    }

    public void setHoverBackgroundColor(Color hoverBackgroundColor) {
        this.hoverBackgroundColor = hoverBackgroundColor;
    }

    public Color getPressedBackgroundColor() {
        return pressedBackgroundColor;
    }

    public void setPressedBackgroundColor(Color pressedBackgroundColor) {
        this.pressedBackgroundColor = pressedBackgroundColor;
    }
    
    public int getIndice() {
    	return this.indice;
    }

	@Override
	public void actionPerformed(ActionEvent novo) {
		// TODO Auto-generated method stub
		
		//String label = novo.getActionCommand();
		JBotao j = (JBotao) novo.getSource(); 
		System.out.println("Botao Peao: "+j.indice);
		this.Notify2(j.indice);
	}

	@Override
	public void addObserver2(Observador2 o) {
		// TODO Auto-generated method stub
		JBotao.lobs.add(o);
	}

	@Override
	public void removeObserver2(Observador2 o) {
		// TODO Auto-generated method stub
		JBotao.lobs.remove(o);
	}

	@Override
	public Object getDados2() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void Notify2(int i) {
		// TODO Auto-generated method stub
		for( Observador2 obj : JBotao.lobs) {
			//Frame j = (JBotao) obj;
			obj.update2(i);
			
		}
	}

	
}