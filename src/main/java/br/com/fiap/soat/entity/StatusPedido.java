package br.com.fiap.soat.entity;

/**
 * Lista de status do pedido.
 */
public enum StatusPedido {

  RECEBIDO, EM_PREPARACAO, PRONTO, FINALIZADO;

  public static StatusPedido fromString(String statusPedidoStr) {

    statusPedidoStr = statusPedidoStr == null ? null : statusPedidoStr.toUpperCase().trim();

    try {
      return StatusPedido.valueOf(statusPedidoStr);
    } catch (Exception e) {
      return null;
    }
  }
}