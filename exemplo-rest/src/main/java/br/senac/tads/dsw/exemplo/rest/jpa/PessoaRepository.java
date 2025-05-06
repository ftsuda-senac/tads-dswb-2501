package br.senac.tads.dsw.exemplo.rest.jpa;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {

    Optional<Pessoa> findByApelido(String apelido);

}
