package com.example.colecciones.tablasdb;

public class Colecctores {
    private Integer codigo;
    private String nombrecoleccion;
    private Integer numpiezas;

    public Colecctores(Integer codigo, String nombrecoleccion, Integer numpiezas) {
        this.codigo = codigo;
        this.nombrecoleccion = nombrecoleccion;
        this.numpiezas = numpiezas;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNombrecoleccion() {
        return nombrecoleccion;
    }

    public void setNombrecoleccion(String nombrecoleccion) {
        this.nombrecoleccion = nombrecoleccion;
    }

    public Integer getNumpiezas() {
        return numpiezas;
    }

    public void setNumpiezas(Integer numpiezas) {
        this.numpiezas = numpiezas;
    }
}
