package br.com.fiap.soat.bdd;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

import br.com.fiap.soat.entity.RegistroProducaoJpa;
import br.com.fiap.soat.entity.StatusPedido;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import io.restassured.response.Response;
import java.time.LocalDateTime;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

public class AtualizarStatusPedidoTest {

  private RegistroProducaoJpa pedido;
  private Response resposta;
  private final String url = "http://localhost:8082/producao/atualizar/{pedido}";

  @Dado("que existe um pedido em preparação na esteira de produção")
  public void criarRequisicao() {
    
    var timestamp = LocalDateTime.of(2025, 2, 8, 12, 0, 0);
    pedido = new RegistroProducaoJpa(3L, StatusPedido.EM_PREPARACAO, timestamp);
  }

  @Quando("o sistema receber uma solicitação para atualizar o status do pedido")
  public void enviarRequisicao() {
    resposta = given()
      .contentType(MediaType.APPLICATION_JSON_VALUE)
      .when()
      .patch(url, pedido.getNumeroPedido());
  }

  @Entao("deve retornar o status atualizado do pedido como pronto")
  public void confereResposta() throws Exception {

    assertEquals(HttpStatus.OK.value(), resposta.getStatusCode());

    var dados = getDadosResposta();

    assertEquals(pedido.getNumeroPedido(), Long.valueOf((Integer) dados.get("numeroPedido")));
    assertEquals(StatusPedido.PRONTO.toString(), dados.get("status"));
  }

  // Métodos auxiliares
  private Map<String, Object> getDadosResposta() throws Exception {
    
    ObjectMapper mapper = new ObjectMapper();
    
    Map<String, Object> responseMap = 
        mapper.readValue(resposta.getBody().asString(),
          new TypeReference<Map<String, Object>>() {});
    
    return (Map<String, Object>) responseMap.get("data");
  }
}
