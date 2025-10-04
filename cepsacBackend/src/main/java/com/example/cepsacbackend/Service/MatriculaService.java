package com.example.cepsacbackend.Service;

import com.example.cepsacbackend.Dto.Matricula.MatriculaCreateDTO;
import com.example.cepsacbackend.Entity.Matricula;

public interface MatriculaService {

    Matricula crearMatricula(MatriculaCreateDTO dto);
    Matricula aprobarMatricula(Integer idMatricula, Integer idAdministrador);
}
