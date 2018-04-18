package examen3a;

public abstract class Herramienta implements IAlquilable {

    private double precioMes, peso;

    public Herramienta(double precioMes, double peso) {
        this.precioMes = precioMes;
        this.peso = peso;
    }

    public double getPrecioMes() {
        return precioMes;
    }

    public double getPeso() {
        return peso;
    }
}
