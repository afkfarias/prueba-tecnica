package com.afarias.prueba_tecnica.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.afarias.prueba_tecnica.dto.EquipoRequestDto;
import com.afarias.prueba_tecnica.dto.EquipoResponseDto;
import com.afarias.prueba_tecnica.entity.Equipo;
import com.afarias.prueba_tecnica.mapper.EquipoMapper;
import com.afarias.prueba_tecnica.mapper.EquipoMapperImpl;
import com.afarias.prueba_tecnica.repository.EquipoRepository;
import com.afarias.prueba_tecnica.service.impl.EquipoServiceImpl;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class EquipoServiceTest {
  @Mock private EquipoRepository equipoRepository;

  @Spy private EquipoMapper equipoMapper = new EquipoMapperImpl();

  @InjectMocks private EquipoServiceImpl equipoService;

  private Equipo equipo;

  private Equipo equipoCreated;
  private EquipoRequestDto equipoRequest;

  @BeforeEach
  void setup() {
    equipo = new Equipo(1L, "FC Barcelona", "La Liga", "España");
    equipoRequest = new EquipoRequestDto("Real Madrid", "La Liga", "España");
    equipoCreated = new Equipo(2L, "Real Madrid", "La Liga", "España");
  }

  @Test
  void obtenerTodos_happyPath() {
    List<Equipo> equipoList =
        Arrays.asList(equipo, new Equipo(2L, "Real Madrid", "La Liga", "España"));
    when(equipoRepository.findAll()).thenReturn(equipoList);

    List<EquipoResponseDto> resultado = equipoService.retornarEquipos();
    assertEquals(2, resultado.size());
    verify(equipoRepository, times(1)).findAll();
  }

  @Test
  void obtenerFiltradoPorNombre_happyPath() {
    List<Equipo> equipoList =
        Arrays.asList(equipo, new Equipo(2L, "Real Madrid", "La Liga", "España"));
    when(equipoRepository.findByNombreContainingIgnoreCase(anyString())).thenReturn(equipoList);

    List<EquipoResponseDto> resultado = equipoService.buscarEquiposPorNombre("Bayer");
    assertEquals(2, resultado.size());
    verify(equipoRepository, times(1)).findByNombreContainingIgnoreCase("Bayer");
  }

  @Test
  void obtenerPorId_happyPath() {
    when(equipoRepository.findById(1L)).thenReturn(Optional.of(equipo));

    EquipoResponseDto resultado = equipoService.obtenerPorId(1L);
    assertNotNull(resultado);
    assertEquals("FC Barcelona", resultado.getNombre());
    verify(equipoRepository, times(1)).findById(1L);
  }

  @Test
  void obtenerPorId_notFoundException() {
    when(equipoRepository.findById(99L)).thenReturn(Optional.empty());

    Exception exception =
        assertThrows(
            RuntimeException.class,
            () -> {
              equipoService.obtenerPorId(99L);
            });

    assertEquals("Equipo no encontrado", exception.getMessage());
    verify(equipoRepository, times(1)).findById(99L);
  }

  @Test
  void crearEquipo_happyPath() {
    when(equipoRepository.save(any(Equipo.class))).thenReturn(equipoCreated);

    EquipoResponseDto resultado = equipoService.crearEquipo(equipoRequest);

    assertNotNull(resultado);
    assertEquals("Real Madrid", resultado.getNombre());
    verify(equipoRepository, times(1)).save(any(Equipo.class));
  }

  @Test
  void actualizarEquipo_happyPath() {
    when(equipoRepository.findById(2L)).thenReturn(Optional.of(equipoCreated));
    when(equipoRepository.save(any(Equipo.class))).thenReturn(equipoCreated);

    EquipoResponseDto resultado = equipoService.actualizarEquipo(2L, equipoRequest);

    assertNotNull(resultado);
    assertEquals("Real Madrid", resultado.getNombre());
    verify(equipoRepository, times(1)).save(any(Equipo.class));
  }

  @Test
  void actualizarEquipo_notFoundException() {
    when(equipoRepository.findById(99L)).thenReturn(Optional.empty());

    Exception exception =
        assertThrows(
            RuntimeException.class,
            () -> {
              equipoService.actualizarEquipo(99L, equipoRequest);
            });

    assertEquals("Equipo no encontrado", exception.getMessage());
    verify(equipoRepository, times(1)).findById(99L);
  }

  @Test
  void eliminarEquipo_happyPath() {
    when(equipoRepository.existsById(1L)).thenReturn(true);
    doNothing().when(equipoRepository).deleteById(1L);

    equipoService.eliminarEquipo(1L);

    verify(equipoRepository, times(1)).deleteById(1L);
  }

  @Test
  void eliminarEquipo_notFoundException() {
    when(equipoRepository.existsById(99L)).thenReturn(false);

    Exception exception =
        assertThrows(
            RuntimeException.class,
            () -> {
              equipoService.eliminarEquipo(99L);
            });

    assertEquals("Equipo no encontrado", exception.getMessage());
    verify(equipoRepository, times(1)).existsById(99L);
  }
}
