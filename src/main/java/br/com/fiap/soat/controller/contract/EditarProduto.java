package br.com.fiap.soat.controller.contract;

import br.com.fiap.soat.dto.ProdutoDto;
import br.com.fiap.soat.entity.ProdutoJpa;
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
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Interface da API Clientes, rota para cadastrar cliente.
 */
@Tag(name = "Produto")
public interface EditarProduto {

  /** 
   * Editar produto.
   *
   * @param codigo O código do produto que será editado.
   * @param produto O produto com as alterações feitas.
   * @return O produto editado.
   */
  @Operation(summary = "Editar produto", description = Constantes.DESCRICAO)
  
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
  
  @Parameter(name = "codigo", description = "O código do produto a ser editado", required = true)

  @PutMapping("/editar/{codigo}")

  ResponseEntity<ProdutoJpa>
      editarProduto(@PathVariable long codigo, @RequestBody ProdutoDto produto);


  /** 
   * Constantes utilizadas pela interface CadastrarClienteApi.
   */
  final class Constantes {

    private Constantes() {}

    public static final String DESCRICAO = "Para editar um produto, informe o código do produto e "
        + "os dados do produto conforme o schema ProdutoDto no final desta página.";
    
    public static final String CODE_OK = "200";
    public static final String DESC_OK = "Ok";
    public static final String EXAMPLE_OK = """
        {
            "codigo": 11,
            "nome": "Saquê Baiano",
            "descricao": "Uma bebida japonesa repensada pela culinária baiana",
            "preco": 19.90,
            "categoria": "BEBIDA"
        }
        """;
    
    public static final String CODE_BAD_REQUEST = "400";
    public static final String DESC_BAD_REQUEST = "Bad Request";
    public static final String EXAMPLE_BAD_REQUEST = """
        {
          "data": null,
          "errorMsg": "O valor do produto deve ser maior que zero."
        }
        """;
  }
}
