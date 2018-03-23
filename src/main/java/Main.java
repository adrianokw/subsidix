import modelo.*;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Main {

    public static void main(String[] args) {
        Calendar dataDeEntrada = new GregorianCalendar(2014, 3, 21);
        Calendar dataDeInicioDoEvento = new GregorianCalendar(2018, 4, 21);
        Calendar dataDeFimDoEvento = new GregorianCalendar(2018, 4, 24);
        Cidade cidade = new Cidade(Estado.MS, "Campo Grande");

        Evento evento = new Evento("Javaneiros", dataDeInicioDoEvento, dataDeFimDoEvento, cidade, false, new BigDecimal("300"));
        Colaborador colaborador = new Colaborador("Adriano", dataDeEntrada, cidade);
        Subsidio subsidio = new Subsidio(colaborador, evento, new BigDecimal("100"));

        System.out.printf("Valor total do subsídio: %s", subsidio.calcularCustoTotal());
    }

}
