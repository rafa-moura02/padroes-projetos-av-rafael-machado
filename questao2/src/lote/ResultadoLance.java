package lote;

/** Resultado da tentativa de lance, com mensagem explicativa (RF02). */
public record ResultadoLance(boolean aceito, String mensagem) {
}
