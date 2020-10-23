package com.example.colecciones;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.example.colecciones.tablasdb.Colecctores;
import com.example.colecciones.utilidades.MyAdapter;
import com.example.colecciones.utilidades.Utilidades;

import java.util.ArrayList;

public class Registro extends AppCompatActivity {
    ListView lvtabla;
    ArrayList<String> listaInformacion1;
    ArrayList<String> listaInformacion2;
    ArrayList<String> listaInformacion3;

    ArrayList<Colecctores> coleccion;
    ConexionSQLite conn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        lvtabla = (ListView) findViewById(R.id.listViewTabla);
        conn = new ConexionSQLite(getApplicationContext(),"bd_colecciones",null,1);
        consultarLista();
        //ArrayAdapter adaptor = new ArrayAdapter(this, R.layout.estilo,listaInformacion);
        MyAdapter adaptor = new MyAdapter(this,listaInformacion1,listaInformacion2,listaInformacion3);
        lvtabla.setAdapter(adaptor);

        lvtabla.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                Colecctores colec=coleccion.get(pos);
                Intent intent=new Intent(Registro.this,Mostrapieza.class);
                Bundle bundle=new Bundle();
                bundle.putSerializable("coleccion",colec);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
    private void consultarLista() {
        SQLiteDatabase db=conn.getReadableDatabase();

        Colecctores colecctores = null;
        coleccion = new ArrayList<Colecctores>();
        //select * from usuarios
        Cursor cursor = db.rawQuery("SELECT * FROM "+Utilidades.TABLA_COlECCION,null);

        while (cursor.moveToNext()){
            colecctores = new Colecctores();
            colecctores.setId(cursor.getInt(0));
            colecctores.setNombre(cursor.getString(1));
            colecctores.setNum(cursor.getString(2));

            coleccion.add(colecctores);
        }
        obtenerLista();
    }

    private void obtenerLista() {
        listaInformacion1 =new ArrayList<String>();
        listaInformacion2 =new ArrayList<String>();
        listaInformacion3 =new ArrayList<String>();

        for (int i=0; i<coleccion.size();i++){
            listaInformacion1.add("Nombre: "+coleccion.get(i).getNombre());
            listaInformacion2.add("Codigo: "+coleccion.get(i).getId());
            listaInformacion3.add("Max Piezas: "+coleccion.get(i).getNum());
        }

    }
}