package com.example.kmeco;


import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.StrictMode;

import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

public class AddProductActivity extends AppCompatActivity implements View.OnClickListener {

    //Creating variables
    ImageButton backArrow;
    EditText productName, quantity, pricePerUnit, description;
    ImageView productPicture;
    Button insertImagebt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);


        backArrow = (ImageButton) findViewById(R.id.backArrow);

        productName = (EditText) findViewById(R.id.ProductName);
        quantity = (EditText) findViewById(R.id.Quantity);
        pricePerUnit = (EditText) findViewById(R.id.PricePerUnit);
        description = (EditText) findViewById(R.id.Description);

        productPicture = (ImageView) findViewById(R.id.ProductPicture);

        insertImagebt = (Button) findViewById(R.id.InsertImagebt);

        productPicture.setOnClickListener((View.OnClickListener) AddProductActivity.this);
        insertImagebt.setOnClickListener((View.OnClickListener) AddProductActivity.this);

    }

    @Override
    public void onClick(View view) {
        Intent openGallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

    }

    ActivityResultLauncher<Intent> camaraLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK) {
                        Bundle extras = result.getData().getExtras();
                        Bitmap imgBitmap = (Bitmap) extras.get("data");
                        productPicture.setImageBitmap(imgBitmap);
                    }
                }
            });
}

