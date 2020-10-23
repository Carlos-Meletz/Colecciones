package com.example.colecciones.utilidades;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.colecciones.R;

import java.util.ArrayList;

public class MyAdapter extends ArrayAdapter<String> {
    Context context;
    ArrayList rnombre;
    ArrayList rcodigo;
    ArrayList rpiezas;

    public MyAdapter(Context c, ArrayList nombre, ArrayList codigo, ArrayList piezas) {
        super(c, R.layout.row, R.id.textView1, nombre);
        this.context = c;
        this.rnombre = nombre;
        this.rcodigo = codigo;
        this.rpiezas = piezas;

    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = layoutInflater.inflate(R.layout.row, parent, false);
        TextView myName = row.findViewById(R.id.textView1);
        TextView myCode = row.findViewById(R.id.textView2);
        TextView myPie = row.findViewById(R.id.textView3);

        // now set our resources on views
        myName.setText(rnombre.get(position).toString());
        myCode.setText(rcodigo.get(position).toString());
        myPie.setText(rpiezas.get(position).toString());
        return row;
    }
}
