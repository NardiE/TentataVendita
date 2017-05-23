package com.example.edoardo.tentatavendita.database;

import com.orm.SugarRecord;

/**
 * Created by Edoardo on 22/05/2017.
 */

public class Operatore extends SugarRecord<Operatore> {
    private String nome;
    private Integer idoperatore;

    public Operatore(String nome, Integer idoperatore) {
        this.nome = nome;
        this.idoperatore = idoperatore;
    }

    public Operatore() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getIdOperatore() {
        return idoperatore;
    }

    public void setIdOperatore(Integer id_operatore) {
        this.idoperatore = id_operatore;
    }
}
