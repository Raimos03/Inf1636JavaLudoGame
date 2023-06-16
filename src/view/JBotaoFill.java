package view;



import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JButton;

//import para fazer o file chooser
import javax.swing.JFileChooser;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


public class JBotaoFill extends JButton {
	


 // botao customizado 
	
	private int indice;
	private Color hoverBackgroundColor;
    private Color pressedBackgroundColor;
    //FrameView

    public JBotaoFill() {
        this(null);
    }

    public JBotaoFill(String text) {
        super(text);
        super.setContentAreaFilled(false);
    }
	
    public JBotaoFill(String text, int i) {
    	
        super(text);
        super.setContentAreaFilled(false);
        this.indice=i;
    }
   
    @Override
    protected void paintComponent(Graphics g) {
    	
        if (getModel().isPressed()) {
            g.setColor(pressedBackgroundColor);
        } else if (getModel().isRollover()) {
            g.setColor(hoverBackgroundColor);
        } else {
            g.setColor(getBackground());
        }
        g.fillRect(0, 0, getWidth(), getHeight()); //**
        
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

	
    public static void salvarArquivo(String caminhoArquivo, String conteudo) {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter(caminhoArquivo+".txt"));
            writer.println(conteudo);
            writer.close();
            System.out.println("Conteúdo salvo com sucesso no arquivo!");
        } catch (IOException e) {
            System.out.println("Erro ao salvar o arquivo: " + e.getMessage());
        }
    }
    
    public static String carregaArquivo(String caminhoArquivo) {
        StringBuilder conteudo = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                conteudo.append(linha).append("\n");
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar o arquivo: " + e.getMessage());
        }
        return conteudo.toString();
    }
    
    
}
