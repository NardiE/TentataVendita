package com.example.edoardo.tentatavendita;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.edoardo.tentatavendita.activity.AzioniCliente;

import java.util.ArrayList;

/**
 * Created by Edoardo on 18/05/2017.
 */


    public class CustomAdapter extends ArrayAdapter<ClienteLista> implements View.OnClickListener{

        private ArrayList<ClienteLista> dataSet;
        Context mContext;



        public CustomAdapter(ArrayList<ClienteLista> data, Context context) {
            super(context, R.layout.client_list_elem, data);
            this.dataSet = data;
            this.mContext=context;

        }

        @Override
        public void onClick(View v) {

            int position=(Integer) v.getTag();
            Object object= getItem(position);
            ClienteLista client=(ClienteLista)object;

            Intent i = new Intent(v.getContext(),AzioniCliente.class);
            v.getContext().startActivity(i);

            /*switch (v.getId())
            {
                case R.id.item_info:
                    Snackbar.make(v, "Release date " +dataModel.getFeature(), Snackbar.LENGTH_LONG)
                            .setAction("No action", null).show();
                    break;
            }*/
        }

        private int lastPosition = -1;

        @Override
        public View getView(int position, View v, ViewGroup parent) {
            // Get the data item for this position
            ClienteLista client = getItem(position);
            if (v==null)
            {
                v=LayoutInflater.from(mContext).inflate(R.layout.client_list_elem, null);
            }
            ClienteLista cl=(ClienteLista) getItem(position);
            TextView name=(TextView) v.findViewById(R.id.name_text);
            TextView address=(TextView) v.findViewById(R.id.indirizzo_text);
            name.setText(cl.getName());
            address.setText(cl.getIndirizzo());
            return v;
        }
    }

