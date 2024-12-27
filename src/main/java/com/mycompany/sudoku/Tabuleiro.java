package com.mycompany.sudoku;

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
    
    public Casa getCasa(int linha, int col){
        return casa[linha][col];
    }
    
    public boolean preenche(int linha, int col, int val){

        Casa c = this.casa[linha][col];  
        if(c.getValor() == 0){
            c.setValor(val);
            if(verifica()){
                return true;
            }else{
                c.removeValor();
                return false;
            }
        }
        else{
            return false;
        }
    }
    
    public boolean verifica(){
        
        //valida linhas
        for (int i = 0; i < 9; i++) {
            boolean[] valores = new boolean[10];
            for (int j = 0; j < 9; j++) {
                int valor = casa[i][j].getValor();
                if (valor != 0) {
                    if (valores[valor]) return false; // Número duplicado
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
                    if (valores[valor]) return false; // Número duplicado
                    valores[valor] = true;
                }
            }
        }
        
        //valida grids
        for (int gridX = 0; gridX < 3; gridX++) {
            for (int gridY = 0; gridY < 3; gridY++) {
                boolean[] valores = new boolean[10];
                for (int i = gridX * 3; i < gridX * 3 + 3; i++) {
                    for (int j = gridY * 3; j < gridY * 3 + 3; j++) {
                        int valor = casa[i][j].getValor();
                        if (valor != 0) {
                            if (valores[valor]) return false;
                            valores[valor] = true;
                        }
                    }
                }
            }
        }
        return true;
    }
    
    public void imprime(){
    
        for (int i = 0; i < 9; i++) {
            if (i % 3 == 0 && i != 0) {
                System.out.println("---------------------"); 
            }
            for (int j = 0; j < 9; j++) {
                if (j % 3 == 0 && j != 0) {
                    System.out.print(" | "); 
                }
                System.out.print(casa[i][j].getValor() == 0 ? "." : casa[i][j].getValor());
                System.out.print(" ");
            }
            System.out.println();
        }
    }
    
}
