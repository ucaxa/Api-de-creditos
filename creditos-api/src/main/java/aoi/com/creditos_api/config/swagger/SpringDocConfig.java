package aoi.com.creditos_api.config.swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfig {


    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().info(new Info()
                .title("API de Consulta de Créditos")
                .version("1.0")
                .description("Endpoints de consulta por NFS-e e Crédito."));
    }



 /*   @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("API de Créditos Constituidos")
                .description("Documentação das API e Créditos Constituidos")
                .version("v1.0")
                .contact(new Contact().name("Reinaldo Silva").email("rein.ant@gmail.com"))
                .license(new License().name("Apache 2.0").url("http://springdoc.org")));
    }*/
}
