package br.com.fiap.soat.controller.contract;

import br.com.fiap.soat.controller.wrapper.ResponseWrapper;
import br.com.fiap.soat.dto.ProdutoDto;
import br.com.fiap.soat.entity.ProdutoJpa;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Interface da API Clientes, rota para cadastrar cliente.
 */
@Tag(name = "Produto")
public interface CadastrarProduto {

  /**
   * Endpoint para o cadastro de produtos.
   *
   * @param clienteDto A requisição com os dados do produto a ser cadastrado.
   * @return Um objeto contendo o produto cadastrado, em caso de sucesso, ou 
   *     a mensagem de erro, em caso de falha.
   */
  @Operation(summary = "Cadastrar produto", description = Constantes.DESCRICAO)

  @ApiResponses(value = {
    @ApiResponse(
      responseCode = Constantes.CODE_CREATED,
      description = Constantes.DESC_CREATED, 
      content = @Content(mediaType = "application/json",
      examples = @ExampleObject(value = Constantes.EXAMPLE_CREATED))),

    @ApiResponse(
      responseCode = Constantes.CODE_BAD_REQUEST,
      description = Constantes.DESC_BAD_REQUEST,
      content = @Content(mediaType = "application/json",
      examples = @ExampleObject(value = Constantes.EXAMPLE_BAD_REQUEST)))
  })

  @PostMapping(value = "/novo")
        
  ResponseWrapper<ProdutoJpa> cadastrarProduto(@RequestBody ProdutoDto clienteDto);

  /** 
   * Constantes utilizadas pela interface CadastrarClienteApi.
   */
  final class Constantes {

    private Constantes() {}

    public static final String DESCRICAO = "Para cadastrar um produto, "
        + "informe os dados do produto conforme o schema ProdutoDto no final desta página.";
    
    public static final String CODE_CREATED = "201";
    public static final String DESC_CREATED = "Created";
    public static final String EXAMPLE_CREATED = """
        {
            "codigo": 1,
            "nome": "Sabor Sertanejo",
            "descricao": "Inspirado na tradição culinária do sertão nordestino",
            "preco": 34.99,
            "categoria": "LANCHE"
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
