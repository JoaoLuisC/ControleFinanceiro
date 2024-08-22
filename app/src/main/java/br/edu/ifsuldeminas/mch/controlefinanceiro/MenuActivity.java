package br.edu.ifsuldeminas.mch.controlefinanceiro;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
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

        ImageButton btnLogout = findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(v -> {
            SharedPreferences preferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.clear();
            editor.apply();

            Intent intent = new Intent(MenuActivity.this, MainActivity.class);
            startActivity(intent);
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

        if (getIntent().hasExtra("USERNAME")) {
            String username = getIntent().getStringExtra("USERNAME");
            Toast.makeText(this, "Bem-vindo, " + username + "!", Toast.LENGTH_SHORT).show();
        }
    }

}
