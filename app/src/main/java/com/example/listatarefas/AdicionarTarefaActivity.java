package com.example.listatarefas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.listatarefas.dao.TarefaDAO;
import com.example.listatarefas.model.Tarefa;
import com.google.android.material.textfield.TextInputEditText;

public class AdicionarTarefaActivity extends AppCompatActivity {

    private TextInputEditText editTarefa;
    private Tarefa tarefaSelecionada;
    private TarefaDAO tarefaDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_tarefa);

        tarefaDAO = new TarefaDAO( getApplicationContext() );

        editTarefa = findViewById(R.id.editNomeTarefa);
        tarefaSelecionada = (Tarefa) getIntent().getSerializableExtra("tarefaSelecionada");

        if(tarefaSelecionada != null)
            editTarefa.setText(tarefaSelecionada.getDescricao());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_adicionar_tarefa, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_save :
                if(editTarefa.getText().toString() != "") {
                    salvarOuAtualizarTarefa();
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void salvarOuAtualizarTarefa() {
        if(tarefaSelecionada != null) {
            atualizarTarefa();
        } else {
            salvarTarefa();
        }
    }

    private void salvarTarefa() {
        Tarefa tarefa = new Tarefa();
        tarefa.setDescricao(editTarefa.getText().toString());

        if(tarefaDAO.salvar(tarefa)) {
            Toast.makeText(getApplicationContext(), "Salvo com sucesso.", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(getApplicationContext(), "Erro ao tentar salvar!", Toast.LENGTH_SHORT).show();
        }
    }

    private void atualizarTarefa() {
        Tarefa tarefa = new Tarefa();
        tarefa.setId(tarefaSelecionada.getId());
        tarefa.setDescricao(editTarefa.getText().toString());

        if(tarefaDAO.atualizar(tarefa)) {
            Toast.makeText(getApplicationContext(), "Atualizado com sucesso.", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(getApplicationContext(), "Erro ao tentar atualizar!", Toast.LENGTH_SHORT).show();
        }
    }
}