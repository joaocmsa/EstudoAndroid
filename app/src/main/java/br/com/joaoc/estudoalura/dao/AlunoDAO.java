package br.com.joaoc.estudoalura.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.joaoc.estudoalura.model.Aluno;

public class AlunoDAO {

    private final static List<Aluno> alunos = new ArrayList<Aluno>();

    public void salvar(Aluno aluno) {
        alunos.add(aluno);
    }

    public List<Aluno> todos() {
        return new ArrayList<>(alunos);
    }
}
