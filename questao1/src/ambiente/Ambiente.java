package ambiente;

import java.util.List;
import java.util.Locale;
import recurso.Armazenamento;
import recurso.Fila;
import recurso.MaquinaVirtual;
import recurso.Recurso;

public final class Ambiente {

    private static final Locale PT_BR = Locale.forLanguageTag("pt-BR");

    private final String nome;
    private final String provedor;
    private final String regiao;
    private final List<MaquinaVirtual> maquinas;
    private final Armazenamento armazenamento;
    private final Fila fila;
    private final double custoMensalEstimado;

    Ambiente(String nome, String provedor, String regiao, List<MaquinaVirtual> maquinas,
            Armazenamento armazenamento, Fila fila, double custoMensalEstimado) {
        this.nome = nome;
        this.provedor = provedor;
        this.regiao = regiao;
        this.maquinas = List.copyOf(maquinas);
        this.armazenamento = armazenamento;
        this.fila = fila;
        this.custoMensalEstimado = custoMensalEstimado;
        validarMesmoProvedor();
    }

    private void validarMesmoProvedor() {
        boolean misturado = maquinas.stream().map(Recurso::getProvedor)
                .anyMatch(p -> !p.equals(provedor))
                || !armazenamento.getProvedor().equals(provedor)
                || !fila.getProvedor().equals(provedor);
        if (misturado) {
            throw new IllegalStateException("Recursos de provedores diferentes no ambiente " + nome);
        }
    }

    public String getNome() {
        return nome;
    }

    public String getProvedor() {
        return provedor;
    }

    public String getRegiao() {
        return regiao;
    }

    public List<MaquinaVirtual> getMaquinas() {
        return maquinas;
    }

    public Armazenamento getArmazenamento() {
        return armazenamento;
    }

    public Fila getFila() {
        return fila;
    }

    public double getCustoMensalEstimado() {
        return custoMensalEstimado;
    }

    public String resumo() {
        StringBuilder sb = new StringBuilder();
        sb.append("----- Ambiente provisionado: ").append(nome).append(" -----\n");
        sb.append("Provedor: ").append(provedor).append(" | Regiao: ").append(regiao).append('\n');
        for (MaquinaVirtual vm : maquinas) {
            sb.append("  Maquina virtual: ").append(vm.getId()).append('\n');
        }
        sb.append("  Armazenamento:   ").append(armazenamento.getId())
                .append(" (").append(armazenamento.getQuantidadeGb()).append(" GB)\n");
        sb.append("  Fila:            ").append(fila.getId()).append('\n');
        sb.append(String.format(PT_BR, "Custo mensal estimado: R$ %,.2f", custoMensalEstimado));
        return sb.toString();
    }
}
