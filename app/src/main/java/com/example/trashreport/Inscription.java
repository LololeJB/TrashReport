package com.example.trashreport;

import static com.example.trashreport.Ressources.UserDAO.InsertUser;
import static com.example.trashreport.Ressources.UserDAO.countOccurencesOfEmail;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.trashreport.Ressources.SQLClient;

public class Inscription extends AppCompatActivity {
    Button inscription;
    Button retour;
    EditText EMail,MDP,MDPVrai;
    SQLClient bdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inscription);
        inscription  = findViewById(R.id.Inscription_Valider);
        retour = findViewById(R.id.Inscription_Retour);
        EMail = findViewById(R.id.Inscription_EmailAddress);
        MDP = findViewById(R.id.Inscription_Password1);
        MDPVrai = findViewById(R.id.Inscription_Password2);

        bdd = new SQLClient(this);
        inscription.setEnabled(false);

        retour.setOnClickListener(v -> {
            finish();
        });

        String eMailText = String.valueOf(EMail.getText());
        String mDPText = String.valueOf(MDP.getText());
        String mDPVraiText = String.valueOf(MDPVrai.getText());

        MDPVrai.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (MDP.getText().equals(MDPVrai.getText())){
                    inscription.setEnabled(!String.valueOf(MDP.getText()).isEmpty());
                }else {
                    inscription.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (String.valueOf(MDP.getText()).equals(String.valueOf(MDPVrai.getText()))){
                    inscription.setEnabled(!String.valueOf(MDP.getText()).isEmpty());
                }else {
                    inscription.setEnabled(false);
                }
            }
        });
        MDP.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (MDP.getText().equals(MDPVrai.getText())){
                    inscription.setEnabled(!String.valueOf(MDP.getText()).isEmpty());
                }else {
                    inscription.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (String.valueOf(MDP.getText()).equals(String.valueOf(MDPVrai.getText()))){
                    inscription.setEnabled(!String.valueOf(MDP.getText()).isEmpty());
                }else {
                    inscription.setEnabled(false);
                }
            }
        });

        inscription.setOnClickListener(v -> {
            if (countOccurencesOfEmail(bdd, String.valueOf(EMail.getText()))==0){
                InsertUser(bdd, String.valueOf(EMail.getText()), String.valueOf(MDP.getText()));
                finish();
            }

        });
    }
}
