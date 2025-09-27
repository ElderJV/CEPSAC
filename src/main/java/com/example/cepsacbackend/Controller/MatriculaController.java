package com.example.cepsacbackend.Controller;

import com.example.cepsacbackend.Dto.Matricula.MatriculaRequestDTO;
import com.example.cepsacbackend.Dto.Matricula.MatriculaResponseDTO;
import com.example.cepsacbackend.Service.MatriculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/matriculas")
public class MatriculaController {

    @Autowired
    private MatriculaService matriculaService;

    @GetMapping("/listar")
    public List<MatriculaResponseDTO> listarMatriculas() {
        return matriculaService.listarMatriculas();
    }

    @GetMapping("/obtener/{idMatricula}")
    public ResponseEntity<MatriculaResponseDTO> obtenerMatricula(@PathVariable Short idMatricula) {
        try {
            return ResponseEntity.ok(matriculaService.obtenerMatricula(idMatricula));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/crear")
    public ResponseEntity<MatriculaResponseDTO> crearMatricula(@RequestBody MatriculaRequestDTO dto) {
        try {
            return ResponseEntity.ok(matriculaService.crearMatricula(dto));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/eliminar/{idMatricula}")
    public ResponseEntity<String> eliminarMatricula(@PathVariable Short idMatricula) {
        try {
            matriculaService.eliminarMatricula(idMatricula);
            return ResponseEntity.ok("Matrícula eliminada correctamente");
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
