package com.example.cepsacbackend.Service.Impl;

import com.example.cepsacbackend.Dto.Usuario.UsuarioRequestDTO;
import com.example.cepsacbackend.Dto.Usuario.UsuarioResponseDTO;
import com.example.cepsacbackend.Dto.Usuario.UsuarioUpdateRequestDTO;
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
    public List<UsuarioResponseDTO> listarUsuarios() {
        List<Usuario> usuarios = repouser.findAll();
        return usuarioMapper.toResponseDTOList(usuarios);
    }

    @Override
    public UsuarioResponseDTO obtenerUsuario(Short idUsuario) {
        Usuario usuario = repouser.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + idUsuario));
        return usuarioMapper.toResponseDTO(usuario);
    }

    @Override
    @Transactional
    public UsuarioResponseDTO crearUsuario(UsuarioRequestDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setPais(resolverPais(dto.getNombrePais()));
        usuario.setTipoIdentificacion(resolverTipoIdentificacion(dto.getIdTipoIdentificacion()));
        usuario.setRol(dto.getRol());
        usuario.setNombre(dto.getNombre());
        usuario.setApellido(dto.getApellido());
        usuario.setCorreo(dto.getCorreo());
        usuario.setPassword(dto.getPassword());
        usuario.setEstado(dto.getEstado());
        usuario.setNumeroCelular(dto.getNumeroCelular());
        usuario.setNumeroIdentificacion(dto.getNumeroIdentificacion());
        Usuario nuevoUsuario = repouser.save(usuario);
        return usuarioMapper.toResponseDTO(nuevoUsuario);
    }

    @Override
    @Transactional
    public UsuarioResponseDTO actualizarUsuario(UsuarioRequestDTO dto) {
        Usuario usuarioExistente = repouser.findById(dto.getIdUsuario())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + dto.getIdUsuario()));

        usuarioExistente.setPais(resolverPais(dto.getNombrePais()));
        usuarioExistente.setTipoIdentificacion(resolverTipoIdentificacion(dto.getIdTipoIdentificacion()));
        usuarioExistente.setRol(dto.getRol());
        usuarioExistente.setNombre(dto.getNombre());
        usuarioExistente.setApellido(dto.getApellido());
        usuarioExistente.setCorreo(dto.getCorreo());

        if (dto.getPassword() != null && !dto.getPassword().isEmpty()) {
            usuarioExistente.setPassword(dto.getPassword());
        }

        usuarioExistente.setEstado(dto.getEstado());
        usuarioExistente.setNumeroCelular(dto.getNumeroCelular());
        usuarioExistente.setNumeroIdentificacion(dto.getNumeroIdentificacion());

        Usuario usuarioActualizado = repouser.save(usuarioExistente);
        return usuarioMapper.toResponseDTO(usuarioActualizado);
    }

    @Override
    @Transactional
    public UsuarioResponseDTO actualizarUsuarioParcialmente(UsuarioUpdateRequestDTO dto) {
        Usuario usuarioExistente = repouser.findById(dto.getIdUsuario())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + dto.getIdUsuario()));

        if (dto.getRol() != null) {
            usuarioExistente.setRol(dto.getRol());
        }
        if (dto.getNombre() != null) {
            usuarioExistente.setNombre(dto.getNombre());
        }
        if (dto.getApellido() != null) {
            usuarioExistente.setApellido(dto.getApellido());
        }
        if (dto.getCorreo() != null) {
            usuarioExistente.setCorreo(dto.getCorreo());
        }
        if (dto.getPassword() != null && !dto.getPassword().isEmpty()) {
            usuarioExistente.setPassword(dto.getPassword());
        }
        if (dto.getEstado() != null) {
            usuarioExistente.setEstado(dto.getEstado());
        }
        if (dto.getNumeroCelular() != null) {
            usuarioExistente.setNumeroCelular(dto.getNumeroCelular());
        }
        if (dto.getNumeroIdentificacion() != null) {
            usuarioExistente.setNumeroIdentificacion(dto.getNumeroIdentificacion());
        }
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
    public void eliminarUsuario(Short idUsuario) {
        if (!repouser.existsById(idUsuario)) {
            throw new RuntimeException("Usuario no encontrado con ID: " + idUsuario);
        }
        repouser.deleteById(idUsuario);
    }
}