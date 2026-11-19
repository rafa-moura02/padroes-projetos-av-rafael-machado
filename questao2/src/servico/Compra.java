package servico;

import java.util.List;

public interface Compra {
    String getNomeLote();

    String getComprador();

    double getValorVenda();

    List<ItemServico> getServicos();

    double getTotal();
}
