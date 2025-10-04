package com.example.cepsacbackend.Controller;

import com.example.cepsacbackend.Dto.Matricula.MatriculaApprovalDTO;
import com.example.cepsacbackend.Dto.Matricula.MatriculaCreateDTO;
import com.example.cepsacbackend.Dto.Matricula.MatriculaResponseDTO;
import com.example.cepsacbackend.Entity.Matricula;
import com.example.cepsacbackend.Mapper.MatriculaMapper;
import com.example.cepsacbackend.Service.MatriculaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.RequestArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/matriculas")
public class MatriculaController {

    private final MatriculaService matriculaService;
    private final MatriculaMapper matriculaMapper;

    @PostMapping
    public ResponseEntity<MatriculaResponseDTO> crear(@RequestBody @Valid MatriculaCreateDTO dto) {
        Matricula m = matriculaService.crearMatricula(dto);
        return new ResponseEntity<>(matriculaMapper.toResponseDTO(m), HttpStatus.CREATED);
    }

    @PutMapping("/{id}/aprobar")
    public ResponseEntity<MatriculaResponseDTO> aprobar(@PathVariable Integer id, @RequestBody @Valid MatriculaApprovalDTO dto) {
        Matricula m = matriculaService.aprobarMatricula(id, dto.idAdministrador());
        return ResponseEntity.ok(matriculaMapper.toResponseDTO(m));
    }
}
