package com.mycompany.sudoku;
import javax.swing.JOptionPane;

public class Sudoku {

    private int modoDeJogo;
    private Tabuleiro tabuleiro;
    
    public Sudoku(){
        this.tabuleiro = new Tabuleiro();
    }
    
    public void inicio(){
        
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
        
        while (true) {
            String entrada = JOptionPane.showInputDialog(null, "Defina um ou mais valores no formato ([linha],[coluna],[valor]):", "Definir Jogo", 
                             JOptionPane.QUESTION_MESSAGE);
            if (entrada.equals("(-1,-1,-1)")) {
                JOptionPane.showMessageDialog(null, "Inserção finalizada.", 
                    "Definir Jogo", JOptionPane.INFORMATION_MESSAGE, null);
                break;
            }

            // Processar múltiplas entradas
            String[] entradas = entrada.split("\\)\\("); // Divide em cada fechamento e abertura de parênteses
            for (String e : entradas) {
                e = e.replace("(", "").replace(")", ""); // Remove os parênteses
                String[] valores = e.split(","); // Divide em linha, coluna e valor

                try {
                    int linha = Integer.parseInt(valores[0]) - 1; // Subtrai 1 para ajustar ao índice 0
                    int coluna = Integer.parseInt(valores[1]) - 1;
                    int valor = Integer.parseInt(valores[2]);

                    // Validação de valores
                    if (linha < 0 || linha >= 9 || coluna < 0 || coluna >= 9 || valor < 1 || valor > 9) {
                        JOptionPane.showMessageDialog(null, "Valores devem estar no intervalo [1,9]!", "Erro", 
                                JOptionPane.INFORMATION_MESSAGE, null);                    
                    } else {
                        tabuleiro.preenche(linha, coluna, valor);
                    }
                } catch (NumberFormatException | ArrayIndexOutOfBoundsException ex) {
                    JOptionPane.showMessageDialog(null, "Entrada "+e+" inválida!", 
                        "Erro", JOptionPane.INFORMATION_MESSAGE, null);
                }
            }
            tabuleiro.imprime();
            System.out.println();
        }
    }
    
    public void jogar(){
        
        String[] opcoes = {"Jogo Aleatório", "Definir Jogo"};
        modoDeJogo = JOptionPane.showOptionDialog(null, "Seja bem-vindo jogador(a)! Escolha uma opção:", "Iniciar jogo", 
                    JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE, null, opcoes, opcoes[0]);
    }
    
    public static void main(String[] args) {
        
        Sudoku jogo = new Sudoku();
        jogo.inicio(); //cria a configuração inicial do jogo
        jogo.jogar(); //inicia o jogo
    }
}
