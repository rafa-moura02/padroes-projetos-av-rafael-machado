package provedor;

// Precos usados para calcular o custo antes de criar os recursos
public record TabelaPrecos(double custoHoraMaquina, double custoGbMesArmazenamento,
        double custoFixoFila) {

    public static final int HORAS_POR_MES = 730;

    public double estimarCustoMensal(int quantidadeMaquinas, int quantidadeGb) {
        return quantidadeMaquinas * custoHoraMaquina * HORAS_POR_MES
                + quantidadeGb * custoGbMesArmazenamento
                + custoFixoFila;
    }
}
