package config;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

// Singleton que le o config.properties uma vez so
public final class Configuracao {

    private static int leituras = 0;

    private final String regiaoPadrao;
    private final double orcamentoMaximo;

    private Configuracao() {
        Properties props = new Properties();
        Path arquivo = localizarArquivo();
        try (InputStream in = Files.newInputStream(arquivo)) {
            props.load(in);
        } catch (IOException e) {
            throw new IllegalStateException("Nao foi possivel ler " + arquivo.toAbsolutePath(), e);
        }
        leituras++;
        this.regiaoPadrao = props.getProperty("regiao.padrao").trim();
        this.orcamentoMaximo = Double.parseDouble(props.getProperty("orcamento.maximo").trim());
    }

    // procura o arquivo rodando de dentro da pasta questao1 ou da src
    private static Path localizarArquivo() {
        Path arquivo = Path.of("src/config.properties");
        if (!Files.exists(arquivo)) {
            arquivo = Path.of("config.properties");
        }
        return arquivo;
    }

    private static final class Holder {
        private static final Configuracao INSTANCIA = new Configuracao();
    }

    public static Configuracao getInstancia() {
        return Holder.INSTANCIA;
    }

    public String getRegiaoPadrao() {
        return regiaoPadrao;
    }

    public double getOrcamentoMaximo() {
        return orcamentoMaximo;
    }

    public static int getQuantidadeLeituras() {
        return leituras;
    }
}
