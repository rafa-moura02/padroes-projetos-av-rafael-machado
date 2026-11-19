package observador;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import lote.Lance;
import lote.Lote;
import util.Moeda;

public class Auditor implements ObservadorLance {

    private static final DateTimeFormatter FORMATO =
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss.SSS");

    private final List<String> registros = new ArrayList<>();

    @Override
    public void atualizar(Lote lote, Lance lance) {
        String registro = "[" + FORMATO.format(LocalDateTime.now()) + "] lote='" + lote.getNome()
                + "' participante=" + lance.licitante().getNome()
                + " valor=" + Moeda.formatar(lance.valor());
        registros.add(registro);
        System.out.println("   [Auditor] registrado: " + registro);
    }

    public List<String> getRegistros() {
        return List.copyOf(registros);
    }

    public void imprimirRegistros() {
        System.out.println("===== REGISTROS DO AUDITOR (" + registros.size() + ") =====");
        registros.forEach(r -> System.out.println("  " + r));
    }
}
