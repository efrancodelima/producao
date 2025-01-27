package br.com.fiap.soat.repository;

import br.com.fiap.soat.entity.StatusPedidoJpa;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface do repositório de pedidos.
 */
@Repository
public interface StatusPedidoRepository extends JpaRepository<StatusPedidoJpa, Long> {

  Optional<StatusPedidoJpa> findTopByNumeroPedidoOrderByTimestampDesc(Long numeroPedido);
}
