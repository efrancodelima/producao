package br.com.fiap.soat.exception;

/**
 * Exceção customizada para recursos não encontrados.
 */
public class NotFoundException extends Exception {

  public NotFoundException() {
    super("Nenhum pedido foi encontrado para o número informado.");
  }
}
