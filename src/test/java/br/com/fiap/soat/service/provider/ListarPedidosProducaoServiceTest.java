package br.com.fiap.soat.service.provider;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;

import br.com.fiap.soat.entity.RegistroProducaoJpa;
import br.com.fiap.soat.entity.StatusPedido;
import br.com.fiap.soat.repository.RegistroProducaoRepository;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class ListarPedidosProducaoServiceTest {

  AutoCloseable closeable;
  ListarPedidosProducaoService service;

  @Mock
  RegistroProducaoRepository repositoryMock;

  @BeforeEach
  void setup() {
    closeable = MockitoAnnotations.openMocks(this);
    this.service = new ListarPedidosProducaoService(repositoryMock);
  }

  @AfterEach
  void tearDown() throws Exception {
    closeable.close();
  }

  @Test
  void deveListarPedidosProducaoComSucesso() {

    var registros = getListaRegistrosProducao();

    doReturn(registros).when(repositoryMock).findAllWithLatestTimestamp();
    

    assertDoesNotThrow(() -> {
      var response = service.execute();
      assertEquals(registros.size(), response.size());
      assertEquals(registros.get(0), response.get(0));
    });
  }

  private List<RegistroProducaoJpa> getListaRegistrosProducao() {
    
    var lista = new ArrayList<RegistroProducaoJpa>();
    
    lista.add(getRegistroProducao(StatusPedido.RECEBIDO));
    lista.add(getRegistroProducao(StatusPedido.EM_PREPARACAO));
    lista.add(getRegistroProducao(StatusPedido.PRONTO));
    lista.add(getRegistroProducao(StatusPedido.FINALIZADO));
    
    return lista;
  }

  private RegistroProducaoJpa getRegistroProducao(StatusPedido status) {
    return new RegistroProducaoJpa(1L, status, LocalDateTime.now());
  }
}
