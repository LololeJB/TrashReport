package com.example.trashreport;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class Inscription extends AppCompatActivity {
    Button inscription;
    Button retour;
    EditText EMail;
    EditText MDP;
    EditText MDPVrai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inscription);
        inscription  = findViewById(R.id.Inscription_Retour);
        retour = findViewById(R.id.Inscription_Valider);
        EMail = findViewById(R.id.Inscription_EmailAddress);
        MDP = findViewById(R.id.Inscription_Password1);
        MDPVrai = findViewById(R.id.Inscription_Password2);
        inscription.setClickable(false);

        retour.setOnClickListener(v -> {
            Intent connexion = new Intent(Inscription.this,MainActivity.class);
            startActivity(connexion);
        });

        String eMailText = String.valueOf(EMail.getText());
        String mDPText = String.valueOf(MDP.getText());
        String mDPVraiText = String.valueOf(MDPVrai.getText());

        if (mDPText.equals(mDPVraiText)){
            inscription.setClickable(!mDPText.isEmpty());
        }else {
            inscription.setClickable(false);
        }
    }
}
