package br.com.fiap.soat.controller.contract;

import br.com.fiap.soat.controller.wrapper.ResponseWrapper;
import br.com.fiap.soat.entity.RegistroProducaoJpa;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Interface da API Pedido, rota para receber pedido.
 */
@Tag(name = "Pedido")
public interface ReceberPedido {

  @Hidden
  @PostMapping(value = "/receber/{pedido}")
  @Parameter(name = "pedido", description = "O número do pedido", required = true)

  ResponseEntity<ResponseWrapper<RegistroProducaoJpa>>
      receberPedido(@PathVariable("pedido") long numeroPedido);

}
