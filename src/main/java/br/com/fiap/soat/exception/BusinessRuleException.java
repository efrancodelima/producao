package br.com.fiap.soat.exception;

/**
 * Exceção customizada para regras de negócio.
 */
public class BusinessRuleException extends Exception {

  public BusinessRuleException() {
    super("O pedido já foi finalizado.");
  }
}
