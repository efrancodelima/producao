package br.com.fiap.soat.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO usado na resposta do Service ConultarPagamentoService.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PagamentoDto {

  private Long codigoPagamento;
  private Long numeroPedido;
  private BigDecimal valor;
  private String status;
  private LocalDateTime timestamp;

}