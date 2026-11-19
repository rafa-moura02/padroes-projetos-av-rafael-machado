package auditoria;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Padrao Singleton (RNF05).
 * Historico unico em todo o programa, acessivel por qualquer classe via
 * getInstancia(). Os metodos sao sincronizados: a data/hora e a sequencia sao
 * geradas dentro da regiao critica, garantindo que nenhum registro se perca ou
 * seja sobrescrito e que a ordem impressa seja a ordem em que foram feitos.
 */
public final class HistoricoAuditoria {

    private final List<RegistroAuditoria> registros = new ArrayList<>();

    private HistoricoAuditoria() {
    }

    private static final class Holder {
        private static final HistoricoAuditoria INSTANCIA = new HistoricoAuditoria();
    }

    public static HistoricoAuditoria getInstancia() {
        return Holder.INSTANCIA;
    }

    public synchronized void registrar(String ambiente, String provedor, Resultado resultado,
            String motivo) {
        registros.add(new RegistroAuditoria(registros.size() + 1, LocalDateTime.now(), ambiente,
                provedor, resultado, motivo));
    }

    public synchronized List<RegistroAuditoria> getRegistros() {
        return List.copyOf(registros);
    }

    public synchronized void imprimir() {
        System.out.println("===== HISTORICO DE AUDITORIA (" + registros.size() + " registros) =====");
        registros.forEach(System.out::println);
    }
}
