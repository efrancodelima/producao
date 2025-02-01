package br.com.fiap.soat.controller;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class Home {

  @GetMapping
  @Hidden
  public String showHome() {
    return "Tech Challenge Fase 4: microsserviço de PRODUCAO rodando!<br><br>"
        + "Link para a API: <a href=\"/swagger-ui/index.html\">Swagger UI</a>";
  }
}
