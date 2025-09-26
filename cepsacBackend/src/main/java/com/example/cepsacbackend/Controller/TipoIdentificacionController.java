package com.example.cepsacbackend.Controller;

import com.example.cepsacbackend.Entity.TipoIdentificacion;
import com.example.cepsacbackend.Repository.TipoIdentificacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tiposidentificacion")
public class TipoIdentificacionController {

    @Autowired
    private TipoIdentificacionRepository repotipoidentificacion;

    @GetMapping("/listar")
    public List<TipoIdentificacion> listarTiposIdentificacion() {
        return repotipoidentificacion.findAll();
    }

    @PostMapping("/obtener")
    public TipoIdentificacion obtenerTipoIdentificacion(@RequestBody Short idTipoIdentificacion) {
        Optional<TipoIdentificacion> tipoIdentificacion = repotipoidentificacion.findById(idTipoIdentificacion);
        return tipoIdentificacion.orElse(null);
    }

    @PostMapping("/crear")
    public TipoIdentificacion crearTipoIdentificacion(@RequestBody TipoIdentificacion tipoIdentificacion) {
        return repotipoidentificacion.save(tipoIdentificacion);
    }

    @PostMapping("/actualizar")
    public TipoIdentificacion actualizarTipoIdentificacion(@RequestBody TipoIdentificacion tipoIdentificacion) {
        return repotipoidentificacion.save(tipoIdentificacion);
    }

    @PostMapping("/eliminar")
    public String eliminarTipoIdentificacion(@RequestBody Short idTipoIdentificacion) {
        repotipoidentificacion.deleteById(idTipoIdentificacion);
        return "Tipo de identificación eliminado correctamente";
    }
}