package br.com.fiap.soat.controller.contract;

import br.com.fiap.soat.controller.wrapper.ResponseWrapper;
import br.com.fiap.soat.entity.RegistroProducaoJpa;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Interface da API Producao, rota para listar os itens da esteira de produção.
 */
@Tag(name = "Producao")
public interface ListarPedidosProducao {

  @Operation(summary = "Listar pedidos em produção", description = Constantes.DESCRICAO)

  @GetMapping(value = "/listar/")

  @ApiResponses(value = {
    @ApiResponse(
      responseCode = Constantes.CODE_OK,
      description = Constantes.DESC_OK,
      content = @Content(mediaType = "application/json",
      examples = @ExampleObject(value = Constantes.EXAMPLE_OK)))
  })
  
  ResponseEntity<ResponseWrapper<List<RegistroProducaoJpa>>> listarPedidosProducao();

  /** 
   * Constantes utilizadas pela interface.
   */
  final class Constantes {

    private Constantes() {}

    public static final String DESCRICAO = "Lista os itens de produção nessa ordem: "
        + "Pronto > Em Preparação > Recebido."
        + "<br>Itens mais antigos primeiro e mais novos depois."
        + "<br>Itens finalizados não são listados.";
    
    public static final String CODE_OK = "200";
    public static final String DESC_OK = "Ok";
    public static final String EXAMPLE_OK = """
        {
          "data": [
            {
              "numeroPedido": 1,
              "status": "EM_PREPARACAO",
              "timestamp": "2025-01-20T10:22:000000"
            },
            {
              "numeroPedido": 2,
              "status": "RECEBIDO",
              "timestamp": "2025-01-20T10:28:000000"
            }
          ],
          "errorMsg": null
        }
        """;

  }
}
