package br.com.fiap.soat.exception.messages;

public enum BadRequestMessage {
    
  NUM_PED_NULL("Informe o número do pedido."),
  NUM_PED_MIN("O número do pedido deve ser igual ou maior que 1.");


  private String mensagem;

  BadRequestMessage(String mensagem) {
    this.mensagem = mensagem;
  }

  public String getMessage() {
    return mensagem;
  }
}
