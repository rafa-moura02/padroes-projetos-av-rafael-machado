package lote;

import comissao.RegraComissao;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import observador.Licitante;
import observador.ObservadorLance;
import observador.SujeitoLance;
import util.Moeda;

public class Lote implements SujeitoLance {

    private static final BigDecimal INCREMENTO_MINIMO = new BigDecimal("1.05");

    private final String nome;
    private final Categoria categoria;
    private final double precoInicial;
    private final double precoReserva;
    private final List<ObservadorLance> observadores = new ArrayList<>();
    private final List<Lance> lances = new ArrayList<>();

    private RegraComissao regraComissao;
    private StatusLote status = StatusLote.ABERTO;
    private double comissaoCobrada;

    public Lote(String nome, Categoria categoria, double precoInicial, double precoReserva) {
        this.nome = nome;
        this.categoria = categoria;
        this.precoInicial = precoInicial;
        this.precoReserva = precoReserva;
        this.regraComissao = categoria.regraPadrao();
    }


    @Override
    public void anexar(ObservadorLance observador) {
        if (!observadores.contains(observador)) {
            observadores.add(observador);
        }
    }

    @Override
    public void desanexar(ObservadorLance observador) {
        observadores.remove(observador);
    }

    @Override
    public void notificar(Lance lance) {
        for (ObservadorLance observador : List.copyOf(observadores)) {
            if (observador != lance.licitante()) {
                observador.atualizar(this, lance);
            }
        }
    }


    public ResultadoLance darLance(Licitante licitante, double valor) {
        if (status != StatusLote.ABERTO) {
            return new ResultadoLance(false, "Lance de " + licitante.getNome() + " de "
                    + Moeda.formatar(valor) + " recusado: o lote '" + nome + "' esta encerrado.");
        }
        BigDecimal minimo = BigDecimal.valueOf(getLanceAtual()).multiply(INCREMENTO_MINIMO);
        if (BigDecimal.valueOf(valor).compareTo(minimo) < 0) {
            return new ResultadoLance(false, "Lance de " + licitante.getNome() + " de "
                    + Moeda.formatar(valor) + " recusado: deve ser pelo menos 5% maior que o lance atual ("
                    + Moeda.formatar(getLanceAtual()) + "), ou seja, no minimo "
                    + Moeda.formatar(minimo.doubleValue()) + ".");
        }
        Lance lance = new Lance(licitante, valor, LocalDateTime.now());
        lances.add(lance);
        notificar(lance);
        return new ResultadoLance(true, "Lance de " + licitante.getNome() + " de "
                + Moeda.formatar(valor) + " aceito no lote '" + nome + "'.");
    }

    public double getLanceAtual() {
        return getMaiorLance().map(Lance::valor).orElse(precoInicial);
    }

    public Optional<Lance> getMaiorLance() {
        return lances.isEmpty() ? Optional.empty() : Optional.of(lances.get(lances.size() - 1));
    }


    public StatusLote encerrar() {
        if (status != StatusLote.ABERTO) {
            throw new IllegalStateException("Lote '" + nome + "' ja foi encerrado.");
        }
        Optional<Lance> maior = getMaiorLance();
        if (maior.isPresent() && maior.get().valor() >= precoReserva) {
            status = StatusLote.VENDIDO;
            comissaoCobrada = regraComissao.calcular(maior.get().valor());
        } else {
            status = StatusLote.NAO_VENDIDO;
        }
        return status;
    }

    public void setRegraComissao(RegraComissao novaRegra) {
        if (status != StatusLote.ABERTO) {
            throw new IllegalStateException("Regra so pode ser trocada antes do encerramento.");
        }
        this.regraComissao = novaRegra;
    }

    public RegraComissao getRegraComissao() {
        return regraComissao;
    }

    public double getValorVenda() {
        exigirVendido();
        return getMaiorLance().orElseThrow().valor();
    }

    public Licitante getComprador() {
        exigirVendido();
        return getMaiorLance().orElseThrow().licitante();
    }

    public double getComissaoCobrada() {
        exigirVendido();
        return comissaoCobrada;
    }

    private void exigirVendido() {
        if (status != StatusLote.VENDIDO) {
            throw new IllegalStateException("Lote '" + nome + "' nao foi vendido.");
        }
    }

    public String getNome() {
        return nome;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public double getPrecoInicial() {
        return precoInicial;
    }

    public double getPrecoReserva() {
        return precoReserva;
    }

    public StatusLote getStatus() {
        return status;
    }

    public List<Lance> getLances() {
        return List.copyOf(lances);
    }
}
