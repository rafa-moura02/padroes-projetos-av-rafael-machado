package observador;

import java.util.HashMap;
import java.util.Map;
import lote.Lance;
import lote.Lote;
import util.Moeda;

/** Observer - ConcreteObserver: exibe o maior lance e o total de lances de cada lote (RF04). */
public class PainelLeiloeiro implements ObservadorLance {

    private final Map<Lote, Integer> totalLances = new HashMap<>();
    private final Map<Lote, Lance> maiorLance = new HashMap<>();

    @Override
    public void atualizar(Lote lote, Lance lance) {
        int total = totalLances.merge(lote, 1, Integer::sum);
        maiorLance.merge(lote, lance, (atual, novo) -> novo.valor() > atual.valor() ? novo : atual);
        Lance maior = maiorLance.get(lote);
        System.out.println("   [Painel do leiloeiro] '" + lote.getNome() + "' | maior lance: "
                + Moeda.formatar(maior.valor()) + " (" + maior.licitante().getNome()
                + ") | total de lances: " + total);
    }
}
