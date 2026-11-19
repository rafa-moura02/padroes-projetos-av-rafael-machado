import acerto.AcertoVendedor;
import comissao.ComissaoArte;
import comissao.ComissaoEletronicos;
import comissao.ComissaoVeiculos;
import lote.Categoria;
import lote.Lote;
import lote.ResultadoLance;
import observador.Auditor;
import observador.Licitante;
import observador.PainelLeiloeiro;
import observador.Participante;
import servico.CertificadoAutenticidade;
import servico.Compra;
import servico.CompraBase;
import servico.EmbalagemEspecial;
import servico.EntregaExpressa;
import servico.ImpressoraFatura;
import servico.SeguroTransporte;
import util.Moeda;

public class Main {

    public static void main(String[] args) {
        Categoria arte = new Categoria("Arte", new ComissaoArte());
        Categoria veiculos = new Categoria("Veiculos", new ComissaoVeiculos());
        Categoria eletronicos = new Categoria("Eletronicos", new ComissaoEletronicos());

        Lote quadro = new Lote("Quadro Entardecer no Litoral", arte, 10_000.00, 15_000.00);
        Lote fusca = new Lote("Fusca 1974 restaurado", veiculos, 20_000.00, 60_000.00);

        Participante ana = new Participante("Ana");
        Participante bruno = new Participante("Bruno");
        Participante carla = new Participante("Carla");
        PainelLeiloeiro painel = new PainelLeiloeiro();
        Auditor auditor = new Auditor();

        for (Lote lote : new Lote[] {quadro, fusca}) {
            lote.anexar(ana);
            lote.anexar(bruno);
            lote.anexar(carla);
            lote.anexar(painel);
            lote.anexar(auditor);
        }

        titulo("Lances no lote " + quadro.getNome());
        lance(quadro, ana, 10_500.00);
        lance(quadro, bruno, 10_800.00); // menos de 5%, vai ser recusado
        lance(quadro, bruno, 12_000.00);
        lance(quadro, carla, 13_000.00);

        titulo("Carla deixa de acompanhar o lote " + quadro.getNome());
        quadro.desanexar(carla);
        lance(quadro, ana, 14_000.00);
        lance(quadro, bruno, 16_000.00);

        titulo("Lances no lote " + fusca.getNome());
        lance(fusca, carla, 22_000.00);
        lance(fusca, ana, 22_500.00);
        lance(fusca, bruno, 25_000.00);

        titulo("Campanha: lote " + quadro.getNome() + " passa a usar a regra de Eletronicos");
        System.out.println("Regra anterior: " + quadro.getRegraComissao().getDescricao());
        quadro.setRegraComissao(eletronicos.regraPadrao());
        System.out.println("Regra vigente:  " + quadro.getRegraComissao().getDescricao());

        titulo("Encerramento dos lotes");
        System.out.println(quadro.getNome() + " -> " + quadro.encerrar()
                + " (maior lance " + Moeda.formatar(quadro.getLanceAtual())
                + ", reserva " + Moeda.formatar(quadro.getPrecoReserva()) + ")");
        System.out.println(fusca.getNome() + " -> " + fusca.encerrar()
                + " (maior lance " + Moeda.formatar(fusca.getLanceAtual())
                + ", reserva " + Moeda.formatar(fusca.getPrecoReserva()) + ")");
        lance(fusca, ana, 30_000.00); // lote ja encerrado

        titulo("Acerto com os vendedores");
        AcertoVendedor.imprimir(quadro);
        System.out.println();
        AcertoVendedor.imprimir(fusca);

        titulo("Fatura do comprador");
        Compra compra = new CompraBase(quadro.getNome(), quadro.getComprador().getNome(),
                quadro.getValorVenda());
        compra = new SeguroTransporte(compra);
        compra = new CertificadoAutenticidade(compra);
        compra = new EmbalagemEspecial(compra);
        compra = new EmbalagemEspecial(compra);
        compra = new EntregaExpressa(compra);
        ImpressoraFatura.imprimir(compra);

        System.out.println();
        auditor.imprimirRegistros();
    }

    private static void lance(Lote lote, Licitante licitante, double valor) {
        ResultadoLance resultado = lote.darLance(licitante, valor);
        System.out.println((resultado.aceito() ? "[ACEITO]   " : "[RECUSADO] ")
                + resultado.mensagem());
    }

    private static void titulo(String texto) {
        System.out.println("\n>>> " + texto);
    }
}
