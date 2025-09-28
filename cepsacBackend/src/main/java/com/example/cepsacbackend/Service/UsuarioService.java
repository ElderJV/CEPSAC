package com.example.cepsacbackend.Service;

import com.example.cepsacbackend.Dto.Usuario.UsuarioCreateDTO;
import com.example.cepsacbackend.Dto.Usuario.UsuarioResponseDTO;
import com.example.cepsacbackend.Dto.Usuario.UsuarioUpdateDTO;
import com.example.cepsacbackend.Enums.Rol;
import com.example.cepsacbackend.Dto.Usuario.UsuarioPatchDTO;

import java.util.List;

public interface UsuarioService {

    List<UsuarioResponseDTO> listarUsuarios();
    UsuarioResponseDTO obtenerUsuario(Short idUsuario);
    UsuarioResponseDTO crearUsuario(UsuarioCreateDTO dto);
    UsuarioResponseDTO actualizarUsuario(UsuarioUpdateDTO dto);
    UsuarioResponseDTO actualizarUsuarioParcialmente(UsuarioPatchDTO dto);
    void eliminarUsuario(Short idUsuario);

    UsuarioResponseDTO restaurarUsuario(Short idUsuario);
    List<UsuarioResponseDTO> listarUsuariosPorRol(Rol rol);
}