package com.afarias.prueba_tecnica.config;

import com.afarias.prueba_tecnica.dto.EquipoRequestDto;
import com.afarias.prueba_tecnica.dto.EquipoResponseDto;
import com.afarias.prueba_tecnica.entity.Equipo;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class EquipoTestConfig {

  public static final Long EQUIPO_ID_VALID = 1L;
  public static final Long EQUIPO_ID_NO_VALID = 99L;

  public static final String EQUIPO_FILTRO_NOMBRE = "Bayern";

  public static final String EQUIPO_NOMBRE = "FC Barcelona";
  public static final String EQUIPO_LIGA = "La Liga";
  public static final String EQUIPO_PAIS = "España";

  public static final String EQUIPO_NOMBRE_FILTRADO = "Bayern Munich";
  public static final String EQUIPO_LIGA_FILTRADO = "Bundesliga";
  public static final String EQUIPO_PAIS_FILTRADO = "Alemania";

  @Bean
  public Equipo expectedEquipoFromDB() {
    return Equipo.builder()
        .id(EQUIPO_ID_VALID)
        .nombre(EQUIPO_NOMBRE)
        .pais(EQUIPO_PAIS)
        .liga(EQUIPO_LIGA)
        .build();
  }

  @Bean
  public EquipoResponseDto expectedEquipo() {
    return EquipoResponseDto.builder()
        .id(EQUIPO_ID_VALID)
        .nombre(EQUIPO_NOMBRE)
        .pais(EQUIPO_PAIS)
        .liga(EQUIPO_LIGA)
        .build();
  }

  @Bean
  public EquipoResponseDto expectedEquipoFiltrado() {
    return EquipoResponseDto.builder()
        .id(EQUIPO_ID_VALID)
        .nombre(EQUIPO_NOMBRE_FILTRADO)
        .pais(EQUIPO_PAIS_FILTRADO)
        .liga(EQUIPO_LIGA_FILTRADO)
        .build();
  }

  @Bean
  public EquipoRequestDto expectedEquipoRequest() {
    return new EquipoRequestDto(EQUIPO_NOMBRE, EQUIPO_LIGA, EQUIPO_PAIS);
  }
}
