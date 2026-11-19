package acerto;

import lote.Lote;
import lote.StatusLote;
import util.Moeda;

/** RF10: imprime valor de venda, comissao cobrada e valor liquido ao vendedor. */
public final class AcertoVendedor {

    private AcertoVendedor() {
    }

    public static void imprimir(Lote lote) {
        System.out.println("===== ACERTO DO VENDEDOR - " + lote.getNome() + " =====");
        if (lote.getStatus() != StatusLote.VENDIDO) {
            System.out.println("Lote nao vendido: nada a repassar e nenhuma comissao cobrada.");
            return;
        }
        double valor = lote.getValorVenda();
        double comissao = lote.getComissaoCobrada();
        System.out.println("Valor de venda:      " + Moeda.formatar(valor));
        System.out.println("Comissao cobrada:    " + Moeda.formatar(comissao)
                + "  [regra: " + lote.getRegraComissao().getDescricao() + "]");
        System.out.println("Liquido ao vendedor: " + Moeda.formatar(valor - comissao));
    }
}
