package observador;

import lote.Lance;

public interface SujeitoLance {
    void anexar(ObservadorLance observador);

    void desanexar(ObservadorLance observador);

    void notificar(Lance lance);
}
