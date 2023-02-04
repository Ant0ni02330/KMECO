package com.example.kmeco;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class generalChat extends AppCompatActivity {

    Button home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general_chat);

        home = (Button) findViewById(R.id.botoninicio);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.botoninicio:
                        Intent goToHome = new Intent(generalChat.this, HomePage.class);
                        startActivity(goToHome);
                    break;
                }
            }
        });
    }
}