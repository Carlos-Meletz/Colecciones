package com.example.colecciones;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.colecciones.tablasdb.Colecctores;
import com.example.colecciones.tablasdb.Piezas;
import com.example.colecciones.utilidades.Utilidades;

import java.util.ArrayList;

public class Mostrapieza extends AppCompatActivity {
    ListView lvtabla2;
    ArrayList<String> listaInformacion;
    ArrayList<Piezas> pieza;
    ConexionSQLite conn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrapieza);
        lvtabla2 = (ListView) findViewById(R.id.listViewTabla2);
        conn = new ConexionSQLite(getApplicationContext(),"bd_colecciones",null,1);
        consultarLista();
        ArrayAdapter adaptor = new ArrayAdapter(this, android.R.layout.simple_list_item_1,listaInformacion);
        lvtabla2.setAdapter(adaptor);
    }
    private void consultarLista() {
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
        obtenerLista();
    }

    private void obtenerLista() {
        listaInformacion=new ArrayList<String>();
        for (int i=0; i<pieza.size();i++){
            listaInformacion.add("ID: "+ pieza.get(i).getId2()+"  |  Cod: "+pieza.get(i).getCod_pie()+"  |  Nom: "+pieza.get(i).getNom_pieza()+"  |  Cole_ID: "
                    +pieza.get(i).getIdcole());
        }

    }
}