package com.example.colecciones;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.colecciones.utilidades.Utilidades;

import java.lang.reflect.Type;

public class Buscar extends AppCompatActivity {
    EditText cam_id;
    CardView crd1,crd2;
    RadioButton c1,c2;
    TextView cam_nom, cam_pie;
    ConexionSQLite conn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar);

        conn = new ConexionSQLite(getApplicationContext(),"bd_colecciones",null,1);
        c1=(RadioButton) findViewById(R.id.rbt1);
        c2=(RadioButton) findViewById(R.id.rbt2);
        crd1 = (CardView) findViewById(R.id.cardeditar);
        crd2 = (CardView) findViewById(R.id.cardeliminar);
        cam_id = (EditText) findViewById(R.id.campoid);
        cam_nom = (TextView) findViewById(R.id.camponom);
        cam_pie = (TextView) findViewById(R.id.campopie);
    }
    public void onClick(View view){
        if(c1.isChecked() == true){
            switch (view.getId()){
                case R.id.btnbuscar:
                    consultar();
                    break;
                case R.id.cardeditar:
                    editar();
                    break;
                case R.id.cardeliminar:
                    eliminar();
                    break;
            }
        }
        if(c2.isChecked() == true){
            switch (view.getId()){
                case R.id.btnbuscar:
                    consultar2();
                    break;
                case R.id.cardeditar:
                    editar2();
                    break;
                case R.id.cardeliminar:
                    eliminar2();
                    break;
            }
        }

    }
    private void eliminar() {
        SQLiteDatabase db = conn.getWritableDatabase();
        String[] paramatro = {cam_id.getText().toString()};
        db.delete(Utilidades.TABLA_COlECCION,Utilidades.CAMPO_COD+"=?",paramatro);
        Toast.makeText(getApplicationContext(),"Se eliminó la colección",Toast.LENGTH_LONG).show();
        cam_id.setText("");
        limpiar();
        db.close();
    }
    private void editar() {
        SQLiteDatabase db = conn.getWritableDatabase();
        String[] paramatro = {cam_id.getText().toString()};
        ContentValues values = new ContentValues();
        values.put(Utilidades.CAMPO_NOMBRE,cam_nom.getText().toString());
        values.put(Utilidades.CAMPO_NPIEZA,cam_pie.getText().toString());
        db.update(Utilidades.TABLA_COlECCION,values,Utilidades.CAMPO_COD+"=?",paramatro);
        Toast.makeText(getApplicationContext(),"Se actualizó la colección",Toast.LENGTH_LONG).show();
        db.close();
    }
    private void consultar() {
        SQLiteDatabase db = conn.getReadableDatabase();
        String[] paramatro = {cam_id.getText().toString()};
        String[] campo= {Utilidades.CAMPO_NOMBRE,Utilidades.CAMPO_NPIEZA};
        try {
            Cursor cursor = db.query(Utilidades.TABLA_COlECCION,campo,Utilidades.CAMPO_COD+"=?",paramatro,null,null,null);
            cursor.moveToFirst();
            cam_nom.setText(cursor.getString(0));
            cam_pie.setText(cursor.getString(1));
            cursor.close();
            cam_nom.setVisibility(View.VISIBLE);
            cam_pie.setVisibility(View.VISIBLE);
            crd1.setVisibility(View.VISIBLE);
            crd2.setVisibility(View.VISIBLE);
        }catch (Exception e){
            Toast.makeText(getApplicationContext(),"La colección no existe",Toast.LENGTH_LONG).show();
            limpiar();
        }

    }
    private void eliminar2() {
        SQLiteDatabase db = conn.getWritableDatabase();
        String[] paramatro = {cam_id.getText().toString()};
        db.delete(Utilidades.TABLA_COlECCION,Utilidades.CAMPO_NOMBRE+"=?",paramatro);
        Toast.makeText(getApplicationContext(),"Se eliminó la colección",Toast.LENGTH_LONG).show();
        cam_id.setText("");
        limpiar();
        db.close();
    }
    private void editar2() {
        SQLiteDatabase db = conn.getWritableDatabase();
        String[] paramatro = {cam_id.getText().toString()};
        ContentValues values = new ContentValues();
        values.put(Utilidades.CAMPO_COD,cam_nom.getText().toString());
        values.put(Utilidades.CAMPO_NPIEZA,cam_pie.getText().toString());
        db.update(Utilidades.TABLA_COlECCION,values,Utilidades.CAMPO_NOMBRE+"=?",paramatro);
        Toast.makeText(getApplicationContext(),"Se actualizó la colección",Toast.LENGTH_LONG).show();
        db.close();
    }
    private void consultar2() {
        SQLiteDatabase db = conn.getReadableDatabase();
        String[] paramatro = {cam_id.getText().toString()};
        String[] campo= {Utilidades.CAMPO_COD,Utilidades.CAMPO_NPIEZA};
        try {
            Cursor cursor = db.query(Utilidades.TABLA_COlECCION,campo,Utilidades.CAMPO_NOMBRE+"=?",paramatro,null,null,null);
            cursor.moveToFirst();
            cam_nom.setText(cursor.getString(0));
            cam_pie.setText(cursor.getString(1));
            cursor.close();
            cam_nom.setVisibility(View.VISIBLE);
            cam_pie.setVisibility(View.VISIBLE);
            crd1.setVisibility(View.VISIBLE);
            crd2.setVisibility(View.VISIBLE);
        }catch (Exception e){
            Toast.makeText(getApplicationContext(),"La colección no existe",Toast.LENGTH_LONG).show();
            limpiar();
        }
    }
    private void limpiar() {
        cam_nom.setText("");
        cam_pie.setText("");
        cam_nom.setVisibility(View.GONE);
        cam_pie.setVisibility(View.GONE);
        crd1.setVisibility(View.GONE);
        crd2.setVisibility(View.GONE);
    }
}