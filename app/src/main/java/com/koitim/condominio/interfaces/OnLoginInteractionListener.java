package com.koitim.condominio.interfaces;

/**
 * Created by 97903736515 on 16/01/17.
 */

public interface OnLoginInteractionListener {

  int OK = 0;
  int EMAIL_INEXISTENTE = 1;
  int SENHA_ERRADA = 2;

  boolean validarNome(String nome);
  boolean validarEmail(String email);
  boolean validarSenha(String senha);
  int validarLogin(String email, String senha);
  int getResultadoLogin();
  void login();
  void exibirCadastro();
  void exibirLogin();
  boolean cadastrar(String nome, String email, String senha);
}
