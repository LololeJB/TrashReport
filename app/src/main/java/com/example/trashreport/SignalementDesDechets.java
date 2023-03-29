package com.example.trashreport;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

import com.example.trashreport.Ressources.SQLClient;

public class SignalementDesDechets extends AppCompatActivity {
    RadioButton bricolage;
    RadioButton verre_plastique;
    RadioGroup liste;
    RadioButton epave;
    RadioButton megot;
    RadioButton papier;
    RadioButton gravat;
    RadioButton autre;
    EditText detail;
    Button signaler;
    Button retour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signalementdesdechets);
        liste = findViewById(R.id.signalementdesdechets_liste);
        detail = findViewById(R.id.signalementdesdechets_text_autre);
        signaler = findViewById(R.id.signalementdesdechets_signaler);
        retour = findViewById(R.id.signalementdesdechets_retour);

        liste.check(R.id.signalementdesdechets_verre_plastique);
        detail.setEnabled(false);
        detail.setActivated(false);
        detail.setClickable(false);
        detail.setVisibility(View.VISIBLE);

        retour.setOnClickListener(v -> {
            finish();
        });
        if (liste.getCheckedRadioButtonId()== R.id.signalementdesdechets_verre_plastique){
            detail.setEnabled(true);
        } else {
            detail.setEnabled(false);
        }

        signaler.setOnClickListener(v -> {
            if (liste.getCheckedRadioButtonId()== R.id.signalementdesdechets_verre_plastique){
                String detailText = String.valueOf(detail.getText());
            }
            Intent connexion = new Intent(SignalementDesDechets.this,MainActivity.class);
            startActivity(connexion);
        });
    }
}