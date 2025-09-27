package com.example.cepsacbackend.Entity;

import com.example.cepsacbackend.Enums.EstadoMatricula;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Matricula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdMatricula")
    private Short idMatricula;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdProgramacionCurso")
    private ProgramacionCurso programacionCurso;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdAlumno")
    private Usuario alumno;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdUsuario")
    private Usuario usuarioRegistro;

    @Column(name = "FechaMatricula", updatable = false)
    private LocalDateTime fechaMatricula = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    @Column(name = "Estado")
    private EstadoMatricula estado = EstadoMatricula.pendiente;

    @Column(name = "Monto", precision = 10, scale = 2)
    private BigDecimal monto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdDescuento")
    private Descuento descuento;
}
