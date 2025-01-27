package br.com.fiap.soat.exception.messages;

/**
 * Mensagens para exceções do tipo Bad Request.
 */
public enum BusinessRuleMessage {
    
  PED_FINALIZADO("O pedido já foi finalizado."),
  PAG_PENDENTE("O pedido ainda não teve o pagamento aprovado.");


  private String mensagem;

  BusinessRuleMessage(String mensagem) {
    this.mensagem = mensagem;
  }

  public String getMessage() {
    return mensagem;
  }

}
