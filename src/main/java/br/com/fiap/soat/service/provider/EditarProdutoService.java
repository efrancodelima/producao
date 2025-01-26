package br.com.fiap.soat.service.provider;

import br.com.fiap.soat.dto.ProdutoDto;
import br.com.fiap.soat.entity.ProdutoJpa;
import br.com.fiap.soat.exception.BadRequestException;
import br.com.fiap.soat.exception.NotFoundException;
import br.com.fiap.soat.exception.messages.NotFoundMessage;
import br.com.fiap.soat.mapper.ProdutoMapper;
import br.com.fiap.soat.repository.ProdutoRepository;
import br.com.fiap.soat.validator.CodigoProdutoValidator;
import br.com.fiap.soat.validator.ProdutoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Service para cadastrar clientes.
 */
@Component
public class EditarProdutoService {

  private final ProdutoRepository repository;

  /**
   * O construtor público do service.
   *
   * @param repository O repositório para acesso ao banco de dados.
   */
  @Autowired
  public EditarProdutoService(ProdutoRepository repository) {
    this.repository = repository;
  }

  /** 
   * Editar produto.
   *
   * @param codigoProduto O código do produto que será editado.
   * @param produtoDto O produto com as alterações feitas.
   * @return O produto editado.
   * @throws BadRequestException Exceção do tipo bad request lançada pelo método.
   * @throws NotFoundException Exceção lançada caso não seja encontrado algum 
   *     produto para o código informado.
   */
  public ProdutoJpa execute(Long codigoProduto, ProdutoDto produtoDto)
      throws BadRequestException, NotFoundException {

    CodigoProdutoValidator.validar(codigoProduto);
    ProdutoValidator.validar(produtoDto);

    var produtoExiste = repository.existsById(codigoProduto);
    if (!produtoExiste) {
      throw new NotFoundException(NotFoundMessage.COD_PRODUTO);
    }
    
    var produtoJpa = ProdutoMapper.toEntity(produtoDto);
    produtoJpa.setCodigo(codigoProduto);

    return repository.save(produtoJpa);
  }
}
