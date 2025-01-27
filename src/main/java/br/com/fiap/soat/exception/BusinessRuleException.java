package br.com.fiap.soat.exception;

import br.com.fiap.soat.exception.messages.BusinessRuleMessage;

/**
 * Exceção customizada para regras de negócio.
 */
public class BusinessRuleException extends Exception {

  /**
   * O construtor público.
   *
   * @param msg Mensagem de erro associada à exceção.
   */
  public BusinessRuleException(BusinessRuleMessage msg) {
    super(msg.getMessage());
  }

}
