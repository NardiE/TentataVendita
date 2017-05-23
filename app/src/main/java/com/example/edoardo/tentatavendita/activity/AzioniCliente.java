package com.example.edoardo.tentatavendita.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.edoardo.tentatavendita.R;
import com.example.edoardo.tentatavendita.database.Cliente;
import com.example.edoardo.tentatavendita.database.Evento;
import com.example.edoardo.tentatavendita.database.Fidelity;
import com.example.edoardo.tentatavendita.database.Operatore;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AzioniCliente extends AppCompatActivity {
    private Cliente c;
    private List<Evento> eventilocali;
    Integer backup_punti;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_azioni_cliente);

        long id = getIntent().getLongExtra("id",-1);

        c = Cliente.findById(Cliente.class,id);
        backup_punti = c.getPunti();

        eventilocali = new ArrayList<Evento>();

        // disabilito tasto done su addebito
        EditText et = (EditText)findViewById(R.id.addebito_text);
        et.setOnEditorActionListener(new TextView.OnEditorActionListener()
        {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event)
            {
                if(actionId== EditorInfo.IME_ACTION_DONE)
                {
                    return  true;
                }
                // if you don't have the return statements in the if structure above, you
                // could put return true; here to always keep the keyboard up when the "DONE"
                // action is pressed. But with the return statements above, it doesn't matter
                return false; // or return true
            }
        });


        ((TextView) this.findViewById(R.id.azione_nome_cliente)).setText(c.getNome());
        updateSaldo();


    }
    public void addebito(View v){
        // chiudo la tastiera dell'addebito
        EditText addebito_text = (EditText) findViewById(R.id.addebito_text);
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(addebito_text.getWindowToken(), 0);

        if(getAddebito() < 0) {
            final AlertDialog.Builder alert = new AlertDialog.Builder(this);
            final EditText edittext = new EditText(getBaseContext());
            edittext.setTextColor(Color.BLACK);
            edittext.setText("Operazione di Accredito");
            alert.setTitle("Conferma Accredito");
            alert.setMessage(getString(R.string.accredito_message));
            alert.setIcon(android.R.drawable.ic_dialog_alert);

            alert.setView(edittext);

            alert.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, final int whichButton) {
                    Integer puntiaddebitati = updateSaldo();
                    generaEvento(edittext.getText().toString(), puntiaddebitati);
                    edittext.setText("");
                }
            });

            alert.setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                }
            });



            final AlertDialog alertdialog = alert.create();
            alertdialog.show();


            // forzo il fatto che venga inserita una motivazione di accredito
            Button b =(Button) ((AlertDialog) alertdialog).getButton(AlertDialog.BUTTON_POSITIVE);
            b.setEnabled(true);

            edittext.addTextChangedListener(new TextWatcher() {

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    Button b =(Button) ((AlertDialog) alertdialog).getButton(AlertDialog.BUTTON_POSITIVE);
                    if(s.toString().trim().length()==0){
                        b.setEnabled(false);
                    } else {
                        b.setEnabled(true);
                    }


                }

                @Override
                public void beforeTextChanged(CharSequence s, int start, int count,
                                              int after) {
                    // TODO Auto-generated method stub

                }

                @Override
                public void afterTextChanged(Editable editable) {

                }


            });


        }
        else {
            new AlertDialog.Builder(this)
                    .setTitle("Conferma Addebito")
                    .setMessage("Vuoi Procedere all' Addebito?")
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int whichButton) {
                            Integer puntiaddebitati = updateSaldo();
                            generaEvento(getString(R.string.addebito_message),puntiaddebitati);

                        }
                    })
                    .setNegativeButton(android.R.string.no, null).show();
        }

    }

    public Integer updateSaldo(){
        // TODO controllo negativo
        TextView saldo_tv = (TextView)findViewById(R.id.saldo_text);
        EditText addebito_tv = (EditText) findViewById(R.id.addebito_text);
        Integer puntiaddebitati = new Integer(getAddebito());
        c.setPunti(c.getPunti() - getAddebito());
        saldo_tv.setText("Saldo Punti: " + c.getPunti().toString());
        addebito_tv.setText("");
        return puntiaddebitati;
    }

    public void generaEvento(String message, Integer puntaddebitati){
        eventilocali.add(new Evento(c,message,puntaddebitati.toString(),getTimeString(),getOperatore()));
    }

    public int getAddebito(){
        EditText addebito_tv = (EditText) findViewById(R.id.addebito_text);
        int punti = 0;

        try {
            punti = Integer.parseInt(addebito_tv.getText().toString());
        } catch(NumberFormatException nfe) {
            punti = 0;
        }
        return punti;
    }

    public void returnto(View v) {
        new AlertDialog.Builder(this)
                .setTitle("Conferma Annulla")
                .setMessage("Vuoi uscire e annullare tutti i dati salvati in questa sessione?")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {
                        c.setPunti(backup_punti);
                        c.save();
                        eventilocali.clear();
                        exit();
                    }
                })
                .setNegativeButton(android.R.string.no, null).show();
    }

    public void confirm(View v) {
        //se esco confermando con un valore dentro addebito procedo all'addebito
        if (!(((EditText)findViewById(R.id.addebito_text)).getText().toString().isEmpty())){
            new AlertDialog.Builder(this)
                    .setTitle("Conferma Uscita")
                    .setMessage("Alcuni dati non sono stati salvati, vuoi uscire comunque?")
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int whichButton) {
                            c.save();
                            for(Evento e: eventilocali){
                                e.save();
                            }
                            exit();

                        }
                    })
                    .setNegativeButton(android.R.string.no, null).show();
        }
        else{
            c.save();
            for(Evento e: eventilocali){
                e.save();
            }
            exit();
        }

    }

    public void exit(){
        this.finish();
    }

    public void seeEvent(View v){
        Intent i = new Intent(v.getContext(),seeEvent.class);
        i.putExtra("id",c.getId());
        v.getContext().startActivity(i);
    }

    public String getTimeString(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        return sdf.format(new Date());
    }

    public Operatore getOperatore(){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String id_operatore = prefs.getString("operatore_id","");
        List <Operatore> opi = Operatore.find(Operatore.class, "idoperatore = ?", id_operatore);
        //TODO gestione errore
        return opi.get(0);
    }

    @Override
    public void onBackPressed() {
    }
}
