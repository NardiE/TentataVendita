package com.example.edoardo.tentatavendita.database;

import com.orm.SugarRecord;

/**
 * Created by Edoardo on 22/05/2017.
 */

public class Evento extends SugarRecord<Evento> {
    private Cliente cliente;
    private String descrizione;
    private String puntievento;
    private String data;
    private Operatore operatore;

    public Evento( Cliente cliente, String descrizione, String puntievento, String data, Operatore operatore){
        this.setCliente(cliente);
        this.setDescrizione(descrizione);
        this.setPuntiEvento(puntievento);
        this.setData(data);
        this.setOperatore(operatore);
    }


    public Evento(){

    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getPuntiEvento() {
        return puntievento;
    }

    public void setPuntiEvento(String puntievento) {
        this.puntievento = puntievento;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Operatore getOperatore() {
        return operatore;
    }

    public void setOperatore(Operatore operatore) {
        this.operatore = operatore;
    }
}

