package provedor.aws;

import java.util.concurrent.atomic.AtomicInteger;
import recurso.Fila;

/** ConcreteProductC1 (SQS). */
class AwsFila implements Fila {

    static final double CUSTO_FIXO_MENSAL = 0.40;
    private static final AtomicInteger SEQUENCIA = new AtomicInteger();

    private final String id;

    AwsFila() {
        this.id = "sqs-" + SEQUENCIA.incrementAndGet();
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
    public double getCustoFixoMensal() {
        return CUSTO_FIXO_MENSAL;
    }
}
