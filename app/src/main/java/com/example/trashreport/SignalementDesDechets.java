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
    RadioGroup liste;
    EditText detail;
    Button signaler;
    Button retour;
    double latitude, longitude;
    SQLClient bdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signalementdesdechets);
        liste = findViewById(R.id.signalementdesdechets_liste);
        detail = findViewById(R.id.signalementdesdechets_text_autre);
        signaler = findViewById(R.id.signalementdesdechets_signaler);
        retour = findViewById(R.id.signalementdesdechets_retour);
        latitude = getIntent().getDoubleExtra("latitude",0);
        longitude = getIntent().getDoubleExtra("longitude",0);

        liste.check(R.id.signalementdesdechets_verre_plastique);
        detail.setVisibility(View.INVISIBLE);

        retour.setOnClickListener(v -> {
            finish();
        });
        if (liste.getCheckedRadioButtonId()== R.id.signalementdesdechets_verre_plastique){
            detail.setVisibility(View.INVISIBLE);
        } else {
            detail.setVisibility(View.VISIBLE);
        }
        liste.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (liste.getCheckedRadioButtonId()== R.id.signalementdesdechets_autre){
                    detail.setVisibility(View.VISIBLE);
                    if (detail.length() >=1) {
                        signaler.setEnabled(true);
                    }else {
                        signaler.setEnabled(false);
                    }
                } else {
                    detail.setVisibility(View.INVISIBLE);
                    signaler.setEnabled(true);
                }
            }
        });

        detail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (detail.length() >=1) {
                    signaler.setEnabled(true);
                }else {
                    signaler.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        signaler.setOnClickListener(v -> {
            bdd = new SQLClient(this);
            int idbutton = liste.getCheckedRadioButtonId();
            RadioButton button = findViewById(idbutton);
            String categorisation = button.toString();
            if (liste.getCheckedRadioButtonId()== R.id.signalementdesdechets_autre){
                categorisation = String.valueOf(detail.getText());
            }
            public static void insertGeoPoint(bdd, latitude, longitude, userid, categorisation);

            Intent connexion = new Intent(SignalementDesDechets.this,MainActivity.class);
            startActivity(connexion);
        });
    }
}