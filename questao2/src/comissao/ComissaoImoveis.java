package comissao;

/** ConcreteStrategy: Imoveis, 3% do valor mais R$ 1.500,00 fixos. */
public class ComissaoImoveis implements RegraComissao {

    private static final double FIXO = 1_500.00;

    @Override
    public double calcular(double valorVenda) {
        return valorVenda * 0.03 + FIXO;
    }

    @Override
    public String getDescricao() {
        return "Imoveis (3% + R$ 1.500,00)";
    }
}
