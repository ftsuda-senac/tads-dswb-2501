package br.senac.tads.dsw.exemplo.rest.security;

import org.springframework.security.core.GrantedAuthority;

public class Permissao implements GrantedAuthority {

    private String nomePermissao;

    public Permissao() {
    }

    public Permissao(String nomePermissao) {
        this.nomePermissao = nomePermissao;
    }

    public String getNomePermissao() {
        return nomePermissao;
    }

    public void setNomePermissao(String nomePermissao) {
        this.nomePermissao = nomePermissao;
    }

    @Override
    public String getAuthority() {
        return nomePermissao;
    }

}
