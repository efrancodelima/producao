package br.com.fiap.soat.service.provider;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doReturn;

import br.com.fiap.soat.entity.RegistroProducaoJpa;
import br.com.fiap.soat.exception.BusinessRuleException;
import br.com.fiap.soat.exception.messages.BusinessRuleMessage;
import br.com.fiap.soat.repository.RegistroProducaoRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

class ReceberPedidoServiceTest {

  AutoCloseable closeable;
  ReceberPedidoService service;

  @Mock
  RegistroProducaoRepository repositoryMock;

  @BeforeEach
  void setup() {
    closeable = MockitoAnnotations.openMocks(this);
    this.service = new ReceberPedidoService(repositoryMock);
  }

  @AfterEach
  void tearDown() throws Exception {
    closeable.close();
  }

  @Test
  void deveReceberPedidoComSucesso() {

    var registroProducao = new RegistroProducaoJpa();

    doReturn(false).when(repositoryMock).existsByNumeroPedido(Mockito.anyLong());
    doReturn(registroProducao).when(repositoryMock).save(Mockito.any());

    assertDoesNotThrow(() -> {
      var response = service.execute(1L);
      assertEquals(registroProducao, response);
    });
  }

  @Test
  void deveLancarExcecaoSePedidoJaFoiRecebido() {

    doReturn(true).when(repositoryMock).existsByNumeroPedido(Mockito.anyLong());

    var excecao = assertThrows(BusinessRuleException.class, () -> {
      service.execute(1L);
    });
 
    assertEquals(excecao.getMessage(), BusinessRuleMessage.PED_RECEBIDO.getMessage());
  }
}
