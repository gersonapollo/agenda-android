package br.com.chaos.agenda;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.text.Normalizer;
import java.util.zip.Inflater;

import javax.xml.datatype.Duration;

import br.com.chaos.agenda.dao.AlunoDao;
import br.com.chaos.agenda.helper.FormularioHelper;
import br.com.chaos.agenda.model.Aluno;

public class FormularioActivity extends AppCompatActivity {

    public static final int CAMERA_CODE = 555;
    private FormularioHelper helper;
    private String caminhoFoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        helper = new FormularioHelper(this);

        Intent intent = getIntent();
        Aluno aluno = (Aluno) intent.getSerializableExtra("aluno");
        if(aluno != null){
            helper.preencherFormulario(aluno);
        }

        View botaoFoto = findViewById(R.id.formulario_botao_foto);
        botaoFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentFoto = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                caminhoFoto = getExternalFilesDir(null) + "/" + System.currentTimeMillis() +".jpg";
                File arquivoFoto = new File(caminhoFoto);
                FormularioActivity context = FormularioActivity.this;
                intentFoto.putExtra(MediaStore.EXTRA_OUTPUT, FileProvider.getUriForFile(context, context.getApplicationContext().getPackageName()
                        +".provider",arquivoFoto));
                startActivityForResult(intentFoto, CAMERA_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == Activity.RESULT_OK && requestCode == CAMERA_CODE){
           helper.carregarImagem(caminhoFoto);

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_formulario, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_formulario_ok:
                Aluno aluno = helper.getAluno();

                AlunoDao dao = new AlunoDao(this);
                if(aluno.getId() != null){
                    dao.atualiza(aluno);
                }else{
                    dao.insere(aluno);
                }
                dao.close();

                Toast.makeText(FormularioActivity.this, "Aluno "+aluno.getNome() +" Adicionado", Toast.LENGTH_SHORT).show();

                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
