package com.example.colecciones.utilidades;

public class Utilidades {
    public static final String TABLA_COlECCION = "coleccion";
    public static final String CAMPO_COD = "Codigo";
    public static final String CAMPO_NOMBRE = "Nombre";
    public static final String CAMPO_NPIEZA = "Piezas";
    public static final String CREAR_TABLA = "CREATE TABLE "+TABLA_COlECCION+"("+CAMPO_COD+" INTEGER,"+CAMPO_NOMBRE+" TEXT,"+CAMPO_NPIEZA+" INTEGER)";

    public static final String TABLA_PIEZAS = "pieza";
    public static final String CAMPO_IDCOLE = "id_cole";
    public static final String CAMPO_ID_PIEZAS = "id_pieza";
    public static final String CAMPO_COD_PIEZAS = "cod_pieza";
    public static final String CAMPO_NOM_PIEZAS = "nom_pieza";
    public static final String CREAR_TABLA_PIEZAS = "CREATE TABLE "+TABLA_PIEZAS+"("+CAMPO_ID_PIEZAS+" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"+CAMPO_COD_PIEZAS+" TEXT,"+CAMPO_NOM_PIEZAS+" TEXT,"+CAMPO_IDCOLE+" INTEGER)";


}
