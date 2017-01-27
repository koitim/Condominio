package com.koitim.condominio.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.TextView;

import com.koitim.condominio.R;
import com.koitim.condominio.interfaces.OnAvisoInteractionListener;
import com.koitim.condominio.model.Aviso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 97903736515 on 20/01/17.
 */

public class AvisosAdapter extends RecyclerView.Adapter<AvisosAdapter.ViewHolder>{

  private List<Aviso> avisosExibicao;
  private List<Aviso> avisos;

  private OnAvisoInteractionListener mListener;

  public AvisosAdapter(List<Aviso> avisos, OnAvisoInteractionListener mListener) {
    this.avisos = avisos;
    this.avisosExibicao = avisos;
    this.mListener = mListener;
  }

  @Override
  public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
    View v = LayoutInflater.from(viewGroup.getContext())
            .inflate(R.layout.condominio_aviso_layout_item, viewGroup, false);
    return new ViewHolder(v, mListener, avisosExibicao);
  }

  @Override
  public void onBindViewHolder(ViewHolder viewHolder, int position) {
    viewHolder.setAviso(avisosExibicao.get(position));
  }

  @Override
  public int getItemCount() {
    return avisosExibicao.size();
  }

  public static class ViewHolder extends RecyclerView.ViewHolder {

    private final TextView tvTitulo;
    private final TextView tvTexto;
    private final TextView tvUsuario;
    private final TextView tvDataInicial;
    private final TextView tvDataFinal;

    public ViewHolder(View v, final OnAvisoInteractionListener mListener, final List<Aviso> avisos) {
      super(v);
      tvTitulo = (TextView) v.findViewById(R.id.aviso_tv_titulo_item);
      tvTexto = (TextView) v.findViewById(R.id.aviso_tv_texto_item);
      tvUsuario = (TextView) v.findViewById(R.id.textView9);
      tvDataInicial = (TextView) v.findViewById(R.id.textView10);
      tvDataFinal = (TextView) v.findViewById(R.id.textView11);
    }

    public void setAviso(Aviso aviso) {
      tvTitulo.setText(aviso.getTitulo());
      tvTexto.setText(aviso.getTexto());
      if (aviso.getUsuario() != null) {
        tvUsuario.setText(aviso.getUsuario().toString());
      }
      if (aviso.getDataInicio() != null) {
        tvDataInicial.setText(aviso.getDataInicio().toString());
      }
      if (aviso.getDataFinal() != null) {
        tvDataFinal.setText(aviso.getDataFinal().toString());
      }
    }
  }

  /** Método responsável pelo filtro. Utilizaremos em um EditText
   *
   * @return
   */
  public Filter getFilter() {
    Filter filter = new Filter() {

      @Override
      protected FilterResults performFiltering(CharSequence usuario) {
        FilterResults results = new FilterResults();
        //se não foi realizado nenhum filtro insere todos os itens.
        if (usuario == null || usuario.length() == 0) {
          results.count = avisos.size();
          results.values = avisos;
        } else {
          //cria um array para armazenar os objetos filtrados.
          List<Aviso> itens_filtrados = new ArrayList<>();

          //percorre toda lista verificando se contem a palavra do filtro na descricao do objeto.
          for (Aviso aviso: avisos) {
            if (aviso.getUsuario() != null) {
              if (aviso.getUsuario().getNome().equals(usuario)) {
                //se conter adiciona na lista de itens filtrados.
                itens_filtrados.add(aviso);
              }
            }
          }
          // Define o resultado do filtro na variavel FilterResults
          results.count = itens_filtrados.size();
          results.values = itens_filtrados;
        }
        return results;
      }

      @SuppressWarnings("unchecked")
      @Override
      protected void publishResults(CharSequence constraint, Filter.FilterResults results) {
        avisosExibicao = (List<Aviso>) results.values; // Valores filtrados.
        notifyDataSetChanged();  // Notifica a lista de alteração
      }

    };
    return filter;
  }
}
