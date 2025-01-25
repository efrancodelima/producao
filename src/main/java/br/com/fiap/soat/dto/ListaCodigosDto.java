package br.com.fiap.soat.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Classe ItemPedidoDto.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListaCodigosDto {

  @Schema(
      description = "Uma lista com códigos.",
      example = """
          [ 2, 3, 5, 7, 11, 13, 17 ]
          """)
  private List<Long> codigos;

}
