package com.koitim.condominio.model;

/**
 * Created by 97903736515 on 16/01/17.
 */

public class Usuario implements IModel{

  public static final String TABELA = "usuario";
  public static final String ID = "id";
  public static final String NOME = "nome";
  public static final String EMAIL = "email";
  public static final String SENHA = "senha";

  private Long id;
  private String nome;
  private String email;
  private String senha;

  public Usuario() {
  }

  public Usuario(Long id, String nome, String email, String senha) {
    this.id = id;
    this.nome = nome;
    this.email = email;
    this.senha = senha;
  }

  public Usuario(Long id, String nome, String email) {
    this(id, nome, email, null);
  }

  public Usuario(String nome, String email, String senha) {
    this(null, nome, email, senha);
  }

  public Usuario(String nome, String email) {
    this(null, nome, email, null);
  }

  @Override
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getSenha() {
    return senha;
  }

  public void setSenha(String senha) {
    this.senha = senha;
  }

  @Override
  public String toString() {
    return nome;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null)
      return false;
    if (!(obj instanceof Usuario))
      return false;
    Usuario usuario = (Usuario) obj;
    return usuario.getEmail().equals(this.email);
  }
}
