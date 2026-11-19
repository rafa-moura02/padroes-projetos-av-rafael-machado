package servico;

import java.util.List;

/**
 * Padrao Decorator - Component (RNF03, RNF04).
 * Tanto a compra simples quanto a compra "decorada" com servicos adicionais
 * sao tratadas pela mesma interface.
 */
public interface Compra {
    String getNomeLote();

    String getComprador();

    double getValorVenda();

    List<ItemServico> getServicos();

    double getTotal();
}
