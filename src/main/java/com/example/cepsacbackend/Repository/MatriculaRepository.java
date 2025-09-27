package com.example.cepsacbackend.Repository;

import com.example.cepsacbackend.Entity.Matricula;
import com.example.cepsacbackend.Enums.EstadoMatricula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatriculaRepository extends JpaRepository<Matricula, Short> {
    List<Matricula> findByEstado(EstadoMatricula estado);
    List<Matricula> findByAlumno_IdUsuario(Short idAlumno);
}
