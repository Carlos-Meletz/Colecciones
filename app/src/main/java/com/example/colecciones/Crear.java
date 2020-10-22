package com.example.colecciones;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.colecciones.utilidades.Utilidades;

public class Crear extends AppCompatActivity {
    private EditText et1,et2,et3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear);
        et1 = (EditText) findViewById(R.id.etcod);
        et2 = (EditText) findViewById(R.id.etnombre);
        et3 = (EditText) findViewById(R.id.etpiezas);
    }
    public void onClick1(View view){
        registrarColeccion();
    }
    private void registrarColeccion() {
        ConexionSQLite cone = new ConexionSQLite(this,"bd_colecciones",null,1);
        SQLiteDatabase db = cone.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Utilidades.CAMPO_COD,et1.getText().toString());
        values.put(Utilidades.CAMPO_NOMBRE,et2.getText().toString());
        values.put(Utilidades.CAMPO_NPIEZA,et3.getText().toString());

        Long idResultante = db.insert(Utilidades.TABLA_COlECCION,Utilidades.CAMPO_COD,values);

        Toast.makeText(getApplicationContext(),"Codigo Registro: "+idResultante,Toast.LENGTH_SHORT).show();
        db.close();
        et1.setText("");
        et2.setText("");
        et3.setText("");
    }
}