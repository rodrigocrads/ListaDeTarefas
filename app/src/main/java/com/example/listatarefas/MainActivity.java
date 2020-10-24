package com.example.listatarefas;

import android.content.Intent;
import android.os.Bundle;

import com.example.listatarefas.adapter.TarefaAdapter;
import com.example.listatarefas.dao.TarefaDAO;
import com.example.listatarefas.helper.RecyclerItemClickListener;
import com.example.listatarefas.model.Tarefa;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;

import android.widget.AdapterView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Tarefa> tarefas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.recyclerViewTarefas);

        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(
                        getApplicationContext(),
                        recyclerView,
                        new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                Log.i("clique", "onItemClick");
                            }

                            @Override
                            public void onLongItemClick(View view, int position) {
                                Log.i("clique", "onLongClick");
                            }

                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {}
                        }
                )
        );

        FloatingActionButton fab = findViewById(R.id.fabAdd);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AdicionarTarefaActivity.class);

                startActivity( intent );
            }
        });
    }

    @Override
    protected void onStart() {
        carregarListaTarefas();
        super.onStart();
    }

    public void carregarListaTarefas()
    {
        TarefaDAO tarefaDAO = new TarefaDAO(getApplicationContext());
        tarefas = tarefaDAO.list();

        // configurar adapter
        TarefaAdapter adapter = new TarefaAdapter(tarefas);

        // configurar Recyclerview
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager( getApplicationContext() );
        recyclerView.setLayoutManager( layoutManager );
        recyclerView.setHasFixedSize( true );
        recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayout.VERTICAL));
        recyclerView.setAdapter(adapter);
    }
}