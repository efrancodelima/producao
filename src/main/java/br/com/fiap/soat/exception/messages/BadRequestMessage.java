package br.com.fiap.soat.exception.messages;

/**
 * Mensagens para exceções do tipo Bad Request.
 */
public enum BadRequestMessage {
    
  COD_PROD_NULL("Informe o código do produto."),
  COD_PROD_MIN("O código do produto deve ser igual ou maior que 1."),
  
  PRODUTO_NULL("Informe os dados do produto."),

  CODIGO_CLIENTE("O código do cliente deve ser igual ou maior que 1."),
  CPF_INVALIDO("O CPF informado é inválido."),
  
  EMAIL_INVALIDO("O e-mail informado é inválido."),
  EMAIL_MAX("O e-mail não pode ter mais de 40 caracteres."),
  ITEM_MIN("O pedido deve conter, pelo menos, um item."),
  NOME_EMAIL_NULL("Informe o nome ou o email do cliente."),
  NOME_INVALIDO("O nome do cliente deve conter, no mínimo, uma palavra com "
      + "três ou mais caracteres."),
  NOME_MAX("O nome do cliente não pode ter mais de 50 caracteres."),
  QTDE_ITEM("A quantidade do item deve ser igual ou maior que 1");


  private String mensagem;

  BadRequestMessage(String mensagem) {
    this.mensagem = mensagem;
  }

  public String getMessage() {
    return mensagem;
  }

}
