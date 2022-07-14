package br.com.sprint4.models;

import javax.persistence.Enumerated;

public enum Sexo {

    FEMININO("Feminino"),
    MASCULINO("Masculino");

    private String valor;
    Sexo(String valor){
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
