package br.com.fiap.soat.controller.contract;

import br.com.fiap.soat.controller.wrapper.ResponseWrapper;
import br.com.fiap.soat.entity.RegistroProducaoJpa;
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

@Tag(name = "Producao")
public interface ConsultarStatusPedido {

  @Operation(
      summary = "Consultar o status de um ou mais pedidos",
      description = Constantes.DESCRICAO
  )

  @ApiResponses(value = {
    @ApiResponse(
      responseCode = Constantes.CODE_OK,
      description = Constantes.DESC_OK,
      content = @Content(mediaType = "application/json",
      examples = @ExampleObject(value = Constantes.EXAMPLE_OK))),
    
    @ApiResponse(
      responseCode = Constantes.CODE_BAD_REQUEST,
      description = Constantes.DESC_BAD_REQUEST,
      content = @Content(mediaType = "application/json",
      examples = @ExampleObject(value = Constantes.EXAMPLE_BAD_REQUEST)))
  })
  
  @Parameter(name = "pedidos", description = "Uma lista com os números dos pedidos",
      required = true, example = "1, 2, 3")
  
  @GetMapping(value = "/consultar/{pedidos}")

  ResponseEntity<ResponseWrapper<List<RegistroProducaoJpa>>>
      consultarStatus(@PathVariable("pedidos") List<Long> numerosPedidos);

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
            }
          ],
          "errorMsg": null
        }
        """;

    public static final String CODE_BAD_REQUEST = "400";
    public static final String DESC_BAD_REQUEST = "Bad Request";
    public static final String EXAMPLE_BAD_REQUEST = """
        {
          "data": null,
          "errorMsg": "O número do pedido deve ser igual ou maior que 1."
        }
        """;

  }
}
