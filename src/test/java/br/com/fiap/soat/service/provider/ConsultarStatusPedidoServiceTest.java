package br.com.fiap.soat.service.provider;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;

import br.com.fiap.soat.entity.RegistroProducaoJpa;
import br.com.fiap.soat.repository.RegistroProducaoRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

class ConsultarStatusPedidoServiceTest {

  AutoCloseable closeable;
  ConsultarStatusPedidoService service;

  @Mock
  RegistroProducaoRepository repositoryMock;

  @BeforeEach
  void setup() {
    closeable = MockitoAnnotations.openMocks(this);
    this.service = new ConsultarStatusPedidoService(repositoryMock);
  }

  @AfterEach
  void tearDown() throws Exception {
    closeable.close();
  }

  @Test
  void deveConsultarStatusPedidoComSucesso() {

    var registroProducao = new RegistroProducaoJpa();

    doReturn(Optional.of(registroProducao)).when(repositoryMock)
        .findTopByNumeroPedidoOrderByTimestampDesc(Mockito.anyLong());

    assertDoesNotThrow(() -> {
      var response = service.execute(getListaNumero());
      assertEquals(registroProducao, response.get(0));
    });
  }

  private List<Long> getListaNumero() {
    var lista = new ArrayList<Long>();
    lista.add(1L);
    return lista;
  }
}
