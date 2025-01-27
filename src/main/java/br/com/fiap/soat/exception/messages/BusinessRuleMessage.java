package br.com.fiap.soat.exception.messages;

/**
 * Mensagens para exceções de regra de negócio.
 */
public enum BusinessRuleMessage {
    
  PED_FINALIZADO("O pedido já foi finalizado.");


  private String mensagem;

  BusinessRuleMessage(String mensagem) {
    this.mensagem = mensagem;
  }

  public String getMessage() {
    return mensagem;
  }

}
