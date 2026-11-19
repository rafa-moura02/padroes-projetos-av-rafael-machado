package comissao;

public class ComissaoVeiculos implements RegraComissao {

    private static final double LIMITE = 5_000.00;

    @Override
    public double calcular(double valorVenda) {
        return Math.min(valorVenda * 0.08, LIMITE);
    }

    @Override
    public String getDescricao() {
        return "Veiculos (8%, limitada a R$ 5.000,00)";
    }
}
