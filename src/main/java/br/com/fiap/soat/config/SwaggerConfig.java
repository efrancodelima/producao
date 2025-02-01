package br.com.fiap.soat.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.tags.Tag;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

  @Bean
  public OpenAPI customOpenApi() {

    var tagProducao = new Tag().name("Producao")
        .description("Operações relacionadas à produção");

    return new OpenAPI()

        .info(new Info()
            .title("Documentação da API")
            .version("2.0")
            .description("Documentação da API do microsservico de PRODUCAO (visão da cozinha)"
                + "<br>FIAP | Pós-tech | Software Architecture | Tech Challenge | Fase 4"))
            .addTagsItem(tagProducao);
  }
}
