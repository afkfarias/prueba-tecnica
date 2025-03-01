package com.afarias.prueba_tecnica.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
    info =
        @Info(
            title = "Api de equipos de fútbol",
            version = "1.0",
            description = "Documentación de la API para gestionar equipos de fútbol."))
public class SwaggerConfig {}
