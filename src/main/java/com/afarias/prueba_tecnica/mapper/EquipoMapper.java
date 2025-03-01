package com.afarias.prueba_tecnica.mapper;

import com.afarias.prueba_tecnica.dto.EquipoRequestDto;
import com.afarias.prueba_tecnica.dto.EquipoResponseDto;
import com.afarias.prueba_tecnica.entity.Equipo;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EquipoMapper {

  EquipoResponseDto toDto(Equipo equipoEntity);

  Equipo toEntity(EquipoRequestDto equipoRequestDto);

  List<EquipoResponseDto> toDto(List<Equipo> listEquipos);
}
