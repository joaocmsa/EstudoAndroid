package br.com.joaoc.estudoalura.ui.activity;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import br.com.joaoc.estudoalura.R;
import br.com.joaoc.estudoalura.dao.AlunoDAO;
import br.com.joaoc.estudoalura.model.Aluno;

import static br.com.joaoc.estudoalura.ui.activity.ConstanteActivities.CHAVE_ALUNO;

public class FormularioAlunoActivity extends AppCompatActivity {

    private static final String TITULO_APP_BAR_NOVO_ALUNO = "Cadastro de aluno";
    private static final String TITULO_APP_BAR_EDITA_ALUNO = "Edição de cadastro";
    private EditText edtNome;
    private EditText edtTelefone;
    private EditText edtEmail;
    private final AlunoDAO alunoDao = new AlunoDAO();
    private Aluno aluno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_aluno);

        iniciaCampos();
        carregaAluno();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        switch (itemId) {
            case R.id.activity_formulario_aluno_menu_salvar:
                finalizaFormulario();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_formulario_aluno_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    private void carregaAluno() {
        Intent dados = getIntent();
        if (dados.hasExtra(CHAVE_ALUNO)) {
            setTitle(TITULO_APP_BAR_EDITA_ALUNO);
            aluno = (Aluno) dados.getSerializableExtra(CHAVE_ALUNO);
            preencheCampos();
        } else {
            setTitle(TITULO_APP_BAR_NOVO_ALUNO);
            aluno = new Aluno();
        }
    }

    private void preencheCampos() {
        edtNome.setText(aluno.getNome());
        edtTelefone.setText(aluno.getTelefone());
        edtEmail.setText(aluno.getEmail());
    }

    private void finalizaFormulario() {
        preencheAluno();
        if (aluno.temIdValido()) {
            alunoDao.editarAluno(aluno);
        } else {
            alunoDao.salvar(aluno);
        }
        finish();
    }

    private void iniciaCampos() {
        edtNome = findViewById(R.id.activity_formulario_aluno_nome);
        edtTelefone = findViewById(R.id.activity_formulario_aluno_telefone);
        edtEmail = findViewById(R.id.activity_formulario_aluno_email);
    }

    private void preencheAluno() {
        aluno.setNome(edtNome.getText().toString());
        aluno.setTelefone(edtTelefone.getText().toString());
        aluno.setEmail(edtEmail.getText().toString());
    }
}
