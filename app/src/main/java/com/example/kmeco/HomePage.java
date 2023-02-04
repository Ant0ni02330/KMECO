package com.example.kmeco;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class HomePage extends AppCompatActivity {

    TextView banner;
    ImageView image00, image01, image10, image11, image20, image21,
              image30,user, chat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        banner = (TextView) findViewById(R.id.banner);
        image00 = (ImageView) findViewById(R.id.image00);
        image01 = (ImageView) findViewById(R.id.image01);
        image10 = (ImageView) findViewById(R.id.image10);
        image11 = (ImageView) findViewById(R.id.image11);
        image20 = (ImageView) findViewById(R.id.image20);
        image21 = (ImageView) findViewById(R.id.image21);
        image30 = (ImageView) findViewById(R.id.image30);
        user = (ImageView) findViewById(R.id.user);
        chat = (ImageView) findViewById(R.id.chat);

        image00.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.image00:
                        Intent goToProduct = new Intent(HomePage.this, producto.class);
                        startActivity(goToProduct);
                    break;
                }
            }
        });

        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.user:
                        Intent goToUser = new Intent(HomePage.this, UserSettings.class);
                        startActivity(goToUser);
                    break;
                }
            }
        });

        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.chat:
                        Intent goToChat = new Intent(HomePage.this, generalChat.class);
                        startActivity(goToChat);
                    break;
                }
            }
        });
    }
}