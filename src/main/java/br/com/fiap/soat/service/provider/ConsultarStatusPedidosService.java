package br.com.fiap.soat.service.provider;

import br.com.fiap.soat.entity.RegistroProducaoJpa;
import br.com.fiap.soat.exception.BadRequestException;
import br.com.fiap.soat.repository.RegistroProducaoRepository;
import br.com.fiap.soat.validator.NumeroPedidoValidator;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Service para receber o pedido.
 */
@Component
public class ConsultarStatusPedidosService {

  private final RegistroProducaoRepository repository;

  @Autowired
  public ConsultarStatusPedidosService(RegistroProducaoRepository repository) {
    this.repository = repository;
  }
  
  public List<RegistroProducaoJpa> execute(List<Long> numerosPedidos) throws BadRequestException {

    NumeroPedidoValidator.validar(numerosPedidos);

    var response = new ArrayList<RegistroProducaoJpa>();

    for (var numero : numerosPedidos) {

      var statusOpt = repository.findTopByNumeroPedidoOrderByTimestampDesc(numero);

      if (statusOpt.isPresent()) {
        response.add(statusOpt.get());
      } else {
        response.add(null);
      }
    }

    return response;
  }
}
