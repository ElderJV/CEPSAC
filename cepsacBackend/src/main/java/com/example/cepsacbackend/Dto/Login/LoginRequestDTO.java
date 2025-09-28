package com.example.cepsacbackend.Dto.Login;

import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequestDTO {

    @NotBlank(message = "El correo es obligatorio")
    @Email(message = "Formato de correo electrónico inválido")
    private String correo;

    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 1, message = "La contraseña no puede estar vacía")
    private String password;
}