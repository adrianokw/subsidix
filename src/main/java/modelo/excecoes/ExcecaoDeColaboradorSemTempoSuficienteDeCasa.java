package modelo.excecoes;

public class ExcecaoDeColaboradorSemTempoSuficienteDeCasa extends RuntimeException {
    public ExcecaoDeColaboradorSemTempoSuficienteDeCasa() {
        super("O colaborador não tem tempo suficiente de casa para pedir subsídio.");
    }
}
