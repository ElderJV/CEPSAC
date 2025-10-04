package com.example.cepsacbackend.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.cepsacbackend.Entity.Pago;

@Repository
public interface PagoRepository extends JpaRepository<Pago, Integer> {

    // Lista todos los pagos asociados a una matrícula reportes
    List<Pago> findByMatriculaIdMatricula(Integer matriculaId);
}
