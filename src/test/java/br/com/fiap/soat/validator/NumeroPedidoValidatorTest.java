package br.com.fiap.soat.validator;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import br.com.fiap.soat.exception.BadRequestException;
import br.com.fiap.soat.exception.messages.BadRequestMessage;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

class NumeroPedidoValidatorTest {

  @Test
  void naoDeveLancarExcecao() {

    // Arrange
    var lista = new ArrayList<Long>();
    lista.add(1L);
    lista.add(2L);

    // Act and assert
    assertDoesNotThrow(() -> {
      NumeroPedidoValidator.validar(lista);
    });
  }

  @Test
  void deveLancarExcecaoQuandoNumeroForNulo() {

    // Arrange
    Long numero = null;

    // Act and assert
    var exception = assertThrows(BadRequestException.class, () -> {
      NumeroPedidoValidator.validar(numero);
    });

    assertEquals(exception.getMessage(), BadRequestMessage.NUM_PED_NULL.getMessage());
  }

  @Test
  void deveLancarExcecaoQuandoListaForNula() {

    // Arrange
    List<Long> lista = null;

    // Act and assert
    var exception = assertThrows(BadRequestException.class, () -> {
      NumeroPedidoValidator.validar(lista);
    });

    assertEquals(exception.getMessage(), BadRequestMessage.NUM_PED_NULL.getMessage());
  }

  @Test
  void deveLancarExcecaoQuandoParametroForMenorQueUm() {

    // Arrange
    var lista = new ArrayList<Long>();
    lista.add(0L);

    // Act and assert
    var exception = assertThrows(BadRequestException.class, () -> {
      NumeroPedidoValidator.validar(lista);
    });

    assertEquals(exception.getMessage(), BadRequestMessage.NUM_PED_MIN.getMessage());
  }
}
