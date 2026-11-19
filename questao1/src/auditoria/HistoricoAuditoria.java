package auditoria;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

// Singleton do historico. Metodos synchronized por causa das threads
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
