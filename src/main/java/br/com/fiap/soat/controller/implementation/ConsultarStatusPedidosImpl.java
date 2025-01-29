package br.com.fiap.soat.controller.implementation;

import br.com.fiap.soat.controller.contract.ConsultarStatusPedido;
import br.com.fiap.soat.controller.wrapper.ResponseWrapper;
import br.com.fiap.soat.entity.RegistroProducaoJpa;
import br.com.fiap.soat.exception.BadRequestException;
import br.com.fiap.soat.service.provider.ConsultarStatusPedidosService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador REST para consultar o status de um ou mais pedidos.
 */
@RestController
@RequestMapping("/producao")
public class ConsultarStatusPedidosImpl implements ConsultarStatusPedido {

  private final ConsultarStatusPedidosService service;

  @Autowired
  public ConsultarStatusPedidosImpl(ConsultarStatusPedidosService service) {
    this.service = service;
  }

  @Override
  public ResponseEntity<ResponseWrapper<List<RegistroProducaoJpa>>>
      consultarStatus(List<Long> numerosPedidos) {
    
    try {
      var response = service.execute(numerosPedidos);
      return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseWrapper<>(response));

    } catch (BadRequestException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
          .body(new ResponseWrapper<>(e.getMessage()));
    }
  }
}

