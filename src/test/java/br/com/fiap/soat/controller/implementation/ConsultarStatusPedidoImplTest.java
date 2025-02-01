package br.com.fiap.soat.controller.implementation;

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
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

class ConsultarStatusPedidoImplTest {

  AutoCloseable closeable;

  @Mock
  ConsultarStatusPedidoService serviceMock;

  @InjectMocks
  ConsultarStatusPedidoImpl controller;

  @BeforeEach
  void setup() {
    closeable = MockitoAnnotations.openMocks(this);
  }

  @AfterEach
  void tearDown() throws Exception {
    closeable.close();
  }

  @Test
  void deveConsultarStatusPedidoComSucesso() throws Exception {

    // Arrange
    var registros = getListaRegistroProducao();
    doReturn(registros).when(serviceMock).execute(Mockito.any());

    // Act
    var response = controller.consultarStatus(getListaNumero());

    // Assert
    assertEquals(HttpStatus.OK.value(), response.getStatusCode().value());
    assertEquals(registros, response.getBody().getData());
    assertEquals(null, response.getBody().getErrorMsg());
  }

  @Test
  void deveRetornarStatusBadRequest() throws Exception {

    // Arrange
    var excecao = new BadRequestException(BadRequestMessage.NUM_PED_MIN);
    doThrow(excecao).when(serviceMock).execute(Mockito.any());

    // Act
    var response = controller.consultarStatus(getListaNumero());

    // Assert
    assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatusCode().value());
    assertEquals(null, response.getBody().getData());
    assertEquals(excecao.getMessage(), response.getBody().getErrorMsg());
  }

  private List<RegistroProducaoJpa> getListaRegistroProducao() {
    var lista = new ArrayList<RegistroProducaoJpa>();
    lista.add(new RegistroProducaoJpa());
    return lista;
  }

  private List<Long> getListaNumero() {
    var lista = new ArrayList<Long>();
    lista.add(1L);
    return lista;
  }
}
