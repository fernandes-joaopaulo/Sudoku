package com.mycompany.sudoku;
import javax.swing.JOptionPane;

public class Sudoku {

    private int modoDeJogo;
    private Tabuleiro tabuleiro;

    public Sudoku() {
        this.tabuleiro = new Tabuleiro();
    }

    public void inicio() {

        String[] opcoes = {"Jogo Aleatório", "Definir Jogo"};
        modoDeJogo = JOptionPane.showOptionDialog(null, "Seja bem-vindo jogador(a)! Escolha uma opção:", "Iniciar jogo",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, opcoes, opcoes[0]);
        if (modoDeJogo == 0) {
            jogoAleatorio();
        }
        if (modoDeJogo == 1) {
            definirJogo();
        }
    }

    public void jogoAleatorio() {
        
        int quant;
        do{
        String sorteio = JOptionPane.showInputDialog(null, "Quantos números deseja sortear?", "Jogo Aleatório",
                JOptionPane.QUESTION_MESSAGE);
        quant = Integer.parseInt(sorteio);
        
        if(quant > 81)
            JOptionPane.showMessageDialog(null, "Valor não permitido.", "Erro",
                            JOptionPane.ERROR_MESSAGE, null);
        }while(quant > 81);

        tabuleiro.gerarJogoCompleto();
        tabuleiro.reduzirTabuleiro(quant);

        tabuleiro.imprime();
        System.out.println("///////////////////////////");
    }

    public void setEntrada(String entrada, boolean fixa) {

        // Processar múltiplas entradas
        String[] entradas = entrada.split("\\)\\("); // Divide em cada fechamento e abertura de parênteses
        for (String e : entradas) {
            e = e.replace("(", "").replace(")", ""); // Remove os parênteses
            String[] valores = e.split(","); // Divide em linha, coluna e valor

            try {
                int linha = Integer.parseInt(valores[0]) - 1;
                int coluna = Integer.parseInt(valores[1]) - 1;
                int valor = Integer.parseInt(valores[2]);

                // Validação de valores
                if (linha < 0 || linha >= 9 || coluna < 0 || coluna >= 9 || valor < 1 || valor > 9) {
                    JOptionPane.showMessageDialog(null, "Valores devem estar no intervalo [1,9]!", "Erro",
                            JOptionPane.ERROR_MESSAGE, null);
                } else {
                    if (fixa) {  //Modo definir Jogo
                        if (!tabuleiro.preenche(linha, coluna, valor)) {
                            JOptionPane.showMessageDialog(null, "Definição em desacordo com as regras do Sudoku.", "Definir Jogo",
                                    JOptionPane.WARNING_MESSAGE, null);
                        }
                    } else {    //Adicionar Jogada
                        tabuleiro.jogada(linha, coluna, valor);
                    }
                }
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException ex) {
                JOptionPane.showMessageDialog(null, "Entrada " + e + " inválida!",
                        "Erro", JOptionPane.ERROR_MESSAGE, null);
            }
        }
        tabuleiro.imprime();
        System.out.println("///////////////////////////");
    }

    public void definirJogo() {

        while (true) {
            String entrada = JOptionPane.showInputDialog(null, "Defina um ou mais valores no formato: (linha,coluna,valor)."
                    + " Digite (-1,-1,-1) para finalizar.", "Definir Jogo", JOptionPane.QUESTION_MESSAGE);
            if (entrada.equals("(-1,-1,-1)")) {
                JOptionPane.showMessageDialog(null, "Inserção finalizada.",
                        "Definir Jogo", JOptionPane.INFORMATION_MESSAGE, null);
                break;
            }
            setEntrada(entrada, true);
        }
    }

    public void jogar() {

        while (true) {
            String[] acoes = {"Adicionar Jogada", "Remover Jogada", "Verificar", "Dica", "Sair"};
            int acao = JOptionPane.showOptionDialog(null, "O que deseja fazer?", "Jogo",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, acoes, acoes[0]);
            if (acao == 0) {
                adicionarJogada();
            }
            if (acao == 1) {
                removerJogada();
            }
            if (acao == 2) {
                verificar();
            }
            if (acao == 3) {
                dica();
            }
            if (acao == 4) {
                break;
            }
            if (tabuleiro.verifica() && tabuleiro.isCheio()) {
                String[] opcoes = {"Sim", "Não"};
                int opcao = JOptionPane.showOptionDialog(null, "Parabéns! Deseja jogar novamente?", "Fim de Jogo",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, opcoes, opcoes[0]);

                if (opcao == 0) { //Reinicia o jogo
                    Sudoku jogo = new Sudoku();
                    jogo.inicio(); //cria a configuração inicial do jogo
                    jogo.jogar(); //inicia o jogo
                    break;
                }
                if(opcao == 1){
                    break;  //Sai do jogo
                }
            }
        }
    }

    public void adicionarJogada() {

        String entrada = JOptionPane.showInputDialog(null, "Defina um ou mais valores no formato (linha,coluna,valor):", "Adicionar Jogada",
                JOptionPane.QUESTION_MESSAGE);
        setEntrada(entrada, false);
    }

    public void removerJogada() {
        String entrada = JOptionPane.showInputDialog(null, "Defina o valor a ser removido no formato: (linha,coluna):", "Remover Jogada",
                JOptionPane.QUESTION_MESSAGE);

        entrada = entrada.replace("(", "").replace(")", ""); // Remove os parênteses
        String[] valores = entrada.split(","); // Divide em linha, coluna e valor

        try {
            int linha = Integer.parseInt(valores[0]) - 1;
            int coluna = Integer.parseInt(valores[1]) - 1;

            // Validação de valores
            if (linha < 0 || linha >= 9 || coluna < 0 || coluna >= 9) {
                JOptionPane.showMessageDialog(null, "Valores devem estar no intervalo [1,9]!", "Erro",
                        JOptionPane.ERROR_MESSAGE, null);
            } else {
                if (tabuleiro.getCasa(linha, coluna).removeValor()) {
                    System.out.println("Valor removido em (" + (linha + 1) + "," + (coluna + 1) + ").");
                } else {
                    System.out.println("Célula fixa!");
                }
            }
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException ex) {
            JOptionPane.showMessageDialog(null, "Entrada " + entrada + " inválida!",
                    "Erro", JOptionPane.ERROR_MESSAGE, null);
        }
        tabuleiro.imprime();
        System.out.println("///////////////////////////");
    }

    public void verificar() {

        if (tabuleiro.verifica()) {
            System.out.println("Tudo ok!");
        } else {
            tabuleiro.relatorio();
        }
    }

    public void dica() {

        String posicao = JOptionPane.showInputDialog(null, "Defina uma posição no formato: (linha,coluna):", "Dica",
                JOptionPane.QUESTION_MESSAGE);
        posicao = posicao.replace("(", "").replace(")", ""); // Remove os parênteses
        String[] valores = posicao.split(","); // Divide em linha e coluna

        try {
            int linha = Integer.parseInt(valores[0]) - 1;
            int coluna = Integer.parseInt(valores[1]) - 1;

            // Validação de valores
            if (linha < 0 || linha >= 9 || coluna < 0 || coluna >= 9) {
                System.out.println("Valores devem estar no intervalo [1,9]!");
            } else if (tabuleiro.getCasa(linha, coluna).isFixo()) {
                System.out.println("Célula fixa!");
            } else {
                System.out.print("Dica: Os valores [ ");
                for (int i = 1; i <= 9; i++) {
                    tabuleiro.getCasa(linha, coluna).setValor(i);
                    if (tabuleiro.verifica()) {
                        System.out.print(i + " ");
                    }
                    tabuleiro.getCasa(linha, coluna).removeValor();
                }
                System.out.println("] podem ser adicionados em (" + (linha + 1) + ", " + (coluna + 1) + ")");
            }
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException ex) {
            JOptionPane.showMessageDialog(null, "Entrada " + posicao + " inválida!",
                    "Erro", JOptionPane.ERROR_MESSAGE, null);
        }
    }

    public static void main(String[] args) {

        Sudoku jogo = new Sudoku();
        jogo.inicio(); //cria a configuração inicial do jogo
        jogo.jogar(); //inicia o jogo
    }
}
