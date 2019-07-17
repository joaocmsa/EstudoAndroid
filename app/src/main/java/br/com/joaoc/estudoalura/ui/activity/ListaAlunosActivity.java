package br.com.joaoc.estudoalura.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.appcompat.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import br.com.joaoc.estudoalura.R;
import br.com.joaoc.estudoalura.model.Aluno;
import br.com.joaoc.estudoalura.ui.ListaAlunosView;

import static br.com.joaoc.estudoalura.ui.activity.ConstanteActivities.CHAVE_ALUNO;

public class ListaAlunosActivity extends AppCompatActivity {

    private static final String TITULO_APP_BAR = "Lista de alunos";
    private final ListaAlunosView listaAlunosView = new ListaAlunosView(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);
        setTitle(TITULO_APP_BAR);
        configuraFabNovoAluno();
        configuraListaAlunos();
    }

    private void configuraListaAlunos() {
        ListView listaAlunos = findViewById(R.id.activity_main_lista_alunos_listview);
        listaAlunosView.configuraAdapter(listaAlunos);
        configuraListenerOnClickItem(listaAlunos);
        registerForContextMenu(listaAlunos);
    }

    private void configuraFabNovoAluno() {
        FloatingActionButton fabNovoAluno = findViewById(R.id.activity_lista_alunos_fab_novo_aluno);
        fabNovoAluno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abreFormularioNovoAluno();
            }
        });
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int itemId = item.getItemId();


        switch (itemId) {
            case R.id.activity_lista_aluno_menu_remover:
                listaAlunosView.confimarRemocao(item);
                break;
            default:
                break;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        listaAlunosView.atualizaLista();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.activity_lista_alunos_menu, menu);
    }

    private void abreFormularioNovoAluno() {
        startActivity(new Intent(ListaAlunosActivity.this,
                FormularioAlunoActivity.class));
    }

    private void configuraListenerOnClickItem(ListView listaAlunos) {
        listaAlunos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Aluno alunoSelecionado = (Aluno) adapterView.getItemAtPosition(position);
                abreFormularioEditaAluno(alunoSelecionado);
            }
        });
    }

    private void abreFormularioEditaAluno(Aluno aluno) {
        Intent goFormularioAluno = new Intent(ListaAlunosActivity.this, FormularioAlunoActivity.class);
        goFormularioAluno.putExtra(CHAVE_ALUNO, aluno);
        startActivity(goFormularioAluno);
    }

}
