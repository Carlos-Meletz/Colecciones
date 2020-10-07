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
    }

    public void cambiar1(View view){
        Intent avance = new Intent(this, Registro.class);
        startActivity(avance);
    }
    public void cambiar2(View view){
        Intent avance = new Intent(this, Crear.class);
        startActivity(avance);
    }

}