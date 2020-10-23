package com.example.colecciones;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.colecciones.tablasdb.Colecctores;
import com.example.colecciones.tablasdb.Piezas;
import com.example.colecciones.utilidades.Utilidades;

import java.util.ArrayList;

public class Mostrapieza extends AppCompatActivity {
    TextView campoId,campoNombre,campoPieza;
    ListView lvtabla2;
    ArrayList<String> listaInformacion;
    ArrayList<Piezas> pieza;
    ConexionSQLite conn;
    int var;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrapieza);
        conn = new ConexionSQLite(getApplicationContext(),"bd_colecciones",null,1);

        campoId = (TextView) findViewById(R.id.box1);
        campoNombre = (TextView) findViewById(R.id.box2);
        campoPieza = (TextView) findViewById(R.id.box3);

        lvtabla2 = (ListView) findViewById(R.id.listViewTabla2);
        Bundle objetoEnviado=getIntent().getExtras();
        Colecctores piezas=null;

        if(objetoEnviado!=null){
            piezas= (Colecctores) objetoEnviado.getSerializable("coleccion");
            var = piezas.getId();
            campoId.setText("Codigo:  "+piezas.getId().toString());
            campoNombre.setText("Nombre de Coleccion:  "+piezas.getNombre());
            campoPieza.setText("Maximo de Piezas:  "+piezas.getNum());
        }
        consultarLista();
        ArrayAdapter adaptor = new ArrayAdapter(this, android.R.layout.simple_list_item_multiple_choice,listaInformacion);
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
        int aux;
        listaInformacion=new ArrayList<String>();
        for (int i=0; i<pieza.size();i++){
            aux = pieza.get(i).getIdcole();
            if(aux == var) {
                listaInformacion.add("ID: " + pieza.get(i).getId2() + "  |  Cod: " + pieza.get(i).getCod_pie() + "  |  Nom: " + pieza.get(i).getNom_pieza() + "  |  Cole_ID: "
                        + pieza.get(i).getIdcole());
            }
        }

    }
}