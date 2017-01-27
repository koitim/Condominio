package com.koitim.condominio.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.koitim.condominio.model.Usuario;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 97903736515 on 16/01/17.
 */

public class UsuarioDAO extends GenericDAO<Usuario> {

  public UsuarioDAO(Context context) {
    super(context);
  }

  @Override
  public void insert(Usuario obj) {
    mDatabase.insert(Usuario.TABELA, null, getContentValues(obj));
  }

  @Override
  public void update(Usuario obj) {
    //TODO: Mudar para não atualizar a senha
    mDatabase.update(Usuario.TABELA, getContentValues(obj), "id = ?", new String[]{obj.getId().toString()});
  }

  @Override
  public void delete(Usuario obj) {
    mDatabase.delete(Usuario.TABELA, "id = ?", new String[]{obj.getId().toString()});
  }

  @Override
  public Usuario find(int id) {
    Usuario usuario = null;

    Cursor cursor = mDatabase.query(
            Usuario.TABELA, null, Usuario.ID+"=?1", new String[]{String.valueOf(id)}, null, null, null
    );
    if (cursor.getCount() != 1) {
      return null;
    }
    cursor.moveToFirst();
    usuario = new Usuario(
            cursor.getLong(cursor.getColumnIndex(Usuario.ID)),
            cursor.getString(cursor.getColumnIndex(Usuario.NOME)),
            cursor.getString(cursor.getColumnIndex(Usuario.EMAIL))
    );
    return usuario;
  }

  public boolean existeEmail(String email) {
    Cursor cursor = mDatabase.query(
            Usuario.TABELA, null, Usuario.EMAIL+"=?1", new String[]{String.valueOf(email)}, null, null, null
    );
    return (cursor.getCount() != 0?true:false);
  }

  public Long getIdUsuario(String email, String senha) {
    Long usuario = Long.valueOf(0);
    Cursor cursor = mDatabase.query(
            Usuario.TABELA, null, Usuario.EMAIL+"=?1 AND "+Usuario.SENHA+"=?2",
            new String[]{email, senha}, null, null, null
    );
    if (cursor.getCount() != 1) {
      return null;
    }
    cursor.moveToFirst();
    usuario = cursor.getLong(cursor.getColumnIndex(Usuario.ID));
    return usuario;
  }

  @Override
  public List<Usuario> findAll() {
    Usuario usuario = null;
    List<Usuario> usuarioList = new ArrayList();
    String sql = "SELECT * FROM " + Usuario.TABELA;
    Cursor cursor = mDatabase.rawQuery(sql, null);
    while (cursor.moveToNext()) {
      usuario = new Usuario(
              cursor.getLong(cursor.getColumnIndex(Usuario.ID)),
              cursor.getString(cursor.getColumnIndex(Usuario.NOME)),
              cursor.getString(cursor.getColumnIndex(Usuario.EMAIL))
      );
      usuarioList.add(usuario);
    }
    return usuarioList;
  }

  @Override
  public ContentValues getContentValues(Usuario obj) {
    ContentValues contentValues = new ContentValues();

    contentValues.put(Usuario.ID, obj.getId());
    contentValues.put(Usuario.NOME, obj.getNome());
    contentValues.put(Usuario.EMAIL, obj.getEmail());
    contentValues.put(Usuario.SENHA, obj.getSenha());

    return contentValues;
  }
}
