package com.example.colecciones;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.colecciones.tablasdb.Colecctores;
import com.example.colecciones.tablasdb.Piezas;
import com.example.colecciones.utilidades.Utilidades;

import java.util.ArrayList;

public class Compartir extends AppCompatActivity {
    EditText et1,et2,et3,et4;
    Spinner spn1;
    ArrayList<String> listaInformacion;
    ArrayList<String> listaInformacion2;
    ArrayList<Piezas> pieza;
    ArrayList<Colecctores> coleccion;
    ConexionSQLite conn;
    String aux="";
    int var;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compartir);
        et1 = (EditText) findViewById(R.id.etmicorreo);
        et2 = (EditText) findViewById(R.id.etpara);
        et3 = (EditText) findViewById(R.id.etasunto);
        et4 = (EditText) findViewById(R.id.etdescripcion);
        spn1 = (Spinner) findViewById(R.id.spinercolecciones);
        conn = new ConexionSQLite(getApplicationContext(),"bd_colecciones",null,1);
        consultarLista();
        consultarPiezas();
        ArrayAdapter<CharSequence> adaptor = new ArrayAdapter(this, R.layout.estilo_spinner,listaInformacion);
        spn1.setAdapter(adaptor);

        spn1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long idl) {

            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

    public void sendEmail(View view){
            String TO = et1.getText().toString();
            String CC = et2.getText().toString();
            Intent emailIntent = new Intent(Intent.ACTION_SEND);
            emailIntent.setData(Uri.parse("mailto:"));
            emailIntent.setType("text/plain");
            emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
            emailIntent.putExtra(Intent.EXTRA_CC, CC);
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, et3.getText().toString());
            String spin = et4.getText().toString()+"\n\nColeccion: "+spn1.getSelectedItem().toString()+"\n";
            emailIntent.putExtra(Intent.EXTRA_TEXT, spin +"\n"+aux);

            try {
                startActivity(Intent.createChooser(emailIntent, "Enviar email..."));
                finish();
            } catch (android.content.ActivityNotFoundException ex) {
                Toast.makeText(Compartir.this,
                        "No tienes clientes de email instalados.", Toast.LENGTH_SHORT).show();
            }
    }
    private void consultarLista() {
        SQLiteDatabase db=conn.getReadableDatabase();

        Colecctores colecctores = null;
        coleccion = new ArrayList<Colecctores>();
        //select * from usuarios
        Cursor cursor = db.rawQuery("SELECT * FROM "+ Utilidades.TABLA_COlECCION,null);

        while (cursor.moveToNext()){
            colecctores = new Colecctores();
            colecctores.setId(cursor.getInt(0));
            colecctores.setNombre(cursor.getString(1));
            colecctores.setNum(cursor.getString(2));

            Log.i("id",colecctores.getId().toString());
            Log.i("nombre",colecctores.getNombre());
            Log.i("piezas",colecctores.getNum());

            coleccion.add(colecctores);

        }
        obtenerLista();
    }

    private void obtenerLista() {
        listaInformacion=new ArrayList<String>();
        listaInformacion.add("Seleccione");
        for (int i=0; i<coleccion.size();i++){
            listaInformacion.add("Codigo: "+ coleccion.get(i).getId()+" -> "+coleccion.get(i).getNombre()+" -> "
                    +coleccion.get(i).getNum()+" piezas.");
        }

    }

    private void consultarPiezas() {
        SQLiteDatabase db=conn.getReadableDatabase();

        Piezas colecctores = null;
        pieza = new ArrayList<Piezas>();
        //select * from usuarios
        Cursor cursor = db.rawQuery("SELECT * FROM "+ Utilidades.TABLA_PIEZAS,null);

        while (cursor.moveToNext()){
            colecctores = new Piezas();
            colecctores.setId2(cursor.getInt(0));
            colecctores.setCod_pie(cursor.getString(1));
            colecctores.setNom_pieza(cursor.getString(2));
            colecctores.setIdcole(cursor.getInt(3));

            pieza.add(colecctores);
        }
        obtenerLista2();
    }

    private void obtenerLista2() {

        long dato =  (spn1.getSelectedItemId());
        var = coleccion.get((int)dato).getId();
        int aux2;
        for (int i = 0; i < pieza.size(); i++) {
            aux2 = pieza.get(i).getIdcole();
            if(aux2 == var){
            aux = aux + "ID: " + pieza.get(i).getId2() + "  |  Cod: " + pieza.get(i).getCod_pie() + "  |  Nom: " + pieza.get(i).getNom_pieza() + "  |  Cole_ID: "
                    + pieza.get(i).getIdcole()+"\n";
            }
        }
    }
}