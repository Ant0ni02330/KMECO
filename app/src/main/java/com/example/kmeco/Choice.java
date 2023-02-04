package com.example.kmeco;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;

public class Choice extends AppCompatActivity {

    public static boolean isClient = false;
    public static boolean isProvider = false;

    private Button providerButton, clientButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);

        providerButton=(Button) findViewById(R.id.botonproveedor);
        clientButton=(Button) findViewById(R.id.botoncliente);

        providerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Choice.this, startSession.class);
                isProvider = true;
                startActivity(intent);
            }
        });

        clientButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Choice.this, startSession.class);
                isClient = true;
                startActivity(intent);
            }
        });
    }
}