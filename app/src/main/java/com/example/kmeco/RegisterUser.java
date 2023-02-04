package com.example.kmeco;

//EL PROBLEMA ESTA AQUI

import static java.lang.Thread.sleep;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

public class RegisterUser extends AppCompatActivity implements View.OnClickListener{

    //creating the varables to use
    private TextView banner, registerUserButton; //registerUser es el botton
    private EditText eTfullName, eTage, eTemail, eTpassword;
    private ProgressBar pBprogressbar;

    private FirebaseAuth mAuth; //creating the database

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder(StrictMode.getVmPolicy())
                .detectLeakedClosableObjects()
                .build());

        mAuth = FirebaseAuth.getInstance(); // initalizing the database

        //initializing the varibales
        banner = (TextView) findViewById(R.id.banner); //looking for Banner
        banner.setOnClickListener(this); //so it goes to the start and acts like a button

        registerUserButton = (Button) findViewById(R.id.registerUser);
        registerUserButton.setOnClickListener(this);

        eTfullName = (EditText) findViewById(R.id.fullname);
        eTage = (EditText) findViewById(R.id.age);
        eTemail = (EditText) findViewById(R.id.email);
        eTpassword = (EditText) findViewById(R.id.password);

        pBprogressbar = (ProgressBar) findViewById(R.id.progressbar);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.banner:
                startActivity(new Intent(this, MainActivity.class));
                break;
            case R.id.registerUser:
                button_login();
                break;
        }
    }

    private void button_login() {
        //first we need to convert each input to a string
        String email =  eTemail.getText().toString().trim();
        String password = eTpassword.getText().toString().trim();
        String fullname = eTfullName.getText().toString().trim();
        String age = eTage.getText().toString().trim();

        if(fullname.isEmpty()){
            eTfullName.setError("Necesario ingresar el nombre completo.");
            //so it sets focus on the field
            eTfullName.requestFocus();
            return;
        }

        if(age.isEmpty()){
            eTage.setError("Necesario ingresar una Edad.");
            eTage.requestFocus();
            try {
                int ageInt = Integer.parseInt(age);
                if (ageInt < 18){
                    eTage.setError("Debes ser mayor de edad para utilizar kmEco.");
                }
            } catch (NumberFormatException ex){
                eTage.setError("Debes de ingresar un Número.");
            }
            return;
        }

        if(email.isEmpty()){
            eTemail.setError("Es necesario ingresar un Email.");
            eTemail.requestFocus();
            return;
        }
        //to check if the email is a correct email
        //if it doesn't match -> Throw the error
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            eTemail.setError("Ingresa una dirección de correo válida");
            eTemail.requestFocus();
            return;
        }

        if(password.isEmpty()){
            eTpassword.setError("Es obligatorio ingresar una contraseña.");
            eTpassword.requestFocus();
            return;
        }
        //firebase doesn't accept password < of 6 char
        if(password.length()<6){
            eTpassword.setError("La contraseña debe tener al menos 6 caracteres.");
            eTpassword.requestFocus();
            return;
        }

        //Setting the visibility of the progressbat to true
        pBprogressbar.setVisibility(View.VISIBLE);

        //DATABASE
        //Checking if the user is already registered
        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        //if the user has been registered
                        if(task.isSuccessful()){
                            User user = new User(fullname, age, email);
                            Toast.makeText(RegisterUser.this, "El usuario se ha registrado.", Toast.LENGTH_LONG).show();
                            pBprogressbar.setVisibility(View.GONE);

                            //sending the user to the DATABASE
                            //not using it
                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid()) //This will return the ID of the Registered User
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            //If the user has been registered and has been inserted into the DB
                                            if(task.isSuccessful()){
                                                Toast.makeText(RegisterUser.this, "El usuario se ha registrado.", Toast.LENGTH_LONG).show();
                                                pBprogressbar.setVisibility(View.GONE);
                                            }
                                            //if the user doesn't register
                                            else{
                                                Toast.makeText(RegisterUser.this, "Ha habido un fallo en el registro", Toast.LENGTH_LONG).show();
                                                pBprogressbar.setVisibility(View.GONE);
                                                Log.e("AUTH", "createUserWithEmailAndPassword failed", task.getException());
                                            }
                                        }
                                    });
                        } else {
                            Toast.makeText(RegisterUser.this, "Ha habido un fallo en el registro", Toast.LENGTH_LONG).show();
                            pBprogressbar.setVisibility(View.GONE);
                            Log.e("AUTH", "createUserWithEmailAndPassword failed", task.getException());
                        }
                    }
                });
        //here
        Intent goToAccess = new Intent(RegisterUser.this, startSession.class);
        try {
            sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        startActivity(goToAccess);
    }
}