package br.com.fiap.soat.controller.contract;

import br.com.fiap.soat.controller.wrapper.ResponseWrapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

/**
 * Interface da API Produto, rota para remover produto.
 */
@Tag(name = "Produto")
public interface RemoverProduto {

  /** 
   * Remover produto.
   *
   * @param codigo O código do produto a ser removido.
   * @return Um objeto com campos nulos, em caso de sucesso,
   *     ou contendo a mensagem de erro, em caso de falha.
   */
  @Operation(summary = "Remover produto", description = Constantes.DESCRICAO)
  
  @ApiResponses(value = {
    @ApiResponse(
      responseCode = Constantes.CODE_NO_CONTENT,
      description = Constantes.DESC_NO_CONTENT),
  
    @ApiResponse(
      responseCode = Constantes.CODE_BAD_REQUEST,
      description = Constantes.DESC_BAD_REQUEST,
      content = @Content(mediaType = "application/json",
      examples = @ExampleObject(value = Constantes.EXAMPLE_BAD_REQUEST))),
  
      @ApiResponse(
        responseCode = Constantes.CODE_UN_ENTITY,
        description = Constantes.DESC_UN_ENTITY,
        content = @Content(mediaType = "application/json",
        examples = @ExampleObject(value = Constantes.EXAMPLE_UN_ENTITY)))
  })
  
  @Parameter(name = "codigo", description = "O código do produto a ser removido", required = true)

  @PutMapping("/remover/{codigo}")

  ResponseEntity<ResponseWrapper<Void>> removerrProduto(@PathVariable long codigo);


  /** 
   * Constantes utilizadas pela interface CadastrarClienteApi.
   */
  final class Constantes {

    private Constantes() {}

    public static final String DESCRICAO = "Para remover um produto, informe o código dele.";
    
    public static final String CODE_NO_CONTENT = "204";
    public static final String DESC_NO_CONTENT = "No Content";
    public static final String EXAMPLE_OK = "";
    
    public static final String CODE_BAD_REQUEST = "400";
    public static final String DESC_BAD_REQUEST = "Bad Request";
    public static final String EXAMPLE_BAD_REQUEST = """
        {
          "data": null,
          "errorMsg": "O código do produto deve ser maior que zero."
        }
        """;
    
    public static final String CODE_UN_ENTITY = "422";
    public static final String DESC_UN_ENTITY = "Unprocessable Entity";
    public static final String EXAMPLE_UN_ENTITY = """
        {
          "data": null,
          "errorMsg": "Não foi encontrado nenhum produto para o código informado."
        }
        """;
  }
}
