package com.example.cepsacbackend.Controller;

import com.example.cepsacbackend.Dto.Usuario.UsuarioRequestDTO;
import com.example.cepsacbackend.Dto.Usuario.UsuarioResponseDTO;
import com.example.cepsacbackend.Dto.Usuario.UsuarioUpdateRequestDTO;
import com.example.cepsacbackend.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/listar")
    public List<UsuarioResponseDTO> listarUsuarios() {
        return usuarioService.listarUsuarios();
    }

    @GetMapping("/obtener/{idUsuario}")
    public ResponseEntity<UsuarioResponseDTO> obtenerUsuario(@PathVariable Short idUsuario) {
        try {
            UsuarioResponseDTO usuarioDTO = usuarioService.obtenerUsuario(idUsuario);
            return ResponseEntity.ok(usuarioDTO);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/crear")
    public ResponseEntity<UsuarioResponseDTO> crearUsuario(@RequestBody UsuarioRequestDTO dto) {
        try {
            UsuarioResponseDTO nuevoUsuarioDTO = usuarioService.crearUsuario(dto);
            return new ResponseEntity<>(nuevoUsuarioDTO, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/actualizar")
    public ResponseEntity<UsuarioResponseDTO> actualizarUsuario(@RequestBody UsuarioRequestDTO dto) {
        try {
            UsuarioResponseDTO usuarioActualizadoDTO = usuarioService.actualizarUsuario(dto);
            return ResponseEntity.ok(usuarioActualizadoDTO);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/actualizar-parcial")
    public ResponseEntity<UsuarioResponseDTO> actualizarUsuarioParcialmente(@RequestBody UsuarioUpdateRequestDTO dto) {
        try {
            UsuarioResponseDTO usuarioActualizadoDTO = usuarioService.actualizarUsuarioParcialmente(dto);
            return ResponseEntity.ok(usuarioActualizadoDTO);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/eliminar/{idUsuario}")
    public ResponseEntity<String> eliminarUsuario(@PathVariable Short idUsuario) {
        try {
            usuarioService.eliminarUsuario(idUsuario);
            return ResponseEntity.ok("Usuario eliminado correctamente");
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}