package br.com.fiap.soat.service.consumer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

import br.com.fiap.soat.controller.wrapper.ResponseWrapper;
import br.com.fiap.soat.dto.PagamentoDto;
import br.com.fiap.soat.exception.BadGatewayException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

class ConsultarPagamentoServiceTest {

  AutoCloseable closeable;
  ParameterizedTypeReference<ResponseWrapper<PagamentoDto>> conversao;

  @Mock
  private RestTemplate restTemplate;

  @InjectMocks
  ConsultarPagamentoService service;

  @BeforeEach
  void setup() {
    closeable = MockitoAnnotations.openMocks(this);
    conversao = new ParameterizedTypeReference<ResponseWrapper<PagamentoDto>>() {};
  }

  @AfterEach
  void tearDown() throws Exception {
    closeable.close();
  }

  @Test
  void deveConsultarPagamentoComSucesso() throws Exception {

    // Arrange
    var pagamentoDto = new PagamentoDto();
    var response = ResponseEntity.ok(new ResponseWrapper<>(pagamentoDto));

    doReturn(response).when(restTemplate)
      .exchange(anyString(), any(HttpMethod.class), any(HttpEntity.class), eq(conversao));

    // Act
    PagamentoDto result = service.execute(1L);

    // Assert
    assertEquals(pagamentoDto, result);
  }

  @Test
  void deveLancarExcecaoSeBodyForNulo() {

    // Arrange
    var response = ResponseEntity.ok(null);

    doReturn(response).when(restTemplate)
      .exchange(anyString(), any(HttpMethod.class), any(HttpEntity.class), eq(conversao));

    // Act and assert
    assertThrows(BadGatewayException.class, () -> {
      service.execute(1L);
    });
  }

  @Test
  void deveLancarExcecaoSeBodyDataForNulo() throws Exception {

    // Arrange
    var response = ResponseEntity.ok(new ResponseWrapper<>(null, null));

    doReturn(response).when(restTemplate)
      .exchange(anyString(), any(HttpMethod.class), any(HttpEntity.class), eq(conversao));

    // Act and assert
    assertThrows(BadGatewayException.class, () -> {
      service.execute(1L);
    });
  }

  @Test
  void deveEncapsularExcecaoRecebida() {

    // Arrange
    var msgExcecao = "Uma mensagem qualquer.";

    doThrow(new RuntimeException(msgExcecao)).when(restTemplate)
      .exchange(anyString(), any(HttpMethod.class), any(HttpEntity.class), eq(conversao));

    // Act and assert
    var excecao = assertThrows(BadGatewayException.class, () -> {
      service.execute(1L);
    });

    assertEquals(msgExcecao, excecao.getMessage());
  }
}
