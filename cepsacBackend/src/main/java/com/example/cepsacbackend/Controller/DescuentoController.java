package com.example.cepsacbackend.Controller;

import com.example.cepsacbackend.Dto.Descuento.DescuentoCreateDTO;
import com.example.cepsacbackend.Dto.Descuento.DescuentoResponseDTO;
import com.example.cepsacbackend.Dto.Descuento.DescuentoUpdateDTO;
import com.example.cepsacbackend.Service.DescuentoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/descuentos")
@Validated
@RequiredArgsConstructor
public class DescuentoController {

    private final DescuentoService servicio;

    @GetMapping("/listar")
    public List<DescuentoResponseDTO> listar() {
        return servicio.listar();
    }

    @GetMapping("/obtener/{id}")
    public ResponseEntity<DescuentoResponseDTO> obtener(@PathVariable("id") Short id) {
        try {
            return ResponseEntity.ok(servicio.obtener(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/crear")
    public ResponseEntity<DescuentoResponseDTO> crear(@Valid @RequestBody DescuentoCreateDTO dto) {
        try {
            DescuentoResponseDTO creado = servicio.crear(dto);
            return new ResponseEntity<>(creado, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/actualizar")
    public ResponseEntity<DescuentoResponseDTO> actualizar(@Valid @RequestBody DescuentoUpdateDTO dto) {
        try {
            DescuentoResponseDTO actualizado = servicio.actualizar(dto);
            return ResponseEntity.ok(actualizado);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminar(@PathVariable("id") Short id) {
        try {
            servicio.eliminar(id);
            return ResponseEntity.ok("descuento eliminado correctamente");
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
