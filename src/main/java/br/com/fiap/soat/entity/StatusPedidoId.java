package br.com.fiap.soat.entity;

import java.io.Serializable;
import java.util.Objects;

import lombok.Data;

@Data
public class StatusPedidoId implements Serializable {

  private Long numeroPedido;
  private StatusPedidoEnum status;

  public StatusPedidoId() {}

  public StatusPedidoId(Long numeroPedido, StatusPedidoEnum status) {
    this.numeroPedido = numeroPedido;
    this.status = status;
  }

  // equals and hashCode methods
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    StatusPedidoId that = (StatusPedidoId) o;
    return Objects.equals(numeroPedido, that.numeroPedido) &&
           status == that.status;
  }

  @Override
  public int hashCode() {
    return Objects.hash(numeroPedido, status);
  }
}
