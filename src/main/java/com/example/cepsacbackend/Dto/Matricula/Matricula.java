package com.example.cepsacbackend.Dto.Matricula;

import com.example.cepsacbackend.Enums.EstadoMatricula;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class MatriculaRequestDTO {
    private Integer idProgramacionCurso;
    private Short idAlumno;
    private Short idUsuarioRegistro;
    private EstadoMatricula estado;
    private BigDecimal monto;
    private Short idDescuento; // opcional
}

@Data
public class MatriculaResponseDTO {
    private Short idMatricula;
    private String nombreAlumno;
    private String correoAlumno;
    private String nombreCurso;
    private EstadoMatricula estado;
    private BigDecimal monto;
    private String nombreDescuento;
}
