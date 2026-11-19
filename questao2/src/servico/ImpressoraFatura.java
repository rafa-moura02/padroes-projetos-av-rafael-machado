package servico;

import util.Moeda;

/**
 * RF09 / RNF04: imprime a fatura tratando qualquer Compra da mesma forma,
 * sem verificar quais (ou quantos) servicos foram contratados.
 */
public final class ImpressoraFatura {

    private ImpressoraFatura() {
    }

    public static void imprimir(Compra compra) {
        System.out.println("===== FATURA DO COMPRADOR =====");
        System.out.println("Lote:           " + compra.getNomeLote());
        System.out.println("Comprador:      " + compra.getComprador());
        System.out.println("Valor de venda: " + Moeda.formatar(compra.getValorVenda()));
        System.out.println("Servicos adicionais:");
        for (ItemServico item : compra.getServicos()) {
            System.out.printf("  - %-30s %s%n", item.descricao(), Moeda.formatar(item.custo()));
        }
        System.out.println("Total a pagar:  " + Moeda.formatar(compra.getTotal()));
    }
}
