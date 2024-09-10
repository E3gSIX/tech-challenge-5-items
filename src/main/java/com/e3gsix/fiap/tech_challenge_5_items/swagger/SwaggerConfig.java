package com.e3gsix.fiap.tech_challenge_5_items.swagger;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Value;

@OpenAPIDefinition(
        info = @Info(
                title = "Itens",
                version = "1.0",
                description = "Microsserviço dedicado à gestão de itens, tendo em vista detalhes como nome, quantidade" +
                        "quantidade em estoque e preço.",
                contact = @Contact(name = "E3gSIX", url = "https://github.com/E3gSIX")
        ),
        security = {
                @SecurityRequirement(name = "bearerAuth")
        }
)
@SecurityScheme(
        name = "bearerAuth",
        description = "JWT authentication",
        scheme = "bearer",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER
)
public class SwaggerConfig {
    @Value("${springdoc.swagger-ui.path}")
    public static final String URL_SWAGGER = "/doc";

    public static final String URL_SWAGGER_DEFAULT = "/swagger";
    public static final String URL_SWAGGER_API = "/v3/api-docs";
}