package br.com.fiap.soat.exception;

import br.com.fiap.soat.exception.messages.BusinessRuleMessage;

public class BusinessRuleException extends Exception {

  public BusinessRuleException(BusinessRuleMessage msg) {
    super(msg.getMessage());
  }
}
