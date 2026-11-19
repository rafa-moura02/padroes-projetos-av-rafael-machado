package servico;

/** ConcreteDecorator: seguro de transporte, 1,5% do valor de venda. */
public class SeguroTransporte extends ServicoAdicional {

    public SeguroTransporte(Compra compra) {
        super(compra);
    }

    @Override
    protected String getDescricao() {
        return "Seguro de transporte (1,5%)";
    }

    @Override
    protected double getCusto() {
        return getValorVenda() * 0.015;
    }
}
