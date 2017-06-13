package br.com.chaos.agenda.helper;

import android.widget.EditText;
import android.widget.RatingBar;

import br.com.chaos.agenda.FormularioActivity;
import br.com.chaos.agenda.R;
import br.com.chaos.agenda.model.Aluno;

/**
 * Created by gersonapollo on 08/06/17.
 */

public class FormularioHelper {

    private final EditText nome;
    private final EditText endereco;
    private final EditText telefone;
    private final EditText email;
    private final RatingBar nota;
    private Aluno aluno;

    public FormularioHelper(FormularioActivity activity){
        nome = (EditText) activity.findViewById(R.id.formulario_nome);
        endereco = (EditText) activity.findViewById(R.id.formulario_endereco);
        telefone = (EditText) activity.findViewById(R.id.formulario_telefone);
        email = (EditText) activity.findViewById(R.id.formulario_email);
        nota = (RatingBar) activity.findViewById(R.id.formulario_nota);
        aluno = new Aluno();
    }

    public Aluno getAluno(){
        aluno.setNome(this.nome.getText().toString());
        aluno.setEndereco(this.endereco.getText().toString());
        aluno.setTelefone(this.telefone.getText().toString());
        aluno.setEmail(this.email.getText().toString());
        aluno.setNota(new Double(this.nota.getProgress()));

        return aluno;
    }

    public void preencherFormulario(Aluno aluno) {
        nome.setText(aluno.getNome());
        endereco.setText(aluno.getEndereco());
        telefone.setText(aluno.getTelefone());
        email.setText(aluno.getEmail());
        nota.setProgress(aluno.getNota().intValue());
        this.aluno = aluno;
    }
}
