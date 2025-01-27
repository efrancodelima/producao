package br.com.fiap.soat.exception;

import br.com.fiap.soat.exception.messages.BusinessRuleMessage;

/**
 * Exceção customizada para regras de negócio.
 */
public class BusinessRuleException extends Exception {

  public BusinessRuleException(BusinessRuleMessage msg) {
    super(msg.getMessage());
  }
}
