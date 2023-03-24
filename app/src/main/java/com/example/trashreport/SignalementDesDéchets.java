package com.example.trashreport;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

public class SignalementDesDéchets extends AppCompatActivity {
    RadioButton bricolage;
    RadioButton verre_plastique;
    RadioGroup liste;
    RadioButton epave;
    RadioButton megot;
    RadioButton papier;
    RadioButton gravat;
    RadioButton autre;
    EditText detail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signalementdesdechets);
        liste = findViewById(R.id.signalementdesdechets_liste);
        bricolage  = findViewById(R.id.signalementdesdechets_bricolage);
        verre_plastique = findViewById(R.id.signalementdesdechets_verre_plastique);
        epave = findViewById(R.id.signalementdesdechets_epave);
        megot = findViewById(R.id.signalementdesdechets_megot);
        papier = findViewById(R.id.signalementdesdechets_papier);
        gravat = findViewById(R.id.signalementdesdechets_gravat);
        autre = findViewById(R.id.signalementdesdechets_autre);
        detail = findViewById(R.id.signalementdesdechets_text_autre);

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

        inscription.setOnClickListener(v -> {
            if (true){
                Intent connexion = new Intent(Inscription.this,MainActivity.class);
                startActivity(connexion);
            }

        });
    }
}