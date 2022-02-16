package com.app.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Inicio extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private Button btnLogOut;
    private TextView nombre, email;
    private ImageView imgUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        btnLogOut = findViewById(R.id.btnCerrarSesion);
        nombre = findViewById(R.id.Nombre);
        email = findViewById(R.id.Correo);
        imgUser = findViewById(R.id.imgPerosna);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        nombre.setText(currentUser.getDisplayName());
        email.setText(currentUser.getEmail());
        Glide.with(this).load(currentUser.getPhotoUrl()).into(imgUser);

        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                //Abrir MainActivity
                Intent mainActivity = new Intent(getApplicationContext(),
                        MainActivity.class);
                startActivity(mainActivity);
                Inicio.this.finish();

            }
        });



    }
}