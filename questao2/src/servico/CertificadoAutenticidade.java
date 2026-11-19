package servico;

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
