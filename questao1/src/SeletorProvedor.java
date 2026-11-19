import provedor.ProvedorFactory;
import provedor.aws.AwsFactory;
import provedor.azure.AzureFactory;

/**
 * Componente (usado pela Main) que escolhe a fabrica concreta a partir do
 * nome do provedor. E o UNICO ponto a alterar ao incluir um novo provedor
 * (ex.: Gcp -> criar GcpFactory + produtos e adicionar um case aqui) - RNF03.
 */
public final class SeletorProvedor {

    private SeletorProvedor() {
    }

    public static ProvedorFactory obter(String provedor) {
        return switch (provedor.toLowerCase()) {
            case "aws" -> new AwsFactory();
            case "azure" -> new AzureFactory();
            default -> throw new IllegalArgumentException("Provedor desconhecido: " + provedor);
        };
    }
}
