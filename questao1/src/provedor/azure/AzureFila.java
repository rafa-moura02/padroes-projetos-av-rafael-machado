package provedor.azure;

import java.util.concurrent.atomic.AtomicInteger;
import recurso.Fila;

class AzureFila implements Fila {

    static final double CUSTO_FIXO_MENSAL = 0.50;
    private static final AtomicInteger SEQUENCIA = new AtomicInteger();

    private final String id;

    AzureFila() {
        this.id = "sb-" + SEQUENCIA.incrementAndGet();
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getProvedor() {
        return AzureFactory.NOME;
    }

    @Override
    public double getCustoFixoMensal() {
        return CUSTO_FIXO_MENSAL;
    }
}
