package com.example.edoardo.tentatavendita;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class CercaCliente extends AppCompatActivity {
    ArrayList<Cliente> dataModels;
    Context context;
    ListView listView;
    private static CustomAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.context=this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cerca_cliente);
        listView = (ListView) findViewById(R.id.client_list);

        dataModels = new ArrayList<>();

        dataModels.add(new Cliente("Antonio Rossi"));
        dataModels.add(new Cliente("Anzio Livi"));
        dataModels.add(new Cliente("Bartolo Cesani"));
        dataModels.add(new Cliente("Carlo Alessi"));
        dataModels.add(new Cliente("Nome Cognome"));
        dataModels.add(new Cliente("Nome Cognome"));
        dataModels.add(new Cliente("Nome Cognome"));
        dataModels.add(new Cliente("Nome Cognome"));
        dataModels.add(new Cliente("Nome Cognome"));
        dataModels.add(new Cliente("Nome Cognome"));
        dataModels.add(new Cliente("Nome Cognome"));
        dataModels.add(new Cliente("Nome Cognome"));
        dataModels.add(new Cliente("Nome Cognome"));
        dataModels.add(new Cliente("Nome Cognome"));
        dataModels.add(new Cliente("Nome Cognome"));
        dataModels.add(new Cliente("Nome Cognome"));
        dataModels.add(new Cliente("Nome Cognome"));
        dataModels.add(new Cliente("Nome Cognome"));
        dataModels.add(new Cliente("Nome Cognome"));
        dataModels.add(new Cliente("Nome Cognome"));
        dataModels.add(new Cliente("Nome Cognome"));
        dataModels.add(new Cliente("Nome Cognome"));
        dataModels.add(new Cliente("Nome Cognome"));
        dataModels.add(new Cliente("Nome Cognome"));
        dataModels.add(new Cliente("Nome Cognome"));
        dataModels.add(new Cliente("Nome Cognome"));
        dataModels.add(new Cliente("Nome Cognome"));
        dataModels.add(new Cliente("Nome Cognome"));
        dataModels.add(new Cliente("Nome Cognome"));
        dataModels.add(new Cliente("Nome Cognome"));
        dataModels.add(new Cliente("Nome Cognome"));
        dataModels.add(new Cliente("Nome Cognome"));
        dataModels.add(new Cliente("Nome Cognome"));
        dataModels.add(new Cliente("Nome Cognome"));
        dataModels.add(new Cliente("Nome Cognome"));
        dataModels.add(new Cliente("Nome Cognome"));

        adapter = new CustomAdapter(dataModels, getApplicationContext());

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(view.getContext(),AzioniCliente.class);
                context.startActivity(i);
            }
        });
    }
}
