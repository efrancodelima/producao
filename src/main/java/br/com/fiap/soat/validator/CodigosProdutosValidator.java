package br.com.fiap.soat.validator;

import br.com.fiap.soat.exception.BadRequestException;
import br.com.fiap.soat.exception.messages.BadRequestMessage;
import java.util.List;

/**
 * Responsável por validar a requisição do controller BuscarProdutos.
 */
public class CodigosProdutosValidator {

  private CodigosProdutosValidator() {}

  /**
   * Valida a requisição do controller BuscarProdutos.
   *
   * @param codigoProdutos Os códigos dos produtos a serem validados.
   * @throws BadRequestException Exceção do tipo bad request lançada pela validação.
   */
  public static void validar(List<Long> codigoProdutos) throws BadRequestException {

    for (Long codigo : codigoProdutos) {

      if (codigo == null) {
        throw new BadRequestException(BadRequestMessage.PROD_COD_NULO);
      }

      if (codigo < 1) {
        throw new BadRequestException(BadRequestMessage.PROD_COD_MIN);
      }
    }
  }
}
