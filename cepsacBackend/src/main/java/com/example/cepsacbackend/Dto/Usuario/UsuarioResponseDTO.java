package com.example.cepsacbackend.Dto.Usuario;

import com.example.cepsacbackend.Enums.EstadoUsuario;
import com.example.cepsacbackend.Enums.Rol;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioResponseDTO {

    private Short idUsuario;
    private Rol rol;
    private String nombre;
    private String apellido;
    private String correo;
    private EstadoUsuario estado;
    private String numeroCelular;
    private String numeroIdentificacion;

    // pais y tipo response
    private Short idPais;
    private String nombrePais;
    private String codigoTelefono;
    private Short idTipoIdentificacion;
    private String nombreTipoIdentificacion;
}