package com.example.colecciones;

import androidx.appcompat.app.AppCompatActivity;
import android.content.ContentValues;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Crear extends AppCompatActivity {
    private EditText et1, et2, et3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear);
        et1 = (EditText) findViewById(R.id.etnombre);
        et2 = (EditText) findViewById(R.id.etpiezas);
        et3 = (EditText) findViewById(R.id.etcod);
    }
    public void guardar(View v){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"Colector",null,1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String cod = et3.getText().toString();
        String name = et1.getText().toString();
        String num = et2.getText().toString();
        ContentValues registro = new ContentValues();
        registro.put("Codigo", cod);
        registro.put("Nombre", name);
        registro.put("No.Piezas", num);
        bd.insert("coleccion", null, registro);
        bd.close();
        et1.setText(""); et2.setText(""); et3.setText("");
        Toast.makeText(this, "Datos del usuario cargados", Toast.LENGTH_SHORT).show();
    }
}