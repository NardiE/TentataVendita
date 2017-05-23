package com.example.edoardo.tentatavendita.database;

import com.orm.SugarRecord;

import java.util.List;

/**
 * Created by Edoardo on 22/05/2017.
 */

public class Cliente extends SugarRecord<Cliente> {

    private String nome;
    private String indirizzo;
    private String cap;
    private String provincia;
    private String telefono;
    private Integer punti;
    private String barcode;

    public Cliente(String nome, String indirizzo, String cap, String provincia, String telefono, Integer punti, String barcode) {
        this.nome = nome;
        this.indirizzo = indirizzo;
        this.cap = cap;
        this.provincia = provincia;
        this.telefono = telefono;
        this.punti = punti;
        this.barcode = barcode;
    }

    public Cliente(){

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public String getCap() {
        return cap;
    }

    public void setCap(String cap) {
        this.cap = cap;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
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

    public static List<Cliente> getClientsSearching(String param){
        String SQL1 = "SELECT * FROM Cliente WHERE indirizzo LIKE '%" + param + "%' OR nome LIKE '%" + param + "%'";
        List<Cliente> clientsfind;
        clientsfind = Cliente.findWithQuery(Cliente.class,SQL1);
        return clientsfind;
    }
}
