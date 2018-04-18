package examen3a;

public abstract class Decorativo implements IAlquilable {

    private double precioMes;
    private double descuento;

    public Decorativo(double precioMes, double descuento) {
        this.precioMes = precioMes;
        this.descuento = descuento;
    }

    public double getPrecioMes() {
        return precioMes;
    }

    public double getDescuento() {
        return descuento;
    }
}
