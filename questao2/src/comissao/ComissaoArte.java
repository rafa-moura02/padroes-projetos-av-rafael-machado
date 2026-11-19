package comissao;

public class ComissaoArte implements RegraComissao {

    @Override
    public double calcular(double valorVenda) {
        return valorVenda * 0.20;
    }

    @Override
    public String getDescricao() {
        return "Arte (20%)";
    }
}
