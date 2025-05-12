package br.senac.tads.dsw.exemplo.rest.security;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsuarioSistemaService implements UserDetailsService {

    private Map<String, UsuarioSistema> usuarios = new HashMap<>();

    public UsuarioSistemaService() {
        usuarios.put("peao", new UsuarioSistema("Peão da Silva", "peao", 
            "{noop}Abcd1234", List.of(new Permissao("SCOPE_PEAO"))));
        usuarios.put("gerente", new UsuarioSistema("Gerente de Souza", "gerente", 
            "{noop}Abcd1234", List.of(new Permissao("SCOPE_PEAO"), 
                new Permissao("SCOPE_GERENTE"))));
        usuarios.put("diretor", new UsuarioSistema("Diretor dos Santos", "diretor", 
            "{noop}Abcd1234", List.of(new Permissao("SCOPE_PEAO"),
                new Permissao("SCOPE_DIRETOR"))));
    }

    @Override
    public UsuarioSistema loadUserByUsername(String username) throws UsernameNotFoundException {
        UsuarioSistema usuario = usuarios.get(username);
        if (usuario == null) {
            throw new UsernameNotFoundException("Usuário " + username + " não encontrado");
        }
        return usuario;
    }

}
