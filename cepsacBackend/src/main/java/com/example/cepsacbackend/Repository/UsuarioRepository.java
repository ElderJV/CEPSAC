package com.example.cepsacbackend.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.cepsacbackend.Entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Short>{
    @Override
    default List<Usuario> findAll() {
        throw new UnsupportedOperationException("Error al traer Usuarios");
    }
}
