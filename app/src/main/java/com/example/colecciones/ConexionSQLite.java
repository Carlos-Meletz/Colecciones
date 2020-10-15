package com.example.colecciones;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

import com.example.colecciones.utilidades.Utilidades;

public class ConexionSQLite extends SQLiteOpenHelper {
    public ConexionSQLite(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Utilidades.CREAR_TABLA);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int vantigua, int vnueva) {
        db.execSQL("DROP TABLE IF EXISTS colecciones");
        onCreate(db);
    }
}
