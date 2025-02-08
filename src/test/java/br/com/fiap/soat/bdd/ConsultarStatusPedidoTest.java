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

public class ConsultarStatusPedidoTest {

  private RegistroProducaoJpa registro;
  private Response response;
  private final String url = "http://localhost:8082/producao/consultar/{pedidos}";

  @Dado("que existe um pedido pronto na esteira de produção")
  public void instanciarRegistro() {
    
    var timestamp = LocalDateTime.of(2025, 2, 8, 18, 41, 0);
    registro = new RegistroProducaoJpa(2L, StatusPedido.PRONTO, timestamp);
  }
  
  @Quando("o sistema receber uma consulta sobre o status do pedido")
  public void enviarRequisicao() {

    response = given()
      .contentType(MediaType.APPLICATION_JSON_VALUE)
      .when()
      .get(url, registro.getNumeroPedido());
  }
  
  @Entao("o sistema deve retornar o status pronto")
  public void conferirResposta() throws Exception {
    
    assertEquals(HttpStatus.OK.value(), response.getStatusCode());

    var responseBody = (new ObjectMapper()).readTree(response.getBody().asString());
    var numeroPedidoRecebido = responseBody.get("data").get(0).get("numeroPedido").asLong();
    var statusPedidoRecebido = responseBody.get("data").get(0).get("status").asText();

    assertEquals(registro.getNumeroPedido(), numeroPedidoRecebido);
    assertEquals(registro.getStatus().toString(), statusPedidoRecebido);
  }
}
