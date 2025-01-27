package br.com.fiap.soat.validator;

import br.com.fiap.soat.exception.BadRequestException;
import br.com.fiap.soat.exception.messages.BadRequestMessage;
import java.util.List;

/**
 * Classe responsável por validar o número do pedido.
 */
public class NumeroPedidoValidator {

  private NumeroPedidoValidator() {}

  public static void validar(Long numeroPedido) throws BadRequestException {

    if (numeroPedido == null) {
      throw new BadRequestException(BadRequestMessage.NUM_PED_NULL);
    }

    if (numeroPedido < 1) {
      throw new BadRequestException(BadRequestMessage.NUM_PED_MIN);
    }
  }

  public static void validar(List<Long> numeroPedido) throws BadRequestException {

    for (Long num : numeroPedido) {
      validar(num);
    }
  }
}
