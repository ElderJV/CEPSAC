package com.example.cepsacbackend.Service;

import com.example.cepsacbackend.Dto.Pago.PagoCreateDTO;
import com.example.cepsacbackend.Entity.MetodoPago;
import com.example.cepsacbackend.Entity.Matricula;
import com.example.cepsacbackend.Entity.Pago;
import com.example.cepsacbackend.Entity.Usuario;
import com.example.cepsacbackend.Enums.EstadoMatricula;
import com.example.cepsacbackend.Enums.Rol;
import com.example.cepsacbackend.Repository.MetodoPagoRepository;
import com.example.cepsacbackend.Repository.MatriculaRepository;
import com.example.cepsacbackend.Repository.PagoRepository;
import com.example.cepsacbackend.Repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PagoService {

    private final PagoRepository pagoRepository;
    private final MatriculaRepository matriculaRepository;
    private final MetodoPagoRepository metodoPagoRepository;
    private final UsuarioRepository usuarioRepository;

    @Transactional
    public Pago registrarPago(PagoCreateDTO dto) {
        Matricula matricula = matriculaRepository.findById(dto.idMatricula())
                .orElseThrow(() -> new RuntimeException("Matrícula no encontrada: " + dto.idMatricula()));
        MetodoPago metodoPago = metodoPagoRepository.findById(dto.idMetodoPago())
                .orElseThrow(() -> new RuntimeException("Método de pago no encontrado: " + dto.idMetodoPago()));
        Usuario admin = usuarioRepository.findById(dto.idUsuarioRegistro())
                .orElseThrow(() -> new RuntimeException("Usuario (registro) no encontrado: " + dto.idUsuarioRegistro()));
        if (admin.getRol() != Rol.ADMINISTRADOR) {
            throw new RuntimeException("El usuario que registra el pago debe ser ADMINISTRADOR");
        }

        Pago pago = new Pago();
        pago.setMatricula(matricula);
        pago.setMetodoPago(metodoPago);
        pago.setMonto(dto.monto());
        pago.setNumeroCuota(dto.numeroCuota());
        pago.setUsuarioRegistro(admin);
        Pago saved = pagoRepository.save(pago);

        // Al registrar el pago, actualizamos el estado de la matrícula a PAGADO
        matricula.setEstado(EstadoMatricula.PAGADO);
        matriculaRepository.save(matricula);

        return saved;
    }
}
