package br.com.fiap.soat.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class RegistroProducaoIdTest {

  @Test
  void equalsDeveRetornarTrue() {
    
    // Arrange
    var id1 = new RegistroProducaoId(1L, StatusPedido.RECEBIDO);
    var id2 = new RegistroProducaoId(1L, StatusPedido.RECEBIDO);

    // Act
    var saoIguais = id1.equals(id2);
    
    // Assert
    assertEquals(true, saoIguais);
  }

  @Test
  void deveGerarIdenticosHashCodes() {
    
    // Arrange
    var id1 = new RegistroProducaoId(1L, StatusPedido.RECEBIDO);
    var id2 = new RegistroProducaoId(1L, StatusPedido.RECEBIDO);
    
    // Act and assert
    assertEquals(id1.hashCode(), id2.hashCode());
  }
}
