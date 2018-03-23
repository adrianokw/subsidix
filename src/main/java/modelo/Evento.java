package modelo;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

public class Evento {
    private String nome;
    private Calendar dataInicio;
    private Calendar dataFim;
    private BigDecimal custo;
    private Cidade cidade;
    private boolean online;

    public Evento(String nome, Calendar dataInicio, Calendar dataFim, Cidade cidade, boolean online, BigDecimal custo) {
        this.nome = nome;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.cidade = cidade;
        this.online = online;
        this.custo = custo;
    }

    public String getNome() {
        return nome;
    }

    public Calendar getDataInicio() {
        return dataInicio;
    }

    public Calendar getDataFim() {
        return dataFim;
    }

    public BigDecimal getCusto() {
        return custo;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public boolean isOnline() {
        return online;
    }
}
