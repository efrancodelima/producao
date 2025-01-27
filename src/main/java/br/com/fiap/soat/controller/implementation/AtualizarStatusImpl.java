package br.com.fiap.soat.controller.implementation;

import br.com.fiap.soat.controller.contract.AtualizarStatus;
import br.com.fiap.soat.controller.wrapper.ResponseWrapper;
import br.com.fiap.soat.entity.StatusPedidoJpa;
import br.com.fiap.soat.exception.BadRequestException;
import br.com.fiap.soat.exception.BusinessRuleException;
import br.com.fiap.soat.exception.NotFoundException;
import br.com.fiap.soat.service.provider.AtualizarStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador REST para atualizar o status do pedido.
 */
@RestController
@RequestMapping("/pedido")
public class AtualizarStatusImpl implements AtualizarStatus {

  private final AtualizarStatusService service;

  /**
   * O construtor público da classe.
   *
   * @param service O service para processar a requisição.
   */
  @Autowired
  public AtualizarStatusImpl(AtualizarStatusService service) {
    this.service = service;
  }

  @Override
  public ResponseEntity<ResponseWrapper<StatusPedidoJpa>> atualizarStatus(long numeroPedido) {
    try {
      var status = service.execute(numeroPedido);
      return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseWrapper<>(status));

    } catch (BadRequestException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseWrapper<>(e.getMessage()));
    
    } catch (BusinessRuleException e) {
      return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(new ResponseWrapper<>(e.getMessage()));
    
    } catch (NotFoundException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseWrapper<>(e.getMessage()));
    }
  }
}

