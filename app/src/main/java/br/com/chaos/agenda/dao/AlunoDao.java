package br.com.chaos.agenda.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import br.com.chaos.agenda.model.Aluno;

/**
 * Created by gersonapollo on 13/06/17.
 */

public class AlunoDao extends SQLiteOpenHelper{

    public AlunoDao(Context context) {
        super(context, "Alunos", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE Alunos(id INTEGER PRIMARY KEY, nome TEXT NOT NULL, endereco TEXT, telefone TEXT, email TEXT, site TEXT, nota REAL)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        switch (oldVersion){
            case 1:
                String sql = "ALTER TABLE Alunos ADD COLUMN caminhoFoto TEXT ";
                db.execSQL(sql);
        }

    }

    public void insere(Aluno aluno) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues content = pegaDadosUsuario(aluno);

        db.insert("Alunos", null, content);


    }

    @NonNull
    private ContentValues pegaDadosUsuario(Aluno aluno) {
        ContentValues content = new ContentValues();
        content.put("nome", aluno.getNome());
        content.put("endereco", aluno.getEndereco());
        content.put("telefone", aluno.getTelefone());
        content.put("email", aluno.getEmail());
        content.put("site", aluno.getSite());
        content.put("nota", aluno.getNota());
        content.put("caminhoFoto", aluno.getCaminhFoto());
        return content;
    }

    public List<Aluno> listarAlunos() {
        SQLiteDatabase db = getWritableDatabase();
        String sql = "SELECT * FROM Alunos";

        Cursor cursor = db.rawQuery(sql, null);

        List<Aluno> alunos = new ArrayList<>();
        while (cursor.moveToNext()){
            Aluno aluno = new Aluno();
            aluno.setId(cursor.getLong(cursor.getColumnIndex("id")));
            aluno.setNome(cursor.getString(cursor.getColumnIndex("nome")));
            aluno.setEndereco(cursor.getString(cursor.getColumnIndex("endereco")));
            aluno.setTelefone(cursor.getString(cursor.getColumnIndex("telefone")));
            aluno.setEmail(cursor.getString(cursor.getColumnIndex("email")));
            aluno.setSite(cursor.getString(cursor.getColumnIndex("site")));
            aluno.setNota(cursor.getDouble(cursor.getColumnIndex("nota")));
            aluno.setCaminhFoto(cursor.getString(cursor.getColumnIndex("caminhoFoto")));
            alunos.add(aluno);
        }
        cursor.close();
        return alunos;
    }

    public void remover(Aluno aluno) {
        SQLiteDatabase db = getWritableDatabase();

        String[] argumentos = {String.valueOf(aluno.getId())};
        db.delete("Alunos", "id = ?", argumentos);
    }

    public void atualiza(Aluno aluno) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues content = pegaDadosUsuario(aluno);

        String[] argumentos = {aluno.getId().toString()};
        db.update("Alunos", content, "id = ?", argumentos);

    }
}
