package com.example.cepsacbackend.Controller;

import com.example.cepsacbackend.Entity.Usuario;
import com.example.cepsacbackend.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository repouser;

    @GetMapping("/listar")
    public List<Usuario> listarUsuarios() {
        return repouser.findAll();
    }

    @PostMapping("/obtener")
    public Usuario obtenerUsuario(@RequestBody Integer idUsuario) {
        Optional<Usuario> usuario = repouser.findById(idUsuario.shortValue());
        return usuario.orElse(null);
    }

    @PostMapping("/crear")
    public Usuario crearUsuario(@RequestBody Usuario usuario) {
        return repouser.save(usuario);
    }

    @PostMapping("/actualizar")
    public Usuario actualizarUsuario(@RequestBody Usuario usuario) {
        return repouser.save(usuario);
    }

    @PostMapping("/eliminar")
    public String eliminarUsuario(@RequestBody Integer idUsuario) {
        repouser.deleteById(idUsuario.shortValue());
        return "Usuario eliminado correctamente";
    }
}