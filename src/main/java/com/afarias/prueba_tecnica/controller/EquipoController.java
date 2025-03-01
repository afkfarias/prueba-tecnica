package com.afarias.prueba_tecnica.controller;

import com.afarias.prueba_tecnica.dto.EquipoRequestDto;
import com.afarias.prueba_tecnica.dto.EquipoResponseDto;
import com.afarias.prueba_tecnica.dto.ErrorResponse;
import com.afarias.prueba_tecnica.service.EquipoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Equipos", description = "Endpoints para gestionar equipos de fútbol")
public class EquipoController {

  private final EquipoService equipoService;

  @Operation(
      summary = "Obtener equipos",
      description = "Devuelve una lista de todos los equipos existentes.")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "Listado de equipos recuperada exitosamente.")
  })
  @GetMapping
  public List<EquipoResponseDto> obtenerTodos() {
    return equipoService.retornarEquipos();
  }

  @Operation(
      summary = "Obtener equipo por id",
      description = "Retorna un equipo por su identificador.")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "Equipo encontrado."),
    @ApiResponse(
        responseCode = "404",
        description = "Equipo no encontrado.",
        content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
  })
  @GetMapping("/{id}")
  public ResponseEntity<EquipoResponseDto> obtenerPorId(@PathVariable Long id) {
    return ResponseEntity.ok(equipoService.obtenerPorId(id));
  }

  @Operation(
      summary = "Buscar equipos por su nombre",
      description = "Devuelve una lista de todos los equipos que contengan el nombre.")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "Listado de equipos recuperada exitosamente.")
  })
  @GetMapping("/buscar")
  public List<EquipoResponseDto> buscarEquipos(@RequestParam String nombre) {
    return equipoService.buscarEquiposPorNombre(nombre);
  }

  @Operation(summary = "Crear un nuevo equipo", description = "Registra un nuevo equipo.")
  @ApiResponses({
    @ApiResponse(responseCode = "201", description = "Equipo creado exitosamente."),
    @ApiResponse(
        responseCode = "400",
        description = "Solicitud inválida o datos incorrectos.",
        content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
  })
  @PostMapping
  public ResponseEntity<EquipoResponseDto> crearEquipo(
      @Valid @RequestBody EquipoRequestDto equipo) {
    return ResponseEntity.status(HttpStatus.CREATED).body(equipoService.crearEquipo(equipo));
  }

  @Operation(
      summary = "Actualizar equipo",
      description = "Modifica los datos de un equipo existente.")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "Equipo modificado exitosamente."),
    @ApiResponse(
        responseCode = "400",
        description = "Solicitud inválida o datos incorrectos.",
        content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
    @ApiResponse(
        responseCode = "404",
        description = "Equipo no encontrado.",
        content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
  })
  @PutMapping("/{id}")
  public ResponseEntity<EquipoResponseDto> actualizarEquipo(
      @PathVariable Long id, @Valid @RequestBody EquipoRequestDto equipo) {
    return ResponseEntity.ok(equipoService.actualizarEquipo(id, equipo));
  }

  @Operation(summary = "Eliminar equipo", description = "Elimina un equipo por su identificador.")
  @ApiResponses({
    @ApiResponse(responseCode = "204", description = "Equipo eliminado exitosamente."),
    @ApiResponse(
        responseCode = "404",
        description = "Equipo no encontrado.",
        content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
  })
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> eliminarEquipo(@PathVariable Long id) {
    equipoService.eliminarEquipo(id);
    return ResponseEntity.noContent().build();
  }
}
