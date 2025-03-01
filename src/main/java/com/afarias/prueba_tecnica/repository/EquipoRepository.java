package com.afarias.prueba_tecnica.repository;

import com.afarias.prueba_tecnica.entity.Equipo;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipoRepository extends JpaRepository<Equipo, Long> {

  /**
   * Obtiene una lista de equipos filtrados por el atributo nombre.
   *
   * @param nombre Nombre del equipo
   * @return Lista con los equipos que contengan el nombre ignorando mayusculas y minusculas.
   */
  List<Equipo> findByNombreContainingIgnoreCase(String nombre);
}
