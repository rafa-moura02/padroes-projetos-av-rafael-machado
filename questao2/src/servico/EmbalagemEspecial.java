package servico;

public class EmbalagemEspecial extends ServicoAdicional {

    public EmbalagemEspecial(Compra compra) {
        super(compra);
    }

    @Override
    protected String getDescricao() {
        return "Embalagem especial";
    }

    @Override
    protected double getCusto() {
        return 180.00;
    }
}
