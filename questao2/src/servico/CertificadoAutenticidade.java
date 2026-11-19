package servico;

/** ConcreteDecorator: certificado de autenticidade, R$ 300,00 fixos. */
public class CertificadoAutenticidade extends ServicoAdicional {

    public CertificadoAutenticidade(Compra compra) {
        super(compra);
    }

    @Override
    protected String getDescricao() {
        return "Certificado de autenticidade";
    }

    @Override
    protected double getCusto() {
        return 300.00;
    }
}
