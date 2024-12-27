package com.mycompany.sudoku;
import javax.swing.JOptionPane;

public class Sudoku {

    private int modoDeJogo;
    private Tabuleiro tabuleiro;
    
    public Sudoku(){
        this.tabuleiro = new Tabuleiro();
    }
    
    public void iniciar(){
        
        String[] opcoes = {"Jogo Aleatório", "Definir Jogo"};
        modoDeJogo = JOptionPane.showOptionDialog(null, "Seja bem-vindo jogador(a)! Escolha uma opção:", "Iniciar jogo", 
                    JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE, null, opcoes, opcoes[0]);
        if(modoDeJogo == 0)
            jogoAleatorio();
        if(modoDeJogo == 1)
            definirJogo();
    }
    
    public void jogoAleatorio(){
        
        String sorteio = JOptionPane.showInputDialog(null, "Quantos números deseja sortear?", "Jogo Aleatório", 
                             JOptionPane.QUESTION_MESSAGE);
        int quant = Integer.parseInt(sorteio);

        int k = 0; 
        while(k < quant){
            int i = (int) (Math.random() * 9);
            int j = (int) (Math.random() * 9);
            int num = (int) (1 + Math.random() * 9);
            if(tabuleiro.preenche(i, j, num))
                k++;
        }
        tabuleiro.imprime();
    }
    
    public void definirJogo(){
        
    }
    
    public static void main(String[] args) {
        
        Sudoku jogo = new Sudoku();
        jogo.iniciar();
    }
}
