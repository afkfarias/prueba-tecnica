package com.afarias.prueba_tecnica.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
    info =
        @Info(
            title = "Api de equipos de fútbol",
            version = "1.0",
            description = "Documentación de la API para gestionar equipos de fútbol."),
    security = @SecurityRequirement(name = "BearerAuth"))
@SecurityScheme(
    name = "BearerAuth",
    scheme = "bearer",
    type = io.swagger.v3.oas.annotations.enums.SecuritySchemeType.HTTP,
    bearerFormat = "JWT")
public class SwaggerConfig {}
