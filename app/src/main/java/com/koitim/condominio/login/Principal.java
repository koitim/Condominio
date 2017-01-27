package com.koitim.condominio.login;

import android.content.Intent;

import android.os.Bundle;
import android.text.TextUtils;

import com.koitim.condominio.BaseActivity;
import com.koitim.condominio.R;
import com.koitim.condominio.dao.UsuarioDAO;
import com.koitim.condominio.interfaces.OnLoginInteractionListener;
import com.koitim.condominio.model.Usuario;

public class Principal extends BaseActivity implements OnLoginInteractionListener {

  private static final int LOGIN = 1;
  private static final int CADASTRO = 2;

  private int telaAtual;
  private int idUsuario;
  private int resultadoLogin;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.login_activity);
    exibirLogin();
  }

  @Override
  public void onBackPressed() {
    if (telaAtual == LOGIN) {
      super.onBackPressed();
    } else {
      exibirLogin();
    }
  }

  @Override
  public boolean validarNome(String nome) {
    return TextUtils.getTrimmedLength(nome) != 0;
  }

  @Override
  public boolean validarEmail(String email) {
    //TODO: Colocar uma validação de e-mail melhor
    return email.contains("@");
  }

  @Override
  public boolean validarSenha(String senha) {
    return senha.length() > 4;
  }

  @Override
  public int validarLogin(String email, String senha) {
    UsuarioDAO dao = new UsuarioDAO(this);
    if (!dao.existeEmail(email)) {
      return EMAIL_INEXISTENTE;
    }
    Long id = dao.getIdUsuario(email, senha);
    if (id == 0) {
      return SENHA_ERRADA;
    }
    idUsuario = id.intValue();
    return OK;
  }

  @Override
  public int getResultadoLogin() {
    return resultadoLogin;
  }

  @Override
  public void login() {
    Intent it = new Intent(this, com.koitim.condominio.condominio.Principal.class);
    it.putExtra("idUsuario", idUsuario);
    startActivity(it);
    finish();
  }

  @Override
  public void exibirCadastro() {
    telaAtual = CADASTRO;
    exibeTela(R.id.login_activity, CADASTRO);
  }

  @Override
  public void exibirLogin() {
    telaAtual = LOGIN;
    exibeTela(R.id.login_activity, LOGIN);
  }

  @Override
  public boolean cadastrar(String nome, String email, String senha) {
    UsuarioDAO dao = new UsuarioDAO(this);
    if (dao.existeEmail(email)) {
      return false;
    }
    dao.insert(new Usuario(nome, email, senha));
    return true;
  }

  @Override
  protected void carregaFragmento(int tela) {
    switch (tela) {
      case LOGIN:
        fragmento = new Login();
        break;
      case CADASTRO:
        fragmento = new CadastroUsuario();
        break;
    }
  }
}

