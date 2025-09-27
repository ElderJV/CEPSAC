package com.example.cepsacbackend.Service;

import com.example.cepsacbackend.Dto.Matricula.MatriculaRequestDTO;
import com.example.cepsacbackend.Dto.Matricula.MatriculaResponseDTO;


import java.util.List;

public interface MatriculaService {

    List<MatriculaResponseDTO> listarMatriculas();

    MatriculaResponseDTO obtenerMatricula(Short idMatricula);

    MatriculaResponseDTO crearMatricula(MatriculaRequestDTO dto);

    MatriculaResponseDTO actualizarMatricula(MatriculaRequestDTO dto);



    void eliminarMatricula(Short idMatricula);
}
