package recurso;

/** Abstract Factory - AbstractProductB. */
public interface Armazenamento extends Recurso {
    int getQuantidadeGb();

    double getCustoPorGbMes();
}
