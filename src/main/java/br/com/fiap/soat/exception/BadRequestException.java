package br.com.fiap.soat.exception;

import br.com.fiap.soat.exception.messages.BadRequestMessage;

/**
 * Exceção customizada para requisições incorretas.
 */
public class BadRequestException extends Exception {

  public BadRequestException(BadRequestMessage msg) {
    super(msg.getMessage());
  }
}
