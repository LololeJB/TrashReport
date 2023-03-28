package com.example.trashreport;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button feur = findViewById(R.id.boutonfeur);
        Button flop = findViewById(R.id.boutonflop);

        feur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotomap = new Intent(MainActivity.this,MapDesDechets.class);
                startActivity(gotomap);
            }

        });
        flop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoInscription = new Intent(MainActivity.this,Inscription.class);
                startActivity(gotoInscription);
            }
        });
    }
}