package comissao;

/**
 * Padrao Strategy - Strategy (RNF02).
 * Cada categoria tem sua regra em uma classe propria; nao ha if/switch sobre
 * a categoria. Novas categorias exigem apenas novas implementacoes.
 */
public interface RegraComissao {
    double calcular(double valorVenda);

    String getDescricao();
}
