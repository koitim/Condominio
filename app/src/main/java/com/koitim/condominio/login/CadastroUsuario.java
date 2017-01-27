package com.koitim.condominio.login;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.koitim.condominio.R;
import com.koitim.condominio.interfaces.OnLoginInteractionListener;

public class CadastroUsuario extends Fragment implements View.OnClickListener {

  private EditText etNome;
  private EditText etEmail;
  private EditText etSenha;

  // TODO: Rename parameter arguments, choose names that match
  // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
  private static final String ARG_PARAM1 = "param1";
  private static final String ARG_PARAM2 = "param2";

  // TODO: Rename and change types of parameters
  private String mParam1;
  private String mParam2;

  private OnLoginInteractionListener mListener;

  public CadastroUsuario() {
    // Required empty public constructor
  }

  /**
   * Use this factory method to create a new instance of
   * this fragment using the provided parameters.
   *
   * @param param1 Parameter 1.
   * @param param2 Parameter 2.
   * @return A new instance of fragment CadastroUsuario.
   */
  // TODO: Rename and change types and number of parameters
  public static CadastroUsuario newInstance(String param1, String param2) {
    CadastroUsuario fragment = new CadastroUsuario();
    Bundle args = new Bundle();
    args.putString(ARG_PARAM1, param1);
    args.putString(ARG_PARAM2, param2);
    fragment.setArguments(args);
    return fragment;
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (getArguments() != null) {
      mParam1 = getArguments().getString(ARG_PARAM1);
      mParam2 = getArguments().getString(ARG_PARAM2);
    }
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.login_cadastro_usuario_layout, container, false);
    etNome = (EditText) view.findViewById(R.id.login_cadastro_et_nome);
    etEmail = (EditText) view.findViewById(R.id.login_cadastro_et_email);
    etSenha = (EditText) view.findViewById(R.id.login_cadastro_et_senha);
    Button btCadastro = (Button) view.findViewById(R.id.login_cadastro_bt_cadastrar);
    btCadastro.setOnClickListener(this);
    return view;
  }

  @Override
  public void onAttach(Context context) {
    super.onAttach(context);
    if (context instanceof OnLoginInteractionListener) {
      mListener = (OnLoginInteractionListener) context;
    } else {
      throw new RuntimeException(context.toString()
              + " deve implementar OnLoginInteractionListener");
    }
  }

  @Override
  public void onDetach() {
    super.onDetach();
    mListener = null;
  }

  @Override
  public void onClick(View view) {
    boolean ehValido = true;
    String nome = etNome.getText().toString();
    if (!mListener.validarNome(nome)) {
      etNome.setError("Nome inválido!");
      ehValido = false;
    }
    String email = etEmail.getText().toString();
    if (!mListener.validarEmail(email)) {
      etEmail.setError("Email inválido!");
      ehValido = false;
    }
    String senha = etSenha.getText().toString();
    if (!mListener.validarSenha(senha)) {
      etSenha.setError("Senha inválida!");
      ehValido = false;
    }
    if (ehValido) {
      if (mListener.cadastrar(nome, email, senha)) {
        Toast.makeText(getActivity().getApplicationContext(), "Cadastro realizado com sucesso!", Toast.LENGTH_LONG).show();
        mListener.exibirLogin();
      } else {
        Snackbar.make(view,
                "Email já cadastrado",
                Snackbar.LENGTH_LONG).show();
      }
    } else {
      Snackbar.make(view,
              "Dados inválidos!",
              Snackbar.LENGTH_LONG).show();
    }
  }
}
