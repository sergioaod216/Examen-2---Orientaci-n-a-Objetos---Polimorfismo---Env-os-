package modelos;

public abstract class Envio {
    private final String codigo;
    private String cliente;
    private double peso;
    private double distancia;

    public Envio(String codigo, String cliente, double peso, double distancia) {
        if (codigo == null || codigo.isBlank()) {
            throw new IllegalArgumentException("El código es obligatorio");
        }
        this.codigo = codigo.trim();
        setCliente(cliente);
        setPeso(peso);
        setDistancia(distancia);
    }

    public String getCodigo() {
        return codigo;
    }

    public String getCliente() {
        return cliente;
    }

    public double getPeso() {
        return peso;
    }

    public double getDistancia() {
        return distancia;
    }

    public void setCliente(String cliente) {
        if (cliente == null || cliente.isBlank()) {
            throw new IllegalArgumentException("El cliente es obligatorio");
        }
        this.cliente = cliente.trim();
    }

    public void setPeso(double peso) {
        if (!Double.isFinite(peso) || peso <= 0) {
            throw new IllegalArgumentException("El peso debe ser mayor que cero");
        }
        this.peso = peso;
    }

    public void setDistancia(double distancia) {
        if (!Double.isFinite(distancia) || distancia <= 0) {
            throw new IllegalArgumentException("La distancia debe ser mayor que cero");
        }
        this.distancia = distancia;
    }

    public abstract String getTipo();

    public abstract double calcularTarifa();
}
