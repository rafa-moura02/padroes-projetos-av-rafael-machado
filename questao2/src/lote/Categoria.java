package lote;

import comissao.RegraComissao;

/**
 * Categoria de lote com sua regra de comissao padrao. Uma nova categoria e
 * apenas uma nova instancia associada a uma nova RegraComissao (RNF02).
 */
public record Categoria(String nome, RegraComissao regraPadrao) {
}
