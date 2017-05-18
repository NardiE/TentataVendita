package com.example.edoardo.tentatavendita;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startQrRec(View v) {
        Intent i = new Intent(this,AzioniCliente.class);
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
}
