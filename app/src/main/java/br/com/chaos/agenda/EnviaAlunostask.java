package br.com.chaos.agenda;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.util.List;

import br.com.chaos.agenda.converter.AlunoConverter;
import br.com.chaos.agenda.dao.AlunoDao;
import br.com.chaos.agenda.model.Aluno;
import br.com.chaos.agenda.webservice.WebClient;

/**
 * Created by gersonapollo on 15/06/17.
 */

public class EnviaAlunostask extends AsyncTask<Void, Void, String> {

    private Context context;
    private ProgressDialog dialog;

    public EnviaAlunostask(Context context) {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        dialog = ProgressDialog.show(context, "Aguarde", "Enviando Alunos...", true, true) ;
    }

    @Override
    protected String doInBackground(Void... params) {

        AlunoDao dao = new AlunoDao(context);
        List<Aluno> alunos = dao.listarAlunos();
        dao.close();

        AlunoConverter converter = new AlunoConverter();
        String json = converter.converteParaJson(alunos);

        WebClient ws = new WebClient();
//        String retorno = ws.post(json);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String retorno = "Alunos Enviados";
        return retorno;
    }

    @Override
    protected void onPostExecute(String retorno) {
        dialog.dismiss();
        Toast.makeText(context, retorno, Toast.LENGTH_LONG).show();
    }
}
