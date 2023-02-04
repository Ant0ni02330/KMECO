package com.example.kmeco;

import static java.lang.Thread.sleep;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class registerProvider extends AppCompatActivity implements View.OnClickListener {
    private FirebaseAuth mAuth;
    private TextView banner, button_loging;
    private EditText fullName, CIF, EMAIL, PASSWORD;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_provider);
        mAuth = FirebaseAuth.getInstance();
        banner = (TextView) findViewById(R.id.banner);
        banner.setOnClickListener(this);

        button_loging = (Button) findViewById(R.id.button_login);
        button_loging.setOnClickListener(this);
        fullName = (EditText) findViewById(R.id.full_name);
        CIF = (EditText) findViewById(R.id.cif);
        EMAIL = (EditText) findViewById(R.id.email_inicio);
        PASSWORD = (EditText) findViewById(R.id.password_inicio);

        progressBar = (ProgressBar) findViewById(R.id.progressBar2);
    }

    public void onClick(View v) {
        switch (v.getId()){
            case R.id.banner:
                Context packageContext = this;
                startActivity(new Intent(packageContext, MainActivity.class));
                break;
            case R.id.button_login:
                button_loging();
                break;
        }
    }

    private void button_loging() {
        String name = fullName.getText().toString().trim();
        String cif = CIF.getText().toString().trim();
        String email = EMAIL.getText().toString().trim();
        String password = PASSWORD.getText().toString().trim();

        if (name.isEmpty()) {
            fullName.setError("Necesario ingresar el nombre completo.");
            fullName.requestFocus();
            return;
        } else if (cif.isEmpty()) {
            CIF.setError("Necesario ingresar el CIF.");
            CIF.requestFocus();
            return;
        } else if (email.isEmpty()) {
            EMAIL.setError("Es necesario ingresar un Email.");
            EMAIL.requestFocus();
            return;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            EMAIL.setError("Ingresa una dirección de correo válida");
            EMAIL.requestFocus();
            return;
        } else if (password.isEmpty()) {
            PASSWORD.setError("Es obligatorio ingresar una contraseña.");
            PASSWORD.requestFocus();
            return;
        } else if (password.length() < 6) {
            PASSWORD.setError("La contraseña debe tener al menos 6 caracteres.");
            PASSWORD.requestFocus();
            return;
        } else {
            String sada="Users";
            progressBar.setVisibility(View.VISIBLE);
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Provider provider = new Provider(name, cif, email);
                                Toast.makeText(registerProvider.this, "El usuario se ha registrado.", Toast.LENGTH_LONG).show();
                                progressBar.setVisibility(View.GONE);
                                FirebaseDatabase.getInstance().getReference()
                                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .setValue(provider).addOnCompleteListener(new OnCompleteListener<Void>(){
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task){
                                                if(task.isSuccessful()){
                                                    Toast.makeText(registerProvider.this, "El usuario se ha registrado.", Toast.LENGTH_LONG).show();
                                                    progressBar.setVisibility(View.GONE);
                                                }else {
                                                    Toast.makeText(registerProvider.this, "Ha habido un fallo en el registro", Toast.LENGTH_LONG).show();
                                                    progressBar.setVisibility(View.GONE);
                                                }
                                            }
                                        });
                            } else {
                                Toast.makeText(registerProvider.this, "Failed to register", Toast.LENGTH_LONG).show();
                                progressBar.setVisibility(View.GONE);
                                Log.e("AUTH", "createUserWithEmailAndPassword failed", task.getException());
                            }
                        }
                    });
        }
        Intent goToAccess = new Intent(registerProvider.this, startSession.class);
        try {
            sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        startActivity(goToAccess);
    }
}