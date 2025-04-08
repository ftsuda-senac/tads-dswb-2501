package br.senac.tads.dsw.exemplo.rest;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.senac.tads.dsw.exemplo.rest.validation.SenhasIguais;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;

@SenhasIguais
public class DadosDto {
  private Integer id;

  //@NotNull
  //@NotEmpty
  @NotBlank
  private String nome;

  @NotBlank(message = "Preencha isso seu animal")
  private String apelido;

  @PastOrPresent
  private LocalDate dataNascimento;

  @NotBlank
  @Email
  private String email;

  private String telefone;

  @JsonProperty(access = JsonProperty.Access.READ_ONLY)
  private List<String> interesses;

  private String senha;

  private String senhaConfirmacao;

  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  private List<Integer> interessesIds;

  public DadosDto() {
  }

  public DadosDto(Integer id, String nome, String dataNascimento,
      String email, String telefone) {
    this.id = id;
    this.nome = nome;
    this.dataNascimento = LocalDate.parse(dataNascimento);
    this.email = email;
    this.telefone = telefone;
  }

  public DadosDto(Integer id, String apelido, String nome, String dataNascimento,
      String email, String telefone, List<String> interesses) {
    this.id = id;
    this.apelido = apelido;
    this.nome = nome;
    this.dataNascimento = LocalDate.parse(dataNascimento);
    this.email = email;
    this.telefone = telefone;
    this.interesses = interesses;

  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getApelido() {
    return apelido;
  }

  public void setApelido(String apelido) {
    this.apelido = apelido;
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

  public String getSenhaConfirmacao() {
    return senhaConfirmacao;
  }

  public void setSenhaConfirmacao(String senhaConfirmacao) {
    this.senhaConfirmacao = senhaConfirmacao;
  }

  public List<String> getInteresses() {
    return interesses;
  }

  public void setInteresses(List<String> interesses) {
    this.interesses = interesses;
  }

  

}