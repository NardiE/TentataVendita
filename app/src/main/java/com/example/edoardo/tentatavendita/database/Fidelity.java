package com.example.edoardo.tentatavendita.database;

import com.orm.SugarRecord;

/**
 * Created by Edoardo on 22/05/2017.
 */

public class Fidelity extends SugarRecord<Fidelity> {
    private Cliente cliente;
    private Integer punti;
    private String barcode;

    public Fidelity(Integer punti, Cliente cliente, String barcode) {
        this.punti = punti;
        this.cliente = cliente;
        this.barcode = barcode;
    }

    public Fidelity(){

    }


    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Integer getPunti() {
        return punti;
    }

    public void setPunti(Integer punti) {
        this.punti = punti;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }


}
