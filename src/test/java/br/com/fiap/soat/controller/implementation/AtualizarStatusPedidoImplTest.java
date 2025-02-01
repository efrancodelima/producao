package br.com.fiap.soat.controller.implementation;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

import br.com.fiap.soat.entity.RegistroProducaoJpa;
import br.com.fiap.soat.exception.BadGatewayException;
import br.com.fiap.soat.exception.BadRequestException;
import br.com.fiap.soat.exception.BusinessRuleException;
import br.com.fiap.soat.exception.NotFoundException;
import br.com.fiap.soat.exception.messages.BadRequestMessage;
import br.com.fiap.soat.exception.messages.BusinessRuleMessage;
import br.com.fiap.soat.service.provider.AtualizarStatusPedidoService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

class AtualizarStatusPedidoImplTest {

  AutoCloseable closeable;
  AtualizarStatusPedidoImpl controller;

  @Mock
  AtualizarStatusPedidoService serviceMock;

  @BeforeEach
  void setup() {
    closeable = MockitoAnnotations.openMocks(this);
    this.controller = new AtualizarStatusPedidoImpl(serviceMock);
  }

  @AfterEach
  void tearDown() throws Exception {
    closeable.close();
  }

  @Test
  void deveAtualizarStatusPedidoComSucesso() throws Exception {

    var registroProducao = new RegistroProducaoJpa();
    doReturn(registroProducao).when(serviceMock).execute(Mockito.anyLong());

    assertDoesNotThrow(() -> {

      var response = controller.atualizarPedido(1L);

      assertEquals(HttpStatus.OK.value(), response.getStatusCode().value());
      assertEquals(registroProducao, response.getBody().getData());
      assertEquals(null, response.getBody().getErrorMsg());
    });
  }

  @Test
  void deveLancarExcecaoBadRequest() throws Exception {

    var excecao = new BadRequestException(BadRequestMessage.NUM_PED_MIN);
    doThrow(excecao).when(serviceMock).execute(Mockito.anyLong());

    assertDoesNotThrow(() -> {

      var response = controller.atualizarPedido(-1L);

      assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatusCode().value());
      assertEquals(null, response.getBody().getData());
      assertEquals(excecao.getMessage(), response.getBody().getErrorMsg());
    });
  }

  @Test
  void deveLancarExcecaoNotFound() throws Exception {

    var excecao = new NotFoundException();
    doThrow(excecao).when(serviceMock).execute(Mockito.anyLong());

    assertDoesNotThrow(() -> {

      var response = controller.atualizarPedido(100L);

      assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatusCode().value());
      assertEquals(null, response.getBody().getData());
      assertEquals(excecao.getMessage(), response.getBody().getErrorMsg());
    });
  }

  @Test
  void deveLancarExcecaoUnprocessableEntity() throws Exception {

    var excecao = new BusinessRuleException(BusinessRuleMessage.PED_FINALIZADO);
    doThrow(excecao).when(serviceMock).execute(Mockito.anyLong());

    assertDoesNotThrow(() -> {

      var response = controller.atualizarPedido(1L);

      assertEquals(HttpStatus.UNPROCESSABLE_ENTITY.value(), response.getStatusCode().value());
      assertEquals(null, response.getBody().getData());
      assertEquals(excecao.getMessage(), response.getBody().getErrorMsg());
    });
  }

  @Test
  void deveLancarExcecaoBadGateway() throws Exception {

    var excecao = new BadGatewayException("");
    doThrow(excecao).when(serviceMock).execute(Mockito.anyLong());

    assertDoesNotThrow(() -> {

      var response = controller.atualizarPedido(1L);

      assertEquals(HttpStatus.BAD_GATEWAY.value(), response.getStatusCode().value());
      assertEquals(null, response.getBody().getData());
      assertEquals(excecao.getMessage(), response.getBody().getErrorMsg());
    });
  }
}
