package com.example.fapehe.sos_kymera;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class evento_1 extends AppCompatActivity {

    ImageButton Dir_mapa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evento_1);

        Dir_mapa = (ImageButton)findViewById(R.id.maps);
        Dir_mapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(evento_1.this, Mapa.class );
                startActivity(intent);
            }
        });

    }
}
