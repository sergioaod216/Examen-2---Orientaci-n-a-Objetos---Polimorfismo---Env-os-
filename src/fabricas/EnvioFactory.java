package fabricas;

import modelos.DatosEnvio;
import modelos.Envio;

// Todas las fabricas tienen este mismo metodo de creacion.
public abstract class EnvioFactory {
    public abstract Envio crearEnvio(DatosEnvio datos);
}
