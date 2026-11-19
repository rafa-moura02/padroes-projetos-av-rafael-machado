package config;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Padrao Singleton (RNF04).
 * Le o arquivo config.properties uma unica vez (inicializacao preguicosa e
 * thread-safe via "Initialization-on-demand holder") e expoe os mesmos valores
 * para todas as classes, sem que a configuracao seja passada por parametro.
 */
public final class Configuracao {

    private static final AtomicInteger LEITURAS = new AtomicInteger();

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
        LEITURAS.incrementAndGet();
        this.regiaoPadrao = props.getProperty("regiao.padrao").trim();
        this.orcamentoMaximo = Double.parseDouble(props.getProperty("orcamento.maximo").trim());
    }

    private static Path localizarArquivo() {
        String informado = System.getProperty("config.path");
        if (informado != null) {
            return Path.of(informado);
        }
        for (String candidato : new String[] {"config.properties", "src/config.properties",
                "questao1/src/config.properties"}) {
            Path p = Path.of(candidato);
            if (Files.exists(p)) {
                return p;
            }
        }
        throw new IllegalStateException("Arquivo config.properties nao encontrado");
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

    /** Quantidade de vezes que o arquivo foi lido (deve ser sempre 1). */
    public static int getQuantidadeLeituras() {
        return LEITURAS.get();
    }
}
