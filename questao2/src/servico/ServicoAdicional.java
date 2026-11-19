package servico;

import java.util.ArrayList;
import java.util.List;

/**
 * Padrao Decorator - Decorator abstrato.
 * Envolve qualquer Compra e acrescenta sua descricao e seu custo a fatura,
 * permitindo qualquer combinacao, ordem e quantidade de servicos.
 */
public abstract class ServicoAdicional implements Compra {

    protected final Compra compra;

    protected ServicoAdicional(Compra compra) {
        this.compra = compra;
    }

    protected abstract String getDescricao();

    protected abstract double getCusto();

    @Override
    public String getNomeLote() {
        return compra.getNomeLote();
    }

    @Override
    public String getComprador() {
        return compra.getComprador();
    }

    @Override
    public double getValorVenda() {
        return compra.getValorVenda();
    }

    @Override
    public List<ItemServico> getServicos() {
        List<ItemServico> servicos = new ArrayList<>(compra.getServicos());
        servicos.add(new ItemServico(getDescricao(), getCusto()));
        return List.copyOf(servicos);
    }

    @Override
    public double getTotal() {
        return compra.getTotal() + getCusto();
    }
}
