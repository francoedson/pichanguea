package com.example.pichanguea;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.ktx.Firebase;

public class RegistrerActivity extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    TextInputEditText etEmail,etPass;
    Button btnRegistrar;
    String email,password;

    @Override
    public void onStart(){
        super.onStart();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if (firebaseUser != null)
        {
            Intent intent = new Intent(getApplicationContext(), PagPrincipalActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrer);
        firebaseAuth = FirebaseAuth.getInstance();
        etEmail = findViewById(R.id.txt_user);
        etPass = findViewById(R.id.txt_pass);
        btnRegistrar = findViewById(R.id.btn_newUser);

        ImageButton btn_back = findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            backToMain();
                                        }
                                    }
        );

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = String.valueOf(etEmail.getText());
                password = String.valueOf(etPass.getText());

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(RegistrerActivity.this, "Ingresa el Email", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(RegistrerActivity.this, "Ingresa el Password", Toast.LENGTH_SHORT).show();
                    return;
                }
                firebaseAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(RegistrerActivity.this, "Usuario creado", Toast.LENGTH_SHORT).show();

                                    // Si la creación de la cuenta es exitosa, inicia la actividad PagPrincipalActivity
                                    Intent intent = new Intent(RegistrerActivity.this, PagPrincipalActivity.class);
                                    startActivity(intent);
                                    finish();

                                } else {
                                    // Si falla la autenticación, muestra un mensaje al usuario.
                                    Toast.makeText(RegistrerActivity.this, "Fallo de autenticacion", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

            }
        });
    }
    public void backToMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}