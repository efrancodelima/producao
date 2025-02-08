package br.com.fiap.soat.bdd;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ConsultarStatusPedidoTest {
  
  @Given("que o cliente possui um pedido")
  public void criarRequisicao() {
    System.out.println("o cliente possui um pedido");
  }
  
  @When("o pedido for enviado para checkout")
  public void enviarRequisicao() {
    System.out.println("o pedido é enviado");
  }
  
  @Then("o sistema deve devolver o status do pedido como recebido")
  public void conferirResposta() {
    System.out.println("a resposta é conferida");
  }
}
