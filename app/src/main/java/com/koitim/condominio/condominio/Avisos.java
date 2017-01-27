package com.koitim.condominio.condominio;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ToggleButton;

import com.koitim.condominio.R;
import com.koitim.condominio.adapter.AvisosAdapter;
import com.koitim.condominio.dao.AvisoDAO;
import com.koitim.condominio.interfaces.OnAvisoInteractionListener;
import com.koitim.condominio.util.SpacesItemDecoration;

import java.util.List;


public class Avisos extends Fragment implements View.OnClickListener {

  private List<com.koitim.condominio.model.Aviso> avisos;
  private AvisosAdapter mAdapter;
  private boolean bGeral;
  private boolean bEspecifico;

  private RecyclerView rvAvisos;
  private ToggleButton tbGeral;
  private ToggleButton tbEspecifico;

  private OnAvisoInteractionListener mListener;

  public Avisos() {
    // Required empty public constructor
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    AvisoDAO avisoDAO = new AvisoDAO(getActivity().getApplicationContext());
    avisos = avisoDAO.findAll();
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.condominio_aviso_layout, container, false);

    rvAvisos = (RecyclerView) view.findViewById(R.id.aviso_rv);
    rvAvisos.addItemDecoration(new SpacesItemDecoration(2));
    rvAvisos.setHasFixedSize(true);

    int scrollPosition = 0;
    if (rvAvisos.getLayoutManager() != null) {
      scrollPosition = ((LinearLayoutManager) rvAvisos.getLayoutManager())
              .findFirstCompletelyVisibleItemPosition();
    }

    rvAvisos.setLayoutManager(new LinearLayoutManager(getActivity()));
    rvAvisos.scrollToPosition(scrollPosition);
    rvAvisos.setItemAnimator(new DefaultItemAnimator());

    tbGeral = (ToggleButton) view.findViewById(R.id.aviso_tb_geral);
    tbEspecifico = (ToggleButton) view.findViewById(R.id.aviso_tb_especifico);
    tbGeral.setOnClickListener(this);
    tbEspecifico.setOnClickListener(this);
    bGeral = true;
    tbGeral.setChecked(bGeral);
    bEspecifico = false;
    tbEspecifico.setChecked(bEspecifico);

    mAdapter = new AvisosAdapter(avisos, mListener);
    rvAvisos.setAdapter(mAdapter);

    return view;
  }

  @Override
  public void onAttach(Context context) {
    super.onAttach(context);
    if (context instanceof OnAvisoInteractionListener) {
      mListener = (OnAvisoInteractionListener) context;
    } else {
      throw new RuntimeException(context.toString()
              + " deve implementar OnAvisoInteractionListener");
    }
  }

  @Override
  public void onDetach() {
    super.onDetach();
    mListener = null;
  }

  @Override
  public void onClick(View v) {
    if (v.getId() == R.id.aviso_tb_geral) {
      if (!bGeral) {
        bEspecifico = !bEspecifico;
        bGeral = !bGeral;
        tbEspecifico.setChecked(bEspecifico);
        mAdapter.getFilter().filter(null);
      }
      tbGeral.setChecked(bGeral);
    } else {
      if (!bEspecifico) {
        bEspecifico = !bEspecifico;
        bGeral = !bGeral;
        tbGeral.setChecked(bGeral);
        mAdapter.getFilter().filter(mListener.getUsuario().getNome());
      }
      tbEspecifico.setChecked(bEspecifico);
    }
  }
}
