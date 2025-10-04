package com.example.cepsacbackend.Dto.Matricula;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public record MatriculaCreateDTO(
    @NotNull Integer idProgramacionCurso,
    @NotNull Integer idAlumno,
    BigDecimal montoBase,
    BigDecimal montoDescontado,
    BigDecimal monto,
    Short idDescuento
) {}
