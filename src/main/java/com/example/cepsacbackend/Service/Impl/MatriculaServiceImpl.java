package com.example.cepsacbackend.Service.Impl;

import com.example.cepsacbackend.Dto.Matricula.MatriculaRequestDTO;
import com.example.cepsacbackend.Dto.Matricula.MatriculaResponseDTO;
import com.example.cepsacbackend.Entity.*;
import com.example.cepsacbackend.Enums.EstadoMatricula;
import com.example.cepsacbackend.Mapper.MatriculaMapper;
import com.example.cepsacbackend.Repository.*;
import com.example.cepsacbackend.Service.MatriculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MatriculaServiceImpl implements MatriculaService {

    @Autowired private MatriculaRepository repomat;
    @Autowired private UsuarioRepository repouser;
    @Autowired private ProgramacionCursoRepository repoprog;
    @Autowired private DescuentoRepository repodesc;
    @Autowired private MatriculaMapper mapper;

    @Override
    public List<MatriculaResponseDTO> listarMatriculas() {
        return mapper.toResponseDTOList(repomat.findAll());
    }

    @Override
    public MatriculaResponseDTO obtenerMatricula(Short idMatricula) {
        Matricula m = repomat.findById(idMatricula)
                .orElseThrow(() -> new RuntimeException("Matrícula no encontrada"));
        return mapper.toResponseDTO(m);
    }

    @Override
    @Transactional
    public MatriculaResponseDTO crearMatricula(MatriculaRequestDTO dto) {
        Matricula m = new Matricula();
        m.setAlumno(repouser.findById(dto.getIdAlumno())
                .orElseThrow(() -> new RuntimeException("Alumno no encontrado")));
        m.setUsuarioRegistro(repouser.findById(dto.getIdUsuarioRegistro())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado")));
        m.setProgramacionCurso(repoprog.findById(dto.getIdProgramacionCurso())
                .orElseThrow(() -> new RuntimeException("Curso no encontrado")));
        if (dto.getIdDescuento() != null) {
            m.setDescuento(repodesc.findById(dto.getIdDescuento())
                    .orElseThrow(() -> new RuntimeException("Descuento no encontrado")));
        }
        m.setEstado(dto.getEstado() != null ? dto.getEstado() : EstadoMatricula.pendiente);
        m.setMonto(dto.getMonto());
        Matricula guardada = repomat.save(m);
        return mapper.toResponseDTO(guardada);
    }

    @Override
    @Transactional
    public void eliminarMatricula(Short idMatricula) {
        if (!repomat.existsById(idMatricula)) {
            throw new RuntimeException("Matrícula no encontrada");
        }
        repomat.deleteById(idMatricula);
    }

    @Override
    public MatriculaResponseDTO actualizarMatricula(MatriculaRequestDTO dto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actualizarMatricula'");
    }
}
