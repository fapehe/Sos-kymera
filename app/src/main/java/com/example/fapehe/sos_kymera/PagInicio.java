package com.example.fapehe.sos_kymera;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class PagInicio extends AppCompatActivity {

    Button iniciar;
    ImageButton ir_evento;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pag_inicio);
        iniciar =(Button)findViewById(R.id.iniciar_sesion);
        iniciar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent= new Intent(PagInicio.this, Iniciar_sesion.class );
                startActivity(intent);
            }
        });

        ir_evento = (ImageButton) findViewById(R.id.evento);
        ir_evento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(PagInicio.this, evento_1.class );
                startActivity(intent);

            }
        });
    }
}
