package provedor.azure;

import java.util.concurrent.atomic.AtomicInteger;
import recurso.MaquinaVirtual;

// construtor sem public para so a AzureFactory conseguir criar
class AzureMaquinaVirtual implements MaquinaVirtual {

    static final double CUSTO_POR_HORA = 0.12;
    private static final AtomicInteger SEQUENCIA = new AtomicInteger();

    private final String id;

    AzureMaquinaVirtual() {
        this.id = "vm-" + SEQUENCIA.incrementAndGet();
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
    public double getCustoPorHora() {
        return CUSTO_POR_HORA;
    }
}
