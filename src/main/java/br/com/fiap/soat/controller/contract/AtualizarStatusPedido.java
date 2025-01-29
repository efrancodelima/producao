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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Interface da API Producao, rota para atualizar o status do pedido.
 */
@Tag(name = "Producao")
public interface AtualizarStatusPedido {

  @Operation(summary = "Atualizar o status do pedido", description = Constantes.DESCRICAO)

  @PostMapping(value = "/atualizar/{pedido}")

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
      examples = @ExampleObject(value = Constantes.EXAMPLE_BAD_REQUEST))),
    
    @ApiResponse(
      responseCode = Constantes.CODE_NOT_FOUND,
      description = Constantes.DESC_NOT_FOUND,
      content = @Content(mediaType = "application/json",
      examples = @ExampleObject(value = Constantes.EXAMPLE_NOT_FOUND))),
    
    @ApiResponse(
      responseCode = Constantes.CODE_UN_ENTITY,
      description = Constantes.DESC_UN_ENTITY,
      content = @Content(mediaType = "application/json",
      examples = @ExampleObject(value = Constantes.EXAMPLE_UN_ENTITY))),
  
    @ApiResponse(
      responseCode = Constantes.CODE_BAD_GATEWAY,
      description = Constantes.DESC_BAD_GATEWAY,
      content = @Content(mediaType = "application/json",
      examples = @ExampleObject(value = Constantes.EXAMPLE_BAD_GATEWAY))),

  })
  
  @Parameter(name = "pedido", description = "O número do pedido", required = true)

  ResponseEntity<ResponseWrapper<RegistroProducaoJpa>>
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
            "status": "PRONTO",
            "timestamp": "2025-01-20T10:22:00.000000"
          },
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

    public static final String CODE_NOT_FOUND = "404";
    public static final String DESC_NOT_FOUND = "Not Found";
    public static final String EXAMPLE_NOT_FOUND = """
        {
          "data": null,
          "errorMsg": "Nenhum pedido foi encontrado para o número informado."
        }
        """;

    public static final String CODE_UN_ENTITY = "422";
    public static final String DESC_UN_ENTITY = "Unprocessable Entity";
    public static final String EXAMPLE_UN_ENTITY = """
        {
          "data": null,
          "errorMsg": "O pedido já foi finalizado."
        }
        """;

    public static final String CODE_BAD_GATEWAY = "502";
    public static final String DESC_BAD_GATEWAY = "Bad Gateway";
    public static final String EXAMPLE_BAD_GATEWAY = """
        {
          "data": null,
          "errorMsg": "Erro na comunicação com o sistema de pagamento."
        }
        """;

  }
}
