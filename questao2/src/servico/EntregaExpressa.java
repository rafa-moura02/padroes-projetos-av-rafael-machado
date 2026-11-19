package servico;

public class EntregaExpressa extends ServicoAdicional {

    public EntregaExpressa(Compra compra) {
        super(compra);
    }

    @Override
    protected String getDescricao() {
        return "Entrega expressa";
    }

    @Override
    protected double getCusto() {
        return 450.00;
    }
}
