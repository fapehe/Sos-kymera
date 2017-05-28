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

public class Registrador extends SQLiteOpenHelper implements DownloadCallback{

    @Override
    public void updateFromDownload(Object result) {

    }

    @Override
    public NetworkInfo getActiveNetworkInfo() {
        return null;
    }

    @Override
    public void onProgressUpdate(int progressCode, int percentComplete) {

    }

    @Override
    public void finishDownloading() {

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
        onCreate(db );
    }
    private String downloadUrl(URL url) throws IOException {
        InputStream stream = null;
        HttpsURLConnection connection = null;
        String result = null;
        try {
            connection = (HttpsURLConnection) url.openConnection();
            // Timeout for reading InputStream arbitrarily set to 3000ms.
            connection.setReadTimeout(3000);
            // Timeout for connection.connect() arbitrarily set to 3000ms.
            connection.setConnectTimeout(3000);
            // For this use case, set HTTP method to GET.
            connection.setRequestMethod("GET");
            // Already true by default but setting just in case; needs to be true since this request
            // is carrying an input (response) body.
            connection.setDoInput(true);
            // Open communications link (network traffic occurs here).
            connection.connect();
            int responseCode = connection.getResponseCode();
            if (responseCode != HttpsURLConnection.HTTP_OK) {
                throw new IOException("HTTP error code: " + responseCode);
            }
            // Retrieve the response body as an InputStream.
            stream = connection.getInputStream();

            if (stream != null) {
                // Converts Stream to String with max length of 500.
                result = readStream(stream, 500);
            }
        } finally {
            // Close Stream and disconnect HTTPS connection.
            if (stream != null) {
                stream.close();
            }
            if (connection != null) {
                connection.disconnect();
            }
        }
        return result;
    }

    private String readStream(InputStream stream, int maxLength) throws IOException {
        String result = null;
        // Read InputStream using the UTF-8 charset.
        InputStreamReader reader = new InputStreamReader(stream, "UTF-8");
        // Create temporary buffer to hold Stream data with specified max length.
        char[] buffer = new char[maxLength];
        // Populate temporary buffer with Stream data.
        int numChars = 0;
        int readSize = 0;
        while (numChars < maxLength && readSize != -1) {
            numChars += readSize;
            int pct = (100 * numChars) / maxLength;

            readSize = reader.read(buffer, numChars, buffer.length - numChars);
        }
        if (numChars != -1) {
            // The stream was not empty.
            // Create String that is actual length of response body if actual length was less than
            // max length.
            numChars = Math.min(numChars, maxLength);
            result = new String(buffer, 0, numChars);
        }
        return result;
    }



}
