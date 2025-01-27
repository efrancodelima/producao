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
 * Interface da API Pedido, rota para listar os itens da esteira de produção.
 */
@Tag(name = "Pedido")
public interface ListarItensProducao {

  @Operation(summary = "Listar itens da esteira de produção", description = Constantes.DESCRICAO)

  @GetMapping(value = "/listar/")

  @ApiResponses(value = {
    @ApiResponse(
      responseCode = Constantes.CODE_OK,
      description = Constantes.DESC_OK,
      content = @Content(mediaType = "application/json",
      examples = @ExampleObject(value = Constantes.EXAMPLE_OK)))
  })
  
  ResponseEntity<ResponseWrapper<List<RegistroProducaoJpa>>> listarItensProducao();

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
          data: [
            {
              "numero": 1,
              "cliente": null,
              "itens": [
                {
                  "produto": {
                    "codigo": 1,
                    "nome": "X-Monstrão",
                    "descricao": null,
                    "preco": 20,
                    "categoria": "LANCHE"
                  },
                  "quantidade": 1,
                  "valorItem": 20
                }
              ],
              "dataHoraCheckout": "2024-09-20T10:22:09.175124",
              "statusPagamento": {
                "codigo": 0,
                "status": "AGUARDANDO_PAGAMENTO",
                "dataHora": "2024-09-20T10:22:09.246543"
              },
              "statusPedido": {
                "status": "RECEBIDO",
                "dataHora": "2024-09-20T10:22:09.175173"
              },
              "valorPedido": 20
            },
            {
              "numero": 2,
              "cliente": null,
              "itens": [
                {
                  "produto": {
                    "codigo": 2,
                    "nome": "X-Monstrinho",
                    "descricao": null,
                    "preco": 20,
                    "categoria": "LANCHE"
                  },
                  "quantidade": 2,
                  "valorItem": 40
                }
              ],
              "dataHoraCheckout": "2024-09-20T10:22:15.888514",
              "statusPagamento": {
                "codigo": 0,
                "status": "AGUARDANDO_PAGAMENTO",
                "dataHora": "2024-09-20T10:22:15.899493"
              },
              "statusPedido": {
                "status": "RECEBIDO",
                "dataHora": "2024-09-20T10:22:15.888543"
              },
              "valorPedido": 40
            }
          ],
          errorMsg: null
        }
        """;

  }
}
