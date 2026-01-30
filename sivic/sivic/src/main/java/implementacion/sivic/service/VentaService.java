package implementacion.sivic.service;

import implementacion.sivic.model.*;
import implementacion.sivic.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class VentaService {

    private final VentaRepository ventaRepository;
    private final FacturaRepository facturaRepository;
    private final CancelacionVentaRepository cancelacionVentaRepository;

    @Transactional
    public Venta registrarVenta(Venta venta) {

        venta.registrarVenta();

        for (DetalleVenta d : venta.getDetalles()) {
            d.setVenta(venta);
            d.calcularSubtotal();
        }

        venta.calcularTotal();
        return ventaRepository.save(venta);
    }

    @Transactional
    public Factura generarFactura(Integer idVenta) {

        Venta venta = ventaRepository.findById(idVenta)
                .orElseThrow(() -> new RuntimeException("Venta no encontrada"));

        Factura factura = new Factura();
        factura.generarFactura(venta);

        return facturaRepository.save(factura);
    }

    @Transactional
    public void cancelarVenta(Integer idVenta) {

        Venta venta = ventaRepository.findById(idVenta)
                .orElseThrow(() -> new RuntimeException("Venta no encontrada"));

        venta.cancelarVenta();

        CancelacionVenta cancelacion = new CancelacionVenta();
        cancelacion.registrarCancelacion(venta);

        cancelacionVentaRepository.save(cancelacion);
        ventaRepository.save(venta);
    }
}

