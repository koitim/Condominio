package com.koitim.condominio.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.koitim.condominio.database.DataBaseHelper;
import com.koitim.condominio.model.IModel;

import java.util.List;

/**
 * Created by 97903736515 on 16/01/17.
 */

abstract class GenericDAO<T extends IModel> {

  SQLiteDatabase mDatabase;

  GenericDAO(Context context) {
    DataBaseHelper dbHelper = new DataBaseHelper(context);
    this.mDatabase = dbHelper.getWritableDatabase();
  }

  public abstract void insert(T obj);

  public abstract void update(T obj);

  public abstract void delete(T obj);

  public abstract T find(int id);

  public abstract List<T> findAll();

  public abstract ContentValues getContentValues(T obj);
}
