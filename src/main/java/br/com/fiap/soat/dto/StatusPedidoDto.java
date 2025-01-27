package br.com.fiap.soat.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Classe StatusPedidoDto.
 */
@Data
@NoArgsConstructor
public class StatusPedidoDto {

  @Schema(description = "Número do pedido.", example = "123")
  public Long numeroPedido;

  @Schema(description = "Status do pedido.", example = "RECEBIDO")
  public String status;

  @Schema(description = "Timestamp da última atualização do pedido.",
      example = "2025-01-20 09:10:00")
  public String timestamp;

}
