package br.com.fiap.soat.controller.implementation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;

import br.com.fiap.soat.entity.RegistroProducaoJpa;
import br.com.fiap.soat.service.provider.ListarPedidosProducaoService;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

class ListarPedidosProducaoImplTest {

  AutoCloseable closeable;

  @Mock
  ListarPedidosProducaoService serviceMock;

  @InjectMocks
  ListarPedidosProducaoImpl controller;

  @BeforeEach
  void setup() {
    closeable = MockitoAnnotations.openMocks(this);
  }

  @AfterEach
  void tearDown() throws Exception {
    closeable.close();
  }

  @Test
  void deveListarItensProducaoComSucesso() {

    // Arrange
    var lista = getListaRegistrosProducao();
    doReturn(lista).when(serviceMock).execute();

    // Act
    var response = controller.listarPedidosProducao();

    // Assert
    assertEquals(HttpStatus.OK.value(), response.getStatusCode().value());
    assertEquals(lista, response.getBody().getData());
    assertEquals(null, response.getBody().getErrorMsg());
    
  }

  private List<RegistroProducaoJpa> getListaRegistrosProducao() {
    var lista = new ArrayList<RegistroProducaoJpa>();
    lista.add(new RegistroProducaoJpa());
    return lista;
  }
}
