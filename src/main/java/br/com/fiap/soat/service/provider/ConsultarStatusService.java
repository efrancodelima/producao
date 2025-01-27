package br.com.fiap.soat.service.provider;

import br.com.fiap.soat.entity.StatusPedidoJpa;
import br.com.fiap.soat.exception.BadGatewayException;
import br.com.fiap.soat.exception.BadRequestException;
import br.com.fiap.soat.repository.StatusPedidoRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Service para receber o pedido.
 */
@Component
public class ConsultarStatusService {

  private final StatusPedidoRepository repository;

  @Autowired
  public ConsultarStatusService(StatusPedidoRepository repository) {
    this.repository = repository;
  }
  
  /**
   * Consulta o status de um ou mais pedidos.
   *
   * @param numerosPedidos Uma lista com os números dos pedidos a serem consultados.
   * @return O status do pedido.
   * @throws BadRequestException Exceção do tipo bad request lançada pelo método.
   * @throws BadGatewayException Exceção do tipo bad gateway lançada pelo método.
   * @throws NotFoundException Exceção do tipo not found lançada pelo método.
   */
  public List<StatusPedidoJpa> execute(List<Long> numerosPedidos) throws BadRequestException {

    var response = new ArrayList<StatusPedidoJpa>();

    for (var numero : numerosPedidos) {

      var statusOpt = repository.findTopByNumeroPedidoOrderByTimestampDesc(numero);

      if (statusOpt.isPresent()) {
        response.add(statusOpt.get());
      } else {
        response.add(null);
      }
    }

    return response;
  }
}
