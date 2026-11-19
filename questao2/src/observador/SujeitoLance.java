package observador;

import lote.Lance;

/** Padrao Observer - Subject: gerencia e notifica os observadores. */
public interface SujeitoLance {
    void anexar(ObservadorLance observador);

    void desanexar(ObservadorLance observador);

    void notificar(Lance lance);
}
