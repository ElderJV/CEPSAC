package com.example.cepsacbackend.Mapper;

import com.example.cepsacbackend.Dto.Matricula.MatriculaCreateDTO;
import com.example.cepsacbackend.Dto.Matricula.MatriculaResponseDTO;
import com.example.cepsacbackend.Entity.Matricula;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MatriculaMapper {

    // crear entidad desde dto de creación, fks se setean en service
    @Mapping(target = "idMatricula", ignore = true)
    @Mapping(target = "programacionCurso", ignore = true)
    @Mapping(target = "alumno", ignore = true)
    @Mapping(target = "administradorAprobador", ignore = true)
    @Mapping(target = "fechaMatricula", ignore = true)
    @Mapping(target = "estado", ignore = true)
    // fecha de matrícula y estado mantienen valores por defecto de la entidad
    Matricula toEntity(MatriculaCreateDTO dto);

    // entidad a responsedto con fks
    @Mapping(source = "programacionCurso.idProgramacionCurso", target = "idProgramacionCurso")
    @Mapping(source = "alumno.idUsuario", target = "idAlumno")
    @Mapping(source = "administradorAprobador.idUsuario", target = "idAdministradorAprobador")
    MatriculaResponseDTO toResponseDTO(Matricula entity);

    List<MatriculaResponseDTO> toResponseDTOList(List<Matricula> entities);
}
