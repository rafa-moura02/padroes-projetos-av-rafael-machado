package provedor.aws;

import java.util.concurrent.atomic.AtomicInteger;
import recurso.Armazenamento;

class AwsArmazenamento implements Armazenamento {

    static final double CUSTO_POR_GB_MES = 0.023;
    private static final AtomicInteger SEQUENCIA = new AtomicInteger();

    private final String id;
    private final int quantidadeGb;

    AwsArmazenamento(int quantidadeGb) {
        this.id = "s3://" + SEQUENCIA.incrementAndGet();
        this.quantidadeGb = quantidadeGb;
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
    public int getQuantidadeGb() {
        return quantidadeGb;
    }

    @Override
    public double getCustoPorGbMes() {
        return CUSTO_POR_GB_MES;
    }
}
