package com.example.trashreport;

import static com.example.trashreport.Ressources.GeoPointsDAO.insertGeoPoint;
import static com.example.trashreport.Ressources.GlobalVars.getUserId;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.trashreport.Ressources.SQLClient;

import java.util.UUID;

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

        retour.setOnClickListener(v -> finish());
        if (liste.getCheckedRadioButtonId()== R.id.signalementdesdechets_verre_plastique){
            detail.setVisibility(View.INVISIBLE);
        } else {
            detail.setVisibility(View.VISIBLE);
        }
        liste.setOnCheckedChangeListener((radioGroup, i) -> {
            if (liste.getCheckedRadioButtonId()== R.id.signalementdesdechets_autre){
                detail.setVisibility(View.VISIBLE);
                signaler.setEnabled(detail.length() >= 1);
            } else {
                detail.setVisibility(View.INVISIBLE);
                signaler.setEnabled(true);
            }
        });

        detail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                signaler.setEnabled(detail.length() >= 1);
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
            insertGeoPoint(bdd, latitude, longitude, getUserId(), categorisation);

            Intent connexion = new Intent(SignalementDesDechets.this,MainActivity.class);
            startActivity(connexion);
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflaterMenu = getMenuInflater();
        inflaterMenu.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.quitmenu:
                Intent connexion = new Intent(SignalementDesDechets.this,MainActivity.class);
                startActivity(connexion);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);
    }
}