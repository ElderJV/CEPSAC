package com.example.cepsacbackend.Mapper;

import com.example.cepsacbackend.Entity.Usuario;
import com.example.cepsacbackend.Dto.Usuario.UsuarioResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;

// permite inyeccion con @Autowired
@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    @Mapping(source = "pais.idPais", target = "idPais")
    @Mapping(source = "pais.nombre", target = "nombrePais")
    @Mapping(source = "tipoIdentificacion.idTipoIdentificacion", target = "idTipoIdentificacion")
    @Mapping(source = "tipoIdentificacion.nombre", target = "nombreTipoIdentificacion")
    UsuarioResponseDTO toResponseDTO(Usuario usuario);

    List<UsuarioResponseDTO> toResponseDTOList(List<Usuario> usuarios);
}
