package br.com.chaos.agenda.converter;

import org.json.JSONException;
import org.json.JSONStringer;

import java.util.List;

import br.com.chaos.agenda.model.Aluno;

/**
 * Created by gersonapollo on 15/06/17.
 */

public class AlunoConverter {
    public String converteParaJson(List<Aluno> alunos) {
        JSONStringer js = new JSONStringer();

        try {
            js.object().key("lista").array().object().key("aluno").array();
            for (Aluno aluno: alunos) {
                js.object();
                js.key("nome").value(aluno.getNome());
                js.key("nota").value(aluno.getNota());
                js.endObject();
            }
            js.endArray().endObject().endArray().endObject();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return js.toString();
    }
}
