package br.com.sprint4.models;

public enum CargoPolitico {

    VEREADOR("Vereador"),
    PREFEITO("Prefeito"),
    DEPUTADO_ESTADUAL("Deputado estadual"),
    DEPUTADO_FEDERAL("Deputado federal"),
    SENADOR("Senador"),
    GOVERNADOR("Governador"),
    PRESIDENTE("Presidente"),
    NENHUM("Nenhum");

    private String valor;
    CargoPolitico(String valor){
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
