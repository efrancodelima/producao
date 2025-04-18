package br.com.fiap.soat.service.consumer;

import br.com.fiap.soat.controller.wrapper.ResponseWrapper;
import br.com.fiap.soat.dto.PagamentoDto;
import br.com.fiap.soat.exception.BadGatewayException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * Service para consultar o status do pagamento.
 * Essa consulta é feita quando o status do pedido passa de "recebido" para "em produção".
 */
@Component
public class ConsultarPagamentoService {

  private final RestTemplate restTemplate;
  private final String baseUrl;
    
  @Autowired
  public ConsultarPagamentoService(RestTemplate restTemplate,
      @Value("${pagamento.service.url}") String baseUrl) {
    this.restTemplate = restTemplate;
    this.baseUrl = baseUrl;
  }
  
  public PagamentoDto execute(Long numeroPedido) throws BadGatewayException {
    
    String url = baseUrl + "pagamento/consultar/" + numeroPedido;

    try {
      var response = restTemplate.exchange(
          url,
          HttpMethod.GET,
          new HttpEntity<>(""),
          new ParameterizedTypeReference<ResponseWrapper<PagamentoDto>>() {});
      
      var responseBody = response.getBody();

      if (responseBody == null || responseBody.getData() == null) {
        throw new BadGatewayException("Erro na comunicação com o sistema de pagamento.");
      } else {
        return responseBody.getData();
      }

    } catch (Exception e) {
      throw new BadGatewayException(e.getMessage());
    }
  }
}
