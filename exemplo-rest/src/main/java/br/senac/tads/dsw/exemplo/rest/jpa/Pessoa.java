package br.senac.tads.dsw.exemplo.rest.jpa;

import java.time.LocalDate;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_pessoa")
public class Pessoa {

    @Id
    @SequenceGenerator(name = "sq_pessoa_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_pessoa_id")
    private Integer id;

    // @NotBlank
    @Column(nullable = false, unique = true)
    private String apelido;

    @Column(nullable = false)
    private String nome;
   
    private LocalDate dataNascimento;

    @Column(nullable = false)
    private String email;

    private String telefone;

    private String senha;

    @OneToMany(mappedBy = "pessoa")
    private Set<FotoPessoa> fotos;

    @ManyToMany
    @JoinTable(name = "tb_pessoa_interesse",
        joinColumns = @JoinColumn(name = "pessoa_id"),
        inverseJoinColumns = @JoinColumn(name = "interesse_id"))
    private Set<Interesse> interesses;

    public Pessoa() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Set<FotoPessoa> getFotos() {
        return fotos;
    }

    public void setFotos(Set<FotoPessoa> fotos) {
        this.fotos = fotos;
    }

    public Set<Interesse> getInteresses() {
        return interesses;
    }

    public void setInteresses(Set<Interesse> interesses) {
        this.interesses = interesses;
    }


}
