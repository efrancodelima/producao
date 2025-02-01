package br.com.fiap.soat.controller.implementation;

import br.com.fiap.soat.controller.contract.AtualizarStatusPedido;
import br.com.fiap.soat.controller.wrapper.ResponseWrapper;
import br.com.fiap.soat.entity.RegistroProducaoJpa;
import br.com.fiap.soat.exception.BadGatewayException;
import br.com.fiap.soat.exception.BadRequestException;
import br.com.fiap.soat.exception.BusinessRuleException;
import br.com.fiap.soat.exception.NotFoundException;
import br.com.fiap.soat.service.provider.AtualizarStatusPedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador REST para atualizar o status do pedido.
 */
@RestController
@RequestMapping("/producao")
public class AtualizarStatusPedidoImpl implements AtualizarStatusPedido {

  private final AtualizarStatusPedidoService service;

  @Autowired
  public AtualizarStatusPedidoImpl(AtualizarStatusPedidoService service) {
    this.service = service;
  }

  @Override
  public ResponseEntity<ResponseWrapper<RegistroProducaoJpa>> atualizarPedido(long numeroPedido) {
    try {
      var status = service.execute(numeroPedido);
      return ResponseEntity.status(HttpStatus.OK).body(new ResponseWrapper<>(status));

    } catch (BadRequestException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
          .body(new ResponseWrapper<>(e.getMessage()));
    
    } catch (NotFoundException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND)
          .body(new ResponseWrapper<>(e.getMessage()));
    
    } catch (BusinessRuleException e) {
      return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
          .body(new ResponseWrapper<>(e.getMessage()));
    
    } catch (BadGatewayException e) {
      return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
          .body(new ResponseWrapper<>(e.getMessage()));
    }
  }
}

