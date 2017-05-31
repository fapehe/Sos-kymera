package com.example.fapehe.sos_kymera;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.NetworkInfo;
import android.provider.BaseColumns;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by fabian on 17/05/2017.
 */

public abstract class Registrador extends SQLiteOpenHelper implements DownloadCallback{

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Usuarios.db";

    public Registrador(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(DatosTabla.Crear_Tabla);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(DatosTabla.SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public static class DatosTabla implements BaseColumns {
            public static final String NOMBRE_TABLA = "Users";
            public static final String COLUMNA_USUARIO = "user";
            public static final String COLUMNA_NOMBRE = "nombre";
            public static final String COLUMNA_EDAD = "edad";
            public static final String COLUMNA_EMAIL = "email";
            public static final String COLUMNA_PASSWORD = "password";

            private static final String TEXT_TYPE = " TEXT";
            private static final String COMMA_SEP = ",";
            private static final String Crear_Tabla =
            "CREATE TABLE " + DatosTabla.NOMBRE_TABLA + " (" +
            DatosTabla.COLUMNA_USUARIO + " INTEGER PRIMARY KEY," +
            DatosTabla.COLUMNA_NOMBRE + TEXT_TYPE + COMMA_SEP +
            DatosTabla.COLUMNA_EDAD + TEXT_TYPE + COMMA_SEP +
            DatosTabla.COLUMNA_EMAIL + TEXT_TYPE+ COMMA_SEP+
            DatosTabla.COLUMNA_PASSWORD + TEXT_TYPE+ " )";

            private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + DatosTabla.NOMBRE_TABLA;
    }

}
