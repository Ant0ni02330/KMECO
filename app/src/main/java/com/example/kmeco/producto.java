package com.example.kmeco;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class producto extends AppCompatActivity {

    Button cartBt;
    ImageView cartIV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producto);

        cartBt = (Button) findViewById(R.id.addCart);
        cartIV = (ImageView) findViewById(R.id.cart);

        cartBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.addCart:
                        Intent goToCart = new Intent(producto.this, Carrito.class);
                        startActivity(goToCart);
                        break;
                }
            }
        });

        cartIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.cart:
                        Intent goToCart = new Intent(producto.this, Carrito.class);
                        startActivity(goToCart);
                        break;
                }
            }
        });
    }
}