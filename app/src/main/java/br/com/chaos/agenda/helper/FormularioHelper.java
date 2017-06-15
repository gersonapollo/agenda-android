package br.com.chaos.agenda.helper;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.EditText;
import android.widget.ImageView;
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
    private final EditText site;
    private final RatingBar nota;
    private final ImageView foto;

    private Aluno aluno;

    public FormularioHelper(FormularioActivity activity){
        nome = (EditText) activity.findViewById(R.id.formulario_nome);
        endereco = (EditText) activity.findViewById(R.id.formulario_endereco);
        telefone = (EditText) activity.findViewById(R.id.formulario_telefone);
        email = (EditText) activity.findViewById(R.id.formulario_email);
        site = (EditText) activity.findViewById(R.id.formulario_site);
        nota = (RatingBar) activity.findViewById(R.id.formulario_nota);
        foto = (ImageView) activity.findViewById(R.id.formulario_foto);
        aluno = new Aluno();
    }

    public Aluno getAluno(){
        aluno.setNome(this.nome.getText().toString());
        aluno.setEndereco(this.endereco.getText().toString());
        aluno.setTelefone(this.telefone.getText().toString());
        aluno.setEmail(this.email.getText().toString());
        aluno.setSite(this.site.getText().toString());
        aluno.setNota(new Double(this.nota.getProgress()));
        aluno.setCaminhFoto(foto.getTag() == null ? "" : foto.getTag().toString());

        return aluno;
    }

    public void preencherFormulario(Aluno aluno) {
        nome.setText(aluno.getNome());
        endereco.setText(aluno.getEndereco());
        telefone.setText(aluno.getTelefone());
        email.setText(aluno.getEmail());
        site.setText(aluno.getSite());
        nota.setProgress(aluno.getNota().intValue());
        carregarImagem(aluno.getCaminhFoto());
        this.aluno = aluno;
    }

    public void carregarImagem(String caminhoFoto) {
        if(caminhoFoto != null && !caminhoFoto.isEmpty()){
            Bitmap fotoBmp = BitmapFactory.decodeFile(caminhoFoto);
            Bitmap bitmapReduzido = Bitmap.createScaledBitmap(fotoBmp, 300, 300, true);
            foto.setImageBitmap(bitmapReduzido);
            foto.setScaleType(ImageView.ScaleType.FIT_XY);
            foto.setTag(caminhoFoto);
        }

    }
}
