import ambiente.Ambiente;
import ambiente.Provisionador;
import ambiente.ProvisionamentoRecusadoException;
import ambiente.SolicitacaoAmbiente;
import auditoria.HistoricoAuditoria;
import config.Configuracao;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        List<SolicitacaoAmbiente> solicitacoes = List.of(
                new SolicitacaoAmbiente("loja-producao", "Aws", 3, 200),
                new SolicitacaoAmbiente("loja-homologacao", "Aws", 1, 50),
                new SolicitacaoAmbiente("erp-producao", "Azure", 2, 500),
                new SolicitacaoAmbiente("bigdata-analytics", "Azure", 10, 5000));

        CountDownLatch largada = new CountDownLatch(1);
        List<Thread> threads = new ArrayList<>();
        for (SolicitacaoAmbiente solicitacao : solicitacoes) {
            Thread t = new Thread(() -> {
                try {
                    largada.await(); // todas as threads partem ao mesmo tempo
                    Provisionador provisionador =
                            new Provisionador(SeletorProvedor.obter(solicitacao.provedor()));
                    Ambiente ambiente = provisionador.provisionar(solicitacao);
                    System.out.println(ambiente.resumo() + "\n");
                } catch (ProvisionamentoRecusadoException e) {
                    System.out.println("----- Provisionamento RECUSADO: "
                            + solicitacao.nomeAmbiente() + " (" + solicitacao.provedor()
                            + ") -> " + e.getMessage() + "\n");
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }, "provisionamento-" + solicitacao.nomeAmbiente());
            threads.add(t);
            t.start();
        }

        largada.countDown();
        for (Thread t : threads) {
            t.join();
        }

        HistoricoAuditoria.getInstancia().imprimir();
        System.out.println("\nArquivo config.properties lido "
                + Configuracao.getQuantidadeLeituras() + " vez(es).");
    }
}
