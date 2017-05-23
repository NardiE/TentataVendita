package com.example.edoardo.tentatavendita;

/**
 * Created by Edoardo on 18/05/2017.
 */

public class ClienteLista {
    private String name;
    private String indirizzo;
    private long id;

    public ClienteLista(String name, String indirizzo){
        this.name = name;
        this.indirizzo = indirizzo;
    }

    public ClienteLista(String name, String indirizzo, long id) {
        this.name = name;
        this.id = id;
        this.indirizzo = indirizzo;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIndirizzo() { return indirizzo; }

    public void setIndirizzo(String indirizzo) { this.indirizzo = indirizzo; }


}
