package br.com.fiap.soat.service.provider;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doReturn;

import br.com.fiap.soat.dto.PagamentoDto;
import br.com.fiap.soat.entity.RegistroProducaoJpa;
import br.com.fiap.soat.entity.StatusPedido;
import br.com.fiap.soat.exception.BusinessRuleException;
import br.com.fiap.soat.exception.messages.BusinessRuleMessage;
import br.com.fiap.soat.repository.RegistroProducaoRepository;
import br.com.fiap.soat.service.consumer.ConsultarPagamentoService;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

class AtualizarStatusPedidoServiceTest {

  AutoCloseable closeable;

  @Mock
  RegistroProducaoRepository repositoryMock;

  @Mock
  ConsultarPagamentoService servicePagamentoMock;

  @InjectMocks
  AtualizarStatusPedidoService service;

  @BeforeEach
  void setup() {
    closeable = MockitoAnnotations.openMocks(this);
  }

  @AfterEach
  void tearDown() throws Exception {
    closeable.close();
  }

  @Test
  void deveAtualizarStatusPedidoComSucesso() throws Exception {

    // Arrange
    var registroProducao = getRegistroProducao(StatusPedido.EM_PREPARACAO);

    doReturn(Optional.of(registroProducao)).when(repositoryMock)
        .findTopByNumeroPedidoOrderByTimestampDesc(Mockito.anyLong());

    doAnswer(invocation -> invocation.getArgument(0))
        .when(repositoryMock)
        .save(any(RegistroProducaoJpa.class));

    // Act
    var response = service.execute(1L);

    // Assert
    assertEquals(StatusPedido.PRONTO, response.getStatus());
  }

  @Test
  void deveLancarExcecaoSePedidoRecebidoNaoPago() throws Exception {

    // Arrange (repository)
    var registroProducao = getRegistroProducao(StatusPedido.RECEBIDO);

    doReturn(Optional.of(registroProducao)).when(repositoryMock)
        .findTopByNumeroPedidoOrderByTimestampDesc(Mockito.anyLong());

    doAnswer(invocation -> invocation.getArgument(0))
        .when(repositoryMock)
        .save(any(RegistroProducaoJpa.class));

    // Arrange (pagamento)
    var pagamento = getRegistroPagamento(false);
    doReturn(pagamento).when(servicePagamentoMock).execute(Mockito.anyLong());

    // Act and assert
    var excecao = assertThrows(BusinessRuleException.class, () -> {
      service.execute(1L);
    });
    
    assertEquals(excecao.getMessage(), BusinessRuleMessage.PAG_PENDENTE.getMessage());
  }

  @Test
  void deveLancarExcecaoSePedidoJaFoiFinalizado() {

    // Arrange (repository)
    var registroProducao = getRegistroProducao(StatusPedido.FINALIZADO);

    doReturn(Optional.of(registroProducao)).when(repositoryMock)
        .findTopByNumeroPedidoOrderByTimestampDesc(Mockito.anyLong());

    // Act and assert
    var excecao = assertThrows(BusinessRuleException.class, () -> {
      service.execute(1L);
    });
    
    assertEquals(excecao.getMessage(), BusinessRuleMessage.PED_FINALIZADO.getMessage());
  }

  private RegistroProducaoJpa getRegistroProducao(StatusPedido status) {
    return new RegistroProducaoJpa(1L, status, LocalDateTime.now().minusMinutes(10));
  }

  private PagamentoDto getRegistroPagamento(boolean pago) {

    var status = "AGUARDANDO_PAGAMENTO";

    if (pago) {
      status = "APROVADO";
    }

    return new PagamentoDto(1L, 1L, BigDecimal.valueOf(55), 
        status, LocalDateTime.now().minusMinutes(20));
  }
}
