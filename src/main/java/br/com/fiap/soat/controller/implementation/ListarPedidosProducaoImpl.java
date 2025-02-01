package br.com.fiap.soat.controller.implementation;

import br.com.fiap.soat.controller.contract.ListarPedidosProducao;
import br.com.fiap.soat.controller.wrapper.ResponseWrapper;
import br.com.fiap.soat.entity.RegistroProducaoJpa;
import br.com.fiap.soat.service.provider.ListarPedidosProducaoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/producao")
public class ListarPedidosProducaoImpl implements ListarPedidosProducao {

  private final ListarPedidosProducaoService service;

  @Autowired
  public ListarPedidosProducaoImpl(ListarPedidosProducaoService service) {
    this.service = service;
  }

  @Override
  public ResponseEntity<ResponseWrapper<List<RegistroProducaoJpa>>> listarPedidosProducao() {
    var listaItens = service.execute();
    return ResponseEntity.status(HttpStatus.OK).body(new ResponseWrapper<>(listaItens));
  }
}

