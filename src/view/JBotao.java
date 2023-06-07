package view;


import java.awt.Color;
import java.awt.Graphics;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class JBotao extends JButton { // botao customizado 

	private Color hoverBackgroundColor;
    private Color pressedBackgroundColor;

    public JBotao() {
        this(null);
    }

    public JBotao(String text) {
        super(text);
        super.setContentAreaFilled(false);
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
        //g.fillRect(0, 0, getWidth(), getHeight());
        
        g.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
        
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

}