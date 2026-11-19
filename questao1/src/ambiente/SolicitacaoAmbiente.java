package ambiente;

/** RF03: dados de uma solicitacao de ambiente. */
public record SolicitacaoAmbiente(String nomeAmbiente, String provedor, int quantidadeMaquinas,
        int quantidadeGb) {

    public SolicitacaoAmbiente {
        if (quantidadeMaquinas < 0 || quantidadeGb < 0) {
            throw new IllegalArgumentException("Quantidades nao podem ser negativas");
        }
    }
}
