package provedor;

import recurso.Armazenamento;
import recurso.Fila;
import recurso.MaquinaVirtual;

/**
 * Padrao Abstract Factory - AbstractFactory (RNF01, RNF02, RNF03).
 * Cada fabrica concreta cria a familia completa de recursos de UM provedor,
 * impedindo a mistura de recursos de provedores diferentes.
 */
public interface ProvedorFactory {
    String getNome();

    TabelaPrecos getTabelaPrecos();

    MaquinaVirtual criarMaquinaVirtual();

    Armazenamento criarArmazenamento(int quantidadeGb);

    Fila criarFila();
}
