package br.com.fiap.soat.service.provider;

import br.com.fiap.soat.entity.StatusPedidoEnum;
import br.com.fiap.soat.entity.StatusPedidoJpa;
import br.com.fiap.soat.exception.BadGatewayException;
import br.com.fiap.soat.exception.BadRequestException;
import br.com.fiap.soat.exception.BusinessRuleException;
import br.com.fiap.soat.exception.NotFoundException;
import br.com.fiap.soat.exception.messages.BadRequestMessage;
import br.com.fiap.soat.exception.messages.BusinessRuleMessage;
import br.com.fiap.soat.exception.messages.NotFoundMessage;
import br.com.fiap.soat.repository.StatusPedidoRepository;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Service para atualizar o status do pedido.
 */
@Component
public class AtualizarStatusService {

  private final StatusPedidoRepository repository;

  @Autowired
  public AtualizarStatusService(StatusPedidoRepository repository) {
    this.repository = repository;
  }
  
  /**
   * Atualiza o status do pedido.
   *
   * @param numeroPedido O número do pedido cujo status será atualizado.
   * @return O status do pedido.
   * @throws BadRequestException Exceção do tipo bad request lançada pelo método.
   * @throws BadGatewayException Exceção do tipo bad gateway lançada pelo método.
   * @throws NotFoundException Exceção do tipo not found lançada pelo método.
   * @throws BusinessRuleException Exceção de regra de negócio lançada pelo método.
   */
  public StatusPedidoJpa execute(long numeroPedido)
         throws BadRequestException, NotFoundException, BusinessRuleException {

    // Valida o número] do pedido
    if (numeroPedido < 1) {
      throw new BadRequestException(BadRequestMessage.NUM_PED_MIN);
    }

    // Verifica se o pedido existe
    var pedidoOpt = repository.findTopByNumeroPedidoOrderByTimestampDesc(numeroPedido);
    if (!pedidoOpt.isPresent()) {
      throw new NotFoundException(NotFoundMessage.PEDIDO);
    }

    // Atualiza o status do pedido
    var nextStatus = nextStatus(pedidoOpt.get().getStatus());

    var statusJpa = new StatusPedidoJpa();
    statusJpa.setNumeroPedido(numeroPedido);
    statusJpa.setStatus(nextStatus);
    statusJpa.setTimestamp(LocalDateTime.now());

    return repository.save(statusJpa);
  }

  private StatusPedidoEnum nextStatus(StatusPedidoEnum status)
      throws BusinessRuleException {

    // verifica pagamento ??

    var listaStatus = StatusPedidoEnum.values();

    int indiceAtual = status.ordinal();
    int ultimoIndice = listaStatus.length - 1;

    if (indiceAtual == ultimoIndice) {
      throw new BusinessRuleException(BusinessRuleMessage.PED_FINALIZADO);
    }

    return listaStatus[indiceAtual + 1];
  }
}
