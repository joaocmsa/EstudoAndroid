package br.com.joaoc.estudoalura;

import android.app.Application;

import br.com.joaoc.estudoalura.dao.AlunoDAO;
import br.com.joaoc.estudoalura.model.Aluno;

@SuppressWarnings("WeakerAccess")
public class AgendaApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        criarListaTeste();
    }

    private void criarListaTeste() {
        AlunoDAO alunoDAO = new AlunoDAO();
        alunoDAO.salvar(new Aluno("João", "123", "joao@email.com"));
        alunoDAO.salvar(new Aluno("Francisco", "234", "chico@email.com"));
    }


}
