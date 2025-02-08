package br.com.fiap.soat.bdd;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

import br.com.fiap.soat.entity.RegistroProducaoJpa;
import br.com.fiap.soat.entity.StatusPedido;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import io.restassured.response.Response;
import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

public class AtualizarStatusPedidoTest {

  private RegistroProducaoJpa registro;
  private Response response;
  private final String url = "http://localhost:8082/producao/atualizar/{pedido}";

  @Dado("que existe um pedido em preparação na esteira de produção")
  public void instanciarRegistro() {
    
    var timestamp = LocalDateTime.of(2025, 2, 8, 12, 0, 0);
    registro = new RegistroProducaoJpa(1L, StatusPedido.EM_PREPARACAO, timestamp);
  }

  @Quando("o sistema receber uma solicitação para atualizar o status do pedido")
  public void enviarRequisicao() {

    response = given()
      .contentType(MediaType.APPLICATION_JSON_VALUE)
      .when()
      .patch(url, registro.getNumeroPedido());
  }

  @Entao("deve retornar o status atualizado do pedido como pronto")
  public void conferirResposta() throws Exception {

    assertEquals(HttpStatus.OK.value(), response.getStatusCode());

    var responseBody = (new ObjectMapper()).readTree(response.getBody().asString());
    var numeroPedidoRecebido = responseBody.get("data").get("numeroPedido").asLong();
    var statusPedidoRecebido = responseBody.get("data").get("status").asText();

    assertEquals(registro.getNumeroPedido(), numeroPedidoRecebido);
    assertEquals(StatusPedido.PRONTO.toString(), statusPedidoRecebido);
  }
}
