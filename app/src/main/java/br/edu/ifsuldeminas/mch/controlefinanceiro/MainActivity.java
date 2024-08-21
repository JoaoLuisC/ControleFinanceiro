package br.edu.ifsuldeminas.mch.controlefinanceiro;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {

    private EditText usernameInput;
    private EditText passwordInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);

        // Obtém o EditText de dentro do TextInputLayout
        TextInputLayout usernameLayout = findViewById(R.id.loginMenuInputName);
        TextInputLayout passwordLayout = findViewById(R.id.loginMenuInputPassword);

        usernameInput = usernameLayout.getEditText();
        passwordInput = passwordLayout.getEditText();

        Button loginButton = findViewById(R.id.loginMenuButtonEnter);

        loginButton.setOnClickListener(v -> {
            String username = usernameInput.getText().toString();
            String password = passwordInput.getText().toString();

            if (username.equals("admin") && password.equals("admin")) {
                Intent intent = new Intent(MainActivity.this, MenuActivity.class);
                intent.putExtra("USERNAME", username);
                startActivity(intent);
            } else {
                Toast.makeText(MainActivity.this, "Login ou senha inválidos", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
