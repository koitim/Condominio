package com.koitim.condominio.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.koitim.condominio.model.Usuario;

/**
 * Created by 97903736515 on 16/01/17.
 */

public class DataBaseHelper extends SQLiteOpenHelper {

  private static final String DATABASE_NAME = "condominio.db";
  private static final int DATABASE_VERSION = 3;

  public DataBaseHelper(Context context) {
    super(context, DATABASE_NAME, null, DATABASE_VERSION);
  }

  @Override
  public void onCreate(SQLiteDatabase db) {
    // Usuario
    String sql = "CREATE TABLE usuario (id INTEGER PRIMARY KEY, nome TEXT, email TEXT, senha TEXT);";
    db.execSQL(sql);
    // Avisos
    sql = "CREATE TABLE aviso (id INTEGER PRIMARY KEY, id_usuario INTEGER, titulo TEXT, texto TEXT, data_inicio TEXT, data_fim TEXT, ativo INTEGER);";
    db.execSQL(sql);
    inicializaAvisos(db);
  }

  private void inicializaAvisos(SQLiteDatabase db) {
    String sql = "INSERT INTO aviso (id_usuario, titulo, texto, data_inicio, data_fim, ativo) VALUES (1 , 'Título 1', 'Texto 1', '20170112', '20170212', 1);";
    db.execSQL(sql);
    sql = "INSERT INTO aviso (titulo, texto, data_inicio, data_fim, ativo) VALUES ('Título 2', 'Texto 2', '20170102', '20170212', 1);";
    db.execSQL(sql);
    sql = "INSERT INTO aviso (titulo, texto, data_inicio, ativo) VALUES ('Título 3', 'Texto 3', '20170120', 1);";
    db.execSQL(sql);
    sql = "INSERT INTO aviso (titulo, texto, data_fim, ativo) VALUES ('Título 4', 'Texto 4', '20170220', 1);";
    db.execSQL(sql);
    sql = "INSERT INTO aviso (titulo, texto, ativo) VALUES ('Título 5', 'Texto 5', 1);";
    db.execSQL(sql);
  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    if (oldVersion == 2 && newVersion == 3) {
      String sql = "ALTER TABLE aviso ADD COLUMN ativo INTEGER;";
      db.execSQL(sql);
      sql = "UPDATE aviso SET ativo = 1;";
      db.execSQL(sql);
    }
  }
}
