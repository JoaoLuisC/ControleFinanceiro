package br.edu.ifsuldeminas.mch.controlefinanceiro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    TextInputEditText txtUser, txtPassword;
    Button btnEnter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);

        txtUser = findViewById(R.id.loginMenuInputName);
        txtPassword = findViewById(R.id.loginMenuInputPassword);

        btnEnter = findViewById(R.id.loginMenuButtonEnter);
        btnEnter.setOnClickListener(view -> {
            if (txtUser.getText().toString().equals("admin") && txtPassword.getText().toString().equals("admin")) {
                Intent intentForm = new Intent(MainActivity.this, MenuActivity.class);
                startActivity(intentForm);
            }
        });
    }
}