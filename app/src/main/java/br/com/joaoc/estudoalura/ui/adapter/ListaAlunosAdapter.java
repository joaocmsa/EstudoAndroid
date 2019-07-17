package br.com.joaoc.estudoalura.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.joaoc.estudoalura.R;
import br.com.joaoc.estudoalura.model.Aluno;

public class ListaAlunosAdapter extends BaseAdapter {

    private final List<Aluno> alunos = new ArrayList<>();
    private final Context context;
    private TextView txtNome;
    private TextView txtTelefone;

    public ListaAlunosAdapter(Context context) {
        this.context = context;
    }

    //retorna a quantidade de elementos que o adapter terá
    @Override
    public int getCount() {
        return alunos.size();
    }

    //retorna o elemento selecionado
    @Override
    public Aluno getItem(int position) {
        return alunos.get(position);
    }

    //retorna o id do elemento selecionado
    @Override
    public long getItemId(int position) {
        return alunos.get(position).getId();
    }

    //cria a nova view para cada elemento
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View viewCriada;

        if(convertView ==null){
            viewCriada = criarCard(parent);
            vinculaTextView(viewCriada);
        }else{
            viewCriada = convertView;
        }

        Aluno aluno = alunos.get(position);
        preencheTextView(aluno);

        return viewCriada;
    }

    private void vinculaTextView(View viewCriada){
        txtNome = viewCriada.findViewById(R.id.card_aluno_lista_alunos_nome);
        txtTelefone = viewCriada.findViewById(R.id.card_aluno_lista_alunos_telefone);
    }
    private void preencheTextView(Aluno aluno) {
        txtNome.setText(aluno.getNome());
        txtTelefone.setText(aluno.getTelefone());
    }

    private View criarCard(ViewGroup parent) {
        return LayoutInflater.from(context).
                inflate(R.layout.card_aluno_lista_alunos, parent, false);
    }

   public void atualizaAdapter(List<Aluno> alunos){
        this.alunos.clear();
        this.alunos.addAll(alunos);
        notifyDataSetChanged();
   }

    public void remove(Aluno aluno) {
        this.alunos.remove(aluno);
        notifyDataSetChanged();
    }
}
