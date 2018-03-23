package modelo;

import modelo.excecoes.ExcecaoDeColaboradorSemTempoSuficienteDeCasa;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SubsidioTest {

    @Test
    void nao_deve_permitir_criar_subsidio_para_colaborador_com_180_dias_ou_menos_de_casa() {
        Calendar dataDeEntradaNaEmpresa = new GregorianCalendar(2018, 1, 10);
        Calendar dataDeInicioDoEvento = (GregorianCalendar) dataDeEntradaNaEmpresa.clone();
        dataDeInicioDoEvento.add(Calendar.DAY_OF_MONTH, 179);
        Calendar dataDeFimDoEvento = new GregorianCalendar(2018, 7, 24);
        Cidade cidade = criarCidadePadrao();
        Evento evento = new Evento("Javaneiros", dataDeInicioDoEvento, dataDeFimDoEvento, cidade, false, new BigDecimal("100"));
        Colaborador subsidiado = new Colaborador("Adriano", dataDeEntradaNaEmpresa, cidade);

        Executable tentaCriarSubsidio = () -> {
            new Subsidio(subsidiado, evento, BigDecimal.ZERO);
        };

        assertThrows(ExcecaoDeColaboradorSemTempoSuficienteDeCasa.class, tentaCriarSubsidio, "Não deve ser possível pedir subsídio para quem tem menos de 6 meses de empresa");
    }

    @Test
    void deve_permitir_criar_subsidio_para_colaborador_com_180_dias_ou_mais_de_casa() {
        Calendar dataDeEntradaNaEmpresa = new GregorianCalendar(2018, 1, 10);
        Calendar dataDeInicioDoEvento = (GregorianCalendar) dataDeEntradaNaEmpresa.clone();
        dataDeInicioDoEvento.add(Calendar.DAY_OF_MONTH, 180);
        Calendar dataDeFimDoEvento = new GregorianCalendar(2018, 7, 24);
        Cidade cidade = criarCidadePadrao();
        Evento evento = new Evento("Javaneiros", dataDeInicioDoEvento, dataDeFimDoEvento, cidade, false, new BigDecimal("100"));
        Colaborador subsidiado = new Colaborador("Adriano", dataDeEntradaNaEmpresa, cidade);

        Subsidio subsidio = new Subsidio(subsidiado, evento, BigDecimal.ZERO);

        assertSame(subsidiado, subsidio.getSubsidiado());
    }

    @Test
    void deve_incluir_o_custo_de_diarias_no_custo_total() {
        int diferencaDoPrimeiroAoUltimoDiaDeEvento = 1;
        Calendar dataDeEntradaNaEmpresa = new GregorianCalendar(2018, 1, 10);
        Calendar dataDeInicioDoEvento = new GregorianCalendar(2018, 8, 10);
        Calendar dataDeFimDoEvento = (GregorianCalendar) dataDeInicioDoEvento.clone();
        dataDeFimDoEvento.add(Calendar.DAY_OF_MONTH, diferencaDoPrimeiroAoUltimoDiaDeEvento);
        Cidade cidade = criarCidadePadrao();
        Evento evento = new Evento("Javaneiros", dataDeInicioDoEvento, dataDeFimDoEvento, cidade, false, BigDecimal.ZERO);
        Colaborador subsidiado = new Colaborador("Adriano", dataDeEntradaNaEmpresa, cidade);

        Subsidio subsidio = new Subsidio(subsidiado, evento, BigDecimal.ZERO);

        BigDecimal valorDasDiariasEsperado = Subsidio.VALOR_DA_DIARIA.multiply(new BigDecimal(diferencaDoPrimeiroAoUltimoDiaDeEvento + 1));
        assertEquals(valorDasDiariasEsperado, subsidio.calcularCustoTotal());
    }

    @Test
    void nao_deve_incluir_o_custo_de_diarias_no_custo_total_se_for_um_evento_online() {
        int diferencaDoPrimeiroAoUltimoDiaDeEvento = 1;
        Calendar dataDeEntradaNaEmpresa = new GregorianCalendar(2018, 1, 10);
        Calendar dataDeInicioDoEvento = new GregorianCalendar(2018, 8, 10);
        Calendar dataDeFimDoEvento = (GregorianCalendar) dataDeInicioDoEvento.clone();
        dataDeFimDoEvento.add(Calendar.DAY_OF_MONTH, diferencaDoPrimeiroAoUltimoDiaDeEvento);
        Cidade cidade = criarCidadePadrao();
        boolean ehOnline = true;
        Evento evento = new Evento("Javaneiros", dataDeInicioDoEvento, dataDeFimDoEvento, cidade, ehOnline, BigDecimal.ZERO);
        Colaborador subsidiado = new Colaborador("Adriano", dataDeEntradaNaEmpresa, cidade);

        Subsidio subsidio = new Subsidio(subsidiado, evento, BigDecimal.ZERO);

        assertEquals(BigDecimal.ZERO, subsidio.calcularCustoTotal());
    }

    @Test
    @Disabled
    void nao_deve_incluir_o_custo_de_diarias_no_custo_total_se_for_um_evento_na_mesma_cidade() {

    }

    private Cidade criarCidadePadrao() {
        return new Cidade(Estado.MS, "Campo Grande");
    }
}