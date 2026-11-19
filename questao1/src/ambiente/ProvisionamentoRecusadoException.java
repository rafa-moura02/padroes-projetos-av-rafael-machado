package ambiente;

/** Lancada quando a solicitacao ultrapassa o orcamento (RF05). */
public class ProvisionamentoRecusadoException extends Exception {
    private static final long serialVersionUID = 1L;

    public ProvisionamentoRecusadoException(String motivo) {
        super(motivo);
    }
}
