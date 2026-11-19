package observador;

import lote.Lance;
import lote.Lote;
import util.Moeda;

/** Observer - ConcreteObserver: participante que da lances e acompanha lotes (RF04). */
public class Participante implements Licitante, ObservadorLance {

    private final String nome;

    public Participante(String nome) {
        this.nome = nome;
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public void atualizar(Lote lote, Lance lance) {
        System.out.println("   [" + nome + "] Novo lance no lote '" + lote.getNome() + "': "
                + Moeda.formatar(lance.valor()));
    }

    @Override
    public String toString() {
        return nome;
    }
}
