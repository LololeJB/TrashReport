package com.example.trashreport;

import static com.example.trashreport.Ressources.UserDAO.countOccurencesOfEmail;
import static com.example.trashreport.Ressources.UserDAO.selectPasswordbyEmail;
import static com.example.trashreport.Ressources.UserDAO.verifPasswordandEmail;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.trashreport.Ressources.SQLClient;

public class MainActivity extends AppCompatActivity {
    SQLClient bdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button connection = findViewById(R.id.boutonSeConnecter);
        Button inscription = findViewById(R.id.boutonSinscrire);
        EditText email = findViewById(R.id.Email);
        EditText password = findViewById(R.id.MotDePasse);
        bdd = new SQLClient(this);

        connection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (countOccurencesOfEmail(bdd, String.valueOf(email.getText()))==1){
                    String mypwd = selectPasswordbyEmail(bdd,String.valueOf(email.getText()));
                    String thepwd = String.valueOf(password.getText());
                    if(verifPasswordandEmail(bdd, String.valueOf(email.getText()),String.valueOf(password.getText())) ==1) {
                        Intent gotomap = new Intent(MainActivity.this, MapDesDechets.class);
                        startActivity(gotomap);
                    }
                }
            }

        });
        inscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoInscription = new Intent(MainActivity.this,Inscription.class);
                startActivity(gotoInscription);
            }
        });
    }
}