package com.example.kmeco;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class add_product extends AppCompatActivity {

    private TextView name;
    private ImageButton addProdcut, profileImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product_home);

        name = findViewById(R.id.name);
        addProdcut = findViewById(R.id.addProduct);
        profileImage = findViewById(R.id.profileImage);

        //include DB here:

        /*profileImage.setOnClickListener(view -> {
            //edit profile
            startActivity(new Intent(add_product.this, ProfileEditProviderActivity.class)); SI HAY TIEMPO SE HACE EL EDITAR PERFIL
        });*/

        addProdcut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // open edit add product activity
                startActivity(new Intent(add_product.this, AddProductActivity.class));
            }
        });
    }
}