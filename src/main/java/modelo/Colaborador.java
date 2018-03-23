package modelo;

import java.util.Calendar;

public class Colaborador {

    private String nome;

    private Calendar dataDeEntradaNaEmpresa;

    private Cidade cidade;

    public Colaborador() {
    }

    public Colaborador(String nome, Calendar dataDeEntradaNaEmpresa, Cidade cidade) {
        this.nome = nome;
        this.dataDeEntradaNaEmpresa = dataDeEntradaNaEmpresa;
        this.cidade = cidade;
    }

    public Calendar getDataDeEntradaNaEmpresa() {
        return dataDeEntradaNaEmpresa;
    }
}
