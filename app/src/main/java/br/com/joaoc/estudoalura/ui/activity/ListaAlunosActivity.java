package br.com.joaoc.estudoalura.ui.activity;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import br.com.joaoc.estudoalura.R;
import br.com.joaoc.estudoalura.dao.AlunoDAO;
import br.com.joaoc.estudoalura.model.Aluno;

import static br.com.joaoc.estudoalura.ui.activity.ConstanteActivities.CHAVE_ALUNO;

public class  ListaAlunosActivity extends AppCompatActivity {

    public static final String TITULO_APP_BAR = "Lista de alunos";
    private final AlunoDAO alunoDAO = new AlunoDAO();
    private ListView listaAlunos;
    private FloatingActionButton fabNovoAluno;
    private ArrayAdapter<Aluno> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);
        setTitle(TITULO_APP_BAR);
        configuraFabNovoAluno();
        configuraListaAlunos();
        alunoDAO.salvar(new Aluno("João", "123", "joao@email.com"));
        alunoDAO.salvar(new Aluno("Francisco", "234", "chico@email.com"));
    }

    private void configuraListaAlunos() {
        listaAlunos = findViewById(R.id.activity_main_lista_alunos_listview);
        configuraAdapter(listaAlunos);
        configuraListenerOnClickItem(listaAlunos);
        registerForContextMenu(listaAlunos);
    }

    private void configuraFabNovoAluno() {
        fabNovoAluno = findViewById(R.id.activity_lista_alunos_fab_novo_aluno);
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
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        Aluno alunoSelecionado = adapter.getItem(menuInfo.position);
        switch (itemId) {
            case R.id.activity_lista_aluno_menu_remover:
                removeAluno(alunoSelecionado);
                break;
            default:
                break;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        atualizaLista();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.activity_lista_alunos_menu, menu);
    }

    private void atualizaLista() {
        adapter.clear();
        adapter.addAll(alunoDAO.todos());
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

    private void removeAluno(Aluno aluno) {
        alunoDAO.remover(aluno);
        adapter.remove(aluno);
    }

    private void abreFormularioEditaAluno(Aluno aluno) {
        Intent goFormularioAluno = new Intent(ListaAlunosActivity.this, FormularioAlunoActivity.class);
        goFormularioAluno.putExtra(CHAVE_ALUNO, aluno);
        startActivity(goFormularioAluno);
    }

    private void configuraAdapter(ListView listaAlunos) {
        adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1);
        listaAlunos.setAdapter(adapter
        );
    }
}
