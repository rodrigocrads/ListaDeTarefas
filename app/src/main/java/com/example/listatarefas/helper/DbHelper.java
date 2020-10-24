package com.example.listatarefas.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    public static int VERSION = 1;
    public static String DB_NAME = "db_tarefas";
    public static String TABLE_TAREFAS = "tarefas";

    public DbHelper(@Nullable Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = String.format(
                "CREATE TABLE IF NOT EXISTS %s (id INTEGER PRIMARY KEY AUTOINCREMENT, descricao TEXT NOT NULL); ",
                TABLE_TAREFAS
        );

        try {
            db.execSQL( sql );

            Log.i("Info DB", "Sucesso ao criar a tabela: " + TABLE_TAREFAS);
        } catch (Exception e) {
            Log.i("Info DB", "Erro ao criar a tabela: " + TABLE_TAREFAS);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Método chamado quando a versão do app é atualizado
    }
}
