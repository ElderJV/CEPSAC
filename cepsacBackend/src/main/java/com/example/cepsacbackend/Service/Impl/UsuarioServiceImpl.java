package com.example.cepsacbackend.Service.Impl;

import com.example.cepsacbackend.Dto.Usuario.UsuarioCreateDTO;
import com.example.cepsacbackend.Dto.Usuario.UsuarioResponseDTO;
import com.example.cepsacbackend.Dto.Usuario.UsuarioUpdateDTO;
import com.example.cepsacbackend.Dto.Usuario.UsuarioPatchDTO;
import com.example.cepsacbackend.Entity.Pais;
import com.example.cepsacbackend.Entity.TipoIdentificacion;
import com.example.cepsacbackend.Entity.Usuario;
import com.example.cepsacbackend.Mapper.UsuarioMapper;
import com.example.cepsacbackend.Repository.PaisRepository;
import com.example.cepsacbackend.Repository.TipoIdentificacionRepository;
import com.example.cepsacbackend.Repository.UsuarioRepository;
import com.example.cepsacbackend.Service.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import com.example.cepsacbackend.Enums.EstadoUsuario;
import com.example.cepsacbackend.Enums.Rol;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository repouser;

    @Autowired
    private PaisRepository repopais;

    @Autowired
    private TipoIdentificacionRepository repotipo;

    @Autowired
    private UsuarioMapper usuarioMapper;

    private Pais resolverPais(String nombrePais) {
        if (nombrePais == null || nombrePais.trim().isEmpty()) {
            return null;
        }
        return repopais.findByNombre(nombrePais)
                .orElseThrow(() -> new RuntimeException("País no encontrado: " + nombrePais));
    }

    private TipoIdentificacion resolverTipoIdentificacion(Short idTipo) {
        if (idTipo == null) {
            return null;
        }
        return repotipo.findById(idTipo)
                .orElseThrow(() -> new RuntimeException("Tipo de Identificación no encontrado id=" + idTipo));
    }

    @Override
    @Transactional(readOnly = true)
    public List<UsuarioResponseDTO> listarUsuarios() {
        List<Usuario> usuarios = repouser.findAllActivos();
        return usuarioMapper.toResponseDTOList(usuarios);
    }

    @Override
    @Transactional(readOnly = true)
    public UsuarioResponseDTO obtenerUsuario(Short idUsuario) {
        Usuario usuario = repouser.findByIdActivo(idUsuario)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + idUsuario));
        return usuarioMapper.toResponseDTO(usuario);
    }

    @Override
    @Transactional
    public UsuarioResponseDTO crearUsuario(UsuarioCreateDTO dto) {
        Usuario usuario = usuarioMapper.toEntity(dto);
        usuario.setPais(resolverPais(dto.getNombrePais()));
        usuario.setTipoIdentificacion(resolverTipoIdentificacion(dto.getIdTipoIdentificacion()));
        Usuario nuevoUsuario = repouser.save(usuario);
        return usuarioMapper.toResponseDTO(nuevoUsuario);
    }

    @Override
    @Transactional
    public UsuarioResponseDTO actualizarUsuario(UsuarioUpdateDTO dto) {
        Usuario usuarioExistente = repouser.findById(dto.getIdUsuario())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + dto.getIdUsuario()));
        usuarioMapper.updateEntityFromUpdateDTO(dto, usuarioExistente);
        usuarioExistente.setPais(resolverPais(dto.getNombrePais()));
        usuarioExistente.setTipoIdentificacion(resolverTipoIdentificacion(dto.getIdTipoIdentificacion()));
        Usuario usuarioActualizado = repouser.save(usuarioExistente);
        return usuarioMapper.toResponseDTO(usuarioActualizado);
    }

    @Override
    @Transactional
    public UsuarioResponseDTO actualizarUsuarioParcialmente(UsuarioPatchDTO dto) {
        Usuario usuarioExistente = repouser.findById(dto.getIdUsuario())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + dto.getIdUsuario()));
        usuarioMapper.updateEntityFromPatchDTO(dto, usuarioExistente);
        if (dto.getNombrePais() != null) {
            usuarioExistente.setPais(resolverPais(dto.getNombrePais()));
        }
        if (dto.getIdTipoIdentificacion() != null) {
            usuarioExistente.setTipoIdentificacion(resolverTipoIdentificacion(dto.getIdTipoIdentificacion()));
        }
        Usuario usuarioActualizado = repouser.save(usuarioExistente);
        return usuarioMapper.toResponseDTO(usuarioActualizado);
    }

    @Override
    @Transactional
    public void eliminarUsuario(Short idUsuario) {
        Usuario usuario = repouser.findByIdActivo(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + idUsuario));
        usuario.setEstado(EstadoUsuario.suspendido);
        repouser.save(usuario);
    }

    //metodo para restaurar usuario suspendido
    @Transactional
    public UsuarioResponseDTO restaurarUsuario(Short idUsuario) {
        Usuario usuario = repouser.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + idUsuario));
        if (usuario.getEstado() != EstadoUsuario.suspendido) {
            throw new RuntimeException("El usuario no está suspendido/eliminado");
        }
        usuario.setEstado(EstadoUsuario.activo);
        Usuario usuarioRestaurado = repouser.save(usuario);
        return usuarioMapper.toResponseDTO(usuarioRestaurado);
    }

    @Transactional(readOnly = true)
    public List<UsuarioResponseDTO> listarUsuariosPorRol(Rol rol) {
        List<Usuario> usuarios = repouser.findByRolActivo(rol);
        return usuarioMapper.toResponseDTOList(usuarios);
    }
}