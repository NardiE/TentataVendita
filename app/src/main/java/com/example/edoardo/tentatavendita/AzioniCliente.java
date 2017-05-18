package com.example.edoardo.tentatavendita;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AzioniCliente extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_azioni_cliente);
    }
    public void addebito(View v){
        new AlertDialog.Builder(this)
                .setTitle("Vuoi Procedere?")
                .setMessage("Vuoi Procedere all' Addebito?")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {
                        TextView saldo = (TextView)findViewById(R.id.saldo_text);
                        EditText addebito = (EditText) findViewById(R.id.addebito_text);
                        saldo.setText("Saldo Punti: 13");
                        addebito.setText("");
                    }})
                .setNegativeButton(android.R.string.no, null).show();

    }
    public void returnto(View v) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}
