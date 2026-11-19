package servico;

import java.util.List;

public class CompraBase implements Compra {

    private final String nomeLote;
    private final String comprador;
    private final double valorVenda;

    public CompraBase(String nomeLote, String comprador, double valorVenda) {
        this.nomeLote = nomeLote;
        this.comprador = comprador;
        this.valorVenda = valorVenda;
    }

    @Override
    public String getNomeLote() {
        return nomeLote;
    }

    @Override
    public String getComprador() {
        return comprador;
    }

    @Override
    public double getValorVenda() {
        return valorVenda;
    }

    @Override
    public List<ItemServico> getServicos() {
        return List.of();
    }

    @Override
    public double getTotal() {
        return valorVenda;
    }
}
