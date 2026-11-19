package observador;

import java.util.HashMap;
import java.util.Map;
import lote.Lance;
import lote.Lote;
import util.Moeda;

public class PainelLeiloeiro implements ObservadorLance {

    private final Map<Lote, Integer> totalLances = new HashMap<>();

    @Override
    public void atualizar(Lote lote, Lance lance) {
        // como o lance so e aceito se for maior, o ultimo recebido e sempre o maior
        int total = totalLances.getOrDefault(lote, 0) + 1;
        totalLances.put(lote, total);
        System.out.println("   [Painel do leiloeiro] '" + lote.getNome() + "' | maior lance: "
                + Moeda.formatar(lance.valor()) + " (" + lance.licitante().getNome()
                + ") | total de lances: " + total);
    }
}
