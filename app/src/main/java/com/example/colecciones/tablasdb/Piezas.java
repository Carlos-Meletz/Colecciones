package com.example.colecciones.tablasdb;

import java.io.Serializable;

public class Piezas implements Serializable {
    private Integer idcole;
    private Integer id2;
    private String cod_pie;
    private String nom_pieza;

    public Piezas(){

    }

    public Piezas(Integer idcole, Integer id2, String cod_pie, String nom_pieza) {
        this.idcole = idcole;
        this.id2 = id2;
        this.cod_pie = cod_pie;
        this.nom_pieza = nom_pieza;
    }

    public Integer getIdcole() {
        return idcole;
    }

    public void setIdcole(Integer idcole) {
        this.idcole = idcole;
    }

    public Integer getId2() {
        return id2;
    }

    public void setId2(Integer id2) {
        this.id2 = id2;
    }

    public String getCod_pie() {
        return cod_pie;
    }

    public void setCod_pie(String cod_pie) {
        this.cod_pie = cod_pie;
    }

    public String getNom_pieza() {
        return nom_pieza;
    }

    public void setNom_pieza(String nom_pieza) {
        this.nom_pieza = nom_pieza;
    }
}
