package com.example.cepsacbackend.Repository;

import com.example.cepsacbackend.Entity.Usuario;
import com.example.cepsacbackend.Enums.EstadoUsuario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Short> {
    java.util.Optional<Usuario> findByCorreo(String correo);
    List<Usuario> findByEstado(EstadoUsuario estado);

}