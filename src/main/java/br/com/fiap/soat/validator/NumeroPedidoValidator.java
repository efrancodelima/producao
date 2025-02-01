package br.com.fiap.soat.validator;

import br.com.fiap.soat.exception.BadRequestException;
import br.com.fiap.soat.exception.messages.BadRequestMessage;
import java.util.List;

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

  public static void validar(List<Long> numerosPedidos) throws BadRequestException {

    if (numerosPedidos == null) {
      throw new BadRequestException(BadRequestMessage.NUM_PED_NULL);
    }

    for (Long num : numerosPedidos) {
      validar(num);
    }
  }
}
