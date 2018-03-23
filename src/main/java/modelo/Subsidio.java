package modelo;

import modelo.excecoes.ExcecaoDeColaboradorSemTempoSuficienteDeCasa;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;

public class Subsidio {

    public static final BigDecimal VALOR_DA_DIARIA = new BigDecimal("400");
    public static final int PRIMEIRO_DIA_DE_EVENTO = 1;
    public static final int NUMERO_DE_DIAS_MINIMO_DE_CASA_PARA_PEDIR_SUBSUDIO = 180;

    private Colaborador subsidiado;
    private Evento evento;
    private BigDecimal custoDePassagem;

    public Subsidio(Colaborador subsidiado, Evento evento, BigDecimal custoDePassagem) {
        validarSeSubsidiadoTemTempoSuficienteDeCasa(subsidiado, evento);

        this.subsidiado = subsidiado;
        this.evento = evento;
        this.custoDePassagem = custoDePassagem;
    }

    private void validarSeSubsidiadoTemTempoSuficienteDeCasa(Colaborador subsidiado, Evento evento) {
        if (ChronoUnit.DAYS.between(subsidiado.getDataDeEntradaNaEmpresa().toInstant(), evento.getDataInicio().toInstant()) < NUMERO_DE_DIAS_MINIMO_DE_CASA_PARA_PEDIR_SUBSUDIO) {
            throw new ExcecaoDeColaboradorSemTempoSuficienteDeCasa();
        }
    }

    private BigDecimal calcularDiarias() {
        long numeroDeDiarias = ChronoUnit.DAYS.between(evento.getDataInicio().toInstant(), evento.getDataFim().toInstant()) + PRIMEIRO_DIA_DE_EVENTO;
        return VALOR_DA_DIARIA.multiply(BigDecimal.valueOf(numeroDeDiarias));
    }

    public BigDecimal calcularCustoTotal() {
        BigDecimal custoDasDiarias = evento.isOnline() ? BigDecimal.ZERO : calcularDiarias();
        return custoDasDiarias.add(custoDePassagem)
                .add(evento.getCusto());
    }

    public BigDecimal getCustoDePassagem() {
        return custoDePassagem;
    }

    public Colaborador getSubsidiado() {
        return subsidiado;
    }
}

