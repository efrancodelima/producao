package br.com.fiap.soat.service.provider;

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
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

class ReceberPedidoServiceTest {

  AutoCloseable closeable;

  @Mock
  RegistroProducaoRepository repositoryMock;

  @InjectMocks
  ReceberPedidoService service;

  @BeforeEach
  void setup() {
    closeable = MockitoAnnotations.openMocks(this);
  }

  @AfterEach
  void tearDown() throws Exception {
    closeable.close();
  }

  @Test
  void deveReceberPedidoComSucesso() throws Exception {

    // Arrange
    var registroProducao = new RegistroProducaoJpa();

    doReturn(false).when(repositoryMock).existsByNumeroPedido(Mockito.anyLong());
    doReturn(registroProducao).when(repositoryMock).save(Mockito.any());

    // Act
    var response = service.execute(1L);

    // Assert
    assertEquals(registroProducao, response);
  }

  @Test
  void deveLancarExcecaoSePedidoJaFoiRecebido() {

    // Arrange
    doReturn(true).when(repositoryMock).existsByNumeroPedido(Mockito.anyLong());

    // Act and assert
    var excecao = assertThrows(BusinessRuleException.class, () -> {
      service.execute(1L);
    });
 
    assertEquals(excecao.getMessage(), BusinessRuleMessage.PED_RECEBIDO.getMessage());
  }
}
