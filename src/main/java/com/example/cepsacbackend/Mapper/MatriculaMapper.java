package com.example.cepsacbackend.Mapper;

import com.example.cepsacbackend.Entity.Matricula;
import com.example.cepsacbackend.Dto.Matricula.MatriculaResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MatriculaMapper {

    @Mapping(source = "alumno.nombre", target = "nombreAlumno")
    @Mapping(source = "alumno.correo", target = "correoAlumno")
    @Mapping(source = "programacionCurso.curso.nombre", target = "nombreCurso")
    @Mapping(source = "descuento.nombre", target = "nombreDescuento")
    MatriculaResponseDTO toResponseDTO(Matricula matricula);

    List<MatriculaResponseDTO> toResponseDTOList(List<Matricula> matriculas);
}
