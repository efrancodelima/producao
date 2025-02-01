package br.com.fiap.soat.controller.implementation;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

import br.com.fiap.soat.entity.RegistroProducaoJpa;
import br.com.fiap.soat.exception.BadRequestException;
import br.com.fiap.soat.exception.messages.BadRequestMessage;
import br.com.fiap.soat.service.provider.ConsultarStatusPedidoService;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

class ConsultarStatusPedidoImplTest {

  AutoCloseable closeable;
  ConsultarStatusPedidoImpl controller;

  @Mock
  ConsultarStatusPedidoService serviceMock;

  @BeforeEach
  void setup() {
    closeable = MockitoAnnotations.openMocks(this);
    this.controller = new ConsultarStatusPedidoImpl(serviceMock);
  }

  @AfterEach
  void tearDown() throws Exception {
    closeable.close();
  }

  @Test
  void deveConsultarStatusPedidoComSucesso() throws Exception {

    var registros = getListaRegistroProducao();
    doReturn(registros).when(serviceMock).execute(Mockito.any());

    assertDoesNotThrow(() -> {

      var response = controller.consultarStatus(getListaNumero());

      assertEquals(HttpStatus.OK.value(), response.getStatusCode().value());
      assertEquals(registros, response.getBody().getData());
      assertEquals(null, response.getBody().getErrorMsg());
    });
  }

  @Test
  void deveLancarExcecaoBadRequest() throws Exception {

    var excecao = new BadRequestException(BadRequestMessage.NUM_PED_MIN);
    doThrow(excecao).when(serviceMock).execute(Mockito.any());

    assertDoesNotThrow(() -> {

      var response = controller.consultarStatus(getListaNumero());

      assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatusCode().value());
      assertEquals(null, response.getBody().getData());
      assertEquals(excecao.getMessage(), response.getBody().getErrorMsg());
    });
  }

  private List<RegistroProducaoJpa> getListaRegistroProducao() {

    var registroProducao = new RegistroProducaoJpa();
    var lista = new ArrayList<RegistroProducaoJpa>();
    lista.add(registroProducao);
    return lista;
  }

  private List<Long> getListaNumero() {

    var lista = new ArrayList<Long>();
    lista.add(1L);
    return lista;
  }
}
