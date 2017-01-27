package com.koitim.condominio.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.koitim.condominio.model.Aviso;
import com.koitim.condominio.model.Usuario;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by 97903736515 on 20/01/17.
 */

public class AvisoDAO extends GenericDAO<Aviso> {

  private Context context;

  public AvisoDAO(Context context) {
    super(context);
    this.context = context;
  }

  @Override
  public void insert(Aviso obj) {
    mDatabase.insert(Aviso.TABELA, null, getContentValues(obj));
  }

  @Override
  public void update(Aviso obj) {
    mDatabase.update(Aviso.TABELA, getContentValues(obj), "id = ?", new String[]{obj.getId().toString()});
  }

  @Override
  public void delete(Aviso obj) {
    mDatabase.delete(Aviso.TABELA, "id = ?", new String[]{obj.getId().toString()});
  }

  @Override
  public Aviso find(int id) {
    Aviso aviso;

    Cursor cursor = mDatabase.query(
            Aviso.TABELA, null, Aviso.ID+"=?1", new String[]{String.valueOf(id)}, null, null, null
    );
    if (cursor.getCount() != 1) {
      return null;
    }
    cursor.moveToFirst();

    UsuarioDAO usuarioDAO = new UsuarioDAO(context);
    Usuario usuario = usuarioDAO.find((int) cursor.getLong(cursor.getColumnIndex(Aviso.USUARIO)));

    Date dataInicio = null;
    Date dataFim = null;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
    String sDataInicio = cursor.getString(cursor.getColumnIndex(Aviso.DATA_INICIO));
    String sDataFim = cursor.getString(cursor.getColumnIndex(Aviso.DATA_INICIO));
    try {
      dataInicio = sdf.parse(sDataInicio);
      dataFim = sdf.parse(sDataFim);
    } catch (ParseException e) {
      e.printStackTrace();
    }

    aviso = new Aviso(
            cursor.getLong(cursor.getColumnIndex(Aviso.ID)),
            usuario,
            cursor.getString(cursor.getColumnIndex(Aviso.TITULO)),
            cursor.getString(cursor.getColumnIndex(Aviso.TEXTO)),
            dataInicio, dataFim
    );
    return aviso;
  }

  @Override
  public List<Aviso> findAll() {
    Aviso aviso;
    List<Aviso> avisoList = new ArrayList();
    String sql = "SELECT * FROM " + Aviso.TABELA;
    Cursor cursor = mDatabase.rawQuery(sql, null);
    UsuarioDAO usuarioDAO = new UsuarioDAO(context);
    Usuario usuario;
    while (cursor.moveToNext()) {

      usuario = usuarioDAO.find((int) cursor.getLong(cursor.getColumnIndex(Aviso.USUARIO)));

      Date dataInicio = null;
      Date dataFim = null;
      SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
      String sDataInicio = cursor.getString(cursor.getColumnIndex(Aviso.DATA_INICIO));
      String sDataFim = cursor.getString(cursor.getColumnIndex(Aviso.DATA_FIM));
      try {
        if (sDataInicio != null) {
          dataInicio = sdf.parse(sDataInicio);
        }
        if (sDataFim != null) {
          dataFim = sdf.parse(sDataFim);
        }
      } catch (ParseException e) {
        e.printStackTrace();
      }

      aviso = new Aviso(
              cursor.getLong(cursor.getColumnIndex(Aviso.ID)),
              usuario,
              cursor.getString(cursor.getColumnIndex(Aviso.TITULO)),
              cursor.getString(cursor.getColumnIndex(Aviso.TEXTO)),
              dataInicio, dataFim
      );
      avisoList.add(aviso);
    }
    return avisoList;
  }

  @Override
  public ContentValues getContentValues(Aviso obj) {
    ContentValues contentValues = new ContentValues();

    contentValues.put(Aviso.ID, obj.getId());
    contentValues.put(Aviso.USUARIO, obj.getUsuario().getId());
    contentValues.put(Aviso.TITULO, obj.getTitulo());
    contentValues.put(Aviso.TEXTO, obj.getTexto());

    Calendar c = Calendar.getInstance();
    Date dataInicial = obj.getDataInicio();
    if (dataInicial != null) {
      c.setTime(dataInicial);
      int mes = c.get(Calendar.MONTH) + 1;
      String sDataInicial = String.valueOf(c.get(Calendar.YEAR)) +
              String.valueOf(mes) +
              String.valueOf(c.get(Calendar.DAY_OF_MONTH));
      contentValues.put(Aviso.DATA_INICIO, sDataInicial);
    }

    Date dataFinal = obj.getDataFinal();
    if (dataFinal != null) {
      c.setTime(dataFinal);
      int mes = c.get(Calendar.MONTH) + 1;
      String sDataFinal = String.valueOf(c.get(Calendar.YEAR)) +
              String.valueOf(mes) +
              String.valueOf(c.get(Calendar.DAY_OF_MONTH));
      contentValues.put(Aviso.DATA_FIM, sDataFinal);
    }

    return contentValues;
  }
}
