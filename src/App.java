import java.util.LinkedHashMap;
import java.util.Map;
import javax.swing.SwingUtilities;

import controladores.EnvioControlador;
import fabricas.AereoFactory;
import fabricas.EnvioFactory;
import fabricas.MaritimoFactory;
import fabricas.TerrestreFactory;
import servicios.EnvioServicio;
import servicios.ServicioEnvios;
import vistas.EnvioVista;
import vistas.VistaEnvios;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Cada tipo de envio tiene su propia fabrica.
            Map<String, EnvioFactory> fabricas = new LinkedHashMap<>();
            fabricas.put("Terrestre", new TerrestreFactory());
            fabricas.put("Aéreo", new AereoFactory());
            fabricas.put("Marítimo", new MaritimoFactory());

            ServicioEnvios servicio = new EnvioServicio(fabricas);
            VistaEnvios vista = new EnvioVista();
            new EnvioControlador(vista, servicio);
            vista.mostrarVentana();
        });
    }
}
