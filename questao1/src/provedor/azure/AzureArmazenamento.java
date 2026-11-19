package provedor.azure;

import java.util.concurrent.atomic.AtomicInteger;
import recurso.Armazenamento;

class AzureArmazenamento implements Armazenamento {

    static final double CUSTO_POR_GB_MES = 0.02;
    private static final AtomicInteger SEQUENCIA = new AtomicInteger();

    private final String id;
    private final int quantidadeGb;

    AzureArmazenamento(int quantidadeGb) {
        this.id = "blob://" + SEQUENCIA.incrementAndGet();
        this.quantidadeGb = quantidadeGb;
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
    public int getQuantidadeGb() {
        return quantidadeGb;
    }

    @Override
    public double getCustoPorGbMes() {
        return CUSTO_POR_GB_MES;
    }
}
