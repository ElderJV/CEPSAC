package com.example.cepsacbackend.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pais {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdPais")
    private Integer idPais;
    
    @Column(name = "Nombre", nullable = false, length = 50)
    private String nombre;
    
    @Column(name = "Codigo", length = 5)
    private String codigo;
    
    // Relación bidireccional con Usuario
    @OneToMany(mappedBy = "pais", fetch = FetchType.LAZY)
    private List<Usuario> usuarios;
}