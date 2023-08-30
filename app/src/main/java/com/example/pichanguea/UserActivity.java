package com.example.pichanguea;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class UserActivity extends AppCompatActivity {

    private EditText txtUser;
    private EditText txtPass;
    private Button btnSaveChanges;
    private ImageButton btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        txtUser = findViewById(R.id.txt_user);
        txtPass = findViewById(R.id.txt_pass);
        btnSaveChanges = findViewById(R.id.btnGuardar);
        btnBack = findViewById(R.id.btn_back);

        btnBack.setOnClickListener( new View.OnClickListener(){
                                               @Override
                                               public void onClick(View view){
                                                   goBack();
                                               }
                                           }
        );

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            String currentUser = user.getEmail();
            String currentPassword = "";

            txtUser.setText(currentUser);
            txtPass.setText(currentPassword);
        }

        btnSaveChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateEmail();
                updatePass();
            }
        });


    }

    private void updateEmail(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String newEmail = txtUser.getText().toString();

        if(user != null && user.getEmail() != null){
            user.updateEmail(newEmail)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(getApplicationContext(), "Correo electrónico actualizado", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getApplicationContext(), "Error al actualizar el correo electrónico: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();;
                            }
                        }
                    });
        }
    }

    private void updatePass(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String newPassword = txtPass.getText().toString();

        if(user!=null && user.getEmail() != null){
            user.updatePassword(newPassword)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(getApplicationContext(), "Contraseña actualizada", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getApplicationContext(), "Error al actualizar la contraseña" +task.getException().getMessage(),  Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }
    private void goBack() {
        Intent intent = new Intent(getApplicationContext(), PagPrincipalActivity.class);
        startActivity(intent);
        finish();
    }

}
