package com.example.edoardo.tentatavendita.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.edoardo.tentatavendita.R;
import com.example.edoardo.tentatavendita.database.Evento;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class seeEvent extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_event);
        long id = getIntent().getLongExtra("id",-1);
        List<Evento> listaeventi= Evento.find(Evento.class, "cliente = ?", String.valueOf(id));
        TextView tv = (TextView) findViewById(R.id.evento_tv);
        for (Evento e: listaeventi){
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            Date d = new Date();
            try {
                d = sdf.parse(e.getData().toString());
            } catch (ParseException e1) {
                e1.printStackTrace();
            }
            sdf = new SimpleDateFormat("HH:mm");
            String s = tv.getText().toString() + System.getProperty("line.separator") + sdf.format(d) + " " + e.getOperatore().getNome() + " " + e.getDescrizione() + " " + e.getPuntiEvento();
            tv.setText(s);
        }
    }
}
