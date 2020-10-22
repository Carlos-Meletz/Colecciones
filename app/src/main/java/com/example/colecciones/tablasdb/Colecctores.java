package com.example.colecciones.tablasdb;

import java.io.Serializable;

public class Colecctores implements Serializable {
    private Integer id;
    private String nombre;
    private String num;

    public Colecctores(Integer id, String nombre, String num) {
        this.id = id;
        this.nombre = nombre;
        this.num = num;
    }

    public Colecctores(){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }
}
