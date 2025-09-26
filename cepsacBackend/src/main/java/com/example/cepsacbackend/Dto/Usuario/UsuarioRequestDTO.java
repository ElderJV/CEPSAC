package com.example.cepsacbackend.Dto.Usuario;

import com.example.cepsacbackend.Enums.EstadoUsuario;
import com.example.cepsacbackend.Enums.Rol;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioRequestDTO {

    private Short idUsuario;
    private Rol rol;
    private String nombre;
    private String apellido;
    private String correo;
    private String password;
    private EstadoUsuario estado;
    private String numeroCelular;
    private String numeroIdentificacion;

    private String nombrePais;
    private Short idTipoIdentificacion;
}
