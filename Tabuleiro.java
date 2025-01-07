package com.mycompany.sudoku;

import java.util.*;

public class Tabuleiro {

    private Casa[][] casa;

    public Tabuleiro() {
        casa = new Casa[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                casa[i][j] = new Casa(0, false);
            }
        }
    }

    public Casa getCasa(int linha, int col) {
        return casa[linha][col];
    }

    public boolean isCheio() {

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (this.casa[i][j].getValor() == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public void imprime() {

        for (int i = 0; i < 9; i++) {
            if (i % 3 == 0 && i != 0) {
                System.out.println("---------------------");
            }
            for (int j = 0; j < 9; j++) {
                if (j % 3 == 0 && j != 0) {
                    System.out.print(" | ");
                }
                if (casa[i][j].getValor() == 0) {
                    System.out.print(" . ");
                } else if (casa[i][j].isFixo()) {
                    System.out.print(" \033[1;31m" + casa[i][j].getValor() + "\033[0m "); //exibe em vermelho os valores fixos
                } else {
                    System.out.print(" " + casa[i][j].getValor() + " ");
                }
            }
            System.out.println();
        }
    }

    public boolean preenche(int linha, int col, int val) {

        Casa c = this.casa[linha][col];
        if (c.getValor() == 0) {
            c.setValor(val);
            if (verifica()) {
                c.setFixo();
                return true;
            } else {
                c.removeValor();
                return false;
            }
        } else {
            return false;
        }
    }

    public void jogada(int linha, int col, int val) {

        Casa c = this.casa[linha][col];
        if (c.getValor() == 0) {
            c.setValor(val);
            System.out.println("Valor definido em (" + (linha + 1) + "," + (col + 1) + ") como " + val + ".");
        } else {
            System.out.println("Célula já preenchida!");
        }
    }

    public boolean verifica() {

        //valida linhas
        for (int i = 0; i < 9; i++) {
            boolean[] valores = new boolean[10];
            for (int j = 0; j < 9; j++) {
                int valor = casa[i][j].getValor();
                if (valor != 0) {
                    if (valores[valor]) {
                        return false;
                    }
                    valores[valor] = true;
                }
            }
        }
        //valida colunas
        for (int j = 0; j < 9; j++) {
            boolean[] valores = new boolean[10];
            for (int i = 0; i < 9; i++) {
                int valor = casa[i][j].getValor();
                if (valor != 0) {
                    if (valores[valor]) {
                        return false;
                    }
                    valores[valor] = true;
                }
            }
        }
        //valida casas
        for (int casaX = 0; casaX < 3; casaX++) {
            for (int casaY = 0; casaY < 3; casaY++) {
                boolean[] valores = new boolean[10];
                for (int i = casaX * 3; i < casaX * 3 + 3; i++) {
                    for (int j = casaY * 3; j < casaY * 3 + 3; j++) {
                        int valor = casa[i][j].getValor();
                        if (valor != 0) {
                            if (valores[valor]) {
                                return false;
                            }
                            valores[valor] = true;
                        }
                    }
                }
            }
        }
        return true;
    }

    public boolean gerarJogoCompleto() {
        return aux(0, 0); // Inicia o preenchimento na célula (0,0)
    }

    private boolean aux(int linha, int coluna) {
        // Se chegarmos à última linha, o tabuleiro está completo
        if (linha == 9) {
            return true;
        }

        // Calcula a próxima célula
        int proxLinha = (coluna == 8) ? linha + 1 : linha;
        int proxColuna = (coluna + 1) % 9;

        // Tenta preencher a célula atual com um número válido
        List<Integer> numeros = new ArrayList<>();
        for (int i = 1; i <= 9; i++) {
            numeros.add(i);
        }
        Collections.shuffle(numeros); // Aleatoriza a ordem dos números

        for (int num : numeros) {
            if (preenche(linha, coluna, num)) {
                casa[linha][coluna] = new Casa(num, true); // Define como valor fixo

                // Preenche o restante do tabuleiro recursivamente
                if (aux(proxLinha, proxColuna)) {
                    return true;
                }

                // Caso não funcione, remove o número
                casa[linha][coluna] = new Casa(0, false);
            }
        }

        return false; // Sem solução para esta configuração
    }

    public void reduzirTabuleiro(int quantidadeDesejada) {
        int totalCelas = 81;
        int valoresARemover = totalCelas - quantidadeDesejada;

        List<int[]> posicoes = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                posicoes.add(new int[]{i, j});
            }
        }

        // Embaralha as posições para garantir remoção aleatória
        Collections.shuffle(posicoes);

        // Remove os valores das posições aleatórias
        for (int k = 0; k < valoresARemover; k++) {
            int[] posicao = posicoes.get(k);
            int linha = posicao[0];
            int coluna = posicao[1];

            // Define o valor como 0 (vazio)
            casa[linha][coluna] = new Casa(0, false);
        }
    }

    public void relatorio() {

        //valida linhas
        for (int i = 0; i < 9; i++) {
            boolean[] valores = new boolean[10];
            for (int j = 0; j < 9; j++) {
                int valor = casa[i][j].getValor();
                if (valor != 0) {
                    if (valores[valor]) {
                        System.out.println("Valor repetido na linha em ("+(i+1)+","+(j+1)+")");
                    }
                    valores[valor] = true;
                }
            }
        }
        //valida colunas
        for (int j = 0; j < 9; j++) {
            boolean[] valores = new boolean[10];
            for (int i = 0; i < 9; i++) {
                int valor = casa[i][j].getValor();
                if (valor != 0) {
                    if (valores[valor]) {
                        System.out.println("Valor repetido na coluna em ("+(i+1)+","+(j+1)+")");
                    }
                    valores[valor] = true;
                }
            }
        }
        //valida casas
        for (int casaX = 0; casaX < 3; casaX++) {
            for (int casaY = 0; casaY < 3; casaY++) {
                boolean[] valores = new boolean[10];
                for (int i = casaX * 3; i < casaX * 3 + 3; i++) {
                    for (int j = casaY * 3; j < casaY * 3 + 3; j++) {
                        int valor = casa[i][j].getValor();
                        if (valor != 0) {
                            if (valores[valor]) {
                                System.out.println("Valor repetido no grid em ("+(i+1)+","+(j+1)+")");
                            }
                            valores[valor] = true;
                        }
                    }
                }
            }
        }
    }
}
