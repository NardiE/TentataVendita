package com.example.edoardo.tentatavendita.activity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.edoardo.tentatavendita.R;
import com.example.edoardo.tentatavendita.database.Cliente;
import com.example.edoardo.tentatavendita.database.Evento;
import com.example.edoardo.tentatavendita.database.Fidelity;
import com.example.edoardo.tentatavendita.database.Operatore;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int PERMISSION_REQUEST_CODE = 1;
    private int click_count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //imposto le preferenze segrete
        setSecretPreferences();

        //chiedo i permessi
        getPermission();

        //copio db
        backupDb();

       // TODO CLEANUP PROCEDURE
        /*
        */
        cleanUp();
        insertSample();
    }

    public void startQrRec(View v) {
        List<Cliente> clienti = Cliente.findWithQuery(Cliente.class, "Select * from Cliente");
        Intent i = new Intent(this,AzioniCliente.class);
        i.putExtra("id",clienti.get(0).getId());
        startActivity(i);
    }

    public void sync(View v) {
        final ProgressDialog barProgressDialog;
        final Handler updateBarHandler = new Handler();;
        barProgressDialog = new ProgressDialog(MainActivity.this);

        barProgressDialog.setTitle("Sincronizzazione");
        barProgressDialog.setMessage("Sto Sincronizzando...");
        barProgressDialog.setProgressStyle(barProgressDialog.STYLE_HORIZONTAL);
        barProgressDialog.setProgress(0);
        barProgressDialog.setMax(20);
        barProgressDialog.show();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    // Here you should write your time consuming task...
                    while (barProgressDialog.getProgress() <= barProgressDialog.getMax()) {

                        Thread.sleep(2000);

                        updateBarHandler.post(new Runnable() {

                            public void run() {

                                barProgressDialog.incrementProgressBy(2);

                            }

                        });

                        if (barProgressDialog.getProgress() == barProgressDialog.getMax()) {

                            barProgressDialog.dismiss();

                        }
                    }
                } catch (Exception e) {
                }
            }
        }).start();
    }

    public void search(View view) {
        Intent i = new Intent(this, CercaCliente.class);
        startActivity(i);
    }

    public void cleanUp(){
        Cliente.deleteAll(Cliente.class);
        Operatore.deleteAll(Operatore.class);
        Evento.deleteAll(Evento.class);
        Cliente.executeQuery("delete from sqlite_sequence where name='CLIENTE'");
        Operatore.executeQuery("delete from sqlite_sequence where name='OPERATORE'");
        Evento.executeQuery("delete from sqlite_sequence where name='EVENTO'");
    }

    public void insertSample(){
        ArrayList <Cliente> clienti = new ArrayList<>();
        ArrayList <Fidelity> fidelity = new ArrayList<>();
        ArrayList <Operatore> operatori = new ArrayList<>();
        clienti.add(new Cliente("Antonio Rossi","Via Ugolini 6 Castelfiorentino","50051","FI","3334460058",50,"0000"));
        clienti.add(new Cliente("Anzio Livi","Via Rossettini 12 Ponte a Elsa","50051","FI","3334460058",40,"0000"));
        clienti.add(new Cliente("Bartolo Cesani","Via Bonzi 33 Certaldo","50051","FI","3334460058",30,"0000"));
        clienti.add(new Cliente("Carlo Alessi","Via Cartoni 200 San Miniato","50051","FI","3334460058",20,"0000"));
        clienti.add(new Cliente("Antonio Rossi","Via Ugolini 6 Castelfiorentino","50051","FI","3334460058",50,"0000"));
        clienti.add(new Cliente("Francesco Grossi","Via Pablo 12 Granaiolo","50051","FI","3334460058",40,"0000"));
        clienti.add(new Cliente("Bartolo Cesani","Via Grossi 33 Certaldo","50051","FI","3334460058",30,"0000"));
        clienti.add(new Cliente("Gino Strada","Via La Malfa 200 San Miniato","50051","FI","3334460058",20,"0000"));
        clienti.add(new Cliente("Gilberto Franco","Via Ugolini 6 Castelfiorentino","50051","FI","3334460058",50,"0000"));
        clienti.add(new Cliente("Franco Pecci","Via Rossettini 12 Ponte a Elsa","50051","FI","3334460058",40,"0000"));
        clienti.add(new Cliente("Roberto Rossi","Via Franco Pisana 33 Certaldo","50051","FI","3334460058",30,"0000"));
        clienti.add(new Cliente("Lino Banfi","Via Rossi 200 Montaione","50051","FI","3334460058",20,"0000"));
        clienti.add(new Cliente("Antonio Sperduto","Via Ugolini 6 Montaione","50051","FI","3334460058",50,"0000"));
        clienti.add(new Cliente("Lucio Nesi","Via Rossettini 12 Gambassi","50051","FI","3334460058",40,"0000"));
        clienti.add(new Cliente("Piero Antoni","Via Bonzi 33 Pontedera","50051","FI","3334460058",30,"0000"));
        clienti.add(new Cliente("Alessandro Greco","Via Cartoni 200 San Miniato Basso","50051","FI","3334460058",20,"0000"));
        operatori.add(new Operatore("Operatore Uno",1));
        operatori.add(new Operatore("Operatore Due",2));
        for (Cliente object: clienti) {
            object.save();
        }
        for (Fidelity object: fidelity) {
            object.save();
        }
        for (Operatore object: operatori) {
            object.save();
        }
    }

    public void getPermission(){
        //chiedo permesso scrivere su sd
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {

            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_DENIED) {

                Log.d("permission", "permission denied to SEND_SMS - requesting it");
                String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};

                requestPermissions(permissions, PERMISSION_REQUEST_CODE);

            }
        }
    }

    public void setSecretPreferences(){
        click_count = 0;

        ImageView settings = (ImageView) findViewById(R.id.setting_iv);

        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(click_count == 9){
                    Intent i = new Intent(view.getContext(), Preferenze.class);
                    startActivity(i);
                    click_count = 0;
                }
                else{
                    click_count++;
                }
            }
        });
    }

    public void backupDb(){
        try {
            String state = Environment.getExternalStorageState();
            File sd = Environment.getExternalStorageDirectory();
            File data = Environment.getDataDirectory();
            if (Environment.MEDIA_MOUNTED.equals(state)) {
                Log.d("Test", "sdcard mounted and writable");
            }
            else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
                Log.d("Test", "sdcard mounted readonly");
            }
            else {
                Log.d("Test", "sdcard state: " + state);
            }
            if (sd.canWrite()) {
                String currentDBPath = "/data/data/" + getPackageName() + "/databases/tentatavendita.db";
                String backupDBPath = "tentatavendita.db";
                File currentDB = new File(currentDBPath);
                File backupDB = new File(sd, backupDBPath);

                if (currentDB.exists()) {
                    FileChannel src = new FileInputStream(currentDB).getChannel();
                    FileChannel dst = new FileOutputStream(backupDB).getChannel();
                    dst.transferFrom(src, 0, src.size());
                    src.close();
                    dst.close();
                }
            }
        } catch (Exception e) {
        }
    }

}
