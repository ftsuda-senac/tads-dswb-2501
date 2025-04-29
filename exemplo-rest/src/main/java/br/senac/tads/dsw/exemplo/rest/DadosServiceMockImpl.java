package br.senac.tads.dsw.exemplo.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class DadosServiceMockImpl implements DadosService {

  private Map<Integer, DadosDto> dados;

  public int contador = 0;

  public DadosServiceMockImpl() {
    dados = new HashMap<>();
    int id;
    id = ++contador;
    dados.put(id, new DadosDto(id, "fulano", "Fulano da Silva", "2000-10-20",
        "fulano@email.com", "11 99999-9999", List.of("Java", "Spring Boot", "SQL")));
    id = ++contador;
    dados.put(id, new DadosDto(id, "ciclano", "Ciclano de Souza", "1999-05-15",
        "ciclano@email.com", "11 98888-8888", List.of("Javascript", "Angular", "React")));
    id = ++contador;
    dados.put(id, new DadosDto(id, "beltrana", "Beltrana dos Santos", "2001-02-09",
        "beltrana@email.com", "11 97777-7777", List.of("Spring Boot", "Microservices")));
  }

  @Override
  public List<DadosDto> findAll() {
    return new ArrayList<>(dados.values());
  }

  @Override
  public Optional<DadosDto> findById(Integer id) {
    return Optional.ofNullable(dados.get(id));
  }

  @Override
  public DadosDto save(DadosDto input) {
    // ID null -> implica incluir novo
    if (input.getId() == null) {
      int id = ++contador;
      input.setId(id);
    }
    dados.put(input.getId(), input);
    return input;
  }

  @Override
  public void delete(int id) {
    dados.remove(id);
  }
}
