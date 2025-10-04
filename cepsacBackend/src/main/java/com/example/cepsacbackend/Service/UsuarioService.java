package com.example.cepsacbackend.Service;

import com.example.cepsacbackend.Dto.Usuario.UsuarioCreateDTO;
import com.example.cepsacbackend.Dto.Usuario.UsuarioResponseDTO;
import com.example.cepsacbackend.Dto.Usuario.UsuarioUpdateDTO;
import com.example.cepsacbackend.Enums.Rol;
import com.example.cepsacbackend.Dto.Usuario.UsuarioPatchDTO;

import java.util.List;

public interface UsuarioService {

    List<UsuarioResponseDTO> listarUsuarios();
    UsuarioResponseDTO obtenerUsuario(Integer idUsuario);
    UsuarioResponseDTO crearUsuario(UsuarioCreateDTO dto);
    UsuarioResponseDTO actualizarUsuario(UsuarioUpdateDTO dto);
    UsuarioResponseDTO actualizarUsuarioParcialmente(UsuarioPatchDTO dto);
    void eliminarUsuario(Integer idUsuario);

    UsuarioResponseDTO restaurarUsuario(Integer idUsuario);
    List<UsuarioResponseDTO> listarUsuariosPorRol(Rol rol);
}