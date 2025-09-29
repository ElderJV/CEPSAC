package com.example.cepsacbackend.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TipoIdentificacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdTipoIdentificacion")
    private Short idTipoIdentificacion;

    @Column(name = "Nombre", nullable = false, length = 50)
    private String nombre;

}