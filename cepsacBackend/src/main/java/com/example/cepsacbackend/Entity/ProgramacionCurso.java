package com.example.cepsacbackend.Entity;

import com.example.cepsacbackend.Enums.ModalidadCurso;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "ProgramacionCurso")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProgramacionCurso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdProgramacionCurso")
    private Integer idProgramacionCurso;

    @Enumerated(EnumType.STRING)
    @Column(name = "Modalidad")
    private ModalidadCurso modalidad;

    @Column(name = "DuracionCurso", precision = 6, scale = 2)
    private BigDecimal duracionCurso;

    @Column(name = "HorasSemanales", precision = 6, scale = 2)
    private BigDecimal horasSemanales;

    @Column(name = "FechaInicio")
    private LocalDate fechaInicio;

    @Column(name = "FechaFin")
    private LocalDate fechaFin;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdUsuario")
    private Usuario usuario;

    // precio base del curso programado
    @Column(name = "Monto", precision = 10, scale = 2)
    private BigDecimal monto;

    // entidad cursodiplomado pendiente
    /*
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdCursoDiplomado")
    private CursoDiplomado cursoDiplomado;
    */
}