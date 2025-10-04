package com.example.cepsacbackend.Service.Impl;

import com.example.cepsacbackend.Dto.Matricula.MatriculaCreateDTO;
import com.example.cepsacbackend.Entity.Descuento;
import com.example.cepsacbackend.Entity.Matricula;
import com.example.cepsacbackend.Entity.ProgramacionCurso;
import com.example.cepsacbackend.Entity.Usuario;
import com.example.cepsacbackend.Enums.EstadoMatricula;
import com.example.cepsacbackend.Enums.Rol;
import com.example.cepsacbackend.Enums.TipoDescuento;
import com.example.cepsacbackend.Mapper.MatriculaMapper;
import com.example.cepsacbackend.Repository.DescuentoRepository;
import com.example.cepsacbackend.Repository.MatriculaRepository;
import com.example.cepsacbackend.Repository.ProgramacionCursoRepository;
import com.example.cepsacbackend.Repository.UsuarioRepository;
import com.example.cepsacbackend.Service.MatriculaService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class MatriculaServiceImpl implements MatriculaService {

    private final MatriculaRepository matriculaRepository;
    private final UsuarioRepository usuarioRepository;
    private final ProgramacionCursoRepository programacionCursoRepository;
    private final DescuentoRepository descuentoRepository;
    private final MatriculaMapper matriculaMapper;

    @Override
    @Transactional
    public Matricula crearMatricula(MatriculaCreateDTO dto) {
        // validar alumno
        Usuario alumno = usuarioRepository.findById(dto.idAlumno())
                .orElseThrow(() -> new RuntimeException("Alumno no encontrado: " + dto.idAlumno()));
        if (alumno.getRol() != Rol.ALUMNO) {
            throw new RuntimeException("El usuario no tiene rol ALUMNO");
        }
        // cargar programación y obtener precio base
        ProgramacionCurso programacion = programacionCursoRepository.findById(dto.idProgramacionCurso())
                .orElseThrow(() -> new RuntimeException("Programación de curso no encontrada: " + dto.idProgramacionCurso()));
        BigDecimal montoBase = programacion.getMonto();
        if (montoBase == null) {
            montoBase = BigDecimal.ZERO; // si no hay monto configurado aún
        }

        BigDecimal montoDescontado = BigDecimal.ZERO;
        Short idDesc = dto.idDescuento();
        if (idDesc != null) {
            Descuento descuento = descuentoRepository.findById(idDesc)
                    .orElseThrow(() -> new RuntimeException("Descuento no encontrado: " + idDesc));
            if (Boolean.TRUE.equals(descuento.getVigente()) && estaVigenteHoy(descuento.getFechaInicio(), descuento.getFechaFin())) {
                BigDecimal valor = descuento.getValor() != null ? descuento.getValor() : BigDecimal.ZERO;
                if (descuento.getTipoDescuento() == TipoDescuento.PORCENTAJE) {
                    BigDecimal porcentaje = valor.divide(BigDecimal.valueOf(100), 6, RoundingMode.HALF_UP);
                    montoDescontado = montoBase.multiply(porcentaje);
                } else {
                    montoDescontado = valor;
                }
                // limitar al monto base y redondear a 2 decimales
                if (montoDescontado.compareTo(montoBase) > 0) {
                    montoDescontado = montoBase;
                }
                montoDescontado = montoDescontado.setScale(2, RoundingMode.HALF_UP);
            }
        }

        BigDecimal montoFinal = montoBase.subtract(montoDescontado);
        if (montoFinal.compareTo(BigDecimal.ZERO) < 0) {
            montoFinal = BigDecimal.ZERO;
        }
        montoFinal = montoFinal.setScale(2, RoundingMode.HALF_UP);

        // mapeamos campos con el mapper
        Matricula m = matriculaMapper.toEntity(dto);
        // set relaciones y montos calculados
        m.setProgramacionCurso(programacion);
        m.setAlumno(alumno);
        m.setMontoBase(montoBase.setScale(2, RoundingMode.HALF_UP));
        m.setMontoDescontado(montoDescontado);
        m.setMonto(montoFinal);

        return matriculaRepository.save(m);
    }

    private boolean estaVigenteHoy(LocalDate inicio, LocalDate fin) {
        LocalDate hoy = LocalDate.now();
        boolean okInicio = (inicio == null) || !hoy.isBefore(inicio);
        boolean okFin = (fin == null) || !hoy.isAfter(fin);
        return okInicio && okFin;
    }

    @Override
    @Transactional
    public Matricula aprobarMatricula(Integer idMatricula, Integer idAdministrador) {
        Matricula m = matriculaRepository.findById(idMatricula)
                .orElseThrow(() -> new RuntimeException("Matrícula no encontrada: " + idMatricula));

        Usuario admin = usuarioRepository.findById(idAdministrador)
                .orElseThrow(() -> new RuntimeException("Administrador no encontrado: " + idAdministrador));
        if (admin.getRol() != Rol.ADMINISTRADOR) {
            throw new RuntimeException("El usuario aprobador no tiene rol ADMINISTRADOR");
        }

        m.setAdministradorAprobador(admin);
        // seteamos estado
        m.setEstado(EstadoMatricula.EN_PROCESO);
        return matriculaRepository.save(m);
    }
}
