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
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Interface da API Pedido, rota para consultar o status de um ou mais pedidos.
 */
@Tag(name = "Pedido")
public interface ConsultarStatus {

  /**
   * Consultar o status de um ou mais pedidos.
   *
   * @param numerosPedidos Uma lista com os números dos pedidos a serem consultados.
   * @return Um objeto contendo a lista com os status dos pedidos, em caso de sucesso,
   *     ou a mensagem de erro, em caso de falha.
   */
  @Operation(summary = "Consultar o status de um ou mais pedidos", description = Constantes.DESCRICAO)

  @ApiResponses(value = {
    @ApiResponse(
      responseCode = Constantes.CODE_OK,
      description = Constantes.DESC_OK,
      content = @Content(mediaType = "application/json",
      examples = @ExampleObject(value = Constantes.EXAMPLE_OK)))
  })
  
  @Parameter(name = "pedidos", description = "Uma lista com os números dos pedidos",
      required = true, example = "1, 2, 3")
  
  @GetMapping(value = "/consultar/{pedidos}")

  ResponseEntity<ResponseWrapper<List<StatusPedidoJpa>>>
      consultarStatus(@PathVariable("pedidos") List<Long> numerosPedidos);

  /** 
   * Constantes utilizadas pela interface.
   */
  final class Constantes {

    private Constantes() {}

    public static final String DESCRICAO = "Para consultar o status de um ou mais pedidos, "
        + "informe os números dos pedidos.";
    
    public static final String CODE_OK = "200";
    public static final String DESC_OK = "Ok";
    public static final String EXAMPLE_OK = """
        {
          "data": [
            {
              "numeroPedido": 1,
              "status": "EM_PREPARACAO",
              "timestamp": "2025-01-20T10:20:00.000000"
            },
            {
              "numeroPedido": 2,
              "status": "RECEBIDO",
              "timestamp": "2025-01-20T10:25:00.000000"
            },
          ],
          "errorMsg": null
        }
        """;

  }
}
