package implementacion.sivic.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "venta")
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idVenta;

    private LocalDate fechaVenta;
    private Float total;

    // true = activa, false = cancelada
    private Boolean estadoVenta;

    @OneToMany(mappedBy = "venta", cascade = CascadeType.ALL)
    private List<DetalleVenta> detalles;

    public void registrarVenta() {
        this.estadoVenta = true;
        this.fechaVenta = LocalDate.now();
    }

    public void cancelarVenta() {
        this.estadoVenta = false;
    }

    public Float calcularTotal() {
        float suma = 0;
        for (DetalleVenta d : detalles) {
            suma += d.getSubtotalVenta();
        }
        this.total = suma;
        return suma;
    }
}
