package com.mycompany.sudoku;

public class Casa {

    private int valor;
    private boolean ehFixo;

    public Casa(int val, boolean ehFixo) {
        this.valor = val;
        this.ehFixo = ehFixo;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int val) {
        if (!ehFixo) {
            this.valor = val;
        } else {
            System.out.println("Célula não editável");
        }
    }

    public boolean removeValor() {
        if (!ehFixo) {
            this.valor = 0;
            return true;
        } else {
            return false;
        }
    }

    public boolean isFixo() {
        return this.ehFixo;
    }

    public void setFixo() {
        this.ehFixo = true;
    }
}
