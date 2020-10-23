package com.example.colecciones;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ConexionSQLite cone = new ConexionSQLite(this,"bd_colecciones",null,1);
    }

    public void cambiar1(View view){
        Intent avance = new Intent(this, Registro.class);
        startActivity(avance);
    }
    public void cambiar2(View view){
        Intent avance = new Intent(this, Crear.class);
        startActivity(avance);
    }
    public void cambiar3(View view){
        Intent avance = new Intent(this, Buscar.class);
        startActivity(avance);
    }
    public void cambiar4(View view){
        Intent avance = new Intent(this, Agregar.class);
        startActivity(avance);
    }
    public void cambiar5(View view){
        Intent avance = new Intent(this, Compartir.class);
        startActivity(avance);
    }
}