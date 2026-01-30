package implementacion.sivic.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "detalle_venta")
public class DetalleVenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDetalle;

    private String producto;
    private Integer cantidadProducto;
    private Float precioUnitario;
    private Float subtotalVenta;

    @ManyToOne
    @JoinColumn(name = "id_venta")
    private Venta venta;

    public void calcularSubtotal() {
        this.subtotalVenta = precioUnitario * cantidadProducto;
    }
}
