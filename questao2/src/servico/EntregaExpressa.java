package servico;

/** ConcreteDecorator: entrega expressa, R$ 450,00 fixos. */
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
