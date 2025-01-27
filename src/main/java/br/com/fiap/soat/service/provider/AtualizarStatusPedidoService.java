package br.com.fiap.soat.service.provider;

import br.com.fiap.soat.entity.RegistroProducaoJpa;
import br.com.fiap.soat.entity.StatusPedido;
import br.com.fiap.soat.exception.BadRequestException;
import br.com.fiap.soat.exception.BusinessRuleException;
import br.com.fiap.soat.exception.NotFoundException;
import br.com.fiap.soat.repository.RegistroProducaoRepository;
import br.com.fiap.soat.validator.NumeroPedidoValidator;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Service para atualizar o status do pedido.
 */
@Component
public class AtualizarStatusPedidoService {

  private final RegistroProducaoRepository repository;

  @Autowired
  public AtualizarStatusPedidoService(RegistroProducaoRepository repository) {
    this.repository = repository;
  }
  
  public RegistroProducaoJpa execute(long numeroPedido)
         throws BadRequestException, NotFoundException, BusinessRuleException {

    NumeroPedidoValidator.validar(numeroPedido);
    
    var pedido = buscarPedido(numeroPedido);
    var novoStatus = nextStatus(pedido.getStatus());
    var statusJpa = new RegistroProducaoJpa(numeroPedido, novoStatus, LocalDateTime.now());
    
    return repository.save(statusJpa);
  }

  private RegistroProducaoJpa buscarPedido(long numeroPedido) throws NotFoundException {

    var pedidoOpt = repository.findTopByNumeroPedidoOrderByTimestampDesc(numeroPedido);

    if (!pedidoOpt.isPresent()) {
      throw new NotFoundException();
    }
    return pedidoOpt.get();
  }

  private StatusPedido nextStatus(StatusPedido status)
      throws BusinessRuleException {

    // verifica pagamento ??

    var listaStatus = StatusPedido.values();

    int indiceAtual = status.ordinal();
    int ultimoIndice = listaStatus.length - 1;

    if (indiceAtual == ultimoIndice) {
      throw new BusinessRuleException();
    }

    return listaStatus[indiceAtual + 1];
  }
}
