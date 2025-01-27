package br.com.fiap.soat.controller.contract;

import br.com.fiap.soat.controller.wrapper.ResponseWrapper;
import br.com.fiap.soat.entity.StatusPedidoJpa;
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

  /**
   * Receber pedido.
   *
   * @param numeroPedido A requisição com o número do pedido a ser recebido.
   * @return Um objeto contendo o status do pedido recebido, em caso de sucesso,
   *     ou a mensagem de erro, em caso de falha.
   */
  // @Hidden
  @PostMapping(value = "/receber/{pedido}")
  @Parameter(name = "pedido", description = "O número do pedido", required = true)

  ResponseEntity<ResponseWrapper<StatusPedidoJpa>>
      receberPedido(@PathVariable("pedido") long numeroPedido);

}
