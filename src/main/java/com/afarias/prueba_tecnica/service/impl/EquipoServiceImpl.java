package com.afarias.prueba_tecnica.service.impl;

import com.afarias.prueba_tecnica.dto.EquipoRequestDto;
import com.afarias.prueba_tecnica.dto.EquipoResponseDto;
import com.afarias.prueba_tecnica.entity.Equipo;
import com.afarias.prueba_tecnica.exception.EntityNotFoundException;
import com.afarias.prueba_tecnica.mapper.EquipoMapper;
import com.afarias.prueba_tecnica.repository.EquipoRepository;
import com.afarias.prueba_tecnica.service.EquipoService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EquipoServiceImpl implements EquipoService {
  private final EquipoRepository equipoRepository;
  private final EquipoMapper equipoMapper;

  public List<EquipoResponseDto> retornarEquipos() {
    return equipoMapper.toDto(equipoRepository.findAll());
  }

  public EquipoResponseDto obtenerPorId(Long id) {
    return equipoMapper.toDto(
        equipoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(Equipo.class)));
  }

  public List<EquipoResponseDto> buscarEquiposPorNombre(String nombre) {
    return equipoMapper.toDto(equipoRepository.findByNombreContainingIgnoreCase(nombre));
  }

  public EquipoResponseDto crearEquipo(EquipoRequestDto equipo) {
    return equipoMapper.toDto(equipoRepository.save(equipoMapper.toEntity(equipo)));
  }

  public EquipoResponseDto actualizarEquipo(Long id, EquipoRequestDto equipo) {
    Equipo equipoUpdated =
        equipoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(Equipo.class));
    equipoUpdated.setLiga(equipo.getLiga());
    equipoUpdated.setPais(equipo.getPais());
    equipoUpdated.setNombre(equipo.getNombre());
    return equipoMapper.toDto(equipoRepository.save(equipoUpdated));
  }

  public void eliminarEquipo(Long id) {
    if (!equipoRepository.existsById(id)) {
      throw new EntityNotFoundException(Equipo.class);
    }
    equipoRepository.deleteById(id);
  }
}
