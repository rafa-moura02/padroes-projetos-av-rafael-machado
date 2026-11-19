package provedor.aws;

import provedor.ProvedorFactory;
import provedor.TabelaPrecos;
import recurso.Armazenamento;
import recurso.Fila;
import recurso.MaquinaVirtual;

/** Abstract Factory - ConcreteFactory da Aws (RF01). */
public class AwsFactory implements ProvedorFactory {

    public static final String NOME = "Aws";

    private static final TabelaPrecos PRECOS = new TabelaPrecos(
            AwsMaquinaVirtual.CUSTO_POR_HORA, AwsArmazenamento.CUSTO_POR_GB_MES,
            AwsFila.CUSTO_FIXO_MENSAL);

    @Override
    public String getNome() {
        return NOME;
    }

    @Override
    public TabelaPrecos getTabelaPrecos() {
        return PRECOS;
    }

    @Override
    public MaquinaVirtual criarMaquinaVirtual() {
        return new AwsMaquinaVirtual();
    }

    @Override
    public Armazenamento criarArmazenamento(int quantidadeGb) {
        return new AwsArmazenamento(quantidadeGb);
    }

    @Override
    public Fila criarFila() {
        return new AwsFila();
    }
}
