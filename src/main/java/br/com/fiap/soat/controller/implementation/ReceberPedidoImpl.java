package br.com.fiap.soat.controller.implementation;

import br.com.fiap.soat.controller.contract.ReceberPedido;
import br.com.fiap.soat.controller.wrapper.ResponseWrapper;
import br.com.fiap.soat.entity.RegistroProducaoJpa;
import br.com.fiap.soat.exception.BadRequestException;
import br.com.fiap.soat.service.provider.ReceberPedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador REST para receber pedido.
 * Esse controler é um webhook usado para receber
 * as notificações de checkout do sistema de pedidos.
 */
@RestController
@RequestMapping("/pedido")
public class ReceberPedidoImpl implements ReceberPedido {

  private final ReceberPedidoService service;

  @Autowired
  public ReceberPedidoImpl(ReceberPedidoService service) {
    this.service = service;
  }

  @Override
  public ResponseEntity<ResponseWrapper<RegistroProducaoJpa>> receberPedido(long numeroPedido) {

    try {
      var statusPedido = service.execute(numeroPedido);
      return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseWrapper<>(statusPedido));

    } catch (BadRequestException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
          .body(new ResponseWrapper<>(e.getMessage()));
    }
  }
}

