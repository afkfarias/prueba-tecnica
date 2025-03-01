package com.afarias.prueba_tecnica.controller;

import com.afarias.prueba_tecnica.dto.EquipoRequestDto;
import com.afarias.prueba_tecnica.dto.EquipoResponseDto;
import com.afarias.prueba_tecnica.service.EquipoService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/equipos")
@RequiredArgsConstructor
public class EquipoController {

  private final EquipoService equipoService;

  @GetMapping
  public List<EquipoResponseDto> obtenerTodos() {
    return equipoService.retornarEquipos();
  }

  @GetMapping("/{id}")
  public ResponseEntity<EquipoResponseDto> obtenerPorId(@PathVariable Long id) {
    return ResponseEntity.ok(equipoService.obtenerPorId(id));
  }

  @GetMapping("/buscar")
  public List<EquipoResponseDto> buscarEquipos(@RequestParam String nombre) {
    return equipoService.buscarEquiposPorNombre(nombre);
  }

  @PostMapping
  public ResponseEntity<EquipoResponseDto> crearEquipo(
      @Valid @RequestBody EquipoRequestDto equipo) {
    return ResponseEntity.status(HttpStatus.CREATED).body(equipoService.crearEquipo(equipo));
  }

  @PutMapping("/{id}")
  public ResponseEntity<EquipoResponseDto> actualizarEquipo(
      @PathVariable Long id, @Valid @RequestBody EquipoRequestDto equipo) {
    return ResponseEntity.ok(equipoService.actualizarEquipo(id, equipo));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<EquipoResponseDto> eliminarEquipo(@PathVariable Long id) {
    equipoService.eliminarEquipo(id);
    return ResponseEntity.noContent().build();
  }
}
