package com.example.kmeco;


import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainProvider extends AppCompatActivity{


    TextView register;
    Button signIn;

    static String TAG = "Main";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider);

        register=(TextView) findViewById(R.id.register_provider);
        signIn = (Button) findViewById(R.id.signin);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.register_provider:
                        Intent goToRegisterProvider = new Intent(MainProvider.this, registerProvider.class);
                        startActivity(goToRegisterProvider);
                        break;
                }
            }
        });

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.signin:
                        Intent goToHome = new Intent(MainProvider.this, HomePage.class);
                        startActivity(goToHome);
                        break;
                }
            }
        });
    }
}