package com.example.fapehe.sos_kymera;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CreadorDB extends AppCompatActivity {

    Button B_registro;
    EditText Usuario,Nombre,Edad,Email,Password;

    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        B_registro=(Button)findViewById(R.id.Registro);
        Usuario =(EditText)findViewById(R.id.Usuario);
        Nombre=(EditText)findViewById(R.id.nombre);
        Edad=(EditText)findViewById(R.id.edad);
        Email=(EditText)findViewById(R.id.email_registro);
        Password=(EditText)findViewById(R.id.password_registro);

        final Registrador registroDB = new Registrador(getApplicationContext()) {
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
        };

        B_registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SQLiteDatabase db = registroDB.getWritableDatabase();
                ContentValues valores = new ContentValues();
                valores.put(Registrador.DatosTabla.COLUMNA_USUARIO,Usuario.getText().toString());
                valores.put(Registrador.DatosTabla.COLUMNA_NOMBRE,Nombre.getText().toString());
                valores.put(Registrador.DatosTabla.COLUMNA_EDAD,Edad.getText().toString());
                valores.put(Registrador.DatosTabla.COLUMNA_EMAIL,Email.getText().toString());
                valores.put(Registrador.DatosTabla.COLUMNA_PASSWORD,Password.getText().toString());

                long Guardado =db.insert(Registrador.DatosTabla.NOMBRE_TABLA, Registrador.DatosTabla.COLUMNA_USUARIO,valores);
                Toast.makeText(getApplicationContext(),"El regisrtro ha sido un exito",Toast.LENGTH_LONG).show();
            }

        });
    }
}
