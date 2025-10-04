package com.example.cepsacbackend.Controller;

import com.example.cepsacbackend.Dto.Pago.PagoCreateDTO;
import com.example.cepsacbackend.Entity.Pago;
import com.example.cepsacbackend.Service.PagoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pagos")
public class PagoController {

    private final PagoService pagoService;

    public PagoController(PagoService pagoService) {
        this.pagoService = pagoService;
    }

    @PostMapping
    public ResponseEntity<Pago> registrar(@RequestBody @Valid PagoCreateDTO dto) {
        Pago p = pagoService.registrarPago(dto);
        return new ResponseEntity<>(p, HttpStatus.CREATED);
    }
}
