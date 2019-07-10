package br.com.joaoc.estudoalura.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.joaoc.estudoalura.model.Aluno;

public class AlunoDAO {

    private final static List<Aluno> alunos = new ArrayList<Aluno>();
    private static int countId = 1;

    public void salvar(Aluno aluno) {
        aluno.setId(countId);
        alunos.add(aluno);
        incrementeIds();
    }

    private void incrementeIds() {
        countId++;
    }

    public void editarAluno(Aluno aluno) {
        Aluno alunoSelecionado = buscaAlunoPeloId(aluno);
        if (alunoSelecionado != null) {
            int posicaoAluno = alunos.indexOf(alunoSelecionado);
            alunos.set(posicaoAluno, aluno);
        }

    }

    private Aluno buscaAlunoPeloId(Aluno aluno) {
        for (Aluno a : alunos) {
            if (a.getId() == aluno.getId()) {
                return a;
            }
        }
        return null;
    }

    public List<Aluno> todos() {
        return new ArrayList<>(alunos);
    }

    public void remover(Aluno alunoSelecionado) {
        Aluno aluno = buscaAlunoPeloId(alunoSelecionado);
        if (aluno != null) {
            alunos.remove(aluno);
        }
    }
}
