package br.com.joaoc.estudoalura.ui.activity;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import br.com.joaoc.estudoalura.R;
import br.com.joaoc.estudoalura.dao.AlunoDAO;

public class  ListaAlunosActivity extends AppCompatActivity {

    public static final String TITULO_APP_BAR = "Lista de alunos";
    private final AlunoDAO alunoDAO = new AlunoDAO();
    private ListView listaAlunos;
    private FloatingActionButton fabNovoAluno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);
        setTitle(TITULO_APP_BAR);

        configuraFabNovoAluno();
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
    protected void onResume() {
        super.onResume();
        configuraListaAlunos();
    }

    private void abreFormularioNovoAluno() {
        startActivity(new Intent(ListaAlunosActivity.this,
                FormularioAlunoActivity.class));
    }

    private void configuraListaAlunos() {
        listaAlunos = findViewById(R.id.activity_main_lista_alunos_listview);
        listaAlunos.setAdapter(new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                alunoDAO.todos())
        );
    }
}
