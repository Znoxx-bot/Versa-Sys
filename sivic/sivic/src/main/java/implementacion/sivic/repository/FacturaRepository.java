package implementacion.sivic.repository;

import implementacion.sivic.model.Factura;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacturaRepository extends JpaRepository<Factura, Integer> {}
