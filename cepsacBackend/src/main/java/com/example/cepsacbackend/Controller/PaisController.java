package com.example.cepsacbackend.Controller;

import com.example.cepsacbackend.Entity.Pais;
import com.example.cepsacbackend.Repository.PaisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/paises")
public class PaisController {

    private final PaisRepository repopais;

    @GetMapping("/listar")
    public List<Pais> listarPaises() {
        return repopais.findAll();
    }

    @PostMapping("/obtener")
    public Pais obtenerPais(@RequestBody Short idPais) {
        Optional<Pais> pais = repopais.findById(idPais);
        return pais.orElse(null);
    }

    @PostMapping("/crear")
    public Pais crearPais(@RequestBody Pais pais) {
        return repopais.save(pais);
    }

    @PostMapping("/actualizar")
    public Pais actualizarPais(@RequestBody Pais pais) {
        return repopais.save(pais);
    }

    @PostMapping("/eliminar")
    public String eliminarPais(@RequestBody Short idPais) {
        repopais.deleteById(idPais);
        return "País eliminado correctamente";
    }
}