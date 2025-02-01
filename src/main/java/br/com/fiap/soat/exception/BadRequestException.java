package br.com.fiap.soat.exception;

import br.com.fiap.soat.exception.messages.BadRequestMessage;

public class BadRequestException extends Exception {

  public BadRequestException(BadRequestMessage msg) {
    super(msg.getMessage());
  }
}
