package br.com.fiap.soat.entity;

import java.io.Serializable;
import java.util.Objects;
import lombok.Data;

@Data
public class RegistroProducaoId implements Serializable {

  private Long numeroPedido;
  private StatusPedido status;

  public RegistroProducaoId() {}

  public RegistroProducaoId(Long numeroPedido, StatusPedido status) {
    this.numeroPedido = numeroPedido;
    this.status = status;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    RegistroProducaoId that = (RegistroProducaoId) o;
    return Objects.equals(numeroPedido, that.numeroPedido) && status == that.status;
  }

  @Override
  public int hashCode() {
    return Objects.hash(numeroPedido, status);
  }
}
