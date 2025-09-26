package com.example.cepsacbackend.Service;

import com.example.cepsacbackend.Dto.Usuario.UsuarioRequestDTO;
import com.example.cepsacbackend.Dto.Usuario.UsuarioResponseDTO;
import com.example.cepsacbackend.Dto.Usuario.UsuarioUpdateRequestDTO;

import java.util.List;

public interface UsuarioService {

    List<UsuarioResponseDTO> listarUsuarios();
    UsuarioResponseDTO obtenerUsuario(Short idUsuario);
    UsuarioResponseDTO crearUsuario(UsuarioRequestDTO dto);
    // metodo para PUT
    UsuarioResponseDTO actualizarUsuario(UsuarioRequestDTO dto);
    // metodo para PATCH
    UsuarioResponseDTO actualizarUsuarioParcialmente(UsuarioUpdateRequestDTO dto);
    void eliminarUsuario(Short idUsuario);
}
