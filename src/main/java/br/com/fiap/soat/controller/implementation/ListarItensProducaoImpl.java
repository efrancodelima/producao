package br.com.fiap.soat.controller.implementation;

import br.com.fiap.soat.controller.contract.ListarItensProducao;
import br.com.fiap.soat.controller.wrapper.ResponseWrapper;
import br.com.fiap.soat.entity.RegistroProducaoJpa;
import br.com.fiap.soat.service.provider.ListarItensProducaoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador REST para listar os itens da esteira de produção.
 */
@RestController
@RequestMapping("/producao")
public class ListarItensProducaoImpl implements ListarItensProducao {

  private final ListarItensProducaoService service;

  @Autowired
  public ListarItensProducaoImpl(ListarItensProducaoService service) {
    this.service = service;
  }

  @Override
  public ResponseEntity<ResponseWrapper<List<RegistroProducaoJpa>>> listarItensProducao() {
    var listaItens = service.execute();
    return ResponseEntity.status(HttpStatus.OK).body(new ResponseWrapper<>(listaItens));
  }
}

