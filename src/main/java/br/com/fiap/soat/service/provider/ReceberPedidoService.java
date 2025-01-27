package br.com.fiap.soat.service.provider;

import br.com.fiap.soat.entity.RegistroProducaoJpa;
import br.com.fiap.soat.entity.StatusPedido;
import br.com.fiap.soat.exception.BadRequestException;
import br.com.fiap.soat.repository.RegistroProducaoRepository;
import br.com.fiap.soat.validator.NumeroPedidoValidator;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Service para registrar o recebimento do pedido.
 */
@Component
public class ReceberPedidoService {

  private final RegistroProducaoRepository repository;

  @Autowired
  public ReceberPedidoService(RegistroProducaoRepository repository) {
    this.repository = repository;
  }
  
  public RegistroProducaoJpa execute(long numeroPedido) throws BadRequestException {

    NumeroPedidoValidator.validar(numeroPedido);

    var status = new RegistroProducaoJpa(numeroPedido, StatusPedido.RECEBIDO, LocalDateTime.now());
    
    return repository.save(status);
  }
}
