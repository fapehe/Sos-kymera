package com.example.fapehe.sos_kymera;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PagInicio extends AppCompatActivity {

    Button ir_evento;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pag_inicio);
        ir_evento =(Button)findViewById(R.id.iniciar_sesion);
        ir_evento.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent= new Intent(PagInicio.this, Iniciar_sesion.class );
                startActivity(intent);
            }
        });
    }
}
