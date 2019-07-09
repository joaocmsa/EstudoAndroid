package br.com.joaoc.estudoalura.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.com.joaoc.estudoalura.R;
import br.com.joaoc.estudoalura.dao.AlunoDAO;
import br.com.joaoc.estudoalura.model.Aluno;

public class FormularioAlunoActivity extends AppCompatActivity {

    public static final String TITULO_APP_BAR = "Cadastro de novo aluno";
    private EditText edtNome;
    private EditText edtTelefone;
    private EditText edtEmail;
    private Button btnSalvar;
    private final AlunoDAO alunoDAO = new AlunoDAO();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_aluno);
        setTitle(TITULO_APP_BAR);

        iniciaCampos();
        configuraButtonSalvar();

    }

    private void configuraButtonSalvar() {
        btnSalvar = findViewById(R.id.activity_formulario_aluno_button_salvar);
        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Aluno aluno = criaAluno();
                salvarAluno(aluno);
            }
        });
    }

    private void iniciaCampos() {
        edtNome = findViewById(R.id.activity_formulario_aluno_nome);
        edtTelefone = findViewById(R.id.activity_formulario_aluno_telefone);
        edtEmail = findViewById(R.id.activity_formulario_aluno_email);
    }

    private void salvarAluno(Aluno aluno) {
        alunoDAO.salvar(aluno);
        finish();
    }

    private Aluno criaAluno() {
        String nome = edtNome.getText().toString();
        String telefone = edtTelefone.getText().toString();
        String email = edtEmail.getText().toString();

        return new Aluno(nome, telefone, email);
    }
}
