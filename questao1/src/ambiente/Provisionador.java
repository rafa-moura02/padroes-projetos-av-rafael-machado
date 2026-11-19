package ambiente;

import auditoria.HistoricoAuditoria;
import auditoria.Resultado;
import config.Configuracao;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import provedor.ProvedorFactory;
import recurso.MaquinaVirtual;

// Monta o ambiente usando so a fabrica recebida, sem saber qual provedor e
public class Provisionador {

    private static final Locale PT_BR = Locale.forLanguageTag("pt-BR");

    private final ProvedorFactory fabrica;

    public Provisionador(ProvedorFactory fabrica) {
        this.fabrica = fabrica;
    }

    public Ambiente provisionar(SolicitacaoAmbiente solicitacao)
            throws ProvisionamentoRecusadoException {
        Configuracao config = Configuracao.getInstancia();
        HistoricoAuditoria historico = HistoricoAuditoria.getInstancia();

        double custo = fabrica.getTabelaPrecos()
                .estimarCustoMensal(solicitacao.quantidadeMaquinas(), solicitacao.quantidadeGb());

        // se passar do orcamento recusa antes de criar qualquer recurso
        if (custo > config.getOrcamentoMaximo()) {
            String motivo = String.format(PT_BR,
                    "custo estimado R$ %,.2f ultrapassa o orcamento de R$ %,.2f",
                    custo, config.getOrcamentoMaximo());
            historico.registrar(solicitacao.nomeAmbiente(), fabrica.getNome(), Resultado.RECUSADO,
                    motivo);
            throw new ProvisionamentoRecusadoException(motivo);
        }

        List<MaquinaVirtual> maquinas = new ArrayList<>();
        for (int i = 0; i < solicitacao.quantidadeMaquinas(); i++) {
            maquinas.add(fabrica.criarMaquinaVirtual());
        }
        Ambiente ambiente = new Ambiente(solicitacao.nomeAmbiente(), fabrica.getNome(),
                config.getRegiaoPadrao(), maquinas,
                fabrica.criarArmazenamento(solicitacao.quantidadeGb()), fabrica.criarFila(), custo);

        historico.registrar(solicitacao.nomeAmbiente(), fabrica.getNome(), Resultado.ACEITO, null);
        return ambiente;
    }
}
