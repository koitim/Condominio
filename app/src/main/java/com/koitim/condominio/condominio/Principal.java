package com.koitim.condominio.condominio;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.koitim.condominio.BaseActivity;
import com.koitim.condominio.R;
import com.koitim.condominio.dao.UsuarioDAO;
import com.koitim.condominio.interfaces.OnAvisoInteractionListener;
import com.koitim.condominio.model.Usuario;

public class Principal extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener, OnAvisoInteractionListener {

  private static final int HOME = 1;
  private static final int AVISOS = 2;
  private static final int DECK = 3;
  private static final int SALAO_FESTAS = 4;

  private int telaAtual;

  private Usuario usuario;

  private TextView tvUsuario;
  private NavigationView navigationView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.candominio_activity);

    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
            this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
    drawer.setDrawerListener(toggle);
    toggle.syncState();

    navigationView = (NavigationView) findViewById(R.id.nav_view);
    navigationView.setNavigationItemSelectedListener(this);

    Intent it = getIntent();
    int idUsuario = it.getIntExtra("idUsuario", 0);
    UsuarioDAO usuarioDAO = new UsuarioDAO(this);
    usuario = usuarioDAO.find(idUsuario);

    tvUsuario = (TextView) navigationView.getHeaderView(0).findViewById(R.id.condominio_header_menu_tv_usuario);
    tvUsuario.setText(usuario.getNome());

    exibirHome();
  }

  @Override
  public void onBackPressed() {
    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    if (drawer.isDrawerOpen(GravityCompat.START)) {
      drawer.closeDrawer(GravityCompat.START);
    } else {
      if (telaAtual == HOME) {
        logout();
      } else {
        exibirHome();
      }
    }
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    int id = item.getItemId();

    if (id == R.id.action_settings) {
      return true;
    }

    return super.onOptionsItemSelected(item);
  }

  @SuppressWarnings("StatementWithEmptyBody")
  @Override
  public boolean onNavigationItemSelected(MenuItem item) {
    int id = item.getItemId();

    switch (id) {
      case R.id.nav_home:
        exibirHome();
        break;
      case R.id.nav_avisos:
        exibirAvisos();
        break;
      case R.id.nav_deck:
        exibirDeck();
        break;
      case R.id.nav_salao_festas:
        exibirSalaoFestas();
        break;
      case R.id.nav_sair:
        logout();
    }
    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    drawer.closeDrawer(GravityCompat.START);
    return true;
  }

  private void logout() {
    //TODO: Limpar variáveis por segurança???
    AlertDialog.Builder builder = new AlertDialog.Builder(this)
            .setCancelable(false)
            .setMessage(R.string.pergunta_sair)
            .setPositiveButton(R.string.sim, new DialogInterface.OnClickListener() {
              @Override
              public void onClick(DialogInterface dialogInterface, int i) {
                Intent it = new Intent(Principal.this, com.koitim.condominio.login.Principal.class);
                startActivity(it);
                finish();

                dialogInterface.dismiss();
              }
            })
            .setNegativeButton(R.string.nao, new DialogInterface.OnClickListener() {
              @Override
              public void onClick(DialogInterface dialogInterface, int i) {
                marcarMenu(telaAtual);
                dialogInterface.dismiss();
              }
            });
    AlertDialog dialog = builder.create();
    dialog.show();
  }

  private void marcarMenu(int tela) {
    switch (tela) {
      case HOME:
        navigationView.setCheckedItem(R.id.nav_home);
        break;
      case AVISOS:
        navigationView.setCheckedItem(R.id.nav_avisos);
        break;
      case DECK:
        navigationView.setCheckedItem(R.id.nav_deck);
        break;
      case SALAO_FESTAS:
        navigationView.setCheckedItem(R.id.nav_salao_festas);
        break;
    }
  }

  @Override
  protected void carregaFragmento(int tela) {
    //TODO: Descomentar cada fragmento depois que eles estiverem criados
    switch (tela) {
      case HOME:
        fragmento = new Home();
        break;
      case AVISOS:
        fragmento = new Avisos();
        break;
      case DECK:
        //fragmento = new Deck();
        break;
      case SALAO_FESTAS:
        //fragmento = new SalaoFestas();
        break;
    }
    telaAtual = tela;
  }

  public void exibirHome() {
    marcarMenu(HOME);
    exibeTela(R.id.content_main, HOME);
  }

  public void exibirAvisos() {
    marcarMenu(AVISOS);
    exibeTela(R.id.content_main, AVISOS);
  }

  public void exibirDeck() {
    marcarMenu(DECK);
    exibeTela(R.id.content_main, DECK);
  }

  public void exibirSalaoFestas() {
    marcarMenu(SALAO_FESTAS);
    exibeTela(R.id.content_main, SALAO_FESTAS);
  }

  @Override
  public Usuario getUsuario() {
    return usuario;
  }
}
