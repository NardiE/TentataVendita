package com.example.edoardo.tentatavendita.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.edoardo.tentatavendita.ClienteLista;
import com.example.edoardo.tentatavendita.CustomAdapter;
import com.example.edoardo.tentatavendita.R;
import com.example.edoardo.tentatavendita.database.Cliente;

import java.util.ArrayList;
import java.util.List;

public class CercaCliente extends AppCompatActivity {
    ArrayList<ClienteLista> dataModels;
    Context context;
    ListView listView;
    private static CustomAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        dataModels = new ArrayList<>();
        this.context=this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cerca_cliente);
        listView = (ListView) findViewById(R.id.client_list);

        List<Cliente> clienti = Cliente.findWithQuery(Cliente.class, "Select * from Cliente");
        updateList(clienti);

        // listener per la ricerca veloce
        setEditTextListener();

        adapter = new CustomAdapter(dataModels, getApplicationContext());

        listView.setAdapter(adapter);

        //procedura avvio azioni clienti
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(view.getContext(),AzioniCliente.class);
                i.putExtra("id",dataModels.get(position).getId());
                context.startActivity(i);
            }
        });

        adapter.notifyDataSetChanged();

    }

    public void searchClient(View view) {
        ((EditText)findViewById(R.id.search_text)).setText("");
    }

    public void updateList(List <Cliente> listaclienti){
        dataModels.clear();
        for(Cliente c: listaclienti){
            dataModels.add(new ClienteLista(c.getNome(),c.getIndirizzo(),c.getId()));
        }
    }

    public void setEditTextListener(){
        EditText edittext = (EditText) findViewById(R.id.search_text);
        edittext.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String param = ((EditText)findViewById(R.id.search_text)).getText().toString();
                List <Cliente> returnlist = Cliente.getClientsSearching(param);
                updateList(returnlist);
                adapter.notifyDataSetChanged();
            }


        });
    }
}
