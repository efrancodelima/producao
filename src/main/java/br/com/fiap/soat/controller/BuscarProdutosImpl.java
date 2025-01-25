package br.com.fiap.soat.controller;

import br.com.fiap.soat.controller.contract.BuscarProdutos;
import br.com.fiap.soat.controller.wrapper.ResponseWrapper;
import br.com.fiap.soat.entity.ProdutoJpa;
import br.com.fiap.soat.exception.BadRequestException;
import br.com.fiap.soat.service.provider.BuscarProdutosService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador REST para buscar um cliente pelo CPF.
 */
@RestController
@RequestMapping("/produto")
public class BuscarProdutosImpl implements BuscarProdutos {

  private final BuscarProdutosService service;

  /**
   * O construtor público da classe.
   *
   * @param service O service para buscar o cliente.
   */
  @Autowired
  public BuscarProdutosImpl(BuscarProdutosService service) {
    this.service = service;
  }

  @Override
  public ResponseWrapper<List<ProdutoJpa>> buscarProdutos(List<Long> codigoProdutos) {

    try {
      var produtos = service.execute(codigoProdutos);
      return new ResponseWrapper<>(HttpStatus.OK, produtos);

    } catch (BadRequestException e) {
      return new ResponseWrapper<>(HttpStatus.BAD_REQUEST, e.getMessage());
    }
  }
}
