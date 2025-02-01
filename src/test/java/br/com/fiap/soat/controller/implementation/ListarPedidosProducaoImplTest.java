package br.com.fiap.soat.controller.implementation;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;

import br.com.fiap.soat.entity.RegistroProducaoJpa;
import br.com.fiap.soat.service.provider.ListarPedidosProducaoService;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

class ListarPedidosProducaoImplTest {

  AutoCloseable closeable;
  ListarPedidosProducaoImpl controller;

  @Mock
  ListarPedidosProducaoService serviceMock;

  @BeforeEach
  void setup() {
    closeable = MockitoAnnotations.openMocks(this);
    this.controller = new ListarPedidosProducaoImpl(serviceMock);
  }

  @AfterEach
  void tearDown() throws Exception {
    closeable.close();
  }

  @Test
  void deveListarItensProducaoComSucesso() {

    var lista = getListaRegistrosProducao();
    doReturn(lista).when(serviceMock).execute();

    assertDoesNotThrow(() -> {

      var response = controller.listarPedidosProducao();

      assertEquals(HttpStatus.OK.value(), response.getStatusCode().value());
      assertEquals(lista, response.getBody().getData());
      assertEquals(null, response.getBody().getErrorMsg());
    });
  }

  private List<RegistroProducaoJpa> getListaRegistrosProducao() {
    var lista = new ArrayList<RegistroProducaoJpa>();
    lista.add(new RegistroProducaoJpa());
    return lista;
  }
}
