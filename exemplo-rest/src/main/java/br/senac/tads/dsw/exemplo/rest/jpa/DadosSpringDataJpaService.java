package br.senac.tads.dsw.exemplo.rest.jpa;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import br.senac.tads.dsw.exemplo.rest.DadosDto;
import br.senac.tads.dsw.exemplo.rest.DadosService;

@Service
@Primary
public class DadosSpringDataJpaService implements DadosService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private InteresseRepository interesseRepository;

    @Override
    public List<DadosDto> findAll() {
        List<Pessoa> entities = pessoaRepository.findAll();
        List<DadosDto> resultados = new  ArrayList<>();
        for (Pessoa p : entities) {
            resultados.add(converterEntityParaDto(p));
        }
        return resultados;
    }

    @Override
    public Optional<DadosDto> findById(Integer id) {
        /* 
        Optional<Pessoa> optPessoaEntity = pessoaRepository.findById(id);
        if (optPessoaEntity.isEmpty()) {
            return Optional.empty();
        }
        DadosDto dto = converterEntityParaDto(optPessoaEntity.get());
        return Optional.of(dto);
        */
        return pessoaRepository.findById(id).map(entity -> converterEntityParaDto(entity));
    }

    private DadosDto converterEntityParaDto(Pessoa pessoaEntity) {
        DadosDto dto  = new DadosDto();
        dto.setId(pessoaEntity.getId());
        dto.setApelido(pessoaEntity.getApelido());
        dto.setNome(pessoaEntity.getNome());
        dto.setEmail(pessoaEntity.getEmail());
        dto.setTelefone(pessoaEntity.getTelefone());
        dto.setDataNascimento(pessoaEntity.getDataNascimento());
        if (pessoaEntity.getInteresses() != null && 
            !pessoaEntity.getInteresses().isEmpty()) {
            List<String> interessesDto = new ArrayList<>();
            for (Interesse interesse : pessoaEntity.getInteresses()) {
                interessesDto.add(interesse.getNome());
            }
            dto.setInteresses(interessesDto);
        }
        return dto;
    }

    @Override
    public DadosDto save(DadosDto dto) {

        Pessoa p = new Pessoa();
        p.setApelido(dto.getApelido());
        p.setNome(dto.getNome());
        p.setEmail(dto.getEmail());
        p.setTelefone(dto.getTelefone());
        p.setDataNascimento(dto.getDataNascimento());
        p.setSenha(dto.getSenha());
        
        Set<Interesse> interesses = new HashSet<>();
        for (Integer interesseId : dto.getInteressesIds()) {
            Optional<Interesse> optInteresse = interesseRepository.findById(interesseId);
            if (optInteresse.isPresent()) {
                interesses.add(optInteresse.get());
            }
        }
        p.setInteresses(interesses);

        pessoaRepository.save(p);

        dto.setId(p.getId());
        return dto;
    }

    @Override
    public void delete(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

}
