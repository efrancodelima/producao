package br.com.fiap.soat.controller;

import br.com.fiap.soat.controller.contract.CadastrarProduto;
import br.com.fiap.soat.controller.wrapper.ResponseWrapper;
import br.com.fiap.soat.dto.ProdutoDto;
import br.com.fiap.soat.entity.ProdutoJpa;
import br.com.fiap.soat.exception.BadRequestException;
import br.com.fiap.soat.service.provider.CadastrarProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador REST para cadastro de clientes.
 */
@RestController
@RequestMapping("/produto")
public class CadastrarProdutoImpl implements CadastrarProduto {

  private final CadastrarProdutoService service;

  /**
   * O construtor público da classe.
   *
   * @param service O service para cadastrar o cliente.
   */
  @Autowired
  public CadastrarProdutoImpl(CadastrarProdutoService service) {
    this.service = service;
  }

  @Override
  public ResponseWrapper<ProdutoJpa> cadastrarProduto(ProdutoDto clienteDto) {

    try {
      var cliente = service.execute(clienteDto);
      return new ResponseWrapper<>(HttpStatus.CREATED, cliente);

    } catch (BadRequestException e) {
      return new ResponseWrapper<>(HttpStatus.BAD_REQUEST, e.getMessage());
    }
  }
}

