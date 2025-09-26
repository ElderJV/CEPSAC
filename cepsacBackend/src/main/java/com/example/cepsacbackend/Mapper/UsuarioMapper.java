package com.example.cepsacbackend.Mapper;

import com.example.cepsacbackend.Entity.Usuario;
import com.example.cepsacbackend.Dto.Usuario.UsuarioResponseDTO;
import com.example.cepsacbackend.Dto.Usuario.UsuarioRequestDTO;
import com.example.cepsacbackend.Dto.Usuario.UsuarioUpdateDTO;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import java.util.List;

// permite inyeccion con @Autowired
@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    // Mapea del request DTO a Entity
    @Mapping(target = "pais", ignore = true)
    @Mapping(target = "tipoIdentificacion", ignore = true)
    Usuario toEntity(UsuarioRequestDTO dto);

    // Para actualización (PUT)
    @Mapping(target = "idUsuario", ignore = true)
    @Mapping(target = "pais", ignore = true)
    @Mapping(target = "tipoIdentificacion", ignore = true)
    void updateEntityFromRequestDTO(UsuarioRequestDTO dto, @MappingTarget Usuario entity);

    // Para actualización parcial (PATCH)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "idUsuario", ignore = true)
    @Mapping(target = "pais", ignore = true)
    @Mapping(target = "tipoIdentificacion", ignore = true)
    void updateEntityFromUpdateDTO(UsuarioUpdateDTO dto, @MappingTarget Usuario entity);

    // Entity -> ResponseDTO
    @Mapping(source = "pais.idPais", target = "idPais")
    @Mapping(source = "pais.nombre", target = "nombrePais")
    @Mapping(source = "tipoIdentificacion.idTipoIdentificacion", target = "idTipoIdentificacion")
    @Mapping(source = "tipoIdentificacion.nombre", target = "nombreTipoIdentificacion")
    UsuarioResponseDTO toResponseDTO(Usuario usuario);

    List<UsuarioResponseDTO> toResponseDTOList(List<Usuario> usuarios);
}
