package br.com.joaoc.estudoalura.ui;

import android.content.Context;
import android.content.DialogInterface;
import androidx.appcompat.app.AlertDialog;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

import br.com.joaoc.estudoalura.dao.AlunoDAO;
import br.com.joaoc.estudoalura.model.Aluno;
import br.com.joaoc.estudoalura.ui.adapter.ListaAlunosAdapter;

public class ListaAlunosView {

    private final ListaAlunosAdapter adapter;
    private final Context context;
    private final AlunoDAO alunoDAO;

    public ListaAlunosView(Context context) {
        this.context = context;
        adapter = new ListaAlunosAdapter(context);
        alunoDAO = new AlunoDAO();
    }

    public void confimarRemocao(final MenuItem item) {
        new AlertDialog.Builder(context).
                setTitle("Remoção de contato").
                setMessage("Deseja realmente remover esse contato?").
                setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                        Aluno alunoSelecionado = adapter.getItem(menuInfo.position);
                        removeAluno(alunoSelecionado);
                    }
                }).
                setNegativeButton("Não", null).show();

    }

    public void atualizaLista() {
        adapter.atualizaAdapter(alunoDAO.todos());
    }

    private void removeAluno(Aluno aluno) {
        alunoDAO.remover(aluno);
        adapter.remove(aluno);
    }

    public void configuraAdapter(ListView listaAlunos) {
        listaAlunos.setAdapter(adapter);
    }
}
