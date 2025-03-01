package com.afarias.prueba_tecnica.service;

import com.afarias.prueba_tecnica.dto.EquipoRequestDto;
import com.afarias.prueba_tecnica.dto.EquipoResponseDto;
import java.util.List;

public interface EquipoService {

  /**
   * Obtiene una lista de los equipos existentes.
   *
   * @return Lista de EquipoResponseDto con la información de los equipos.
   */
  List<EquipoResponseDto> retornarEquipos();

  /**
   * Obtiene un equipo por ID
   *
   * @param id Identificador del equipo
   * @return DTO con la ifnormación del equipo.
   */
  EquipoResponseDto obtenerPorId(Long id);

  /**
   * Recupera un listado de los equipos filtrados por nombre.
   *
   * @param nombre Texto que debe contener el nombre
   * @return Lista de EquipoResponseDto con la información de los equipos cuyo nombre contenga el
   *     parámetro nombre.
   */
  List<EquipoResponseDto> buscarEquiposPorNombre(String nombre);

  /**
   * Crea un nuevo equipo en la base de datos.
   *
   * @param equipo Datos del equipo a crear.
   * @return EquipoResponseDto con la información del equipo creado.
   */
  EquipoResponseDto crearEquipo(EquipoRequestDto equipo);

  /**
   * Actualiza los datos de un equipo existente.
   *
   * @param id Identificador del equipo a actualizar
   * @param equipo Nuevos datos del equipo.
   * @return EquipoResponseDto con la ifnromación actualizada del equipo.
   */
  EquipoResponseDto actualizarEquipo(Long id, EquipoRequestDto equipo);

  /**
   * Elimina un equipo de la base de datos
   *
   * @param id Identificador del equipo a eliminar.
   */
  void eliminarEquipo(Long id);
}
