package provedor;

import recurso.Armazenamento;
import recurso.Fila;
import recurso.MaquinaVirtual;

// Fabrica abstrata: cada provedor cria seus tres recursos
public interface ProvedorFactory {
    String getNome();

    TabelaPrecos getTabelaPrecos();

    MaquinaVirtual criarMaquinaVirtual();

    Armazenamento criarArmazenamento(int quantidadeGb);

    Fila criarFila();
}
