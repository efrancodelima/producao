package br.com.fiap.soat.controller;

import br.com.fiap.soat.controller.contract.EditarProduto;
import br.com.fiap.soat.controller.wrapper.ResponseWrapper;
import br.com.fiap.soat.dto.ProdutoDto;
import br.com.fiap.soat.entity.ProdutoJpa;
import br.com.fiap.soat.exception.BadRequestException;
import br.com.fiap.soat.exception.NotFoundException;
import br.com.fiap.soat.service.provider.EditarProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador REST para cadastro de clientes.
 */
@RestController
@RequestMapping("/produto")
public class EditarProdutoImpl implements EditarProduto {

  private final EditarProdutoService service;

  /**
   * O construtor público da classe.
   *
   * @param service O service para cadastrar o cliente.
   */
  @Autowired
  public EditarProdutoImpl(EditarProdutoService service) {
    this.service = service;
  }

  @Override
  public ResponseWrapper<ProdutoJpa> editarProduto(long codigo, ProdutoDto produtoDto) {

    try {
      var produto = service.execute(codigo, produtoDto);
      return new ResponseWrapper<>(HttpStatus.CREATED, produto);

    } catch (BadRequestException e) {
      return new ResponseWrapper<>(HttpStatus.BAD_REQUEST, e.getMessage());
    
    } catch (NotFoundException e) {
      return new ResponseWrapper<>(HttpStatus.NOT_FOUND, e.getMessage());
    }
  }
}

