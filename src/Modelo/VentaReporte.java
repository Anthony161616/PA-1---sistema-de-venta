
package Modelo;

public class VentaReporte {
    private int id;
    private String nombre;
    private String vendedor;
    private double total;

    public VentaReporte() {
    }

    
    public VentaReporte(int id, String nombre, String vendedor, double total) {
        this.id = id;
        this.nombre = nombre;
        this.vendedor = vendedor;
        this.total = total;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getVendedor() {
        return vendedor;
    }

    public void setVendedor(String vendedor) {
        this.vendedor = vendedor;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
    
    
}
