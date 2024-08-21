package br.edu.ifsuldeminas.mch.controlefinanceiro;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

        Button btnAddBill = findViewById(R.id.btnAddPayments);
        btnAddBill.setOnClickListener(view -> {
            Intent intent = new Intent(MenuActivity.this, AddActivity.class);
            startActivity(intent);
        });

        Button btnList = findViewById(R.id.btnListPayments);
        btnList.setOnClickListener(view -> {
            Intent intent = new Intent(MenuActivity.this, ListActivity.class);
            startActivity(intent);
        });

        Button btnCoins = findViewById(R.id.btnCoinsList);
        btnCoins.setOnClickListener(view -> {

            Toast toast = Toast.makeText(MenuActivity.this, "Será disponível na versão 2.0!", Toast.LENGTH_SHORT);
            toast.show();
//            Intent intent = new Intent(MenuActivity.this, CoinsActivity.class);
//            startActivity(intent);
        });


        String username = getIntent().getStringExtra("USERNAME");
        Toast.makeText(this, "Bem-vindo, " + username + "!", Toast.LENGTH_SHORT).show();
    }
}
