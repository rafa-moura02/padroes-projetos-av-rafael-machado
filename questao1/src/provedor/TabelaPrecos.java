package provedor;

/**
 * Precos de um provedor, usados para estimar o custo antes de criar recursos
 * (RF05: solicitacao recusada nao cria nenhum recurso).
 */
public record TabelaPrecos(double custoHoraMaquina, double custoGbMesArmazenamento,
        double custoFixoFila) {

    public static final int HORAS_POR_MES = 730;

    /** RF04: maquinas x custo/hora x 730 + GB x custo/GB + custo fixo da fila. */
    public double estimarCustoMensal(int quantidadeMaquinas, int quantidadeGb) {
        return quantidadeMaquinas * custoHoraMaquina * HORAS_POR_MES
                + quantidadeGb * custoGbMesArmazenamento
                + custoFixoFila;
    }
}
