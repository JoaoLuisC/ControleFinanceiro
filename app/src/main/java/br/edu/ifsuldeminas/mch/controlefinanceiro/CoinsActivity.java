package br.edu.ifsuldeminas.mch.controlefinanceiro;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import br.edu.ifsuldeminas.mch.controlefinanceiro.model.Currency;
import br.edu.ifsuldeminas.mch.controlefinanceiro.model.CurrencyService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CoinsActivity extends AppCompatActivity {
    private static final String BASE_URL = "https://economia.awesomeapi.com.br/";

    private ListView coinsListView;

    Map<String, Currency> coinsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.currency_quote_page);


        coinsListView = findViewById(R.id.listCoins);

        // Configura o Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CurrencyService service = retrofit.create(CurrencyService.class);

        String coins = "USD-BRL,EUR-BRL,BTC-BRL,GBP-BRL,CAD-BRL,AUD-BRL,CHF-BRL,CNY-BRL,JPY-BRL,MXN-BRL,SEK-BRL,NZD-BRL,TRY-BRL,SGD-BRL";

        // Faz a chamada à API
        Call<Map<String, Currency>> call = service.getExchangeRates(coins);

        // Executa a requisição de forma assíncrona
        call.enqueue(new Callback<Map<String, Currency>>() {
            @Override
            public void onResponse(Call<Map<String, Currency>> call, Response<Map<String, Currency>> response) {
                if (response.isSuccessful()) {
                    coinsList = response.body();
                    updateCoins();

                } else {
                    //  txt.setText("Retrofit"+ "Erro na resposta: " + response.message());
                    Log.e("Retrofit", "Erro na resposta: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<Map<String, Currency>> call, Throwable t) {
                // txt.setText("Retrofit"+ "Erro na requisição: " + t.getMessage());
                Log.e("Retrofit", "Erro na requisição: " + t.getMessage());
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void updateCoins() {
        List<Currency> currencies = new ArrayList<>();

        for (Map.Entry<String, Currency> entry : coinsList.entrySet()) {
            currencies.add(entry.getValue());
        }

        ArrayAdapter<Currency> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, currencies);
        coinsListView.setAdapter(arrayAdapter);
    }
}
