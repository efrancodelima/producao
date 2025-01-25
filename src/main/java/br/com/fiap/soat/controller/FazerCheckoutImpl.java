package br.com.fiap.soat.controller;

import br.com.fiap.soat.controller.contract.FazerCheckout;
import br.com.fiap.soat.controller.wrapper.ResponseWrapper;
import br.com.fiap.soat.dto.PedidoDto;
import br.com.fiap.soat.dto.StatusPedidoDto;
import br.com.fiap.soat.exception.BadGatewayException;
import br.com.fiap.soat.exception.BadRequestException;
import br.com.fiap.soat.exception.NotFoundException;
import br.com.fiap.soat.service.provider.FazerCheckoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador REST para fazer checkout do pedido.
 */
@RestController
@RequestMapping("/pedidos")
public class FazerCheckoutImpl implements FazerCheckout {

  // Atributos
  private final FazerCheckoutService service;

  /**
   * O construtor público da classe.
   *
   * @param service O service para fazer checkout do pedido.
   */
  @Autowired
  public FazerCheckoutImpl(FazerCheckoutService service) {
    this.service = service;
  }

  @Override
  public ResponseWrapper<StatusPedidoDto> fazerCheckout(PedidoDto pedidoDto) {

    try {
      var statusPedidoDto = service.execute(pedidoDto);
      return new ResponseWrapper<>(HttpStatus.CREATED, statusPedidoDto);

    } catch (BadGatewayException e) {
      return new ResponseWrapper<>(HttpStatus.BAD_GATEWAY, e.getMessage());
    
    } catch (BadRequestException e) {
      return new ResponseWrapper<>(HttpStatus.BAD_REQUEST, e.getMessage());
    
    } catch (NotFoundException e) {
      return new ResponseWrapper<>(HttpStatus.NOT_FOUND, e.getMessage());
    }
  }



  // var StatusPedidoDto = 
    
}
