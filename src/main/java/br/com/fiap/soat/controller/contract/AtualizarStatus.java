package br.com.fiap.soat.controller.contract;

import br.com.fiap.soat.controller.wrapper.ResponseWrapper;
import br.com.fiap.soat.entity.StatusPedidoJpa;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Interface da API Pedido, rota para atualizar o status do pedido.
 */
@Tag(name = "Pedido")
public interface AtualizarStatus {

  /**
   * Atualiza o status do pedido.
   *
   * @param numeroPedido O número do pedido a ser atualizado.
   * @return Um objeto contendo o status do pedido atualizado, em caso de sucesso,
   *     ou a mensagem de erro, em caso de falha.
   */
  @Operation(summary = "Atualizar o status do pedido", description = Constantes.DESCRICAO)

  @PostMapping(value = "/atualizar/{pedido}")

  @ApiResponses(value = {
    @ApiResponse(
      responseCode = Constantes.CODE_OK,
      description = Constantes.DESC_OK,
      content = @Content(mediaType = "application/json",
      examples = @ExampleObject(value = Constantes.EXAMPLE_OK)))
  })
  
  @Parameter(name = "pedido", description = "O número do pedido", required = true)

  ResponseEntity<ResponseWrapper<StatusPedidoJpa>>
      atualizarPedido(@PathVariable("pedido") long numeroPedido);

  /** 
   * Constantes utilizadas pela interface.
   */
  final class Constantes {

    private Constantes() {}

    public static final String DESCRICAO = "Para atualizar o status de um pedido, "
        + "informe o número do pedido.<br>Os status possuem uma ordem sequencial, então "
        + "ele mudará automaticamente para o valor seguinte.";
    
    public static final String CODE_OK = "200";
    public static final String DESC_OK = "Ok";
    public static final String EXAMPLE_OK = """
        {
          "data": {
            "numeroPedido": 1,
            "status": "RECEBIDO",
            "timestamp": "2024-09-20T10:22:09.175173"
          },
          "errorMsg": null
        }
        """;

  }
}
