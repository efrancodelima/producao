package br.com.fiap.soat.service.provider;

import br.com.fiap.soat.entity.ProdutoJpa;
import br.com.fiap.soat.exception.BadRequestException;
import br.com.fiap.soat.repository.ProdutoRepository;
import br.com.fiap.soat.service.contract.Service;
import br.com.fiap.soat.validator.CodigosProdutosValidator;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Service para buscar clientes pelo número do CPF.
 */
@Component
public class BuscarProdutosService implements Service<List<Long>, List<ProdutoJpa>> {

  private final ProdutoRepository repository;

  /**
   * O construtor público do service.
   *
   * @param repository O repositório para acesso ao banco de dados.
   */
  @Autowired
  public BuscarProdutosService(ProdutoRepository repository) {
    this.repository = repository;
  }

  @Override
  public List<ProdutoJpa> execute(List<Long> codigoProdutos)
      throws BadRequestException {
    
    CodigosProdutosValidator.validar(codigoProdutos);

    List<ProdutoJpa> produtos = new ArrayList<>();

    for (Long codigo : codigoProdutos) {

      var produtoOpt = repository.findById(codigo);

      if (produtoOpt.isPresent()) {
        produtos.add(produtoOpt.get());
      
      } else {
        produtos.add(null);
      }
    }

    return produtos;
  }
  
}
