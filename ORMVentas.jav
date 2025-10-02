import jakarta.persistence.*;

@Entity
@Table(name = "ventas")
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String dni_cli;
    private String nombre_cli;
    private String vendedor;
    private double total;
    private String fecha;

    // Getters y Setters
}
