package br.com.fiap.soat.controller.wrapper;

import java.util.Objects;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

/**
 * Classe para encapsular o objeto de retorno da API,
 * no caso da requisição ser bem sucedida, ou o erro, no caso constrário.
 */
public class ResponseWrapper<T> extends ResponseEntity<T> {

  private final String errorMsg;

  /**
   * O construtor da resposta bem sucedida.
   *
   * @param body O objeto a ser inserido no corpo da resposta.
   */
  public ResponseWrapper(HttpStatusCode status, T body) {
    super(body, status);
    this.errorMsg = null;
  }

  /**
   * O construtor da resposta mal sucedida.
   *
   * @param errorMsg A mensagem de erro a ser inserida no corpo da resposta.
   */
  public ResponseWrapper(HttpStatusCode status, String errorMsg) {
    super(status);
    this.errorMsg = errorMsg;
  }

  public String getErrorMsg() {
    return errorMsg;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }
    ResponseWrapper<?> that = (ResponseWrapper<?>) o;
    return Objects.equals(errorMsg, that.errorMsg);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), errorMsg);
  }
}
