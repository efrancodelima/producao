package br.com.fiap.soat.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entidade JPA StatusPedido.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "status_pedido")
@IdClass(StatusPedidoId.class)
public class StatusPedidoJpa implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @Column(name = "numero_pedido")
  private Long numeroPedido;

  @Id
  @Enumerated(EnumType.STRING)
  @Column(name = "status", nullable = false)
  private StatusPedidoEnum status;

  @Column(name = "timestamp", nullable = false)
  private LocalDateTime timestamp;
}
