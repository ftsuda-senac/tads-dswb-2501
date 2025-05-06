package br.senac.tads.dsw.exemplo.rest.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UsuarioSistema implements UserDetails {

    private String username;

    private String hashSenha;

    private String nomeCompleto;

    private List<Permissao> permissoes;

    public UsuarioSistema() {
    }

    public UsuarioSistema(String nomeCompleto, String username, String hashSenha, List<Permissao> permissoes) {
        this.nomeCompleto = nomeCompleto;
        this.username = username;
        this.hashSenha = hashSenha;
        this.permissoes = permissoes;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getHashSenha() {
        return hashSenha;
    }

    public void setHashSenha(String hashSenha) {
        this.hashSenha = hashSenha;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public List<Permissao> getPermissoes() {
        return permissoes;
    }

    public void setPermissoes(List<Permissao> permissoes) {
        this.permissoes = permissoes;
    }

    @Override
    // public Collection<? extends GrantedAuthority> getAuthorities() {
    public List<Permissao> getAuthorities() {
        return this.permissoes;
    }

    @Override
    public String getPassword() {
        return this.hashSenha;
    }

}
