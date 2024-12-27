package com.mycompany.sudoku;

public class Casa {
    
    private int valor;
    private boolean ehFixo;
    
    public Casa(int val, boolean ehFixo){
        this.valor = val;
        this.ehFixo = ehFixo;
    }
    
    public int getValor(){
        return valor;
    }
    
    public void setValor(int val){
        if(!ehFixo)
            this.valor = val;
        else
            System.out.println("Célula não editável");
    }
    
    public void removeValor(){
        if(!ehFixo)
            this.valor = 0;
        else
            System.out.println("Célula não editável");
    }
}
