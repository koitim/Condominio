package com.koitim.condominio.model;

import java.util.Date;

/**
 * Created by 97903736515 on 20/01/17.
 */

public class Aviso implements IModel {

  public static final String TABELA = "aviso";
  public static final String ID = "id";
  public static final String USUARIO = "id_usuario";
  public static final String TITULO = "titulo";
  public static final String TEXTO = "texto";
  public static final String DATA_INICIO = "data_inicio";
  public static final String DATA_FIM = "data_fim";

  private Long id;
  private Usuario usuario;
  private String titulo;
  private String texto;
  private Date dataInicio;
  private Date dataFinal;

  public Aviso(Long id, Usuario usuario, String titulo, String texto, Date dataInicio, Date dataFinal) {
    this.id = id;
    this.usuario = usuario;
    this.titulo = titulo;
    this.texto = texto;
    this.dataInicio = dataInicio;
    this.dataFinal = dataFinal;
  }

  public Aviso(Long id, String titulo, String texto, Date dataInicio, Date dataFinal) {
    this(id, null, titulo, texto, dataInicio, dataFinal);
  }

  public Aviso(Usuario usuario, String titulo, String texto, Date dataInicio, Date dataFinal) {
    this(null, usuario, titulo, texto, dataInicio, dataFinal);
  }

  public Aviso(String titulo, String texto, Date dataInicio, Date dataFinal) {
    this(null, null, titulo, texto, dataInicio, dataFinal);
  }

  public Aviso(Long id, Usuario usuario, String titulo, String texto) {
    this(id, usuario, titulo, texto, null, null);
  }

  public Aviso(Long id, String titulo, String texto) {
    this(id, null, titulo, texto, null, null);
  }

  public Aviso(Usuario usuario, String titulo, String texto) {
    this(null, usuario, titulo, texto, null, null);
  }

  public Aviso(String titulo, String texto) {
    this(null, null, titulo, texto, null, null);
  }

  @Override
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Usuario getUsuario() {
    return usuario;
  }

  public void setUsuario(Usuario usuario) {
    this.usuario = usuario;
  }

  public String getTitulo() {
    return titulo;
  }

  public void setTitulo(String titulo) {
    this.titulo = titulo;
  }

  public String getTexto() {
    return texto;
  }

  public void setTexto(String texto) {
    this.texto = texto;
  }

  public Date getDataInicio() {
    return dataInicio;
  }

  public void setDataInicio(Date dataInicio) {
    this.dataInicio = dataInicio;
  }

  public Date getDataFinal() {
    return dataFinal;
  }

  public void setDataFinal(Date dataFinal) {
    this.dataFinal = dataFinal;
  }
}
