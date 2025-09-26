package com.example.cepsacbackend.Controller;

import com.example.cepsacbackend.Entity.Pais;
import com.example.cepsacbackend.Repository.PaisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/paises")
public class PaisController {

    @Autowired
    private PaisRepository repoPais;

    @GetMapping("/listar")
    public List<Pais> listarPaises() {
        return repoPais.findAll();
    }

    @PostMapping("/obtener")
    public Pais obtenerPais(@RequestBody Integer idPais) {
        Optional<Pais> pais = repoPais.findById(idPais);
        return pais.orElse(null);
    }

    @PostMapping("/crear")
    public Pais crearPais(@RequestBody Pais pais) {
        return repoPais.save(pais);
    }

    @PostMapping("/actualizar")
    public Pais actualizarPais(@RequestBody Pais pais) {
        return repoPais.save(pais);
    }

    @PostMapping("/eliminar")
    public String eliminarPais(@RequestBody Integer idPais) {
        repoPais.deleteById(idPais);
        return "País eliminado correctamente";
    }
}