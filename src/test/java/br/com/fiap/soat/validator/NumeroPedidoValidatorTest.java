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

    var lista = new ArrayList<Long>();
    lista.add(1L);
    lista.add(2L);

    assertDoesNotThrow(() -> {
      NumeroPedidoValidator.validar(lista);
    });
  }

  @Test
  void deveLancarExcecaoQuandoNumeroForNulo() {

    Long numero = null;

    var exception = assertThrows(BadRequestException.class, () -> {
      NumeroPedidoValidator.validar(numero);
    });

    assertEquals(exception.getMessage(), BadRequestMessage.NUM_PED_NULL.getMessage());
  }

  @Test
  void deveLancarExcecaoQuandoListaForNula() {

    List<Long> lista = null;

    var exception = assertThrows(BadRequestException.class, () -> {
      NumeroPedidoValidator.validar(lista);
    });

    assertEquals(exception.getMessage(), BadRequestMessage.NUM_PED_NULL.getMessage());
  }

  @Test
  void deveLancarExcecaoQuandoParametroForMenorQueUm() {

    var lista = new ArrayList<Long>();
    lista.add(0L);

    var exception = assertThrows(BadRequestException.class, () -> {
      NumeroPedidoValidator.validar(lista);
    });

    assertEquals(exception.getMessage(), BadRequestMessage.NUM_PED_MIN.getMessage());
  }
}
