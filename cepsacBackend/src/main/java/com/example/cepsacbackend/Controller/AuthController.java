package com.example.cepsacbackend.Controller;

import com.example.cepsacbackend.Config.Security.JwtService;
import com.example.cepsacbackend.Dto.Login.LoginRequestDTO;
import com.example.cepsacbackend.Dto.Login.AuthResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager gestorAutenticacion;
    private final JwtService servicioJwt;
    private final UserDetailsService servicioDetallesUsuario;

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> iniciarSesion(@RequestBody LoginRequestDTO peticion) {

        // Autenticar credenciales
        gestorAutenticacion.authenticate(
                new UsernamePasswordAuthenticationToken(
                        peticion.getEmail(),
                        peticion.getContrasena()
                )
        );
        // traemos userdetails
        final UserDetails detallesUsuario = servicioDetallesUsuario.loadUserByUsername(peticion.getEmail());
        // generamos token
        final String tokenJwt = servicioJwt.generarToken(detallesUsuario);
        // devolvemos token
        return ResponseEntity.ok(AuthResponseDTO.builder().token(tokenJwt).build());
    }
}