package com.example.colecciones;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.speech.tts.UtteranceProgressListener;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import com.example.colecciones.tablasdb.Colecctores;
import com.example.colecciones.utilidades.Utilidades;

import java.util.ArrayList;

public class Agregar extends AppCompatActivity {
    Spinner combocole;
    EditText edt1,edt2;

    ArrayList<String> listaInformacion;
    ArrayList<Colecctores> coleccion;
    ConexionSQLite conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar);

        combocole = (Spinner) findViewById(R.id.combocoleccion);
        edt1 = (EditText) findViewById(R.id.incod);
        edt2 = (EditText) findViewById(R.id.innom);

        conn = new ConexionSQLite(getApplicationContext(),"bd_colecciones",null,1);
        consultarLista();
        ArrayAdapter<CharSequence> adaptor = new ArrayAdapter(this, android.R.layout.simple_list_item_1,listaInformacion);
        combocole.setAdapter(adaptor);

        combocole.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long idl) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }
    private void registrarpieza() {
        SQLiteDatabase db = conn.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Utilidades.CAMPO_COD_PIEZAS,edt1.getText().toString());
        values.put(Utilidades.CAMPO_NOM_PIEZAS,edt2.getText().toString());

        int idcombo = (int) combocole.getSelectedItemId();
        if(idcombo!=0){
            Log.i("Tamaño",coleccion.size()+"");
            Log.i("id combo",idcombo+"");
            Log.i("id combo - 1",(idcombo-1)+"");
            int idcole = coleccion.get(idcombo-1).getId();
            Log.i("id_cole",idcole+"");
            values.put(Utilidades.CAMPO_IDCOLE,idcole);
            Long idResul = db.insert(Utilidades.TABLA_PIEZAS,Utilidades.CAMPO_ID_PIEZAS,values);

            Toast.makeText(getApplicationContext(),"Id registro: "+idResul,Toast.LENGTH_SHORT).show();
            db.close();
        }else{
            Toast.makeText(getApplicationContext(),"Debe seleccionar una Coleccion",Toast.LENGTH_LONG).show();
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

    public void onClick2(View view){
        switch (view.getId()){
            case R.id.btngp: registrarpieza();
        }
    }
}