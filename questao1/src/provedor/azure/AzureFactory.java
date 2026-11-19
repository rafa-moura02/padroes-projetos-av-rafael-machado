package provedor.azure;

import provedor.ProvedorFactory;
import provedor.TabelaPrecos;
import recurso.Armazenamento;
import recurso.Fila;
import recurso.MaquinaVirtual;

public class AzureFactory implements ProvedorFactory {

    public static final String NOME = "Azure";

    private static final TabelaPrecos PRECOS = new TabelaPrecos(
            AzureMaquinaVirtual.CUSTO_POR_HORA, AzureArmazenamento.CUSTO_POR_GB_MES,
            AzureFila.CUSTO_FIXO_MENSAL);

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
        return new AzureMaquinaVirtual();
    }

    @Override
    public Armazenamento criarArmazenamento(int quantidadeGb) {
        return new AzureArmazenamento(quantidadeGb);
    }

    @Override
    public Fila criarFila() {
        return new AzureFila();
    }
}
