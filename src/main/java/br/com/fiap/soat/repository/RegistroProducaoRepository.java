package br.com.fiap.soat.repository;

import br.com.fiap.soat.entity.RegistroProducaoJpa;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Interface do repositório de pedidos.
 */
@Repository
public interface RegistroProducaoRepository extends JpaRepository<RegistroProducaoJpa, Long> {

  Optional<RegistroProducaoJpa> findTopByNumeroPedidoOrderByTimestampDesc(Long numeroPedido);

  @Query("SELECT r FROM RegistroProducaoJpa r WHERE r.timestamp = "
      + "(SELECT MAX(r2.timestamp) FROM RegistroProducaoJpa r2 "
      + "WHERE r2.numeroPedido = r.numeroPedido)")
  List<RegistroProducaoJpa> findAllWithLatestTimestamp();
}
