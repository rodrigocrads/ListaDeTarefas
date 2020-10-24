package com.example.listatarefas.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.listatarefas.helper.DbHelper;
import com.example.listatarefas.model.Tarefa;

import java.util.ArrayList;
import java.util.List;

public class TarefaDAO implements ITarefaDAO {

    private SQLiteDatabase writer;
    private SQLiteDatabase reader;

    public TarefaDAO(Context context) {
        DbHelper db = new DbHelper(context);
        writer = db.getWritableDatabase();
        reader = db.getReadableDatabase();
    }

    @Override
    public boolean salvar(Tarefa tarefa) {
        ContentValues values = new ContentValues();
        values.put("descricao", tarefa.getDescricao());
        try {
            writer.insert(DbHelper.TABLE_TAREFAS, null, values);
            Log.i("TarefaDAO", "Tarefa salva com sucesso.");
        } catch (Exception e) {
            Log.i("TarefaDAO", "Erro ao tentar salvar tarefa: " + e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean atualizar(Tarefa tarefa) {
        ContentValues values = new ContentValues();
        values.put("descricao", tarefa.getDescricao());

        String[] args = { String.valueOf(tarefa.getId()) };
        try {
            writer.update(DbHelper.TABLE_TAREFAS, values, "id=?", args);
            Log.i("TarefaDAO", "Tarefa atualizada com sucesso.");
        } catch (Exception e) {
            Log.i("TarefaDAO", "Erro ao tentar atualizar tarefa: " + e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean deletar(Tarefa tarefa) {
        return false;
    }

    @Override
    public List<Tarefa> list() {
        List<Tarefa> tarefas = new ArrayList<>();

        String sql = "SELECT * FROM " + DbHelper.TABLE_TAREFAS + " ;";
        Cursor cursor = reader.rawQuery(sql, null);

        while( cursor.moveToNext() ) {
            Long id = cursor.getLong( cursor.getColumnIndex("id"));
            String descricao = cursor.getString( cursor.getColumnIndex("descricao"));

            Tarefa tarefa = new Tarefa();
            tarefa.setDescricao(descricao);
            tarefa.setId((id));

            tarefas.add(tarefa);
        }

        return tarefas;
    }
}
