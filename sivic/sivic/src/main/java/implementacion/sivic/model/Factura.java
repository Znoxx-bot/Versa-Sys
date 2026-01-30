package implementacion.sivic.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "factura")
public class Factura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idFactura;

    private LocalDate fechaEmision;
    private Float total;

    @OneToOne
    @JoinColumn(name = "id_venta")
    private Venta venta;

    public void generarFactura(Venta venta) {
        this.venta = venta;
        this.fechaEmision = LocalDate.now();
        this.total = venta.getTotal();
    }
}
