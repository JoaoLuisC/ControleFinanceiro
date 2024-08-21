package br.edu.ifsuldeminas.mch.controlefinanceiro;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MenuActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu_page);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Encontrar o botão "Sair" e definir o listener
        ImageButton btnSair = findViewById(R.id.btn_sair);
        btnSair.setOnClickListener(v -> {
            // Criar um Intent para voltar à atividade de login
            Intent intent = new Intent(MenuActivity.this, MainActivity.class);
            startActivity(intent);
            // Opcional: Finaliza a atividade atual para que o usuário não possa voltar a ela
            finish();
        });

        String username = getIntent().getStringExtra("USERNAME");
        Toast.makeText(this, "Bem-vindo, " + username + "!", Toast.LENGTH_SHORT).show();
    }
}
