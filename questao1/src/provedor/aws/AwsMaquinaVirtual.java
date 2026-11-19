package provedor.aws;

import java.util.concurrent.atomic.AtomicInteger;
import recurso.MaquinaVirtual;

/** ConcreteProductA1. Construtor package-private: so a AwsFactory instancia (RNF06). */
class AwsMaquinaVirtual implements MaquinaVirtual {

    static final double CUSTO_POR_HORA = 0.10;
    private static final AtomicInteger SEQUENCIA = new AtomicInteger();

    private final String id;

    AwsMaquinaVirtual() {
        this.id = "i-" + SEQUENCIA.incrementAndGet();
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getProvedor() {
        return AwsFactory.NOME;
    }

    @Override
    public double getCustoPorHora() {
        return CUSTO_POR_HORA;
    }
}
