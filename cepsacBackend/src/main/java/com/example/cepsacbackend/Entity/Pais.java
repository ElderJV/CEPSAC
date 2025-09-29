package com.example.cepsacbackend.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pais {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdPais")
    private Short idPais;

    @Column(name = "Nombre", nullable = false, length = 50)
    private String nombre;

    @Column(name = "Codigo", length = 2)
    private String codigo;

    @Column(name = "CodigoTelefono", length = 5)
    private String codigotelefono;

}