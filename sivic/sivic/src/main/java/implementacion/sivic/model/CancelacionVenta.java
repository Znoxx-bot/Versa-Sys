package implementacion.sivic.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "cancelacion_venta")
public class CancelacionVenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCancelacionVenta;

    private LocalDate fechaCancelacion;

    @OneToOne
    @JoinColumn(name = "id_venta")
    private Venta venta;

    public void registrarCancelacion(Venta venta) {
        this.venta = venta;
        this.fechaCancelacion = LocalDate.now();
    }
}
