package br.com.fiap.soat.service.provider;

import br.com.fiap.soat.entity.StatusPedidoEnum;
import br.com.fiap.soat.entity.StatusPedidoJpa;
import br.com.fiap.soat.exception.BadGatewayException;
import br.com.fiap.soat.exception.BadRequestException;
import br.com.fiap.soat.exception.messages.BadRequestMessage;
import br.com.fiap.soat.repository.StatusPedidoRepository;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Service para receber o pedido.
 */
@Component
public class ReceberPedidoService {

  private final StatusPedidoRepository repository;

  @Autowired
  public ReceberPedidoService(StatusPedidoRepository repository) {
    this.repository = repository;
  }
  
  /**
   * Recebe o pedido.
   *
   * @param numeroPedido O número do pedido.
   * @return O status do pedido.
   * @throws BadRequestException Exceção do tipo bad request lançada pelo método.
   * @throws BadGatewayException Exceção do tipo bad gateway lançada pelo método.
   * @throws NotFoundException Exceção do tipo not found lançada pelo método.
   */
  public StatusPedidoJpa execute(long numeroPedido) throws BadRequestException {

    if (numeroPedido < 1) {
      throw new BadRequestException(BadRequestMessage.NUM_PED_MIN);
    }

    var status = new StatusPedidoJpa();
    status.setNumeroPedido(numeroPedido);
    status.setStatus(StatusPedidoEnum.RECEBIDO);
    status.setTimestamp(LocalDateTime.now());

    return repository.save(status);
  }
}
