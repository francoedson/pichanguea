package com.example.pichanguea;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class PagPrincipalActivity extends AppCompatActivity {
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    LinearLayout btnLogOut, btnEditProfile;
    TextView tvDetailUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pag_principal);
        firebaseAuth = FirebaseAuth.getInstance();
        btnLogOut = findViewById(R.id.btnLogOut);
        btnEditProfile = findViewById(R.id.btnEdit);
        tvDetailUser = findViewById(R.id.textView2);
        firebaseUser  = firebaseAuth.getCurrentUser();

        if (firebaseUser == null) {
            goLogin();
        } else {
            tvDetailUser.setText(firebaseUser.getEmail());
        }
        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                goLogin();
            }
        });

        btnEditProfile.setOnClickListener( new View.OnClickListener(){
                                              @Override
                                              public void onClick(View view){
                                                  goEditUser();
                                              }
                                          }
        );
    }
    private void goLogin() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void goEditUser() {
        Intent intent = new Intent(getApplicationContext(), UserActivity.class);
        startActivity(intent);
        finish();
    }
}