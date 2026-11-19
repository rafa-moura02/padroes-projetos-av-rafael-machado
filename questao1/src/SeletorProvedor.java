import provedor.ProvedorFactory;
import provedor.aws.AwsFactory;
import provedor.azure.AzureFactory;

// Escolhe a fabrica pelo nome. Para adicionar Gcp basta criar as classes e um case aqui
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
