package com.afarias.prueba_tecnica.controller;

import static com.afarias.prueba_tecnica.config.EquipoTestConfig.EQUIPO_FILTRO_NOMBRE;
import static com.afarias.prueba_tecnica.config.EquipoTestConfig.EQUIPO_ID_NO_VALID;
import static com.afarias.prueba_tecnica.config.EquipoTestConfig.EQUIPO_ID_VALID;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.afarias.prueba_tecnica.config.EquipoTestConfig;
import com.afarias.prueba_tecnica.dto.EquipoRequestDto;
import com.afarias.prueba_tecnica.dto.EquipoResponseDto;
import com.afarias.prueba_tecnica.entity.Equipo;
import com.afarias.prueba_tecnica.exception.EntityNotFoundException;
import com.afarias.prueba_tecnica.service.EquipoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(controllers = EquipoController.class)
@Import(EquipoTestConfig.class)
public class EquipoControllerTest {

  @Autowired private MockMvc mockMvc;

  @Autowired private ObjectMapper objectMapper;

  @MockitoBean private EquipoService equipoService;

  @Autowired private EquipoResponseDto expectedEquipo;

  @Autowired private EquipoResponseDto expectedEquipoFiltrado;

  @Autowired private EquipoRequestDto expectedEquipoRequest;

  @Test
  @WithMockUser
  void obtenerTodosLosEquipos_happyPath() throws Exception {
    List<EquipoResponseDto> equipoResponseDtos = List.of(expectedEquipo);
    when(equipoService.retornarEquipos()).thenReturn(equipoResponseDtos);

    mockMvc
        .perform(get("/equipos").with(csrf()))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(1)));

    verify(equipoService, times(1)).retornarEquipos();
  }

  @Test
  @WithMockUser
  void obtenerPorId_happyPath() throws Exception {
    when(equipoService.obtenerPorId(EQUIPO_ID_VALID)).thenReturn(expectedEquipo);

    mockMvc
        .perform(get("/equipos/{id}", EQUIPO_ID_VALID).with(csrf()))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.nombre").value("FC Barcelona"));

    verify(equipoService, times(1)).obtenerPorId(EQUIPO_ID_VALID);
  }

  @Test
  @WithMockUser
  void obtenerPorId_notFound() throws Exception {
    when(equipoService.obtenerPorId(EQUIPO_ID_NO_VALID))
        .thenThrow(new EntityNotFoundException(Equipo.class));

    mockMvc
        .perform(get("/equipos/{id}", EQUIPO_ID_NO_VALID).with(csrf()))
        .andExpect(status().isNotFound())
        .andExpect(jsonPath("$.mensaje").value("Equipo no encontrado"));

    verify(equipoService, times(1)).obtenerPorId(EQUIPO_ID_NO_VALID);
  }

  @Test
  @WithMockUser
  void obtenerPorNombre_happyPath() throws Exception {
    when(equipoService.buscarEquiposPorNombre("Bayern"))
        .thenReturn(List.of(expectedEquipoFiltrado));

    mockMvc
        .perform(get("/equipos/buscar").param("nombre", EQUIPO_FILTRO_NOMBRE).with(csrf()))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].nombre").value("Bayern Munich"));

    verify(equipoService, times(1)).buscarEquiposPorNombre("Bayern");
  }

  @Test
  @WithMockUser()
  void crearEquipo_happyPath() throws Exception {
    when(equipoService.crearEquipo(any(EquipoRequestDto.class))).thenReturn(expectedEquipo);

    mockMvc
        .perform(
            post("/equipos")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(expectedEquipoRequest)))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.nombre").value("FC Barcelona"));

    verify(equipoService, times(1)).crearEquipo(any(EquipoRequestDto.class));
  }

  @Test
  @WithMockUser()
  void crearEquipo_badRequest() throws Exception {
    mockMvc
        .perform(
            post("/equipos")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new EquipoRequestDto())))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.mensaje").value("La solicitud es inválida"));
  }

  @Test
  @WithMockUser()
  void crearEquipo_EmptyRequest() throws Exception {
    mockMvc
        .perform(post("/equipos").with(csrf()).contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.mensaje").value("La solicitud es inválida"));
  }

  @Test
  @WithMockUser
  void actualizarEquipo_happyPath() throws Exception {
    when(equipoService.actualizarEquipo(eq(EQUIPO_ID_VALID), any(EquipoRequestDto.class)))
        .thenReturn(expectedEquipo);

    mockMvc
        .perform(
            put("/equipos/{id}", EQUIPO_ID_VALID)
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(expectedEquipoRequest)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.nombre").value("FC Barcelona"));

    verify(equipoService, times(1))
        .actualizarEquipo(eq(EQUIPO_ID_VALID), any(EquipoRequestDto.class));
  }

  @Test
  @WithMockUser
  void eliminarEquipo_happyPath() throws Exception {
    doNothing().when(equipoService).eliminarEquipo(1L);
    mockMvc.perform(delete("/equipos/{id}", 1L).with(csrf())).andExpect(status().isNoContent());

    verify(equipoService, times(1)).eliminarEquipo(1L);
  }
}
