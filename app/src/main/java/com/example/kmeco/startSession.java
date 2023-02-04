package com.example.kmeco;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class startSession extends AppCompatActivity implements View.OnClickListener {

    TextView banner;
    Button signIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_session);
        //initializing variable
        TextView register = (TextView) findViewById(R.id.register);
        register.setOnClickListener(this);

        signIn = (Button) findViewById(R.id.signin);

        banner = (TextView) findViewById(R.id.banner);

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch(view.getId()) {
                    case R.id.signin:
                        Intent goToHome = new Intent(startSession.this, HomePage.class);
                        startActivity(goToHome);
                        break;
                }
            }
        });

        banner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch(view.getId()) {
                    case R.id.banner:
                        Intent goToMain = new Intent(startSession.this, HomePage.class);
                        startActivity(goToMain);
                        break;
                }
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //si el usuario clickea on registrar
            case R.id.register:
                if (Choice.isClient) {
                    startActivity(new Intent(this, RegisterUser.class));
                    break;
                } else if (Choice.isProvider) {
                    startActivity(new Intent(this, registerProvider.class));
                    break;
                }
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}