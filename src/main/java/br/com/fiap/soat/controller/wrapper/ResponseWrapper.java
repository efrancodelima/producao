package br.com.fiap.soat.controller.wrapper;

/**
 * Classe para encapsular o retorno dos controllers.
 */
public class ResponseWrapper<T> {

  private T data;
  private String errorMsg;

  public ResponseWrapper(T data) {
    this.data = data;
  }

  public ResponseWrapper(String msgError) {
    this.errorMsg = msgError;
  }

  // Getters e setters
  public T getData() {
    return data;
  }

  public void setData(T data) {
    this.data = data;
  }

  public String getErrorMsg() {
    return errorMsg;
  }

  public void setErrorMsg(String error) {
    this.errorMsg = error;
  }
}
