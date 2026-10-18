package controladores;

import modelos.DatosEnvio;
import modelos.Envio;
import servicios.ServicioEnvios;
import vistas.VistaEnvios;

public class EnvioControlador {
    private final VistaEnvios vista;
    private final ServicioEnvios servicio;

    public EnvioControlador(VistaEnvios vista, ServicioEnvios servicio) {
        this.vista = vista;
        this.servicio = servicio;

        vista.setTiposEnvio(servicio.getTipos());
        vista.setGuardarEnvioClick(evento -> guardarEnvio());
        vista.setEliminarEnvioClick(evento -> eliminarEnvio());
        mostrarEnvios();
    }

    private void mostrarEnvios() {
        vista.mostrarEnvios(servicio.listar());
    }

    private void guardarEnvio() {
        String codigo = vista.getCodigo().trim();
        String cliente = vista.getCliente().trim();
        String tipo = vista.getTipoEnvioSeleccionado();

        if (codigo.isEmpty() || cliente.isEmpty()) {
            vista.mostrarMensaje("Debe ingresar el código y el cliente");
            return;
        }
        if (servicio.buscar(codigo) != null) {
            vista.mostrarMensaje("Ya existe un envío con ese código");
            return;
        }

        try {
            double peso = Double.parseDouble(vista.getPeso().trim());
            double distancia = Double.parseDouble(vista.getDistancia().trim());
            DatosEnvio datos = new DatosEnvio(codigo, cliente, peso, distancia);
            Envio envio = servicio.agregar(tipo, datos);

            if (envio != null) {
                mostrarEnvios();
                vista.limpiarCampos();
                vista.ocultarEdicionEnvio();
            } else {
                vista.mostrarMensaje("No se pudo guardar. Revise el peso y la distancia");
            }
        } catch (NumberFormatException error) {
            vista.mostrarMensaje("El peso y la distancia deben ser números");
        }
    }

    private void eliminarEnvio() {
        String codigo = vista.pedirCodigo();
        if (codigo == null) {
            return;
        }
        if (codigo.isBlank()) {
            vista.mostrarMensaje("Debe ingresar el código del envío");
            return;
        }
        if (servicio.eliminar(codigo)) {
            mostrarEnvios();
            vista.mostrarMensaje("Envío retirado correctamente");
        } else {
            vista.mostrarMensaje("No existe un envío con ese código");
        }
    }
}
