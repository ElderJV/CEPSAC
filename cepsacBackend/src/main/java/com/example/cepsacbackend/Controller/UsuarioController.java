package com.example.cepsacbackend.Controller;

import com.example.cepsacbackend.Dto.Usuario.UsuarioCreateDTO;
import com.example.cepsacbackend.Dto.Usuario.UsuarioResponseDTO;
import com.example.cepsacbackend.Dto.Usuario.UsuarioUpdateDTO;
import com.example.cepsacbackend.Enums.Rol;
import com.example.cepsacbackend.Dto.Usuario.UsuarioPatchDTO;
import com.example.cepsacbackend.Service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@RestController
@Validated
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService userservice;

    @GetMapping("/listar")
    public List<UsuarioResponseDTO> listarUsuarios() {
        return userservice.listarUsuarios();
    }

    @GetMapping("/listar/{rol}")
    public List<UsuarioResponseDTO> listarUsuariosPorRol(@PathVariable Rol rol) {
        return userservice.listarUsuariosPorRol(rol);
    }

    @GetMapping("/obtener/{idUsuario}")
    public ResponseEntity<UsuarioResponseDTO> obtenerUsuario(@PathVariable Short idUsuario) {
        try {
            UsuarioResponseDTO usuarioDTO = userservice.obtenerUsuario(idUsuario);
            return ResponseEntity.ok(usuarioDTO);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/crear")
    public ResponseEntity<UsuarioResponseDTO> crearUsuario(@Valid @RequestBody UsuarioCreateDTO dto) {
        try {
            UsuarioResponseDTO nuevoUsuarioDTO = userservice.crearUsuario(dto);
            return new ResponseEntity<>(nuevoUsuarioDTO, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/actualizar")
    public ResponseEntity<UsuarioResponseDTO> actualizarUsuario(@Valid @RequestBody UsuarioUpdateDTO dto) {
        try {
            UsuarioResponseDTO usuarioActualizadoDTO = userservice.actualizarUsuario(dto);
            return ResponseEntity.ok(usuarioActualizadoDTO);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/actualizar-parcial")
    public ResponseEntity<UsuarioResponseDTO> actualizarUsuarioParcialmente(@Valid @RequestBody UsuarioPatchDTO dto) {
        try {
            UsuarioResponseDTO usuarioActualizadoDTO = userservice.actualizarUsuarioParcialmente(dto);
            return ResponseEntity.ok(usuarioActualizadoDTO);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/eliminar/{idUsuario}")
    public ResponseEntity<String> eliminarUsuario(@PathVariable Short idUsuario) {
        try {
            userservice.eliminarUsuario(idUsuario);
            return ResponseEntity.ok("Usuario suspendido/eliminado correctamente");
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/restaurar/{idUsuario}")
    public ResponseEntity<UsuarioResponseDTO> restaurarUsuario(@PathVariable Short idUsuario) {
        try {
            UsuarioResponseDTO usuarioRestaurado = userservice.restaurarUsuario(idUsuario);
            return ResponseEntity.ok(usuarioRestaurado);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}