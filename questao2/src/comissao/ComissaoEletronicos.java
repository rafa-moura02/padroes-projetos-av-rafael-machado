package comissao;

public class ComissaoEletronicos implements RegraComissao {

    private static final double MINIMO = 50.00;

    @Override
    public double calcular(double valorVenda) {
        return Math.max(valorVenda * 0.12, MINIMO);
    }

    @Override
    public String getDescricao() {
        return "Eletronicos (12%, minimo R$ 50,00)";
    }
}
