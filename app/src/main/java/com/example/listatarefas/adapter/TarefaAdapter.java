package com.example.listatarefas.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.listatarefas.R;
import com.example.listatarefas.model.Tarefa;

import java.util.List;

public class TarefaAdapter extends RecyclerView.Adapter<TarefaAdapter.MyViewHolder> {
    private List<Tarefa> tarefas;

    public TarefaAdapter(List<Tarefa> lista) {
        this.tarefas = lista;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View listItem = LayoutInflater.from( parent.getContext() )
                            .inflate(R.layout.lista_tarefa_adapter, parent, false);
        return new MyViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Tarefa tarefa = tarefas.get(position);
        holder.tarefa.setText( tarefa.getDescricao() );
    }

    @Override
    public int getItemCount() {
        return this.tarefas.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tarefa;
        public MyViewHolder(View itemView) {
            super(itemView);
            tarefa = (TextView) itemView.findViewById(R.id.textTarefa);
        }
    }
}
