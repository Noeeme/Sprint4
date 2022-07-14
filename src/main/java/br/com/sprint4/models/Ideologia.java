package br.com.sprint4.models;

public enum Ideologia {
    DIREITA("Direita"),
    CENTRO("Centro"),
    ESQUERDA("Esquerda");

    private String valor;

    Ideologia(String valor){
        this.valor = valor;
    }


    public String getValor() {
        return valor;
    }

    @Override
    public String toString() {
        return valor;
    }
}
