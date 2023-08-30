package com.example.pichanguea;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn_ingresar=findViewById(R.id.btn_ingresar);
        btn_ingresar.setOnClickListener( new View.OnClickListener(){
                                         @Override
                                         public void onClick(View view){
                                             navegarToLogin();
                                         }
                                     }
        );

        Button btn_registrar=findViewById(R.id.btn_registrar);
        btn_registrar.setOnClickListener( new View.OnClickListener(){
                                             @Override
                                             public void onClick(View view){
                                                 navegartoRegister();
                                             }
                                         }
        );
    }
    public void navegarToLogin(){
        Intent intent =new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void navegartoRegister(){
        Intent intent =new Intent(this, RegistrerActivity.class);
        startActivity(intent);
    }
}