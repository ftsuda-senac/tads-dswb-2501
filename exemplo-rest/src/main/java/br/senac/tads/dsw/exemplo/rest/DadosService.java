package br.senac.tads.dsw.exemplo.rest;

import java.util.List;
import java.util.Optional;

public interface DadosService {

    List<DadosDto> findAll();

    Optional<DadosDto> findById(Integer id);

    DadosDto save(DadosDto input);

    void delete(int id);

}