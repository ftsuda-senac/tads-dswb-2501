package br.senac.tads.dsw.exemplo.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.senac.tads.dsw.exemplo.rest.jpa.Interesse;
import br.senac.tads.dsw.exemplo.rest.jpa.InteresseRepository;

@RestController
@RequestMapping("/api/interesses")
public class InteresseController {

    // private List<InteresseDto> interesses = List.of(
    //     new InteresseDto(1, "Java"),
    //     new InteresseDto(2, "Javascript"),
    //     new InteresseDto(3, "HTML"),
    //     new InteresseDto(4, "CSS"),
    //     new InteresseDto(5, "Angular"),
    //     new InteresseDto(6, "React"),
    //     new InteresseDto(7, "Spring Boot"),
    //     new InteresseDto(8, "SQL")
    // );

    @Autowired
    private InteresseRepository interesseRepository;

    @GetMapping
    public List<InteresseDto> findAll() {
        List<Interesse> interessesEntities = interesseRepository.findAll();
        List<InteresseDto> resultado = new ArrayList<>();
        for (Interesse interesse : interessesEntities) {
            resultado.add(new InteresseDto(interesse.getId(), interesse.getNome()));
        }
        return resultado;
    }


    public record InteresseDto(int id, String nome) {
    
    }

}

