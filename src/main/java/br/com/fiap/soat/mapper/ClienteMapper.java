package br.com.fiap.soat.mapper;

import br.com.fiap.soat.dto.ClienteDto;
import br.com.fiap.soat.entity.ProdutoJpa;

/**
 * Responsável por mapear um cliente DTO para uma entidade JPA.
 */
public class ClienteMapper {

  private ClienteMapper() {}

  /**
   * Mapeia um cliente DTO para uma entidade JPA.
   *
   * @param clienteDto O objeto DTO a ser mapeado.
   * @return A entidade JPA.
   */
  public static ProdutoJpa toEntity(ClienteDto clienteDto) {
    
    if (clienteDto == null) {
      return null;
    }
    return new ProdutoJpa();
  }
}
